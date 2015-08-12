/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.metadata;

import Sirius.navigator.connection.SessionManager;
import Sirius.navigator.exception.ConnectionException;

import Sirius.server.middleware.types.MetaClass;
import Sirius.server.middleware.types.MetaObject;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.apache.log4j.Logger;

import java.io.File;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.custom.switchon.utils.TagUtils;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.navigator.utils.ClassCacheMultiple;

/**
 * X-CUAHSI Batch Import.
 *
 * @author   Pascal Dihé
 * @version  $Revision$, $Date$
 */
public final class XcuahsiBatchImport {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(XcuahsiBatchImport.class);
    private static final String TAGGROUP = "keywords - X-CUAHSI";
    private static final String TAGGROUP_SEPARATOR = ";";
    private static final String RESOURCE_NAME = "title";
    private static final String RESOURCE_KEYWORDS = "keywords";
    private static final String RESOURCE_URI = "link";

    //~ Instance fields --------------------------------------------------------

    long keywordsFound = 0;
    long keywordsAdded = 0;

    private final HashMap<String, CidsBean> tagMap = new HashMap<String, CidsBean>();
    private final boolean dryRun;
    private final MetaClass resourceClass;

    private final Set<String> missingKeywords = new HashSet<String>();
    private final Set<String> missingResources = new HashSet<String>();

