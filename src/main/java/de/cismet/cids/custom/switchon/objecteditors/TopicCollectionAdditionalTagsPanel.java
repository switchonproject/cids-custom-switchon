/**
 * *************************************************
 *
 * cismet GmbH, Saarbruecken, Germany
 * 
* ... and it just works.
 * 
***************************************************
 */
package de.cismet.cids.custom.switchon.objecteditors;

import java.util.ArrayList;

import de.cismet.cids.custom.switchon.gui.InfoProviderJPanel;
import de.cismet.cids.custom.switchon.gui.InfoReceiver;
import de.cismet.cids.custom.switchon.gui.utils.FastBindableReferenceComboFactory;
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
 * @author Gilles Baatz
 * @version $Revision$, $Date$
 */
public class TopicCollectionAdditionalTagsPanel extends InfoProviderJPanel implements CidsBeanStore, Disposable {

    //~ Static fields/initializers ---------------------------------------------
    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            TopicCollectionAdditionalTagsPanel.class);

    //~ Instance fields --------------------------------------------------------
    private CidsBean cidsBean;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.cismet.cids.custom.switchon.objecteditors.AdditionalTagsPanel additionalTagsPanel;
    private javax.swing.JButton btnNewCollection;
    private javax.swing.JComboBox cmbCollection;
    private javax.swing.JComboBox cmbTopic;
    private de.cismet.cids.custom.switchon.gui.utils.CreateNewTagAction createNewCollection;
    private javax.swing.JPanel pnlCollection;
    private javax.swing.JPanel pnlTopicCategory;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------
    /**
     * Creates new form TopicCollectionAdditionalTagsPanel.
     */
    public TopicCollectionAdditionalTagsPanel() {
        initComponents();
    }

    //~ Methods ----------------------------------------------------------------
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        createNewCollection = new de.cismet.cids.custom.switchon.gui.utils.CreateNewTagAction();
        pnlTopicCategory = new javax.swing.JPanel();
        cmbTopic = FastBindableReferenceComboFactory.createTagsFastBindableReferenceComboBox(Taggroups.TOPIC_CATEGORY)
        ;
        pnlCollection = new javax.swing.JPanel();
        cmbCollection = FastBindableReferenceComboFactory.createTagsFastBindableReferenceComboBox(Taggroups.COLLECTION) ;
        btnNewCollection = new javax.swing.JButton();
        ArrayList<Taggroups> taggroups = new ArrayList<Taggroups>();
        taggroups.add(Taggroups.KEYWORDS_INSPIRE_THEMES_1_0);
        taggroups.add(Taggroups.KEYWORDS_CUAHSI);
        taggroups.add(Taggroups.KEYWORDS_OPEN);
        additionalTagsPanel = new de.cismet.cids.custom.switchon.objecteditors.AdditionalTagsPanel(taggroups);

        setLayout(new java.awt.GridBagLayout());

        pnlTopicCategory.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(TopicCollectionAdditionalTagsPanel.class, "TopicCollectionAdditionalTagsPanel.pnlTopicCategory.border.title"))); // NOI18N
        pnlTopicCategory.setLayout(new java.awt.GridBagLayout());

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${cidsBean.topiccategory}"), cmbTopic, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        cmbTopic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTopicActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlTopicCategory.add(cmbTopic, gridBagConstraints);
        ((FastBindableReferenceCombo)cmbTopic).setNullable(false);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        add(pnlTopicCategory, gridBagConstraints);

        pnlCollection.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(TopicCollectionAdditionalTagsPanel.class, "TopicCollectionAdditionalTagsPanel.pnlCollection.border.title"))); // NOI18N
        pnlCollection.setLayout(new java.awt.GridBagLayout());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${cidsBean.collection}"), cmbCollection, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        cmbCollection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCollectionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlCollection.add(cmbCollection, gridBagConstraints);
        createNewCollection.setCombo((FastBindableReferenceCombo)cmbCollection);

        btnNewCollection.setAction(createNewCollection);
        org.openide.awt.Mnemonics.setLocalizedText(btnNewCollection, org.openide.util.NbBundle.getMessage(TopicCollectionAdditionalTagsPanel.class, "TopicCollectionAdditionalTagsPanel.btnNewCollection.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        pnlCollection.add(btnNewCollection, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        add(pnlCollection, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(additionalTagsPanel, gridBagConstraints);

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param evt DOCUMENT ME!
     */
    private void cmbTopicActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTopicActionPerformed
        final String desc = TagUtils.getDescriptionOfTag(cmbTopic.getSelectedItem());
        provideInformation(desc);
    }//GEN-LAST:event_cmbTopicActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param evt DOCUMENT ME!
     */
    private void cmbCollectionActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCollectionActionPerformed
        final String desc = TagUtils.getDescriptionOfTag(cmbCollection.getSelectedItem());
        provideInformation(desc);
    }//GEN-LAST:event_cmbCollectionActionPerformed

    @Override
    public CidsBean getCidsBean() {
        return cidsBean;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        bindingGroup.unbind();
        if (cidsBean != null) {
            this.cidsBean = cidsBean;
            DefaultCustomObjectEditor.setMetaClassInformationToMetaClassStoreComponentsInBindingGroup(
                    bindingGroup,
                    this.cidsBean);
            additionalTagsPanel.setCidsBean(cidsBean);
            bindingGroup.bind();
            
            if (cmbTopic.getSelectedIndex() < 0) {
                cmbTopic.setSelectedItem(this.getDefaultTopicCategory());
            }
        }
    }

    @Override
    public void dispose() {
        bindingGroup.unbind();
        additionalTagsPanel.dispose();
    }

    @Override
    public void setInfoReceiver(final InfoReceiver infoReceiver) {
        super.setInfoReceiver(infoReceiver);
        additionalTagsPanel.setInfoReceiver(infoReceiver);
    }

    protected Object getDefaultTopicCategory() {
        if (cmbTopic.getModel().getSize() > 10) {
            // select inlandWater
            LOG.debug("set default topic category to "+cmbTopic.getItemAt(10));
            return cmbTopic.getItemAt(10);
        } else {
            LOG.warn("could not select default topic 'inlandWaters', number of topics: " + cmbTopic.getModel().getSize());
            return null;
        }
    }
}
