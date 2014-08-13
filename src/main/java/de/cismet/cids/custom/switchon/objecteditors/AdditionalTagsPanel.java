/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objecteditors;

import Sirius.server.middleware.types.LightweightMetaObject;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.swing.SortOrder;

import de.cismet.cids.custom.switchon.gui.JXListBugFixes;
import de.cismet.cids.custom.switchon.gui.utils.QueryComboBox;
import de.cismet.cids.custom.switchon.gui.utils.TagsJList;
import de.cismet.cids.custom.switchon.utils.TaggroupUtils;
import de.cismet.cids.custom.switchon.utils.Taggroups;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;

import de.cismet.tools.gui.StaticSwingTools;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class AdditionalTagsPanel extends javax.swing.JPanel implements CidsBeanStore {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AdditionalTagsPanel.class);

    //~ Instance fields --------------------------------------------------------

    private CidsBean cidsBean;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRemove;
    private javax.swing.JComboBox cmbTagGroups;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstAssignedTags;
    private javax.swing.JList lstTags;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form AdditionalTagsPanel.
     */
    public AdditionalTagsPanel() {
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

        jLabel1 = new javax.swing.JLabel();
        cmbTagGroups = new QueryComboBox("SELECT t.ID,"
                        + " t.NAME"
                        + " FROM taggroup t"
                        + " ORDER BY t.name",
                false,
                "Taggroup");
        ;
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstTags = new TagsJList(Taggroups.ACCESS_CONDITIONS);
        ;
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstAssignedTags = new JXListBugFixes();
        jPanel1 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(AdditionalTagsPanel.class, "AdditionalTagsPanel.border.title"))); // NOI18N
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

        cmbTagGroups.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    cmbTagGroupsActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        add(cmbTagGroups, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel2,
            org.openide.util.NbBundle.getMessage(AdditionalTagsPanel.class, "AdditionalTagsPanel.jLabel2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        add(jLabel2, gridBagConstraints);

        jScrollPane1.setViewportView(lstTags);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        add(jScrollPane1, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel3,
            org.openide.util.NbBundle.getMessage(AdditionalTagsPanel.class, "AdditionalTagsPanel.jLabel3.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        add(jLabel3, gridBagConstraints);

        lstAssignedTags.setToolTipText(org.openide.util.NbBundle.getMessage(
                AdditionalTagsPanel.class,
                "AdditionalTagsPanel.lstAssignedTags.toolTipText")); // NOI18N

        final org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create(
                "${cidsBean.tags}");
        final org.jdesktop.swingbinding.JListBinding jListBinding = org.jdesktop.swingbinding.SwingBindings
                    .createJListBinding(
                        org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                        this,
                        eLProperty,
                        lstAssignedTags);
        bindingGroup.addBinding(jListBinding);

        jScrollPane2.setViewportView(lstAssignedTags);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        add(jScrollPane2, gridBagConstraints);

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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        add(jPanel1, gridBagConstraints);

        bindingGroup.bind();
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnAddActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnAddActionPerformed
        final List<LightweightMetaObject> selectedTags = lstTags.getSelectedValuesList();

        final List<CidsBean> tags = cidsBean.getBeanCollectionProperty("tags");
        for (final LightweightMetaObject tag : selectedTags) {
            tags.add(tag.getBean());
        }
    } //GEN-LAST:event_btnAddActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmbTagGroupsActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_cmbTagGroupsActionPerformed
        final LightweightMetaObject taggroupMo = (LightweightMetaObject)cmbTagGroups.getSelectedItem();
        final Taggroups chosenTaggroup = TaggroupUtils.getTaggroupFromString(taggroupMo.toString());
        if (chosenTaggroup != null) {
            ((TagsJList)lstTags).changeModelToTaggroup(chosenTaggroup);
        } else {
            lstTags.setModel(null);
        }
    }                                                                                //GEN-LAST:event_cmbTagGroupsActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnRemoveActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnRemoveActionPerformed
        final List<CidsBean> selectedTags = lstAssignedTags.getSelectedValuesList();

        final List<CidsBean> tags = cidsBean.getBeanCollectionProperty("tags");
        for (final CidsBean tag : selectedTags) {
            tags.remove(tag);
        }
    } //GEN-LAST:event_btnRemoveActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnNewActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnNewActionPerformed
        final TagAndTagGroupEditor tagAndTagGroupEditor = new TagAndTagGroupEditor();
        final Set<CidsBean> newlyAddedTags = new ShowEditorInDialog(StaticSwingTools.getParentFrame(this),
                true,
                tagAndTagGroupEditor).showDialog();
        ((TagsJList)lstTags).reload();

        final Collection<CidsBean> assignedTags = cidsBean.getBeanCollectionProperty("tags");
        assignedTags.addAll(newlyAddedTags);
    } //GEN-LAST:event_btnNewActionPerformed

    @Override
    public CidsBean getCidsBean() {
        return cidsBean;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        bindingGroup.unbind();
        if (cidsBean != null) {
            this.cidsBean = cidsBean;
            bindingGroup.bind();
            ((JXListBugFixes)lstAssignedTags).setAutoCreateRowSorter(true);
            ((JXListBugFixes)lstAssignedTags).setSortOrder(SortOrder.DESCENDING);
            ((JXListBugFixes)lstAssignedTags).toggleSortOrder();
        }
    }
}
