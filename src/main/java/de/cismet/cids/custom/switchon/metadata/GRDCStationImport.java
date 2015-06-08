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

import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
 * @author   pd
 * @version  $Revision$, $Date$
 */
public class GRDCStationImport {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(GRDCStationImport.class);

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
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

            final int objectId = -1; // 7222;
            final int classId = resourceClass.getId();
            final CidsBean cidsBean = SessionManager.getProxy()
                        .getMetaObject(objectId + "@" + classId + "@SWITCHON")
                        .getBean();
            if (LOG.isDebugEnabled()) {
                LOG.debug("Template Bean '" + cidsBean + "' loaded");
            }

            final CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            final ObjectMapper mapper = new CsvMapper();

            final MappingIterator<Map<String, String>> it = mapper.reader(Map.class)
                        .with(bootstrapSchema)
                        .readValues(GRDCStationImport.class.getResourceAsStream("GRDC_Stations.csv"));

            int i = 0;
            final DateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
            dateformat.setLenient(false);

            final DateFormat yearDateformat = new SimpleDateFormat("yyyy");
            dateformat.setLenient(false);

            final NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);

            final GeometryFactory geometryFactory = new GeometryFactory();

            while (it.hasNext()) {
                i++;
                CidsBean templateResource = null;
                final Map<String, String> rowAsMap = it.next();
                final String stationName = rowAsMap.get("station") + '(' + rowAsMap.get("grdc_no") + ')';

                LOG.info("processing GRDC Station #" + i + " '" + stationName + "'");
                System.out.println("processing GRDC Station #" + i + " '" + stationName + "'");

                try {
                    String query = "SELECT " + resourceClass.getID() + ", " + resourceClass.getPrimaryKey() + " ";
                    query += "FROM " + resourceClass.getTableName();
                    query += " WHERE name ilike '" + stationName.replaceAll("'", "''") + "' limit 1";
                    final MetaObject[] metaObjects = SessionManager.getProxy()
                                .getMetaObjectByQuery(SessionManager.getSession().getUser(), query, "SWITCHON");
                    if ((metaObjects != null) && (metaObjects.length == 1)) {
                        LOG.warn("GRDC Station '" + stationName
                                    + "', does already exist, updating station");
                        templateResource = metaObjects[0].getBean();
                    } else {
                        if (LOG.isDebugEnabled()) {
                            LOG.debug("GRDC Station '" + stationName + "' not found, creating new station");
                        }
                        templateResource = cloneCidsBean(cidsBean, false);
                    }
                } catch (Exception ex) {
                    LOG.error("could not search for GRDC Station '" + stationName + "'", ex);
                }

                if (templateResource == null) {
                    templateResource = cloneCidsBean(cidsBean, false);
                }

                // templateResource.getMetaObject().setStatus(MetaObject.NEW);
                // templateResource.setProperty("id", -1);
                templateResource.setProperty("name", stationName);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("name: " + templateResource.getProperty("name"));
                }

                final StringBuilder description = new StringBuilder();
                description.append("GRDC Station No. ")
                        .append(rowAsMap.get("grdc_no"))
                        .append(' ')
                        .append(rowAsMap.get("station"))
                        .append(" at river ")
                        .append(rowAsMap.get("river"))
                        .append(" in country ")
                        .append(rowAsMap.get("country_code"))
                        .append(".\n")
                        .append("Altitude: ")
                        .append(rowAsMap.get("altitude"))
                        .append('\n')
                        .append("WMO subregion: ")
                        .append(rowAsMap.get("sub_reg"))
                        .append('\n')
                        .append("national station ID: ")
                        .append(rowAsMap.get("nat_id"))
                        .append('\n')
                        .append("GRDC No. of next downstream GRDC station: ")
                        .append(rowAsMap.get("ds_stat_no"))
                        .append('\n')
                        .append("daily data available from: ")
                        .append(rowAsMap.get("d_start"))
                        .append('\n')
                        .append("daily data available until: ")
                        .append(rowAsMap.get("d_end"))
                        .append('\n')
                        .append("years of daily data: ")
                        .append(rowAsMap.get("d_yrs"))
                        .append('\n')
                        .append("percentage of missing values (daily data): ")
                        .append(rowAsMap.get("d_miss"))
                        .append('\n')
                        .append("monthly data available from: ")
                        .append(rowAsMap.get("m_start"))
                        .append('\n')
                        .append("monthly data available until: ")
                        .append(rowAsMap.get("m_miss"))
                        .append('\n')
                        .append("percentage of missing values (monthly data): ")
                        .append(rowAsMap.get("m_yrs"))
                        .append('\n')
                        .append("totally earliest data available: ")
                        .append(rowAsMap.get("t_start"))
                        .append('\n')
                        .append("totally latest data available: ")
                        .append(rowAsMap.get("t_end"))
                        .append('\n')
                        .append("years (maximum using daily and monthly data): ")
                        .append(rowAsMap.get("t_yrs"))
                        .append('\n');

                templateResource.setProperty("description", description.toString());
                if (LOG.isDebugEnabled()) {
                    LOG.debug("description: " + templateResource.getProperty("description"));
                }

                final UUID uuid = UUID.randomUUID();
                templateResource.setProperty("uuid", uuid.toString());
                if (LOG.isDebugEnabled()) {
                    LOG.debug("uuid: " + templateResource.getProperty("uuid"));
                }

                Timestamp date;
                String dateString = null;
                String dateProperty = null;

                try {
                    dateString = rowAsMap.get("t_start");
                    dateProperty = "fromdate";
                    date = new Timestamp(yearDateformat.parse(dateString).getTime());
                    templateResource.setProperty(dateProperty, date);
                    if (LOG.isDebugEnabled()) {
                        LOG.debug(dateProperty + ": " + templateResource.getProperty(dateProperty));
                    }
                } catch (Exception ex) {
                    LOG.error("could not set data property '" + dateProperty
                                + "' to '" + dateString + "'", ex);
                }

                try {
                    dateString = rowAsMap.get("t_end");
                    dateProperty = "todate";
                    date = new Timestamp(yearDateformat.parse(dateString).getTime());
                    templateResource.setProperty(dateProperty, date);
                    if (LOG.isDebugEnabled()) {
                        LOG.debug(dateProperty + ": " + templateResource.getProperty(dateProperty));
                    }
                } catch (Exception ex) {
                    LOG.error("could not set data property '" + dateProperty
                                + "' to '" + dateString + "'", ex);
                }

                try {
                    dateString = rowAsMap.get("f_import");
                    dateProperty = "creationdate";
                    date = new Timestamp(dateformat.parse(dateString).getTime());
                    templateResource.setProperty(dateProperty, date);
                    if (LOG.isDebugEnabled()) {
                        LOG.debug(dateProperty + ": " + templateResource.getProperty(dateProperty));
                    }
                } catch (Exception ex) {
                    LOG.error("could not set data property '" + dateProperty
                                + "' to '" + dateString + "'", ex);
                }

                try {
                    dateString = rowAsMap.get("l_import");
                    dateProperty = "lastmodificationdate";
                    date = new Timestamp(dateformat.parse(dateString).getTime());
                    templateResource.setProperty(dateProperty, date);
                    if (LOG.isDebugEnabled()) {
                        LOG.debug(dateProperty + ": " + templateResource.getProperty(dateProperty));
                    }
                } catch (Exception ex) {
                    LOG.error("could not set data property '" + dateProperty
                                + "' to '" + dateString + "'", ex);
                }

                templateResource.setProperty("publicationdate", new Timestamp(System.currentTimeMillis()));
                if (LOG.isDebugEnabled()) {
                    LOG.debug("publicationdate: " + templateResource.getProperty("publicationdate"));
                }

                try {
                    final double lat = numberFormat.parse(rowAsMap.get("lat")).doubleValue();
                    final double lng = numberFormat.parse(rowAsMap.get("long")).doubleValue();
                    final double alt = numberFormat.parse(rowAsMap.get("altitude")).doubleValue();
                    final Coordinate coordinate = new Coordinate(lng, lat, alt);
                    final Geometry geometry = geometryFactory.createPoint(coordinate);
                    geometry.setSRID(4326);
                    final CidsBean geometryBean = CismapUtils.createGeometryBean(geometry);
                    templateResource.setProperty("spatialcoverage", geometryBean);
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("spatialcoverage: "
                                    + ((CidsBean)templateResource.getProperty("spatialcoverage")).getProperty(
                                        "geo_field"));
                    }
                } catch (Exception ex) {
                    LOG.fatal("could not set spatial coverage "
                                + rowAsMap.get("lat") + " / "
                                + rowAsMap.get("long"),
                        ex);
                    continue;
                }

                templateResource.persist();
                LOG.info("GRDC Station #" + i + " '" + stationName
                            + "' successfully imported into Meta-Data Repository");
            }

            LOG.info(i + " GRDC Stations processed");
            System.exit(0);
        } catch (Exception ex) {
            LOG.fatal(ex.getMessage(), ex);
            System.exit(1);
        }
    }

    /**
     * Clones the given bean.
     *
     * @param   bean        DOCUMENT ME!
     * @param   cloneBeans  true, iff a deep copy of the sub beans should be created
     *
     * @return  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    public static CidsBean cloneCidsBean(final CidsBean bean, final boolean cloneBeans) throws Exception {
        if (bean == null) {
            return null;
        }
        final CidsBean clone = bean.getMetaObject().getMetaClass().getEmptyInstance().getBean();

        for (final String propName : bean.getPropertyNames()) {
            if (!propName.toLowerCase().equals("id")) {
                final Object o = bean.getProperty(propName);

                if (o instanceof CidsBean) {
                    if (cloneBeans) {
                        clone.setProperty(propName, cloneCidsBean((CidsBean)o, cloneBeans));
                    } else {
                        clone.setProperty(propName, (CidsBean)o);
                    }
                } else if (o instanceof Collection) {
                    final List<CidsBean> list = (List<CidsBean>)o;
                    final List<CidsBean> newList = new ArrayList<CidsBean>();

                    for (final CidsBean tmpBean : list) {
                        if (cloneBeans) {
                            newList.add(cloneCidsBean(tmpBean, cloneBeans));
                        } else {
                            newList.add(tmpBean);
                        }
                    }
                    clone.getBeanCollectionProperty(propName).addAll(newList);
                } else if (o instanceof Geometry) {
                    clone.setProperty(propName, ((Geometry)o).clone());
                } else if (o instanceof Long) {
                    clone.setProperty(propName, new Long(o.toString()));
                } else if (o instanceof Double) {
                    clone.setProperty(propName, new Double(o.toString()));
                } else if (o instanceof Integer) {
                    clone.setProperty(propName, new Integer(o.toString()));
                } else if (o instanceof Boolean) {
                    clone.setProperty(propName, new Boolean(o.toString()));
                } else if (o instanceof String) {
                    clone.setProperty(propName, o);
                } else if (o instanceof java.sql.Timestamp) {
                    clone.setProperty(propName, ((java.sql.Timestamp)o).clone());
                } else {
                    if (o != null) {
                        LOG.error("unknown property type: " + o.getClass().getName());
                    }
                    clone.setProperty(propName, o);
                }
            }
        }

        return clone;
    }
}
