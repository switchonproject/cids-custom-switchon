/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.metadata;

import Sirius.navigator.connection.SessionManager;

import Sirius.server.middleware.types.MetaClass;
import Sirius.server.middleware.types.MetaObject;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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

    private static final Logger LOG = Logger.getLogger(TRMMImport.class);
    private static final String TAGGROUP = "keywords - X-CUAHSI";
    private static final String TAGGROUP_SEPARATOR = ";";
    private static final String RESOURCE_NAME = "title";
    private static final String RESOURCE_KEYWORDS = "keywords";
    private static final String RESOURCE_URI = "link";

    //~ Instance fields --------------------------------------------------------

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
            final Map<String, String> rowAsMap = it.next();
            final String resourceName = rowAsMap.get(RESOURCE_NAME);
            LOG.info("processing Resource #" + i + " '" + resourceName + "'");
            // System.out.println("processing Resource #" + i + " '" + resourceName + "'");

            final String keywordsString = rowAsMap.get(RESOURCE_KEYWORDS);
            final List<CidsBean> keywords = this.findTags(keywordsString);
            final int keywordsSize = keywords.size();

            String query = "SELECT " + resourceClass.getID() + ", " + resourceClass.getPrimaryKey() + " ";
            query += "FROM " + resourceClass.getTableName();
            query += " WHERE name ilike '%" + resourceName.replaceAll("'", "''") + "%' limit 1";
            final MetaObject[] metaObjects = SessionManager.getProxy()
                        .getMetaObjectByQuery(SessionManager.getSession().getUser(), query, "SWITCHON");

            if ((metaObjects != null) && (metaObjects.length > 0)) {
                if (metaObjects.length > 1) {
                    for (final MetaObject metaObject : metaObjects) {
                        LOG.error("duplicate name for object " + metaObject.getId()
                                    + ": " + metaObject.getAttributeByFieldName("name").getValue());
                    }

                    final String message = "name '" + resourceName + "' in '" + csvFile
                                + "' is not unique in Meta-Data Repository: " + metaObjects.length;
                    LOG.error(message);
                    // throw new Exception(message);
                }

                final CidsBean resourceBean = metaObjects[0].getBean();
                final Collection<CidsBean> tags = resourceBean.getBeanCollectionProperty("tags");
                for (final CidsBean tagBean : tags) {
                    for (final CidsBean keywordBean : keywords) {
                        if ((tagBean.getProperty("name").toString().equalsIgnoreCase(
                                            keywordBean.getProperty("name").toString()))
                                    && (((CidsBean)tagBean.getProperty("taggroup")).getProperty("name").toString()
                                        .equalsIgnoreCase(
                                            ((CidsBean)keywordBean.getProperty("taggroup")).getProperty("name")
                                                .toString()))) {
                            keywords.remove(keywordBean);
                        }
                    }
                }
                if (LOG.isDebugEnabled()) {
                    LOG.debug("adding " + keywords.size() + " of " + keywordsSize
                                + " X-CUAHSI Keywords to Resource #" + i + " '" + resourceName + "'");
                }
                resourceBean.addCollectionElements("tags", keywords);

                if (!this.dryRun && !keywords.isEmpty()) {
                    resourceBean.persist();
                    LOG.info("Resource #" + i + " '" + resourceName
                                + "' successfully imported into Meta-Data Repository.");
                } else {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Resource #" + i + " '" + resourceName
                                    + "' not updated, no new keywords added.");
                    }
                }
            } else {
                final String message = "resource with name '" + resourceName + "' in '" + csvFile
                            + "' not found in Meta-Data Repository!";
                LOG.warn(message);
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
     * @param  args  DOCUMENT ME!
     */
    public static void main(final String[] args) {
        final Properties p = new Properties();

        p.put("log4j.appender.File", "org.apache.log4j.FileAppender"); // NOI18N
        p.put("log4j.appender.File.file", "switchon.log.txt");         // NOI18N
        p.put("log4j.appender.File.layout", "org.apache.log4j.SimpleLayout");
        p.put("log4j.appender.File.append", "false");                  // NOI18N
        p.put("log4j.logger.de.cismet", "ERROR, File");                // NOI18N
        p.put("log4j.logger.de.cismet", "ALL, Remote");

        p.put("log4j.appender.Remote", "org.apache.log4j.net.SocketAppender"); // NOI18N
        p.put("log4j.appender.Remote.remoteHost", "localhost");                // NOI18N
        p.put("log4j.appender.Remote.port", "4445");                           // NOI18N
        p.put("log4j.appender.Remote.locationInfo", "true");                   // NOI18N
        p.put("log4j.rootLogger", "ALL,Remote");                               // NOI18N

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
                xcuahsiBatchImport.importXcuahiKeywords(csvFile);

                System.out.println(csvFile + " ----------------------------------------");
                System.out.println(csvFile + "MISSING KEYWORDS: ");
                for (final String missingKeyword : xcuahsiBatchImport.missingKeywords) {
                    System.out.println(missingKeyword);
                }

                System.out.println(csvFile + "MISSING RESOURCES: ");
                for (final String missingResource : xcuahsiBatchImport.missingResources) {
                    System.out.println(missingResource);
                }

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
