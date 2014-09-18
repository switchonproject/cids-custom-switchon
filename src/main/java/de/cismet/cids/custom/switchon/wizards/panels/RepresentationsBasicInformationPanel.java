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
import de.cismet.cids.custom.switchon.wizards.MetaDataWizardAction;
import de.cismet.cids.custom.switchon.wizards.NameProvider;

import de.cismet.cids.dynamics.CidsBean;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class RepresentationsBasicInformationPanel
        extends GenericAbstractWizardPanel<RepresentationsBasicInformationVisualPanel> implements NameProvider,
        PropertyChangeListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(RepresentationsBasicInformationPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new RepresentationsBasicInformationPanel object.
     */
    public RepresentationsBasicInformationPanel() {
        super(RepresentationsBasicInformationVisualPanel.class);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new RepresentationsBasicInformationVisualPanel(Taggroups.REPRESENTATION_TYPE);
    }

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
        return org.openide.util.NbBundle.getMessage(
                RepresentationsBasicInformationPanel.class,
                "RepresentationsBasicInformationPanel.name");
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    @Override
    public boolean isValid() {
        final CidsBean representation = getComponent().getCidsBean();
        final String name = (String)representation.getProperty("name");
        final String desc = (String)representation.getProperty("description");
        final CidsBean type = (CidsBean)representation.getProperty("type");

        boolean valid = true;
        if (StringUtils.isBlank(name) || StringUtils.isBlank(desc) || (type == null)) {
            valid = false;
        }
        return valid;
    }
}
