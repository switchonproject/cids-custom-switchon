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

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
            PropertyChangeListener,
            ListSelectionListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(RepresentationsPanel.class);

    //~ Instance fields --------------------------------------------------------

    private CidsBean selectedRepresentation = null;

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
        selectedRepresentation = null;
        getComponent().addTableSelectionListener(this);
        resource.addPropertyChangeListener(this);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final CidsBean resource = getComponent().getCidsBean();

        wizard.putProperty(MetaDataWizardAction.PROP_SELECTED_REPRESENTATION_BEAN, selectedRepresentation);

        resource.removePropertyChangeListener(this);
        getComponent().removeTableSelectionListener(this);
        getComponent().dispose();
    }

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(RepresentationsPanel.class, "RepresentationsPanel.name");
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    @Override
    public void valueChanged(final ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            selectedRepresentation = getComponent().getSelectedRepresentation();
            changeSupport.fireChange();
        }
    }
}
