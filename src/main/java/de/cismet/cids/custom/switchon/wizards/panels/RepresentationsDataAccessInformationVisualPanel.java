/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import de.cismet.cids.custom.switchon.gui.MarkMandtoryFieldsStrong;
import de.cismet.cids.custom.switchon.gui.MarkMandtoryFieldsStrongUtils;
import de.cismet.cids.custom.switchon.gui.utils.FastBindableReferenceComboFactory;
import de.cismet.cids.custom.switchon.utils.TagUtils;
import de.cismet.cids.custom.switchon.utils.Taggroups;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;
import de.cismet.cids.dynamics.Disposable;

import de.cismet.cids.editors.DefaultCustomObjectEditor;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class RepresentationsDataAccessInformationVisualPanel extends javax.swing.JPanel implements CidsBeanStore,
    Disposable,
    MarkMandtoryFieldsStrong {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            RepresentationsDataAccessInformationVisualPanel.class);

    //~ Instance fields --------------------------------------------------------

    private CidsBean representation;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbApplication;
    private javax.swing.JComboBox cmbContentType;
    private javax.swing.JComboBox cmbFunction;
    private javax.swing.JComboBox cmbProtocol;
    private javax.swing.Box.Filler filler1;
    private de.cismet.cids.custom.switchon.wizards.WizardInfoBoxPanel infoBoxPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblApplication;
    private javax.swing.JLabel lblContentLocation;
    private javax.swing.JLabel lblContentType;
    private javax.swing.JLabel lblFunction;
    private javax.swing.JLabel lblProtocol;
    private javax.swing.JTextField txtContentLocation;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form RepresentationsDataAccessInformationVisualPanel.
     */
    public RepresentationsDataAccessInformationVisualPanel() {
        initComponents();

        // make the application components, till they are really needed
        lblApplication.setVisible(false);
        cmbApplication.setVisible(false);
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

        infoBoxPanel = new de.cismet.cids.custom.switchon.wizards.WizardInfoBoxPanel();
        jPanel1 = new javax.swing.JPanel();
        lblContentLocation = new javax.swing.JLabel();
        txtContentLocation = new javax.swing.JTextField();
        lblFunction = new javax.swing.JLabel();
        cmbFunction = FastBindableReferenceComboFactory.createTagsFastBindableReferenceComboBox(Taggroups.FUNCTION);
        lblProtocol = new javax.swing.JLabel();
        cmbProtocol = FastBindableReferenceComboFactory.createTagsFastBindableReferenceComboBox(Taggroups.PROTOCOL);
        lblApplication = new javax.swing.JLabel();
        cmbApplication = FastBindableReferenceComboFactory.createTagsFastBindableReferenceComboBox(
                Taggroups.APPLICATION_PROFILE);
        lblContentType = new javax.swing.JLabel();
        cmbContentType = FastBindableReferenceComboFactory.createTagsFastBindableReferenceComboBox(
                Taggroups.CONTENT_TYPE);
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));

        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        add(infoBoxPanel, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    RepresentationsDataAccessInformationVisualPanel.class,
                    "RepresentationsDataAccessInformationVisualPanel.jPanel1.border.title"))); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            lblContentLocation,
            org.openide.util.NbBundle.getMessage(
                RepresentationsDataAccessInformationVisualPanel.class,
                "RepresentationsDataAccessInformationVisualPanel.lblContentLocation.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
        jPanel1.add(lblContentLocation, gridBagConstraints);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.contentlocation}"),
                txtContentLocation,
                org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        txtContentLocation.addFocusListener(new java.awt.event.FocusAdapter() {

                @Override
                public void focusGained(final java.awt.event.FocusEvent evt) {
                    txtContentLocationFocusGained(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 10);
        jPanel1.add(txtContentLocation, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            lblFunction,
            org.openide.util.NbBundle.getMessage(
                RepresentationsDataAccessInformationVisualPanel.class,
                "RepresentationsDataAccessInformationVisualPanel.lblFunction.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel1.add(lblFunction, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.function}"),
                cmbFunction,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        cmbFunction.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    cmbFunctionActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel1.add(cmbFunction, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            lblProtocol,
            org.openide.util.NbBundle.getMessage(
                RepresentationsDataAccessInformationVisualPanel.class,
                "RepresentationsDataAccessInformationVisualPanel.lblProtocol.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel1.add(lblProtocol, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.protocol}"),
                cmbProtocol,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        cmbProtocol.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    cmbProtocolActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel1.add(cmbProtocol, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            lblApplication,
            org.openide.util.NbBundle.getMessage(
                RepresentationsDataAccessInformationVisualPanel.class,
                "RepresentationsDataAccessInformationVisualPanel.lblApplication.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 5);
        jPanel1.add(lblApplication, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.applicationprofile}"),
                cmbApplication,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        cmbApplication.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    cmbApplicationActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 10);
        jPanel1.add(cmbApplication, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            lblContentType,
            org.openide.util.NbBundle.getMessage(
                RepresentationsDataAccessInformationVisualPanel.class,
                "RepresentationsDataAccessInformationVisualPanel.lblContentType.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel1.add(lblContentType, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.contenttype}"),
                cmbContentType,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        cmbContentType.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    cmbContentTypeActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel1.add(cmbContentType, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        add(jPanel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        add(filler1, gridBagConstraints);

        bindingGroup.bind();
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void txtContentLocationFocusGained(final java.awt.event.FocusEvent evt) { //GEN-FIRST:event_txtContentLocationFocusGained
        infoBoxPanel.setInformation("Please provide a link to the externally stored data, e.g. a ZIP file.");
    }                                                                                 //GEN-LAST:event_txtContentLocationFocusGained

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmbContentTypeActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_cmbContentTypeActionPerformed
        final String desc = TagUtils.getDescriptionOfTag(cmbContentType.getSelectedItem());
        infoBoxPanel.setInformation(desc);
    }                                                                                  //GEN-LAST:event_cmbContentTypeActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmbFunctionActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_cmbFunctionActionPerformed
        final String desc = TagUtils.getDescriptionOfTag(cmbFunction.getSelectedItem());
        infoBoxPanel.setInformation(desc);
    }                                                                               //GEN-LAST:event_cmbFunctionActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmbProtocolActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_cmbProtocolActionPerformed
        final String desc = TagUtils.getDescriptionOfTag(cmbProtocol.getSelectedItem());
        infoBoxPanel.setInformation(desc);
    }                                                                               //GEN-LAST:event_cmbProtocolActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmbApplicationActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_cmbApplicationActionPerformed
        final String desc = TagUtils.getDescriptionOfTag(cmbApplication.getSelectedItem());
        infoBoxPanel.setInformation(desc);
    }                                                                                  //GEN-LAST:event_cmbApplicationActionPerformed

    @Override
    public CidsBean getCidsBean() {
        return representation;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        bindingGroup.unbind();
        if (cidsBean != null) {
            this.representation = cidsBean;
            DefaultCustomObjectEditor.setMetaClassInformationToMetaClassStoreComponentsInBindingGroup(
                bindingGroup,
                this.representation);
            bindingGroup.bind();
        }
    }

    @Override
    public void dispose() {
        bindingGroup.bind();
    }

    /**
     * DOCUMENT ME!
     *
     * @param  panelWasOpen  DOCUMENT ME!
     */
    public void changeAppearanceAsImportDocumentPanelWasOpen(final boolean panelWasOpen) {
        txtContentLocation.setEnabled(!panelWasOpen);
    }

    @Override
    public void markMandatoryFieldsStrong() {
        MarkMandtoryFieldsStrongUtils.markJLabelsStrong(lblContentLocation, lblContentType, lblFunction, lblProtocol);
    }
}
