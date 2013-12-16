/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.converter;

import org.openide.WizardDescriptor;
import org.openide.util.ChangeSupport;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

import java.awt.Component;

import javax.swing.event.ChangeListener;

/**
 * DOCUMENT ME!
 *
 * @author   jlauter
 * @version  $Revision$, $Date$
 */
public final class EulerComputationWizardPanelMetadata implements WizardDescriptor.Panel {

    //~ Instance fields --------------------------------------------------------

    private final transient ChangeSupport changeSupport;

    private transient WizardDescriptor wizard;
    private transient EulerComputationVisualPanelMetadata component;

    private transient String name;
    private transient String description;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new EulerComputationWizardPanelMetadata object.
     */
    public EulerComputationWizardPanelMetadata() {
        changeSupport = new ChangeSupport(this);
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public String getDescription() {
        return description;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public String getName() {
        return name;
    }

    @Override
    public Component getComponent() {
        if (component == null) {
            component = new EulerComputationVisualPanelMetadata(this);
        }

        return component;
    }

    @Override
    public boolean isValid() {
        final String currentName = component.getSelectedName();
        boolean valid;

        if ((currentName == null) || currentName.isEmpty()) {
            wizard.putProperty(
                WizardDescriptor.PROP_WARNING_MESSAGE,
                NbBundle.getMessage(
                    EulerComputationWizardPanelMetadata.class,
                    "EulerComputationWizardPanelMetadata.isValid().emptyName"));
            valid = false;
        } else {
            wizard.putProperty(WizardDescriptor.PROP_WARNING_MESSAGE, null);

            final String currentDesc = component.getSelectedDescription();
            if ((currentDesc == null) || currentDesc.isEmpty()) {
                wizard.putProperty(
                    WizardDescriptor.PROP_INFO_MESSAGE,
                    org.openide.util.NbBundle.getMessage(
                        EulerComputationWizardPanelMetadata.class,
                        "EulerComputationWizardPanelMetadata.isValid().emptyDescription"));
            } else {
                wizard.putProperty(WizardDescriptor.PROP_INFO_MESSAGE, null);
            }
            valid = true;
        }
        return valid;
    }

    @Override
    public HelpCtx getHelp() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    public void readSettings(final Object settings) {
        wizard = (WizardDescriptor)settings;
        name = (String)wizard.getProperty(EulerComputationWizardAction.PROP_EULER2_NAME);
        description = (String)wizard.getProperty(EulerComputationWizardAction.PROP_EULER2_DESC);
        if ((name == null) || name.isEmpty()) {
            changeSupport.fireChange();
        }
    }

    @Override
    public void storeSettings(final Object settings) {
        wizard = (WizardDescriptor)settings;
        wizard.putProperty(EulerComputationWizardAction.PROP_EULER2_NAME, component.getSelectedName());
        wizard.putProperty(EulerComputationWizardAction.PROP_EULER2_DESC, component.getSelectedDescription());
    }

    @Override
    public void addChangeListener(final ChangeListener l) {
        changeSupport.addChangeListener(l);
    }

    @Override
    public void removeChangeListener(final ChangeListener l) {
        changeSupport.removeChangeListener(l);
    }
    /**
     * DOCUMENT ME!
     */
    protected void fireChangeEvent() {
        changeSupport.fireChange();
    }
}
