/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.utils;

import Sirius.navigator.connection.SessionManager;

import Sirius.server.middleware.types.MetaClass;
import Sirius.server.middleware.types.MetaObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.client.apache.ApacheHttpClient;
import com.sun.jersey.client.apache.config.ApacheHttpClientConfig;
import com.sun.jersey.client.apache.config.DefaultApacheHttpClientConfig;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.math.BigDecimal;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.navigator.utils.ClassCacheMultiple;

import de.cismet.netutil.Proxy;

/**
 * DOCUMENT ME!
 *
 * @author   Pascal Dihé
 * @version  $Revision$, $Date$
 */
final class ZenodoUploader {

    //~ Static fields/initializers ---------------------------------------------

    private static final String DEPOSITIONS_API = "deposit/depositions";
    private static final String DOMAIN = "SWITCHON";
    private static final String META_CLASS = "resource";
    private static final int TIMEOUT = 10000;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final Logger LOGGER = Logger.getLogger(ZenodoUploader.class);

    //~ Instance fields --------------------------------------------------------

    private final List<CidsBean> resources = new ArrayList<CidsBean>();
    private final Properties properties;
    private final String zenodoApiKey;
    private final File tempDirectory;
    private final MetaClass resourceClass;
    private final List<Integer> resourceIds;
    private final String zenodoApi;
    private final Map<String, Client> clientCache;
    private final Proxy proxy;
    /** for caching username/password combinations (needed for basic auth). */
    private final Map<String, String> credentialsCache;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new ZenodoUploader object.
     *
     * @param   propertyFileStream   DOCUMENT ME!
     * @param   resourcesFileStream  DOCUMENT ME!
     *
     * @throws  IOException  DOCUMENT ME!
     * @throws  Exception    DOCUMENT ME!
     */
    private ZenodoUploader(final InputStream propertyFileStream, final InputStream resourcesFileStream)
            throws IOException, Exception {
        final InputStreamReader isr = new InputStreamReader(propertyFileStream);
        final BufferedReader br = new BufferedReader(isr);

        clientCache = new HashMap<String, Client>();
        credentialsCache = new HashMap<String, String>();

        properties = new Properties();
        try {
            properties.load(br);
        } catch (IOException ioex) {
            BasicConfigurator.configure();
            LOGGER.error("could not load properties file: " + ioex.getMessage(), ioex);
            throw ioex;
        }

        PropertyConfigurator.configure(properties);

        // add trailing '/' to the root resource if not present
        if ('/' == properties.getProperty("zenodoiApi").charAt(properties.getProperty("zenodoiApi").length() - 1)) {
            this.zenodoApi = properties.getProperty("zenodoiApi");
        } else {
            this.zenodoApi = properties.getProperty("zenodoiApi") + "/"; // NOI18N
        }

        this.zenodoApiKey = properties.getProperty("zenodoApiKey");

        this.proxy = Proxy.fromPreferences();
        LOGGER.info("connecting to zenodoi API '" + this.zenodoApi + "' through proxy '" + this.proxy.getHost());

        DevelopmentTools.initSessionManagerFromRestfulConnectionOnLocalhost(
            properties.getProperty("domain"),
            properties.getProperty("usergroup"),
            properties.getProperty("username"),
            properties.getProperty("password"));
        resourceIds = new ArrayList<Integer>();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(
                    resourcesFileStream,
                    StandardCharsets.UTF_8));
        String resourceIdString;
        while ((resourceIdString = reader.readLine()) != null) {
            resourceIds.add(Integer.parseInt(resourceIdString));
        }

        LOGGER.info(resourceIds.size() + " resource IDs read");

