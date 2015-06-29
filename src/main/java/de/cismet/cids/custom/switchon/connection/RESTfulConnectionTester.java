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

import org.apache.log4j.Logger;

import org.openide.util.Exceptions;

import java.lang.invoke.MethodHandles;

import java.net.URI;

import java.rmi.RemoteException;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.UriBuilder;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.server.CallServerService;
import de.cismet.cids.server.ws.rest.RESTfulInterfaceConnector;

import de.cismet.tools.gui.log4jquickconfig.Log4JQuickConfig;

/**
 * DOCUMENT ME!
 *
 * @author   Pascal Dihé
 * @version  $Revision$, $Date$
 */
public final class RESTfulConnectionTester {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass());

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
                    "d:/work/cids-custom-switchon/src/switchonDist/client/switchon/config/navigator.cfg",
                    "d:/work/cids-custom-switchon/src/switchonDist/client/switchon",
                    "d:/work/cids-custom-switchon/src/switchonDist/client/switchon/plugins",
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
        final MetaObject[] metaObjects = pureRestLegacyCoreConnection.getMetaObject(this.user, moQuery);
        LOG.info(metaObjects.length + " resources found.");
        System.out.println("Initilaizing Dummy Database Core with " + metaObjects.length + " Meta Objects");
        final long startTime = System.currentTimeMillis();
        for (final MetaObject metaObject : metaObjects) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("saving meta object '" + metaObject.getName() + "'");
            }
            pureRestDatabaseCoreConnection.insertMetaObject(user, metaObject, metaObject.getDomain());
            System.out.print('.');
        }
        final long endTime = System.currentTimeMillis() - startTime;
        System.out.println("\nInitialization of Dummy Database Core with " + metaObjects.length + " Meta Objects: "
                    + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime)));
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
            final String message = "could n ot build restServerURL '" + restServerURL + "': "
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
        MetaObject[] metaObjects;

        System.out.println("----------------------------------------------------");
        System.out.println("get Lightweight Meta Objects Performance Tests");

        startTime = System.currentTimeMillis();
        metaObjects = legacyRestConnection.getAllLightweightMetaObjectsForClass(classId, user, new String[] {});
        endTime = System.currentTimeMillis() - startTime;
        System.out.println("LEGACY REST CONNECTION get " + metaObjects.length + " Lightweight Meta Objects: "
                    + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime)));

        startTime = System.currentTimeMillis();
        metaObjects = pureRestDatabaseCoreConnection.getAllLightweightMetaObjectsForClass(
                classId,
                user,
                new String[] {});
        endTime = System.currentTimeMillis() - startTime;
        System.out.println("PURE REST CONNECTION (DUMMY DATABASE CORE) get " + metaObjects.length
                    + " Lightweight Meta Objects: "
                    + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime)));

        startTime = System.currentTimeMillis();
        metaObjects = pureRestLegacyCoreConnection.getAllLightweightMetaObjectsForClass(classId, user, new String[] {
                });
        endTime = System.currentTimeMillis() - startTime;
        System.out.println("PURE REST CONNECTION (LEGACY CORE) get " + metaObjects.length
                    + " Lightweight Meta Objects: "
                    + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime)));

        System.out.println("----------------------------------------------------");
        System.out.println("get Meta Objects by Query Performance Tests");

        startTime = System.currentTimeMillis();
        metaObjects = legacyRestConnection.getMetaObject(user, moQuery, "SWITCHON");
        endTime = System.currentTimeMillis() - startTime;
        System.out.println("LEGACY REST CONNECTION get " + metaObjects.length + " Meta Objects by Query: "
                    + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime)));

        startTime = System.currentTimeMillis();
        metaObjects = pureRestDatabaseCoreConnection.getMetaObject(user, moQuery, "SWITCHON");
        endTime = System.currentTimeMillis() - startTime;
        System.out.println("PURE REST CONNECTION (DUMMY DATABASE CORE) get " + metaObjects.length
                    + " Meta Objects by Query: "
                    + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime)));

        startTime = System.currentTimeMillis();
        metaObjects = pureRestLegacyCoreConnection.getMetaObject(user, moQuery, "SWITCHON");
        endTime = System.currentTimeMillis() - startTime;
        System.out.println("PURE REST CONNECTION (LEGACY CORE) get " + metaObjects.length + " Meta Objects by Query: "
                    + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime)));

        System.out.println("----------------------------------------------------");
        System.out.println("get Meta Objects by Id Performance Tests");

        startTime = System.currentTimeMillis();
        for (final MetaObject metaObject : metaObjects) {
            legacyRestConnection.getMetaObject(user, metaObject.getID(), classId, domain);
        }
        endTime = System.currentTimeMillis() - startTime;
        System.out.println("LEGACY REST CONNECTION get " + metaObjects.length + " Meta Objects (single calls): "
                    + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime)));

        startTime = System.currentTimeMillis();
        for (final MetaObject metaObject : metaObjects) {
            pureRestDatabaseCoreConnection.getMetaObject(user, metaObject.getID(), classId, domain);
        }
        endTime = System.currentTimeMillis() - startTime;
        System.out.println("PURE REST CONNECTION (DUMMY DATABASE CORE) get " + metaObjects.length
                    + " Meta Objects (single calls): "
                    + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime)));

        startTime = System.currentTimeMillis();
        for (final MetaObject metaObject : metaObjects) {
            pureRestLegacyCoreConnection.getMetaObject(user, metaObject.getID(), classId, domain);
        }
        endTime = System.currentTimeMillis() - startTime;
        System.out.println("PURE REST CONNECTION (LEGACY CORE) get " + metaObjects.length
                    + " Meta Objects (single calls): "
                    + String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(endTime)));
    }

    /**
     * DOCUMENT ME!
     *
     * @param  args  DOCUMENT ME!
     */
    public static void main(final String[] args) {
        try {
            Log4JQuickConfig.configure4LumbermillOnLocalhost();
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
