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
public class RepresentationsDataImportPanel extends GenericAbstractWizardPanel<RepresentationsDataImportVisualPanel>
        implements NameProvider {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(RepresentationsDataAccessInformationPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new RepresentationsDataImportPanel object.
     */
    public RepresentationsDataImportPanel() {
        super(RepresentationsDataImportVisualPanel.class);
        setGeneralInformation(org.openide.util.NbBundle.getMessage(
                RepresentationsDataImportVisualPanel.class,
                "RepresentationsDataImportVisualPanel.generalInformation"));
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected void read(final WizardDescriptor wizard) {
        getComponent().setWizard(wizard);
        getComponent().setWizardPropertyToIndicateIfImportButtonWasPressed(
            MetaDataWizardAction.PROP_RepresentationsDataImportPanel_IMPORT_BUTTON_PRESSED);
        final CidsBean metaData = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_SELECTED_REPRESENTATION_BEAN);
        getComponent().setCidsBean(metaData);
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
        getComponent().setResource(resource);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
    }

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(
                RepresentationsDataImportPanel.class,
                "RepresentationsDataImportPanel.name");
    }
}
