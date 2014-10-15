/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import org.openide.WizardDescriptor;

import java.awt.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
public class RepresentationsPanel extends GenericAbstractWizardPanel<RepresentationsVisualPanel>
        implements NameProvider,
            LeapOtherPanels,
            PropertyChangeListener,
            ListSelectionListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(RepresentationsPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new RepresentationsPanel object.
     */
    public RepresentationsPanel() {
        super(RepresentationsVisualPanel.class);
        setGeneralInformation(org.openide.util.NbBundle.getMessage(
                RepresentationsVisualPanel.class,
                "RepresentationsVisualPanel.infoBoxPanel.generalInformation")); // NOI18N
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
        resource.addPropertyChangeListener(this);
        getComponent().addTableSelectionListener(this);
        wizard.putProperty(
            MetaDataWizardAction.PROP_SELECTED_REPRESENTATION_BEAN,
            null);
        enableFinishButton();
    }

    /**
     * DOCUMENT ME!
     *
     * @param  wizard  DOCUMENT ME!
     */
    @Override
    protected void store(final WizardDescriptor wizard) {
        if (WizardDescriptor.NEXT_OPTION.equals(wizard.getValue())) {
            // first get PROP_SELECTED_REPRESENTATION_BEAN, if that is null, check if no meta data was selected in the
            // GUI
            CidsBean selectedRepresentation = (CidsBean)wizard.getProperty(
                    MetaDataWizardAction.PROP_SELECTED_REPRESENTATION_BEAN);
            if (selectedRepresentation == null) {
                selectedRepresentation = getComponent().getSelectedRepresentation();
            }

            wizard.putProperty(
                MetaDataWizardAction.PROP_SELECTED_REPRESENTATION_BEAN,
                selectedRepresentation);
        }
        getComponent().getCidsBean().removePropertyChangeListener(this);
        getComponent().removeTableSelectionListener(this);
        getComponent().dispose();
    }

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(RepresentationsPanel.class, "RepresentationsPanel.name");
    }

    @Override
    public String nextPanelClassSimpleName() {
        if (wizard.getProperty(
                        MetaDataWizardAction.PROP_SELECTED_REPRESENTATION_BEAN) == null) {
            return RelationshipsPanel.class.getSimpleName();
        } else {
            return null;
        }
    }

    @Override
    public boolean isValid() {
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
        if ("advanced".equals(wizard.getProperty(MetaDataWizardAction.PROP_CONFIGURATION))
                    && (getComponent().getSelectedRepresentation() == null)) {
            showWarning(org.openide.util.NbBundle.getMessage(
                    RepresentationsPanel.class,
                    "RepresentationsPanel.isValid().advancedProfileNoneSelected"));
            return false;
        } else if (resource.getBeanCollectionProperty("representation").isEmpty()) {
            showWarning(org.openide.util.NbBundle.getMessage(
                    RepresentationsPanel.class,
                    "RepresentationsPanel.isValid().warn"));
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String previousPanelClassSimpleName() {
        return AdditonalMetaDataPanel.class.getSimpleName();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
        enableFinishButton();
    }

    @Override
    public void valueChanged(final ListSelectionEvent e) {
        changeSupport.fireChange();
        enableFinishButton();
    }

    /**
     * DOCUMENT ME!
     */
    private void enableFinishButton() {
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
        final boolean enableFinishButton =
            "advanced".equals(wizard.getProperty(MetaDataWizardAction.PROP_CONFIGURATION))
                    && !resource.getBeanCollectionProperty("representation").isEmpty();
        if (enableFinishButton) {
            for (final Object o : wizard.getOptions()) {
                if (o instanceof JButton) {
                    final JButton button = (JButton)o;
                    if ("Finish".equals(button.getActionCommand())) {
                        button.setEnabled(true);
                        break;
                    }
                }
            }
        }
    }
}
