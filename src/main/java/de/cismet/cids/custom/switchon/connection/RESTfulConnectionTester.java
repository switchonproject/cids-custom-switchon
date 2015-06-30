/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.connection;

import Sirius.navigator.connection.ConnectionInfo;
import Sirius.navigator.connection.SessionManager;
import Sirius.navigator.exception.ConnectionException;
import Sirius.navigator.resource.PropertyManager;

import Sirius.server.middleware.types.MetaClass;
import Sirius.server.middleware.types.MetaObject;
import Sirius.server.newuser.User;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.openide.util.Exceptions;

import java.lang.invoke.MethodHandles;

import java.net.URI;

import java.rmi.RemoteException;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.UriBuilder;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.server.CallServerService;
import de.cismet.cids.server.ws.rest.RESTfulInterfaceConnector;

/**
 * DOCUMENT ME!
 *
 * @author   Pascal DihÃ©
 * @version  $Revision$, $Date$
 */
public final class RESTfulConnectionTester {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass());
    private static final String baseDir = "d:/work/cids-custom-switchon/src/switchonDist/client/switchon/";

    //~ Instance fields --------------------------------------------------------

    private final CallServerService pureRestLegacyCoreConnection;
    private final CallServerService pureRestDatabaseCoreConnection;
    private final CallServerService legacyRestConnection;
    private final User user;
    private final String moQuery = "SELECT c.id as classId, r.ID as objectId "
                + "FROM resource r, cs_class c "
                + "WHERE c.name = 'resource' ORDER by r.name";
    private final String domain = "SWITCHON";

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new RESTfulConnectionTester object.
     *
     * @throws  Exception  DOCUMENT ME!
     */
    private RESTfulConnectionTester() throws Exception {
        PropertyManager.getManager()
                .configure(
                    baseDir
                    + "config/navigator.cfg",
                    baseDir
                    + "switchon",
                    baseDir
                    + "plugins",
                    null,
                    null);

        final ConnectionInfo connectionInfo = PropertyManager.getManager().getConnectionInfo();

        pureRestLegacyCoreConnection = this.initConnection("http://localhost/", 8890);
        user = pureRestLegacyCoreConnection.getUser(
                connectionInfo.getUsergroupDomain(),
                connectionInfo.getUsergroup(),
                connectionInfo.getUserDomain(),
                connectionInfo.getUsername(),
                connectionInfo.getPassword());

        pureRestDatabaseCoreConnection = this.initConnection("http://localhost/", 8891);
        // neded to authenticate user in connector
        pureRestDatabaseCoreConnection.getUser(
            connectionInfo.getUsergroupDomain(),
            connectionInfo.getUsergroup(),
            connectionInfo.getUserDomain(),
            connectionInfo.getUsername(),
            connectionInfo.getPassword());

        // this call is needed to fill the Meta Class Cache (needed by CidsBean)
        DevelopmentTools.initSessionManagerFromRestfulConnectionOnLocalhost(
            "SWITCHON",
            "Administratoren",
            "admin",
            "cismet");
        PropertyConfigurator.configure(baseDir + "config/log4j.debug.properties");

        legacyRestConnection = SessionManager.getProxy().getCallServerService();
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * Fill the database Core: Copy resources from cids REST server running with legacy rest entity core to cids REST
     * server running with database entity core.
     *
     * @throws  RemoteException  DOCUMENT ME!
     */
    private void initDbCore() throws RemoteException {
        final MetaClass resourceClass = pureRestLegacyCoreConnection.getClassByTableName(
                this.user,
                "resource",
                "SWITCHON");
        final int classId = resourceClass.getID();
        final MetaObject[] metaObjects = legacyRestConnection.getAllLightweightMetaObjectsForClass(
                classId,
                user,
                new String[] {});
        LOG.info(metaObjects.length + " MetaObject found by legacy core.");

        final MetaObject[] existingMetaObjects = pureRestDatabaseCoreConnection.getAllLightweightMetaObjectsForClass(
                classId,
                user,
                new String[] {});

        LOG.info(existingMetaObjects.length + "MetaObject found by database core.");
        final HashSet<Integer> metaObjectIds = new HashSet<Integer>();
        for (final MetaObject lwMetaObject : existingMetaObjects) {
            metaObjectIds.add(lwMetaObject.getId());
        }

        final long startTime = System.currentTimeMillis();
        for (final MetaObject lwMetaObject : metaObjects) {
            final int metaObjectId = lwMetaObject.getID();
            if (!metaObjectIds.contains(metaObjectId)) {
                final MetaObject metaObject = legacyRestConnection.getMetaObject(
                        user,
                        metaObjectId,
                        classId,
                        domain);
                pureRestDatabaseCoreConnection.insertMetaObject(user, metaObject, metaObject.getDomain());
                System.out.print('.');
            }
        }
        final long endTime = System.currentTimeMillis() - startTime;
        System.out.println("\nInitialization of Dummy Database Core with "
                    + (metaObjects.length - existingMetaObjects.length) + " Meta Objects: "
                    + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime))
                    + String.format(" (%d ms)", endTime) + String.format(" (%d ms)", endTime));
    }

    /**
     * DOCUMENT ME!
     *
     * @param   restServerURL  DOCUMENT ME!
     * @param   port           DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  ConnectionException  DOCUMENT ME!
     */
    private RESTfulInterfaceConnector initConnection(final String restServerURL, final int port)
            throws ConnectionException {
        try {
            final UriBuilder uriBuilder = UriBuilder.fromUri(restServerURL);
            final URI callServerURI = uriBuilder.port(port).build();
            LOG.info("creating REST connection to service '" + callServerURI + "'");
            return new RESTfulInterfaceConnector(callServerURI.toString(), null, null);
        } catch (Exception ex) {
            final String message = "could not build restServerURL '" + restServerURL + "': "
                        + ex.getMessage();
            LOG.error(message, ex);
            throw new ConnectionException(message, ex);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    private void performanceTest() throws Exception {
        final MetaClass resourceClass = pureRestLegacyCoreConnection.getClassByTableName(
                this.user,
                "resource",
                "SWITCHON");
        final int classId = resourceClass.getID();
        long startTime;
        long endTime;
        final int[] metaObjectIds;
        final MetaObject[] metaObjects;
        final int start = 25;
        final int max = 800;
        final HttpClient httpclient = new HttpClient();

        metaObjects = legacyRestConnection.getAllLightweightMetaObjectsForClass(classId, user, new String[] {});
        metaObjectIds = new int[metaObjects.length];
        for (int i = 0; i < metaObjects.length; i++) {
            metaObjectIds[i] = metaObjects[i].getId();
        }

//        System.out.println("----------------------------------------------------");
//        System.out.println("get Lightweight Meta Objects Performance Tests");

//        startTime = System.currentTimeMillis();
//        metaObjects = legacyRestConnection.getAllLightweightMetaObjectsForClass(classId, user, new String[] {});
//        endTime = System.currentTimeMillis() - startTime;
//        System.out.println("LEGACY REST CONNECTION get " + metaObjects.length + " Lightweight Meta Objects: "
//                    + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime)) + String.format(" (%d ms)", endTime));
//
//        startTime = System.currentTimeMillis();
//        metaObjects = pureRestDatabaseCoreConnection.getAllLightweightMetaObjectsForClass(
//                classId,
//                user,
//                new String[] {});
//        endTime = System.currentTimeMillis() - startTime;
//        System.out.println("PURE REST CONNECTION (DUMMY DATABASE CORE) get " + metaObjects.length
//                    + " Lightweight Meta Objects: "
//                    + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime)) + String.format(" (%d ms)", endTime));
//
//        startTime = System.currentTimeMillis();
//        metaObjects = pureRestLegacyCoreConnection.getAllLightweightMetaObjectsForClass(
//                classId,
//                user,
//                new String[] {});
//        endTime = System.currentTimeMillis() - startTime;
//        System.out.println("PURE REST CONNECTION (LEGACY CORE) get " + metaObjects.length
//                    + " Lightweight Meta Objects: "
//                    + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime)) + String.format(" (%d ms)", endTime));
//
//        for (int i = 25; i <= max; i *= 2) {
//            System.out.println("----------------------------------------------------");
//            System.out.println("get Meta Objects by Query Performance Tests: "
//                        + i + " Meta Objects");
//
//            final String query = moQuery + " LIMIT " + i;
//
//            startTime = System.currentTimeMillis();
//            metaObjects = legacyRestConnection.getMetaObject(user, query, "SWITCHON");
//            endTime = System.currentTimeMillis() - startTime;
//            System.out.println("LEGACY REST CONNECTION get " + metaObjects.length + " Meta Objects by Query: "
//                        + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime)) + String.format(" (%d ms)", endTime));
//
//            startTime = System.currentTimeMillis();
//            metaObjects = pureRestDatabaseCoreConnection.getMetaObject(user, query, "SWITCHON");
//            endTime = System.currentTimeMillis() - startTime;
//            System.out.println("PURE REST CONNECTION (DUMMY DATABASE CORE) get " + metaObjects.length
//                        + " Meta Objects by Query: "
//                        + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime)) + String.format(" (%d ms)", endTime));
//
//            startTime = System.currentTimeMillis();
//            metaObjects = pureRestLegacyCoreConnection.getMetaObject(user, query, "SWITCHON");
//            endTime = System.currentTimeMillis() - startTime;
//            System.out.println("PURE REST CONNECTION (LEGACY CORE) get " + metaObjects.length
//                        + " Meta Objects by Query: "
//                        + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime)) + String.format(" (%d ms)", endTime));
//        }

        for (int i = start; i <= max; i *= 2) {
            System.out.println("----------------------------------------------------");
            System.out.println("get " + i + " Meta Objects Performance Tests");

            startTime = System.currentTimeMillis();
            for (int j = 0; j < i; j++) {
                System.out.print('.');
                legacyRestConnection.getMetaObject(user, metaObjectIds[j], classId, domain);
            }
            endTime = System.currentTimeMillis() - startTime;
            System.out.println("\nLEGACY REST CONNECTION get " + i + " Meta Objects (single calls): "
                        + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime))
                        + String.format(" (%d ms)", endTime));

            startTime = System.currentTimeMillis();
            for (int j = 0; j < i; j++) {
                System.out.print('.');
                final HttpMethod method = new GetMethod("http://localhost:8891/SWITCHON.resource/" + metaObjectIds[j]);
                method.setQueryString("role=all&limit=" + i);
                method.setRequestHeader("Authorization", "Basic YWRtaW5AU1dJVENIT046Y2lzbWV0");
                httpclient.executeMethod(method);
                method.getResponseBody();
                method.releaseConnection();
            }
            endTime = System.currentTimeMillis() - startTime;
            System.out.println("\nPLAIN HTTP CONNECTION (DUMMY DATABASE CORE) get " + i
                        + " Meta Objects (single calls): "
                        + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime))
                        + String.format(" (%d ms)", endTime));

            startTime = System.currentTimeMillis();
            for (int j = 0; j < i; j++) {
                pureRestDatabaseCoreConnection.getMetaObject(user, metaObjectIds[j], classId, domain);
                System.out.print('.');
            }
            endTime = System.currentTimeMillis() - startTime;
            System.out.println("\nPURE REST CONNECTION (DUMMY DATABASE CORE) get " + i
                        + " Meta Objects (single calls): "
                        + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime))
                        + String.format(" (%d ms)", endTime));

            startTime = System.currentTimeMillis();
            for (int j = 0; j < i; j++) {
                System.out.print('.');
                final HttpMethod method = new GetMethod("http://localhost:8890/SWITCHON.resource/" + metaObjectIds[j]);
                method.setQueryString("role=all&limit=" + i);
                method.setRequestHeader("Authorization", "Basic YWRtaW5AU1dJVENIT046Y2lzbWV0");
                httpclient.executeMethod(method);
                method.getResponseBody();
                method.releaseConnection();
            }
            endTime = System.currentTimeMillis() - startTime;
            System.out.println("\nPLAIN HTTP CONNECTION (LEGACY CORE) get " + i + " Meta Objects (single calls): "
                        + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime))
                        + String.format(" (%d ms)", endTime));

            startTime = System.currentTimeMillis();
            for (int j = 0; j < i; j++) {
                System.out.print('.');
                pureRestLegacyCoreConnection.getMetaObject(user, metaObjectIds[j], classId, domain);
            }
            endTime = System.currentTimeMillis() - startTime;
            System.out.println("\nPURE REST CONNECTION (LEGACY CORE) get " + i
                        + " Meta Objects (single calls): "
                        + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime))
                        + String.format(" (%d ms)", endTime));
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param  args  DOCUMENT ME!
     */
    public static void main(final String[] args) {
        PropertyConfigurator.configure(baseDir + "config/log4j.debug.properties");

        try {
            // Log4JQuickConfig.configure4LumbermillOnLocalhost();
            final RESTfulConnectionTester connectionTester = new RESTfulConnectionTester();

            // has to called only once!
            // connectionTester.initDbCore();

            connectionTester.performanceTest();

            System.exit(0);
        } catch (Exception ex) {
            LOG.fatal(ex.getMessage(), ex);
            Exceptions.printStackTrace(ex);
            System.exit(1);
        }
    }
}
