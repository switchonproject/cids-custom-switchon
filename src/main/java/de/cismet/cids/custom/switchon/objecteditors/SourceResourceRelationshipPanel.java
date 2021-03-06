/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objecteditors;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;

import de.cismet.cids.custom.switchon.gui.InfoProviderJPanel;
import de.cismet.cids.custom.switchon.gui.utils.RendererTools;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;
import de.cismet.cids.dynamics.Disposable;

import de.cismet.cids.navigator.utils.CidsBeanDropListener;
import de.cismet.cids.navigator.utils.CidsBeanDropTarget;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class SourceResourceRelationshipPanel extends InfoProviderJPanel implements CidsBeanStore, Disposable {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            SourceResourceRelationshipPanel.class);

    //~ Instance fields --------------------------------------------------------

    private List<CidsBean> fromResources;
    private CidsBean cidsBean;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRemoveSourceResource;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSourceResourceDragIcon;
    private javax.swing.JTable tblFromResource;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form SourceResourceRelationshipPanel.
     */
    public SourceResourceRelationshipPanel() {
        initComponents();
        try {
            new CidsBeanDropTarget(tblFromResource);
            new CidsBeanDropTarget(lblSourceResourceDragIcon);
        } catch (Exception ex) {
            LOG.fatal(ex);
        }
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

        btnRemoveSourceResource = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFromResource = new FromResourceDropListenerTable();
        lblSourceResourceDragIcon = new FromResourceDropListenerLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    SourceResourceRelationshipPanel.class,
                    "SourceResourceRelationshipPanel.border.title"))); // NOI18N
        setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            btnRemoveSourceResource,
            org.openide.util.NbBundle.getMessage(
                SourceResourceRelationshipPanel.class,
                "SourceResourceRelationshipPanel.btnRemoveSourceResource.text")); // NOI18N
        btnRemoveSourceResource.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnRemoveSourceResourceActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        add(btnRemoveSourceResource, gridBagConstraints);

        tblFromResource.setAutoCreateRowSorter(true);
        tblFromResource.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        final org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create(
                "${fromResources}");
        final org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings
                    .createJTableBinding(
                        org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                        this,
                        eLProperty,
                        tblFromResource);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(
                org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${type.name}"));
        columnBinding.setColumnName("Type.name");
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        tblFromResource.addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(final java.awt.event.MouseEvent evt) {
                    tblFromResourceMouseClicked(evt);
                }
            });
        jScrollPane1.setViewportView(tblFromResource);
        if (tblFromResource.getColumnModel().getColumnCount() > 0) {
            tblFromResource.getColumnModel()
                    .getColumn(0)
                    .setHeaderValue(org.openide.util.NbBundle.getMessage(
                            SourceResourceRelationshipPanel.class,
                            "SourceResourceRelationshipPanel.tblFromResource.columnModel.title0_1")); // NOI18N
            tblFromResource.getColumnModel()
                    .getColumn(1)
                    .setHeaderValue(org.openide.util.NbBundle.getMessage(
                            SourceResourceRelationshipPanel.class,
                            "SourceResourceRelationshipPanel.tblFromResource.columnModel.title1_1")); // NOI18N
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        add(jScrollPane1, gridBagConstraints);

        lblSourceResourceDragIcon.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/de/cismet/cids/custom/switchon/document_import.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(
            lblSourceResourceDragIcon,
            org.openide.util.NbBundle.getMessage(
                SourceResourceRelationshipPanel.class,
                "SourceResourceRelationshipPanel.lblSourceResourceDragIcon.text"));              // NOI18N
        lblSourceResourceDragIcon.setToolTipText(org.openide.util.NbBundle.getMessage(
                SourceResourceRelationshipPanel.class,
                "SourceResourceRelationshipPanel.lblSourceResourceDragIcon.toolTipText"));       // NOI18N
        lblSourceResourceDragIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        add(lblSourceResourceDragIcon, gridBagConstraints);

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

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void tblFromResourceMouseClicked(final java.awt.event.MouseEvent evt) { //GEN-FIRST:event_tblFromResourceMouseClicked
        provideInformation(org.openide.util.NbBundle.getMessage(
                SourceResourceRelationshipPanel.class,
                "SourceResourceRelationshipPanel.tblFromResource.info"));
    }                                                                               //GEN-LAST:event_tblFromResourceMouseClicked

    @Override
    public CidsBean getCidsBean() {
        return cidsBean;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        bindingGroup.unbind();
        if (cidsBean != null) {
            this.cidsBean = cidsBean;
            fromResources = cidsBean.getBeanCollectionProperty("fromresources"); // NOI18N

            bindingGroup.bind();
            if (tblFromResource.getColumnModel().getColumnCount() > 0) {
                tblFromResource.getColumnModel()
                        .getColumn(0)
                        .setHeaderValue(org.openide.util.NbBundle.getMessage(
                                RelationshipEditor.class,
                                "SourceResourceRelationshipPanel.tblFromResource.columnModel.title0_1")); // NOI18N
                tblFromResource.getColumnModel()
                        .getColumn(1)
                        .setHeaderValue(org.openide.util.NbBundle.getMessage(
                                RelationshipEditor.class,
                                "SourceResourceRelationshipPanel.tblFromResource.columnModel.title1_1")); // NOI18N
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

    /**
     * DOCUMENT ME!
     *
     * @param  beans  DOCUMENT ME!
     */
    private void addBeansToFromResource(final ArrayList<CidsBean> beans) {
        if (beans != null) {
            for (final CidsBean bean : beans) {
                if (bean.getClass().getSimpleName().equalsIgnoreCase("resource")) { // NOI18N
                    if (!fromResources.contains(bean)) {
                        fromResources.add(bean);
                    }
                }
            }
            tblFromResource.requestFocus();
        }
    }

    /**
     * DOCUMENT ME!
     */
    public void makeNonEditable() {
        RendererTools.makeReadOnly(tblFromResource);
        btnRemoveSourceResource.setVisible(false);
        lblSourceResourceDragIcon.setVisible(false);
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
            addBeansToFromResource(beans);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private class FromResourceDropListenerLabel extends JLabel implements CidsBeanDropListener {

        //~ Methods ------------------------------------------------------------

        @Override
        public void beansDropped(final ArrayList<CidsBean> beans) {
            addBeansToFromResource(beans);
        }
    }
}
