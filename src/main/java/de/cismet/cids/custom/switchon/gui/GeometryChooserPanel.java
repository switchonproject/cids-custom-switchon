/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui;

import com.vividsolutions.jts.geom.Geometry;

import org.apache.log4j.Logger;

import java.awt.EventQueue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.cismet.cids.custom.switchon.gui.utils.CismapUtils;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;
import de.cismet.cids.dynamics.Disposable;

import de.cismet.cids.editors.DefaultCustomObjectEditor;

import de.cismet.cismap.cids.geometryeditor.DefaultCismapGeometryComboBoxEditor;

import de.cismet.cismap.commons.features.FeatureCollection;
import de.cismet.cismap.commons.features.FeatureCollectionEvent;
import de.cismet.cismap.commons.features.FeatureCollectionListener;
import de.cismet.cismap.commons.gui.MappingComponent;
import de.cismet.cismap.commons.gui.piccolo.eventlistener.CreateGeometryListenerInterface;
import de.cismet.cismap.commons.gui.piccolo.eventlistener.CreateNewGeometryListener;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class GeometryChooserPanel extends javax.swing.JPanel implements CidsBeanStore,
    Disposable,
    FeatureCollectionListener,
    PropertyChangeListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(GeometryChooserPanel.class);

    //~ Instance fields --------------------------------------------------------

    private CidsBean cidsBean;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btngMapButtons;
    private de.cismet.cismap.cids.geometryeditor.DefaultCismapGeometryComboBoxEditor cmbGeometry;
    private javax.swing.JToggleButton cmdNewPolygon;
    private javax.swing.JToggleButton cmdPan;
    private javax.swing.JToggleButton cmdRemoveGeometry;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private de.cismet.cids.custom.switchon.gui.PreviewMapPanel previewMapPanel;
    private javax.swing.JTextField txtCoordinates;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form GeometryChooserPanel.
     */
    public GeometryChooserPanel() {
        initComponents();
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

        btngMapButtons = new javax.swing.ButtonGroup();
        previewMapPanel = new de.cismet.cids.custom.switchon.gui.PreviewMapPanel();
        cmbGeometry = new de.cismet.cismap.cids.geometryeditor.DefaultCismapGeometryComboBoxEditor();
        jLabel1 = new javax.swing.JLabel();
        txtCoordinates = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        cmdPan = new javax.swing.JToggleButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(32767, 0));
        cmdRemoveGeometry = new javax.swing.JToggleButton();
        cmdNewPolygon = new javax.swing.JToggleButton();

        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(previewMapPanel, gridBagConstraints);
        previewMapPanel.setGeoFieldPropertyKey("spatialcoverage.geo_field");
        previewMapPanel.setPurePreviewMap(false);

        final org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.spatialcoverage}"),
                cmbGeometry,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        binding.setConverter(((DefaultCismapGeometryComboBoxEditor)cmbGeometry).getConverter());
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        add(cmbGeometry, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel1,
            org.openide.util.NbBundle.getMessage(GeometryChooserPanel.class, "GeometryChooserPanel.jLabel1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        add(jLabel1, gridBagConstraints);

        txtCoordinates.setText(org.openide.util.NbBundle.getMessage(
                GeometryChooserPanel.class,
                "GeometryChooserPanel.txtCoordinates.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 10, 10);
        add(txtCoordinates, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        btngMapButtons.add(cmdPan);
        cmdPan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pan.gif"))); // NOI18N
        cmdPan.setSelected(true);
        cmdPan.setToolTipText(org.openide.util.NbBundle.getMessage(
                GeometryChooserPanel.class,
                "GeometryChooserPanel.cmdPan.toolTipText"));                                  // NOI18N
        cmdPan.setBorderPainted(false);
        cmdPan.setFocusPainted(false);
        cmdPan.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    cmdPanActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(cmdPan, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(filler1, gridBagConstraints);

        btngMapButtons.add(cmdRemoveGeometry);
        cmdRemoveGeometry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove.png"))); // NOI18N
        cmdRemoveGeometry.setToolTipText(org.openide.util.NbBundle.getMessage(
                GeometryChooserPanel.class,
                "GeometryChooserPanel.cmdRemoveGeometry.toolTipText"));                                     // NOI18N
        cmdRemoveGeometry.setBorderPainted(false);
        cmdRemoveGeometry.setFocusPainted(false);
        cmdRemoveGeometry.setMaximumSize(new java.awt.Dimension(58, 34));
        cmdRemoveGeometry.setMinimumSize(new java.awt.Dimension(58, 34));
        cmdRemoveGeometry.setPreferredSize(new java.awt.Dimension(58, 34));
        cmdRemoveGeometry.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    cmdRemoveGeometryActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel1.add(cmdRemoveGeometry, gridBagConstraints);

        btngMapButtons.add(cmdNewPolygon);
        cmdNewPolygon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/newPolygon.png"))); // NOI18N
        cmdNewPolygon.setToolTipText(org.openide.util.NbBundle.getMessage(
                GeometryChooserPanel.class,
                "GeometryChooserPanel.cmdNewPolygon.toolTipText"));                                         // NOI18N
        cmdNewPolygon.setBorderPainted(false);
        cmdNewPolygon.setFocusPainted(false);
        cmdNewPolygon.setMaximumSize(new java.awt.Dimension(58, 34));
        cmdNewPolygon.setMinimumSize(new java.awt.Dimension(58, 34));
        cmdNewPolygon.setPreferredSize(new java.awt.Dimension(58, 34));
        cmdNewPolygon.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    cmdNewPolygonActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel1.add(cmdNewPolygon, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jPanel1, gridBagConstraints);

        bindingGroup.bind();
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmdPanActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_cmdPanActionPerformed
        if (getMap() != null) {
            getMap().setInteractionMode(MappingComponent.PAN);
        }
    }                                                                          //GEN-LAST:event_cmdPanActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmdNewPolygonActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_cmdNewPolygonActionPerformed
        EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    ((CreateNewGeometryListener)getMap().getInputListener(MappingComponent.NEW_POLYGON)).setMode(
                        CreateGeometryListenerInterface.POLYGON);
                    getMap().setInteractionMode(MappingComponent.NEW_POLYGON);
                }
            });
    } //GEN-LAST:event_cmdNewPolygonActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmdRemoveGeometryActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_cmdRemoveGeometryActionPerformed
        EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    getMap().setInteractionMode(MappingComponent.REMOVE_POLYGON);
                }
            });
    } //GEN-LAST:event_cmdRemoveGeometryActionPerformed

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
            cmbGeometry.setCidsMetaObject(cidsBean.getMetaObject());
            bindingGroup.bind();
            previewMapPanel.setCidsBean(cidsBean);
            getMap().getFeatureCollection().addFeatureCollectionListener(this);
            cidsBean.addPropertyChangeListener(this);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public Geometry getGeometry() {
        return previewMapPanel.getGeometry();
    }

    /**
     * DOCUMENT ME!
     *
     * @param  geometry  DOCUMENT ME!
     */
    public void setGeometry(final Geometry geometry) {
        previewMapPanel.setGeometry(geometry);
    }

    @Override
    public void dispose() {
        bindingGroup.unbind();
        ((DefaultCismapGeometryComboBoxEditor)cmbGeometry).dispose();
        getMap().getFeatureCollection().removeFeatureCollectionListener(this);
        cidsBean.removePropertyChangeListener(this);
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private MappingComponent getMap() {
        return previewMapPanel.getMappingComponent();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("spatialcoverage")) {
            getMap().getFeatureCollection().removeFeatureCollectionListener(this);
            if (evt.getOldValue() == null) {
                try {
                    final Geometry geoObj = (Geometry)cidsBean.getProperty("spatialcoverage.geo_field");
                    setGeometry(geoObj);
                } catch (Exception ex) {
                    throw new RuntimeException("Error when setting geom origin.", ex);
                }
            }
            amountOfFeaturesChanged();
            getMap().getFeatureCollection().addFeatureCollectionListener(this);
        }
    }

    /**
     * DOCUMENT ME!
     */
    private void amountOfFeaturesChanged() {
        final FeatureCollection featureCollection = getMap().getFeatureCollection();
        final int featureAmount = featureCollection.getFeatureCount();

        if (featureAmount >= 1) {
            cmdNewPolygon.setEnabled(false);
            cmdRemoveGeometry.setEnabled(true);
        } else {
            cmdNewPolygon.setEnabled(true);
            cmdRemoveGeometry.setEnabled(false);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param  geomCidsBean  DOCUMENT ME!
     */
    private void setDrawnGeometryToCidsBean(final CidsBean geomCidsBean) {
        cidsBean.removePropertyChangeListener(this);
        try {
            cidsBean.setProperty("spatialcoverage", geomCidsBean);
        } catch (Exception ex) {
            LOG.error(ex, ex);
        }
        cidsBean.addPropertyChangeListener(this);
    }

    @Override
    public void featuresAdded(final FeatureCollectionEvent fce) {
        amountOfFeaturesChanged();
        setDrawnGeometryToCidsBean(CismapUtils.createGeometryBean(getGeometry()));
    }

    @Override
    public void allFeaturesRemoved(final FeatureCollectionEvent fce) {
        amountOfFeaturesChanged();
        setDrawnGeometryToCidsBean(null);
    }

    @Override
    public void featuresRemoved(final FeatureCollectionEvent fce) {
        amountOfFeaturesChanged();
        setDrawnGeometryToCidsBean(null);
    }

    @Override
    public void featuresChanged(final FeatureCollectionEvent fce) {
    }

    @Override
    public void featureSelectionChanged(final FeatureCollectionEvent fce) {
    }

    @Override
    public void featureReconsiderationRequested(final FeatureCollectionEvent fce) {
    }

    @Override
    public void featureCollectionChanged() {
    }
}
