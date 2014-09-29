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
public interface InfoProvider {

    //~ Methods ----------------------------------------------------------------

    /**
     * The parameter information will be shown in another Component, typically a InfoReceiver.
     *
     * @param  information  DOCUMENT ME!
     */
    void provideInformation(String information);

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    InfoReceiver getInfoReceiver();

    /**
     * DOCUMENT ME!
     *
     * @param  infoReceiver  DOCUMENT ME!
     */
    void setInfoReceiver(InfoReceiver infoReceiver);

    /**
     * DOCUMENT ME!
     *
     * @param  error  DOCUMENT ME!
     */
    void provideError(String error);
}
