/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objecteditors;

import Sirius.navigator.connection.SessionManager;
import Sirius.navigator.ui.RequestsFullSizeComponent;

import Sirius.server.middleware.types.MetaObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultListModel;
import javax.swing.SortOrder;
import javax.swing.SwingWorker;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.custom.switchon.gui.JXListBugFixes;
import de.cismet.cids.custom.switchon.gui.utils.FastBindableReferenceComboFactory;
import de.cismet.cids.custom.switchon.search.server.MetaObjectProvenanceRelationshipSearchStatement;
import de.cismet.cids.custom.switchon.search.server.MetaObjectUsageRelationshipsSearchStatement;
import de.cismet.cids.custom.switchon.utils.Taggroups;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.editors.DefaultCustomObjectEditor;
import de.cismet.cids.editors.EditorClosedEvent;
import de.cismet.cids.editors.EditorSaveListener;
import de.cismet.cids.editors.FastBindableReferenceCombo;

import de.cismet.cids.tools.metaobjectrenderer.CidsBeanRenderer;

import de.cismet.tools.gui.StaticSwingTools;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class ResourceEditor extends javax.swing.JPanel implements CidsBeanRenderer,
    RequestsFullSizeComponent,
    EditorSaveListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ResourceEditor.class);

    //~ Instance fields --------------------------------------------------------

    private CidsBean cidsBean;
    private CidsBean provenanceRelationship = null;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.cismet.cids.custom.switchon.objecteditors.AdditionalTagsPanel additionalTagsPanel;
    private de.cismet.cids.custom.switchon.objecteditors.BasicPropertiesPanel basicPropertiesPanel;
    private javax.swing.JButton btnAddLocation;
    private javax.swing.JButton btnAddRepresentation;
    private javax.swing.JButton btnAddSrid;
    private javax.swing.JButton btnCreateRealtionship;
    private javax.swing.JButton btnEditProvenanceRelationship;
    private javax.swing.JButton btnEditRelationship;
    private javax.swing.JButton btnEditRepresentation;
    private javax.swing.JButton btnRemoveRepresentation;
    private javax.swing.JComboBox cmbStandard;
    private javax.swing.JComboBox cmbStandard1;
    private javax.swing.JComboBox cmbTopic;
    private de.cismet.cids.custom.switchon.objecteditors.ContactEditor contactEditor;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private de.cismet.cids.custom.switchon.gui.GeometryChooserPanel geometryChooserPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private de.cismet.cids.custom.switchon.objecteditors.LicenseInformationPanel licenseInformationPanel;
    private javax.swing.JList lstRelationships;
    private javax.swing.JList lstRepresentation;
    private de.cismet.cids.custom.switchon.objecteditors.MetaDataPanel metaDataPanel;
    private javax.swing.JPanel pnlBasicProperties;
    private javax.swing.JPanel pnlContact;
    private javax.swing.JPanel pnlDataAndMetaData;
    private javax.swing.JPanel pnlGeographicProperties;
    private javax.swing.JPanel pnlGeography;
    private javax.swing.JPanel pnlLicenseInformation;
    private javax.swing.JPanel pnlOtherProperties;
    private javax.swing.JPanel pnlRelationships;
    private javax.swing.JPanel pnlTagsAndCategory;
    private javax.swing.JPanel pnlTemporalInformation;
    private de.cismet.cids.custom.switchon.objecteditors.TemporalInformationPanel temporalInformationPanel;
    private javax.swing.JTextField txtProvenanceRelationship;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form ResourceEditor.
     */
    public ResourceEditor() {
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlBasicProperties = new javax.swing.JPanel();
        basicPropertiesPanel = new de.cismet.cids.custom.switchon.objecteditors.BasicPropertiesPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        pnlTagsAndCategory = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cmbTopic = FastBindableReferenceComboFactory.createTagsFastBindableReferenceComboBox(Taggroups.TOPIC_CATEGORY);
        final ArrayList<Taggroups> taggroups = new ArrayList<Taggroups>();
        taggroups.add(Taggroups.COLLECTION);
        taggroups.add(Taggroups.HYDROLOGICAL_CONCEPT);
        taggroups.add(Taggroups.KEYWORDS_INSPIRE_THEMES_1_0);
        taggroups.add(Taggroups.KEYWORDS_OPEN);
        additionalTagsPanel = new de.cismet.cids.custom.switchon.objecteditors.AdditionalTagsPanel(taggroups);
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        pnlContact = new javax.swing.JPanel();
        contactEditor = new de.cismet.cids.custom.switchon.objecteditors.ContactEditor();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        pnlGeographicProperties = new javax.swing.JPanel();
        pnlGeography = new javax.swing.JPanel();
        geometryChooserPanel = new de.cismet.cids.custom.switchon.gui.GeometryChooserPanel();
        pnlOtherProperties = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbStandard = FastBindableReferenceComboFactory.createTagsFastBindableReferenceComboBox(Taggroups.SRID);
        btnAddSrid = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cmbStandard1 = FastBindableReferenceComboFactory.createTagsFastBindableReferenceComboBox(Taggroups.LOCATION);
        btnAddLocation = new javax.swing.JButton();
        pnlTemporalInformation = new javax.swing.JPanel();
        temporalInformationPanel = new TemporalInformationPanel(true);
        ;
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        pnlLicenseInformation = new javax.swing.JPanel();
        licenseInformationPanel = new LicenseInformationPanel(true);
        ;
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        pnlDataAndMetaData = new javax.swing.JPanel();
        metaDataPanel = new de.cismet.cids.custom.switchon.objecteditors.MetaDataPanel();
        jPanel6 = new javax.swing.JPanel();
        btnAddRepresentation = new javax.swing.JButton();
        btnRemoveRepresentation = new javax.swing.JButton();
        btnEditRepresentation = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstRepresentation = new JXListBugFixes();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        pnlRelationships = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtProvenanceRelationship = new javax.swing.JTextField();
        btnEditProvenanceRelationship = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstRelationships = new javax.swing.JList();
        btnEditRelationship = new javax.swing.JButton();
        btnCreateRealtionship = new javax.swing.JButton();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));

        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        pnlBasicProperties.setOpaque(false);
        pnlBasicProperties.setLayout(new java.awt.GridBagLayout());

        basicPropertiesPanel.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlBasicProperties.add(basicPropertiesPanel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        pnlBasicProperties.add(filler1, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceEditor.class,
                "ResourceEditor.pnlBasicProperties.TabConstraints.tabTitle"),
            pnlBasicProperties); // NOI18N

        pnlTagsAndCategory.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.jPanel1.border.title"))); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.topiccategory}"),
                cmbTopic,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(cmbTopic, gridBagConstraints);
        ((FastBindableReferenceCombo)cmbTopic).setNullable(false);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        pnlTagsAndCategory.add(jPanel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlTagsAndCategory.add(additionalTagsPanel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        pnlTagsAndCategory.add(filler2, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceEditor.class,
                "ResourceEditor.pnlTagsAndCategory.TabConstraints.tabTitle"),
            pnlTagsAndCategory); // NOI18N

        pnlContact.setLayout(new java.awt.GridBagLayout());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.contact}"),
                contactEditor,
                org.jdesktop.beansbinding.BeanProperty.create("cidsBean"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlContact.add(contactEditor, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        pnlContact.add(filler3, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceEditor.class,
                "ResourceEditor.pnlContact.TabConstraints.tabTitle"),
            pnlContact); // NOI18N

        pnlGeographicProperties.setLayout(new java.awt.GridBagLayout());

        pnlGeography.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    ResourceEditor.class,
                    "ResourceEditor.pnlGeography.border.title"))); // NOI18N
        pnlGeography.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlGeography.add(geometryChooserPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        pnlGeographicProperties.add(pnlGeography, gridBagConstraints);

        pnlOtherProperties.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    ResourceEditor.class,
                    "ResourceEditor.pnlOtherProperties.border.title"))); // NOI18N
        pnlOtherProperties.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel1,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.jLabel1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
        pnlOtherProperties.add(jLabel1, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.srid}"),
                cmbStandard,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        pnlOtherProperties.add(cmbStandard, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnAddSrid,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.btnAddSrid.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 10);
        pnlOtherProperties.add(btnAddSrid, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel2,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.jLabel2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 5);
        pnlOtherProperties.add(jLabel2, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.location}"),
                cmbStandard1,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        pnlOtherProperties.add(cmbStandard1, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnAddLocation,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.btnAddLocation.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 10);
        pnlOtherProperties.add(btnAddLocation, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlGeographicProperties.add(pnlOtherProperties, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceEditor.class,
                "ResourceEditor.pnlGeographicProperties.TabConstraints.tabTitle"),
            pnlGeographicProperties); // NOI18N

        pnlTemporalInformation.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlTemporalInformation.add(temporalInformationPanel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        pnlTemporalInformation.add(filler4, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceEditor.class,
                "ResourceEditor.pnlTemporalInformation.TabConstraints.tabTitle"),
            pnlTemporalInformation); // NOI18N

        pnlLicenseInformation.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        pnlLicenseInformation.add(licenseInformationPanel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        pnlLicenseInformation.add(filler5, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceEditor.class,
                "ResourceEditor.pnlLicenseInformation.TabConstraints.tabTitle_1"),
            pnlLicenseInformation); // NOI18N

        pnlDataAndMetaData.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        pnlDataAndMetaData.add(metaDataPanel, gridBagConstraints);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.jPanel6.border.title"))); // NOI18N
        jPanel6.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            btnAddRepresentation,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.btnAddRepresentation.text")); // NOI18N
        btnAddRepresentation.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnAddRepresentationActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        jPanel6.add(btnAddRepresentation, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnRemoveRepresentation,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.btnRemoveRepresentation.text")); // NOI18N
        btnRemoveRepresentation.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnRemoveRepresentationActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        jPanel6.add(btnRemoveRepresentation, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnEditRepresentation,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.btnEditRepresentation.text")); // NOI18N
        btnEditRepresentation.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnEditRepresentationActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel6.add(btnEditRepresentation, gridBagConstraints);

        lstRepresentation.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        final org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create(
                "${cidsBean.representation}");
        final org.jdesktop.swingbinding.JListBinding jListBinding = org.jdesktop.swingbinding.SwingBindings
                    .createJListBinding(
                        org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                        this,
                        eLProperty,
                        lstRepresentation);
        bindingGroup.addBinding(jListBinding);

        jScrollPane2.setViewportView(lstRepresentation);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel6.add(jScrollPane2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(filler8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        pnlDataAndMetaData.add(jPanel6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        pnlDataAndMetaData.add(filler6, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceEditor.class,
                "ResourceEditor.pnlDataAndMetaData.TabConstraints.tabTitle_1"),
            pnlDataAndMetaData); // NOI18N

        pnlRelationships.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.jPanel4.border.title"))); // NOI18N
        jPanel4.setLayout(new java.awt.GridBagLayout());

        txtProvenanceRelationship.setText(org.openide.util.NbBundle.getMessage(
                ResourceEditor.class,
                "ResourceEditor.txtProvenanceRelationship.text")); // NOI18N
        txtProvenanceRelationship.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel4.add(txtProvenanceRelationship, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnEditProvenanceRelationship,
            org.openide.util.NbBundle.getMessage(
                ResourceEditor.class,
                "ResourceEditor.btnEditProvenanceRelationship.text")); // NOI18N
        btnEditProvenanceRelationship.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnEditProvenanceRelationshipActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        jPanel4.add(btnEditProvenanceRelationship, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        pnlRelationships.add(jPanel4, gridBagConstraints);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.jPanel7.border.title"))); // NOI18N
        jPanel7.setLayout(new java.awt.GridBagLayout());

        lstRelationships.setModel(new DefaultListModel());
        lstRelationships.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(lstRelationships);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(jScrollPane3, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnEditRelationship,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.btnEditRelationship.text")); // NOI18N
        btnEditRelationship.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnEditRelationshipActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        jPanel7.add(btnEditRelationship, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnCreateRealtionship,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.btnCreateRealtionship.text")); // NOI18N
        btnCreateRealtionship.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnCreateRealtionshipActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(btnCreateRealtionship, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        jPanel7.add(filler10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlRelationships.add(jPanel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        pnlRelationships.add(filler7, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceEditor.class,
                "ResourceEditor.pnlRelationships.TabConstraints.tabTitle_1"),
            pnlRelationships); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jTabbedPane1, gridBagConstraints);

        bindingGroup.bind();
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnRemoveRepresentationActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnRemoveRepresentationActionPerformed
        final CidsBean representation = (CidsBean)lstRepresentation.getSelectedValue();
        if (representation != null) {
            final List<CidsBean> representations = cidsBean.getBeanCollectionProperty("representation");
            representations.remove(representation);
        }
    }                                                                                           //GEN-LAST:event_btnRemoveRepresentationActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnAddRepresentationActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnAddRepresentationActionPerformed
        final CidsBean representation;
        try {
            representation = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "representation");
        } catch (Exception ex) {
            LOG.error("Metadata-CidsBean could not be created.");
            return;
        }

        final RepresentationEditor representationEditor = new RepresentationEditor();
        representationEditor.setCidsBean(representation);
        new ShowEditorInDialog(StaticSwingTools.getParentFrame(this),
            true,
            representationEditor).showDialog();

        // add the newly created representation-CidsBean
        cidsBean.getBeanCollectionProperty("representation").addAll(representationEditor.getNewlyAddedCidsBeans());
    } //GEN-LAST:event_btnAddRepresentationActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnEditRepresentationActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnEditRepresentationActionPerformed
        final CidsBean representation = (CidsBean)lstRepresentation.getSelectedValue();
        if (representation != null) {
            final RepresentationEditor representationEditor = new RepresentationEditor();
            representationEditor.setCidsBean(representation);
            new ShowEditorInDialog(StaticSwingTools.getParentFrame(this),
                true,
                representationEditor).showDialog();

            // replace the old cidsBean with the persisted cidsBean
            final List<CidsBean> representations = cidsBean.getBeanCollectionProperty("representation");
            representations.remove(representation);
            for (final CidsBean persistedRepresentation : representationEditor.getPersistedCidsBeans()) {
                representations.add(persistedRepresentation);
            }
        }
    } //GEN-LAST:event_btnEditRepresentationActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnEditProvenanceRelationshipActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnEditProvenanceRelationshipActionPerformed
        CidsBean relationship = provenanceRelationship;
        if (relationship == null) {
            try {
                relationship = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "relationship");
                relationship.setProperty("toresource", cidsBean);
            } catch (Exception ex) {
                LOG.error("Could not create new Relationship-CidsBean.", ex);
            }
        }

        if (relationship != null) {
            final RelationshipEditor relationshipEditor = new RelationshipEditor();
            relationshipEditor.setCidsBean(relationship);
            final ShowEditorInDialog dialog = new ShowEditorInDialog(StaticSwingTools.getParentFrame(this),
                    true,
                    relationshipEditor);
            dialog.setModal(false);
            dialog.showDialog();
            new ProvenanceRelationshipFetcherWorker().execute();
        }
    } //GEN-LAST:event_btnEditProvenanceRelationshipActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnEditRelationshipActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnEditRelationshipActionPerformed
        final CidsBean selectedRelationship = (CidsBean)lstRelationships.getSelectedValue();
        if (selectedRelationship != null) {
            final RelationshipEditor relationshipEditor = new RelationshipEditor();
            relationshipEditor.setCidsBean(selectedRelationship);
            final ShowEditorInDialog dialog = new ShowEditorInDialog(StaticSwingTools.getParentFrame(this),
                    true,
                    relationshipEditor);
            dialog.setModal(false);
            dialog.showDialog();
            new UsageRelationshipsFetcherWorker().execute();
        }
    }                                                                                       //GEN-LAST:event_btnEditRelationshipActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnCreateRealtionshipActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnCreateRealtionshipActionPerformed
        try {
            final CidsBean newCidsBean = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "relationship");
            newCidsBean.getBeanCollectionProperty("fromresources").add(cidsBean);

            final RelationshipEditor relationshipEditor = new RelationshipEditor();
            relationshipEditor.setCidsBean(newCidsBean);
            final ShowEditorInDialog dialog = new ShowEditorInDialog(StaticSwingTools.getParentFrame(this),
                    true,
                    relationshipEditor);
            dialog.setModal(false);
            dialog.showDialog();
            new UsageRelationshipsFetcherWorker().execute();
        } catch (Exception ex) {
            LOG.error("new Relationship-CidsBean could not be created.", ex);
        }
    } //GEN-LAST:event_btnCreateRealtionshipActionPerformed

    @Override
    public CidsBean getCidsBean() {
        return cidsBean;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        bindingGroup.unbind();
        if (cidsBean != null) {
            this.cidsBean = cidsBean;

            new ProvenanceRelationshipFetcherWorker().execute();
            new UsageRelationshipsFetcherWorker().execute();

            DefaultCustomObjectEditor.setMetaClassInformationToMetaClassStoreComponentsInBindingGroup(
                bindingGroup,
                this.cidsBean);
            basicPropertiesPanel.setCidsBean(cidsBean);
            additionalTagsPanel.setCidsBean(cidsBean);
            temporalInformationPanel.setCidsBean(cidsBean);
            licenseInformationPanel.setCidsBean(cidsBean);
            metaDataPanel.setCidsBean(cidsBean);
            geometryChooserPanel.setCidsBean(cidsBean);

            bindingGroup.bind();

            orderLists();
        }
    }

    @Override
    public void dispose() {
        bindingGroup.unbind();
    }

    /**
     * DOCUMENT ME!
     */
    private void orderLists() {
        ((JXListBugFixes)lstRepresentation).setAutoCreateRowSorter(true);
        ((JXListBugFixes)lstRepresentation).setSortOrder(SortOrder.DESCENDING);
        ((JXListBugFixes)lstRepresentation).toggleSortOrder();
    }

    @Override
    public String getTitle() {
        if (cidsBean != null) {
            return cidsBean.toString();
        } else {
            return "new Resource";
        }
    }

    @Override
    public void setTitle(final String title) {
    }

    /**
     * DOCUMENT ME!
     *
     * @param   args  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    public static void main(final String[] args) throws Exception {
        DevelopmentTools.createEditorInFrameFromRMIConnectionOnLocalhost(
            "SWITCHON",
            "Administratoren",
            "admin",
            "cismet",
            "resource",
            1,
            1280,
            1024);
    }

    @Override
    public void editorClosed(final EditorClosedEvent event) {
    }

    @Override
    public boolean prepareForSave() {
        return true;
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    class ProvenanceRelationshipFetcherWorker extends SwingWorker<CidsBean, Void> {

        //~ Methods ------------------------------------------------------------

        @Override
        protected CidsBean doInBackground() throws Exception {
            final MetaObjectProvenanceRelationshipSearchStatement relationshipSearchStatement =
                new MetaObjectProvenanceRelationshipSearchStatement(SessionManager.getSession().getUser(),
                    cidsBean.getPrimaryKeyValue());
            final Collection searchResults = SessionManager.getConnection()
                        .customServerSearch(SessionManager.getSession().getUser(), relationshipSearchStatement);
            if ((searchResults != null) && !searchResults.isEmpty()) {
                final ArrayList firstColumnObject = (ArrayList)searchResults.toArray(new Object[1])[0];
                final Object firstRowObject = firstColumnObject.get(0);
                return ((MetaObject)firstRowObject).getBean();
            }
            return null;
        }

        @Override
        protected void done() {
            CidsBean provenanceRelationship = null;
            try {
                provenanceRelationship = get();
            } catch (InterruptedException ex) {
                LOG.warn(ex, ex);
            } catch (ExecutionException ex) {
                LOG.warn(ex, ex);
            }

            ResourceEditor.this.provenanceRelationship = provenanceRelationship;

            if (provenanceRelationship != null) {
                txtProvenanceRelationship.setText(provenanceRelationship.toString());
            } else {
                txtProvenanceRelationship.setText("");
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    class UsageRelationshipsFetcherWorker extends SwingWorker<Collection, Void> {

        //~ Methods ------------------------------------------------------------

        @Override
        protected Collection doInBackground() throws Exception {
            final MetaObjectUsageRelationshipsSearchStatement relationshipSearchStatement =
                new MetaObjectUsageRelationshipsSearchStatement(SessionManager.getSession().getUser(),
                    cidsBean.getPrimaryKeyValue());
            final Collection searchResults = SessionManager.getConnection()
                        .customServerSearch(SessionManager.getSession().getUser(), relationshipSearchStatement);
            return searchResults;
        }

        @Override
        protected void done() {
            Collection usageRelationships = null;
            try {
                usageRelationships = get();
            } catch (InterruptedException ex) {
                LOG.warn(ex, ex);
            } catch (ExecutionException ex) {
                LOG.warn(ex, ex);
            }

            final DefaultListModel model = ((DefaultListModel)lstRelationships.getModel());
            model.clear();

            if (usageRelationships != null) {
                for (final Object mo : usageRelationships) {
                    model.addElement(((MetaObject)mo).getBean());
                }
            }
        }
    }
}
