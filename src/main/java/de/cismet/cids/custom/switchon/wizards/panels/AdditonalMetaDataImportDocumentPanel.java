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
public class AdditonalMetaDataImportDocumentPanel
        extends GenericAbstractWizardPanel<AdditonalMetaDataImportDocumentVisualPanel> implements NameProvider {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(AdditonalMetaDataImportDocumentPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new AdditonalMetaDataImportDocumentPanel object.
     */
    public AdditonalMetaDataImportDocumentPanel() {
        super(AdditonalMetaDataImportDocumentVisualPanel.class);
        setGeneralInformation(org.openide.util.NbBundle.getMessage(
                AdditonalMetaDataImportDocumentVisualPanel.class,
                "AdditonalMetaDataImportDocumentVisualPanel.generalInformation"));
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected void read(final WizardDescriptor wizard) {
        wizard.putProperty(MetaDataWizardAction.PROP_AdditonalMetaDataImportDocumentPanel_WAS_OPENED, Boolean.TRUE);
        final CidsBean metaData = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_SELECTED_METADATA_BEAN);
        getComponent().setCidsBean(metaData);
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
