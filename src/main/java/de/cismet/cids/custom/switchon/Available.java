/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public interface Available<T> {

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   type  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    boolean isAvailable(T type);

    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    public static final class PositiveAvailable<A> implements Available<A> {

        //~ Methods ------------------------------------------------------------

        @Override
        public boolean isAvailable(final A type) {
            return true;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    public static final class NegativeAvailable<A> implements Available<A> {

        //~ Methods ------------------------------------------------------------

        @Override
        public boolean isAvailable(final A type) {
            return false;
        }
    }
}
