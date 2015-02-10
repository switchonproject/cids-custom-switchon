/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui;

import java.awt.Font;

import javax.swing.JLabel;

/**
 * This class contains util-methods which can be used when implementing {@link MarkMandtoryFieldsStrong}.
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class MarkMandtoryFieldsStrongUtils {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            MarkMandtoryFieldsStrongUtils.class);

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  jlabels  DOCUMENT ME!
     */
    public static void markJLabelsStrong(final JLabel... jlabels) {
        for (final JLabel l : jlabels) {
            markJLabelString(l);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param  jLabel  DOCUMENT ME!
     */
    public static void markJLabelString(final JLabel jLabel) {
        final Font newFont = jLabel.getFont().deriveFont(Font.ITALIC);
        jLabel.setFont(newFont);
    }
}
