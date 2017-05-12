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
package de.cismet.cids.custom.switchon.utils;

import Sirius.navigator.connection.SessionManager;

import Sirius.server.middleware.types.MetaClass;
import Sirius.server.middleware.types.MetaObject;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.navigator.utils.ClassCacheMultiple;

/**
 * DOCUMENT ME!
 *
 * @author   Pascal Dihé
 * @version  $Revision$, $Date$
 */
public class ZenodoUploader {

    //~ Static fields/initializers ---------------------------------------------

    private static final String DOMAIN = "SWITCHON";
    private static final String META_CLASS = "resource";

    private static final Logger LOGGER = Logger.getLogger(ZenodoUploader.class);

    //~ Instance fields --------------------------------------------------------

    private final List<CidsBean> resources = new ArrayList<CidsBean>();

    private final String zenodoiApiKey;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new ZenodoUploader object.
     *
     * @param   resourceIdsFile  DOCUMENT ME!
     * @param   username         DOCUMENT ME!
     * @param   password         DOCUMENT ME!
     * @param   zenodoiApiKey    DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    public ZenodoUploader(final File resourceIdsFile,
            final String username,
            final String password,
            final String zenodoiApiKey) throws Exception {
        this.zenodoiApiKey = zenodoiApiKey;

        DevelopmentTools.initSessionManagerFromRestfulConnectionOnLocalhost(
            "SWITCH-ON",
            null,
            username,
            password);

        final List<Integer> resourceIds = new ArrayList<Integer>();
        final BufferedReader reader = new BufferedReader(new FileReader(resourceIdsFile));
        String resourceIdString;
        while ((resourceIdString = reader.readLine()) != null) {
            resourceIds.add(Integer.parseInt(resourceIdString));
        }

        LOGGER.info(resourceIds.size() + " resource ID read from '" + resourceIdsFile.getName() + "'");

        final MetaClass resourceClass = ClassCacheMultiple.getMetaClass(DOMAIN, META_CLASS);
        for (final int resourceId : resourceIds) {
            final MetaObject metaObject = SessionManager.getProxy()
                        .getMetaObject(resourceId, resourceClass.getId(), DOMAIN);
            resources.add(metaObject.getBean());
        }
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  args  DOCUMENT ME!
     */
    public static void main(final String[] args) {
        final Properties log4jProperties = new Properties();
        log4jProperties.put("log4j.appender.Remote", "org.apache.log4j.net.SocketAppender");
        log4jProperties.put("log4j.appender.Remote.remoteHost", "localhost");
        log4jProperties.put("log4j.appender.Remote.port", "4445");
        log4jProperties.put("log4j.appender.Remote.locationInfo", "true");

        log4jProperties.put("log4j.appender.File", "org.apache.log4j.FileAppender");
        log4jProperties.put("log4j.appender.File.file", "shpImport.log");
        log4jProperties.put("log4j.appender.File.layout", "org.apache.log4j.PatternLayout");
        log4jProperties.put(
            "log4j.appender.File.layout.ConversionPattern",
            "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
        log4jProperties.put("log4j.appender.File.append", "false");

        log4jProperties.put(
            "log4j.logger.de.cismet.cids.custom.switchon.utils.server.SpatialIndexTools",
            "ALL,Remote,File");

        PropertyConfigurator.configure(log4jProperties);

        if (args.length == 0) {
            LOGGER.fatal("first required argument 'resource ids file' is missing, bailing out!");
            System.exit(1);
        } else if (args.length < 2) {
            LOGGER.fatal("2nd required argument 'username' is missing, bailing out!");
            System.exit(1);
        } else if (args.length < 3) {
            LOGGER.fatal("3rd required argument 'password' is missing, bailing out!");
            System.exit(1);
        } else if (args.length < 4) {
            LOGGER.fatal("4th required argument 'zenodoiApiKey' is missing, bailing out!");
            System.exit(1);
        }

        final File resourceIdsFile = new File(args[0]);
        final String username = args[1];
        final String password = args[2];
        final String zenodoiApiKey = args[3];

        ZenodoUploader.LOGGER.info("Starting ZenodoUploader with \n "
                    + "zip directory: '" + resourceIdsFile.getAbsolutePath() + "'.");

        try {
            final ZenodoUploader zenodoUploader = new ZenodoUploader(
                    resourceIdsFile,
                    username,
                    password,
                    zenodoiApiKey);
        } catch (Throwable t) {
            ZenodoUploader.LOGGER.fatal(t.getMessage(), t);
        }
    }
}
