/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import de.cismet.cids.custom.switchon.gui.utils.FastBindableReferenceComboFactory;
import de.cismet.cids.custom.switchon.utils.Taggroups;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;
import de.cismet.cids.dynamics.Disposable;

import de.cismet.cids.editors.DefaultCustomObjectEditor;
import de.cismet.cids.editors.FastBindableReferenceCombo;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class GeographicInformationVisualPanel extends javax.swing.JPanel implements CidsBeanStore, Disposable {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            GeographicInformationVisualPanel.class);

    //~ Instance fields --------------------------------------------------------

    CidsBean resource;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddLocation;
    private javax.swing.JComboBox cmbLocation;
    private javax.swing.JComboBox cmbSrid;
    private de.cismet.cids.custom.switchon.gui.utils.CreateNewTagAction createNewLocation;
    private javax.swing.Box.Filler filler1;
    private de.cismet.cids.custom.switchon.gui.GeometryChooserPanel geometryChooserPanel;
    private de.cismet.cids.custom.switchon.gui.InfoBoxPanel infoBoxPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel pnlOtherProperties;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form GeographicInformationVisualPanel.
     */
    public GeographicInformationVisualPanel() {
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

        createNewLocation = new de.cismet.cids.custom.switchon.gui.utils.CreateNewTagAction();
        geometryChooserPanel = new de.cismet.cids.custom.switchon.gui.GeometryChooserPanel();
        pnlOtherProperties = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbSrid = FastBindableReferenceComboFactory.createTagsFastBindableReferenceComboBox(Taggroups.SRID);
        jLabel2 = new javax.swing.JLabel();
        cmbLocation = FastBindableReferenceComboFactory.createTagsFastBindableReferenceComboBox(Taggroups.LOCATION);
        btnAddLocation = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        infoBoxPanel = new de.cismet.cids.custom.switchon.gui.InfoBoxPanel();

        setLayout(new java.awt.GridBagLayout());

        geometryChooserPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    GeographicInformationVisualPanel.class,
                    "GeographicInformationVisualPanel.geometryChooserPanel.border.title"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(geometryChooserPanel, gridBagConstraints);

        pnlOtherProperties.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    GeographicInformationVisualPanel.class,
                    "GeographicInformationVisualPanel.pnlOtherProperties.border.title"))); // NOI18N
        pnlOtherProperties.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel1,
            org.openide.util.NbBundle.getMessage(
                GeographicInformationVisualPanel.class,
                "GeographicInformationVisualPanel.jLabel1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        pnlOtherProperties.add(jLabel1, gridBagConstraints);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.srid}"),
                cmbSrid,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        cmbSrid.addFocusListener(new java.awt.event.FocusAdapter() {

                @Override
                public void focusGained(final java.awt.event.FocusEvent evt) {
                    cmbSridFocusGained(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 10);
        pnlOtherProperties.add(cmbSrid, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel2,
            org.openide.util.NbBundle.getMessage(
                GeographicInformationVisualPanel.class,
                "GeographicInformationVisualPanel.jLabel2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        pnlOtherProperties.add(jLabel2, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.location}"),
                cmbLocation,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        cmbLocation.addFocusListener(new java.awt.event.FocusAdapter() {

                @Override
                public void focusGained(final java.awt.event.FocusEvent evt) {
                    cmbLocationFocusGained(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        pnlOtherProperties.add(cmbLocation, gridBagConstraints);

        createNewLocation.setCombo((FastBindableReferenceCombo)cmbLocation);
        btnAddLocation.setAction(createNewLocation);
        org.openide.awt.Mnemonics.setLocalizedText(
            btnAddLocation,
            org.openide.util.NbBundle.getMessage(
                GeographicInformationVisualPanel.class,
                "GeographicInformationVisualPanel.btnAddLocation.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        pnlOtherProperties.add(btnAddLocation, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        pnlOtherProperties.add(filler1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        add(pnlOtherProperties, gridBagConstraints);

        infoBoxPanel.setGeneralInformation(org.openide.util.NbBundle.getMessage(
                GeographicInformationVisualPanel.class,
                "GeographicInformationVisualPanel.infoBoxPanel.generalInformation")); // NOI18N
        infoBoxPanel.setMinimumSize(new java.awt.Dimension(134, 55));
        infoBoxPanel.setPreferredSize(new java.awt.Dimension(748, 55));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        add(infoBoxPanel, gridBagConstraints);

        bindingGroup.bind();
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmbSridFocusGained(final java.awt.event.FocusEvent evt) { //GEN-FIRST:event_cmbSridFocusGained
        infoBoxPanel.setInformation("Please select the spatial reference system of the resource.");
    }                                                                      //GEN-LAST:event_cmbSridFocusGained

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmbLocationFocusGained(final java.awt.event.FocusEvent evt) { //GEN-FIRST:event_cmbLocationFocusGained
        infoBoxPanel.setInformation("Please specify a location of the resource or add a new one.");
    }                                                                          //GEN-LAST:event_cmbLocationFocusGained

    @Override
    public CidsBean getCidsBean() {
        return resource;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        bindingGroup.unbind();
        this.resource = cidsBean;
        DefaultCustomObjectEditor.setMetaClassInformationToMetaClassStoreComponentsInBindingGroup(
            bindingGroup,
            this.resource);
        geometryChooserPanel.setCidsBean(resource);
        bindingGroup.bind();
    }

    @Override
    public void dispose() {
        bindingGroup.unbind();
        geometryChooserPanel.dispose();
    }
}
