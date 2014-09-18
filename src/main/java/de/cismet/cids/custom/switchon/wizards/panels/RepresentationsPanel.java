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

import de.cismet.cids.custom.switchon.wizards.DefaultPropertySetter;
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
public class RepresentationsPanel extends GenericAbstractWizardPanel<RepresentationsVisualPanel>
        implements NameProvider {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(RepresentationsPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new RepresentationsPanel object.
     */
    public RepresentationsPanel() {
        super(RepresentationsVisualPanel.class);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        final RepresentationsVisualPanel component = (RepresentationsVisualPanel)super.createComponent();
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
            MetaDataWizardAction.PROP_SELECTED_REPRESENTATION_BEAN,
            null);
    }

    /**
     * DOCUMENT ME!
     *
     * @param  wizard  DOCUMENT ME!
     */
    @Override
    protected void store(final WizardDescriptor wizard) {
        CidsBean selectedRepresentation = getComponent().getSelectedRepresentation();
        final CidsBean wizMetaData = (CidsBean)wizard.getProperty(
                MetaDataWizardAction.PROP_SELECTED_REPRESENTATION_BEAN);

        // the store method is always run twice. Check if it is the second execution.
        // it is the second execution if:
        // representation in the wizard is not null
        if (wizMetaData != null) {
            return;
        }

        if (selectedRepresentation == null) {
            try {
                // no representation selected, thus create a new representation and add it to the resource
                selectedRepresentation = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "representation"); // NOI18N
                DefaultPropertySetter.setDefaultsToRepresentationCidsBean(selectedRepresentation);

                final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
                resource.getBeanCollectionProperty("representation").add(selectedRepresentation); // NOI18N
            } catch (Exception ex) {
                LOG.error(ex, ex);
                return;
            }
        }

        wizard.putProperty(
            MetaDataWizardAction.PROP_SELECTED_REPRESENTATION_BEAN,
            selectedRepresentation);
    }

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(RepresentationsPanel.class, "RepresentationsPanel.name");
    }
}
