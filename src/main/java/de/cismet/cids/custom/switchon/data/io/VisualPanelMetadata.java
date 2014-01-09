/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.data.io;

import org.apache.log4j.Logger;

import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import de.cismet.cids.custom.switchon.MonitorstationContext;
import de.cismet.cids.custom.switchon.Utils;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.editors.DefaultCustomObjectEditor;

/**
 * DOCUMENT ME!
 *
 * @author   bfriedrich
 * @version  $Revision$, $Date$
 */
public class VisualPanelMetadata extends javax.swing.JPanel {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(VisualPanelMetadata.class);

    //~ Instance fields --------------------------------------------------------

    private final transient PropertyChangeListener pcL;

    private final transient WizardPanelMetadata ctrl;
    private transient CidsBean cidsBean;

    private final transient ImageIcon stationIcon;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.cismet.cids.editors.DefaultBindableReferenceCombo cboStation;
    private javax.swing.JCheckBox chkForecast;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblForecast;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblStation;
    private javax.swing.JLabel lblYear;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtYear;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form TimeSeriesImportFileChoosePanel.
     *
     * @param   ctrl  DOCUMENT ME!
     *
     * @throws  NullPointerException  DOCUMENT ME!
     */
    public VisualPanelMetadata(final WizardPanelMetadata ctrl) {
        if (ctrl == null) {
            throw new NullPointerException("Given WizardPanelMetadata instance must not be null"); // NOI18N
        }

        this.ctrl = ctrl;
        this.pcL = new BeanChangeListener();
        this.stationIcon = ImageUtilities.loadImageIcon("de/cismet/cids/custom/switchon/monitor_16.png", false); // NOI18N

        initComponents();

        this.setName(NbBundle.getMessage(VisualPanelMetadata.class, "VisualPanelMetadata.this.name"));

        this.cboStation.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent ae) {
                    ctrl.fireChangeEvent();
                }
            });

        cboStation.setRenderer(new StationRenderer());
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public CidsBean getCidsBean() {
        return this.cidsBean;
    }

    /**
     * DOCUMENT ME!
     */
    public void init() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Entering init()");
        }

        this.cidsBean = this.ctrl.getCidsBean();

        this.txtName.setText("");
        this.txtDescription.setText("");
        this.cboStation.setSelectedIndex(-1);
        this.chkForecast.setSelected(false);

        try {
            this.cidsBean.setProperty("forecast", Boolean.FALSE);
        } catch (final Exception e) {
            LOG.error("ERROR", e);
        }

        initYearField();

        DefaultCustomObjectEditor.setMetaClassInformationToMetaClassStoreComponentsInBindingGroup(
            bindingGroup,
            cidsBean);
        this.bindingGroup.unbind();
        this.bindingGroup.bind();

        cidsBean.addPropertyChangeListener(pcL);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Leaving init()");
        }
    }
    /**
     * FIXME: quick and dirty
     *
     * @return  DOCUMENT ME!
     */
    Integer getYear() {
        try {
            return Integer.parseInt(txtYear.getText());
        } catch (final NumberFormatException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("unparsable year: " + txtYear.getText(), e); // NOI18N
            }

            return null;
        }
    }

    /**
     * DOCUMENT ME!
     */
    private void initYearField() {
        final boolean isIdf = Utils.TABLENAME_IDFCURVE.equals(ctrl.getTableName());
        txtYear.setEnabled(isIdf);
        lblYear.setEnabled(isIdf);
        txtYear.setToolTipText(isIdf
                ? null : NbBundle.getMessage(VisualPanelMetadata.class, "VisualPanelMetadata.txtYear.tooltiptext")); // NOI18N
    }

    /**
     * DOCUMENT ME!
     */
    public void finalise() {
        cidsBean.removePropertyChangeListener(pcL);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPanel1 = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        lblStation = new javax.swing.JLabel();
        lblForecast = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        cboStation = new de.cismet.cids.editors.DefaultBindableReferenceCombo();
        chkForecast = new javax.swing.JCheckBox();
        lblYear = new javax.swing.JLabel();
        txtYear = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        lblName.setText(org.openide.util.NbBundle.getMessage(
                VisualPanelMetadata.class,
                "VisualPanelMetadata.lblName.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel1.add(lblName, gridBagConstraints);

        lblDescription.setText(org.openide.util.NbBundle.getMessage(
                VisualPanelMetadata.class,
                "VisualPanelMetadata.lblDescription.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel1.add(lblDescription, gridBagConstraints);

        lblStation.setText(org.openide.util.NbBundle.getMessage(
                VisualPanelMetadata.class,
                "VisualPanelMetadata.lblStation.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel1.add(lblStation, gridBagConstraints);

        lblForecast.setText(org.openide.util.NbBundle.getMessage(
                VisualPanelMetadata.class,
                "VisualPanelMetadata.lblForecast.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel1.add(lblForecast, gridBagConstraints);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.name}"),
                txtName,
                org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel1.add(txtName, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.description}"),
                txtDescription,
                org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel1.add(txtDescription, gridBagConstraints);

        cboStation.setMinimumSize(new java.awt.Dimension(225, 25));
        cboStation.setPreferredSize(new java.awt.Dimension(225, 25));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.station}"),
                cboStation,
                org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel1.add(cboStation, gridBagConstraints);

        chkForecast.setText(org.openide.util.NbBundle.getMessage(
                VisualPanelMetadata.class,
                "VisualPanelMetadata.chkForecast.text")); // NOI18N
        chkForecast.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel1.add(chkForecast, gridBagConstraints);

        lblYear.setText(NbBundle.getMessage(VisualPanelMetadata.class, "VisualPanelMetadata.lblYear.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel1.add(lblYear, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.year}"),
                txtYear,
                org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        txtYear.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    txtYearActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel1.add(txtYear, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        bindingGroup.bind();
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void txtYearActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_txtYearActionPerformed
        // TODO add your handling code here:
    } //GEN-LAST:event_txtYearActionPerformed

    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private final class BeanChangeListener implements PropertyChangeListener {

        //~ Methods ------------------------------------------------------------

        @Override
        public void propertyChange(final PropertyChangeEvent evt) {
            ctrl.fireChangeEvent();
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private final class StationRenderer extends DefaultListCellRenderer {

        //~ Methods ------------------------------------------------------------

        @Override
        public Component getListCellRendererComponent(final JList list,
                final Object value,
                final int index,
                final boolean isSelected,
                final boolean cellHasFocus) {
            final Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (c instanceof JLabel) {
                final JLabel label = (JLabel)c;

                if (value == null) {
                    label.setText(cboStation.getNullValueRepresentation());
                } else {
                    final CidsBean bean = (CidsBean)value;
                    final String name = (String)bean.getProperty("name"); // NOI18N
                    final String type = (String)bean.getProperty("type"); // NOI18N

                    final int colonIndex = type.indexOf(':');

                    if (colonIndex > 0) {
                        final String ctxKey = type.substring(0, colonIndex);
                        final MonitorstationContext ctx = MonitorstationContext.getMonitorstationContext(ctxKey);
                        label.setText(name + "(" + ctx.getLocalisedName() + ")"); // NOI18N
                    } else {
                        label.setText(name);
                    }

                    label.setIcon(stationIcon);
                }
            }

            return c;
        }
    }
}
