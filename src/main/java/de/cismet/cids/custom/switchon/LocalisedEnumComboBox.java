/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import org.openide.util.WeakListeners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Arrays;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public final class LocalisedEnumComboBox<T extends LocalisedEnum<T>> extends JComboBox {

    //~ Instance fields --------------------------------------------------------

    private final transient Class<T> lEnum;
    private final transient Available<T> available;

    private final transient ListCellRenderer listR;
    private final transient ActionListener actionL;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new LocalisedEnumComboBox object.
     *
     * @param  lEnum  DOCUMENT ME!
     */
    public LocalisedEnumComboBox(final Class<T> lEnum) {
        this(lEnum, new Available.PositiveAvailable());
    }

    /**
     * Creates a new LocalisedEnumComboBox object.
     *
     * @param   lEnum      DOCUMENT ME!
     * @param   available  DOCUMENT ME!
     *
     * @throws  IllegalStateException  DOCUMENT ME!
     */
    public LocalisedEnumComboBox(final Class<T> lEnum, final Available<T> available) {
        this.lEnum = lEnum;
        this.available = available;

        final T[] values;
        try {
            values = (T[])lEnum.getMethod("values", (Class[])null).invoke(null, (Object[])null);                   // NOI18N
        } catch (final Exception ex) {
            throw new IllegalStateException("localisedEnum class must implement static method: T[] values()", ex); // NOI18N
        }

        Arrays.sort(values);

        boolean foundAvailable = false;
        for (final T value : values) {
            addItem(value);

            if (!foundAvailable && available.isAvailable(value)) {
                setSelectedItem(value);
                foundAvailable = true;
            }
        }

        listR = new ResolutionRenderer();
        actionL = new LocalisedEnumActionListener();
        setRenderer(listR);
        addActionListener(WeakListeners.create(ActionListener.class, actionL, this));
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private final class LocalisedEnumActionListener implements ActionListener {

        //~ Instance fields ----------------------------------------------------

        private transient T currentItem;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new ResolutionActionListener object.
         */
        public LocalisedEnumActionListener() {
            currentItem = (T)getSelectedItem();
        }

        //~ Methods ------------------------------------------------------------

        @Override
        public void actionPerformed(final ActionEvent e) {
            final T selected = (T)getSelectedItem();
            if (available.isAvailable(selected)) {
                currentItem = selected;
            } else {
                setSelectedItem(currentItem);
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private final class ResolutionRenderer extends DefaultListCellRenderer {

        //~ Methods ------------------------------------------------------------

        @Override
        public Component getListCellRendererComponent(final JList list,
                final Object value,
                final int index,
                final boolean isSelected,
                final boolean cellHasFocus) {
            final Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if ((value != null) && (lEnum.isAssignableFrom(value.getClass())) && (c instanceof JLabel)) {
                final T lEnumValue = (T)value;
                final JLabel label = (JLabel)c;

                label.setText(lEnumValue.getLocalisedName());

                label.setEnabled(available.isAvailable(lEnumValue));
            }

            return c;
        }
    }
}
