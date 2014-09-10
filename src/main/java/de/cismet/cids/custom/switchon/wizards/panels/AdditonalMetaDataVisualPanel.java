/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;
import de.cismet.cids.dynamics.Disposable;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class AdditonalMetaDataVisualPanel extends javax.swing.JPanel implements CidsBeanStore, Disposable {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            AdditonalMetaDataVisualPanel.class);

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.cismet.cids.custom.switchon.gui.InfoBoxPanel infoBoxPanel;
    private de.cismet.cids.custom.switchon.objecteditors.MetaDataPanel metaDataPanel;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form AdditonalMetaDataVisualPanel.
     */
    public AdditonalMetaDataVisualPanel() {
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

        metaDataPanel = new de.cismet.cids.custom.switchon.objecteditors.MetaDataPanel();
        infoBoxPanel = new de.cismet.cids.custom.switchon.gui.InfoBoxPanel();

        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(metaDataPanel, gridBagConstraints);

        infoBoxPanel.setGeneralInformation(org.openide.util.NbBundle.getMessage(
                AdditonalMetaDataVisualPanel.class,
                "AdditonalMetaDataVisualPanel.infoBoxPanel.generalInformation")); // NOI18N
        infoBoxPanel.setMinimumSize(new java.awt.Dimension(134, 100));
        infoBoxPanel.setPreferredSize(new java.awt.Dimension(748, 55));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        add(infoBoxPanel, gridBagConstraints);
    }                                                                             // </editor-fold>//GEN-END:initComponents

    @Override
    public CidsBean getCidsBean() {
        return metaDataPanel.getCidsBean();
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        metaDataPanel.setCidsBean(cidsBean);
    }

    @Override
    public void dispose() {
        metaDataPanel.dispose();
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public CidsBean getSelectedMetaData() {
        return metaDataPanel.getSelectedMetaData();
    }
}
