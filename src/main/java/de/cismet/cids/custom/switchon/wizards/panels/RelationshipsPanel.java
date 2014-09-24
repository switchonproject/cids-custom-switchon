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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.List;

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
public class RelationshipsPanel extends GenericAbstractWizardPanel<RelationshipsVisualPanel> implements NameProvider,
    PropertyChangeListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(RelationshipsPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new RelationshipsPanel object.
     */
    public RelationshipsPanel() {
        super(RelationshipsVisualPanel.class);
        setGeneralInformation(org.openide.util.NbBundle.getMessage(
                RelationshipsVisualPanel.class,
                "RelationshipsVisualPanel.infoBoxPanel.generalInformation")); // NOI18N
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected void read(final WizardDescriptor wizard) {
        CidsBean relationship = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_CREATED_RELATIONSHIP_BEAN);
        if (relationship == null) {
            relationship = createNewRelationshipCidsBean(wizard);
            wizard.putProperty(MetaDataWizardAction.PROP_CREATED_RELATIONSHIP_BEAN, relationship);
        }
        relationship.addPropertyChangeListener(this);
        getComponent().setCidsBean(relationship);
        // disable the target resource table if a resource bean is present
        getComponent().setEnableTargetResource(wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN) == null);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final CidsBean relationship = getComponent().getCidsBean();
        relationship.removePropertyChangeListener(this);
        getComponent().dispose();
    }

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(RelationshipsPanel.class, "RelationshipsPanel.name");
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    @Override
    public boolean isValid() {
        final CidsBean relationship = getComponent().getCidsBean();
        final CidsBean toresource = (CidsBean)relationship.getProperty("toresource");
        final List<CidsBean> fromResources = relationship.getBeanCollectionProperty("fromresources");

        return (toresource != null) && !fromResources.isEmpty();
    }

    /**
     * DOCUMENT ME!
     *
     * @param   wizard  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private CidsBean createNewRelationshipCidsBean(final WizardDescriptor wizard) {
        CidsBean relationship = null;
        try {
            relationship = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "relationship");
            final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
            relationship.setProperty("toresource", resource);
        } catch (Exception ex) {
            LOG.error("Could not create new Relationship-CidsBean", ex);
        }
        return relationship;
    }
}
