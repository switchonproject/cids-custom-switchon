/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.search;

import Sirius.navigator.connection.SessionManager;
import Sirius.navigator.search.dynamic.SearchControlListener;
import Sirius.navigator.search.dynamic.SearchControlPanel;

import Sirius.server.middleware.types.LightweightMetaObject;
import Sirius.server.middleware.types.MetaClass;

import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URL;

import java.sql.Time;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.custom.switchon.gui.utils.ComponentTitledBorder;
import de.cismet.cids.custom.switchon.gui.utils.Taggroups;
import de.cismet.cids.custom.switchon.gui.utils.TagsComboBox;
import de.cismet.cids.custom.switchon.gui.utils.TagsJList;

import de.cismet.cids.navigator.utils.ClassCacheMultiple;

import de.cismet.cids.server.search.MetaObjectNodeServerSearch;

import de.cismet.cids.tools.search.clientstuff.CidsWindowSearch;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
@org.openide.util.lookup.ServiceProvider(service = CidsWindowSearch.class)
public class ResourceWindowSearch extends javax.swing.JPanel implements CidsWindowSearch, SearchControlListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            ResourceWindowSearch.class);
    public static final String DOMAIN = "SWITCHON";

    //~ Instance fields --------------------------------------------------------

    private final ImageIcon icon;
    private final MetaClass metaClass;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnGeospatial;
    private javax.swing.JButton btnSearch;
    private javax.swing.JCheckBox chbGeospatial;
    private javax.swing.JCheckBox chbKeywords;
    private javax.swing.JCheckBox chbSearchInTitleAndDescription;
    private javax.swing.JCheckBox chbTemporal;
    private javax.swing.JCheckBox chbTitle;
    private javax.swing.JComboBox cmbGeospatial;
    private javax.swing.JComboBox cmbTopics;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker jdpEndDate;
    private org.jdesktop.swingx.JXDatePicker jdpStartDate;
    private javax.swing.JList lstKeywords;
    private javax.swing.JPanel pnlGeospatialExtent;
    private javax.swing.JPanel pnlKeywordsAndTopics;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlSearchButtons;
    private javax.swing.JPanel pnlStatus;
    private javax.swing.JPanel pnlTemporalExtent;
    private javax.swing.JPanel pnlTitleAndDescription;
    private javax.swing.JPanel tabAdvancedSearch;
    private javax.swing.JPanel tabAggregatedSearch;
    private javax.swing.JPanel tabBasic;
    private javax.swing.JTabbedPane tpaSearchTabs;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form ResourceWindowSearch.
     */
    public ResourceWindowSearch() {
        initComponents();
        addBorderToPanels();

        btnSearch.setVisible(false);
        btnCancel.setVisible(false);

        final JPanel pnlSearchCancel = new SearchControlPanel(this);
        final java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        pnlSearchButtons.add(pnlSearchCancel, gridBagConstraints);

        metaClass = ClassCacheMultiple.getMetaClass(DOMAIN, "resource"); // NOI18N

        byte[] iconDataFromMetaclass = new byte[] {};

        if (metaClass != null) {
            iconDataFromMetaclass = metaClass.getIconData();
        }

        if (iconDataFromMetaclass.length > 0) {
            LOG.info("Using icon from metaclass.");                                                              // NOI18N
            icon = new ImageIcon(metaClass.getIconData());
        } else {
            LOG.warn("Metaclass icon is not set. Trying to load default icon.");                                 // NOI18N
            final URL urlToIcon = getClass().getResource("/de/cismet/cids/custom/wunda_blau/search/search.png"); // NOI18N

            if (urlToIcon != null) {
                icon = new ImageIcon(urlToIcon);
            } else {
                icon = new ImageIcon(new byte[] {});
            }
        }
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  args  DOCUMENT ME!
     */
    public static void main(final String[] args) {
        try {
            DevelopmentTools.initSessionManagerFromRMIConnectionOnLocalhost(
                "SWITCHON",        // NOI18N
                "Administratoren", // NOI18N
                "admin",           // NOI18N
                "cismet");         // NOI18N
            final JScrollPane jsp = new JScrollPane(new ResourceWindowSearch());
            DevelopmentTools.showTestFrame(jsp, 800, 1000);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    /**
     * DOCUMENT ME!
     */
    private void addBorderToPanels() {
        chbTemporal.setText(NbBundle.getMessage(
                ResourceWindowSearch.class,
                "ResourceWindowSearch.addBorderToPanels.temporal"));
        chbTemporal.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
                    final boolean selected = ((JCheckBox)e.getSource()).isSelected();
                    jdpStartDate.setEnabled(selected);
                    jdpEndDate.setEnabled(selected);
                }
            });

        ComponentTitledBorder border = new ComponentTitledBorder(
                chbTemporal,
                pnlTemporalExtent,
                BorderFactory.createLineBorder(Color.black));
        pnlTemporalExtent.setBorder(border);

        chbGeospatial.setText(NbBundle.getMessage(
                ResourceWindowSearch.class,
                "ResourceWindowSearch.addBorderToPanels.geospatial"));
        chbGeospatial.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
                    final boolean selected = ((JCheckBox)e.getSource()).isSelected();
                    cmbGeospatial.setEnabled(selected);
                    btnGeospatial.setEnabled(selected);
                }
            });
        border = new ComponentTitledBorder(
                chbGeospatial,
                pnlGeospatialExtent,
                BorderFactory.createLineBorder(Color.black));
        pnlGeospatialExtent.setBorder(border);

        chbKeywords.setText(NbBundle.getMessage(
                ResourceWindowSearch.class,
                "ResourceWindowSearch.addBorderToPanels.keywords"));
        chbKeywords.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
                    final boolean selected = ((JCheckBox)e.getSource()).isSelected();
                    lstKeywords.setEnabled(selected);
                    cmbTopics.setEnabled(selected);
                }
            });
        border = new ComponentTitledBorder(
                chbKeywords,
                pnlKeywordsAndTopics,
                BorderFactory.createLineBorder(Color.black));
        pnlKeywordsAndTopics.setBorder(border);

        chbTitle.setText(NbBundle.getMessage(
                ResourceWindowSearch.class,
                "ResourceWindowSearch.addBorderToPanels.title"));
        chbTitle.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
                    final boolean selected = ((JCheckBox)e.getSource()).isSelected();
                    txtTitle.setEnabled(selected);
                    chbSearchInTitleAndDescription.setEnabled(selected);
                }
            });
        border = new ComponentTitledBorder(
                chbTitle,
                pnlTitleAndDescription,
                BorderFactory.createLineBorder(Color.black));
        pnlTitleAndDescription.setBorder(border);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        chbGeospatial = new javax.swing.JCheckBox();
        chbTemporal = new javax.swing.JCheckBox();
        chbKeywords = new javax.swing.JCheckBox();
        chbTitle = new javax.swing.JCheckBox();
        pnlMain = new javax.swing.JPanel();
        btnClear = new javax.swing.JButton();
        tpaSearchTabs = new javax.swing.JTabbedPane();
        tabBasic = new javax.swing.JPanel();
        pnlGeospatialExtent = new javax.swing.JPanel();
        btnGeospatial = new javax.swing.JButton();
        cmbGeospatial = new TagsComboBox(Taggroups.LOCATION);
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        pnlTemporalExtent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jdpStartDate = new org.jdesktop.swingx.JXDatePicker();
        jdpEndDate = new org.jdesktop.swingx.JXDatePicker();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(32767, 0));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        pnlTitleAndDescription = new javax.swing.JPanel();
        txtTitle = new javax.swing.JTextField();
        chbSearchInTitleAndDescription = new javax.swing.JCheckBox();
        pnlKeywordsAndTopics = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstKeywords = new TagsJList(Taggroups.KEYWORDS_INSPIRE_THEMES_1_0, Taggroups.KEYWORDS_OPEN);
        cmbTopics = new TagsComboBox(Taggroups.TOPIC_CATEGORY);
        tabAdvancedSearch = new javax.swing.JPanel();
        tabAggregatedSearch = new javax.swing.JPanel();
        pnlSearchButtons = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        pnlStatus = new javax.swing.JPanel();

        org.openide.awt.Mnemonics.setLocalizedText(
            chbGeospatial,
            org.openide.util.NbBundle.getMessage(
                ResourceWindowSearch.class,
                "ResourceWindowSearch.chbGeospatial.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(
            chbTemporal,
            org.openide.util.NbBundle.getMessage(ResourceWindowSearch.class, "ResourceWindowSearch.chbTemporal.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(
            chbKeywords,
            org.openide.util.NbBundle.getMessage(ResourceWindowSearch.class, "ResourceWindowSearch.chbKeywords.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(
            chbTitle,
            org.openide.util.NbBundle.getMessage(ResourceWindowSearch.class, "ResourceWindowSearch.chbTitle.text")); // NOI18N

        setLayout(new java.awt.BorderLayout());

        pnlMain.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            btnClear,
            org.openide.util.NbBundle.getMessage(ResourceWindowSearch.class, "ResourceWindowSearch.btnClear.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlMain.add(btnClear, gridBagConstraints);

        tabBasic.setLayout(new java.awt.GridBagLayout());

        pnlGeospatialExtent.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    ResourceWindowSearch.class,
                    "ResourceWindowSearch.pnlGeospatialExtent.border.title"))); // NOI18N
        pnlGeospatialExtent.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            btnGeospatial,
            org.openide.util.NbBundle.getMessage(
                ResourceWindowSearch.class,
                "ResourceWindowSearch.btnGeospatial.text")); // NOI18N
        btnGeospatial.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        pnlGeospatialExtent.add(btnGeospatial, gridBagConstraints);

        cmbGeospatial.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        pnlGeospatialExtent.add(cmbGeospatial, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        pnlGeospatialExtent.add(filler2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
        tabBasic.add(pnlGeospatialExtent, gridBagConstraints);

        pnlTemporalExtent.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    ResourceWindowSearch.class,
                    "ResourceWindowSearch.pnlTemporalExtent.border.title"))); // NOI18N
        pnlTemporalExtent.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel1,
            org.openide.util.NbBundle.getMessage(ResourceWindowSearch.class, "ResourceWindowSearch.jLabel1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlTemporalExtent.add(jLabel1, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel2,
            org.openide.util.NbBundle.getMessage(ResourceWindowSearch.class, "ResourceWindowSearch.jLabel2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlTemporalExtent.add(jLabel2, gridBagConstraints);

        jdpStartDate.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlTemporalExtent.add(jdpStartDate, gridBagConstraints);

        jdpEndDate.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlTemporalExtent.add(jdpEndDate, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        pnlTemporalExtent.add(filler1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        pnlTemporalExtent.add(filler3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        tabBasic.add(pnlTemporalExtent, gridBagConstraints);

        pnlTitleAndDescription.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    ResourceWindowSearch.class,
                    "ResourceWindowSearch.pnlTitleAndDescription.border.title"))); // NOI18N
        pnlTitleAndDescription.setLayout(new java.awt.GridBagLayout());

        txtTitle.setText(org.openide.util.NbBundle.getMessage(
                ResourceWindowSearch.class,
                "ResourceWindowSearch.txtTitle.text")); // NOI18N
        txtTitle.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlTitleAndDescription.add(txtTitle, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            chbSearchInTitleAndDescription,
            org.openide.util.NbBundle.getMessage(
                ResourceWindowSearch.class,
                "ResourceWindowSearch.chbSearchInTitleAndDescription.text")); // NOI18N
        chbSearchInTitleAndDescription.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pnlTitleAndDescription.add(chbSearchInTitleAndDescription, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        tabBasic.add(pnlTitleAndDescription, gridBagConstraints);

        pnlKeywordsAndTopics.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    ResourceWindowSearch.class,
                    "ResourceWindowSearch.pnlKeywordsAndTopics.border.title"))); // NOI18N
        pnlKeywordsAndTopics.setLayout(new java.awt.GridBagLayout());

        lstKeywords.setEnabled(false);
        jScrollPane1.setViewportView(lstKeywords);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlKeywordsAndTopics.add(jScrollPane1, gridBagConstraints);

        cmbTopics.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlKeywordsAndTopics.add(cmbTopics, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 10);
        tabBasic.add(pnlKeywordsAndTopics, gridBagConstraints);

        tpaSearchTabs.addTab(org.openide.util.NbBundle.getMessage(
                ResourceWindowSearch.class,
                "ResourceWindowSearch.tabBasic.TabConstraints.tabTitle"),
            tabBasic); // NOI18N

        final javax.swing.GroupLayout tabAdvancedSearchLayout = new javax.swing.GroupLayout(tabAdvancedSearch);
        tabAdvancedSearch.setLayout(tabAdvancedSearchLayout);
        tabAdvancedSearchLayout.setHorizontalGroup(
            tabAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(
                0,
                703,
                Short.MAX_VALUE));
        tabAdvancedSearchLayout.setVerticalGroup(
            tabAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(
                0,
                285,
                Short.MAX_VALUE));

        tpaSearchTabs.addTab(org.openide.util.NbBundle.getMessage(
                ResourceWindowSearch.class,
                "ResourceWindowSearch.tabAdvancedSearch.TabConstraints.tabTitle"),
            tabAdvancedSearch); // NOI18N

        final javax.swing.GroupLayout tabAggregatedSearchLayout = new javax.swing.GroupLayout(tabAggregatedSearch);
        tabAggregatedSearch.setLayout(tabAggregatedSearchLayout);
        tabAggregatedSearchLayout.setHorizontalGroup(
            tabAggregatedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(
                0,
                703,
                Short.MAX_VALUE));
        tabAggregatedSearchLayout.setVerticalGroup(
            tabAggregatedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(
                0,
                285,
                Short.MAX_VALUE));

        tpaSearchTabs.addTab(org.openide.util.NbBundle.getMessage(
                ResourceWindowSearch.class,
                "ResourceWindowSearch.tabAggregatedSearch.TabConstraints.tabTitle"),
            tabAggregatedSearch); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlMain.add(tpaSearchTabs, gridBagConstraints);

        pnlSearchButtons.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            btnCancel,
            org.openide.util.NbBundle.getMessage(ResourceWindowSearch.class, "ResourceWindowSearch.btnCancel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pnlSearchButtons.add(btnCancel, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnSearch,
            org.openide.util.NbBundle.getMessage(ResourceWindowSearch.class, "ResourceWindowSearch.btnSearch.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pnlSearchButtons.add(btnSearch, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlMain.add(pnlSearchButtons, gridBagConstraints);

        add(pnlMain, java.awt.BorderLayout.CENTER);

        pnlStatus.setPreferredSize(new java.awt.Dimension(682, 50));

        final javax.swing.GroupLayout pnlStatusLayout = new javax.swing.GroupLayout(pnlStatus);
        pnlStatus.setLayout(pnlStatusLayout);
        pnlStatusLayout.setHorizontalGroup(
            pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(
                0,
                718,
                Short.MAX_VALUE));
        pnlStatusLayout.setVerticalGroup(
            pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(
                0,
                50,
                Short.MAX_VALUE));

        add(pnlStatus, java.awt.BorderLayout.SOUTH);
    } // </editor-fold>//GEN-END:initComponents

    @Override
    public JComponent getSearchWindowComponent() {
        return this;
    }

    @Override
    public MetaObjectNodeServerSearch getServerSearch() {
        final MetaObjectNodeResourceSearchStatement searchStatement = new MetaObjectNodeResourceSearchStatement(
                SessionManager.getSession().getUser());
        if (chbGeospatial.isSelected()) {
            final Object selectedItem = cmbGeospatial.getSelectedItem();
            if (selectedItem instanceof LightweightMetaObject) {
                searchStatement.setLocation(((LightweightMetaObject)selectedItem).getName());
            } else {
            }
        }

        if (chbKeywords.isSelected()) {
            final List<LightweightMetaObject> keywords = lstKeywords.getSelectedValuesList();
            final List<String> keywordsNames = new ArrayList<String>(keywords.size());
            for (final LightweightMetaObject mo : keywords) {
                keywordsNames.add(mo.getName());
            }
            searchStatement.setKeywordList(keywordsNames);

            final LightweightMetaObject moTopic = (LightweightMetaObject)cmbTopics.getSelectedItem();
            searchStatement.setTopicCategory(moTopic.getName());
        }

        if (chbTemporal.isSelected()) {
            final Date start = jdpStartDate.getDate();
            if (start != null) {
                searchStatement.setFromDate(new Time(start.getTime()));
            } else {
                searchStatement.setFromDate(null);
            }

            final Date end = jdpEndDate.getDate();
            if (end != null) {
                searchStatement.setToDate(new Time(end.getTime()));
            } else {
                searchStatement.setFromDate(null);
            }
        }

        if (chbTitle.isSelected()) {
            searchStatement.setTitle(txtTitle.getText());
            if (chbSearchInTitleAndDescription.isSelected()) {
                searchStatement.setDescription(txtTitle.getText());
            }
        }

        return searchStatement;
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(ResourceWindowSearch.class, "ResourceWindowSearch.name");
    }

    @Override
    public MetaObjectNodeServerSearch assembleSearch() {
        return getServerSearch();
    }

    @Override
    public void searchStarted() {
    }

    @Override
    public void searchDone(final int numberOfResults) {
    }

    @Override
    public void searchCanceled() {
    }

    @Override
    public boolean suppressEmptyResultMessage() {
        return false;
    }
}
