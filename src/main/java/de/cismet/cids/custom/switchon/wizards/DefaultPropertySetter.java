/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards;

import java.sql.Timestamp;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Future;

import de.cismet.cids.custom.switchon.utils.CidsBeanUtils;
import de.cismet.cids.custom.switchon.utils.TagUtils;

import de.cismet.cids.dynamics.CidsBean;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class DefaultPropertySetter {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            DefaultPropertySetter.class);
    // resource
    private static final Future<CidsBean> defaultTypeResource = TagUtils.fetchFutureTagByName("external data");
    private static final Future<CidsBean> defaultLanguage = TagUtils.fetchFutureTagByName("eng");
    private static final Future<CidsBean> defaultSrid = TagUtils.fetchFutureTagByName("EPSG:4326"); // NOI18N
    private static final Future<CidsBean> defaultLocation = TagUtils.fetchFutureTagByName("World"); // NOI18N
    private static final Future<CidsBean> defaultAccessConditions = TagUtils.fetchFutureTagByName(
            "other");
    private static final Future<CidsBean> defaultAccessLimitations = TagUtils.fetchFutureTagByName("No limitation");
    private static final Future<CidsBean> defaultConformity = TagUtils.fetchFutureTagByName("Not evaluated");
    // Contact
    private static final Future<CidsBean> defaultRoleResource = TagUtils.fetchFutureTagByName("resourceProvider");
    private static final Future<CidsBean> defaultRoleMetaData = TagUtils.fetchFutureTagByName("pointOfContact");
    // Representation
    private static final Future<CidsBean> defaultTypeRepresentation = TagUtils.fetchFutureTagByName("original data");
    private static final Future<CidsBean> defaultContentType = TagUtils.fetchFutureTagByName(
            "application/octet-stream");
    private static final Future<CidsBean> defaultFunction = TagUtils.fetchFutureTagByName("information");
    private static final Future<CidsBean> defaultProtocol = TagUtils.fetchFutureTagByName("WWW:LINK-1.0-http--link");
    private static final Future<CidsBean> defaultApplicationprofile = TagUtils.fetchFutureTagByName("Web Browser");
    // meta-data
    private static final Future<CidsBean> defaultStandard = TagUtils.fetchFutureTagByName("none");
    private static final Future<CidsBean> defaultTypeMetaData = TagUtils.fetchFutureTagByName("basic meta-data");
    private static Future relationshipMetaDataType = TagUtils.fetchFutureTagByName("relationship meta-data");
    private static final Future defaultMetaDataStandard = TagUtils.fetchFutureTagByName("SWITCH-ON SIM");
    public static final String defaultNameMetaData = "Autogenerated SWITCH-ON Basic Meta-Data";
    private static final String defaultDescriptionMetaData =
        "Basic Meta-Data record created, derived or imported by the SWITCH-ON project according to the SWITCH-ON Standard Information Model (SIM) for Meta-Data for the SWITCH-ON Spatial Information Platform (SIP).";
    // meta-data-contact
    private static final String defaultDescriptionMetaDataContact =
        "Sharing Water-related Information to Tackle Changes in the Hydrosphere – for Operational Needs (SWITCH-ON).";
    public static final String defaultOrganisationMetaDataContact = "SWITCH-ON";
    public static final String defaultNameMetaDataContact = defaultOrganisationMetaDataContact;
    private static final Future<CidsBean> defaultRoleMetaDataContact = TagUtils.fetchFutureTagByName("pointOfContact");
    private static final String defaultURLMetaDataContact = "http://www.water-switch-on.eu/";

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  resource  DOCUMENT ME!
     */
    public static void setDefaultsToResourceCidsBean(final CidsBean resource) {
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultLanguage, resource, "language");
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultTypeResource, resource, "type");
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultSrid, resource, "srid");         // NOI18N
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultLocation, resource, "location"); // NOI18N
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultAccessConditions, resource, "accessconditions");
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultAccessLimitations, resource, "accesslimitations");
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultConformity, resource, "conformity");
    }

    /**
     * DOCUMENT ME!
     *
     * @param  contact  DOCUMENT ME!
     */
    public static void setDefaultsToContactCidsBean(final CidsBean contact) {
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultRoleResource, contact, "role"); // NOI18N
    }
    /**
     * DOCUMENT ME!
     *
     * @param  contact  DOCUMENT ME!
     */
    public static void setDefaultsToContactCidsBeanMetaData(final CidsBean contact) {
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultRoleMetaData, contact, "role"); // NOI18N
    }

    /**
     * DOCUMENT ME!
     *
     * @param   contact  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    public static void setDefaultsToMetaDataContactCidsBean(final CidsBean contact) throws Exception {
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultRoleMetaDataContact, contact, "role"); // NOI18N
        contact.setProperty("name", defaultNameMetaDataContact);
        contact.setProperty("description", defaultDescriptionMetaDataContact);
        contact.setProperty("organisation", defaultOrganisationMetaDataContact);
        contact.setProperty("url", defaultURLMetaDataContact);
    }

    /**
     * DOCUMENT ME!
     *
     * @param   representation  DOCUMENT ME!
     * @param   resource        DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    public static void setDefaultsToRepresentationCidsBeanDerivedByResource(final CidsBean representation,
            final CidsBean resource) throws Exception {
        final String resourceName = (String)resource.getProperty("name");
        representation.setProperty("name", resourceName + " Representation");
        representation.setProperty("description", "Representation of the resource " + resourceName);
        representation.setProperty("uuid", UUID.randomUUID().toString());
    }

    /**
     * DOCUMENT ME!
     *
     * @param  representation  DOCUMENT ME!
     */
    public static void setDefaultsToRepresentationCidsBean(final CidsBean representation) {
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultTypeRepresentation, representation, "type");
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultContentType, representation, "contenttype");
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultFunction, representation, "function");
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultProtocol, representation, "protocol");
    }

    /**
     * DOCUMENT ME!
     *
     * @param   metadata  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    public static void setDefaultsToMetaDataCidsBean(final CidsBean metadata) throws Exception {
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultLanguage, metadata, "language");
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultStandard, metadata, "standard");
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultContentType, metadata, "contenttype");
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultTypeMetaData, metadata, "type");
        metadata.setProperty("creationdate", new Timestamp(new Date().getTime()));
    }

    /**
     * DOCUMENT ME!
     *
     * @param   metadata  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    public static void setDefaultsToRelationshipMetaDataCidsBean(final CidsBean metadata) throws Exception {
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultLanguage, metadata, "language");
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultStandard, metadata, "standard");
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(relationshipMetaDataType, metadata, "type");
    }

    /**
     * DOCUMENT ME!
     *
     * @param   metadata  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    public static void setDefaultsToMetaDataCidsBeanForBasicProfile(final CidsBean metadata) throws Exception {
        metadata.setProperty("name", defaultNameMetaData);
        metadata.setProperty("description", defaultDescriptionMetaData);
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultLanguage, metadata, "language");
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultMetaDataStandard, metadata, "standard");
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultContentType, metadata, "contenttype");
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(defaultTypeMetaData, metadata, "type");
    }

    /**
     * DOCUMENT ME!
     *
     * @param   relationship  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static CidsBean createNewMetaDataForRelationshipCidsBean(final CidsBean relationship) {
        final CidsBean metaData;
        try {
            metaData = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "metadata");
            DefaultPropertySetter.setDefaultsToRelationshipMetaDataCidsBean(metaData);
            metaData.setProperty("name", relationship.toString() + " Meta-Data");
            metaData.setProperty("description", "Meta-Data of the " + relationship.toString());
            final CidsBean contact = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "contact");
            DefaultPropertySetter.setDefaultsToMetaDataContactCidsBean(contact);
            metaData.setProperty("contact", contact);
            return metaData;
        } catch (Exception ex) {
            DefaultPropertySetter.LOG.error(ex, ex);
        }
        return null;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   metadata  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static boolean isStandardMetaData(final CidsBean metadata) {
        if (metadata != null) {
            final String name = (String)metadata.getProperty("name");
            return defaultNameMetaData.equals(name);
        } else {
            return false;
        }
    }
}
