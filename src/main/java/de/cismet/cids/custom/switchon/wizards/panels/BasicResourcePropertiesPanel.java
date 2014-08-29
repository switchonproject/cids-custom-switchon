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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.UUID;
import java.util.concurrent.Future;

import de.cismet.cids.custom.switchon.utils.CidsBeanUtils;
import de.cismet.cids.custom.switchon.utils.TagUtils;
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
    private static final Future<CidsBean> defaultType = TagUtils.fetchFutureTagByName("external data");
    private static final Future<CidsBean> defaultLanguage = TagUtils.fetchFutureTagByName("eng");

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new BasicResourcePropertiesPanel object.
     */
    public BasicResourcePropertiesPanel() {
        super(BasicResourcePropertiesVisualPanel.class);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
        setDefaults(resource);
        getComponent().setCidsBean(resource);
        resource.addPropertyChangeListener(this);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final CidsBean resource = getComponent().getCidsBean();
        resource.removePropertyChangeListener(this);

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
        return java.util.ResourceBundle.getBundle("de/cismet/cids/custom/switchon/wizards/Bundle")
                    .getString("BasicResourcePropertiesPanel.getName()");
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

    /**
     * DOCUMENT ME!
     *
     * @param  resource  DOCUMENT ME!
     */
    private void setDefaults(final CidsBean resource) {
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(
            defaultLanguage,
            resource,
            "language");
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultType, resource, "type");
    }
}
