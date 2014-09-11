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
public class AdditonalMetaDataContactInformationPanel
        extends GenericAbstractWizardPanel<AdditonalMetaDataContactInformationVisualPanel> implements NameProvider,
        PropertyChangeListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(AdditonalMetaDataContactInformationPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new AdditonalMetaDataContactInformationPanel object.
     */
    public AdditonalMetaDataContactInformationPanel() {
        super(AdditonalMetaDataContactInformationVisualPanel.class);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        final AdditonalMetaDataContactInformationVisualPanel component =
            (AdditonalMetaDataContactInformationVisualPanel)super.createComponent();
        component.setModel(this);
        return component;
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean metadata = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_SELECTED_METADATA_BEAN);
        metadata.addPropertyChangeListener(this);
        getComponent().setCidsBean(metadata);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final CidsBean metadata = getComponent().getCidsBean();
        metadata.removePropertyChangeListener(this);
        getComponent().dispose();
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(
                AdditonalMetaDataContactInformationPanel.class,
                "AdditonalMetaDataContactInformationPanel.name");
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    @Override
    public boolean isValid() {
        final CidsBean metadata = getComponent().getCidsBean();
        final CidsBean contact = (CidsBean)metadata.getProperty("contact");
        final String organisation = (String)contact.getProperty("organisation");

        return StringUtils.isNotBlank(organisation);
    }
}
