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
public class RepresentationsPanel extends GenericAbstractWizardPanel<RepresentationsVisualPanel>
        implements NameProvider,
            PropertyChangeListener {

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
    protected void read(final WizardDescriptor wizard) {
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
        getComponent().setCidsBean(resource);
        resource.addPropertyChangeListener(this);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final CidsBean resource = getComponent().getCidsBean();

        final CidsBean selectedRepresentation = getComponent().getSelectedRepresentation();
        if (selectedRepresentation != null) {
            wizard.putProperty(MetaDataWizardAction.PROP_SELECTED_REPRESENTATION_BEAN, selectedRepresentation);
        }

        resource.removePropertyChangeListener(this);
        getComponent().dispose();
    }

    @Override
    public String getName() {
        return "Resource Representation";
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    /**
     * The panel is valid if there is no representation at all, or if there are representations, but one must be
     * selected.
     *
     * @return  DOCUMENT ME!
     */
    @Override
    public boolean isValid() {
        final CidsBean resource = getComponent().getCidsBean();
        final List<CidsBean> representations = resource.getBeanCollectionProperty("representation");
        if (representations.isEmpty()) {
            return true;
        }

        final CidsBean selectedRepresentation = getComponent().getSelectedRepresentation();
        return selectedRepresentation != null;
    }
}
