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
public class LicenseInformationVisualPanel extends javax.swing.JPanel implements CidsBeanStore,
    Disposable,
    InfoReceiver {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            LicenseInformationVisualPanel.class);

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.cismet.cids.custom.switchon.wizards.WizardInfoBoxPanel infoBoxPanel;
    private de.cismet.cids.custom.switchon.objecteditors.LicenseInformationPanel licenseInformationPanel;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form LicenseInformationVisualPanel.
     */
    public LicenseInformationVisualPanel() {
        initComponents();
        licenseInformationPanel.setInfoReceiver(infoBoxPanel);
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

        licenseInformationPanel = new de.cismet.cids.custom.switchon.objecteditors.LicenseInformationPanel(true);
        infoBoxPanel = new de.cismet.cids.custom.switchon.wizards.WizardInfoBoxPanel();

        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 5);
        add(licenseInformationPanel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        add(infoBoxPanel, gridBagConstraints);
    } // </editor-fold>//GEN-END:initComponents

    @Override
    public CidsBean getCidsBean() {
        return licenseInformationPanel.getCidsBean();
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        licenseInformationPanel.setCidsBean(cidsBean);
    }

    @Override
    public void dispose() {
        licenseInformationPanel.dispose();
    }

    @Override
    public void setInformation(final String information) {
        infoBoxPanel.setInformation(information);
    }

    @Override
    public void setError(final String error) {
        infoBoxPanel.setError(error);
    }
}
