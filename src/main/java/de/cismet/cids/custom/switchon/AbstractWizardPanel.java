/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import org.openide.WizardDescriptor;
import org.openide.util.ChangeSupport;
import org.openide.util.HelpCtx;

import java.awt.Component;

import javax.swing.event.ChangeListener;

/**
 * Why is this needed here? The same class already exists in cismet-gui-commons.
 *
 * @author      martin.scholl@cismet.de
 * @version     $Revision$, $Date$
 * @deprecated  same class already exists in cismet-gui-commons
 */
public abstract class AbstractWizardPanel implements WizardDescriptor.Panel {

    //~ Instance fields --------------------------------------------------------

    protected final transient ChangeSupport changeSupport;
    protected transient WizardDescriptor wizard;

    private transient volatile Component component;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new AbstractWizardPanel object.
     */
    public AbstractWizardPanel() {
        this.changeSupport = new ChangeSupport(this);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public Component getComponent() {
        if (component == null) {
            synchronized (this) {
                if (component == null) {
                    component = createComponent();
                }
            }
        }

        return component;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    protected abstract Component createComponent();

    @Override
    public HelpCtx getHelp() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    public void readSettings(final Object settings) {
        this.wizard = (WizardDescriptor)settings;
        this.read(wizard);
    }

    @Override
    public void storeSettings(final Object settings) {
        this.wizard = (WizardDescriptor)settings;
        this.store(wizard);
    }

    /**
     * DOCUMENT ME!
     *
     * @param  wizard  DOCUMENT ME!
     */
    protected abstract void read(final WizardDescriptor wizard);

    /**
     * DOCUMENT ME!
     *
     * @param  wizard  DOCUMENT ME!
     */
    protected abstract void store(final WizardDescriptor wizard);

    @Override
    public void addChangeListener(final ChangeListener l) {
        changeSupport.addChangeListener(l);
    }

    @Override
    public void removeChangeListener(final ChangeListener l) {
        changeSupport.removeChangeListener(l);
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
