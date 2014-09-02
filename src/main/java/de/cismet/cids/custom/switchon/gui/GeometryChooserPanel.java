/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui;

import com.vividsolutions.jts.geom.Geometry;

import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

import org.apache.log4j.Logger;

import org.jdom.DataConversionException;

import org.openide.util.Exceptions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.List;

import de.cismet.cids.custom.switchon.SwitchOnConstants;
import de.cismet.cids.custom.switchon.gui.utils.CismapUtils;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;
import de.cismet.cids.dynamics.Disposable;

import de.cismet.cids.editors.DefaultCustomObjectEditor;

import de.cismet.cismap.cids.geometryeditor.DefaultCismapGeometryComboBoxEditor;

import de.cismet.cismap.commons.BoundingBox;
import de.cismet.cismap.commons.CrsTransformer;
import de.cismet.cismap.commons.XBoundingBox;
import de.cismet.cismap.commons.features.DefaultStyledFeature;
import de.cismet.cismap.commons.features.Feature;
import de.cismet.cismap.commons.features.StyledFeature;
import de.cismet.cismap.commons.gui.MappingComponent;
import de.cismet.cismap.commons.gui.layerwidget.ActiveLayerModel;
import de.cismet.cismap.commons.interaction.CismapBroker;
import de.cismet.cismap.commons.raster.wms.simple.SimpleWMS;
import de.cismet.cismap.commons.raster.wms.simple.SimpleWmsGetMapUrl;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class GeometryChooserPanel extends javax.swing.JPanel implements CidsBeanStore, Disposable {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(GeometryChooserPanel.class);

    //~ Instance fields --------------------------------------------------------

    final StyledFeature previewGeometry = new DefaultStyledFeature();

    private final MappingComponent previewMap;
    private CidsBean cidsBean;
    private Geometry lastRefreshedGeometry = null;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.cismet.cismap.cids.geometryeditor.DefaultCismapGeometryComboBoxEditor cmbGeometry;
    private javax.swing.JPanel pnlMap;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form GeometryChooserPanel.
     */
    public GeometryChooserPanel() {
        initComponents();
        previewMap = new MappingComponent();
        pnlMap.setLayout(new BorderLayout());
        pnlMap.add(previewMap, BorderLayout.CENTER);
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        pnlMap = new javax.swing.JPanel();
        cmbGeometry = new de.cismet.cismap.cids.geometryeditor.DefaultCismapGeometryComboBoxEditor();

        setLayout(new java.awt.GridBagLayout());

        pnlMap.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        add(pnlMap, gridBagConstraints);

        final org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.spatialcoverage}"),
                cmbGeometry,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setConverter(((DefaultCismapGeometryComboBoxEditor)cmbGeometry).getConverter());
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 5);
        add(cmbGeometry, gridBagConstraints);

        bindingGroup.bind();
    } // </editor-fold>//GEN-END:initComponents

    @Override
    public CidsBean getCidsBean() {
        return cidsBean;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        bindingGroup.unbind();
        if (cidsBean != null) {
            this.cidsBean = cidsBean;
            DefaultCustomObjectEditor.setMetaClassInformationToMetaClassStoreComponentsInBindingGroup(
                bindingGroup,
                this.cidsBean);
            bindingGroup.bind();
            initMap();

            cidsBean.addPropertyChangeListener(new PropertyChangeListener() {

                    @Override
                    public void propertyChange(final PropertyChangeEvent evt) {
                        if (evt.getPropertyName().equals("spatialcoverage")) {
                            if (evt.getOldValue() == null) {
                                try {
                                    final Geometry geoObj = (Geometry)cidsBean.getProperty("spatialcoverage.geo_field");
                                    initMap();
                                    setGeometry(geoObj);
                                } catch (Exception ex) {
                                    throw new RuntimeException("Error when setting geom origin.", ex);
                                }
                            }
                        }
                    }
                });
        }
    }

    /**
     * DOCUMENT ME!
     */
    private void initMap() {
        if (cidsBean != null) {
            final Object geoObj = cidsBean.getProperty("spatialcoverage.geo_field");
            XBoundingBox tmpBufferedBox;
            Geometry tmpPureGeom = null;
            if (geoObj instanceof Geometry) {
                tmpPureGeom = CrsTransformer.transformToGivenCrs((Geometry)geoObj,
                        SwitchOnConstants.COMMONS.SRS_SERVICE);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("SwitchOnConstants.Commons.GeoBUffer: " + SwitchOnConstants.COMMONS.GEO_BUFFER);
                }
                tmpBufferedBox = new XBoundingBox(tmpPureGeom.getEnvelope().buffer(
                            SwitchOnConstants.COMMONS.GEO_BUFFER));
            } else {
                final String srsCode = CismapBroker.getInstance()
                            .getMappingComponent()
                            .getMappingModel()
                            .getSrs()
                            .getCode();
                final BoundingBox initb = CismapBroker.getInstance().getMappingComponent().getInitialBoundingBox();
                tmpBufferedBox = new XBoundingBox(initb.getX1(),
                        initb.getY1(),
                        initb.getX2(),
                        initb.getY2(),
                        srsCode,
                        CismapBroker.getInstance().getMappingComponent().getMappingModel().getSrs().isMetric());
            }
            final XBoundingBox bufferedBox = tmpBufferedBox;
            final Geometry pureGeom = tmpPureGeom;
            final Runnable mapRunnable = new Runnable() {

                    @Override
                    public void run() {
                        final ActiveLayerModel mappingModel = new ActiveLayerModel();
                        mappingModel.setSrs(SwitchOnConstants.COMMONS.SRS_SERVICE);
                        mappingModel.addHome(new XBoundingBox(
                                bufferedBox.getX1(),
                                bufferedBox.getY1(),
                                bufferedBox.getX2(),
                                bufferedBox.getY2(),
                                SwitchOnConstants.COMMONS.SRS_SERVICE,
                                true));
                        final SimpleWMS swms = new SimpleWMS(new SimpleWmsGetMapUrl(
                                    SwitchOnConstants.COMMONS.MAP_CALL_STRING));
                        swms.setName("Resource");

                        previewGeometry.setGeometry(pureGeom);
                        previewGeometry.setFillingPaint(new Color(1, 0, 0, 0.5f));
                        previewGeometry.setLineWidth(3);
                        previewGeometry.setLinePaint(new Color(1, 0, 0, 1f));
                        // add the raster layer to the model
                        mappingModel.addLayer(swms);
                        // set the model
                        previewMap.setMappingModel(mappingModel);
                        // initial positioning of the map
                        final int duration = previewMap.getAnimationDuration();
                        previewMap.setAnimationDuration(0);
                        previewMap.gotoInitialBoundingBox();
                        // interaction mode
                        previewMap.setInteractionMode(MappingComponent.ZOOM);
                        // finally when all configurations are done ...
                        previewMap.unlock();
                        previewMap.addCustomInputListener("MUTE", new PBasicInputEventHandler() {

                                @Override
                                public void mouseClicked(final PInputEvent evt) {
                                    if (evt.getClickCount() > 1) {
                                        final CidsBean bean = cidsBean;
                                        CismapUtils.switchToCismapMap();
                                        CismapUtils.addBeanGeomAsFeatureToCismapMap(bean, false);
                                    }
                                }
                            });
                        previewMap.setInteractionMode("MUTE");
                        previewMap.getFeatureCollection().addFeature(previewGeometry);
                        previewMap.setAnimationDuration(duration);
                    }
                };
            if (EventQueue.isDispatchThread()) {
                mapRunnable.run();
            } else {
                EventQueue.invokeLater(mapRunnable);
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public Geometry getGeometry() {
        Geometry geom = null;
        for (final Feature f : previewMap.getFeatureCollection().getAllFeatures()) {
            final Geometry g = f.getGeometry();
            if (g != null) {
                geom = g;
                break;
            }
        }
        return geom;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  geometry  DOCUMENT ME!
     */
    public void setGeometry(final Geometry geometry) {
        // remove all features from the map
        final List<Feature> featuresToRemove = new ArrayList<Feature>();
        for (final Feature f : previewMap.getFeatureCollection().getAllFeatures()) {
            featuresToRemove.add(f);
        }
        previewMap.getFeatureCollection().removeFeatures(featuresToRemove);
        if (geometry != null) {
            final StyledFeature dsf = new DefaultStyledFeature();
            dsf.setGeometry(geometry);
            dsf.setFillingPaint(new Color(1, 0, 0, 0.5f));
            dsf.setLineWidth(3);
            dsf.setLinePaint(new Color(1, 0, 0, 1f));
            dsf.setEditable(true);

            final XBoundingBox box;
            try {
                box = new XBoundingBox(geometry.getEnvelope().buffer(
                            SwitchOnConstants.COMMONS.GEO_BUFFER));
            } catch (NullPointerException npe) {
                LOG.error(
                    "NPE in the constructor of XBoundingBox. This happens if a renderer/editor is started with DevelopmentTools.",
                    npe);
                return;
            }

            if (previewMap.getMappingModel() != null) {
                ((ActiveLayerModel)previewMap.getMappingModel()).addHome(box);
                previewMap.gotoInitialBoundingBox();
            } else {
                LOG.error(
                    "previewMap.getMappingModel() is null, can not set initial bounding box",
                    new NullPointerException());
            }

            previewMap.getFeatureCollection().addFeature(dsf);
        }
    }

    @Override
    public void dispose() {
        bindingGroup.unbind();
        ((DefaultCismapGeometryComboBoxEditor)cmbGeometry).dispose();
    }
}
