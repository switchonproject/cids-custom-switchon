/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui.utils;

import java.util.ArrayList;
import java.util.List;

import de.cismet.cids.custom.switchon.utils.Taggroups;

import de.cismet.cids.dynamics.CidsBean;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class ResourceUtils {

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   resourceBean  DOCUMENT ME!
     * @param   taggroup      DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static List<CidsBean> filterTagsOfResource(final CidsBean resourceBean, final Taggroups taggroup) {
        final List<CidsBean> tags = resourceBean.getBeanCollectionProperty("tags");
        final List<CidsBean> filteredTags = new ArrayList<CidsBean>();
        for (final CidsBean tag : tags) {
            final String taggroupName = (String)tag.getProperty("taggroup.name");
            if (taggroup.getValue().equalsIgnoreCase(taggroupName)) {
                filteredTags.add(tag);
            }
        }
        return filteredTags;
    }
}
