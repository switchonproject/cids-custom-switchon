/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class ContentTypeUtils {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            ContentTypeUtils.class);
    public static BufferedImage UNKOWN;

    static {
        try {
            UNKOWN = ImageIO.read(ContentTypeUtils.class.getResource(
                        "/de/cismet/cids/custom/switchon/contentTypeIcons/unknown.png"));
        } catch (IOException ex) {
            LOG.error("Could not fetch ERROR_IMAGE", ex);
            UNKOWN = null;
        }
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   contentTypeName  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Image getImageForContentType(final String contentTypeName) {
        if (contentTypeName == null) {
            return UNKOWN;
        }
        Image image = null;
        try {
            image = ImageIO.read(ContentTypeUtils.class.getResource(
                        "/de/cismet/cids/custom/switchon/contentTypeIcons/"
                                + contentTypeName.replace('/', '-')
                                + ".png"));
        } catch (IOException ex) {
            image = UNKOWN;
        }
        return image;
    }
}
