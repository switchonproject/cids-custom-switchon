/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import org.openide.util.NbBundle;

import de.cismet.cids.custom.switchon.utils.Taggroups;
import de.cismet.cids.custom.switchon.wizards.MetaDataWizardAction;

import de.cismet.cids.dynamics.CidsBean;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class ResourceBasicInformationPanel extends AbstractBasicInformationPanel {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            ResourceBasicInformationPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new ResourceBasicInformationPanel object.
     */
    public ResourceBasicInformationPanel() {
        super();
        setGeneralInformation(org.openide.util.NbBundle.getMessage(
                ResourceBasicInformationPanel.class,
                "BasicInformationVisualPanel.infoBoxPanel.generalInformation"));
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Taggroups getTypeTaggroup() {
        return Taggroups.RESOURCE_TYPE;
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(ResourceBasicInformationPanel.class, "BasicResourcePropertiesPanel.getName()");
    }

    @Override
    public CidsBean getCidsBean() {
        return (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        wizard.putProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN, cidsBean);
    }
}
