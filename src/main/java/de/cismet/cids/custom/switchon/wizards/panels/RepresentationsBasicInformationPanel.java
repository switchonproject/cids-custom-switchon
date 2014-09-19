/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import org.apache.log4j.Logger;

import java.awt.Component;

import de.cismet.cids.custom.switchon.utils.Taggroups;
import de.cismet.cids.custom.switchon.wizards.MetaDataWizardAction;

import de.cismet.cids.dynamics.CidsBean;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class RepresentationsBasicInformationPanel extends AbstractBasicInformationPanel {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(RepresentationsBasicInformationPanel.class);

    //~ Methods ----------------------------------------------------------------

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(
                RepresentationsBasicInformationPanel.class,
                "RepresentationsBasicInformationPanel.name");
    }

    @Override
    protected Taggroups getTypeTaggroup() {
        return Taggroups.REPRESENTATION_TYPE;
    }

    @Override
    protected String getGeneralInformation() {
        return org.openide.util.NbBundle.getMessage(
                RepresentationsBasicInformationPanel.class,
                "RepresentationsBasicInformationVisualPanel.generalInformation");
    }

    @Override
    public CidsBean getCidsBean() {
        return (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_SELECTED_REPRESENTATION_BEAN);
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        wizard.putProperty(MetaDataWizardAction.PROP_SELECTED_REPRESENTATION_BEAN, cidsBean);
    }
}
