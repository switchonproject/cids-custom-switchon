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
import org.openide.util.NbBundle;

import java.awt.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.UUID;

import de.cismet.cids.custom.switchon.utils.Taggroups;
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
public class BasicResourcePropertiesPanel extends GenericAbstractWizardPanel<BasicResourcePropertiesVisualPanel>
        implements NameProvider,
            PropertyChangeListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(BasicResourcePropertiesPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new BasicResourcePropertiesPanel object.
     */
    public BasicResourcePropertiesPanel() {
        super(BasicResourcePropertiesVisualPanel.class);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new BasicResourcePropertiesVisualPanel(Taggroups.RESOURCE_TYPE);
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
        getComponent().setCidsBean(resource);
        resource.addPropertyChangeListener(this);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final CidsBean resource = getComponent().getCidsBean();
        resource.removePropertyChangeListener(this);
        getComponent().dispose();

        try {
            final String uuid = (String)resource.getProperty("uuid");
            if (StringUtils.isBlank(uuid)) {
                resource.setProperty("uuid", UUID.randomUUID().toString());
            }
        } catch (Exception ex) {
            LOG.error(ex, ex);
        }
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(BasicResourcePropertiesPanel.class, "BasicResourcePropertiesPanel.getName()");
    }

    @Override
    public boolean isValid() {
        final CidsBean resource = getComponent().getCidsBean();
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
