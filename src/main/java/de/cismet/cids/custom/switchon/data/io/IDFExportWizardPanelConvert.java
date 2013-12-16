/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.data.io;

import org.apache.log4j.Logger;

import org.openide.WizardDescriptor;
import org.openide.util.Cancellable;
import org.openide.util.NbBundle;

import java.awt.Component;
import java.awt.EventQueue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.util.concurrent.Future;

import de.cismet.cids.custom.switchon.AbstractWizardPanel;
import de.cismet.cids.custom.switchon.IDFCurve;
import de.cismet.cids.custom.switchon.StatusPanel;
import de.cismet.cids.custom.switchon.concurrent.SwitchonConcurrency;
import de.cismet.cids.custom.switchon.converter.IDFConverter;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public final class IDFExportWizardPanelConvert extends AbstractWizardPanel implements Cancellable {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient Logger LOG = Logger.getLogger(IDFExportWizardPanelConvert.class);

    //~ Instance fields --------------------------------------------------------

    private final transient Object lock;

    private transient Future exportTask;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new TimeSeriesExportWizardPanelConvert object.
     */
    public IDFExportWizardPanelConvert() {
        this.lock = new Object();
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new StatusPanel(NbBundle.getMessage(
                    IDFExportWizardPanelConvert.class,
                    "IDFExportWizardPanelConvert.createComponent().statusPanel.name")); // NOI18N
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        synchronized (lock) {
            final File exportFile = (File)wizard.getProperty(WizardPanelFileExport.PROP_EXPORT_FILE);
            final IDFConverter idfConverter = (IDFConverter)wizard.getProperty(
                    AbstractConverterChoosePanelCtrl.PROP_CONVERTER);
            final IDFCurve idfCurve = (IDFCurve)wizard.getProperty(IDFExportWizardAction.PROP_IDF_CURVE);

            assert exportFile != null : "export file must not be null"; // NOI18N
            assert idfConverter != null : "converter must not be null"; // NOI18N
            assert idfCurve != null : "idf curve must not be null";     // NOI18N

            exportTask = SwitchonConcurrency.getSwitchonGeneralPurposePool().submit(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                setStatusEDT(
                                    true,
                                    NbBundle.getMessage(
                                        IDFExportWizardPanelConvert.class,
                                        "IDFExportWizardPanelConvert.read(WizardDescriptor).exportTask.status.exporting")); // NOI18N

                                final InputStream is = idfConverter.convertBackward(idfCurve);
                                final FileOutputStream fos = new FileOutputStream(exportFile);

                                final byte[] buff = new byte[8192];
                                int read;
                                while ((read = is.read(buff)) > 0) {
                                    fos.write(buff, 0, read);
                                }

                                is.close();
                                fos.close();

                                setStatusEDT(
                                    false,
                                    NbBundle.getMessage(
                                        IDFExportWizardPanelConvert.class,
                                        "IDFExportWizardPanelConvert.read(WizardDescriptor).exportTask.status.exportSuccessful")); // NOI18N

                                synchronized (lock) {
                                    IDFExportWizardPanelConvert.this.exportTask = null;
                                }
                            } catch (final Throwable ex) {
                                LOG.error("cannot export timeseries", ex);                                                     // NOI18N
                                setStatusEDT(
                                    false,
                                    NbBundle.getMessage(
                                        IDFExportWizardPanelConvert.class,
                                        "IDFExportWizardPanelConvert.read(WizardDescriptor).exportTask.status.exportingError", // NOI18N
                                        ex.getMessage()));

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
