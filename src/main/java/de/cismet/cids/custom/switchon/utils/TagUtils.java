/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.utils;

import Sirius.navigator.connection.SessionManager;
import Sirius.navigator.exception.ConnectionException;

import Sirius.server.middleware.types.MetaClass;
import Sirius.server.middleware.types.MetaObject;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.navigator.utils.ClassCacheMultiple;

import de.cismet.commons.concurrency.CismetExecutors;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class TagUtils {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(TagUtils.class);
    private static final ExecutorService executor = CismetExecutors.newCachedThreadPool();

    //~ Methods ----------------------------------------------------------------

    /**
     * Returns the description of tag. The tag can be a MetaObject or a CidsBean.
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

    /**
     * Fetches a future of a tag by its name, if no tag is available or something goes wrong, then null will be
     * returned.
     *
     * @param   name  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Future<CidsBean> fetchFutureTagByName(final String name) {
        final Future<CidsBean> futureTag = executor.submit(new Callable<CidsBean>() {

                    @Override
                    public CidsBean call() throws Exception {
                        return fetchTagByName(name);
                    }
                });
        return futureTag;
    }

    /**
     * Fetches a tag by its name, if no tag is available or something goes wrong, then null will be returned.
     *
     * @param   name  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static CidsBean fetchTagByName(final String name) {
        try {
            final MetaClass MB_MC = ClassCacheMultiple.getMetaClass("SWITCHON", "tag");
            String query = "SELECT " + MB_MC.getID() + ", " + MB_MC.getPrimaryKey() + " ";
            query += "FROM " + MB_MC.getTableName();
            query += " WHERE name ilike '" + name + "' limit 1";
            final MetaObject[] metaObjects = SessionManager.getProxy()
                        .getMetaObjectByQuery(SessionManager.getSession().getUser(), query, "SWITCHON");
            if ((metaObjects != null) && (metaObjects.length == 1)) {
                return metaObjects[0].getBean();
            } else {
                return null;
            }
        } catch (ConnectionException ex) {
            LOG.error(ex, ex);
            return null;
        }
    }

    /**
     * Iterates through a list of tags and returns the first tag whose taggroup equals the taggroup in the parameter.
     *
     * @param   tags      DOCUMENT ME!
     * @param   taggroup  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static CidsBean returnFirstOccurrenceOfTaggroup(final List<CidsBean> tags, final String taggroup) {
        for (final CidsBean tag : tags) {
            final CidsBean taggroupBean = (CidsBean)tag.getProperty("taggroup");
            final String taggroupName = (String)taggroupBean.getProperty("name");
            if (taggroupName.equals(taggroup)) {
                return tag;
            }
        }
        return null;
    }
}
