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
public class TagsJList extends QueryJList {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(TagsJList.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new TagsJList object.
     */
    public TagsJList() {
        super("", "Tag");
    }

    /**
     * Creates a new TagsJList object.
     *
     * @param  taggroup  DOCUMENT ME!
     */
    public TagsJList(final Taggroups taggroup) {
        super("SELECT t.ID,"
                    + "       t.NAME"
                    + " FROM tag t"
                    + " JOIN taggroup g ON t.taggroup = g.id "
                    + " WHERE g.name ilike '" + taggroup.getValue() + "'"
                    + " ORDER BY t.name",
            "Tag");
    }
    /**
     * Creates a new TagsJList object.
     *
     * @param  taggroup1  DOCUMENT ME!
     * @param  taggroup2  DOCUMENT ME!
     */
    public TagsJList(final Taggroups taggroup1, final Taggroups taggroup2) {
        super("SELECT t.ID,"
                    + "       t.NAME"
                    + " FROM tag t"
                    + " JOIN taggroup g ON t.taggroup = g.id "
                    + " WHERE g.name ilike '" + taggroup1.getValue() + "'"
                    + " OR g.name ilike '" + taggroup2.getValue() + "'"
                    + " ORDER BY t.name",
            "Tag");
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  taggroup  DOCUMENT ME!
     */
    public void changeModelToTaggroup(final Taggroups taggroup) {
        executeQueryAndSetModel("SELECT t.ID,"
                    + "       t.NAME"
                    + " FROM tag t"
                    + " JOIN taggroup g ON t.taggroup = g.id "
                    + " WHERE g.name ilike '" + taggroup.getValue() + "'"
                    + " ORDER BY t.name");
    }

    /**
     * DOCUMENT ME!
     *
     * @param  taggroupID  DOCUMENT ME!
     */
    public void changeModelToTaggroup(final int taggroupID) {
        executeQueryAndSetModel("SELECT t.ID,"
                    + "       t.NAME"
                    + " FROM tag t"
                    + " WHERE t.taggroup = " + taggroupID
                    + " ORDER BY t.name");
    }
}
