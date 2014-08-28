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
     * DOCUMENT ME!
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
}
