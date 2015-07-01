/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.cismet.cids.custom.switchon.metadata;

import Sirius.navigator.connection.SessionManager;

import Sirius.server.middleware.types.MetaClass;
import Sirius.server.middleware.types.MetaObject;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

import org.apache.log4j.Logger;

import java.net.URL;

import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.custom.switchon.gui.utils.CismapUtils;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.navigator.utils.ClassCacheMultiple;

/**
 * Import BfG GRDC Data as single Stations form postprocessed GRDC Meta-Data XSLX File.
 *
 * @author   Pascal Dihé
 * @version  $Revision$, $Date$
 */
public class GRDCStationTUWienImport {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(GRDCStationTUWienImport.class);

    //~ Methods ----------------------------------------------------------------

    /**
     * Imports TU Wien GRDC re-purposed Station meta-data into the SWITCH-ON Meta-Data Repository. Creates a new
     * resource for each re-purposed station dataset or updates existing resources. Requires a template bean to copy the
     * shared properties (keywords, etc.).
     *
     * <p>TODO: create relationship object to the general GRDC Resource</p>
     *
     * @param  args  DOCUMENT ME!
     */
    public static void main(final String[] args) {
        try {
            DevelopmentTools.initSessionManagerFromRestfulConnectionOnLocalhost(
                "SWITCHON",
                "Administratoren",
                "admin",
                "cismet");
            LOG.info("server connection created");

            final MetaClass resourceClass = ClassCacheMultiple.getMetaClass("SWITCHON", "resource");
            // final MetaClass representationClass = ClassCacheMultiple.getMetaClass("SWITCHON", "representation");
            final MetaClass relationshipClass = ClassCacheMultiple.getMetaClass("SWITCHON", "relationship");

            // id of template object (Station 6242500)
            // select id from "public".resource where name = 'WIEN-NUSSDORF (6242500)';
            final int resourceTemplateObjectId = 7223;
            final int resourceTemplateClassId = resourceClass.getId();
            final int tagClassId = ClassCacheMultiple.getMetaClass("SWITCHON", "tag").getId();
            final CidsBean resourceTemplate = SessionManager.getProxy()
                        .getMetaObject(resourceTemplateObjectId + "@" + resourceTemplateClassId + "@SWITCHON")
                        .getBean();

            // id of the GRDC root resource object
// final int grdcObjectId = 7222;
// final CidsBean grdcResource = SessionManager.getProxy()
// .getMetaObject(grdcObjectId + "@" + resourceTemplateClassId + "@SWITCHON")
// .getBean();
            final MetaObject[] grdMetaObjects = SessionManager.getProxy()
                        .getMetaObjectByQuery(
                            SessionManager.getSession().getUser(),
                            "select "
                            + resourceTemplateClassId
                            + " as class_id, id as object_id from resource where resource.name ilike '%global runoff database%' order by id limit 1");
            final CidsBean grdcResource = grdMetaObjects[0].getBean();

            final MetaObject[] repurposedTagMetaObjects = SessionManager.getProxy()
                        .getMetaObjectByQuery(
                            SessionManager.getSession().getUser(),
                            "select "
                            + tagClassId
                            + " as class_id, id as object_id from tag where tag.name = 'repurposed' order by id limit 1");
            final CidsBean repurposedTag = repurposedTagMetaObjects[0].getBean();

            if ((resourceTemplate.getBeanCollectionProperty("representation") == null)
                        || resourceTemplate.getBeanCollectionProperty("representation").isEmpty()) {
                throw new Exception("Template Bean '" + resourceTemplate + "' does not contain a representation!");
            }

            final CidsBean representationTemplate = resourceTemplate.getBeanCollectionProperty("representation").get(0);
            resourceTemplate.getBeanCollectionProperty("representation").clear();

            if (LOG.isDebugEnabled()) {
                LOG.debug("Template Bean '" + resourceTemplate + "' loaded");
            }

            final CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            final ObjectMapper mapper = new CsvMapper();

            final MappingIterator<Map<String, String>> it = mapper.reader(Map.class)
                        .with(bootstrapSchema)
                        .readValues(GRDCStationTUWienImport.class.getResourceAsStream("GRDC_Stations_TUWien.csv"));

            int i = 0;
            // final DateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
            // dateformat.setLenient(false);

            final DateFormat yearDateformat = new SimpleDateFormat("yyyy");
            yearDateformat.setLenient(false);

            final NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);

            final GeometryFactory geometryFactory = new GeometryFactory();

            while (it.hasNext()) {
                i++;
                CidsBean resourceBean = null;
                CidsBean representationBean = null;
                final CidsBean relationshipBean = relationshipClass.getEmptyInstance().getBean();
                final Map<String, String> rowAsMap = it.next();
                final String stationId = rowAsMap.get("ID");
                final String stationName = rowAsMap.get("Station_Location") + " (" + stationId + ") Peak Flow";

                LOG.info("processing TU Wien GRDC Station #" + i + " '" + stationName + "'");
                System.out.println("processing TU Wien GRDC Station #" + i + " '" + stationName + "'");

                try {
                    String query = "SELECT " + resourceClass.getID() + ", " + resourceClass.getPrimaryKey() + " ";
                    query += "FROM " + resourceClass.getTableName();
                    query += " WHERE name ilike '%" + stationName.replaceAll("'", "''") + "%' limit 1";
                    final MetaObject[] metaObjects = SessionManager.getProxy()
                                .getMetaObjectByQuery(SessionManager.getSession().getUser(), query, "SWITCHON");
                    if ((metaObjects != null) && (metaObjects.length == 1)) {
                        LOG.warn("GRDC Station '" + stationName
                                    + "', does already exist, updating station");
                        resourceBean = metaObjects[0].getBean();

                        if ((resourceBean.getBeanCollectionProperty("representation") != null)
                                    && !resourceBean.getBeanCollectionProperty("representation").isEmpty()) {
                            representationBean = resourceBean.getBeanCollectionProperty("representation").get(0);
                            resourceBean.getBeanCollectionProperty("representation").clear();
                        } else {
                            representationBean = GRDCStationImport.cloneCidsBean(representationTemplate, false);
                        }
                    } else {
                        if (LOG.isDebugEnabled()) {
                            LOG.debug("GRDC Station '" + stationName + "' not found, creating new station");
                        }
                        resourceBean = GRDCStationImport.cloneCidsBean(resourceTemplate, false);
                        representationBean = GRDCStationImport.cloneCidsBean(representationTemplate, false);
                    }
                } catch (Exception ex) {
                    LOG.error("could not search for GRDC Station '" + stationName + "'", ex);
                }

                if (resourceBean == null) {
                    resourceBean = GRDCStationImport.cloneCidsBean(resourceTemplate, false);
                }

                if (representationBean == null) {
                    representationBean = GRDCStationImport.cloneCidsBean(representationTemplate, false);
                }

                // RESOURCE ----------------------------------------------------
                // resourceBean.getMetaObject().setStatus(MetaObject.NEW);
                // resourceBean.setProperty("id", -1);
                resourceBean.setProperty("name", stationName);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("name: " + resourceBean.getProperty("name"));
                }

                final StringBuilder resourceDescription = new StringBuilder();

                resourceDescription.append("Monthly peak flows at GRDC Station ")
                        .append(stationName)
                        .append(' ')
                        .append(" at river ")
                        .append(rowAsMap.get("River_Name"))
                        .append(" in country ")
                        .append(rowAsMap.get("Country"))
                        .append(" covering ")
                        .append(rowAsMap.get("Catchment_Area (km2)"))
                        .append("km² catchment area.");

                resourceBean.setProperty("description", resourceDescription.toString());
                if (LOG.isDebugEnabled()) {
                    LOG.debug("description: " + resourceBean.getProperty("description"));
                }

                final UUID uuid = UUID.randomUUID();
                resourceBean.setProperty("uuid", uuid.toString());
                if (LOG.isDebugEnabled()) {
                    LOG.debug("uuid: " + resourceBean.getProperty("uuid"));
                }

                Timestamp date;
                String dateString = null;
                String dateProperty = null;

                try {
                    dateString = rowAsMap.get("d_start");
                    dateProperty = "fromdate";
                    date = new Timestamp(yearDateformat.parse(dateString).getTime());
                    resourceBean.setProperty(dateProperty, date);
                    if (LOG.isDebugEnabled()) {
                        LOG.debug(dateProperty + ": " + resourceBean.getProperty(dateProperty));
                    }
                } catch (Exception ex) {
                    LOG.error("could not set data property '" + dateProperty
                                + "' to '" + dateString + "'", ex);
                }

                try {
                    dateString = rowAsMap.get("d_end");
                    dateProperty = "todate";
                    date = new Timestamp(yearDateformat.parse(dateString).getTime());
                    resourceBean.setProperty(dateProperty, date);
                    if (LOG.isDebugEnabled()) {
                        LOG.debug(dateProperty + ": " + resourceBean.getProperty(dateProperty));
                    }
                } catch (Exception ex) {
                    LOG.error("could not set data property '" + dateProperty
                                + "' to '" + dateString + "'", ex);
                }

                resourceBean.setProperty("creationdate", new Timestamp(System.currentTimeMillis()));
                if (LOG.isDebugEnabled()) {
                    LOG.debug("creationdate: " + resourceBean.getProperty("creationdate"));
                }

                resourceBean.setProperty("lastmodificationdate", new Timestamp(System.currentTimeMillis()));
                if (LOG.isDebugEnabled()) {
                    LOG.debug("lastmodificationdate: " + resourceBean.getProperty("lastmodificationdate"));
                }

                resourceBean.setProperty("publicationdate", new Timestamp(System.currentTimeMillis()));
                if (LOG.isDebugEnabled()) {
                    LOG.debug("publicationdate: " + resourceBean.getProperty("publicationdate"));
                }

                try {
                    final double lat = numberFormat.parse(rowAsMap.get("LAT")).doubleValue();
                    final double lng = numberFormat.parse(rowAsMap.get("LON")).doubleValue();
                    final double alt = numberFormat.parse(rowAsMap.get("Outlet_elevation (m.a.s.l)")).doubleValue();
                    final Coordinate coordinate = new Coordinate(lng, lat, alt);
                    final Geometry geometry = geometryFactory.createPoint(coordinate);
                    geometry.setSRID(4326);
                    final CidsBean geometryBean = CismapUtils.createGeometryBean(geometry);
                    resourceBean.setProperty("spatialcoverage", geometryBean);
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("spatialcoverage: "
                                    + ((CidsBean)resourceBean.getProperty("spatialcoverage")).getProperty("geo_field"));
                    }
                } catch (Exception ex) {
                    LOG.fatal("could not set spatial coverage "
                                + rowAsMap.get("lat") + " / "
                                + rowAsMap.get("long"),
                        ex);
                    continue;
                }

