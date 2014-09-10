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

import de.cismet.cids.custom.switchon.wizards.GenericAbstractWizardPanel;
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
public class AdditonalMetaDataBasicInformationPanel
        extends GenericAbstractWizardPanel<AdditonalMetaDataBasicInformationVisualPanel> implements NameProvider,
        PropertyChangeListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(AdditonalMetaDataBasicInformationPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new AdditonalMetaDataBasicInformationPanel object.
     */
    public AdditonalMetaDataBasicInformationPanel() {
        super(AdditonalMetaDataBasicInformationVisualPanel.class);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean metaData = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_SELECTED_METADATA_BEAN);
        getComponent().setCidsBean(metaData);
        metaData.addPropertyChangeListener(this);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final CidsBean metaData = getComponent().getCidsBean();
        metaData.removePropertyChangeListener(this);
        getComponent().dispose();

        try {
            final String uuid = (String)metaData.getProperty("uuid");
            if (StringUtils.isBlank(uuid)) {
                metaData.setProperty("uuid", UUID.randomUUID().toString());
            }
        } catch (Exception ex) {
            LOG.error(ex, ex);
        }
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(
                AdditonalMetaDataBasicInformationPanel.class,
                "AdditonalMetaDataBasicInformationPanel.name");
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    @Override
    public boolean isValid() {
        final CidsBean metaData = getComponent().getCidsBean();
        final String name = (String)metaData.getProperty("name");
        final String desc = (String)metaData.getProperty("description");
        final CidsBean type = (CidsBean)metaData.getProperty("type");

        boolean valid = true;
        if (StringUtils.isBlank(name) || StringUtils.isBlank(desc) || (type == null)) {
            valid = false;
        }
        return valid;
    }
}
