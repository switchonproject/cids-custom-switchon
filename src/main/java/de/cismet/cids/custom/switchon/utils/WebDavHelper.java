/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.cismet.cids.custom.switchon.utils;

import Sirius.navigator.connection.SessionManager;
//import de.cismet.cids.custom.switchon.search.server.actions.WebDavTunnelAction;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.awt.Component;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import javax.swing.ProgressMonitorInputStream;

import de.cismet.cids.server.actions.ServerActionParameter;

import de.cismet.netutil.Proxy;

/**
 * DOCUMENT ME!
 *
 * @author   daniel
 * @version  $Revision$, $Date$
 */
public class WebDavHelper {

    //~ Static fields/initializers ---------------------------------------------

    private static Logger LOG = Logger.getLogger(WebDavHelper.class);
    public static final String WEBDAV_OVER_TUNNEL = "webDavTunnelAction";

    //~ Instance fields --------------------------------------------------------

    final Proxy proxy;
    final String username;
    final String password;
    final boolean useNtAuth;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new WebDavHelper object.
     *
     * @param  proxy      DOCUMENT ME!
     * @param  username   DOCUMENT ME!
     * @param  password   DOCUMENT ME!
     * @param  useNtAuth  DOCUMENT ME!
     */
    public WebDavHelper(final Proxy proxy, final String username, final String password, final boolean useNtAuth) {
        this.proxy = proxy;
        this.username = username;
        this.password = password;
        this.useNtAuth = useNtAuth;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   prefix        DOCUMENT ME!
     * @param   originalFile  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static String generateWebDAVFileName(final String prefix, final File originalFile) {
        final String[] fileNameSplit = originalFile.getName().split("\\.");
        String webFileName = prefix + System.currentTimeMillis() + "-" + Math.abs(originalFile.getName().hashCode());
        if (fileNameSplit.length > 1) {
            final String ext = fileNameSplit[fileNameSplit.length - 1];
            webFileName += "." + ext;
        }
        return webFileName;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   fileName         DOCUMENT ME!
     * @param   toUpload         DOCUMENT ME!
     * @param   webDavDirectory  DOCUMENT ME!
     * @param   parent           DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    public void uploadFileToWebDAV(final String fileName,
            final File toUpload,
            final String webDavDirectory,
            final Component parent) throws Exception {
//        final BufferedInputStream bfis = new BufferedInputStream(new ProgressMonitorInputStream(
//                    parent,
//                    "Bild wird übertragen...",
//                    new FileInputStream(toUpload)));
//        final byte[] bytes = IOUtils.toByteArray(bfis);
//        try {
//            final ServerActionParameter proxySAP = new ServerActionParameter<Proxy>(
//                    WebDavTunnelAction.PARAMETER_TYPE.PROXY.toString(),
//                    proxy);
//            final ServerActionParameter usernameSAP = new ServerActionParameter<String>(
//                    WebDavTunnelAction.PARAMETER_TYPE.USERNAME.toString(),
//                    username);
//            final ServerActionParameter passwordSAP = new ServerActionParameter<String>(
//                    WebDavTunnelAction.PARAMETER_TYPE.PASSWORD.toString(),
//                    password);
//            final ServerActionParameter ntAuthSAP = new ServerActionParameter<Boolean>(
//                    WebDavTunnelAction.PARAMETER_TYPE.NTAUTH.toString(),
//                    useNtAuth);
//
//            final ServerActionParameter putSAP = new ServerActionParameter<String>(WebDavTunnelAction.PARAMETER_TYPE.PUT
//                            .toString(),
//                    webDavDirectory
//                            + encodeURL(fileName));
//            SessionManager.getProxy()
//                    .executeTask(
//                        WEBDAV_OVER_TUNNEL,
//                        "WUNDA_BLAU",
//                        bytes,
//                        putSAP,
//                        proxySAP,
//                        usernameSAP,
//                        passwordSAP,
//                        ntAuthSAP);
//        } finally {
//            IOUtils.closeQuietly(bfis);
//        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param   fileName         DOCUMENT ME!
     * @param   webDavDirectory  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public boolean deleteFileFromWebDAV(final String fileName,
            final String webDavDirectory) {
//        if ((fileName != null) && (fileName.length() > 0)) {
//            try {
//                final ServerActionParameter proxySAP = new ServerActionParameter<Proxy>(
//                        WebDavTunnelAction.PARAMETER_TYPE.PROXY.toString(),
//                        proxy);
//                final ServerActionParameter usernameSAP = new ServerActionParameter<String>(
//                        WebDavTunnelAction.PARAMETER_TYPE.USERNAME.toString(),
//                        username);
//                final ServerActionParameter passwordSAP = new ServerActionParameter<String>(
//                        WebDavTunnelAction.PARAMETER_TYPE.PASSWORD.toString(),
//                        password);
//                final ServerActionParameter ntAuthSAP = new ServerActionParameter<Boolean>(
//                        WebDavTunnelAction.PARAMETER_TYPE.NTAUTH.toString(),
//                        useNtAuth);
//
//                final ServerActionParameter deleteSAP = new ServerActionParameter<String>(
//                        WebDavTunnelAction.PARAMETER_TYPE.DELETE.toString(),
//                        webDavDirectory
//                                + encodeURL(fileName));
//                SessionManager.getProxy()
//                        .executeTask(
//                            WEBDAV_OVER_TUNNEL,
//                            "WUNDA_BLAU",
//                            null,
//                            deleteSAP,
//                            proxySAP,
//                            usernameSAP,
//                            passwordSAP,
//                            ntAuthSAP);
//                return true;
//            } catch (Exception ex) {
//                LOG.error(ex, ex);
//            }
//        }
        return false;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   fileName         DOCUMENT ME!
     * @param   webDavDirectory  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    public InputStream getFileFromWebDAV(final String fileName,
            final String webDavDirectory) throws Exception {
//        final ServerActionParameter proxySAP = new ServerActionParameter<Proxy>(WebDavTunnelAction.PARAMETER_TYPE.PROXY
//                        .toString(),
//                proxy);
//        final ServerActionParameter usernameSAP = new ServerActionParameter<String>(
//                WebDavTunnelAction.PARAMETER_TYPE.USERNAME.toString(),
//                username);
//        final ServerActionParameter passwordSAP = new ServerActionParameter<String>(
//                WebDavTunnelAction.PARAMETER_TYPE.PASSWORD.toString(),
//                password);
//        final ServerActionParameter ntAuthSAP = new ServerActionParameter<Boolean>(
//                WebDavTunnelAction.PARAMETER_TYPE.NTAUTH.toString(),
//                useNtAuth);
//
//        final String encodedFileName = WebDavHelper.encodeURL(fileName);
//        final ServerActionParameter getSAP = new ServerActionParameter<String>(WebDavTunnelAction.PARAMETER_TYPE.GET
//                        .toString(),
//                webDavDirectory
//                        + encodedFileName);
//        final byte[] result = (byte[])SessionManager.getProxy()
//                    .executeTask(
//                            WebDavHelper.WEBDAV_OVER_TUNNEL,
//                            "WUNDA_BLAU",
//                            null,
//                            getSAP,
//                            proxySAP,
//                            usernameSAP,
//                            passwordSAP,
//                            ntAuthSAP);
//        return new ByteArrayInputStream(result);
        return null;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   fileName  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static String getFilenameFromUrl(final String fileName) {
        final String[] splittedFileName = fileName.split("/");
        return splittedFileName[splittedFileName.length - 1];
    }

    /**
     * DOCUMENT ME!
     *
     * @param   url  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static String encodeURL(final String url) {
        try {
            if (url == null) {
                return null;
            }
            final String[] tokens = url.split("/", -1);
            StringBuilder encodedURL = null;

            for (final String tmp : tokens) {
                if (encodedURL == null) {
                    encodedURL = new StringBuilder(URLEncoder.encode(tmp, "UTF-8"));
                } else {
                    encodedURL.append("/").append(URLEncoder.encode(tmp, "UTF-8"));
                }
            }

            if (encodedURL != null) {
                // replace all + with %20 because the method URLEncoder.encode() replaces all spaces with '+', but
                // the web dav client interprets %20 as a space.
                return encodedURL.toString().replaceAll("\\+", "%20");
            } else {
                return "";
            }
        } catch (final UnsupportedEncodingException e) {
            LOG.error("Unsupported encoding.", e);
        }
        return url;
    }
}
