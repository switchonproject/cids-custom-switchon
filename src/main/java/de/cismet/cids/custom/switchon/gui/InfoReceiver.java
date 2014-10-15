/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui;

/**
 * An {@InfoReceiver} receives information from an {@InfoProvider} and shows it to the user.
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public interface InfoReceiver {

    //~ Methods ----------------------------------------------------------------

    /**
     * Show an information to the user.
     *
     * @param  information  DOCUMENT ME!
     */
    void setInformation(String information);

    /**
     * Show an error to the user.
     *
     * @param  error  DOCUMENT ME!
     */
    void setError(String error);
}
