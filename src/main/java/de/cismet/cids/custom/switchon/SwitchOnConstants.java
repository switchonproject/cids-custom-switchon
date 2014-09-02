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

import org.apache.commons.lang.StringUtils;

import org.jdom.Element;

import de.cismet.cids.custom.switchon.gui.utils.CismapUtils;

import de.cismet.tools.PropertyReader;

import de.cismet.tools.configuration.Configurable;
import de.cismet.tools.configuration.ConfigurationManager;
import de.cismet.tools.configuration.NoWriteError;

/**
 * DOCUMENT ME!
 *
 * @author   srichter
 * @version  $Revision$, $Date$
 */
public final class SwitchOnConstants implements Configurable {

    //~ Static fields/initializers ---------------------------------------------

    public static final SwitchOnConstants COMMONS = new SwitchOnConstants();
    public static final String MLESSNUMBER =
        "nmless=5061756C612030352E31322E32303035204A75737475732032352E30372E323030382054616E6A612030362E31302E31393734";

    //
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(SwitchOnConstants.class);
    public static final String NEWLINE = "<br>";
    public static final String LINK_SEPARATOR_TOKEN = "::";

    //~ Instance fields --------------------------------------------------------

    public final String SRS_GEOM;
    public final String SRS_SERVICE;
    public String MAP_CALL_STRING;
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
            LOG.fatal("SwitchOnConstants Error!", ex);
            throw new RuntimeException(ex);
        }
        try {
            final ConfigurationManager manager = CismapUtils.getCismapPlugin().getConfigurationManager();
            final String home = manager.getHome();
            final String fs = manager.getFileSeperator();
            final String folder = manager.getFolder();
            final String fileName = "switchOnConstantsConfiguration.xml";

            manager.configure(this, home + fs + folder + fs + fileName);
        } catch (final Exception ex) {
            LOG.fatal("SwitchOnConstants Error while loading the xml!", ex);
        }
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public void configure(final Element parent) {
        final Element previewMap = parent.getChild("previewMap");
        final String url = previewMap.getChildText("previewMapUrl");
        if (StringUtils.isNotBlank(url)) {
            MAP_CALL_STRING = url;
        }
    }

    @Override
    public void masterConfigure(final Element parent) {
    }

    @Override
    public Element getConfiguration() throws NoWriteError {
        return null;
    }
}
