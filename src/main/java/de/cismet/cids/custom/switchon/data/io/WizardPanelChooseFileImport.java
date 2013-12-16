/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.data.io;

import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;

import java.awt.Component;

import java.io.File;

import java.text.MessageFormat;

/**
 * DOCUMENT ME!
 *
 * @author   bfriedrich
 * @version  $Revision$, $Date$
 */
public class WizardPanelChooseFileImport extends AbstractWizardPanelCtrl {

    //~ Static fields/initializers ---------------------------------------------

    public static final String PROP_INPUT_FILE = "__prop_input_file__"; // NOI18N

    //~ Instance fields --------------------------------------------------------

    private final transient VisualPanelChooseFileImport comp;

    private transient File importFile;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new TimeSeriesImportFileChoosePanelCtrl object.
     */
    public WizardPanelChooseFileImport() {
        this.comp = new VisualPanelChooseFileImport(this);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public Component getComponent() {
        return this.comp;
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        this.comp.init();
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        wizard.putProperty(PROP_INPUT_FILE, this.importFile);
    }

    @Override
    public boolean isValid() {
        final String fileName = this.comp.getFileName();
        if ((fileName == null) || fileName.trim().isEmpty()) {
            wizard.putProperty(
                WizardDescriptor.PROP_WARNING_MESSAGE,
                NbBundle.getMessage(
                    WizardPanelChooseFileImport.class,
                    "WizardPanelChooseFile.isValid().wizard.putProperty(String,String).noFile"));
            return false;
        }

        final File file = new File(fileName);
        if (!file.exists()) {
            wizard.putProperty(
                WizardDescriptor.PROP_WARNING_MESSAGE,
                MessageFormat.format(
                    NbBundle.getMessage(
                        WizardPanelChooseFileImport.class,
                        "WizardPanelChooseFile.isValid().wizard.putProperty(String,String).noExistence"),
                    fileName));
            return false;
        }

        if (!file.canRead()) {
            wizard.putProperty(
                WizardDescriptor.PROP_WARNING_MESSAGE,
                MessageFormat.format(
                    NbBundle.getMessage(
                        WizardPanelChooseFileImport.class,
                        "WizardPanelChooseFile.isValid().wizard.putProperty(String,String).noRead"),
                    fileName));
            return false;
        }

        wizard.putProperty(WizardDescriptor.PROP_INFO_MESSAGE, null);
        this.importFile = file;
        return true;
    }
}
