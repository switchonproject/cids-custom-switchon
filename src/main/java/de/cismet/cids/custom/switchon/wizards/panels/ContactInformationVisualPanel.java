/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import Sirius.server.middleware.types.MetaObject;

import java.awt.event.ItemEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.cismet.cids.custom.switchon.gui.InfoReceiver;
import de.cismet.cids.custom.switchon.gui.MarkMandtoryFieldsStrong;
import de.cismet.cids.custom.switchon.gui.utils.QueryComboBox;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;
import de.cismet.cids.dynamics.Disposable;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class ContactInformationVisualPanel extends javax.swing.JPanel implements CidsBeanStore,
    Disposable,
    PropertyChangeListener,
    MarkMandtoryFieldsStrong,
    InfoReceiver {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            ContactInformationVisualPanel.class);

    //~ Instance fields --------------------------------------------------------

    private CidsBean resource;
    private CidsBean newlyCreatedContact;
    private PropertyChangeListener model;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JComboBox cmbContacts;
    private de.cismet.cids.custom.switchon.objecteditors.ContactEditor contactEditor;
    protected de.cismet.cids.custom.switchon.wizards.WizardInfoBoxPanel infoBoxPanel;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form AdditonalMetaDataContactInformationVisualPanel.
     */
    public ContactInformationVisualPanel() {
        initComponents();

        contactEditor.setInfoReceiver(infoBoxPanel);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public CidsBean getCidsBean() {
        return resource;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        resource = cidsBean;
        CidsBean contact = (CidsBean)resource.getProperty("contact");
        if (contact == null) {
            final Object selectedObject = cmbContacts.getSelectedItem();
            if (selectedObject instanceof MetaObject) {
                contact = ((MetaObject)selectedObject).getBean();
            } else if (selectedObject instanceof CidsBean) {
                contact = (CidsBean)selectedObject;
            }

            try {
                resource.setProperty("contact", contact);
                model.propertyChange(null);
            } catch (Exception ex) {
                LOG.error(ex, ex);
            }
        } else if (cmbContacts.getSelectedIndex() == -1){
            //FIXME: does not work ....
            //cmbContacts.setSelectedItem(contact);
        }
        
        contactEditor.setCidsBean(contact);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        infoBoxPanel = new de.cismet.cids.custom.switchon.wizards.WizardInfoBoxPanel();
        jPanel1 = new javax.swing.JPanel();
        btnEdit = new javax.swing.JButton();
        cmbContacts = new QueryComboBox(
                "select id, organisation as name from contact order by organisation",
                false,
                "contact");
        ;
        btnNew = new javax.swing.JButton();
        contactEditor = new de.cismet.cids.custom.switchon.objecteditors.ContactEditor();

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
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        add(infoBoxPanel, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            btnEdit,
            org.openide.util.NbBundle.getMessage(
                ContactInformationVisualPanel.class,
                "ContactInformationVisualPanel.btnEdit.text")); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnEditActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 20);
        jPanel1.add(btnEdit, gridBagConstraints);

        cmbContacts.addItemListener(new java.awt.event.ItemListener() {

                @Override
                public void itemStateChanged(final java.awt.event.ItemEvent evt) {
                    cmbContactsItemStateChanged(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 10);
        jPanel1.add(cmbContacts, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnNew,
            org.openide.util.NbBundle.getMessage(
                ContactInformationVisualPanel.class,
                "ContactInformationVisualPanel.btnNew.text")); // NOI18N
        btnNew.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnNewActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        jPanel1.add(btnNew, gridBagConstraints);

        contactEditor.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(contactEditor, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jPanel1, gridBagConstraints);
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void formFocusGained(final java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
    }//GEN-LAST:event_formFocusGained

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnNewActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        if (newlyCreatedContact == null) {
            try {
                newlyCreatedContact = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "contact");
                newlyCreatedContact.addPropertyChangeListener(this);
                cmbContacts.addItem(newlyCreatedContact);
                cmbContacts.setSelectedItem(newlyCreatedContact);
            } catch (Exception ex) {
                LOG.error(ex, ex);
            }
        }
    }//GEN-LAST:event_btnNewActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmbContactsItemStateChanged(final java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbContactsItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            final Object selectedContact = cmbContacts.getSelectedItem();
            CidsBean selectContactBean = null;
            if (selectedContact instanceof MetaObject) {
                selectContactBean = ((MetaObject)selectedContact).getBean();
            } else if (selectedContact instanceof CidsBean) {
                selectContactBean = (CidsBean)selectedContact;
            }
            try {
                resource.setProperty("contact", selectContactBean);
                model.propertyChange(null);
            } catch (Exception ex) {
                LOG.error(ex, ex);
            }
            contactEditor.setCidsBean(selectContactBean);
            contactEditor.setEnabled(selectContactBean == newlyCreatedContact);
        }
    }//GEN-LAST:event_cmbContactsItemStateChanged

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnEditActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        contactEditor.setEnabled(true);
    }//GEN-LAST:event_btnEditActionPerformed

    @Override
    public void dispose() {
        contactEditor.dispose();
        if (newlyCreatedContact != null) {
            newlyCreatedContact.removePropertyChangeListener(this);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public PropertyChangeListener getModel() {
        return model;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  model  DOCUMENT ME!
     */
    public void setModel(final PropertyChangeListener model) {
        this.model = model;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        model.propertyChange(evt);
    }

    @Override
    public void markMandatoryFieldsStrong() {
        contactEditor.markMandatoryFieldsStrong();
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
