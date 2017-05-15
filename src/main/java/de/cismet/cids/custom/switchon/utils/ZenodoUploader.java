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
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.client.apache.ApacheHttpClient;
import com.sun.jersey.client.apache.config.ApacheHttpClientConfig;
import com.sun.jersey.client.apache.config.DefaultApacheHttpClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

import static de.cismet.cidsx.client.connector.RESTfulInterfaceConnector.ENTITIES_API;

/**
 * DOCUMENT ME!
 *
 * @author   Pascal Dihé
 * @version  $Revision$, $Date$
 */
public class ZenodoUploader {

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
    public ZenodoUploader(final InputStream propertyFileStream, final InputStream resourcesFileStream)
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
    protected MultivaluedMap createUserParameters() {
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
     * @return  DOCUMENT ME!
     *
     * @throws  RemoteException          DOCUMENT ME!
     * @throws  JsonProcessingException  DOCUMENT ME!
     */
    private JsonNode createDeposition() throws RemoteException, JsonProcessingException {
        final MultivaluedMap queryParameters = this.createUserParameters();
        final WebResource webResource = this.createWebResource(DEPOSITIONS_API).queryParams(queryParameters);
        WebResource.Builder builder = this.createAuthorisationHeader(webResource);
        builder = this.createMediaTypeHeaders(builder);
        final JsonNode objectNode = builder.post(ObjectNode.class, MAPPER.createObjectNode());
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("empty deposition " + objectNode.get("id").asLong() + " created");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(MAPPER.writeValueAsString(objectNode));
        }

        return objectNode;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private int performUpload() {
        int i = 0;

        for (final int resourceId : resourceIds) {
            try {
                final MetaObject metaObject = SessionManager.getProxy()
                            .getMetaObject(resourceId, resourceClass.getId(), DOMAIN);
                final CidsBean cidsBean = metaObject.getBean();
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("CidsBean " + metaObject.getID() + " - '" + metaObject.getName() + "' loaded");
                }

                i++;
            } catch (Throwable ex) {
                LOGGER.error("error while rpcessing resource #" + i + " - " + resourceId, ex);
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
    protected WebResource createWebResource(final String path) {
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
        if (!clientCache.containsKey(path)) {
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

        final Client client = clientCache.get(path);
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
    protected String getBasicAuthString() throws RemoteException {
        return "Bearer " + this.zenodoApiKey;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   builder  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    protected WebResource.Builder createMediaTypeHeaders(final WebResource.Builder builder) {
        return builder.type(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE);
    }

    /**
     * DOCUMENT ME!
     *
     * @param   webResource  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    protected WebResource.Builder createMediaTypeHeaders(final WebResource webResource) {
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

            zenodoUploader.createDeposition();
        } catch (Throwable t) {
            ZenodoUploader.LOGGER.fatal(t.getMessage(), t);
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
