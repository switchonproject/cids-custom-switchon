/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.data.io;

import org.openide.WizardDescriptor;
import org.openide.util.Cancellable;
import org.openide.util.NbBundle;

import java.awt.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import java.util.concurrent.Future;

import de.cismet.cids.custom.switchon.StatusPanel;
import de.cismet.cids.custom.switchon.concurrent.SwitchonConcurrency;
import de.cismet.cids.custom.switchon.converter.Converter;

/**
 * DOCUMENT ME!
 *
 * @author   bfriedrich
 * @version  $Revision$, $Date$
 */
public class WizardPanelConversionForward extends AbstractWizardPanelCtrl implements Cancellable {

    //~ Static fields/initializers ---------------------------------------------

    public static final String PROP_CONVERTED = "__prop_converted__"; // NOI18N

    //~ Instance fields --------------------------------------------------------

    private final transient StatusPanel comp;
    private transient volatile Object converted;
    private transient Future<?> runningTask;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new WizardPanelConversion object.
     */
    public WizardPanelConversionForward() {
        this.comp = new StatusPanel(NbBundle.getMessage(
                    WizardPanelConversionForward.class,
                    "WizardPanelConversion.comp.name")); // NOI18N
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public Component getComponent() {
        return this.comp;
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        this.comp.setBusy(true);
        this.comp.setStatusMessage(NbBundle.getMessage(
                WizardPanelConversionForward.class,
                "WizardPanelConversion.comp.setConversionStatus().start"));

        this.runningTask = SwitchonConcurrency.getSwitchonGeneralPurposePool().submit(new Runnable() {

                    @Override
                    public void run() {
                        final File importFile = (File)wizard.getProperty(WizardPanelChooseFileImport.PROP_INPUT_FILE);
                        final Converter converter = (Converter)wizard.getProperty(
                                AbstractConverterChoosePanelCtrl.PROP_CONVERTER);

                        try {
                            final FileInputStream fin = new FileInputStream(importFile);
                            final BufferedInputStream bin = new BufferedInputStream(fin);

                            converted = converter.convertForward(bin);
                            comp.setStatusMessage(
                                NbBundle.getMessage(
                                    WizardPanelConversionForward.class,
                                    "WizardPanelConversion.comp.setConversionStatus().finish"));
                        } catch (final Exception e) {
                            // TODO distinguish different exception types for better error messages

                            wizard.putProperty(WizardDescriptor.PROP_ERROR_MESSAGE, e);
                            comp.setStatusMessage(
                                NbBundle.getMessage(
                                    WizardPanelConversionForward.class,
                                    "WizardPanelConversion.comp.setConversionStatus().error"));
                            wizard.setValid(false);
                        }

                        WizardPanelConversionForward.this.fireChangeEvent();
                        comp.setBusy(false);

                        synchronized (WizardPanelConversionForward.this) {
                            WizardPanelConversionForward.this.runningTask = null;
                        }
                    }
                });
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        wizard.putProperty(PROP_CONVERTED, this.converted);
        wizard.putProperty(WizardDescriptor.PROP_INFO_MESSAGE, null);
    }

    @Override
    public boolean isValid() {
        return this.converted != null;
    }

    @Override
    public synchronized boolean cancel() {
        if (this.runningTask != null) {
            this.runningTask.cancel(true);
            this.runningTask = null;
        }

        return true;
    }
}
