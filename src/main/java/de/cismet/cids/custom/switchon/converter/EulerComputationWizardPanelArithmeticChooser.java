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

import java.awt.Component;

import javax.swing.event.ChangeListener;

/**
 * DOCUMENT ME!
 *
 * @author   jlauter
 * @version  $Revision$, $Date$
 */
public class EulerComputationWizardPanelArithmeticChooser implements WizardDescriptor.Panel {

    //~ Instance fields --------------------------------------------------------

    private transient String eulerArithmetic;
    private final transient ChangeSupport changeSupport;
    private transient WizardDescriptor wizard;
    private transient EulerComputationVisualPanelArithmeticChooser component;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new EulerComputationWizardPanelArithmeticChooser object.
     */
    public EulerComputationWizardPanelArithmeticChooser() {
        changeSupport = new ChangeSupport(this);
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public String getEulerArithmetic() {
        return eulerArithmetic;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  eulerArithmetic  DOCUMENT ME!
     */
    public void setEulerArithmetic(final String eulerArithmetic) {
        this.eulerArithmetic = eulerArithmetic;

        changeSupport.fireChange();
    }

    @Override
    public Component getComponent() {
        if (component == null) {
            component = new EulerComputationVisualPanelArithmeticChooser(this);
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    public void readSettings(final Object settings) {
        wizard = (WizardDescriptor)settings;
        setEulerArithmetic((String)wizard.getProperty(EulerComputationWizardAction.PROP_EULER_ARITHMETIC));
        ((EulerComputationVisualPanelArithmeticChooser)getComponent()).init();
    }

    @Override
    public void storeSettings(final Object settings) {
        wizard.putProperty(EulerComputationWizardAction.PROP_EULER_ARITHMETIC, eulerArithmetic);
    }

    @Override
    public boolean isValid() {
        if ((eulerArithmetic == null) || eulerArithmetic.isEmpty()) {
            wizard.putProperty(
                WizardDescriptor.PROP_INFO_MESSAGE,
                org.openide.util.NbBundle.getMessage(
                    EulerComputationWizardPanelArithmeticChooser.class,
                    "EulerComputationWizardPanelArithmeticChooser.isValid().emptyArithmetic"));

            return false;
        } else {
            wizard.putProperty(WizardDescriptor.PROP_INFO_MESSAGE, null);

            return true;
        }
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
