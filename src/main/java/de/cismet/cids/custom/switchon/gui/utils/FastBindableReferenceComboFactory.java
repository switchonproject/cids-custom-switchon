/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui.utils;

import de.cismet.cids.custom.switchon.utils.Taggroups;
import de.cismet.cids.editors.FastBindableReferenceCombo;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class FastBindableReferenceComboFactory {

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   taggroup  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static FastBindableReferenceCombo createTagsFastBindableReferenceComboBox(final Taggroups taggroup) {
        final String query = "SELECT t.ID,"
                    + "       t.NAME"
                    + " FROM TAG t"
                    + " JOIN taggroup g ON t.taggroup = g.id "
                    + " WHERE g.name ilike '" + taggroup.getValue() + "'";
        final FastBindableReferenceCombo fastBindableReferenceCombo = new FastBindableReferenceCombo(
                query,
                "%1$2s",
                new String[] { "NAME" });
        fastBindableReferenceCombo.setSorted(true);
        return fastBindableReferenceCombo;
    }
}
