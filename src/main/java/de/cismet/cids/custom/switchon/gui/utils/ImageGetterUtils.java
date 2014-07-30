/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui.utils;

import org.apache.commons.lang.StringUtils;

import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.IOException;

import java.net.URL;

import javax.imageio.ImageIO;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class ImageGetterUtils {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            ImageGetterUtils.class);
    public static BufferedImage UNKOWN;
    public static BufferedImage DOKUMENT;
    public static final String DOCUMENT_LETTER_PATH = "/de/cismet/cids/custom/switchon/letterIcons/document-attribute-";
    public static final String CIRCLE_LETTER_PATH = "/de/cismet/cids/custom/switchon/letterIcons/icon-circle";

    static {
        try {
            UNKOWN = ImageIO.read(ImageGetterUtils.class.getResource(
                        "/de/cismet/cids/custom/switchon/contentTypeIcons/unknown.png"));
        } catch (IOException ex) {
            LOG.error("Could not fetch ERROR_IMAGE", ex);
            UNKOWN = null;
        }
        try {
            DOKUMENT = ImageIO.read(ImageGetterUtils.class.getResource(
                        "/de/cismet/cids/custom/switchon/letterIcons/document.png"));
        } catch (IOException ex) {
            LOG.error("Could not fetch DOKUMENT", ex);
            DOKUMENT = null;
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

        final URL resource = ImageGetterUtils.class.getResource(
                "/de/cismet/cids/custom/switchon/contentTypeIcons/"
                        + contentTypeName.replace('/', '-')
                        + ".png");

        Image image = UNKOWN;
        if (resource != null) {
            try {
                image = ImageIO.read(resource);
            } catch (IOException ex) {
                LOG.error("Error while reading an icon", ex);
            }
        }
        return image;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   protocolName  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Image getImageForProtocol(final String protocolName) {
        if (protocolName == null) {
            return UNKOWN;
        }

        final URL resource = ImageGetterUtils.class.getResource(
                "/de/cismet/cids/custom/switchon/contentTypeIcons/"
                        + protocolName.replace('/', '-')
                        + ".png");

        Image image = UNKOWN;
        if (resource != null) {
            try {
                image = ImageIO.read(resource);
            } catch (IOException ex) {
                LOG.error("Error while reading an icon", ex);
            }
        }
        return image;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   str   DOCUMENT ME!
     * @param   path  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Image getImageForString(final String str, final String path) {
        char letter = '\0';
        if (StringUtils.isNotBlank(str)) {
            letter = str.charAt(0);
        }
        return getImageForLetter(letter, path);
    }

    /**
     * DOCUMENT ME!
     *
     * @param   letter  DOCUMENT ME!
     * @param   path    DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Image getImageForLetter(char letter, final String path) {
        if (!Character.isAlphabetic(letter)) {
            return DOKUMENT;
        }
        letter = Character.toLowerCase(letter);

        final URL resource = ImageGetterUtils.class.getResource(path + letter + ".png");

        Image image = UNKOWN;
        if (resource != null) {
            try {
                image = ImageIO.read(resource);
            } catch (IOException ex) {
                LOG.error("Error while reading an icon", ex);
            }
        }
        return image;
    }
}
