/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import org.apache.log4j.Logger;

import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;

import java.awt.Component;

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
public class LicenseInformationPanel extends GenericAbstractWizardPanel<LicenseInformationVisualPanel>
        implements NameProvider {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(LicenseInformationPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new LicenseInformationPanel object.
     */
    public LicenseInformationPanel() {
        super(LicenseInformationVisualPanel.class);
        setGeneralInformation(org.openide.util.NbBundle.getMessage(
                LicenseInformationVisualPanel.class,
                "LicenseInformationVisualPanel.infoBoxPanel.generalInformation")); // NOI18N
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new LicenseInformationVisualPanel();
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
        getComponent().setCidsBean(resource);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        // do nothing
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(LicenseInformationPanel.class, "LicenseInformationPanel.name");
    }
}
