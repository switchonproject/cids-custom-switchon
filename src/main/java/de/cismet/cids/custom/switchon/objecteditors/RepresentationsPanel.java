/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objecteditors;

import java.util.HashSet;
import java.util.List;

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
public class RepresentationsPanel extends javax.swing.JPanel implements CidsBeanStore, Disposable {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(MetadataEditor.class);

    //~ Instance fields --------------------------------------------------------

    private CidsBean cidsBean;
    private List<CidsBean> representations;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddRepresentation;
    private javax.swing.JButton btnEditRepresentation;
    private javax.swing.JButton btnRemoveRepresentation;
    private javax.swing.Box.Filler filler8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRepresentations;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form RepresentationsPanel.
     */
    public RepresentationsPanel() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();
        initComponents();

        final org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create(
                "${representations}");
        final org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings
                    .createJTableBinding(
                        org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ,
                        this,
                        eLProperty,
                        tblRepresentations);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(
                org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${type.name}"));
        columnBinding.setColumnName("Type");
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create(
                    "${contenttype.name}"));
        columnBinding.setColumnName("Contenttype");
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${function.name}"));
        columnBinding.setColumnName("Function");
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

        btnAddRepresentation = new javax.swing.JButton();
        btnRemoveRepresentation = new javax.swing.JButton();
        btnEditRepresentation = new javax.swing.JButton();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRepresentations = new javax.swing.JTable();

        setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            btnAddRepresentation,
            org.openide.util.NbBundle.getMessage(
                RepresentationsPanel.class,
                "RepresentationsPanel.btnAddRepresentation.text")); // NOI18N
        btnAddRepresentation.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnAddRepresentationActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 5);
        add(btnAddRepresentation, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnRemoveRepresentation,
            org.openide.util.NbBundle.getMessage(
                RepresentationsPanel.class,
                "RepresentationsPanel.btnRemoveRepresentation.text")); // NOI18N
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
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        add(btnRemoveRepresentation, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnEditRepresentation,
            org.openide.util.NbBundle.getMessage(
                RepresentationsPanel.class,
                "RepresentationsPanel.btnEditRepresentation.text")); // NOI18N
        btnEditRepresentation.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnEditRepresentationActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        add(btnEditRepresentation, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 0.5;
        add(filler8, gridBagConstraints);

        tblRepresentations.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblRepresentations);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        add(jScrollPane1, gridBagConstraints);
    } // </editor-fold>//GEN-END:initComponents

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
            representationEditor).showDialog();

        // add the newly created representation-CidsBean
        representations.addAll(representationEditor.getNewlyAddedCidsBeans());
    } //GEN-LAST:event_btnAddRepresentationActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnRemoveRepresentationActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnRemoveRepresentationActionPerformed
        final int selectedRow = tblRepresentations.getSelectedRow();
        if (selectedRow != -1) {
            final CidsBean selectedRepresentation = representations.get(tblRepresentations.convertRowIndexToModel(
                        selectedRow));
            representations.remove(selectedRepresentation);
        }
    }                                                                                           //GEN-LAST:event_btnRemoveRepresentationActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnEditRepresentationActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnEditRepresentationActionPerformed
        final int selectedRow = tblRepresentations.getSelectedRow();
        if (selectedRow != -1) {
            final CidsBean selectedRepresentation = representations.get(tblRepresentations.convertRowIndexToModel(
                        selectedRow));
            final RepresentationEditor representationEditor = new RepresentationEditor();
            representationEditor.setCidsBean(selectedRepresentation);
            new ShowEditorInDialog(StaticSwingTools.getParentFrame(this),
                representationEditor).showDialog();

            // replace the old cidsBean with the persisted cidsBean
            final HashSet<CidsBean> persistedCidsBeans = representationEditor.getPersistedCidsBeans();
            if (!persistedCidsBeans.isEmpty()) {
                // only one cidsBean can be returned
                representations.remove(selectedRepresentation);
                representations.add(persistedCidsBeans.iterator().next());
            }
        }
    } //GEN-LAST:event_btnEditRepresentationActionPerformed

    @Override
    public CidsBean getCidsBean() {
        return cidsBean;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        bindingGroup.unbind();
        if (cidsBean != null) {
            this.cidsBean = cidsBean;
            this.representations = cidsBean.getBeanCollectionProperty("representation");

            bindingGroup.bind();
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public List<CidsBean> getRepresentations() {
        return representations;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  representations  DOCUMENT ME!
     */
    public void setRepresentations(final List<CidsBean> representations) {
        this.representations = representations;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public CidsBean getSelectedRepresentation() {
        final int selectedRow = tblRepresentations.getSelectedRow();
        if (selectedRow != -1) {
            final CidsBean selectedRepresentation = representations.get(tblRepresentations.convertRowIndexToModel(
                        selectedRow));
            return selectedRepresentation;
        } else {
            return null;
        }
    }

    @Override
    public void dispose() {
        bindingGroup.unbind();
    }
}
