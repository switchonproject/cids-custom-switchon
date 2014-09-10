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
 * A Utility class, which loads images according to the first letter of a string or the content type.
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

    //~ Enums ------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    public enum ImageSize {

        //~ Enum constants -----------------------------------------------------

        PIXEL_16("_16.png"), PIXEL_32("_32.png");

        //~ Instance fields ----------------------------------------------------

        private String value;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new ImageSize object.
         *
         * @param  value  DOCUMENT ME!
         */
        private ImageSize(final String value) {
            this.value = value;
        }
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * If the parameter extension is not null and no image for the ContentType was found, then a image for that
     * extension is searched.
     *
     * @param   contentTypeName  DOCUMENT ME!
     * @param   imageSize        DOCUMENT ME!
     * @param   extension        DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Image getImageForContentType(final String contentTypeName,
            final ImageSize imageSize,
            final String extension) {
        if (contentTypeName == null) {
            return getFallBackImage(imageSize);
        }

        URL resource = null;
        if (contentTypeName.contains("/")) {
            resource = ImageGetterUtils.class.getResource(
                    "/de/cismet/tools/gui/downloadmanager/documenttypes/"
                            + contentTypeName.split("/")[1]
                            + imageSize.value);
        }

        Image image = getFallBackImage(imageSize);
        if (resource != null) {
            try {
                image = ImageIO.read(resource);
            } catch (IOException ex) {
                LOG.error("Error while reading an icon", ex);
            }
        } else if (extension != null) {
            image = getImageForExtension(extension, imageSize);
        }
        return image;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   extension  DOCUMENT ME!
     * @param   imageSize  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Image getImageForExtension(final String extension, final ImageSize imageSize) {
        if (extension == null) {
            return getFallBackImage(imageSize);
        }

        final URL resource = ImageGetterUtils.class.getResource(
                "/de/cismet/tools/gui/downloadmanager/documenttypes/"
                        + extension.toLowerCase()
                        + imageSize.value);

        Image image = getFallBackImage(imageSize);
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
     * @param   imageSize  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private static Image getFallBackImage(final ImageSize imageSize) {
        final URL resource = ImageGetterUtils.class.getResource(
                "/de/cismet/tools/gui/downloadmanager/documenttypes/fallback_single"
                        + imageSize.value);
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
     * Get image according to the first letter of a string.
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