        this.resourceClass = ClassCacheMultiple.getMetaClass(DOMAIN, META_CLASS);
        this.tempDirectory = ZenodoUploader.createTempDirectory();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ZenodoUploader TEMP Directory created: " + tempDirectory.getAbsolutePath());
        }
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private MultivaluedMap createUserParameters() {
        final MultivaluedMap queryParams = new MultivaluedMapImpl();
        queryParams.add("access_token", this.zenodoApiKey);
        return queryParams;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  RemoteException          DOCUMENT ME!
     * @throws  JsonProcessingException  DOCUMENT ME!
     */
    private JsonNode[] getDepositions() throws RemoteException, JsonProcessingException {
        final MultivaluedMap queryParameters = this.createUserParameters();
        final WebResource webResource = this.createWebResource(DEPOSITIONS_API).queryParams(queryParameters);
        WebResource.Builder builder = this.createAuthorisationHeader(webResource);
        builder = this.createMediaTypeHeaders(builder);
        final JsonNode[] objectNodes = builder.get(ObjectNode[].class);

        // for(final JsonNode objectNode:objectNodes) {
        // LOGGER.info(objectNode.toString());
        // System.out.println(new ObjectMapper().writeValueAsString(objectNode));
        // }

        return objectNodes;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   depositionId  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  RemoteException          DOCUMENT ME!
     * @throws  JsonProcessingException  DOCUMENT ME!
     */
    private ObjectNode getDeposition(final long depositionId) throws RemoteException, JsonProcessingException {
        final MultivaluedMap queryParameters = this.createUserParameters();
        final WebResource webResource = this.createWebResource(DEPOSITIONS_API + "/" + depositionId)
                    .queryParams(queryParameters);
        WebResource.Builder builder = this.createAuthorisationHeader(webResource);
        builder = this.createMediaTypeHeaders(builder);
        final ObjectNode objectNode = builder.get(ObjectNode.class);

        // for(final JsonNode objectNode:objectNodes) {
        // LOGGER.info(objectNode.toString());
        // System.out.println(new ObjectMapper().writeValueAsString(objectNode));
        // }

        return objectNode;
    }

    /**
     * Creates an empty deposition.
     *
     * @return  DOCUMENT ME!
     *
     * @throws  RemoteException          DOCUMENT ME!
     * @throws  JsonProcessingException  DOCUMENT ME!
     */
    private ObjectNode createDeposition() throws RemoteException, JsonProcessingException {
        final MultivaluedMap queryParameters = this.createUserParameters();
        final WebResource webResource = this.createWebResource(DEPOSITIONS_API).queryParams(queryParameters);
        WebResource.Builder builder = this.createAuthorisationHeader(webResource);
        builder = this.createMediaTypeHeaders(builder);
        final ObjectNode objectNode = builder.post(ObjectNode.class, MAPPER.createObjectNode());
        return objectNode;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   depositionId  DOCUMENT ME!
     * @param   file          DOCUMENT ME!
     * @param   mediaType     DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  RemoteException  DOCUMENT ME!
     * @throws  IOException      DOCUMENT ME!
     */
    private JsonNode uploadDepositionFile(final long depositionId, final File file, final MediaType mediaType)
            throws RemoteException, IOException {
        final long current = System.currentTimeMillis();
        final MultivaluedMap queryParameters = this.createUserParameters();

        final WebResource webResource = this.createWebResource(DEPOSITIONS_API + "/" + depositionId + "/files")
                    .queryParams(queryParameters);
        final WebResource.Builder builder = this.createAuthorisationHeader(webResource);
        builder.type(MediaType.MULTIPART_FORM_DATA_TYPE).accept(MediaType.APPLICATION_JSON_TYPE);

        // upload file to zenodo. tricky and error prone.
        final FileDataBodyPart filePart = new FileDataBodyPart("file", file);
        // here we set the real file name!
        filePart.setContentDisposition(
            FormDataContentDisposition.name("file").fileName(file.getName()).build());

        filePart.setMediaType(mediaType);
        // This part is ignored by the zenodo API ??!!
        // http://developers.zenodo.org/?shell#deposition-files
        final FormDataMultiPart multiPartData = new FormDataMultiPart();
        multiPartData.field(
            "filename",
            file.getName(),
            MediaType.TEXT_PLAIN_TYPE);
        multiPartData.bodyPart(filePart);

        final JsonNode objectNode = builder.post(ObjectNode.class, multiPartData);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("file '" + file.getName() + "' (" + (objectNode.get("filesize").asLong() / 1024)
                        + "kb) uploaded in " + ((System.currentTimeMillis() - current) / 1000) + "s");
        }
        return objectNode;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   file  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  IOException  DOCUMENT ME!
     */
    private MediaType getMediaType(final File file) throws IOException {
        String mediaType = Files.probeContentType(file.toPath());
        if ((mediaType == null) || mediaType.isEmpty()) {
            mediaType = URLConnection.guessContentTypeFromName(file.getName());
            if ((mediaType == null) || mediaType.isEmpty()) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.warn("cannot detect media type of file '" + file.getName() + "'");
                }
                return MediaType.APPLICATION_OCTET_STREAM_TYPE;
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("media type of file '" + file.getName() + " detected: " + mediaType);
        }
        return MediaType.valueOf(mediaType);
    }

    /**
     * DOCUMENT ME!
     *
     * @param   resourceBean  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private ArrayList<File> downloadResources(final CidsBean resourceBean) {
        final ArrayList<File> files = new ArrayList<File>();
        final List<CidsBean> representationBeans = resourceBean.getBeanCollectionProperty("representation");
        final int i = 0;
        for (final CidsBean representationBean : representationBeans) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("processing representation #" + i + " ' "
                            + representationBean.getProperty("name") + "' of resource '"
                            + resourceBean.getProperty("name") + "'");
            }

            if (((CidsBean)representationBean.getProperty("type")).getProperty("name").toString().equalsIgnoreCase(
                            "original data")) {
                if (((CidsBean)representationBean.getProperty("function")).getProperty("name").toString()
                            .equalsIgnoreCase("download")) {
                    try {
                        final File file = this.downloadResource(representationBean);
                        if (file != null) {
                            files.add(file);
                            if (LOGGER.isDebugEnabled()) {
                                LOGGER.debug("successfully downloaded file from '"
                                            + representationBean.getProperty("contentlocation")
                                            + "' to " + file.getAbsolutePath());
                            }
                        }
                    } catch (IOException ex) {
                        LOGGER.error("could not download representation #" + i + "' "
                                    + representationBean.getProperty("name") + "' of resource '"
                                    + resourceBean.getProperty("name") + "' from URL '"
                                    + representationBean.getProperty("contentlocation") + "': " + ex.getMessage());
                    }
                } else if (LOGGER.isDebugEnabled()) {
                    LOGGER.warn("ignoring non-downloadable representation ("
                                + ((CidsBean)representationBean.getProperty("function")).getProperty("name")
                                + "): " + representationBean.getProperty("contenttype"));
                }
            } else if (LOGGER.isDebugEnabled()) {
                LOGGER.warn("ignoring non-downloadable representation ("
                            + ((CidsBean)representationBean.getProperty("function")).getProperty("name")
                            + "): " + representationBean.getProperty("contenttype"));
            }
        }

        return files;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   representationBean  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  MalformedURLException  DOCUMENT ME!
     * @throws  IOException            DOCUMENT ME!
     */
    private File downloadResource(final CidsBean representationBean) throws MalformedURLException, IOException {
        final URL fileUrl = new URL(representationBean.getProperty("contentlocation").toString());
        final String filename = fileUrl.getFile();
        if ((filename == null) || filename.isEmpty()) {
            LOGGER.warn("could not downlod non-file from url: " + representationBean.getProperty("contentlocation"));
            return null;
        }

        final File file = new File(this.tempDirectory, filename);
        FileUtils.copyURLToFile(fileUrl, file, TIMEOUT, TIMEOUT);

        return file;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   deposition    DOCUMENT ME!
     * @param   resourceBean  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    private ObjectNode copyMetadata(final ObjectNode deposition, final CidsBean resourceBean) throws Exception {
        final ObjectNode metadata = (ObjectNode)deposition.get("metadata");

        metadata.put("upload_type", "dataset");
        metadata.put("title", resourceBean.getProperty("name").toString());
        metadata.put("description", resourceBean.getProperty("description").toString());

        final ArrayNode creators = metadata.putArray("creators");
        final CidsBean contact = (CidsBean)resourceBean.getProperty("contact");

        if ((contact.getProperty("name") == null) || contact.getProperty("name").toString().isEmpty()) {
            if ((contact.getProperty("organisation") == null)
                        || contact.getProperty("oranisation").toString().isEmpty()) {
                throw new Exception("resource " + resourceBean.getPrimaryKeyValue() + " '"
                            + resourceBean.getProperty("name") + "' does not have valid contact information");
            } else {
                final ObjectNode creator = creators.addObject();
                creator.put("name", contact.getProperty("organisation").toString());
                creator.put("affiliation", contact.getProperty("organisation").toString());
            }
        } else {
            final String[] contactNames = contact.getProperty("name").toString().split(";");
            for (final String contactName : contactNames) {
                final ObjectNode creator = creators.addObject();
                creator.put("name", contactName);
                if ((contact.getProperty("organisation") != null)
                            && !contact.getProperty("oranisation").toString().isEmpty()) {
                    creator.put("affiliation", contact.getProperty("organisation").toString());
                }
            }
        }

        final CidsBean accessConditions = (CidsBean)resourceBean.getProperty("accessconditions");
        if (accessConditions.getProperty("name").toString().equalsIgnoreCase("research only")
                    || accessConditions.getProperty("name").toString().equalsIgnoreCase("other")) {
            metadata.put("access_right", "restricted");
            if ((resourceBean.getProperty("licensestatement") != null)
                        && !resourceBean.getProperty("licensestatement").toString().isEmpty()) {
                metadata.put("access_conditions", resourceBean.getProperty("licensestatement").toString());
            } else {
                metadata.put("access_conditions", resourceBean.getProperty("licensestatement").toString());
            }
        }

        return deposition;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    private int performUpload() throws Exception {
        int i = 0;

        for (final int resourceId : resourceIds) {
            try {
                final MetaObject metaObject = SessionManager.getProxy()
                            .getMetaObject(resourceId,
                                resourceClass.getId(), DOMAIN);
                final CidsBean resourceBean = metaObject.getBean();
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("resource " + metaObject.getID() + " - '"
                                + metaObject.getName() + "' with "
                                + resourceBean.getBeanCollectionProperty("representation").size()
                                + " representations loaded");
                }

                final ArrayList<File> downloadResources = this.downloadResources(resourceBean);
                if (downloadResources.isEmpty()) {
                    LOGGER.error("no valid representations for resource " + metaObject.getID() + " - '"
                                + metaObject.getName() + "', skipping resource!");
                    continue;
                }

                ObjectNode deposition = this.createDeposition();
                final long depositionId = deposition.get("id").asLong();
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.info("empty deposition " + depositionId + " created");
                }
                // if (LOGGER.isDebugEnabled()) {
                // LOGGER.debug(MAPPER.writeValueAsString(emptyDeposition));
                // }

                for (final File file : downloadResources) {
                    final JsonNode depositionFile = this.uploadDepositionFile(
                            depositionId,
                            file,
                            this.getMediaType(file));
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.info("deposition file '" + file.getName() + "' uploaded. checksum: "
                                    + depositionFile.get("checksum").asText());
                    }
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(MAPPER.writeValueAsString(depositionFile));
                    }
                }

                deposition = this.getDeposition(depositionId);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(MAPPER.writeValueAsString(deposition));
                }

                this.copyMetadata(deposition, resourceBean);

                i++;

                if (i == 3) {
                    System.exit(0);
                }
            } catch (Throwable ex) {
                LOGGER.error("error while processing  resource #" + i + " - "
                            + resourceId + ": " + ex.getMessage(),
                    ex);
            }
        }

        LOGGER.info(i + " resources processed");
        return i;
    }

    /**
     * Creates a {@link WebResource.Builder} from the given path and the given params. The given path will be appended
     * to the root path of this connector, thus shall denote a path relative to the root resource. The given {@link Map}
     * of queryParams will be appended to the query.
     *
     * @param   path  the path relative to the root resource
     *
     * @return  a <code>WebResource</code> ready to perform an operation (GET, POST, PUT...)
     */
    private WebResource createWebResource(final String path) {
        // remove leading '/' if present
        final String resource;
        if ((path == null) || path.isEmpty()) {
            resource = zenodoApi;
        } else if ('/' == path.charAt(0)) {
            resource = zenodoApi + path.substring(1, path.length() - 1);
        } else {
            resource = zenodoApi + path;
        }

        // create new client and webresource from the given resource
        if (clientCache.isEmpty()) {
            LOGGER.info("adding new client for path '" + path + "' and resource '" + resource + "' to cache");
            final DefaultApacheHttpClientConfig clientConfig = new DefaultApacheHttpClientConfig();
            if (proxy.isEnabled()) {
                if ((proxy.getHost() != null) && (proxy.getPort() > 0)) {
                    clientConfig.getProperties()
                            .put(
                                ApacheHttpClientConfig.PROPERTY_PROXY_URI,
                                "http://"
                                + proxy.getHost()
                                + ":"
                                + proxy.getPort());
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("proxy set: " + proxy);
                    }

                    if ((proxy.getUsername() != null) && (proxy.getPassword() != null)) {
                        clientConfig.getState()
                                .setProxyCredentials(
                                    null,
                                    proxy.getHost(),
                                    proxy.getPort(),
                                    proxy.getUsername(),
                                    proxy.getPassword(),
                                    proxy.getDomain(),
                                    "");
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug("proxy credentials set: " + proxy);
                        }
                    }
                }
            }

            clientConfig.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, TIMEOUT);
            clientConfig.getClasses().add(com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider.class);
            clientCache.put(path, ApacheHttpClient.create(clientConfig));
        }

        // hacketyhack
        final Client client = clientCache.values().iterator().next();
        final UriBuilder uriBuilder = UriBuilder.fromPath(resource);

        final WebResource webResource = client.resource(uriBuilder.build());
        return webResource;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   webResource  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  RemoteException  DOCUMENT ME!
     */
    protected WebResource.Builder createAuthorisationHeader(final WebResource webResource) throws RemoteException {
        final String basicAuthString = this.getBasicAuthString();
        final WebResource.Builder builder = webResource.header("Authorization", basicAuthString);
        return builder;
    }

    /**
     * Tries to lookup a basic auth string for a previously authenticated user FIXME: better work with session ids, etc.
     *
     * @return  DOCUMENT ME!
     *
     * @throws  RemoteException  DOCUMENT ME!
     */
    private String getBasicAuthString() throws RemoteException {
        return "Bearer " + this.zenodoApiKey;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   builder  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private WebResource.Builder createMediaTypeHeaders(final WebResource.Builder builder) {
        return builder.type(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE);
    }

    /**
     * DOCUMENT ME!
     *
     * @param   webResource  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private WebResource.Builder createMediaTypeHeaders(final WebResource webResource) {
        return webResource.type(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE);
    }

    /**
     * DOCUMENT ME!
     *
     * @param  args  DOCUMENT ME!
     */
    public static void main(final String[] args) {
        final InputStream propertiesFileStream;
        final InputStream resourcesFileStream;
        final ZenodoUploader zenodoUploader;
        try {
            if (args.length == 2) {
                propertiesFileStream = Files.newInputStream(FileSystems.getDefault().getPath(args[0]),
                        StandardOpenOption.READ);
                resourcesFileStream = Files.newInputStream(FileSystems.getDefault().getPath(args[0]),
                        StandardOpenOption.READ);
            } else {
                propertiesFileStream = ZenodoUploader.class.getResourceAsStream("zenodo.properties");
                resourcesFileStream = ZenodoUploader.class.getResourceAsStream("zenodoResources.txt");
            }

            zenodoUploader = new ZenodoUploader(propertiesFileStream, resourcesFileStream);

            zenodoUploader.performUpload();
        } catch (Throwable t) {
            ZenodoUploader.LOGGER.fatal(t.getMessage(), t);
            System.exit(1);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  IOException  DOCUMENT ME!
     */
    private static File createTempDirectory() throws IOException {
        final File temp;
        int i = 0;

        temp = File.createTempFile("SWITCH-ON_", Long.toString(System.nanoTime()));
        temp.delete();
        File directory = new File(temp.getAbsolutePath() + (++i));

        while (directory.exists()) {
            directory = new File(temp.getAbsolutePath() + (++i));
        }

        directory.mkdir();
        return directory;
    }
}
