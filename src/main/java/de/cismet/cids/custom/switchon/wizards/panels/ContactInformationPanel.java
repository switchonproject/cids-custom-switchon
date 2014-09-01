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
public class ContactInformationPanel extends GenericAbstractWizardPanel<ContactInformationVisualPanel>
        implements NameProvider,
            PropertyChangeListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(ContactInformationPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new ContactInformationPanel object.
     */
    public ContactInformationPanel() {
        super(ContactInformationVisualPanel.class);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean contact = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_CONTACT_BEAN);
        contact.addPropertyChangeListener(this);
        getComponent().setCidsBean(contact);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final CidsBean contact = getComponent().getCidsBean();
        contact.removePropertyChangeListener(this);
        getComponent().dispose();
    }

    @Override
    public String getName() {
        return java.util.ResourceBundle.getBundle("de/cismet/cids/custom/switchon/wizards/panels/Bundle")
                    .getString("ContactInformation.name");
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    @Override
    public boolean isValid() {
        final CidsBean resource = getComponent().getCidsBean();
        final String organisation = (String)resource.getProperty("organisation");
        final String description = (String)resource.getProperty("description");

        return StringUtils.isNotBlank(description) && StringUtils.isNotBlank(organisation);
    }
}
