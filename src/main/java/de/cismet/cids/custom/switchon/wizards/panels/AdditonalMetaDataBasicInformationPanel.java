/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import org.apache.log4j.Logger;

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
public class AdditonalMetaDataBasicInformationPanel extends AbstractBasicInformationPanel {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(AdditonalMetaDataBasicInformationPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new AdditonalMetaDataBasicInformationPanel object.
     */
    public AdditonalMetaDataBasicInformationPanel() {
        super();
        setGeneralInformation(org.openide.util.NbBundle.getMessage(
                AdditonalMetaDataBasicInformationPanel.class,
                "AdditonalMetaDataBasicInformationVisualPanel.infobox.gerneralInfromation"));
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public String getName() {
        return NbBundle.getMessage(
                AdditonalMetaDataBasicInformationPanel.class,
                "AdditonalMetaDataBasicInformationPanel.name");
    }

    @Override
    public CidsBean getCidsBean() {
        return (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_SELECTED_METADATA_BEAN);
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        wizard.putProperty(MetaDataWizardAction.PROP_SELECTED_METADATA_BEAN, cidsBean);
    }

    @Override
    protected Taggroups getTypeTaggroup() {
        return Taggroups.META_DATA_TYPE;
    }
}
