/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objecteditors;

import Sirius.server.middleware.types.MetaObject;

import org.apache.commons.lang.StringUtils;

import org.jfree.util.Log;

import java.awt.Component;

import java.util.HashSet;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.custom.switchon.gui.utils.QueryComboBox;
import de.cismet.cids.custom.switchon.gui.utils.QueryJList;
import de.cismet.cids.custom.switchon.gui.utils.TagsJList;
import de.cismet.cids.custom.switchon.utils.ActionTagUtils;
import de.cismet.cids.custom.switchon.utils.TaggroupUtils;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.editors.EditorClosedEvent;
import de.cismet.cids.editors.EditorSaveListener;

import de.cismet.cids.tools.metaobjectrenderer.CidsBeanRenderer;

/**
 * The TagAndTagGroupEditor is a unusual CidsBean-Editor as it allows to create and modify more than one CidsBean.
 * Furthermore the CidsBeans can be of the type Tag or TagGroup. To achieve this the editor can not act as a usual
 * editor and has to persist the changed CidsBeans himself.
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class TagAndTagGroupEditor extends javax.swing.JPanel implements EditorSaveListener,
    CidsBeanRenderer,
    EditorShowableInDialog {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(TagAndTagGroupEditor.class);
    private static final String ACTION_TAG = "custom.tag.admin@SWITCHON";

    //~ Instance fields --------------------------------------------------------

    /**
     * The CidsBean which is set and get by NavigatorAttributeEditorGui, although it will be persist by this class and
     * not by NavigatorAttributeEditorGui.
     */
    private CidsBean dummyCidsBean;

    private CidsBean selectedTagGroup;
    private CidsBean selectedTag;
    private final HashSet<CidsBean> modifiedBeans = new HashSet<CidsBean>();
    private final HashSet<CidsBean> deletedBeans = new HashSet<CidsBean>();
    private HashSet<CidsBean> newlyAddedTags = new HashSet<CidsBean>();
    private HashSet<CidsBean> persistedCidsBeans = new HashSet<CidsBean>();
    private boolean hasActionTag = false;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGroupDelete;
    private javax.swing.JButton btnGroupEdit;
    private javax.swing.JButton btnGroupNew;
    private javax.swing.JButton btnTagDelete;
    private javax.swing.JButton btnTagEdit;
    private javax.swing.JButton btnTagNew;
    private javax.swing.JComboBox cmbTagGroups;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList lstTagGroups;
    private javax.swing.JList lstTags;
    private javax.swing.JPanel pnlTagGroups;
    private javax.swing.JPanel pnlTags;
    private javax.swing.JTextField txtGroupName;
    private javax.swing.JTextField txtTagName;
    private javax.swing.JTextArea txtaGroupDescrption;
    private javax.swing.JTextArea txtaTagDescription;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form TagAndTagGroupEditor.
     */
    public TagAndTagGroupEditor() {
        try {
            this.dummyCidsBean = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "tag");
        } catch (Exception ex) {
            LOG.error("Could not create new Tag-CidsBean.", ex);
        }
        try {
            hasActionTag = ActionTagUtils.checkActionTag(ACTION_TAG);
        } catch (Exception ex) {
            LOG.error("The value of the action tag can not be checked.", ex);
        }
        initComponents();
        if (cmbTagGroups.getModel().getSize() > 0) {
            cmbTagGroups.setSelectedIndex(0);
        }

        btnGroupDelete.setEnabled(hasActionTag);
        btnGroupEdit.setEnabled(hasActionTag);
        btnGroupNew.setEnabled(hasActionTag);
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

        pnlTagGroups = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstTagGroups = new QueryJList("SELECT t.ID,"
                        + "       t.NAME"
                        + " FROM taggroup t"
                        + " ORDER BY t.name",
                "Taggroup");
        jPanel3 = new javax.swing.JPanel();
        btnGroupNew = new javax.swing.JButton();
        btnGroupEdit = new javax.swing.JButton();
        btnGroupDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtGroupName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaGroupDescrption = new javax.swing.JTextArea();
        pnlTags = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTagName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtaTagDescription = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstTags = new TagsJList();
        ;
        jLabel5 = new javax.swing.JLabel();
        cmbTagGroups = new QueryComboBox("SELECT t.ID," + "       t.NAME" + " FROM taggroup t" + " ORDER BY t.name",
                false,
                "taggroup");
        jPanel4 = new javax.swing.JPanel();
        btnTagNew = new javax.swing.JButton();
        btnTagEdit = new javax.swing.JButton();
        btnTagDelete = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(32767, 0));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));

        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());

        pnlTagGroups.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    TagAndTagGroupEditor.class,
                    "TagAndTagGroupEditor.pnlTagGroups.border.title"))); // NOI18N
        pnlTagGroups.setOpaque(false);
        pnlTagGroups.setLayout(new java.awt.GridBagLayout());

        lstTagGroups.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstTagGroups.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

                @Override
                public void valueChanged(final javax.swing.event.ListSelectionEvent evt) {
                    lstTagGroupsValueChanged(evt);
                }
            });
        jScrollPane1.setViewportView(lstTagGroups);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 11, 5, 5);
        pnlTagGroups.add(jScrollPane1, gridBagConstraints);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            btnGroupNew,
            org.openide.util.NbBundle.getMessage(TagAndTagGroupEditor.class, "TagAndTagGroupEditor.btnGroupNew.text")); // NOI18N
        btnGroupNew.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnGroupNewActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnGroupNew, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnGroupEdit,
            org.openide.util.NbBundle.getMessage(TagAndTagGroupEditor.class, "TagAndTagGroupEditor.btnGroupEdit.text")); // NOI18N
        btnGroupEdit.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnGroupEditActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnGroupEdit, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnGroupDelete,
            org.openide.util.NbBundle.getMessage(
                TagAndTagGroupEditor.class,
                "TagAndTagGroupEditor.btnGroupDelete.text")); // NOI18N
        btnGroupDelete.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnGroupDeleteActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnGroupDelete, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlTagGroups.add(jPanel3, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel1,
            org.openide.util.NbBundle.getMessage(TagAndTagGroupEditor.class, "TagAndTagGroupEditor.jLabel1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 10);
        pnlTagGroups.add(jLabel1, gridBagConstraints);

        txtGroupName.setEnabled(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${selectedTagGroup.name}"),
                txtGroupName,
                org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        pnlTagGroups.add(txtGroupName, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel2,
            org.openide.util.NbBundle.getMessage(TagAndTagGroupEditor.class, "TagAndTagGroupEditor.jLabel2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 10);
        pnlTagGroups.add(jLabel2, gridBagConstraints);

        txtaGroupDescrption.setColumns(20);
        txtaGroupDescrption.setLineWrap(true);
        txtaGroupDescrption.setRows(5);
        txtaGroupDescrption.setWrapStyleWord(true);
        txtaGroupDescrption.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${selectedTagGroup.description}"),
                txtaGroupDescrption,
                org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        jScrollPane2.setViewportView(txtaGroupDescrption);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        pnlTagGroups.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(pnlTagGroups, gridBagConstraints);

        pnlTags.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    TagAndTagGroupEditor.class,
                    "TagAndTagGroupEditor.pnlTags.border.title"))); // NOI18N
        pnlTags.setOpaque(false);
        pnlTags.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel3,
            org.openide.util.NbBundle.getMessage(TagAndTagGroupEditor.class, "TagAndTagGroupEditor.jLabel3.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 10);
        pnlTags.add(jLabel3, gridBagConstraints);

        txtTagName.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${selectedTag.name}"),
                txtTagName,
                org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        pnlTags.add(txtTagName, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel4,
            org.openide.util.NbBundle.getMessage(TagAndTagGroupEditor.class, "TagAndTagGroupEditor.jLabel4.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 10);
        pnlTags.add(jLabel4, gridBagConstraints);

        txtaTagDescription.setColumns(20);
        txtaTagDescription.setLineWrap(true);
        txtaTagDescription.setRows(5);
        txtaTagDescription.setWrapStyleWord(true);
        txtaTagDescription.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${selectedTag.description}"),
                txtaTagDescription,
                org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        jScrollPane3.setViewportView(txtaTagDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 10);
        pnlTags.add(jScrollPane3, gridBagConstraints);

        lstTags.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstTags.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

                @Override
                public void valueChanged(final javax.swing.event.ListSelectionEvent evt) {
                    lstTagsValueChanged(evt);
                }
            });
        jScrollPane4.setViewportView(lstTags);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        pnlTags.add(jScrollPane4, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel5,
            org.openide.util.NbBundle.getMessage(TagAndTagGroupEditor.class, "TagAndTagGroupEditor.jLabel5.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 5);
        pnlTags.add(jLabel5, gridBagConstraints);

        cmbTagGroups.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    cmbTagGroupsActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        pnlTags.add(cmbTagGroups, gridBagConstraints);

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            btnTagNew,
            org.openide.util.NbBundle.getMessage(TagAndTagGroupEditor.class, "TagAndTagGroupEditor.btnTagNew.text")); // NOI18N
        btnTagNew.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnTagNewActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnTagNew, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnTagEdit,
            org.openide.util.NbBundle.getMessage(TagAndTagGroupEditor.class, "TagAndTagGroupEditor.btnTagEdit.text")); // NOI18N
        btnTagEdit.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnTagEditActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnTagEdit, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnTagDelete,
            org.openide.util.NbBundle.getMessage(TagAndTagGroupEditor.class, "TagAndTagGroupEditor.btnTagDelete.text")); // NOI18N
        btnTagDelete.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnTagDeleteActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnTagDelete, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        pnlTags.add(jPanel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        pnlTags.add(filler1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(pnlTags, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        add(filler2, gridBagConstraints);

        bindingGroup.bind();
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void lstTagGroupsValueChanged(final javax.swing.event.ListSelectionEvent evt) { //GEN-FIRST:event_lstTagGroupsValueChanged
        if (!evt.getValueIsAdjusting()) {
            final MetaObject selectedMO = (MetaObject)lstTagGroups.getSelectedValue();
            CidsBean selectedTagGroup = null;
            if (selectedMO != null) {
                selectedTagGroup = selectedMO.getBean();
            }
            bindingGroup.unbind();
            this.selectedTagGroup = selectedTagGroup;
            bindingGroup.bind();
            txtGroupName.setEnabled(false);
            txtaGroupDescrption.setEnabled(false);
        }
    }                                                                                       //GEN-LAST:event_lstTagGroupsValueChanged

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void lstTagsValueChanged(final javax.swing.event.ListSelectionEvent evt) { //GEN-FIRST:event_lstTagsValueChanged
        if (!evt.getValueIsAdjusting()) {
            final MetaObject selectedMO = (MetaObject)lstTags.getSelectedValue();
            CidsBean selectedTag = null;
            if (selectedMO != null) {
                selectedTag = selectedMO.getBean();
            }
            bindingGroup.unbind();
            this.selectedTag = selectedTag;
            bindingGroup.bind();
            txtTagName.setEnabled(false);
            txtaTagDescription.setEnabled(false);

            final boolean isNewlyAdded = newlyAddedTags.contains(selectedTag);
            btnTagDelete.setEnabled(hasActionTag || isNewlyAdded);
            btnTagEdit.setEnabled(hasActionTag || isNewlyAdded);
        }
    } //GEN-LAST:event_lstTagsValueChanged

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmbTagGroupsActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_cmbTagGroupsActionPerformed
        final MetaObject selectedMO = ((MetaObject)cmbTagGroups.getSelectedItem());
        if (selectedMO != null) {
            ((TagsJList)lstTags).changeModelToTaggroup(selectedMO.getID());

            btnTagDelete.setEnabled(hasActionTag);
            btnTagEdit.setEnabled(hasActionTag);
            btnTagNew.setEnabled(hasActionTag || TaggroupUtils.isTaggroupOpen(selectedMO.getName()));
        }
    } //GEN-LAST:event_cmbTagGroupsActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnGroupEditActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnGroupEditActionPerformed
        txtGroupName.setEnabled(true);
        txtaGroupDescrption.setEnabled(true);
        modifiedBeans.add(selectedTagGroup);
        dummyCidsBean.setArtificialChangeFlag(true);
    }                                                                                //GEN-LAST:event_btnGroupEditActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnTagEditActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnTagEditActionPerformed
        txtTagName.setEnabled(true);
        txtaTagDescription.setEnabled(true);
        modifiedBeans.add(selectedTag);
        dummyCidsBean.setArtificialChangeFlag(true);
    }                                                                              //GEN-LAST:event_btnTagEditActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnGroupDeleteActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnGroupDeleteActionPerformed
        if (selectedTagGroup != null) {
            deletedBeans.add(selectedTagGroup);
            modifiedBeans.remove(selectedTagGroup);

            cmbTagGroups.removeItem(selectedTagGroup.getMetaObject());
            ((DefaultListModel)lstTagGroups.getModel()).removeElement(selectedTagGroup.getMetaObject());
            lstTagGroups.getSelectionModel().clearSelection();
            dummyCidsBean.setArtificialChangeFlag(true);
        }
    } //GEN-LAST:event_btnGroupDeleteActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnTagDeleteActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnTagDeleteActionPerformed
        if (selectedTag != null) {
            deletedBeans.add(selectedTag);
            newlyAddedTags.remove(selectedTag);
            modifiedBeans.remove(selectedTag);

            ((DefaultListModel)lstTags.getModel()).removeElement(selectedTag.getMetaObject());
            lstTags.getSelectionModel().clearSelection();
            dummyCidsBean.setArtificialChangeFlag(true);
        }
    } //GEN-LAST:event_btnTagDeleteActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnGroupNewActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnGroupNewActionPerformed
        try {
            final CidsBean newTaggroupBean = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "taggroup");
            newTaggroupBean.setProperty("name", "New Taggroup");
            newTaggroupBean.setProperty("description", "No description provided.");

            modifiedBeans.add(newTaggroupBean);
            final DefaultListModel model = ((DefaultListModel)lstTagGroups.getModel());
            final MetaObject mo = newTaggroupBean.getMetaObject();
            model.addElement(mo);
            final int index = model.indexOf(mo);
            lstTagGroups.setSelectedIndex(index);
            lstTagGroups.ensureIndexIsVisible(index);
            btnGroupEdit.doClick();
            dummyCidsBean.setArtificialChangeFlag(true);
        } catch (Exception ex) {
            LOG.error("new Taggroup CidsBean could not be created.", ex);
        }
    } //GEN-LAST:event_btnGroupNewActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnTagNewActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnTagNewActionPerformed
        try {
            final CidsBean newTagBean = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "tag");
            newTagBean.setProperty("name", "New Tag");
            newTagBean.setProperty("description", "No description provided.");
            newTagBean.setProperty("taggroup", ((MetaObject)cmbTagGroups.getSelectedItem()).getBean());

            modifiedBeans.add(newTagBean);
            newlyAddedTags.add(newTagBean);
            final DefaultListModel model = ((DefaultListModel)lstTags.getModel());
            final MetaObject mo = newTagBean.getMetaObject();
            model.addElement(mo);
            final int index = model.indexOf(mo);
            lstTags.setSelectedIndex(index);
            lstTags.ensureIndexIsVisible(index);
            btnTagEdit.doClick();
            dummyCidsBean.setArtificialChangeFlag(true);
        } catch (Exception ex) {
            LOG.error("new Tag CidsBean could not be created.", ex);
        }
    } //GEN-LAST:event_btnTagNewActionPerformed

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
            "tag",
            2,
            1280,
            1024);
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public CidsBean getSelectedTagGroup() {
        return selectedTagGroup;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  selectedTagGroup  DOCUMENT ME!
     */
    public void setSelectedTagGroup(final CidsBean selectedTagGroup) {
        this.selectedTagGroup = selectedTagGroup;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public CidsBean getSelectedTag() {
        return selectedTag;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  selectedTag  DOCUMENT ME!
     */
    public void setSelectedTag(final CidsBean selectedTag) {
        this.selectedTag = selectedTag;
    }

    @Override
    public void editorClosed(final EditorClosedEvent event) {
    }

    /**
     * Saves the modified cidsBeans. The newly added cidsbeans will also be persisted and the persisted cidsbeans will
     * be saved in an own set. After the persist that set will replace the <code>newlyAddedTags</code>-set.
     */
    @Override
    public void saveChanges() {
        final HashSet<CidsBean> newlyAddedTagsAfterPersist = new HashSet<CidsBean>(newlyAddedTags.size());
        for (final CidsBean modifiedBean : modifiedBeans) {
            try {
                final CidsBean persistedBean = modifiedBean.persist();
                persistedCidsBeans.add(persistedBean);
                if (newlyAddedTags.contains(modifiedBean)) {
                    newlyAddedTagsAfterPersist.add(persistedBean);
                }
            } catch (Exception ex) {
                Log.error(ex.getMessage(), ex);
            }
        }
        newlyAddedTags = newlyAddedTagsAfterPersist;

        for (final CidsBean beanToDelete : deletedBeans) {
            try {
                beanToDelete.delete();
                beanToDelete.persist();
            } catch (Exception ex) {
                Log.error(ex.getMessage(), ex);
            }
        }

        JOptionPane.showMessageDialog(
            this,
            "The changed tags and taggroups were saved.",
            "Changes saved",
            JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public boolean prepareForSave() {
        boolean save = true;
        for (final CidsBean modifiedBean : modifiedBeans) {
            if (StringUtils.isBlank(modifiedBean.toString())) {
                JOptionPane.showMessageDialog(
                    this,
                    "Can not save. The name of one object is blank.",
                    "Blank name",
                    JOptionPane.WARNING_MESSAGE);
                save = false;
                break;
            }
        }

        if (save) {
            saveChanges();
        }

        return false;
    }

    @Override
    public CidsBean getCidsBean() {
        return dummyCidsBean;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        if (cidsBean != null) {
            dummyCidsBean = cidsBean;
        }
    }

    @Override
    public void dispose() {
        bindingGroup.unbind();
    }

    @Override
    public String getTitle() {
        return "Tag and Tag Group";
    }

    @Override
    public void setTitle(final String title) {
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    @Override
    public HashSet<CidsBean> getNewlyAddedCidsBeans() {
        return newlyAddedTags;
    }

    @Override
    public HashSet<CidsBean> getModifiedCidsBeans() {
        return persistedCidsBeans;
    }

    @Override
    public Component getComponent() {
        return this;
    }
}
