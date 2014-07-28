/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.cismet.cids.custom.switchon;

import de.cismet.tools.PropertyReader;

/**
 * DOCUMENT ME!
 *
 * @author   srichter
 * @version  $Revision$, $Date$
 */
public final class SwitchOnConstants {

    //~ Static fields/initializers ---------------------------------------------

    public static final SwitchOnConstants COMMONS = new SwitchOnConstants();
    public static final String MLESSNUMBER =
        "nmless=5061756C612030352E31322E32303035204A75737475732032352E30372E323030382054616E6A612030362E31302E31393734";

    //
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(SwitchOnConstants.class);
    public static final String NEWLINE = "<br>";
    public static final String LINK_SEPARATOR_TOKEN = "::";

    //~ Instance fields --------------------------------------------------------

    public final String SRS_GEOM;
    public final String SRS_SERVICE;
    public final String MAP_CALL_STRING;
    public final double GEO_BUFFER;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new AlkisConstants object.
     *
     * @throws  RuntimeException  DOCUMENT ME!
     */
    private SwitchOnConstants() {
        try {
            final PropertyReader serviceProperties = new PropertyReader(
                    "/de/cismet/cids/custom/switchon/previewMap.properties");

            SRS_GEOM = serviceProperties.getProperty("SRS_GEOM");
            SRS_SERVICE = serviceProperties.getProperty("SRS_SERVICE");
            MAP_CALL_STRING = serviceProperties.getProperty("MAP_CALL_STRING") + SRS_SERVICE;
            GEO_BUFFER = Double.parseDouble(serviceProperties.getProperty("GEO_BUFFER"));
        } catch (final Exception ex) {
            log.fatal("AlkisCommons Error!", ex);
            throw new RuntimeException(ex);
        }
    }
}
