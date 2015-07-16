/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards;

import Sirius.navigator.connection.SessionManager;

import org.apache.log4j.Logger;

import org.openide.WizardDescriptor;
import org.openide.util.Cancellable;

import java.awt.Component;
import java.awt.EventQueue;

import java.io.File;
import java.io.FileOutputStream;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.concurrent.Future;

import de.cismet.cids.custom.switchon.AbstractWizardPanel;
import de.cismet.cids.custom.switchon.StatusPanel;
import de.cismet.cids.custom.switchon.concurrent.SwitchonConcurrency;

import de.cismet.cids.server.actions.ServerActionParameter;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public final class WizardPanelFileExportProgress extends AbstractWizardPanel implements Cancellable {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient Logger LOG = Logger.getLogger(WizardPanelFileExportProgress.class);

    //~ Instance fields --------------------------------------------------------

    private final transient Object lock;

    private transient Future exportTask;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new TimeSeriesExportWizardPanelConvert object.
     */
    public WizardPanelFileExportProgress() {
        this.lock = new Object();
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new StatusPanel("CSV Export"); // NOI18N
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        synchronized (lock) {
            final File exportFile = (File)wizard.getProperty(WizardPanelFileExport.PROP_EXPORT_FILE);
            assert exportFile != null : "export file must not be null"; // NOI18N

            exportTask = SwitchonConcurrency.getSwitchonGeneralPurposePool().submit(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                setStatusEDT(true,
                                    "Exporting content of Meta-Data Repository\n to CSV"); // NOI18N

                                LOG.info("Exporting Meta-Data Repository to file '" + exportFile + "'");
                                final FileOutputStream fos = new FileOutputStream(exportFile);

                                final ServerActionParameter zipParameter = new ServerActionParameter("zip", true);
                                final Object zipFile = SessionManager.getProxy()
                                            .executeTask("csvExportAction", "SWITCHON", null, zipParameter);

                                if (byte[].class.isAssignableFrom(zipFile.getClass())
                                            || Byte[].class.isAssignableFrom(zipFile.getClass())) {
                                    LOG.info("Meta-Data Repository successfully exported to file '" + exportFile + "'");
                                    fos.write((byte[])zipFile);
                                    fos.close();
                                    setStatusEDT(false,
                                        "Meta-Data Repository successfully\n exported to CSV"); // NOI18N
                                } else {
                                    final String message = "cannot convert zip object '"
                                                + zipFile.getClass() + "' to byte[]!";
                                    throw new Exception(message);
                                }

                                synchronized (lock) {
                                    WizardPanelFileExportProgress.this.exportTask = null;
                                }
                            } catch (final Throwable ex) {
                                LOG.error("Could not export Meta-Data Repository to CSV: "
                                            + ex.getMessage(), ex); // NOI18N
                                setStatusEDT(
                                    false,
                                    "Could not export Meta-Data Repository to CSV:\n "
                                            + ex.getMessage());

                                if (ex instanceof Error) {
                                    throw (Error)ex;
                                }
                            } finally {
                                changeSupport.fireChange();
                            }
                        }
                    });
        }
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        // noop
    }

    @Override
    public boolean cancel() {
        synchronized (lock) {
            if (exportTask != null) {
                if (!exportTask.cancel(true)) {
                    if (exportTask.isDone()) {
                        // noop
                    } else {
                        LOG.warn("export task could not be cancelled"); // NOI18N

                        return false;
                    }
                }
            }

            return true;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param  busy     DOCUMENT ME!
     * @param  message  DOCUMENT ME!
     */
    private void setStatusEDT(final boolean busy, final String message) {
        EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    ((StatusPanel)getComponent()).setBusy(busy);
                    ((StatusPanel)getComponent()).setStatusMessage(message);
                }
            });
    }
}
