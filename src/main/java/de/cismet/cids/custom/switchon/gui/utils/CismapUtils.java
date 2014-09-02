/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui.utils;

import Sirius.navigator.plugin.PluginRegistry;

import Sirius.server.middleware.types.MetaObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cismap.commons.features.Feature;
import de.cismet.cismap.commons.features.FeatureGroups;
import de.cismet.cismap.commons.gui.MappingComponent;
import de.cismet.cismap.commons.interaction.CismapBroker;

import de.cismet.cismap.navigatorplugin.CidsFeature;
import de.cismet.cismap.navigatorplugin.CismapPlugin;

import static de.cismet.cids.custom.switchon.Utils.CISMAP_PLUGIN_NAME;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class CismapUtils {

    //~ Static fields/initializers ---------------------------------------------

    private static final String CISMAP_PLUGIN_NAME = "cismap";

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     */
    public static void switchToCismapMap() {
        PluginRegistry.getRegistry()
                .getPluginDescriptor(CISMAP_PLUGIN_NAME)
                .getUIDescriptor(CISMAP_PLUGIN_NAME)
                .getView()
                .makeVisible();
    }

    /**
     * DOCUMENT ME!
     *
     * @param  bean   DOCUMENT ME!
     * @param  clear  DOCUMENT ME!
     */
    public static void addBeanGeomAsFeatureToCismapMap(final CidsBean bean, final boolean clear) {
        if (bean != null) {
            final MetaObject mo = bean.getMetaObject();
            final List<MetaObject> mos = new ArrayList<MetaObject>(1);
            mos.add(mo);
            addBeanGeomsAsFeaturesToCismapMap(mos, clear);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param  metaObjectList  DOCUMENT ME!
     * @param  clear           DOCUMENT ME!
     */
    public static void addBeanGeomsAsFeaturesToCismapMap(final Collection<MetaObject> metaObjectList,
            final boolean clear) {
        if (metaObjectList != null) {
            final MappingComponent bigMap = CismapBroker.getInstance().getMappingComponent();
            if (clear) {
                bigMap.getFeatureCollection().removeAllFeatures();
            }
            final List<Feature> addedFeatures = new ArrayList<Feature>(metaObjectList.size());
            for (final MetaObject mo : metaObjectList) {
                final CidsFeature newGeomFeature = new CidsFeature(mo);
                addedFeatures.addAll(FeatureGroups.expandAll(newGeomFeature));
            }
            bigMap.getFeatureCollection().addFeatures(addedFeatures);
            bigMap.zoomToFeatureCollection();
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static CismapPlugin getCismapPlugin() {
        return (CismapPlugin)PluginRegistry.getRegistry().getPlugin(CISMAP_PLUGIN_NAME);
    }
}
