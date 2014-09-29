/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public interface InfoReceiver {

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  information  DOCUMENT ME!
     */
    void setInformation(String information);

    /**
     * DOCUMENT ME!
     *
     * @param  error  DOCUMENT ME!
     */
    void setError(String error);
}
