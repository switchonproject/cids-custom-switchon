/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import Sirius.navigator.connection.SessionManager;
import Sirius.navigator.exception.ConnectionException;
import Sirius.navigator.plugin.PluginRegistry;
import Sirius.navigator.types.treenode.DefaultMetaTreeNode;
import Sirius.navigator.types.treenode.RootTreeNode;
import Sirius.navigator.ui.ComponentRegistry;
import Sirius.navigator.ui.tree.MetaCatalogueTree;

import Sirius.server.middleware.types.MetaClass;
import Sirius.server.middleware.types.MetaObject;
import Sirius.server.middleware.types.MetaObjectNode;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import org.apache.log4j.Logger;

import org.openide.util.ImageUtilities;

import java.util.Properties;
import java.util.concurrent.Future;

import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.navigator.utils.ClassCacheMultiple;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public final class Utils {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient Logger LOG = Logger.getLogger(Utils.class);
    public static final String TABLENAME_RAINEVENT = "RAINEVENT"; // NOI18N
    public static final String TABLENAME_IDFCURVE = "IDF_CURVE";  // NOI18N
    public static final String CISMAP_PLUGIN_NAME = "cismap";     // NOI18N
    public static final String EPSG_WGS84 = "EPSG:4326";          // NOI18N

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new Utils object.
     */
    private Utils() {
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Future reloadCatalogTree() {
        final MetaCatalogueTree tree = ComponentRegistry.getRegistry().getCatalogueTree();
        final TreePath path = tree.getSelectionPath();
        final DefaultTreeModel model = (DefaultTreeModel)tree.getModel();

        try {
            final RootTreeNode root = new RootTreeNode(SessionManager.getProxy().getRoots());
            model.setRoot(root);
            model.reload();

            return tree.exploreSubtree(path);
        } catch (final Exception ex) {
            LOG.warn("could not reload tree", ex); // NOI18N
        }

        return null;
    }

    /**
     * Searches for {@link CidsBean}s upwards the {@link TreePath} of the currently selected node of the catalogue tree.
     * If a type {@link MetaClass} is given the search will continue until a {@link MetaObject} is found whose <code>
     * MetaClass</code> is equal to the given <code>MetaClass</code>. If the given type is <code>null</code> the first
     * parent <code>MetaObject</code> will be returned regardless of its type.
     *
     * @param   type  whether a parent object of that particular type shall be searched for
     *
     * @return  the <code>CidsBean</code> of the found <code>MetaObject</code> or <code>null</code> if the search was
     *          not successful for any reason
     *
     * @throws  IllegalStateException  DOCUMENT ME!
     */
    public static CidsBean getParentObject(final MetaClass type) {
        final MetaCatalogueTree tree = ComponentRegistry.getRegistry().getCatalogueTree();
        final TreePath selectionPath = tree.getSelectionPath();

        if (selectionPath == null) {
            LOG.warn("currently no node selected in catalog tree, no parent can be determined");

            return null;
        }

        final TreePath path = selectionPath.getParentPath();

        if (path == null) {
            LOG.warn("no parent path available for currently selected node"); // NOI18N

            return null;
        }

        CidsBean bean = null;
        for (int i = path.getPathCount() - 1; i > -1; --i) {
            final Object element = path.getPathComponent(i);
            if (element instanceof DefaultMetaTreeNode) {
                final DefaultMetaTreeNode node = (DefaultMetaTreeNode)element;
                if (node.isObjectNode()) {
                    final Object userobject = node.getUserObject();

                    assert userobject != null : "null user object in object node";                                             // NOI18N
                    assert userobject instanceof MetaObjectNode : "user object not instance of MetaObjectNode in object node"; // NOI18N

                    final MetaObjectNode mon = (MetaObjectNode)userobject;

                    final MetaObject mo;
                    if (mon.getObject() == null) {
                        try {
                            mo = SessionManager.getProxy()
                                        .getMetaObject(mon.getObjectId(),
                                                mon.getClassId(),
                                                SessionManager.getSession().getUser().getDomain());
                        } catch (final ConnectionException ex) {
                            final String message =
                                "MetoObject not present in MetaObjectNode and cannot fetch meta object from server"; // NOI18N
                            LOG.error(message, ex);
                            throw new IllegalStateException(message, ex);
                        }
                    } else {
                        mo = mon.getObject();
                    }

                    if (type == null) {
                        bean = mo.getBean();

                        break;
                    } else {
                        final MetaClass moType = mo.getMetaClass();

                        assert moType != null : "metaclass of found object is null"; // NOI18N

                        if (moType.equals(type)) {
                            bean = mo.getBean();

                            break;
                        }
                    }
                }
            } else {
                LOG.warn("path element not instance of DefaultMetaTreeNode, cannot retrieve parent object"); // NOI18N

                return null;
            }
        }

        return bean;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   properties  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static String toTSTBCompatiblePropListing(final Properties properties) {
        final StringBuilder sb = new StringBuilder();

        for (final String key : properties.stringPropertyNames()) {
            final String value = properties.getProperty(key);

            sb.append('\'').append(key).append('\'');
            sb.append("=>");
            sb.append('\'').append(value).append('\'');
            sb.append(',');
        }

        // delete last ',' if at least one value has been added
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("created property string: " + sb.toString()); // NOI18N
        }

        return sb.toString();
    }

    /**
     * DOCUMENT ME!
     *
     * @param   properties  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Properties fromTSTBCompatiblePropListing(final String properties) {
        final Properties props = new Properties();

        if ((properties != null) && !properties.isEmpty()) {
            final String[] kvSplit = properties.split(","); // NOI18N
            for (final String kv : kvSplit) {
                final String[] kvp = kv.split("=>");        // NOI18N
                if (kvp.length == 2) {
                    final String k = kvp[0].trim();
                    final String v = kvp[1].trim();
                    if ((k.length() > 2) && (v.length() > 2)) {
                        final String key = k.substring(1, k.length() - 1);
                        final String value = v.substring(1, v.length() - 1);

                        props.put(key, value);
                    } else {
                        LOG.warn("ignoring illegal property: " + kv); // NOI18N
                    }
                } else {
                    LOG.warn("ignoring illegal property: " + kv);     // NOI18N
                }
            }
        }

        return props;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   id         DOCUMENT ME!
     * @param   tablename  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static CidsBean fetchCidsBean(final int id, final String tablename) {
        return fetchCidsBean(id, tablename, SessionManager.getSession().getUser().getDomain());
    }

    /**
     * DOCUMENT ME!
     *
     * @param   id         DOCUMENT ME!
     * @param   tablename  DOCUMENT ME!
     * @param   domain     DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static CidsBean fetchCidsBean(final int id, final String tablename, final String domain) {
        final MetaClass mc = ClassCacheMultiple.getMetaClass(domain, tablename);
        try {
            final MetaObject mo = SessionManager.getProxy().getMetaObject(id, mc.getID(), domain);

            if (mo != null) {
                return mo.getBean();
            } else {
                LOG.warn("could not find meta object #" + id + " in table " + tablename + " @" + domain);
                return null;
            }
        } catch (final ConnectionException ex) {
            LOG.warn("cannot get timeseries bean from server", ex); // NOI18N
            return null;
        }
    }

    /**
     * DOCUMENT ME!
     */
    public static void showMappingComponent() {
        PluginRegistry.getRegistry()
                .getPluginDescriptor(CISMAP_PLUGIN_NAME)
                .getUIDescriptor(CISMAP_PLUGIN_NAME)
                .getView()
                .makeVisible();
    }

    /**
     * DOCUMENT ME!
     *
     * @param   clazz  DOCUMENT ME!
     * @param   name   DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  IllegalArgumentException  DOCUMENT ME!
     */
    public static ImageIcon loadImageIcon(final Class clazz, final String name) {
        if ((clazz == null) || (name == null) || name.isEmpty()) {
            throw new IllegalArgumentException("class or name is null or name is empty: " + clazz + " || " + name); // NOI18N
        }

        final String path = clazz.getCanonicalName().replace(clazz.getSimpleName(), "").replace(".", "/"); // NOI18N

        if (LOG.isDebugEnabled()) {
            LOG.debug("loading imageicon from path: " + path + name);
        }

        return ImageUtilities.loadImageIcon(path + name, false);
    }

    /**
     * DOCUMENT ME!
     *
     * @param   bbox  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Coordinate[] getLlAndUr(final Geometry bbox) {
        final Coordinate[] llUr = new Coordinate[2];

        Coordinate ll = bbox.getCoordinate();
        Coordinate ur = bbox.getCoordinate();

        for (final Coordinate candidate : bbox.getCoordinates()) {
            if ((candidate.x <= ll.x) && (candidate.y <= ll.y)) {
                ll = candidate;
            } else if ((candidate.x >= ur.x) && (candidate.y >= ur.y)) {
                ur = candidate;
            }
        }

        llUr[0] = ll;
        llUr[1] = ur;

        return llUr;
    }
}
