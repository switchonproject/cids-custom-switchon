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
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean relationship = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_CREATED_RELATIONSHIP_BEAN);
        relationship.addPropertyChangeListener(this);
        getComponent().setCidsBean(relationship);
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
}
