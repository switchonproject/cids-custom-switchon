/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objecteditors;

import java.awt.event.ActionListener;

import java.util.HashSet;
import java.util.List;

import javax.swing.JButton;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import de.cismet.cids.custom.switchon.gui.utils.RendererTools;
import de.cismet.cids.custom.switchon.objectrenderer.MetadataRenderer;
import de.cismet.cids.custom.switchon.wizards.DefaultPropertySetter;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;
import de.cismet.cids.dynamics.Disposable;

import de.cismet.tools.gui.StaticSwingTools;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class MetaDataPanel extends javax.swing.JPanel implements CidsBeanStore, Disposable {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(MetadataEditor.class);

    //~ Instance fields --------------------------------------------------------

    private CidsBean cidsBean;
    private List<CidsBean> metadatas;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMetaData;
    private javax.swing.JButton btnEditMetaData;
    private javax.swing.JButton btnRemoveMetaData;
    private javax.swing.JButton btnSeeDetails;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblMetaDatas;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form MetaDataPanel.
     */
    public MetaDataPanel() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();
        initComponents();
        tblMetaDatas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(final ListSelectionEvent e) {
                    final boolean oneOrMoreSelected = tblMetaDatas.getSelectedRowCount() > 0;
                    btnEditMetaData.setEnabled(oneOrMoreSelected);
                    btnRemoveMetaData.setEnabled(oneOrMoreSelected);
                }
            });

        final org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create(
                "${metadatas}");
        final org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings
                    .createJTableBinding(
                        org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                        this,
                        eLProperty,
                        tblMetaDatas);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(
                org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${type.name}"));
        columnBinding.setColumnName("Type");
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${contact}"));
        columnBinding.setColumnName("Contact");
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${content}"));
        columnBinding.setColumnName("Document");
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
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

        btnAddMetaData = new javax.swing.JButton();
        btnRemoveMetaData = new javax.swing.JButton();
        btnEditMetaData = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMetaDatas = new javax.swing.JTable();
        btnSeeDetails = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            btnAddMetaData,
            org.openide.util.NbBundle.getMessage(MetaDataPanel.class, "MetaDataPanel.btnAddMetaData.text")); // NOI18N
        btnAddMetaData.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnAddMetaDataActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 5);
        add(btnAddMetaData, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnRemoveMetaData,
            org.openide.util.NbBundle.getMessage(MetaDataPanel.class, "MetaDataPanel.btnRemoveMetaData.text")); // NOI18N
        btnRemoveMetaData.setEnabled(false);
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
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        add(btnRemoveMetaData, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnEditMetaData,
            org.openide.util.NbBundle.getMessage(MetaDataPanel.class, "MetaDataPanel.btnEditMetaData.text")); // NOI18N
        btnEditMetaData.setEnabled(false);
        btnEditMetaData.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnEditMetaDataActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        add(btnEditMetaData, gridBagConstraints);

        tblMetaDatas.setAutoCreateRowSorter(true);
        tblMetaDatas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] {}));
        tblMetaDatas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tblMetaDatas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        add(jScrollPane2, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnSeeDetails,
            org.openide.util.NbBundle.getMessage(MetaDataPanel.class, "MetaDataPanel.btnSeeDetails.text")); // NOI18N
        btnSeeDetails.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnSeeDetailsActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 5);
        add(btnSeeDetails, gridBagConstraints);
        btnSeeDetails.setVisible(false);
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnAddMetaDataActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnAddMetaDataActionPerformed
        final CidsBean metaData;
        try {
            metaData = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "metadata");
        } catch (Exception ex) {
            LOG.error("Metadata-CidsBean could not be created.");
            return;
        }

        final MetadataEditor metadataEditor = new MetadataEditor();
        metadataEditor.setCidsBean(metaData);
        new ShowEditorInDialog(StaticSwingTools.getParentFrame(this),
            metadataEditor).showDialog();

        // add the newly created metaData-CidsBean
        metadatas.addAll(metadataEditor.getNewlyAddedCidsBeans());
    } //GEN-LAST:event_btnAddMetaDataActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnRemoveMetaDataActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnRemoveMetaDataActionPerformed
        final int selectedRow = tblMetaDatas.getSelectedRow();
        if (selectedRow != -1) {
            final CidsBean selectedMetaData = metadatas.get(tblMetaDatas.convertRowIndexToModel(
                        selectedRow));
            metadatas.remove(selectedMetaData);
        }
    }                                                                                     //GEN-LAST:event_btnRemoveMetaDataActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnEditMetaDataActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnEditMetaDataActionPerformed
        final int selectedRow = tblMetaDatas.getSelectedRow();
        if (selectedRow != -1) {
            final CidsBean selectedMetaData = metadatas.get(tblMetaDatas.convertRowIndexToModel(
                        selectedRow));
            final MetadataEditor metadataEditor = new MetadataEditor();
            metadataEditor.setCidsBean(selectedMetaData);
            new ShowEditorInDialog(StaticSwingTools.getParentFrame(this),
                metadataEditor).showDialog();

            // replace the old cidsBean with the persisted cidsBean
            final HashSet<CidsBean> persistedCidsBeans = metadataEditor.getModifiedCidsBeans();
            if (!persistedCidsBeans.isEmpty()) {
                // only one cidsBean can be returned
                metadatas.remove(selectedMetaData);
                metadatas.add(persistedCidsBeans.iterator().next());
            }
        }
    } //GEN-LAST:event_btnEditMetaDataActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnSeeDetailsActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnSeeDetailsActionPerformed
        final int selectedRow = tblMetaDatas.getSelectedRow();
        if (selectedRow != -1) {
            final CidsBean selectedMetaData = metadatas.get(tblMetaDatas.convertRowIndexToModel(
                        selectedRow));
            final MetadataRenderer metadataRenderer = new MetadataRenderer();
            metadataRenderer.setCidsBean(selectedMetaData);
            new ShowEditorInDialog(StaticSwingTools.getParentFrame(this),
                metadataRenderer).showDialog();
        }
    }                                                                                 //GEN-LAST:event_btnSeeDetailsActionPerformed

    @Override
    public CidsBean getCidsBean() {
        return cidsBean;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        bindingGroup.unbind();
        if (cidsBean != null) {
            this.cidsBean = cidsBean;
            this.metadatas = cidsBean.getBeanCollectionProperty("metadata");

            bindingGroup.bind();
            if (tblMetaDatas.getColumnModel().getColumnCount() > 0) {
                tblMetaDatas.getColumnModel().getColumn(2).setCellRenderer(new NullCellRenderer());
                tblMetaDatas.getColumnModel().getColumn(3).setCellRenderer(new NullCellRenderer());
            }

            // set filter to hide standard meta data
            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tblMetaDatas.getModel());
            tblMetaDatas.setRowSorter(sorter);

            sorter.setRowFilter(
                new RowFilter<TableModel, Integer>() {

                    @Override
                    public boolean include(final RowFilter.Entry<? extends TableModel, ? extends Integer> entry) {
                        return !DefaultPropertySetter.isStandardMetaData(metadatas.get(entry.getIdentifier()));
                    }
                });
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public List<CidsBean> getMetadatas() {
        return metadatas;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  metadatas  DOCUMENT ME!
     */
    public void setMetadatas(final List<CidsBean> metadatas) {
        this.metadatas = metadatas;
    }

    @Override
    public void dispose() {
        bindingGroup.unbind();
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public CidsBean getSelectedMetaData() {
        final int selectedRow = tblMetaDatas.getSelectedRow();
        if (selectedRow != -1) {
            final CidsBean selectedRepresentation = metadatas.get(tblMetaDatas.convertRowIndexToModel(
                        selectedRow));
            return selectedRepresentation;
        } else {
            return null;
        }
    }

    /**
     * Removes all actionListeners from the Add-button and adds the parameter actionListener.
     *
     * @param  actionListener  The ActionListener which will be added to the button.
     */
    public void replaceActionListenerOfAddButton(final ActionListener actionListener) {
        removeActionListeners(btnAddMetaData);
        btnAddMetaData.addActionListener(actionListener);
    }

    /**
     * Removes all actionListeners from the Edit-button and adds the parameter actionListener.
     *
     * @param  actionListener  The ActionListener which will be added to the button.
     */
    public void replaceActionListenerOfEditButton(final ActionListener actionListener) {
        removeActionListeners(btnEditMetaData);
        btnEditMetaData.addActionListener(actionListener);
    }

    /**
     * Remove all ActionListeners from a button.
     *
     * @param  button  DOCUMENT ME!
     */
    private void removeActionListeners(final JButton button) {
        for (final ActionListener l : button.getActionListeners()) {
            button.removeActionListener(l);
        }
    }

    /**
     * Clear the selection of the table with the MetaDatas.
     */
    public void clearTableSelection() {
        tblMetaDatas.clearSelection();
    }

    /**
     * DOCUMENT ME!
     */
    public void makeNonEditable() {
        btnAddMetaData.setVisible(false);
        btnEditMetaData.setVisible(false);
        btnRemoveMetaData.setVisible(false);
        btnSeeDetails.setVisible(true);
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * Print no if the value is null, otherwise yes.
     *
     * @version  $Revision$, $Date$
     */
    private class NullCellRenderer extends DefaultTableCellRenderer {

        //~ Methods ------------------------------------------------------------

        @Override
        protected void setValue(final Object value) {
            if (value == null) {
                this.setText("No");
            } else {
                this.setText("Yes");
            }
        }
    }
}
