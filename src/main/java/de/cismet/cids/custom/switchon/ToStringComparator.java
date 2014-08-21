/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import java.util.Comparator;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class ToStringComparator implements Comparator<Object> {

    //~ Methods ----------------------------------------------------------------

    @Override
    public int compare(final Object o1, final Object o2) {
        return String.valueOf(o1).compareTo(String.valueOf(o2));
    }
}
