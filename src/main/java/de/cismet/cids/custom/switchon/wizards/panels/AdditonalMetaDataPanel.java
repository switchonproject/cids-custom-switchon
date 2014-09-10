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
public class AdditonalMetaDataPanel extends GenericAbstractWizardPanel<AdditonalMetaDataVisualPanel>
        implements NameProvider {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(AdditonalMetaDataPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new AdditonalMetaDataPanel object.
     */
    public AdditonalMetaDataPanel() {
        super(AdditonalMetaDataVisualPanel.class);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
        getComponent().setCidsBean(resource);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        CidsBean selectedMetaData = getComponent().getSelectedMetaData();
        if (selectedMetaData == null) {
            try {
                // no metadata selected, thus create a new metadata and add it to the resource
                selectedMetaData = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "metadata"); // NOI18N
                DefaultPropertySetter.setDefaultsToMetaDataCidsBean(selectedMetaData);
                final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
                resource.getBeanCollectionProperty("metadata").add(selectedMetaData);               // NOI18N
            } catch (Exception ex) {
                LOG.error(ex, ex);
                return;
            }
        }

        wizard.putProperty(
            MetaDataWizardAction.PROP_SELECTED_METADATA_BEAN,
            selectedMetaData);
    }

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(AdditonalMetaDataPanel.class, "AdditonalMetaDataPanel.name");
    }
}