    private final String[] csvFiles;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new XcuahsiBatchImport object.
     *
     * @param  csvFiles  DOCUMENT ME!
     * @param  dryRun    DOCUMENT ME!
     */
    private XcuahsiBatchImport(final String[] csvFiles, final boolean dryRun) {
        this.csvFiles = csvFiles;
        this.dryRun = dryRun;

        if (dryRun) {
            LOG.info("perfoming dry run of  X-CUAHSI Batch Import");
        } else {
            LOG.info("perfoming X-CUAHSI Batch Import");
        }

        try {
            DevelopmentTools.initSessionManagerFromRestfulConnectionOnLocalhost(
                "SWITCHON",
                "Administratoren",
                "admin",
                "cismet");

            LOG.info("server connection created");
        } catch (Exception ex) {
            LOG.fatal(ex.getMessage(), ex);
            System.exit(1);
        }

        this.resourceClass = ClassCacheMultiple.getMetaClass("SWITCHON", "resource");
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   csvFile  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    private void collectMissingTags(final String csvFile) throws Exception {
        LOG.info("keywords in CSV File '" + csvFile + "' that are missing in the Meta-Data Repository");

        final CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
        final ObjectMapper mapper = new CsvMapper();

        final MappingIterator<Map<String, String>> it = mapper.reader(Map.class)
                    .with(bootstrapSchema)
                    .readValues(this.getClass().getResourceAsStream(csvFile));

        int i = 0;
        while (it.hasNext()) {
            i++;
            final Map<String, String> rowAsMap = it.next();
            final String keywordsString = rowAsMap.get(RESOURCE_KEYWORDS);
            final List<CidsBean> keywords = this.findTags(keywordsString);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param   csvFile  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    private void importXcuahiKeywords(final String csvFile) throws Exception {
        LOG.info("importing X-CUAHSI Keywords from CSV File '" + csvFile + "'");

        final CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
        final ObjectMapper mapper = new CsvMapper();

        final MappingIterator<Map<String, String>> it = mapper.reader(Map.class)
                    .with(bootstrapSchema)
                    .readValues(this.getClass().getResourceAsStream(csvFile));

        int i = 0;
        while (it.hasNext()) {
            i++;
            boolean fromUrl = false;
            final Map<String, String> rowAsMap = it.next();
            final String resourceName = rowAsMap.get(RESOURCE_NAME).trim();
            LOG.info("processing Resource #" + i + " '" + resourceName + "'");
            // System.out.println("processing Resource #" + i + " '" + resourceName + "'");

            final String keywordsString = rowAsMap.get(RESOURCE_KEYWORDS);
            final List<CidsBean> keywords = this.findTags(keywordsString);
            final int keywordsSize = keywords.size();
            this.keywordsFound += keywordsSize;

            MetaObject[] metaObjects = this.getResourceByName(resourceName);

            if ((metaObjects == null) || (metaObjects.length == 0)) {
                final String message = "resource #" + i + " with name '" + resourceName + "' in '" + csvFile
                            + "' not found in Meta-Data Repository!";
                LOG.warn(message);

                final String resourceLink = rowAsMap.get(RESOURCE_URI).trim();
                if (LOG.isDebugEnabled()) {
                    LOG.debug("trying to find Resource #" + i + " '" + resourceName + "' by URI '"
                                + resourceLink + "'");
                }
                metaObjects = this.getResourceByLink(resourceLink);
                fromUrl = true;
            }

            if ((metaObjects != null) && (metaObjects.length > 0)) {
                if (metaObjects.length > 1) {
                    for (final MetaObject metaObject : metaObjects) {
                        LOG.warn("duplicate name for resource #" + i + " '" + metaObject.getId()
                                    + ": " + metaObject.getAttributeByFieldName("name").getValue()
                                    + " in '" + csvFile + "'");
                    }

                    final String message = "name '" + resourceName + "' of resource #" + i + " in '" + csvFile
                                + "' is not unique in Meta-Data Repository: " + metaObjects.length;
                    LOG.error(message);
                    // throw new Exception(message);
                }

                for (final MetaObject metaObject : metaObjects) {
                    final CidsBean resourceBean = metaObject.getBean();
                    if (fromUrl) {
                        LOG.warn("name '" + resourceName + "' of resource #" + i
                                    + " in CSV file '" + csvFile + "' does not match name '"
                                    + resourceBean.getProperty("name").toString()
                                    + "' of resource #" + resourceBean.getProperty("id").toString()
                                    + " in Meta-Data Repository: "
                                    + resourceBean.getProperty("name").toString().equalsIgnoreCase(resourceName));
                    }

                    final Collection<CidsBean> tags = resourceBean.getBeanCollectionProperty("tags");
                    for (final CidsBean tagBean : tags) {
                        final ListIterator<CidsBean> keywordsIerator = keywords.listIterator();
                        while (keywordsIerator.hasNext()) {
                            final CidsBean keywordBean = keywordsIerator.next();
                            if ((tagBean.getProperty("name").toString().equalsIgnoreCase(
                                                keywordBean.getProperty("name").toString()))
                                        && (((CidsBean)tagBean.getProperty("taggroup")).getProperty("name").toString()
                                            .equalsIgnoreCase(
                                                ((CidsBean)keywordBean.getProperty("taggroup")).getProperty("name")
                                                    .toString()))) {
                                keywordsIerator.remove();
                            }
                        }
                    }

                    this.keywordsAdded += keywords.size();
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("adding " + keywords.size() + " of " + keywordsSize
                                    + " X-CUAHSI Keywords to Resource #" + i + " '" + resourceName + "'");
                    }

                    if (!this.dryRun && !keywords.isEmpty()) {
                        resourceBean.addCollectionElements("tags", keywords);
                        resourceBean.persist();
                        LOG.info("Resource #" + i + " '" + resourceName
                                    + "' successfully imported into Meta-Data Repository.");
                    } else {
                        if (LOG.isDebugEnabled()) {
                            LOG.debug("Resource #" + i + " '" + resourceName
                                        + "' not updated, no new keywords added.");
                        }
                    }
                }
            } else {
                final String message = "resource with name '" + resourceName + "' in '" + csvFile
                            + "' not found in Meta-Data Repository!";
                LOG.error(message);
                missingResources.add(resourceName);
                // throw new Exception();
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param   tagsString  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    private List<CidsBean> findTags(final String tagsString) throws Exception {
        final ArrayList<CidsBean> tagsList = new ArrayList<CidsBean>();

        if (tagsString.contains(TAGGROUP_SEPARATOR)) {
            final String[] tagStrings = tagsString.split(TAGGROUP_SEPARATOR);
            for (final String tagString : tagStrings) {
                final CidsBean tagBean = this.findTag(tagString.trim());
                if (tagBean != null) {
                    tagsList.add(tagBean);
                }
            }
        } else {
            final CidsBean tagBean = this.findTag(tagsString.trim());
            if (tagBean != null) {
                tagsList.add(tagBean);
            }
        }

        return tagsList;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   tagName  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    private CidsBean findTag(final String tagName) throws Exception {
        if (this.tagMap.containsKey(tagName)) {
            return this.tagMap.get(tagName);
        } else {
            final CidsBean tag = TagUtils.fetchTagByNameAndTaggroup(tagName, TAGGROUP);
            if (tag != null) {
                this.tagMap.put(tagName, tag);
                return tag;
            } else {
                final String message = "X-CUAHI Keyword '" + tagName + "' not found in Meta-Data Repository!";
                LOG.warn(message);
                this.missingKeywords.add(tagName);
                // throw new Exception(message);
                return null;
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param   resourceName  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  ConnectionException  DOCUMENT ME!
     */
    private MetaObject[] getResourceByName(final String resourceName) throws ConnectionException {
        String query = "SELECT " + resourceClass.getID() + ", " + resourceClass.getPrimaryKey() + " ";
        query += "FROM " + resourceClass.getTableName();
        query += " WHERE name ilike '" + resourceName.replaceAll("'", "''") + "'";
        return SessionManager.getProxy().getMetaObjectByQuery(SessionManager.getSession().getUser(), query, "SWITCHON");
    }

    /**
     * DOCUMENT ME!
     *
     * @param   link  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  ConnectionException  DOCUMENT ME!
     */
    private MetaObject[] getResourceByLink(final String link) throws ConnectionException {
        String query = "SELECT " + resourceClass.getID() + ", "
                    + resourceClass.getTableName() + "." + resourceClass.getPrimaryKey() + " ";
        query += "FROM " + resourceClass.getTableName();
        query +=
            " INNER JOIN jt_resource_representation ON jt_resource_representation.resource_reference = resource.id ";
        query += "INNER JOIN representation ON jt_resource_representation.representationid = representation.id ";
        query += "WHERE representation.contentlocation = '" + link + "';";

        return SessionManager.getProxy().getMetaObjectByQuery(SessionManager.getSession().getUser(), query, "SWITCHON");
    }

    /**
     * DOCUMENT ME!
     *
     * @param  args  DOCUMENT ME!
     */
    public static void main(final String[] args) {
        final String logfile = System.getProperty("user.home") + File.separator
                    + XcuahsiBatchImport.class.getSimpleName() + ".log.txt";
        System.out.println("Logfile: " + logfile);
        final Properties p = new Properties();

        p.put("log4j.appender.File", "org.apache.log4j.FileAppender"); // NOI18N
        p.put("log4j.appender.File.file", logfile);                    // NOI18N
        p.put("log4j.appender.File.layout", "org.apache.log4j.SimpleLayout");
        p.put("log4j.appender.File.append", "false");                  // NOI18N
        // p.put("log4j.rootLogger", "WARN,File");                       // NOI18N
        p.put("log4j.logger.de.cismet.cids.custom.switchon.metadata", "ERROR,File");

//        p.put("log4j.appender.Remote", "org.apache.log4j.net.SocketAppender"); // NOI18N
//        p.put("log4j.appender.Remote.remoteHost", "localhost");                // NOI18N
//        p.put("log4j.appender.Remote.port", "4445");                           // NOI18N
//        p.put("log4j.appender.Remote.locationInfo", "true");                   // NOI18N
//        p.put("log4j.rootLogger", "ALL,Remote");                               // NOI18N

        org.apache.log4j.PropertyConfigurator.configure(p);

        final String[] csvFiles = {
                "BatchA.csv",
                "BatchB.csv",
                "BatchC.csv",
                "BatchD.csv",
                "BatchE.csv",
                "BatchF.csv",
                "BatchG.csv"
            };

        final XcuahsiBatchImport xcuahsiBatchImport = new XcuahsiBatchImport(csvFiles, true);

        try {
            for (final String csvFile : csvFiles) {
                // xcuahsiBatchImport.collectMissingTags(csvFile);
                xcuahsiBatchImport.importXcuahiKeywords(csvFile);

                System.out.println(csvFile + " ----------------------------------------");
                System.out.println("KEYWORDS IN CSV FILE: " + xcuahsiBatchImport.keywordsFound);
                System.out.println("KEYWORDS ADDED TO RESOURCE: " + xcuahsiBatchImport.keywordsAdded);
                System.out.println("MISSING KEYWORDS: ");
                for (final String missingKeyword : xcuahsiBatchImport.missingKeywords) {
                    System.out.println(missingKeyword);
                }

                System.out.println("MISSING RESOURCES: ");
                for (final String missingResource : xcuahsiBatchImport.missingResources) {
                    System.out.println(missingResource);
                }

                xcuahsiBatchImport.keywordsFound = 0;
                xcuahsiBatchImport.keywordsAdded = 0;
                xcuahsiBatchImport.missingKeywords.clear();
                xcuahsiBatchImport.missingResources.clear();
            }
        } catch (Exception ex) {
            LOG.fatal(ex.getMessage(), ex);
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        System.exit(0);
    }
}
