/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import java.util.Comparator;

import de.cismet.cids.dynamics.CidsBean;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public final class NamedCidsBeanComparator implements Comparator<CidsBean> {

    //~ Methods ----------------------------------------------------------------

    @Override
    public int compare(final CidsBean o1, final CidsBean o2) {
        if ((o1 == null) && (o2 == null)) {
            return 0;
        } else if ((o1 == null) && (o2 != null)) {
            return -1;
        } else if ((o1 != null) && (o2 == null)) {
            return 1;
        } else {
            final String name1 = (String)o1.getProperty("name"); // NOI18N
            final String name2 = (String)o2.getProperty("name"); // NOI18N

            if ((name1 == null) && (name2 == null)) {
                return 0;
            } else if ((name1 == null) && (name2 != null)) {
                return -1;
            } else if ((name1 != null) && (name2 == null)) {
                return 1;
            } else {
                return name1.compareTo(name2);
            }
        }
    }
}
