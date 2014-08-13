/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objecteditors;

import Sirius.navigator.ui.RequestsFullSizeComponent;

import java.util.List;

import javax.swing.SortOrder;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.custom.switchon.gui.JXListBugFixes;
import de.cismet.cids.custom.switchon.gui.utils.FastBindableReferenceComboFactory;
import de.cismet.cids.custom.switchon.utils.Taggroups;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.editors.DefaultCustomObjectEditor;
import de.cismet.cids.editors.EditorClosedEvent;
import de.cismet.cids.editors.EditorSaveListener;
import de.cismet.cids.editors.FastBindableReferenceCombo;

import de.cismet.cids.tools.metaobjectrenderer.CidsBeanRenderer;

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.cismet.cids.custom.switchon.objecteditors.AdditionalTagsPanel additionalTagsPanel;
    private de.cismet.cids.custom.switchon.objecteditors.BasicPropertiesPanel basicPropertiesPanel;
    private javax.swing.JButton btnAddMetaData;
    private javax.swing.JButton btnAddRepresentation;
    private javax.swing.JButton btnEditMetaData;
    private javax.swing.JButton btnEditRepresentation;
    private javax.swing.JButton btnRemoveMetaData;
    private javax.swing.JButton btnRemoveRepresentation;
    private javax.swing.JComboBox cmbTopic;
    private de.cismet.cids.custom.switchon.objecteditors.ContactEditor contactEditor;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler8;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private de.cismet.cids.custom.switchon.objecteditors.LicenseInformationPanel licenseInformationPanel;
    private javax.swing.JList lstMetaData;
    private javax.swing.JList lstRepresentation;
    private javax.swing.JPanel pnlBasicProperties;
    private javax.swing.JPanel pnlContact;
    private javax.swing.JPanel pnlDataAndMetaData;
    private javax.swing.JPanel pnlGeographicProperties;
    private javax.swing.JPanel pnlLicenseInformation;
    private javax.swing.JPanel pnlMap;
    private javax.swing.JPanel pnlTagsAndCategory;
    private javax.swing.JPanel pnlTemporalInformation;
    private de.cismet.cids.custom.switchon.objecteditors.TemporalInformationPanel temporalInformationPanel;
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
        additionalTagsPanel = new de.cismet.cids.custom.switchon.objecteditors.AdditionalTagsPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        pnlContact = new javax.swing.JPanel();
        contactEditor = new de.cismet.cids.custom.switchon.objecteditors.ContactEditor();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        pnlGeographicProperties = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        pnlMap = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
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
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstMetaData = new JXListBugFixes();
        btnAddMetaData = new javax.swing.JButton();
        btnRemoveMetaData = new javax.swing.JButton();
        btnEditMetaData = new javax.swing.JButton();
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

        pnlGeographicProperties.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.jPanel2.border.title"))); // NOI18N
        jPanel2.setLayout(null);

        org.openide.awt.Mnemonics.setLocalizedText(
            jButton1,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.jButton1.text")); // NOI18N
        jPanel2.add(jButton1);
        jButton1.setBounds(410, 30, 94, 25);

        org.openide.awt.Mnemonics.setLocalizedText(
            jButton2,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.jButton2.text")); // NOI18N
        jPanel2.add(jButton2);
        jButton2.setBounds(410, 80, 94, 25);
        jPanel2.add(pnlMap);
        pnlMap.setBounds(10, 30, 380, 190);

        pnlGeographicProperties.add(jPanel2);
        jPanel2.setBounds(20, 20, 520, 250);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.jPanel3.border.title"))); // NOI18N
        jPanel3.setLayout(null);
        pnlGeographicProperties.add(jPanel3);
        jPanel3.setBounds(30, 280, 510, 210);

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

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.jPanel5.border.title"))); // NOI18N
        jPanel5.setLayout(new java.awt.GridBagLayout());

        lstMetaData.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create(
                "${cidsBean.metadata}");
        org.jdesktop.swingbinding.JListBinding jListBinding = org.jdesktop.swingbinding.SwingBindings
                    .createJListBinding(
                        org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                        this,
                        eLProperty,
                        lstMetaData);
        bindingGroup.addBinding(jListBinding);

        jScrollPane1.setViewportView(lstMetaData);

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
        jPanel5.add(jScrollPane1, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnAddMetaData,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.btnAddMetaData.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        jPanel5.add(btnAddMetaData, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnRemoveMetaData,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.btnRemoveMetaData.text")); // NOI18N
        btnRemoveMetaData.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnRemoveMetaDataActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        jPanel5.add(btnRemoveMetaData, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnEditMetaData,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.btnEditMetaData.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(btnEditMetaData, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        pnlDataAndMetaData.add(jPanel5, gridBagConstraints);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.jPanel6.border.title"))); // NOI18N
        jPanel6.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            btnAddRepresentation,
            org.openide.util.NbBundle.getMessage(ResourceEditor.class, "ResourceEditor.btnAddRepresentation.text")); // NOI18N
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel6.add(btnEditRepresentation, gridBagConstraints);

        lstRepresentation.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${cidsBean.representation}");
        jListBinding = org.jdesktop.swingbinding.SwingBindings.createJListBinding(
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
    private void btnRemoveMetaDataActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnRemoveMetaDataActionPerformed
        final CidsBean metaData = (CidsBean)lstMetaData.getSelectedValue();
        if (metaData != null) {
            final List<CidsBean> metaDatas = cidsBean.getBeanCollectionProperty("metadata");
            metaDatas.remove(metaData);
        }
    }                                                                                     //GEN-LAST:event_btnRemoveMetaDataActionPerformed

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
            basicPropertiesPanel.setCidsBean(cidsBean);
            additionalTagsPanel.setCidsBean(cidsBean);
            temporalInformationPanel.setCidsBean(cidsBean);
            licenseInformationPanel.setCidsBean(cidsBean);

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
        ((JXListBugFixes)lstMetaData).setAutoCreateRowSorter(true);
        ((JXListBugFixes)lstMetaData).setSortOrder(SortOrder.DESCENDING);
        ((JXListBugFixes)lstMetaData).toggleSortOrder();
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
}
