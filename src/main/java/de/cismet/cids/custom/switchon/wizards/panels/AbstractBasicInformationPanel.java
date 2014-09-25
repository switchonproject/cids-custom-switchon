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
        return panel;
    }

    /**
     * The used type taggroup when the BasicInformationVisualPanel is created.
     *
     * @return  DOCUMENT ME!
     */
    protected abstract Taggroups getTypeTaggroup();

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
            final String uuid = (String)cidsBean.getProperty("uuid");       // NOI18N
            if (StringUtils.isBlank(uuid)) {
                cidsBean.setProperty("uuid", UUID.randomUUID().toString()); // NOI18N
            }
        } catch (Exception ex) {
            LOG.error(ex, ex);
        }
    }

    @Override
    public boolean isValid() {
        final CidsBean resource = getComponent().getCidsBean();
        final String name = (String)resource.getProperty("name");        // NOI18N
        final String desc = (String)resource.getProperty("description"); // NOI18N
        final CidsBean type = (CidsBean)resource.getProperty("type");    // NOI18N

        boolean valid = true;
        if (StringUtils.isBlank(name) || StringUtils.isBlank(desc) || (type == null)) {
            valid = false;
        }

        if (StringUtils.isBlank(name)) {
            showWarning(org.openide.util.NbBundle.getMessage(
                    AbstractBasicInformationPanel.class,
                    "AbstractBasicInformationPanel.isValid().nameMissing"));        // NOI18N
        } else if (StringUtils.isBlank(desc)) {
            showWarning(org.openide.util.NbBundle.getMessage(
                    AbstractBasicInformationPanel.class,
                    "AbstractBasicInformationPanel.isValid().descriptionMissing")); // NOI18N
        } else if (type == null) {
            showWarning(org.openide.util.NbBundle.getMessage(
                    AbstractBasicInformationPanel.class,
                    "AbstractBasicInformationPanel.isValid().typeMissing"));        // NOI18N
        } else {
            showGeneralInformation();
        }

        return valid;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }
}
