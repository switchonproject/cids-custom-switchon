/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objecteditors;

import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.error.ErrorInfo;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.tools.gui.StaticSwingTools;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class ShowEditorInDialog extends javax.swing.JDialog {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ShowEditorInDialog.class);

    //~ Instance fields --------------------------------------------------------

    private boolean changesSaved = false;
    private final EditorShowableInDialog editor;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JPanel pnlEditor;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form TagAndTagGroupEditorDialog.
     *
     * @param  parent  DOCUMENT ME!
     * @param  modal   DOCUMENT ME!
     * @param  editor  DOCUMENT ME!
     */
    public ShowEditorInDialog(final java.awt.Frame parent, final boolean modal, final EditorShowableInDialog editor) {
        super(parent, modal);
        initComponents();
        this.editor = editor;
        final GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlEditor.add(editor.getComponent(), gridBagConstraints);
        this.pack();
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

        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(32767, 0));
        pnlEditor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            btnSave,
            org.openide.util.NbBundle.getMessage(ShowEditorInDialog.class, "ShowEditorInDialog.btnSave.text")); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnSaveActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 10);
        getContentPane().add(btnSave, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnCancel,
            org.openide.util.NbBundle.getMessage(ShowEditorInDialog.class, "ShowEditorInDialog.btnCancel.text")); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnCancelActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        getContentPane().add(btnCancel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(filler1, gridBagConstraints);

        pnlEditor.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(pnlEditor, gridBagConstraints);

        pack();
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnSaveActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnSaveActionPerformed
        try {
            editor.saveChanges();
            changesSaved = true;
            this.dispose();
        } catch (Exception ex) {
            LOG.error("CidsBean could not be saved", ex);
            final JXErrorPane errorPane = new JXErrorPane();
            final ErrorInfo errorInfo = new ErrorInfo(
                    "Error",
                    "Saving Cidsbean failed",
                    ex.getMessage(),
                    "ERROR",
                    ex,
                    Level.ALL,
                    null);
            errorPane.setErrorInfo(errorInfo);
            errorPane.setVisible(true);
        }
    }                                                                           //GEN-LAST:event_btnSaveActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnCancelActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }                                                                             //GEN-LAST:event_btnCancelActionPerformed

    /**
     * Shows the dialog and returns true, if the changed cidsBeans were saved. On false, the dialog was canceled, no
     * cidsBeans were changed or an error occurred.
     *
     * @return  DOCUMENT ME!
     */
    public boolean showDialog() {
        changesSaved = false;
        StaticSwingTools.showDialog(this);
        return changesSaved;
    }
}
