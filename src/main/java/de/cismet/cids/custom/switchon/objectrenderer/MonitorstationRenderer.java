/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objectrenderer;

import org.apache.log4j.Logger;

import org.openide.util.NbBundle;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.cismet.cids.custom.switchon.AbstractCidsBeanRenderer;
import de.cismet.cids.custom.switchon.MonitorstationContext;
import de.cismet.cids.custom.switchon.Utils;
import de.cismet.cids.custom.switchon.Variable;
import de.cismet.cids.custom.switchon.objecteditors.MonitorstationEditor.VarCheckBox;

import de.cismet.cismap.commons.Crs;
import de.cismet.cismap.commons.XBoundingBox;
import de.cismet.cismap.commons.gui.MappingComponent;
import de.cismet.cismap.commons.gui.layerwidget.ActiveLayerModel;
import de.cismet.cismap.commons.raster.wms.simple.SimpleWMS;
import de.cismet.cismap.commons.raster.wms.simple.SimpleWmsGetMapUrl;

import de.cismet.cismap.navigatorplugin.CidsFeature;

/**
 * DOCUMENT ME!
 *
 * @author   mscholl
 * @version  $Revision$, $Date$
 */
public class MonitorstationRenderer extends AbstractCidsBeanRenderer {

    //~ Static fields/initializers ---------------------------------------------

    private static final String WMS_DEMIS_WORLDMAP_GETMAP_TEMPLATE =
        "http://www2.demis.nl/WMS/wms.ashx?wms=WorldMap&&VERSION=1.1.0&REQUEST=GetMap&BBOX=<cismap:boundingBox>&WIDTH=<cismap:width>&HEIGHT=<cismap:height>&SRS=EPSG:4326&FORMAT=image/png&TRANSPARENT=TRUE&BGCOLOR=0xF0F0F0&EXCEPTIONS=application/vnd.ogc.se_xml&LAYERS=Bathymetry,Countries,Topography,Hillshading,Builtup%20areas,Coastlines,Waterbodies,Inundated,Rivers,Streams,Railroads,Highways,Roads,Trails,Borders,Cities,Settlements,Spot%20elevations,Airports,Ocean%20features&STYLES";

    /** LOGGER. */
    private static final transient Logger LOG = Logger.getLogger(MonitorstationRenderer.class);

    //~ Instance fields --------------------------------------------------------

