/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import org.apache.log4j.Logger;

import de.cismet.cids.custom.switchon.utils.Taggroups;
import de.cismet.cids.custom.switchon.wizards.MetaDataWizardAction;

import de.cismet.cids.dynamics.CidsBean;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class RelationshipsBasicInformationPanel extends AbstractBasicInformationPanel {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(RelationshipsBasicInformationPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new RelationshipsBasicInformationPanel object.
     */
    public RelationshipsBasicInformationPanel() {
        super();
        setGeneralInformation(org.openide.util.NbBundle.getMessage(
                AdditonalMetaDataBasicInformationPanel.class,
                "RelationshipsBasicInformationPanel.infobox.gerneralInfromation"));
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(
                RelationshipsBasicInformationPanel.class,
                "RelationshipsBasicInformationPanel.name");
    }

    @Override
    public CidsBean getCidsBean() {
        return (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_CREATED_RELATIONSHIP_BEAN);
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        wizard.putProperty(MetaDataWizardAction.PROP_CREATED_RELATIONSHIP_BEAN, cidsBean);
    }

    @Override
    protected Taggroups getTypeTaggroup() {
        return Taggroups.RELATIONSHIP_TYPE;
    }
}
