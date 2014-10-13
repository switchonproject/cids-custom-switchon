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

import java.awt.Component;

import de.cismet.cids.custom.switchon.wizards.GenericAbstractWizardPanel;
import de.cismet.cids.custom.switchon.wizards.LeapOtherPanels;
import de.cismet.cids.custom.switchon.wizards.MetaDataWizardAction;
import de.cismet.cids.custom.switchon.wizards.NameProvider;

import de.cismet.cids.dynamics.CidsBean;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class AdditonalMetaDataPanel extends GenericAbstractWizardPanel<AdditonalMetaDataVisualPanel>
        implements NameProvider,
            LeapOtherPanels {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(AdditonalMetaDataPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new AdditonalMetaDataPanel object.
     */
    public AdditonalMetaDataPanel() {
        super(AdditonalMetaDataVisualPanel.class);
        setGeneralInformation(org.openide.util.NbBundle.getMessage(
                AdditonalMetaDataVisualPanel.class,
                "AdditonalMetaDataVisualPanel.infoBoxPanel.generalInformation")); // NOI18N
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        final AdditonalMetaDataVisualPanel component = (AdditonalMetaDataVisualPanel)super.createComponent();
        component.addButtonShouldSimulateNextButton(wizard);
        component.editButtonShouldSimulateNextButton(wizard);
        return component;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  wizard  DOCUMENT ME!
     */
    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
        getComponent().setCidsBean(resource);
        wizard.putProperty(
            MetaDataWizardAction.PROP_SELECTED_METADATA_BEAN,
            null);
    }

    /**
     * DOCUMENT ME!
     *
     * @param  wizard  DOCUMENT ME!
     */
    @Override
    protected void store(final WizardDescriptor wizard) {
        if (WizardDescriptor.NEXT_OPTION.equals(wizard.getValue())) {
            // first get PROP_SELECTED_METADATA_BEAN, if that is null, check if no meta data was selected in the GUI
            CidsBean selectedMetaData = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_SELECTED_METADATA_BEAN);
            if (selectedMetaData == null) {
                selectedMetaData = getComponent().getSelectedMetaData();
            }

            wizard.putProperty(
                MetaDataWizardAction.PROP_SELECTED_METADATA_BEAN,
                selectedMetaData);
        }
        getComponent().dispose();
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(AdditonalMetaDataPanel.class, "AdditonalMetaDataPanel.name");
    }

    @Override
    public String nextPanelClassSimpleName() {
        if (wizard.getProperty(
                        MetaDataWizardAction.PROP_SELECTED_METADATA_BEAN) == null) {
            return RepresentationsPanel.class.getSimpleName();
        } else {
            return null;
        }
    }

    @Override
    public String previousPanelClassSimpleName() {
        return null;
    }
}
