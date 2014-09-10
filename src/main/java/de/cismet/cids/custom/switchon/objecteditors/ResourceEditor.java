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
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;

import de.cismet.cids.client.tools.DevelopmentTools;

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
    private de.cismet.cids.custom.switchon.objecteditors.BasicPropertiesPanel basicPropertiesPanel;
    private javax.swing.JButton btnAddLocation;
    private javax.swing.JButton btnAddSrid;
    private javax.swing.JButton btnCreateRealtionship;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEditProvenanceRelationship;
    private javax.swing.JButton btnEditRelationship;
    private javax.swing.JButton btnNewContact;
    private javax.swing.JComboBox cmbContact;
    private javax.swing.JComboBox cmbLocation;
    private javax.swing.JComboBox cmbSrid;
    private de.cismet.cids.custom.switchon.objecteditors.ContactEditor contactEditor;
    private de.cismet.cids.custom.switchon.gui.utils.CreateNewTagAction createNewLocation;
    private de.cismet.cids.custom.switchon.gui.utils.CreateNewTagAction createNewSrid;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler7;
    private de.cismet.cids.custom.switchon.gui.GeometryChooserPanel geometryChooserPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private de.cismet.cids.custom.switchon.objecteditors.LicenseInformationPanel licenseInformationPanel;
    private javax.swing.JList lstRelationships;
    private de.cismet.cids.custom.switchon.objecteditors.MetaDataPanel metaDataPanel;
    private javax.swing.JPanel pnlBasicProperties;
    private javax.swing.JPanel pnlContact;
    private javax.swing.JPanel pnlGeographicProperties;
    private javax.swing.JPanel pnlGeography;
    private javax.swing.JPanel pnlLicenseInformation;
    private javax.swing.JPanel pnlMetaData;
    private javax.swing.JPanel pnlOtherProperties;
    private javax.swing.JPanel pnlRelationships;
    private javax.swing.JPanel pnlRepresentations;
    private javax.swing.JPanel pnlTagsAndCategory;
    private javax.swing.JPanel pnlTemporalInformation;
    private de.cismet.cids.custom.switchon.objecteditors.RepresentationsPanel representationsPanel;
    private de.cismet.cids.custom.switchon.objecteditors.TemporalInformationPanel temporalInformationPanel;
    private de.cismet.cids.custom.switchon.objecteditors.TopicCollectionAdditionalTagsPanel
        topicCollectionAdditionalTagsPanel;
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

        createNewSrid = new de.cismet.cids.custom.switchon.gui.utils.CreateNewTagAction();
        createNewLocation = new de.cismet.cids.custom.switchon.gui.utils.CreateNewTagAction();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlBasicProperties = new javax.swing.JPanel();
        basicPropertiesPanel = new BasicPropertiesPanel(Taggroups.RESOURCE_TYPE);
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        pnlTagsAndCategory = new javax.swing.JPanel();
        topicCollectionAdditionalTagsPanel =
            new de.cismet.cids.custom.switchon.objecteditors.TopicCollectionAdditionalTagsPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        pnlContact = new javax.swing.JPanel();
        contactEditor = new de.cismet.cids.custom.switchon.objecteditors.ContactEditor();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        jPanel2 = new javax.swing.JPanel();
        btnNewContact = new javax.swing.JButton();
        cmbContact = new FastBindableReferenceCombo("%1$2s", new String[] { "organisation" });
        btnEdit = new javax.swing.JButton();
        pnlGeographicProperties = new javax.swing.JPanel();
        pnlGeography = new javax.swing.JPanel();
        geometryChooserPanel = new de.cismet.cids.custom.switchon.gui.GeometryChooserPanel();
        pnlOtherProperties = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbSrid = FastBindableReferenceComboFactory.createTagsFastBindableReferenceComboBox(Taggroups.SRID);
        btnAddSrid = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cmbLocation = FastBindableReferenceComboFactory.createTagsFastBindableReferenceComboBox(Taggroups.LOCATION);
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
        pnlMetaData = new javax.swing.JPanel();
        metaDataPanel = new de.cismet.cids.custom.switchon.objecteditors.MetaDataPanel();
        pnlRepresentations = new javax.swing.JPanel();
        representationsPanel = new de.cismet.cids.custom.switchon.objecteditors.RepresentationsPanel();
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlTagsAndCategory.add(topicCollectionAdditionalTagsPanel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 0.5;
        pnlTagsAndCategory.add(filler2, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceEditor.class,
                "ResourceEditor.pnlTagsAndCategory.TabConstraints.tabTitle"),
            pnlTagsAndCategory); // NOI18N

        pnlContact.setLayout(new java.awt.GridBagLayout());

        contactEditor.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    ResourceEditor.class,
                    "ResourceEditor.contactEditor.border.title"))); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.contact}"),
                contactEditor,
                org.jdesktop.beansbinding.BeanProperty.create("cidsBean"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        pnlContact.add(contactEditor, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        pnlContact.add(filler3, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.jPanel2.border.title"))); // NOI18N
        jPanel2.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            btnNewContact,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.btnNewContact.text")); // NOI18N
        btnNewContact.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnNewContactActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        jPanel2.add(btnNewContact, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.contact}"),
                cmbContact,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        cmbContact.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    cmbContactActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(cmbContact, gridBagConstraints);
        ((FastBindableReferenceCombo)cmbContact).setSorted(true);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnEdit,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.btnEdit.text")); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnEditActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        jPanel2.add(btnEdit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        pnlContact.add(jPanel2, gridBagConstraints);

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
                cmbSrid,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        pnlOtherProperties.add(cmbSrid, gridBagConstraints);

        createNewSrid.setCombo((FastBindableReferenceCombo)cmbSrid);
        btnAddSrid.setAction(createNewSrid);
        org.openide.awt.Mnemonics.setLocalizedText(
            btnAddSrid,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.btnAddSrid.text")); // NOI18N
        btnAddSrid.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnAddSridActionPerformed(evt);
                }
            });
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
                cmbLocation,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        pnlOtherProperties.add(cmbLocation, gridBagConstraints);

        createNewLocation.setCombo((FastBindableReferenceCombo)cmbLocation);
        btnAddLocation.setAction(createNewLocation);
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

        pnlMetaData.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlMetaData.add(metaDataPanel, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceEditor.class,
                "ResourceEditor.pnlMetaData.TabConstraints.tabTitle_1"),
            pnlMetaData); // NOI18N

        pnlRepresentations.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlRepresentations.add(representationsPanel, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceEditor.class,
                "ResourceEditor.pnlRepresentations.TabConstraints.tabTitle"),
            pnlRepresentations); // NOI18N

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
            final NonModalShowEditorInDialog dialog = new NonModalShowEditorInDialog(StaticSwingTools.getParentFrame(
                        this),
                    relationshipEditor);
            dialog.addListener(new NonModalShowEditorInDialog.ChangesSavedListener() {

                    @Override
                    public void changesWereSaved() {
                        new ProvenanceRelationshipFetcherWorker().execute();
                    }
                });
            dialog.showDialog();
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
            final NonModalShowEditorInDialog dialog = new NonModalShowEditorInDialog(StaticSwingTools.getParentFrame(
                        this),
                    relationshipEditor);
            dialog.addListener(new NonModalShowEditorInDialog.ChangesSavedListener() {

                    @Override
                    public void changesWereSaved() {
                        new UsageRelationshipsFetcherWorker().execute();
                    }
                });
            dialog.showDialog();
        }
    } //GEN-LAST:event_btnEditRelationshipActionPerformed

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
            final NonModalShowEditorInDialog dialog = new NonModalShowEditorInDialog(StaticSwingTools.getParentFrame(
                        this),
                    relationshipEditor);
            dialog.addListener(new NonModalShowEditorInDialog.ChangesSavedListener() {

                    @Override
                    public void changesWereSaved() {
                        new UsageRelationshipsFetcherWorker().execute();
                    }
                });
            dialog.showDialog();
        } catch (Exception ex) {
            LOG.error("new Relationship-CidsBean could not be created.", ex);
        }
    } //GEN-LAST:event_btnCreateRealtionshipActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnAddSridActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnAddSridActionPerformed
    }                                                                              //GEN-LAST:event_btnAddSridActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnNewContactActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnNewContactActionPerformed
        final CidsBean contact;
        try {
            contact = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "contact");
        } catch (Exception ex) {
            LOG.error("Contact cidsBean could not be created.", ex);
            return;
        }

        final ContactEditor contactEditor = new ContactEditor(true);
        contactEditor.setCidsBean(contact);
        final ShowEditorInDialog dialog = new ShowEditorInDialog(StaticSwingTools.getParentFrame(this),
                contactEditor);
        dialog.setTitle(contactEditor.getTitle());
        dialog.showDialog();

        // contactEditor.getPersistedCidsBeans().size() should be 0 or 1
        for (final CidsBean persistedContact : contactEditor.getPersistedCidsBeans()) {
            try {
                cidsBean.setProperty("contact", persistedContact);
            } catch (Exception ex) {
                LOG.error("Property contact can not be set.", ex);
            }
        }
        ((FastBindableReferenceCombo)cmbContact).refreshModel();
    } //GEN-LAST:event_btnNewContactActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnEditActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnEditActionPerformed
        contactEditor.setEnabled(true);
    }                                                                           //GEN-LAST:event_btnEditActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmbContactActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_cmbContactActionPerformed
        contactEditor.setEnabled(false);
    }                                                                              //GEN-LAST:event_cmbContactActionPerformed

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
            topicCollectionAdditionalTagsPanel.setCidsBean(cidsBean);
            temporalInformationPanel.setCidsBean(cidsBean);
            licenseInformationPanel.setCidsBean(cidsBean);
            metaDataPanel.setCidsBean(cidsBean);
            geometryChooserPanel.setCidsBean(cidsBean);
            representationsPanel.setCidsBean(cidsBean);

            bindingGroup.bind();
        }
    }

    @Override
    public void dispose() {
        bindingGroup.unbind();
        topicCollectionAdditionalTagsPanel.dispose();
        geometryChooserPanel.dispose();
        temporalInformationPanel.dispose();
        metaDataPanel.dispose();
        basicPropertiesPanel.dispose();
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
