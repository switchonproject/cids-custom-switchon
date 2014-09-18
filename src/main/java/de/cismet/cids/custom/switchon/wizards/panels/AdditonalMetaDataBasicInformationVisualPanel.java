/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import de.cismet.cids.custom.switchon.utils.Taggroups;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class AdditonalMetaDataBasicInformationVisualPanel extends BasicInformationVisualPanel {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            AdditonalMetaDataBasicInformationVisualPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form AdditonalMetaDataBasicInformationVisualPanel.
     *
     * @deprecated  Constructor for Netbeans
     */
    public AdditonalMetaDataBasicInformationVisualPanel() {
        this(Taggroups.META_DATA_TYPE);
        LOG.warn("Do not use this constructor, it is only there for the Netbeans GUI editor.", new Exception());
    }

    /**
     * Creates new form AdditonalMetaDataBasicInformationVisualPanel.
     *
     * @param  taggroup  DOCUMENT ME!
     */
    public AdditonalMetaDataBasicInformationVisualPanel(final Taggroups taggroup) {
        super(taggroup);
        infoBoxPanel.setGeneralInformation(org.openide.util.NbBundle.getMessage(
                AdditonalMetaDataBasicInformationVisualPanel.class,
                "AdditonalMetaDataBasicInformationVisualPanel.infobox.gerneralInfromation"));
    }
}
