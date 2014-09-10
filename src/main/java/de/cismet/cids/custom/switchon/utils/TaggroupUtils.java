/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.utils;

import java.util.HashSet;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class TaggroupUtils {

    //~ Static fields/initializers ---------------------------------------------

    private static final HashSet<Taggroups> openTaggroups = new HashSet<Taggroups>();

    static {
        openTaggroups.add(Taggroups.ACCESS_CONDITIONS);
        openTaggroups.add(Taggroups.APPLICATION_PROFILE);
        openTaggroups.add(Taggroups.CATCHMENTS);
        openTaggroups.add(Taggroups.COLLECTION);
        openTaggroups.add(Taggroups.CONTENT_TYPE);
        openTaggroups.add(Taggroups.GEOGRAPHY);
        openTaggroups.add(Taggroups.HYDROLOGICAL_CONCEPT);
        openTaggroups.add(Taggroups.KEYWORDS_OPEN);
        openTaggroups.add(Taggroups.LOCATION);
        openTaggroups.add(Taggroups.META_DATA_STANDARD);
        openTaggroups.add(Taggroups.PROTOCOL);
        openTaggroups.add(Taggroups.SRID);
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   taggroup  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static boolean isTaggroupOpen(final Taggroups taggroup) {
        return openTaggroups.contains(taggroup);
    }

    /**
     * DOCUMENT ME!
     *
     * @param   taggroupName  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static boolean isTaggroupOpen(final String taggroupName) {
        final Taggroups matchesName = getTaggroupFromString(taggroupName);
        return isTaggroupOpen(matchesName);
    }

    /**
     * DOCUMENT ME!
     *
     * @param   taggroupName  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Taggroups getTaggroupFromString(final String taggroupName) {
        Taggroups taggroupToReturn = null;
        for (final Taggroups taggroup : Taggroups.values()) {
            if (taggroup.getValue().equals(taggroupName)) {
                taggroupToReturn = taggroup;
                break;
            }
        }
        return taggroupToReturn;
    }
}
