/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui;

import org.jdesktop.swingx.JXList;

import java.util.ArrayList;
import java.util.List;

/**
 * The JXList from SwingX 1.6 contains bugs in the methods JXList.getSelectedValue(), JXList.getSelectedValues() and
 * JXList.setSelectedValue(). See also:
 * https://java.net/jira/browse/SWINGX-1263?page=com.atlassian.jira.plugin.system.issuetabpanels%3Aall-tabpanel
 * Therefore the method were copied from SwingX 1.6.5-1.
 *
 * @version  $Revision$, $Date$
 */
public class JXListBugFixes extends JXList {

    //~ Methods ----------------------------------------------------------------

    /**
     * Returns the value for the smallest selected cell index; <i>the selected value</i> when only a single item is
     * selected in the list. When multiple items are selected, it is simply the value for the smallest selected index.
     * Returns {@code null} if there is no selection.
     *
     * <p>This is a convenience method that simply returns the model value for {@code getMinSelectionIndex}, taking into
     * account sorting and filtering.</p>
     *
     * @return  the first selected value
     *
     * @see     #getMinSelectionIndex
     * @see     #getModel
     * @see     #addListSelectionListener
     */
    @Override
    public Object getSelectedValue() {
        final int i = getSelectedIndex();
        return (i == -1) ? null : getElementAt(i);
    }

    /**
     * Selects the specified object from the list, taking into account sorting and filtering.
     *
     * @param  anObject      the object to select
     * @param  shouldScroll {@code true} if the list should scroll to display the selected object, if one exists;
     *                       otherwise {@code false}
     */
    @Override
    public void setSelectedValue(final Object anObject, final boolean shouldScroll) {
        // Note: this method is a copy of JList.setSelectedValue,
        // including comments. It simply usues getElementCount() and getElementAt()
        // instead of the model.
        if (anObject == null) {
            setSelectedIndex(-1);
        } else if (!anObject.equals(getSelectedValue())) {
            int i;
            int c;
            for (i = 0, c = getElementCount(); i < c; i++) {
                if (anObject.equals(getElementAt(i))) {
                    setSelectedIndex(i);
                    if (shouldScroll) {
                        ensureIndexIsVisible(i);
                    }
                    repaint();
                    /**
                     * FIX-ME setSelectedIndex does not redraw all the time
                     * with the basic l&f*
                     */
                    return;
                }
            }
            setSelectedIndex(-1);
        }
        repaint();
        /**
         * FIX-ME setSelectedIndex does not redraw all the time with the
         * basic l&f*
         */
    }

    /**
     * Returns an array of all the selected values, in increasing order based on their indices in the list and taking
     * into account sorting and filtering.
     *
     * @return  the selected values, or an empty array if nothing is selected
     *
     * @see     #isSelectedIndex
     * @see     #getModel
     * @see     #addListSelectionListener
     */
    @Override
    public Object[] getSelectedValues() {
        final int[] selectedIndexes = getSelectedIndices();
        final Object[] selectedValues = new Object[selectedIndexes.length];
        for (int i = 0; i < selectedIndexes.length; i++) {
            selectedValues[i] = getElementAt(selectedIndexes[i]);
        }
        return selectedValues;
    }

    @Override
    public List getSelectedValuesList() {
        final int[] selectedIndexes = getSelectedIndices();
        final ArrayList selectedValues = new ArrayList(selectedIndexes.length);
        for (int i = 0; i < selectedIndexes.length; i++) {
            selectedValues.add(i, getElementAt(selectedIndexes[i]));
        }
        return selectedValues;
    }
}
