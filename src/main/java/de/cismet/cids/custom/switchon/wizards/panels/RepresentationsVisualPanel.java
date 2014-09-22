/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import org.openide.WizardDescriptor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionListener;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;
import de.cismet.cids.dynamics.Disposable;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class RepresentationsVisualPanel extends javax.swing.JPanel implements CidsBeanStore, Disposable {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            RepresentationsVisualPanel.class);

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.cismet.cids.custom.switchon.gui.InfoBoxPanel infoBoxPanel;
    private de.cismet.cids.custom.switchon.objecteditors.RepresentationsPanel representationsPanel;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form RepresentationsVisualPanel.
     */
    public RepresentationsVisualPanel() {
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

        representationsPanel = new de.cismet.cids.custom.switchon.objecteditors.RepresentationsPanel();
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
        add(representationsPanel, gridBagConstraints);

        infoBoxPanel.setGeneralInformation(org.openide.util.NbBundle.getMessage(
                RepresentationsVisualPanel.class,
                "RepresentationsVisualPanel.infoBoxPanel.generalInformation")); // NOI18N
        infoBoxPanel.setMinimumSize(new java.awt.Dimension(134, 100));
        infoBoxPanel.setPreferredSize(new java.awt.Dimension(748, 100));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        add(infoBoxPanel, gridBagConstraints);
    }                                                                           // </editor-fold>//GEN-END:initComponents

    @Override
    public CidsBean getCidsBean() {
        return representationsPanel.getCidsBean();
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        representationsPanel.setCidsBean(cidsBean);
    }

    @Override
    public void dispose() {
        representationsPanel.dispose();
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public CidsBean getSelectedRepresentation() {
        return representationsPanel.getSelectedRepresentation();
    }

    /**
     * DOCUMENT ME!
     *
     * @param  listener  DOCUMENT ME!
     */
    public void addTableSelectionListener(final ListSelectionListener listener) {
        representationsPanel.addTableSelectionListener(listener);
    }
    /**
     * DOCUMENT ME!
     *
     * @param  listener  DOCUMENT ME!
     */
    public void removeTableSelectionListener(final ListSelectionListener listener) {
        representationsPanel.removeTableSelectionListener(listener);
    }

    /**
     * The add button of the metaDataPanel should work like clicking the next button. Although the selection in the
     * table has to be cleared first, because otherwise the selected representation will be edited.
     *
     * @param  wizardDescriptor  DOCUMENT ME!
     */
    public void addButtonShouldSimulateNextButton(final WizardDescriptor wizardDescriptor) {
        representationsPanel.replaceActionListenerOfAddButton(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
                    representationsPanel.clearTableSelection();
                    wizardDescriptor.doNextClick();
                }
            });
    }

    /**
     * The edit button of the metaDataPanel should work exactly like clicking the next button.
     *
     * @param  wizardDescriptor  DOCUMENT ME!
     */
    public void editButtonShouldSimulateNextButton(final WizardDescriptor wizardDescriptor) {
        representationsPanel.replaceActionListenerOfEditButton(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
                    wizardDescriptor.doNextClick();
                }
            });
    }
}
