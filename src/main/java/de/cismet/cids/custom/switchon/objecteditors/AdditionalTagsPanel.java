/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objecteditors;

import Sirius.server.middleware.types.LightweightMetaObject;
import Sirius.server.middleware.types.MetaObject;

import org.openide.util.NbBundle;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import de.cismet.cids.custom.switchon.gui.InfoProviderJPanel;
import de.cismet.cids.custom.switchon.gui.utils.QueryComboBox;
import de.cismet.cids.custom.switchon.gui.utils.RendererTools;
import de.cismet.cids.custom.switchon.gui.utils.TagsJList;
import de.cismet.cids.custom.switchon.utils.TagUtils;
import de.cismet.cids.custom.switchon.utils.TaggroupUtils;
import de.cismet.cids.custom.switchon.utils.Taggroups;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;
import de.cismet.cids.dynamics.Disposable;

import de.cismet.tools.gui.StaticSwingTools;

/**
 * AdditionalTagsPanel allows it to choose a taggroup and assign tags from that to a cidsBean. It makes it also possible
 * to create new tags of that taggroup. The selectable taggroups have to be determined in the constructor. Furthermore
 * the GUI can be branded via the NbBundle, this means that the JLabels are different depending on the use of the GUI.
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class AdditionalTagsPanel extends InfoProviderJPanel implements CidsBeanStore, Disposable {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AdditionalTagsPanel.class);
    public static final String BRANDING_NONE = null;
    public static final String BRANDING_KEYWORDS = "keywords";

    //~ Instance fields --------------------------------------------------------

    public final String branding;

    private CidsBean cidsBean;
    /** The query for the combobox containing the Taggroups. */
    private final String tagGroupQuery;
    private List<CidsBean> assignedTags = new ArrayList<CidsBean>();
    private List<Taggroups> allowedTaggroups;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRemove;
    private javax.swing.JComboBox cmbTagGroups;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList lstTags;
    private javax.swing.JTable tblAssignedTags;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new AdditionalTagsPanel object.
     */
    public AdditionalTagsPanel() {
        this(null, BRANDING_NONE);
    }

    /**
     * Creates a new AdditionalTagsPanel object.
     *
     * @param  allowedTaggroups  DOCUMENT ME!
     */
    public AdditionalTagsPanel(final List<Taggroups> allowedTaggroups) {
        this(allowedTaggroups, BRANDING_NONE);
    }

    /**
     * Creates new form AdditionalTagsPanel.
     *
     * @param  allowedTaggroups  DOCUMENT ME!
     * @param  branding          DOCUMENT ME!
     */
    public AdditionalTagsPanel(final List<Taggroups> allowedTaggroups, final String branding) {
        this.branding = branding;
        this.allowedTaggroups = allowedTaggroups;

        String tagGroupQuery = "SELECT t.ID,"
                    + " t.NAME"
                    + " FROM taggroup t";

        if ((allowedTaggroups == null) || allowedTaggroups.isEmpty()) {
            tagGroupQuery += " WHERE FALSE ";
        } else {
            tagGroupQuery += " WHERE t.name ilike '" + allowedTaggroups.get(0).getValue() + "' ";
            for (int i = 1; i < allowedTaggroups.size(); i++) {
                tagGroupQuery += " OR t.name ilike '" + allowedTaggroups.get(i).getValue() + "' ";
            }
        }

        tagGroupQuery += " ORDER BY t.name";
        LOG.debug("tagGroupQuery created: " + tagGroupQuery);
        this.tagGroupQuery = tagGroupQuery;
        NbBundle.setBranding(branding);
        initComponents();
        NbBundle.setBranding(null);
        if ((allowedTaggroups == null) || allowedTaggroups.isEmpty()) {
            cmbTagGroups.setEnabled(false);
            lstTags.setEnabled(false);
            btnAdd.setEnabled(false);
            btnNew.setEnabled(false);
            btnRemove.setEnabled(false);
        } else {
            changeListModelToSelectedTaggroup();
        }

        tblAssignedTags.getSelectionModel().addListSelectionListener(
            new javax.swing.event.ListSelectionListener() {

                @Override
                public void valueChanged(final ListSelectionEvent evt) {
                    customRowSelectionEventHandler(evt);
                }
            });
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstTags = new TagsJList();
        ;
        cmbTagGroups = new QueryComboBox(tagGroupQuery, false, "Taggroup");
        ;
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAssignedTags = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(AdditionalTagsPanel.class, "AdditionalTagsPanel.border.title"))); // NOI18N
        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel1,
            org.openide.util.NbBundle.getMessage(AdditionalTagsPanel.class, "AdditionalTagsPanel.jLabel1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
        add(jLabel1, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel2,
            org.openide.util.NbBundle.getMessage(AdditionalTagsPanel.class, "AdditionalTagsPanel.jLabel2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        add(jLabel2, gridBagConstraints);

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        lstTags.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

                @Override
                public void valueChanged(final javax.swing.event.ListSelectionEvent evt) {
                    lstTagsValueChanged(evt);
                }
            });
        jScrollPane1.setViewportView(lstTags);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        cmbTagGroups.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    cmbTagGroupsActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        jPanel3.add(cmbTagGroups, gridBagConstraints);

        jPanel4.add(jPanel3);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel3,
            org.openide.util.NbBundle.getMessage(AdditionalTagsPanel.class, "AdditionalTagsPanel.jLabel3.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            btnAdd,
            org.openide.util.NbBundle.getMessage(AdditionalTagsPanel.class, "AdditionalTagsPanel.btnAdd.text")); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnAddActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(btnAdd, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnNew,
            org.openide.util.NbBundle.getMessage(AdditionalTagsPanel.class, "AdditionalTagsPanel.btnNew.text")); // NOI18N
        btnNew.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnNewActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(btnNew, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnRemove,
            org.openide.util.NbBundle.getMessage(AdditionalTagsPanel.class, "AdditionalTagsPanel.btnRemove.text")); // NOI18N
        btnRemove.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnRemoveActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(btnRemove, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel2.add(jPanel1, gridBagConstraints);

        jScrollPane3.setMinimumSize(new java.awt.Dimension(200, 22));

        final org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create(
                "${assignedTags}");
        final org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings
                    .createJTableBinding(
                        org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                        this,
                        eLProperty,
                        tblAssignedTags);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(
                org.jdesktop.beansbinding.ELProperty.create("${taggroup}"));
        columnBinding.setColumnName("Taggroup");
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane3.setViewportView(tblAssignedTags);
        if (tblAssignedTags.getColumnModel().getColumnCount() > 0) {
            tblAssignedTags.getColumnModel()
                    .getColumn(0)
                    .setHeaderValue(org.openide.util.NbBundle.getMessage(
                            AdditionalTagsPanel.class,
                            "AdditionalTagsPanel.tblAssignedTags.columnModel.title0_2")); // NOI18N
            tblAssignedTags.getColumnModel()
                    .getColumn(1)
                    .setHeaderValue(org.openide.util.NbBundle.getMessage(
                            AdditionalTagsPanel.class,
                            "AdditionalTagsPanel.tblAssignedTags.columnModel.title1_2")); // NOI18N
        }
        tblAssignedTags.setAutoCreateRowSorter(true);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel2.add(jScrollPane3, gridBagConstraints);

        jPanel4.add(jPanel2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel4, gridBagConstraints);

        bindingGroup.bind();
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnAddActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        final List<LightweightMetaObject> selectedTags = lstTags.getSelectedValuesList();

        final List<CidsBean> tags = cidsBean.getBeanCollectionProperty("tags");
        for (final LightweightMetaObject tag : selectedTags) {
            tags.add(tag.getBean());
        }
    }//GEN-LAST:event_btnAddActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmbTagGroupsActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTagGroupsActionPerformed
        changeListModelToSelectedTaggroup();

        final String description = TagUtils.getDescriptionOfTag(cmbTagGroups.getSelectedItem());
        provideInformation(description);

        btnNew.setEnabled(TaggroupUtils.isTaggroupOpen(cmbTagGroups.getSelectedItem().toString()));
    }//GEN-LAST:event_cmbTagGroupsActionPerformed

    /**
     * DOCUMENT ME!
     */
    private void changeListModelToSelectedTaggroup() {
        final LightweightMetaObject taggroupMo = (LightweightMetaObject)cmbTagGroups.getSelectedItem();
        Taggroups chosenTaggroup = null;
        if (taggroupMo != null) {
            chosenTaggroup = TaggroupUtils.getTaggroupFromString(taggroupMo.toString());
        }

        if (chosenTaggroup != null) {
            ((TagsJList)lstTags).changeModelToTaggroup(chosenTaggroup);
        } else {
            lstTags.setModel(new DefaultListModel());
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnRemoveActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        final List<CidsBean> selectedTags = new ArrayList<CidsBean>(tblAssignedTags.getSelectedRowCount());

        for (final int viewIndex : tblAssignedTags.getSelectedRows()) {
            final int modelIndex = tblAssignedTags.convertRowIndexToModel(viewIndex);
            selectedTags.add(assignedTags.get(modelIndex));
        }

        assignedTags.removeAll(selectedTags);
    }//GEN-LAST:event_btnRemoveActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnNewActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        try {
            final CidsBean selectedTaggroup = ((MetaObject)cmbTagGroups.getSelectedItem()).getBean();
            final SimpleTagEditor simpleTagEditor = new SimpleTagEditor(selectedTaggroup);
            simpleTagEditor.setCidsBean(CidsBean.createNewCidsBeanFromTableName("SWITCHON", "tag"));
            final ShowEditorInDialog dialog = new ShowEditorInDialog(StaticSwingTools.getParentFrame(this),
                    simpleTagEditor);
            dialog.setTitle(selectedTaggroup.toString());
            dialog.showDialog();

            ((TagsJList)lstTags).reload();
            assignedTags.addAll(simpleTagEditor.getNewlyAddedCidsBeans());
        } catch (Exception ex) {
            LOG.error("Could not create new tag-CidsBean", ex);
        }
    }//GEN-LAST:event_btnNewActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void customRowSelectionEventHandler(final javax.swing.event.ListSelectionEvent evt) {
        final int rowIndexView = tblAssignedTags.getSelectedRow();
        if (rowIndexView >= 0) {
            final int rowIndexModel = tblAssignedTags.convertRowIndexToModel(rowIndexView);

            final String description = TagUtils.getDescriptionOfTag(assignedTags.get(rowIndexModel));
            provideInformation(description);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void lstTagsValueChanged(final javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstTagsValueChanged
        final String description = TagUtils.getDescriptionOfTag(lstTags.getSelectedValue());
        provideInformation(description);
    }//GEN-LAST:event_lstTagsValueChanged

    @Override
    public CidsBean getCidsBean() {
        return cidsBean;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        bindingGroup.unbind();
        if (cidsBean != null) {
            this.cidsBean = cidsBean;
            assignedTags = cidsBean.getBeanCollectionProperty("tags");
            bindingGroup.bind();

            // after the binding the column titles and the row filter have to be set again, as they will be overwritten
            // otherwise set column titles
            NbBundle.setBranding(branding);
            if (tblAssignedTags.getColumnModel().getColumnCount() > 0) {
                tblAssignedTags.getColumnModel()
                        .getColumn(0)
                        .setHeaderValue(org.openide.util.NbBundle.getMessage(
                                AdditionalTagsPanel.class,
                                "AdditionalTagsPanel.tblAssignedTags.columnModel.title0_2")); // NOI18N
                tblAssignedTags.getColumnModel()
                        .getColumn(1)
                        .setHeaderValue(org.openide.util.NbBundle.getMessage(
                                AdditionalTagsPanel.class,
                                "AdditionalTagsPanel.tblAssignedTags.columnModel.title1_2")); // NOI18N
            }
            NbBundle.setBranding(null);

            // set row filter, to only show those tags from the allowed taggroups
            final RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {

                    @Override
                    public boolean include(final RowFilter.Entry entry) {
                        final CidsBean taggroup = (CidsBean)entry.getValue(0);
                        final String taggroupName = (String)taggroup.getProperty("name");
                        boolean allowed = false;
                        for (final Taggroups allowedTaggroup : allowedTaggroups) {
                            if (allowedTaggroup.getValue().equalsIgnoreCase(taggroupName)) {
                                allowed = true;
                                break;
                            }
                        }
                        return allowed;
                    }
                };

            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tblAssignedTags.getModel());
            sorter.setRowFilter(filter);
            tblAssignedTags.setRowSorter(sorter);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public List<CidsBean> getAssignedTags() {
        return assignedTags;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  assignedTags  DOCUMENT ME!
     */
    public void setAssignedTags(final List<CidsBean> assignedTags) {
        this.assignedTags = assignedTags;
    }

    @Override
    public void dispose() {
        bindingGroup.unbind();
    }

    /**
     * DOCUMENT ME!
     */
    public void makeNonEditable() {
        RendererTools.makeReadOnly(btnAdd);
        RendererTools.makeReadOnly(btnNew);
        RendererTools.makeReadOnly(btnRemove);
        RendererTools.makeReadOnly(cmbTagGroups);
        RendererTools.makeReadOnly(lstTags);
        RendererTools.makeReadOnly(tblAssignedTags);
    }
}