    private final transient ChangeListener posPreventVarChangeL;
    private final transient ChangeListener negPreventVarChangeL;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel lblContext;
    private javax.swing.JLabel lblContextValue;
    private javax.swing.JLabel lblHeadingMap;
    private javax.swing.JLabel lblHeadingMetadata;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNameValue;
    private de.cismet.cismap.commons.gui.MappingComponent map;
    private de.cismet.tools.gui.SemiRoundedPanel panHeadInfo;
    private de.cismet.tools.gui.SemiRoundedPanel panHeadInfo1;
    private javax.swing.JPanel pnlDataContent;
    private de.cismet.tools.gui.RoundedPanel pnlMap;
    private javax.swing.JPanel pnlVariables;
    private de.cismet.tools.gui.RoundedPanel pnldata;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form MonitorstationRenderer.
     */
    public MonitorstationRenderer() {
        this.posPreventVarChangeL = new PreventChangeListener(true);
        this.negPreventVarChangeL = new PreventChangeListener(false);

        initComponents();

        initVariables();
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     */
    private void initVariables() {
        final Variable[] vars = Variable.values();
        final GridLayout varLayout = new GridLayout(Math.round(vars.length / 2.0f), 2, 5, 5);
        pnlVariables.setLayout(varLayout);
        for (final Variable var : vars) {
            final VarCheckBox box = new VarCheckBox(var);
            box.setContentAreaFilled(false);
            pnlVariables.add(box);
        }
    }

    /**
     * DOCUMENT ME!
     */
    @Override
    protected void init() {
        bindingGroup.unbind();
        bindingGroup.bind();

        initType();
        initMap();

        final CidsFeature cidsFeature = new CidsFeature(cidsBean.getMetaObject());
        map.getFeatureCollection().addFeature(cidsFeature);
    }

    /**
     * DOCUMENT ME!
     */
    private void initType() {
        final String type = (String)cidsBean.getProperty("type"); // NOI18N
        clearCheckboxes();

        if (type != null) {
            if ("R".equals(type)) {
                LOG.warn("old monitor station type: " + cidsBean); // NOI18N

                return;
            }

            final String[] split = type.split(":", 2); // NOI18N

            assert (split.length == 1) || (split.length == 2) : "illegal type definition: " + type; // NOI18N

            final String ctxKey = split[0];
            lblContextValue.setText(MonitorstationContext.getMonitorstationContext(ctxKey).getLocalisedName());

            if (split.length == 2) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("assuming type with variable description:" + type); // NOI18N
                }

                final String[] vars = split[1].split(","); // NOI18N
                for (final String var : vars) {
                    setVarSelected(Variable.getVariable(var), true);
                }
            }
        }
    }

    /**
     * DOCUMENT ME!
     */
    @Override
    public void dispose() {
        map.getFeatureCollection().removeAllFeatures();

        super.dispose();
    }

    /**
     * DOCUMENT ME!
     */
    private void clearCheckboxes() {
        for (final Component c : pnlVariables.getComponents()) {
            if (c instanceof JCheckBox) {
                final JCheckBox box = (JCheckBox)c;
                box.setSelected(false);
                box.removeChangeListener(posPreventVarChangeL);
                box.addChangeListener(negPreventVarChangeL);
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param   var       DOCUMENT ME!
     * @param   selected  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  IllegalArgumentException  DOCUMENT ME!
     */
    private boolean setVarSelected(final Variable var, final boolean selected) {
        for (final Component c : pnlVariables.getComponents()) {
            if (c instanceof VarCheckBox) {
                final VarCheckBox box = (VarCheckBox)c;
                if (box.getVar().equals(var)) {
                    box.removeChangeListener(selected ? negPreventVarChangeL : posPreventVarChangeL);
                    box.setSelected(selected);
                    box.addChangeListener(selected ? posPreventVarChangeL : negPreventVarChangeL);

                    return true;
                }
            }
        }

        throw new IllegalArgumentException("var not present: " + var); // NOI18N
    }

    /**
     * DOCUMENT ME!
     */
    private void initMap() {
        map.lock();

        // europe
        final XBoundingBox bbox = new XBoundingBox(
                -23.328414916992188,
                27.816315492228306,
                45.596466064453125,
                74.51063839571114,
                Utils.EPSG_WGS84,
                false);

        final ActiveLayerModel mappingModel = new ActiveLayerModel();
        mappingModel.setSrs(new Crs(Utils.EPSG_WGS84, Utils.EPSG_WGS84, Utils.EPSG_WGS84, false, true));
        mappingModel.addHome(bbox);

        final SimpleWmsGetMapUrl getMapUrl = new SimpleWmsGetMapUrl(WMS_DEMIS_WORLDMAP_GETMAP_TEMPLATE);
        mappingModel.addLayer(new SimpleWMS(getMapUrl));

        map.setMappingModel(mappingModel);
        map.setInteractionMode(MappingComponent.OVERVIEW);
        map.gotoInitialBoundingBox();
        map.unlock();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        pnldata = new de.cismet.tools.gui.RoundedPanel();
        panHeadInfo = new de.cismet.tools.gui.SemiRoundedPanel();
        lblHeadingMetadata = new javax.swing.JLabel();
        pnlDataContent = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblNameValue = new javax.swing.JLabel();
        lblContext = new javax.swing.JLabel();
        lblContextValue = new javax.swing.JLabel();
        pnlVariables = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        pnlMap = new de.cismet.tools.gui.RoundedPanel();
        panHeadInfo1 = new de.cismet.tools.gui.SemiRoundedPanel();
        lblHeadingMap = new javax.swing.JLabel();
        map = new de.cismet.cismap.commons.gui.MappingComponent();

        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());

        pnldata.setLayout(new java.awt.GridBagLayout());

        panHeadInfo.setBackground(new java.awt.Color(51, 51, 51));
        panHeadInfo.setMinimumSize(new java.awt.Dimension(109, 24));
        panHeadInfo.setPreferredSize(new java.awt.Dimension(109, 24));
        panHeadInfo.setLayout(new java.awt.FlowLayout());

        lblHeadingMetadata.setForeground(new java.awt.Color(255, 255, 255));
        lblHeadingMetadata.setText(org.openide.util.NbBundle.getMessage(
                MonitorstationRenderer.class,
                "MonitorstationRenderer.lblHeadingMetadata.text")); // NOI18N
        panHeadInfo.add(lblHeadingMetadata);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        pnldata.add(panHeadInfo, gridBagConstraints);

        pnlDataContent.setOpaque(false);
        pnlDataContent.setLayout(new java.awt.GridBagLayout());

        lblName.setText(NbBundle.getMessage(MonitorstationRenderer.class, "MonitorstationRenderer.lblName.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDataContent.add(lblName, gridBagConstraints);

        final org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.name}"),
                lblNameValue,
                org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDataContent.add(lblNameValue, gridBagConstraints);

        lblContext.setText(NbBundle.getMessage(MonitorstationRenderer.class, "MonitorstationRenderer.lblContext.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDataContent.add(lblContext, gridBagConstraints);

        lblContextValue.setText(NbBundle.getMessage(
                MonitorstationRenderer.class,
                "MonitorstationRenderer.lblContextValue.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDataContent.add(lblContextValue, gridBagConstraints);

        pnlVariables.setBorder(javax.swing.BorderFactory.createTitledBorder(
                NbBundle.getMessage(MonitorstationRenderer.class, "MonitorstationRenderer.pnlVariables.border.title"))); // NOI18N
        pnlVariables.setToolTipText(NbBundle.getMessage(
                MonitorstationRenderer.class,
                "MonitorstationRenderer.pnlVariables.toolTipText"));                                                     // NOI18N
        pnlVariables.setOpaque(false);
        pnlVariables.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDataContent.add(pnlVariables, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        pnldata.add(pnlDataContent, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 1.0;
        pnldata.add(filler1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(pnldata, gridBagConstraints);

        pnlMap.setLayout(new java.awt.GridBagLayout());

        panHeadInfo1.setBackground(new java.awt.Color(51, 51, 51));
        panHeadInfo1.setMinimumSize(new java.awt.Dimension(109, 24));
        panHeadInfo1.setPreferredSize(new java.awt.Dimension(109, 24));
        panHeadInfo1.setLayout(new java.awt.FlowLayout());

        lblHeadingMap.setForeground(new java.awt.Color(255, 255, 255));
        lblHeadingMap.setText(org.openide.util.NbBundle.getMessage(
                MonitorstationRenderer.class,
                "MonitorstationRenderer.lblHeadingMap.text")); // NOI18N
        panHeadInfo1.add(lblHeadingMap);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        pnlMap.add(panHeadInfo1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlMap.add(map, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(pnlMap, gridBagConstraints);

        bindingGroup.bind();
    } // </editor-fold>//GEN-END:initComponents

    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private final class PreventChangeListener implements ChangeListener {

        //~ Instance fields ----------------------------------------------------

        private final boolean state;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new PreventChangeListener object.
         *
         * @param  state  DOCUMENT ME!
         */
        public PreventChangeListener(final boolean state) {
            this.state = state;
        }

        //~ Methods ------------------------------------------------------------

        /**
         * DOCUMENT ME!
         *
         * @param  e  DOCUMENT ME!
         */
        @Override
        public void stateChanged(final ChangeEvent e) {
            final JCheckBox box = (JCheckBox)e.getSource();
            box.setSelected(state);
        }
    }
}
