/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import java.io.Serializable;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
@XmlRootElement
public final class Rainevent implements Serializable {

    //~ Instance fields --------------------------------------------------------

    private int interval;
    private List<Double> precipitations;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new Rainevent object.
     */
    public Rainevent() {
    }

    /**
     * Creates a new Rainevent object.
     *
     * @param  interval        secondsToMm DOCUMENT ME!
     * @param  precipitations  DOCUMENT ME!
     */
    public Rainevent(final int interval, final List<Double> precipitations) {
        this.interval = interval;
        this.precipitations = precipitations;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  interval  DOCUMENT ME!
     */
    public void setInterval(final int interval) {
        this.interval = interval;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getInterval() {
        return this.interval;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  precipitations  DOCUMENT ME!
     */
    public void setPrecipitations(final List<Double> precipitations) {
        this.precipitations = precipitations;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public List<Double> getPrecipitations() {
        return this.precipitations;
    }
}
