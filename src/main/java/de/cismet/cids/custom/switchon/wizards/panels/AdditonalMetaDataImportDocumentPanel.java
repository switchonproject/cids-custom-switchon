/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import org.apache.log4j.Logger;

import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;

import de.cismet.cids.custom.switchon.wizards.GenericAbstractWizardPanel;
import de.cismet.cids.custom.switchon.wizards.MetaDataWizardAction;
import de.cismet.cids.custom.switchon.wizards.NameProvider;

import de.cismet.cids.dynamics.CidsBean;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class AdditonalMetaDataImportDocumentPanel extends GenericAbstractWizardPanel<BasicImportDocumentVisualPanel>
        implements NameProvider {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(AdditonalMetaDataImportDocumentPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new AdditonalMetaDataImportDocumentPanel object.
     */
    public AdditonalMetaDataImportDocumentPanel() {
        super(BasicImportDocumentVisualPanel.class);
        setGeneralInformation(org.openide.util.NbBundle.getMessage(
                BasicImportDocumentVisualPanel.class,
                "AdditonalMetaDataImportDocumentVisualPanel.generalInformation"));
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected void read(final WizardDescriptor wizard) {
        getComponent().setWizard(wizard);
        getComponent().setWizardPropertyToIndicateIfImportButtonWasPressed(
            MetaDataWizardAction.PROP_AdditonalMetaDataImportDocumentPanel_IMPORT_BUTTON_WAS_PRESSED);
        final CidsBean metaData = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_SELECTED_METADATA_BEAN);
        getComponent().setCidsBean(metaData);
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
        getComponent().setResource(resource);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(
                AdditonalMetaDataImportDocumentPanel.class,
                "AdditonalMetaDataImportDocumentPanel.name");
    }
}
