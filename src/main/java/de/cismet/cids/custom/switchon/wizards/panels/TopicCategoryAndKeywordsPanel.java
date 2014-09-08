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
import org.openide.util.NbBundle;

import java.awt.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.cismet.cids.custom.switchon.wizards.MetaDataWizardAction;
import de.cismet.cids.custom.switchon.wizards.NameProvider;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.commons.gui.wizard.AbstractWizardPanel;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class TopicCategoryAndKeywordsPanel extends AbstractWizardPanel implements NameProvider, PropertyChangeListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(TopicCategoryAndKeywordsPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new TopicCategoryAndKeywordsPanel object.
     */
    public TopicCategoryAndKeywordsPanel() {
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new TopicCategoryAndKeywordsVisualPanel();
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
        resource.addPropertyChangeListener(this);
        ((TopicCategoryAndKeywordsVisualPanel)getComponent()).setCidsBean(resource);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final CidsBean resource = ((TopicCategoryAndKeywordsVisualPanel)getComponent()).getCidsBean();
        resource.removePropertyChangeListener(this);
        ((TopicCategoryAndKeywordsVisualPanel)getComponent()).dispose();
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(TopicCategoryAndKeywordsPanel.class, "TopicCategoryAndKeywordsPanel.getName()");
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    @Override
    public boolean isValid() {
        final CidsBean resource = ((TopicCategoryAndKeywordsVisualPanel)getComponent()).getCidsBean();
        final CidsBean topiccategory = (CidsBean)resource.getProperty("topiccategory");

        return topiccategory != null;
    }
}
