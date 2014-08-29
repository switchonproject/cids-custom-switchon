/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards;

import java.awt.Component;

import de.cismet.commons.gui.wizard.AbstractWizardPanel;

/**
 * A AbstractWizardPanel which knows the class of its VisualPanel and which can generate the VisualPanel itself.
 * This avoids some code, especially the cast after a call of <code>getComponent()</code>
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 * @param <T> The class of the VisualPanel
 */
public abstract class GenericAbstractWizardPanel<T extends Component> extends AbstractWizardPanel {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            GenericAbstractWizardPanel.class);

    //~ Instance fields --------------------------------------------------------

    private final Class<T> clazz;

    //~ Constructors -----------------------------------------------------------

    /**
     * The actual class of the generic has to be known, as otherwise a new instance of the class can not be created
     * automatically.
     *
     * @param  clazz  DOCUMENT ME!
     */
    public GenericAbstractWizardPanel(final Class<T> clazz) {
        this.clazz = clazz;
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException ex) {
            LOG.error(ex, ex);
        } catch (IllegalAccessException ex) {
            LOG.error(ex, ex);
        }
        return null;
    }

    @Override
    public T getComponent() {
        return (T)super.getComponent();
    }
}
