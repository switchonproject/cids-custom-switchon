/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.WKTReader;

import org.apache.log4j.Logger;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.AbstractMap.SimpleEntry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

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
public class GeometryChooserPanel extends InfoProviderJPanel implements CidsBeanStore,
    Disposable,
    FeatureCollectionListener,
    PropertyChangeListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(GeometryChooserPanel.class);

    //~ Instance fields --------------------------------------------------------

    private CidsBean cidsBean;

    private final SimpleEntry<String, String>[] countriesModel;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btngMapButtons;
    private de.cismet.cismap.cids.geometryeditor.DefaultCismapGeometryComboBoxEditor cmbGeometry;
    private javax.swing.JToggleButton cmdNewPolygon;
    private javax.swing.JToggleButton cmdPan;
    private javax.swing.JToggleButton cmdRemoveGeometry;
    private javax.swing.JToggleButton cmdZoom;
    private javax.swing.JComboBox countriesComboBox;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
        this.countriesModel = new SimpleEntry[] {
                new SimpleEntry<String, String>("NONE", ""),
                new SimpleEntry<String, String>("WORLD", "POLYGON((-180 -90,-180 90,180 90,180 -90,-180 -90))"),
                new SimpleEntry<String, String>(
                    "EUROPE",
                    "POLYGON((-31.266001 27.636311,-31.266001 81.008797,39.869301 81.008797,39.869301 27.636311,-31.266001 27.636311))"),
                new SimpleEntry<String, String>(
                    "Aland",
                    "POLYGON((19.5131942070001 59.9044863950001,19.5131942070001 60.4807803410001,21.0966903000001 60.4807803410001,21.0966903000001 59.9044863950001,19.5131942070001 59.9044863950001))"),
                new SimpleEntry<String, String>(
                    "Albania",
                    "POLYGON((19.2720325110001 39.637013245,19.2720325110001 42.6548135380001,21.0366793210001 42.6548135380001,21.0366793210001 39.637013245,19.2720325110001 39.637013245))"),
                new SimpleEntry<String, String>(
                    "Andorra",
                    "POLYGON((1.4064563390001 42.4286774700001,1.4064563390001 42.649361674,1.76509078000015 42.649361674,1.76509078000015 42.4286774700001,1.4064563390001 42.4286774700001))"),
                new SimpleEntry<String, String>(
                    "Austria",
                    "POLYGON((9.52115482500011 46.3786430870001,9.52115482500011 49.0097744750001,17.1483378500001 49.0097744750001,17.1483378500001 46.3786430870001,9.52115482500011 46.3786430870001))"),
                new SimpleEntry<String, String>(
                    "Belarus",
                    "POLYGON((23.165644979 51.2351683560001,23.165644979 56.1568059290001,32.7195321040001 56.1568059290001,32.7195321040001 51.2351683560001,23.165644979 51.2351683560001))"),
                new SimpleEntry<String, String>(
                    "Belgium",
                    "POLYGON((2.52179992769047 49.495222881,2.52179992769047 51.4962376910001,6.37452518700007 51.4962376910001,6.37452518700007 49.495222881,2.52179992769047 49.495222881))"),
                new SimpleEntry<String, String>(
                    "Bosnia and Herz.",
                    "POLYGON((15.7160738520001 42.5592121380002,15.7160738520001 45.2845238250002,19.618884725 45.2845238250002,19.618884725 42.5592121380002,15.7160738520001 42.5592121380002))"),
                new SimpleEntry<String, String>(
                    "Bulgaria",
                    "POLYGON((22.3450232340001 41.2381041470001,22.3450232340001 44.228434539,28.6035262380001 44.228434539,28.6035262380001 41.2381041470001,22.3450232340001 41.2381041470001))"),
                new SimpleEntry<String, String>(
                    "Croatia",
                    "POLYGON((13.5014754570001 42.4163272160001,13.5014754570001 46.5469790650001,19.4078381750001 46.5469790650001,19.4078381750001 42.4163272160001,13.5014754570001 42.4163272160001))"),
                new SimpleEntry<String, String>(
                    "Czech Rep.",
                    "POLYGON((12.076140991 48.557915752,12.076140991 51.0400123090001,18.8374337160001 51.0400123090001,18.8374337160001 48.557915752,12.076140991 48.557915752))"),
                new SimpleEntry<String, String>(
                    "Denmark",
                    "POLYGON((8.09400475400008 54.5685895850001,8.09400475400008 57.7511660830001,15.1513778000001 57.7511660830001,15.1513778000001 54.5685895850001,8.09400475400008 54.5685895850001))"),
                new SimpleEntry<String, String>(
                    "Estonia",
                    "POLYGON((21.8323673840001 57.5158185830001,21.8323673840001 59.6708845070001,28.1864754640001 59.6708845070001,28.1864754640001 57.5158185830001,21.8323673840001 57.5158185830001))"),
                new SimpleEntry<String, String>(
                    "Faeroe Is.",
                    "POLYGON((-7.64415442599994 61.3941104190001,-7.64415442599994 62.3989118510001,-6.27578691299991 62.3989118510001,-6.27578691299991 61.3941104190001,-7.64415442599994 61.3941104190001))"),
                new SimpleEntry<String, String>(
                    "Finland",
                    "POLYGON((20.62316451 59.811224677,20.62316451 70.0753103640001,31.5695247800001 70.0753103640001,31.5695247800001 59.811224677,20.62316451 59.811224677))"),
                new SimpleEntry<String, String>(
                    "France",
                    "POLYGON((-61.7978409499999 -21.3707821589999,-61.7978409499999 51.0875408834804,55.8545028000001 51.0875408834804,55.8545028000001 -21.3707821589999,-61.7978409499999 -21.3707821589999))"),
                new SimpleEntry<String, String>(
                    "Germany",
                    "POLYGON((5.85248986800011 47.271120911,5.85248986800011 55.065334377,15.0220593670001 55.065334377,15.0220593670001 47.271120911,5.85248986800011 47.271120911))"),
                new SimpleEntry<String, String>(
                    "Gibraltar",
                    "POLYGON((-5.35838675876349 36.1105003930001,-5.35838675876349 36.1411196720123,-5.33877348311998 36.1411196720123,-5.33877348311998 36.1105003930001,-5.35838675876349 36.1105003930001))"),
                new SimpleEntry<String, String>(
                    "Greece",
                    "POLYGON((19.6264754570001 34.8150088560001,19.6264754570001 41.7504759730001,28.2397567070001 41.7504759730001,28.2397567070001 34.8150088560001,19.6264754570001 34.8150088560001))"),
                new SimpleEntry<String, String>(
                    "Guernsey",
                    "POLYGON((-2.67345130099989 49.4115664730001,-2.67345130099989 49.731390692,-2.17031816299993 49.731390692,-2.17031816299993 49.4115664730001,-2.67345130099989 49.4115664730001))"),
                new SimpleEntry<String, String>(
                    "Hungary",
                    "POLYGON((16.0940352780001 45.741343486,16.0940352780001 48.5692328900001,22.8776005460001 48.5692328900001,22.8776005460001 45.741343486,16.0940352780001 45.741343486))"),
                new SimpleEntry<String, String>(
                    "Iceland",
                    "POLYGON((-24.539906379 63.3967145850001,-24.539906379 66.564154364,-13.5029190749999 66.564154364,-13.5029190749999 63.3967145850001,-24.539906379 63.3967145850001))"),
                new SimpleEntry<String, String>(
                    "Ireland",
                    "POLYGON((-10.4781794909999 51.4457054710001,-10.4781794909999 55.386379299,-5.99351966099994 55.386379299,-5.99351966099994 51.4457054710001,-10.4781794909999 51.4457054710001))"),
                new SimpleEntry<String, String>(
                    "Isle of Man",
                    "POLYGON((-4.79015051999994 54.0569522160001,-4.79015051999994 54.4190127620001,-4.3119197259999 54.4190127620001,-4.3119197259999 54.0569522160001,-4.79015051999994 54.0569522160001))"),
                new SimpleEntry<String, String>(
                    "Italy",
                    "POLYGON((6.60272831200007 35.489243882,6.60272831200007 47.085214945,18.5174259770001 47.085214945,18.5174259770001 35.489243882,6.60272831200007 35.489243882))"),
                new SimpleEntry<String, String>(
                    "Jersey",
                    "POLYGON((-2.24201412699992 49.1713320980001,-2.24201412699992 49.267035223,-2.00829016799992 49.267035223,-2.00829016799992 49.1713320980001,-2.24201412699992 49.1713320980001))"),
                new SimpleEntry<String, String>(
                    "Kosovo",
                    "POLYGON((20.024751424 41.8440103160001,20.024751424 43.2630709840001,21.7727584220001 43.2630709840001,21.7727584220001 41.8440103160001,20.024751424 41.8440103160001))"),
                new SimpleEntry<String, String>(
                    "Latvia",
                    "POLYGON((20.9685978520001 55.6669908660001,20.9685978520001 58.0751384490001,28.2172746170001 58.0751384490001,28.2172746170001 55.6669908660001,20.9685978520001 55.6669908660001))"),
                new SimpleEntry<String, String>(
                    "Liechtenstein",
                    "POLYGON((9.47588627100012 47.0524004120001,9.47588627100012 47.2628010050001,9.61572269700011 47.2628010050001,9.61572269700011 47.0524004120001,9.47588627100012 47.0524004120001))"),
                new SimpleEntry<String, String>(
                    "Lithuania",
                    "POLYGON((20.9245687005624 53.8868411260001,20.9245687005624 56.4426024370001,26.8007202560001 56.4426024370001,26.8007202560001 53.8868411260001,20.9245687005624 53.8868411260001))"),
                new SimpleEntry<String, String>(
                    "Luxembourg",
                    "POLYGON((5.71492720500004 49.441324362,5.71492720500004 50.1749746710001,6.50257938700014 50.1749746710001,6.50257938700014 49.441324362,5.71492720500004 49.441324362))"),
                new SimpleEntry<String, String>(
                    "Macedonia",
                    "POLYGON((20.4441573490001 40.8493940230001,20.4441573490001 42.3703347790001,23.0095821530001 42.3703347790001,23.0095821530001 40.8493940230001,20.4441573490001 40.8493940230001))"),
                new SimpleEntry<String, String>(
                    "Malta",
                    "POLYGON((14.1836043630001 35.801214911,14.1836043630001 36.0755882830001,14.5671492850001 36.0755882830001,14.5671492850001 35.801214911,14.1836043630001 35.801214911))"),
                new SimpleEntry<String, String>(
                    "Moldova",
                    "POLYGON((26.617889038 45.461773987,26.617889038 48.4860338340001,30.1315763750001 48.4860338340001,30.1315763750001 45.461773987,26.617889038 45.461773987))"),
                new SimpleEntry<String, String>(
                    "Monaco",
                    "POLYGON((7.36575020700008 43.7179690770001,7.36575020700008 43.763505554,7.4374540320631 43.763505554,7.4374540320631 43.7179690770001,7.36575020700008 43.7179690770001))"),
                new SimpleEntry<String, String>(
                    "Montenegro",
                    "POLYGON((18.4335307210001 41.852362372,18.4335307210001 43.5478856410001,20.3551705320001 43.5478856410001,20.3551705320001 41.852362372,18.4335307210001 41.852362372))"),
                new SimpleEntry<String, String>(
                    "Netherlands",
                    "POLYGON((-68.4173884759999 12.0220401060001,-68.4173884759999 53.5580915390001,7.19850590000004 53.5580915390001,7.19850590000004 12.0220401060001,-68.4173884759999 12.0220401060001))"),
                new SimpleEntry<String, String>(
                    "Norway",
                    "POLYGON((-9.11742102799991 -54.4624976539999,-9.11742102799991 80.7700869810001,33.6403914720001 80.7700869810001,33.6403914720001 -54.4624976539999,-9.11742102799991 -54.4624976539999))"),
                new SimpleEntry<String, String>(
                    "Poland",
                    "POLYGON((14.123922973 48.9940131640001,14.123922973 54.8383242860001,24.1431563720001 54.8383242860001,24.1431563720001 48.9940131640001,14.123922973 48.9940131640001))"),
                new SimpleEntry<String, String>(
                    "Portugal",
                    "POLYGON((-31.2849014959999 30.0292422550001,-31.2849014959999 42.15362966,-6.20594722499993 42.15362966,-6.20594722499993 30.0292422550001,-31.2849014959999 30.0292422550001))"),
                new SimpleEntry<String, String>(
                    "Romania",
                    "POLYGON((20.2428259690001 43.6500499480001,20.2428259690001 48.2748322560001,29.6995548840001 48.2748322560001,29.6995548840001 43.6500499480001,20.2428259690001 43.6500499480001))"),
                new SimpleEntry<String, String>(
                    "Russia",
                    "POLYGON((-180 41.1926805620002,-180 81.8587100280001,180 81.8587100280001,180 41.1926805620002,-180 41.1926805620002))"),
                new SimpleEntry<String, String>(
                    "San Marino",
                    "POLYGON((12.3856287450001 43.892055515,12.3856287450001 43.9825667860001,12.4923922540001 43.9825667860001,12.4923922540001 43.892055515,12.3856287450001 43.892055515))"),
                new SimpleEntry<String, String>(
                    "Serbia",
                    "POLYGON((18.8449784750001 42.2349425250001,18.8449784750001 46.1738752240001,22.9845707600001 46.1738752240001,22.9845707600001 42.2349425250001,18.8449784750001 42.2349425250001))"),
                new SimpleEntry<String, String>(
                    "Slovakia",
                    "POLYGON((16.8444804280001 47.7500064090001,16.8444804280001 49.601779684,22.5396366780001 49.601779684,22.5396366780001 47.7500064090001,16.8444804280001 47.7500064090001))"),
                new SimpleEntry<String, String>(
                    "Slovenia",
                    "POLYGON((13.3652612710001 45.42363678,13.3652612710001 46.863962301,16.5153015540001 46.863962301,16.5153015540001 45.42363678,13.3652612710001 45.42363678))"),
                new SimpleEntry<String, String>(
                    "Spain",
                    "POLYGON((-18.1672257149999 27.6422386740001,-18.1672257149999 43.793443101,4.3370874360001 43.793443101,4.3370874360001 27.6422386740001,-18.1672257149999 27.6422386740001))"),
                new SimpleEntry<String, String>(
                    "Sweden",
                    "POLYGON((11.1081649100001 55.3426781270001,11.1081649100001 69.0363556930001,24.1634135340001 69.0363556930001,24.1634135340001 55.3426781270001,11.1081649100001 55.3426781270001))"),
                new SimpleEntry<String, String>(
                    "Switzerland",
                    "POLYGON((5.95480920400013 45.820718486,5.95480920400013 47.8011660770001,10.466626831 47.8011660770001,10.466626831 45.820718486,5.95480920400013 45.820718486))"),
                new SimpleEntry<String, String>(
                    "Ukraine",
                    "POLYGON((22.1328398030001 44.381048895,22.1328398030001 52.3689492800001,40.1595430910002 52.3689492800001,40.1595430910002 44.381048895,22.1328398030001 44.381048895))"),
                new SimpleEntry<String, String>(
                    "United Kingdom",
                    "POLYGON((-13.6913142569999 49.9096133480001,-13.6913142569999 60.84788646,1.77116946700002 60.84788646,1.77116946700002 49.9096133480001,-13.6913142569999 49.9096133480001))")
            };

        initComponents();
        this.countriesComboBox.setSelectedIndex(-1);
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
        cmdZoom = new javax.swing.JToggleButton();
        cmdNewPolygon = new javax.swing.JToggleButton();
        cmdRemoveGeometry = new javax.swing.JToggleButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(32767, 0));
        jLabel2 = new javax.swing.JLabel();
        countriesComboBox = new javax.swing.JComboBox();

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
        txtCoordinates.addFocusListener(new java.awt.event.FocusAdapter() {

                @Override
                public void focusGained(final java.awt.event.FocusEvent evt) {
                    txtCoordinatesFocusGained(evt);
                }
            });
        txtCoordinates.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    txtCoordinatesActionPerformed(evt);
                }
            });
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
        jPanel1.add(cmdPan, new java.awt.GridBagConstraints());

        btngMapButtons.add(cmdZoom);
        cmdZoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/zoom.gif"))); // NOI18N
        cmdZoom.setSelected(true);
        cmdZoom.setToolTipText(org.openide.util.NbBundle.getMessage(
                GeometryChooserPanel.class,
                "GeometryChooserPanel.cmdZoom.toolTipText"));                                   // NOI18N
        cmdZoom.setBorderPainted(false);
        cmdZoom.setFocusPainted(false);
        cmdZoom.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    cmdZoomActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(cmdZoom, gridBagConstraints);

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
        gridBagConstraints.gridy = 0;
        jPanel1.add(cmdNewPolygon, gridBagConstraints);

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
        gridBagConstraints.gridy = 0;
        jPanel1.add(cmdRemoveGeometry, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(filler1, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel2,
            org.openide.util.NbBundle.getMessage(GeometryChooserPanel.class, "GeometryChooserPanel.jLabel2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel1.add(jLabel2, gridBagConstraints);

        countriesComboBox.setModel(new DefaultComboBoxModel(this.countriesModel));
        countriesComboBox.setMinimumSize(new java.awt.Dimension(150, 20));
        countriesComboBox.setPreferredSize(new java.awt.Dimension(150, 20));
        countriesComboBox.setRenderer(new CountriesRenderer());
        countriesComboBox.addItemListener(new java.awt.event.ItemListener() {

                @Override
                public void itemStateChanged(final java.awt.event.ItemEvent evt) {
                    countriesComboBoxItemStateChanged(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(countriesComboBox, gridBagConstraints);

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
                    countriesComboBox.setSelectedIndex(-1);
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
                    countriesComboBox.setSelectedIndex(-1);
                }
            });
    } //GEN-LAST:event_cmdRemoveGeometryActionPerformed

    /**
     * maxY, maxX, minY, minX.
     *
     * @param  evt  DOCUMENT ME!
     */
    private void txtCoordinatesActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_txtCoordinatesActionPerformed
        try {
            final String[] coords = txtCoordinates.getText().split(",");               // NOI18N
            if (coords.length != 4) {
                throw new Exception("The text field does not contain four values.");   // NOI18N
            }
            final double maxY = Double.parseDouble(coords[0]);
            final double maxX = Double.parseDouble(coords[1]);
            final double minY = Double.parseDouble(coords[2]);
            final double minX = Double.parseDouble(coords[3]);

            final Coordinate[] coordinates = new Coordinate[5];
            coordinates[0] = new Coordinate(minX, maxY);
            coordinates[1] = new Coordinate(maxX, maxY);
            coordinates[2] = new Coordinate(maxX, minY);
            coordinates[3] = new Coordinate(minX, minY);
            coordinates[4] = new Coordinate(minX, maxY);

            final Geometry rectangle = new GeometryFactory().createPolygon(coordinates);
            previewMapPanel.setGeometry(rectangle);
            countriesComboBox.setSelectedIndex(-1);
        } catch (Exception ex) {
            LOG.warn(ex, ex);
            provideError(org.openide.util.NbBundle.getMessage(
                    GeometryChooserPanel.class,
                    "GeometryChooserPanel.txtCoordinatesActionPerformed.error"));
        }
    } //GEN-LAST:event_txtCoordinatesActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void txtCoordinatesFocusGained(final java.awt.event.FocusEvent evt) { //GEN-FIRST:event_txtCoordinatesFocusGained
        provideInformation(org.openide.util.NbBundle.getMessage(
                GeometryChooserPanel.class,
                "GeometryChooserPanel.txtCoordinatesFocusGained.info"));
    }                                                                             //GEN-LAST:event_txtCoordinatesFocusGained

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmdZoomActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_cmdZoomActionPerformed
        if (getMap() != null) {
            getMap().setInteractionMode(MappingComponent.ZOOM);
        }
    }                                                                           //GEN-LAST:event_cmdZoomActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void countriesComboBoxItemStateChanged(final java.awt.event.ItemEvent evt) { //GEN-FIRST:event_countriesComboBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            final Object item = evt.getItem();
            if (item != null) {
                try {
                    final String WKTString = ((SimpleEntry<String, String>)item).getValue();
                    final Geometry geometry = new WKTReader().read(WKTString);
                    this.setGeometry(geometry);
                } catch (Exception ex) {
                    LOG.error(ex.getMessage(), ex);
                }
            }
        }
    }                                                                                    //GEN-LAST:event_countriesComboBoxItemStateChanged

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
        showCoordinatesInTextField(geometry);
    }

    /**
     * maxY, maxX, minY, minX.
     *
     * @param  geometry  DOCUMENT ME!
     */
    private void showCoordinatesInTextField(final Geometry geometry) {
        if (geometry != null) {
            if (geometry.isRectangle()) {
                final Coordinate[] coordinates = geometry.getCoordinates();
                final Coordinate minXminY = coordinates[0];
                final Coordinate maxXmaxY = coordinates[2];
                txtCoordinates.setEditable(true);
                txtCoordinates.setText(maxXmaxY.y + "," + maxXmaxY.x + "," + minXminY.y + "," + minXminY.x); // NOI18N
            } else {
                txtCoordinates.setEditable(false);
                txtCoordinates.setText(geometry.getGeometryType());
            }
        } else {
            txtCoordinates.setEditable(true);
            txtCoordinates.setText("");                                                                      // NOI18N
        }
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
        if (evt.getPropertyName().equals("spatialcoverage")) {                                           // NOI18N
            getMap().getFeatureCollection().removeFeatureCollectionListener(this);
            if (evt.getOldValue() == null) {
                try {
                    final Geometry geoObj = (Geometry)cidsBean.getProperty("spatialcoverage.geo_field"); // NOI18N
                    setGeometry(geoObj);
                    // this.countriesComboBox.setSelectedIndex(-1);
                } catch (Exception ex) {
                    throw new RuntimeException("Error when setting geom origin.", ex);                   // NOI18N
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
     * @param  geometry  geomCidsBean DOCUMENT ME!
     */
    private void setDrawnGeometryToCidsBean(final Geometry geometry) {
        cidsBean.removePropertyChangeListener(this);
        try {
            cidsBean.setProperty("spatialcoverage", CismapUtils.createGeometryBean(geometry)); // NOI18N
            showCoordinatesInTextField(geometry);
        } catch (Exception ex) {
            LOG.error(ex, ex);
        }
        cidsBean.addPropertyChangeListener(this);
    }

    /**
     * DOCUMENT ME!
     *
     * @param  visible  DOCUMENT ME!
     */
    public void setVisibleGeometryComboBox(final boolean visible) {
        cmbGeometry.setVisible(visible);
    }

    @Override
    public void featuresAdded(final FeatureCollectionEvent fce) {
        amountOfFeaturesChanged();
        setDrawnGeometryToCidsBean(getGeometry());
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

    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    class CountriesRenderer extends BasicComboBoxRenderer {

        //~ Methods ------------------------------------------------------------

        @Override
        public Component getListCellRendererComponent(final JList list,
                final Object value,
                final int index,
                final boolean isSelected,
                final boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

            if (value != null) {
                final SimpleEntry<String, String> entry = (SimpleEntry<String, String>)value;
                setText(entry.getKey());
            }

            if (index == -1) {
                setText("NONE");
            }

            return this;
        }
    }
}
