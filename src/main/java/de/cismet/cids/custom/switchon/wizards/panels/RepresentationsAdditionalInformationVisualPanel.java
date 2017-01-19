/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import de.cismet.cids.custom.switchon.gui.InfoReceiver;
import de.cismet.cids.custom.switchon.objecteditors.SpatialAndTemporalPropertiesPanel;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;
import de.cismet.cids.dynamics.Disposable;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class RepresentationsAdditionalInformationVisualPanel extends javax.swing.JPanel implements CidsBeanStore,
    Disposable,
    InfoReceiver {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            RepresentationsAdditionalInformationVisualPanel.class);

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    protected de.cismet.cids.custom.switchon.wizards.WizardInfoBoxPanel infoBoxPanel;
    private de.cismet.cids.custom.switchon.objecteditors.SpatialAndTemporalPropertiesPanel
        spatialAndTemporalPropertiesPanel;
        // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form RepresentationsAdditionalInformationVisualPanel.
     */
    public RepresentationsAdditionalInformationVisualPanel() {
        initComponents();
        spatialAndTemporalPropertiesPanel.setInfoReceiver(infoBoxPanel);
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

        spatialAndTemporalPropertiesPanel = new SpatialAndTemporalPropertiesPanel(true);
        ;
        infoBoxPanel = new de.cismet.cids.custom.switchon.wizards.WizardInfoBoxPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));

        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        add(spatialAndTemporalPropertiesPanel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        add(infoBoxPanel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        add(filler1, gridBagConstraints);
    } // </editor-fold>//GEN-END:initComponents

    @Override
    public CidsBean getCidsBean() {
        return spatialAndTemporalPropertiesPanel.getCidsBean();
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        spatialAndTemporalPropertiesPanel.setCidsBean(cidsBean);
    }

    @Override
    public void dispose() {
        spatialAndTemporalPropertiesPanel.dispose();
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
