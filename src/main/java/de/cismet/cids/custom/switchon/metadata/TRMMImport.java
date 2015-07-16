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

import org.apache.log4j.Logger;

import java.net.URL;

import java.util.Map;
import java.util.UUID;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.navigator.utils.ClassCacheMultiple;

/**
 * DOCUMENT ME!
 *
 * @author   pd
 * @version  $Revision$, $Date$
 */
public class TRMMImport {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(TRMMImport.class);

    private static final int resourceTemplateObjectId = 10480; // 8969;
    private static final String csvFile = "TRMM.csv";          // "GLDAS.csv";

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

            final int resourceTemplateClassId = resourceClass.getId();
            final CidsBean resourceTemplate = SessionManager.getProxy()
                        .getMetaObject(resourceTemplateObjectId + "@" + resourceTemplateClassId + "@SWITCHON")
                        .getBean();

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
                        .readValues(GRDCStationTUWienImport.class.getResourceAsStream(csvFile));

            int i = 0;

            while (it.hasNext()) {
                i++;
                CidsBean resourceBean = null;
                CidsBean representationBean = null;
                final Map<String, String> rowAsMap = it.next();
                final String resourceName = rowAsMap.get("name");
                boolean isUpdate = false;

                LOG.info("processing Resource #" + i + " '" + resourceName + "'");
                System.out.println("processing Resource #" + i + " '" + resourceName + "'");

                try {
                    String query = "SELECT " + resourceClass.getID() + ", " + resourceClass.getPrimaryKey() + " ";
                    query += "FROM " + resourceClass.getTableName();
                    query += " WHERE name ilike '%" + resourceName.replaceAll("'", "''") + "%' limit 1";
                    final MetaObject[] metaObjects = SessionManager.getProxy()
                                .getMetaObjectByQuery(SessionManager.getSession().getUser(), query, "SWITCHON");
                    if ((metaObjects != null) && (metaObjects.length > 0)) {
                        LOG.info("Resource '" + resourceName
                                    + "', does already exist, updating resource");
                        resourceBean = metaObjects[0].getBean();
                        isUpdate = true;

                        if (metaObjects.length > 1) {
                            LOG.warn(metaObjects.length + " entries for Resource '" + resourceName
                                        + "', do already exist, updating only the first resource!");
                        }

                        if ((resourceBean.getBeanCollectionProperty("representation") != null)
                                    && !resourceBean.getBeanCollectionProperty("representation").isEmpty()) {
                            representationBean = resourceBean.getBeanCollectionProperty("representation").get(0);
                            resourceBean.getBeanCollectionProperty("representation").clear();
                        } else {
                            representationBean = GRDCStationImport.cloneCidsBean(representationTemplate, false);
                        }
                    } else {
                        if (LOG.isDebugEnabled()) {
                            LOG.debug("Resource '" + resourceName + "' not found, creating new resource");
                        }
                        resourceBean = GRDCStationImport.cloneCidsBean(resourceTemplate, false);
                        representationBean = GRDCStationImport.cloneCidsBean(representationTemplate, false);
                    }
                } catch (Exception ex) {
                    LOG.error("could not search for Resource '" + resourceName + "'", ex);
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
                resourceBean.setProperty("name", resourceName);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("name: " + resourceBean.getProperty("name"));
                }

                final UUID uuid = UUID.randomUUID();
                resourceBean.setProperty("uuid", uuid.toString());
                if (LOG.isDebugEnabled()) {
                    LOG.debug("uuid: " + resourceBean.getProperty("uuid"));
                }

                // REPRESENTATION ----------------------------------------------

                try {
                    final URL contentLocation = new URL(rowAsMap.get("uri"));
                    representationBean.setProperty("contentlocation", contentLocation.toString());
                    resourceBean.getBeanCollectionProperty("representation").add(representationBean);
                } catch (Exception ex) {
                    LOG.error("could not set content location for Resource '" + resourceName + "'", ex);
                    continue;
                }

                // save the new resource
                resourceBean = resourceBean.persist();

                LOG.info("Resource #" + i + " '" + resourceName
                            + "' successfully imported into Meta-Data Repository.");
            }

            LOG.info(i + " Resources processed");
            System.exit(0);
        } catch (Exception ex) {
            LOG.fatal(ex.getMessage(), ex);
            System.exit(1);
        }
    }
}
