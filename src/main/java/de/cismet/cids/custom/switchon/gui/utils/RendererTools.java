/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui.utils;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXHyperlink;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 * Contains some methods to set gui components to read only.
 *
 * @version  $Revision$, $Date$
 */
public class RendererTools {

    //~ Static fields/initializers ---------------------------------------------

    private static final JTextField txtTemp;

    static {
        txtTemp = new JFormattedTextField();
        txtTemp.setEnabled(false);
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  comp  DOCUMENT ME!
     */
    public static void makeReadOnly(final JComponent comp) {
        if (comp instanceof JTextComponent) {
            final JTextComponent tComp = (JTextComponent)comp;
            tComp.setEditable(false);
            tComp.setOpaque(false);
            tComp.setBorder(null);
        } else if (comp instanceof JScrollPane) {
            final JScrollPane jsp = (JScrollPane)comp;
            jsp.setOpaque(false);
            jsp.getViewport().setOpaque(false);
        } else if (comp instanceof JComboBox) {
            final JComboBox cb = (JComboBox)comp;
            cb.setEnabled(false);
            cb.setRenderer(new CustomListCellRenderer());
            makeTextBlackOfDisabledComboBox(cb);
        } else if (comp instanceof JXDatePicker) {
            final JXDatePicker dc = (JXDatePicker)comp;
            dc.setEnabled(false);
            dc.getEditor().setDisabledTextColor(Color.BLACK);
            dc.getEditor().setOpaque(false);
            dc.getEditor().setBorder(null);
            // make the button invisible
            dc.getComponent(1).setVisible(false);
        } else if (comp instanceof JCheckBox) {
            ((JCheckBox)comp).setEnabled(false);
        } else if (comp instanceof JXHyperlink) {
            final JXHyperlink hl = (JXHyperlink)comp;
            hl.setEnabled(false);
        } else if (comp != null) {
            comp.setEnabled(false);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param  comboBox  DOCUMENT ME!
     */
    public static void makeTextBlackOfDisabledComboBox(final JComboBox comboBox) {
        final Component editorComponent = comboBox.getEditor().getEditorComponent();
        if (editorComponent instanceof JTextComponent) {
            ((JTextComponent)editorComponent).setDisabledTextColor(Color.black);
            ((JTextComponent)editorComponent).setBackground(Color.white);
        }
    }
}
