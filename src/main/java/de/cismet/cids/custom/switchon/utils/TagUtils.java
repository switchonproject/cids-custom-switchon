/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.utils;

import Sirius.server.middleware.types.MetaObject;

import de.cismet.cids.dynamics.CidsBean;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class TagUtils {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(TagUtils.class);

    //~ Methods ----------------------------------------------------------------

    /**
     * Returns the description of tag. The
     *
     * @param   tag  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static String getDescriptionOfTag(final Object tag) {
        CidsBean tagBean = null;
        if (tag instanceof MetaObject) {
            tagBean = ((MetaObject)tag).getBean();
        } else if (tag instanceof CidsBean) {
            tagBean = (CidsBean)tag;
        }
        String description;
        if (tagBean != null) {
            description = (String)tagBean.getProperty("description");
        } else {
            description = "No description available.";
        }
        return description;
    }
}
