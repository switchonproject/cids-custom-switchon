/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.data.io;

import org.openide.WizardDescriptor;
import org.openide.util.ChangeSupport;
import org.openide.util.HelpCtx;

import javax.swing.event.ChangeListener;

/**
 * DOCUMENT ME!
 *
 * @author   bfriedrich
 * @version  $Revision$, $Date$
 */
public abstract class AbstractWizardPanelCtrl implements WizardDescriptor.Panel {

    //~ Instance fields --------------------------------------------------------

    protected transient WizardDescriptor wizard;
    private final transient ChangeSupport changeSupport;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new AbstractWizardPanelCtrl object.
     */
    public AbstractWizardPanelCtrl() {
        this.changeSupport = new ChangeSupport(this);
    }

    //~ Methods ----------------------------------------------------------------

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
    protected abstract void read(WizardDescriptor wizard);
    /**
     * DOCUMENT ME!
     *
     * @param  wizard  DOCUMENT ME!
     */
    protected abstract void store(WizardDescriptor wizard);

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
    public void fireChangeEvent() {
        changeSupport.fireChange();
    }
}
