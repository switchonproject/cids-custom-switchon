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
public enum Taggroups {

    //~ Enum constants ---------------------------------------------------------

    ACCESS_CONDITIONS("access conditions"), ACCESS_LIMITATIONS("access limitations"),
    APPLICATION_PROFILE("application profile"), CATCHMENTS("catchments"), COLLECTION("collection"),
    CONTENT_TYPE("content type"), FUNCTION("function"), GEOGRAPHY("geography"),
    HYDROLOGICAL_CONCEPT("hydrological concept"), KEYWORDS_INSPIRE_THEMES_1_0("keywords - INSPIRE themes 1.0"),
    KEYWORDS_OPEN("keywords - open"), LANGUAGE("language"), LOCATION("location"),
    META_DATA_STANDARD("meta-data standard"), META_DATA_TYPE("meta-data type"), PROTOCOL("protocol"),
    REALTIONSHIP_TYPE("realtionship type"), REPRESENTATION_TYPE("representation type"), ROLE("role"), SRID("srid"),
    TOPIC_CATEGORY("topic category"), CONFORMITY("conformity"), RESOURCE_TYPE("resource type");

    //~ Instance fields --------------------------------------------------------

    private final String name;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new TAGGROUPS object.
     *
     * @param  name  id DOCUMENT ME!
     */
    Taggroups(final String name) {
        this.name = name;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public String getValue() {
        return name;
    }
}
