/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import java.util.ArrayList;

import de.cismet.cids.custom.switchon.gui.utils.FastBindableReferenceComboFactory;
import de.cismet.cids.custom.switchon.objecteditors.AdditionalTagsPanel;
import de.cismet.cids.custom.switchon.utils.TagUtils;
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
public class TopicCategoryAndKeywordsVisualPanel extends javax.swing.JPanel implements CidsBeanStore, Disposable {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            TopicCategoryAndKeywordsVisualPanel.class);

    //~ Instance fields --------------------------------------------------------

    private CidsBean resource;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.cismet.cids.custom.switchon.objecteditors.AdditionalTagsPanel additionalTagsPanel;
    private javax.swing.JComboBox cmbTopic;
    private javax.swing.Box.Filler filler1;
    private de.cismet.cids.custom.switchon.gui.InfoBoxPanel infoBoxPanel;
    private javax.swing.JPanel jPanel1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form TopicCategoryAndKeywordsVisualPanel.
     */
    public TopicCategoryAndKeywordsVisualPanel() {
        initComponents();
        additionalTagsPanel.setInfoReceiver(infoBoxPanel);
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

        final ArrayList<Taggroups> taggroups = new ArrayList<Taggroups>();
        taggroups.add(Taggroups.KEYWORDS_INSPIRE_THEMES_1_0);
        taggroups.add(Taggroups.KEYWORDS_OPEN);
        additionalTagsPanel = new AdditionalTagsPanel(taggroups, AdditionalTagsPanel.BRANDING_KEYWORDS);
        jPanel1 = new javax.swing.JPanel();
        cmbTopic = FastBindableReferenceComboFactory.createTagsFastBindableReferenceComboBox(Taggroups.TOPIC_CATEGORY);
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(32767, 0));
        infoBoxPanel = new de.cismet.cids.custom.switchon.gui.InfoBoxPanel();

        addFocusListener(new java.awt.event.FocusAdapter() {

                @Override
                public void focusGained(final java.awt.event.FocusEvent evt) {
                    formFocusGained(evt);
                }
            });
        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        add(additionalTagsPanel, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    TopicCategoryAndKeywordsVisualPanel.class,
                    "TopicCategoryAndKeywordsVisualPanel.jPanel1.border.title"))); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        final org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.topiccategory}"),
                cmbTopic,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        cmbTopic.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    cmbTopicActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(cmbTopic, gridBagConstraints);
        ((FastBindableReferenceCombo)cmbTopic).setNullable(false);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        jPanel1.add(filler1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jPanel1, gridBagConstraints);

        infoBoxPanel.setGeneralInformation(org.openide.util.NbBundle.getMessage(
                TopicCategoryAndKeywordsVisualPanel.class,
                "TopicCategoryAndKeywordsVisualPanel.infoBoxPanel.generalInformation")); // NOI18N
        infoBoxPanel.setMinimumSize(new java.awt.Dimension(134, 55));
        infoBoxPanel.setPreferredSize(new java.awt.Dimension(748, 55));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        add(infoBoxPanel, gridBagConstraints);

        bindingGroup.bind();
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmbTopicActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_cmbTopicActionPerformed
        final String desc = TagUtils.getDescriptionOfTag(cmbTopic.getSelectedItem());
        infoBoxPanel.setInformation(desc);
    }                                                                            //GEN-LAST:event_cmbTopicActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void formFocusGained(final java.awt.event.FocusEvent evt) { //GEN-FIRST:event_formFocusGained
        infoBoxPanel.showGeneralInformation();
    }                                                                   //GEN-LAST:event_formFocusGained

    @Override
    public CidsBean getCidsBean() {
        return resource;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        bindingGroup.unbind();
        resource = cidsBean;
        DefaultCustomObjectEditor.setMetaClassInformationToMetaClassStoreComponentsInBindingGroup(
            bindingGroup,
            this.resource);
        additionalTagsPanel.setCidsBean(cidsBean);
        bindingGroup.bind();
    }

    @Override
    public void dispose() {
        bindingGroup.unbind();
    }
}
