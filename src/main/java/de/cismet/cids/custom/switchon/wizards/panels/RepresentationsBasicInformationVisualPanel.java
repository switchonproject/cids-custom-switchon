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
public class RepresentationsBasicInformationVisualPanel extends BasicInformationVisualPanel {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            RepresentationsBasicInformationVisualPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form RepresentationsBasicInformationVisualPanel.
     *
     * @deprecated  Constructor for Netbeans
     */
    public RepresentationsBasicInformationVisualPanel() {
        this(Taggroups.META_DATA_TYPE);
        LOG.warn("Do not use this constructor, it is only there for the Netbeans GUI editor.", new Exception()); // NOI18N
    }

    /**
     * Creates a new RepresentationsBasicInformationVisualPanel object.
     *
     * @param  taggroup  DOCUMENT ME!
     */
    RepresentationsBasicInformationVisualPanel(final Taggroups taggroup) {
        super(taggroup);
        infoBoxPanel.setGeneralInformation(org.openide.util.NbBundle.getMessage(
                RepresentationsBasicInformationVisualPanel.class,
                "RepresentationsBasicInformationVisualPanel.generalInformation"));
    }
}
