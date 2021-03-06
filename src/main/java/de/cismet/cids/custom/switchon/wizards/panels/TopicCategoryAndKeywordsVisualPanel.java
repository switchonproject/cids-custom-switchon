/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import de.cismet.cids.custom.switchon.gui.InfoReceiver;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;
import de.cismet.cids.dynamics.Disposable;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class TopicCategoryAndKeywordsVisualPanel extends javax.swing.JPanel implements CidsBeanStore,
    Disposable,
    InfoReceiver {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            TopicCategoryAndKeywordsVisualPanel.class);

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.cismet.cids.custom.switchon.wizards.WizardInfoBoxPanel infoBoxPanel;
    private de.cismet.cids.custom.switchon.objecteditors.TopicCollectionAdditionalTagsPanel
        topicCollectionAdditionalTagsPanel;
        // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form TopicCategoryAndKeywordsVisualPanel.
     */
    public TopicCategoryAndKeywordsVisualPanel() {
        initComponents();
        topicCollectionAdditionalTagsPanel.setInfoReceiver(infoBoxPanel);
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

        infoBoxPanel = new de.cismet.cids.custom.switchon.wizards.WizardInfoBoxPanel();
        topicCollectionAdditionalTagsPanel =
            new de.cismet.cids.custom.switchon.objecteditors.TopicCollectionAdditionalTagsPanel();

        addFocusListener(new java.awt.event.FocusAdapter() {

                @Override
                public void focusGained(final java.awt.event.FocusEvent evt) {
                    formFocusGained(evt);
                }
            });
        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        add(infoBoxPanel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 10, 0);
        add(topicCollectionAdditionalTagsPanel, gridBagConstraints);
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void formFocusGained(final java.awt.event.FocusEvent evt) { //GEN-FIRST:event_formFocusGained
    }                                                                   //GEN-LAST:event_formFocusGained

    @Override
    public CidsBean getCidsBean() {
        return topicCollectionAdditionalTagsPanel.getCidsBean();
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        topicCollectionAdditionalTagsPanel.setCidsBean(cidsBean);
    }

    @Override
    public void dispose() {
        topicCollectionAdditionalTagsPanel.dispose();
    }

    @Override
    public void setInformation(final String information) {
        infoBoxPanel.setInformation(information);
    }

    @Override
    public void setError(final String error) {
        infoBoxPanel.setError(error);
    }

    /**
     * DOCUMENT ME!
     *
     * @param  readOnly  DOCUMENT ME!
     */
    public void setReadOnly(final boolean readOnly) {
        this.topicCollectionAdditionalTagsPanel.setReadOnly(readOnly);
    }
}