                // REPRESENTATION ----------------------------------------------
                representationBean.setProperty("name", "Flood Peak Data (" + stationId + ")");
                representationBean.setProperty(
                    "description",
                    "The data in the .peak "
                            + "file shows the date of the start of a flood event, the end "
                            + "of the event, the date of maximum flood discharge, maximum "
                            + "discharge and some index representing the volume of water.");

                try {
                    // TODO: replace with real storage URL!
                    final URL contentLocation = new URL(
                            "https://repos.deltares.nl/repos/SWITCH-ON/Data/WP3/Repurposed/Flood_Experiment1_PeakFiles/"
                                    + rowAsMap.get("ID")
                                    + ".peak");
                    representationBean.setProperty("contentlocation", contentLocation.toString());
                    resourceBean.getBeanCollectionProperty("representation").add(representationBean);
                } catch (Exception ex) {
                    LOG.error("could not set content location for TU Wien GRDC Station '" + stationName + "'", ex);
                    continue;
                }

                // save the new resource
                resourceBean = resourceBean.persist();

                // RELATIONSHP -------------------------------------------------
                // establish the relationship
                relationshipBean.setProperty("name", "Flood Peak Data from GRDC Station " + stationId);
                relationshipBean.setProperty(
                    "description",
                    "Flood peak data for GRDC Station "
                            + stationName
                            + " has been derived from the Global Runoff Data Centre's Global Runoff Data Base (GRDB) by the institute of hydraulic engineering and water resources management of the technical university of vienna.");
                relationshipBean.setProperty("type", repurposedTag);
                relationshipBean.setProperty("uuid", UUID.randomUUID());

                relationshipBean.getBeanCollectionProperty("fromresources").add(grdcResource);
                relationshipBean.setProperty("toresource", resourceBean);

                relationshipBean.persist();

                LOG.info("TU Wien GRDC Station #" + i + " '" + stationName
                            + "' successfully imported into Meta-Data Repository.");
            }

            LOG.info(i + " TU Wien GRDC Stations processed");
            System.exit(0);
        } catch (Exception ex) {
            LOG.fatal(ex.getMessage(), ex);
            System.exit(1);
        }
    }
}
