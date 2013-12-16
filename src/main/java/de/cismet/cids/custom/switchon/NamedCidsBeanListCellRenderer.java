/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import de.cismet.cids.dynamics.CidsBean;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public final class NamedCidsBeanListCellRenderer extends DefaultListCellRenderer {

    //~ Methods ----------------------------------------------------------------

    @Override
    public Component getListCellRendererComponent(final JList list,
            final Object value,
            final int index,
            final boolean isSelected,
            final boolean cellHasFocus) {
        final Component c = super.getListCellRendererComponent(
                list,
                value,
                index,
                isSelected,
                cellHasFocus);

        if ((c instanceof JLabel) && (value instanceof CidsBean)) {
            // assume modelinput
            final CidsBean bean = (CidsBean)value;
            ((JLabel)c).setText((String)bean.getProperty("name")); // NOI18N
        }

        return c;
    }
}
