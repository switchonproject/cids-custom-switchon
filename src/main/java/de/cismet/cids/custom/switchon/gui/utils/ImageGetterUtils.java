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
import org.apache.commons.lang.StringUtils;

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
        Image image = null;
        try {
            image = ImageIO.read(ImageGetterUtils.class.getResource(
                        "/de/cismet/cids/custom/switchon/contentTypeIcons/"
                                + contentTypeName.replace('/', '-')
                                + ".png"));
        } catch (IOException ex) {
            image = UNKOWN;
        }
        return image;
    }
    
    public static Image getImageForString(String str){
        char letter = '\0';
        if(StringUtils.isNotBlank(str)){
            letter = str.charAt(0);
        }
        return getImageForLetter(letter);
    }
    
    public static Image getImageForLetter(char letter){
        if(!Character.isAlphabetic(letter)){
            return DOKUMENT;
        }
        letter = Character.toLowerCase(letter);
                Image image = null;
        try {
            image = ImageIO.read(ImageGetterUtils.class.getResource(
                        "/de/cismet/cids/custom/switchon/letterIcons/document-attribute-"
                                + letter
                                + ".png"));
        } catch (IOException ex) {
            image = UNKOWN;
        }
        return image;
    }
    
    
}
