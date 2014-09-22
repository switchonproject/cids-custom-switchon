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

import java.util.UUID;

import de.cismet.cids.custom.switchon.utils.Taggroups;
import de.cismet.cids.custom.switchon.wizards.GenericAbstractWizardPanel;
import de.cismet.cids.custom.switchon.wizards.NameProvider;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public abstract class AbstractBasicInformationPanel extends GenericAbstractWizardPanel<BasicInformationVisualPanel>
        implements NameProvider,
            PropertyChangeListener,
            CidsBeanStore {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(AbstractBasicInformationPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new BasicResourcePropertiesPanel object.
     */
    public AbstractBasicInformationPanel() {
        super(BasicInformationVisualPanel.class);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        final BasicInformationVisualPanel panel = new BasicInformationVisualPanel(getTypeTaggroup());
        panel.setGeneralInformation(getGeneralInformation());
        return panel;
    }

    /**
     * The used type taggroup when the BasicInformationVisualPanel is created.
     *
     * @return  DOCUMENT ME!
     */
    protected abstract Taggroups getTypeTaggroup();

    /**
     * The shown general information in the BasicInformationVisualPanel.
     *
     * @return  DOCUMENT ME!
     */
    protected abstract String getGeneralInformation();

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean cidsBean = getCidsBean();
        getComponent().setCidsBean(cidsBean);
        cidsBean.addPropertyChangeListener(this);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final CidsBean cidsBean = getCidsBean();
        cidsBean.removePropertyChangeListener(this);
        getComponent().dispose();

        try {
            final String uuid = (String)cidsBean.getProperty("uuid");
            if (StringUtils.isBlank(uuid)) {
                cidsBean.setProperty("uuid", UUID.randomUUID().toString());
            }
        } catch (Exception ex) {
            LOG.error(ex, ex);
        }
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
