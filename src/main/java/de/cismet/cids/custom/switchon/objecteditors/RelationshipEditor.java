/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objecteditors;

import Sirius.navigator.ui.RequestsFullSizeComponent;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.custom.switchon.utils.Taggroups;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.editors.DefaultCustomObjectEditor;

import de.cismet.cids.navigator.utils.CidsBeanDropListener;
import de.cismet.cids.navigator.utils.CidsBeanDropTarget;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class RelationshipEditor extends AbstractEditorShowableInDialog implements RequestsFullSizeComponent {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(MetadataEditor.class);

    //~ Instance fields --------------------------------------------------------

    /** The single resource 'toResource' has to be put in a list, such that the binding to the table works. */
    List<CidsBean> toResourceList;
    List<CidsBean> fromResources;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.cismet.cids.custom.switchon.objecteditors.AdditionalTagsPanel additionalTagsPanel;
    private de.cismet.cids.custom.switchon.objecteditors.BasicPropertiesPanel basicPropertiesPanel;
    private javax.swing.JButton btnRemoveSourceResource;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private de.cismet.cids.custom.switchon.objecteditors.MetaDataPanel metaDataPanel;
    private de.cismet.cids.custom.switchon.objecteditors.MetaDataPanel metaDataPanel1;
    private javax.swing.JTable tblFromResource;
    private javax.swing.JTable tblToResource;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form RelationshipEditor.
     */
    public RelationshipEditor() {
        initComponents();
        new CidsBeanDropTarget(tblFromResource);
        new CidsBeanDropTarget(tblToResource);
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

        metaDataPanel1 = new de.cismet.cids.custom.switchon.objecteditors.MetaDataPanel();
        jPanel2 = new javax.swing.JPanel();
        basicPropertiesPanel = new de.cismet.cids.custom.switchon.objecteditors.BasicPropertiesPanel();
        final ArrayList<Taggroups> taggroups = new ArrayList<Taggroups>();
        taggroups.add(Taggroups.COLLECTION);
        taggroups.add(Taggroups.GEOGRAPHY);
        taggroups.add(Taggroups.HYDROLOGICAL_CONCEPT);
        taggroups.add(Taggroups.KEYWORDS_INSPIRE_THEMES_1_0);
        taggroups.add(Taggroups.KEYWORDS_OPEN);
        additionalTagsPanel = new de.cismet.cids.custom.switchon.objecteditors.AdditionalTagsPanel(taggroups);
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblToResource = new ToResourceDropListenerTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnRemoveSourceResource = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFromResource = new FromResourceDropListenerTable();
        metaDataPanel = new de.cismet.cids.custom.switchon.objecteditors.MetaDataPanel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(950, 700));
        setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        basicPropertiesPanel.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(basicPropertiesPanel, gridBagConstraints);

        additionalTagsPanel.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(additionalTagsPanel, gridBagConstraints);

        add(jPanel2);

        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(344, 332));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    RelationshipEditor.class,
                    "RelationshipEditor.jPanel4.border.title"))); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        tblToResource.setAutoCreateRowSorter(true);
        tblToResource.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${toResource}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings
                    .createJTableBinding(
                        org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                        this,
                        eLProperty,
                        tblToResource);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(
                org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${type.name}"));
        columnBinding.setColumnName("Type.name");
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(tblToResource);
        if (tblToResource.getColumnModel().getColumnCount() > 0) {
            tblToResource.getColumnModel()
                    .getColumn(0)
                    .setHeaderValue(org.openide.util.NbBundle.getMessage(
                            RelationshipEditor.class,
                            "RelationshipEditor.tblToResource.columnModel.title0_1")); // NOI18N
            tblToResource.getColumnModel()
                    .getColumn(1)
                    .setHeaderValue(org.openide.util.NbBundle.getMessage(
                            RelationshipEditor.class,
                            "RelationshipEditor.tblToResource.columnModel.title1_1")); // NOI18N
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel4.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel3.add(jPanel4, gridBagConstraints);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(0, 1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    RelationshipEditor.class,
                    "RelationshipEditor.jPanel5.border.title"))); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            btnRemoveSourceResource,
            org.openide.util.NbBundle.getMessage(
                RelationshipEditor.class,
                "RelationshipEditor.btnRemoveSourceResource.text")); // NOI18N
        btnRemoveSourceResource.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnRemoveSourceResourceActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        jPanel5.add(btnRemoveSourceResource, gridBagConstraints);

        tblFromResource.setAutoCreateRowSorter(true);
        tblFromResource.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${fromResources}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                eLProperty,
                tblFromResource);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${type.name}"));
        columnBinding.setColumnName("Type.name");
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tblFromResource);
        if (tblFromResource.getColumnModel().getColumnCount() > 0) {
            tblFromResource.getColumnModel()
                    .getColumn(0)
                    .setHeaderValue(org.openide.util.NbBundle.getMessage(
                            RelationshipEditor.class,
                            "RelationshipEditor.tblFromResource.columnModel.title0_1")); // NOI18N
            tblFromResource.getColumnModel()
                    .getColumn(1)
                    .setHeaderValue(org.openide.util.NbBundle.getMessage(
                            RelationshipEditor.class,
                            "RelationshipEditor.tblFromResource.columnModel.title1_1")); // NOI18N
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        jPanel5.add(jScrollPane1, gridBagConstraints);

        jPanel1.add(jPanel5);

        metaDataPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    RelationshipEditor.class,
                    "RelationshipEditor.metaDataPanel.border.title"))); // NOI18N
        metaDataPanel.setOpaque(false);
        jPanel1.add(metaDataPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        jPanel3.add(jPanel1, gridBagConstraints);

        add(jPanel3);

        bindingGroup.bind();
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnRemoveSourceResourceActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnRemoveSourceResourceActionPerformed
        final int selectedRow = tblFromResource.getSelectedRow();
        if (selectedRow != -1) {
            final CidsBean selectedResource = fromResources.get(tblFromResource.convertRowIndexToModel(
                        selectedRow));
            fromResources.remove(selectedResource);
        }
    }                                                                                           //GEN-LAST:event_btnRemoveSourceResourceActionPerformed

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
            metaDataPanel.setCidsBean(cidsBean);

            toResourceList = new ArrayList<CidsBean>(1);
            toResourceList.add((CidsBean)cidsBean.getProperty("toresource"));

            fromResources = cidsBean.getBeanCollectionProperty("fromresources");

            bindingGroup.bind();

            if (tblToResource.getColumnModel().getColumnCount() > 0) {
                tblToResource.getColumnModel()
                        .getColumn(0)
                        .setHeaderValue(org.openide.util.NbBundle.getMessage(
                                RelationshipEditor.class,
                                "RelationshipEditor.jTable1.columnModel.title0_1")); // NOI18N
                tblToResource.getColumnModel()
                        .getColumn(1)
                        .setHeaderValue(org.openide.util.NbBundle.getMessage(
                                RelationshipEditor.class,
                                "RelationshipEditor.jTable1.columnModel.title1_1")); // NOI18N
            }

            if (tblFromResource.getColumnModel().getColumnCount() > 0) {
                tblFromResource.getColumnModel()
                        .getColumn(0)
                        .setHeaderValue(org.openide.util.NbBundle.getMessage(
                                RelationshipEditor.class,
                                "RelationshipEditor.tblFromResource.columnModel.title0_1")); // NOI18N
                tblFromResource.getColumnModel()
                        .getColumn(1)
                        .setHeaderValue(org.openide.util.NbBundle.getMessage(
                                RelationshipEditor.class,
                                "RelationshipEditor.tblFromResource.columnModel.title1_1")); // NOI18N
            }
        }
    }

    @Override
    public void dispose() {
        bindingGroup.unbind();
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
            "relationship",
            1,
            1280,
            1024);
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public List<CidsBean> getToResource() {
        return toResourceList;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  toResource  DOCUMENT ME!
     */
    public void setToResource(final List<CidsBean> toResource) {
        this.toResourceList = toResource;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public List<CidsBean> getFromResources() {
        return fromResources;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  fromResources  DOCUMENT ME!
     */
    public void setFromResources(final List<CidsBean> fromResources) {
        this.fromResources = fromResources;
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private class FromResourceDropListenerTable extends JTable implements CidsBeanDropListener {

        //~ Methods ------------------------------------------------------------

        @Override
        public void beansDropped(final ArrayList<CidsBean> beans) {
            if (beans != null) {
                for (final CidsBean bean : beans) {
                    if (bean.getClass().getSimpleName().equalsIgnoreCase("resource")) {
                        fromResources.add(bean);
                    }
                }
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private class ToResourceDropListenerTable extends JTable implements CidsBeanDropListener {

        //~ Methods ------------------------------------------------------------

        @Override
        public void beansDropped(final ArrayList<CidsBean> beans) {
            if (beans != null) {
                for (final CidsBean bean : beans) {
                    // use the first match
                    if (bean.getClass().getSimpleName().equalsIgnoreCase("resource")) {
                        try {
                            cidsBean.setProperty("toresource", bean);
                            toResourceList.clear();
                            toResourceList.add(bean);
                            break;
                        } catch (Exception ex) {
                            LOG.error("Could not set toresource of cidsBean", ex);
                        }
                    }
                }
            }
        }
    }
}
