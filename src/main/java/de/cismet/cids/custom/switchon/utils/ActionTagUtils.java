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

import org.apache.log4j.Logger;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class ActionTagUtils {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient Logger LOG = Logger.getLogger(ActionTagUtils.class);

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   tagToCheck  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static boolean checkActionTag(final String tagToCheck) {
        boolean result;
        try {
            result = SessionManager.getConnection().getConfigAttr(SessionManager.getSession().getUser(), tagToCheck)
                        != null;
        } catch (ConnectionException ex) {
            LOG.error("Can not check ActionTag!", ex);
            result = false;
        }
        return result;
    }
}
