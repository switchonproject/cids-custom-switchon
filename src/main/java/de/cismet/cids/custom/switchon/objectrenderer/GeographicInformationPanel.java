/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objectrenderer;

import com.vividsolutions.jts.geom.Geometry;

import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

import org.apache.commons.lang.StringUtils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import de.cismet.cids.custom.switchon.SwitchOnConstants;
import de.cismet.cids.custom.switchon.gui.utils.CismapUtils;
import de.cismet.cids.custom.switchon.gui.utils.FastBindableReferenceComboFactory;
import de.cismet.cids.custom.switchon.gui.utils.RendererTools;
import de.cismet.cids.custom.switchon.gui.utils.ResourceUtils;
import de.cismet.cids.custom.switchon.gui.utils.Taggroups;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;

import de.cismet.cids.editors.DefaultCustomObjectEditor;

import de.cismet.cismap.commons.CrsTransformer;
import de.cismet.cismap.commons.XBoundingBox;
import de.cismet.cismap.commons.features.DefaultStyledFeature;
import de.cismet.cismap.commons.features.StyledFeature;
import de.cismet.cismap.commons.gui.MappingComponent;
import de.cismet.cismap.commons.gui.layerwidget.ActiveLayerModel;
import de.cismet.cismap.commons.raster.wms.simple.SimpleWMS;
import de.cismet.cismap.commons.raster.wms.simple.SimpleWmsGetMapUrl;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class GeographicInformationPanel extends javax.swing.JPanel implements CidsBeanStore {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            GeographicInformationPanel.class);

    //~ Instance fields --------------------------------------------------------

    final StyledFeature previewGeometry = new DefaultStyledFeature();

    private CidsBean cidsBean;
    private final MappingComponent previewMap;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbLocation;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstCatchements;
    private javax.swing.JPanel pnlMap;
    private javax.swing.JTextField txtResolutions;
    private javax.swing.JTextField txtScales;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form GeographicInformationPanel.
     */
    public GeographicInformationPanel() {
        initComponents();

        previewMap = new MappingComponent();
        pnlMap.setLayout(new BorderLayout());
        pnlMap.add(previewMap, BorderLayout.CENTER);

        RendererTools.makeReadOnly(txtResolutions);
        RendererTools.makeReadOnly(txtScales);
        RendererTools.makeReadOnly(cmbLocation);
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

        jPanel1 = new javax.swing.JPanel();
        pnlMap = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCatchements = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbLocation = FastBindableReferenceComboFactory.createTagsFastBindableReferenceComboBox(Taggroups.LOCATION);
        txtResolutions = new javax.swing.JTextField();
        txtScales = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    GeographicInformationPanel.class,
                    "GeographicInformationPanel.jPanel1.border.title"))); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        final javax.swing.GroupLayout pnlMapLayout = new javax.swing.GroupLayout(pnlMap);
        pnlMap.setLayout(pnlMapLayout);
        pnlMapLayout.setHorizontalGroup(
            pnlMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));
        pnlMapLayout.setVerticalGroup(
            pnlMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(pnlMap, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.75;
        gridBagConstraints.weighty = 1.0;
        add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    GeographicInformationPanel.class,
                    "GeographicInformationPanel.jPanel2.border.title"))); // NOI18N
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(0, 0));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(0, 0));
        jScrollPane1.setViewportView(lstCatchements);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.weighty = 1.0;
        add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    GeographicInformationPanel.class,
                    "GeographicInformationPanel.jPanel3.border.title"))); // NOI18N
        jPanel3.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel1,
            org.openide.util.NbBundle.getMessage(
                GeographicInformationPanel.class,
                "GeographicInformationPanel.jLabel1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
        jPanel3.add(jLabel1, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel2,
            org.openide.util.NbBundle.getMessage(
                GeographicInformationPanel.class,
                "GeographicInformationPanel.jLabel2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel3.add(jLabel2, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel3,
            org.openide.util.NbBundle.getMessage(
                GeographicInformationPanel.class,
                "GeographicInformationPanel.jLabel3.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 5);
        jPanel3.add(jLabel3, gridBagConstraints);

        final org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.location}"),
                cmbLocation,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 10);
        jPanel3.add(cmbLocation, gridBagConstraints);

        txtResolutions.setText(org.openide.util.NbBundle.getMessage(
                GeographicInformationPanel.class,
                "GeographicInformationPanel.txtResolutions.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel3.add(txtResolutions, gridBagConstraints);

        txtScales.setText(org.openide.util.NbBundle.getMessage(
                GeographicInformationPanel.class,
                "GeographicInformationPanel.txtScales.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 10);
        jPanel3.add(txtScales, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(jPanel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        add(filler1, gridBagConstraints);

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
            initCatchmentsList();
            initSpatialResolutionsAndScales();
        }
    }

    /**
     * DOCUMENT ME!
     */
    private void initMap() {
        if (cidsBean != null) {
            final Object geoObj = cidsBean.getProperty("spatialcoverage.geo_field");
            if (geoObj instanceof Geometry) {
                final Geometry pureGeom = CrsTransformer.transformToGivenCrs((Geometry)geoObj,
                        SwitchOnConstants.COMMONS.SRS_SERVICE);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("SwitchOnConstants.Commons.GeoBUffer: " + SwitchOnConstants.COMMONS.GEO_BUFFER);
                }
                final XBoundingBox box;
                try {
                    box = new XBoundingBox(pureGeom.getEnvelope().buffer(
                                SwitchOnConstants.COMMONS.GEO_BUFFER));
                } catch (NullPointerException npe) {
                    LOG.error(
                        "NPE in the constructor of XBoundingBox. This happens if a renderer/editor is started with DevelopmentTools.",
                        npe);
                    return;
                }
                final double diagonalLength = Math.sqrt((box.getWidth() * box.getWidth())
                                + (box.getHeight() * box.getHeight()));
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Buffer for map: " + diagonalLength);
                }
                final XBoundingBox bufferedBox = new XBoundingBox(box.getGeometry().buffer(diagonalLength));
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
                            swms.setName("Spatial Coverage");

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
    }

    /**
     * DOCUMENT ME!
     */
    private void initCatchmentsList() {
        final DefaultListModel<CidsBean> listModel = new DefaultListModel<CidsBean>();
        for (final CidsBean catchement : ResourceUtils.filterTagsOfResource(cidsBean, Taggroups.CATCHMENTS)) {
            listModel.addElement(catchement);
        }
        lstCatchements.setModel(listModel);
    }

    /**
     * DOCUMENT ME!
     */
    private void initSpatialResolutionsAndScales() {
        final List<String> resolutions = new ArrayList<String>();
        final List<String> scales = new ArrayList<String>();
        for (final CidsBean representation : cidsBean.getBeanCollectionProperty("representation")) {
            final String resolution = (String)representation.getProperty("spatialresolution");
            if (StringUtils.isNotBlank(resolution)) {
                resolutions.add(resolution);
            }

            final Integer spatialscale = (Integer)representation.getProperty("spatialscale");
            if (spatialscale != null) {
                scales.add(spatialscale.toString());
            }
        }
        txtResolutions.setText(StringUtils.join(resolutions, ", "));
        txtScales.setText(StringUtils.join(scales, ", "));
    }
}
