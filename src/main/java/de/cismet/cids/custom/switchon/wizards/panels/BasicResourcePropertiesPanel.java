/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import org.openide.WizardDescriptor;

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
public class BasicResourcePropertiesPanel extends AbstractWizardPanel implements NameProvider, PropertyChangeListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(BasicResourcePropertiesPanel.class);

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new BasicResourcePropertiesVisualPanel();
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
        resource.addPropertyChangeListener(this);
        ((BasicResourcePropertiesVisualPanel)getComponent()).setCidsBean(resource);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final CidsBean resource = ((BasicResourcePropertiesVisualPanel)getComponent()).getCidsBean();
        wizard.putProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN, resource);
        resource.removePropertyChangeListener(this);
    }

    @Override
    public String getName() {
        return java.util.ResourceBundle.getBundle("de/cismet/cids/custom/switchon/wizards/Bundle")
                    .getString("BasicResourcePropertiesPanel.getName()");
    }

    @Override
    public boolean isValid() {
        final CidsBean resource = ((BasicResourcePropertiesVisualPanel)getComponent()).getCidsBean();
        final String name = (String)resource.getProperty("name");
        final String desc = (String)resource.getProperty("description");
        final CidsBean type = (CidsBean)resource.getProperty("type");

        boolean valid = true;
        if (StringUtils.isBlank(name) || StringUtils.isBlank(desc) || (type == null)) {
            valid = false;
        }
        return valid;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }
}
