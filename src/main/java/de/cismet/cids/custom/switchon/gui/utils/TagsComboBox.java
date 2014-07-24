/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui.utils;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class TagsComboBox extends QueryComboBox {

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new TagsComboBox object.
     *
     * @param  taggroup  DOCUMENT ME!
     */
    public TagsComboBox(final Taggroups taggroup) {
        super("SELECT t.ID,"
                    + "       t.NAME"
                    + " FROM tag t"
                    + " JOIN taggroup g ON t.taggroup = g.id "
                    + " WHERE g.name ilike '" + taggroup.getValue() + "'"
                    + " ORDER BY t.name");
    }
}
