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
        setGeneralInformation(org.openide.util.NbBundle.getMessage(
                ContactInformationVisualPanel.class,
                "ContactInformationVisualPanel.infoBoxPanel.generalInformation")); // NOI18N
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        final ContactInformationVisualPanel component = (ContactInformationVisualPanel)super.createComponent();
        component.setModel(this);
        return component;
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
        resource.addPropertyChangeListener(this);
        getComponent().setCidsBean(resource);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final CidsBean resource = getComponent().getCidsBean();
        resource.removePropertyChangeListener(this);
        getComponent().dispose();
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(ContactInformationPanel.class, "ContactInformation.name");
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    @Override
    public boolean isValid() {
        final CidsBean resource = getComponent().getCidsBean();
        final CidsBean contact = (CidsBean)resource.getProperty("contact");      // NOI18N
        final String organisation = (String)contact.getProperty("organisation"); // NOI18N

        final boolean isValid = StringUtils.isNotBlank(organisation);

        if (isValid) {
            showGeneralInformation();
        } else {
            showWarning(org.openide.util.NbBundle.getMessage(
                    ContactInformationPanel.class,
                    "ContactInformationPanel.isValid().missingOrganisation"));
        }

        return isValid;
    }
}
