
package de.cismet.cids.custom.switchon.gui.utils;

/**
 *
 * @author Gilles Baatz
 */
public class TagsComboBox  extends QueryComboBox{
    
        public TagsComboBox(final Taggroups taggroup) {
        super("SELECT t.ID,"
                    + "       t.NAME"
                    + " FROM tag t"
                    + " JOIN taggroup g ON t.taggroup = g.id "
                    + " WHERE g.name ilike '" + taggroup.getValue() + "'"
                            + " ORDER BY t.name");
        }
    
}
