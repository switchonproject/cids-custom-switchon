/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import org.openide.util.WeakListeners;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import de.cismet.cids.custom.switchon.converter.EulerComputationWizardAction;

/**
 * DOCUMENT ME!
 *
 * @author   mscholl
 * @version  $Revision$, $Date$
 */
public class IDFTablePanel extends javax.swing.JPanel {

    //~ Static fields/initializers ---------------------------------------------

// private static final transient Logger LOG = Logger.getLogger(IDFTablePanel.class);
    private static final int DURATION_COLUMN_WIDTH = 40;

    //~ Instance fields --------------------------------------------------------

    private transient IDFCurve idfCurve;
    private transient int selectedColIndex;
    private transient int selectedRowStart;
    private transient int selectedRowEnd;
    private final JPopupMenu popup;
    private final JMenuItem eulerMenuItem;
    private final ActionListener popupL;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblDuration;
    private javax.swing.JLabel lblFrequency;
    private javax.swing.JLabel lblHeaderSpace;
    private javax.swing.JScrollPane spIDFTable;
    private javax.swing.JTable tblIDF;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form IDFTablePanel.
     *
     * @param  idfCurve  cidsBeanIDFcurve curve DOCUMENT ME!
     */
    public IDFTablePanel(final IDFCurve idfCurve) {
        this.idfCurve = idfCurve;

        initComponents();

        lblDuration.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        lblDuration.setForeground(UIManager.getColor("TableHeader.foreground"));
        lblDuration.setBackground(UIManager.getColor("TableHeader.background"));
        lblDuration.setFont(UIManager.getFont("TableHeader.font"));

        lblFrequency.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        lblFrequency.setForeground(UIManager.getColor("TableHeader.foreground"));
        lblFrequency.setBackground(UIManager.getColor("TableHeader.background"));
        lblFrequency.setFont(UIManager.getFont("TableHeader.font"));

        lblHeaderSpace.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        lblHeaderSpace.setForeground(UIManager.getColor("TableHeader.foreground"));
        lblHeaderSpace.setBackground(UIManager.getColor("TableHeader.background"));
        lblHeaderSpace.setFont(UIManager.getFont("TableHeader.font"));

        Font font = lblFrequency.getFont();
        font = font.deriveFont(Font.BOLD);
        lblFrequency.setFont(font);

        init();

        popup = new JPopupMenu();
        popup.add(eulerMenuItem = new JMenuItem(
                    org.openide.util.NbBundle.getMessage(
                        IDFTablePanel.class,
                        "IDFTablePanle(CidsBean).menuItem.computation")));
        eulerMenuItem.setEnabled(false);
        popupL = new EulerComputationWizardAction(this);
        eulerMenuItem.addActionListener(WeakListeners.create(ActionListener.class, popupL, eulerMenuItem));
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public IDFCurve getIDFcurve() {
        return idfCurve;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  idfCurve  cidsBeanIDFcurve DOCUMENT ME!
     */
    public void setIDFcurve(final IDFCurve idfCurve) {
        this.idfCurve = idfCurve;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getSelectedColIndex() {
        return selectedColIndex;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  selectedColIndex  DOCUMENT ME!
     */
    public void setSelectedColIndex(final int selectedColIndex) {
        this.selectedColIndex = selectedColIndex;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getSelectedRowEnd() {
        return selectedRowEnd;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  selectedRowEnd  DOCUMENT ME!
     */
    public void setSelectedRowEnd(final int selectedRowEnd) {
        this.selectedRowEnd = selectedRowEnd;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getSelectedRowStart() {
        return selectedRowStart;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  selectedRowStart  DOCUMENT ME!
     */
    public void setSelectedRowStart(final int selectedRowStart) {
        this.selectedRowStart = selectedRowStart;
    }

    /**
     * DOCUMENT ME!
     */
    private void init() {
        tblIDF.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblIDF.getTableHeader().setReorderingAllowed(false);
        tblIDF.setCellSelectionEnabled(true);

        final List<Integer> frequencies = idfCurve.getFrequencies();
        final Object[] columnHeaders = new Object[frequencies.size() + 1];
        columnHeaders[0] = "";
        System.arraycopy(frequencies.toArray(), 0, columnHeaders, 1, columnHeaders.length - 1);

        final IDFTableModel model = new IDFTableModel(idfCurve.getDurationIntensityRows(), columnHeaders);

        tblIDF.setModel(model);

        final IDFCellRenderer renderer = new IDFCellRenderer();

        final TableColumnModel tcm = tblIDF.getColumnModel();
        for (int i = 0; i < tcm.getColumnCount(); ++i) {
            tcm.getColumn(i).setCellRenderer(renderer);
            tcm.getColumn(i).setHeaderRenderer(renderer);

            if (i == 0) {
                tcm.getColumn(i).setPreferredWidth(DURATION_COLUMN_WIDTH);
            }
        }

        tblIDF.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(final ListSelectionEvent lse) {
                    if (lse.getValueIsAdjusting()) {
                        return;
                    }
                    final int colIndexStart = tblIDF.getSelectedColumn();
                    final int colIndexEnd = tblIDF.getColumnModel().getSelectionModel().getMaxSelectionIndex();
                    final int rowIndexStart = tblIDF.getSelectedRow();
                    final int rowIndexEnd = tblIDF.getSelectionModel().getMaxSelectionIndex();

                    if ((colIndexStart != colIndexEnd) || (rowIndexStart != 0)) {
                        eulerMenuItem.setEnabled(false);
                    } else {
                        eulerMenuItem.setEnabled(true);

                        setSelectedColIndex(colIndexEnd);
                        setSelectedRowStart(rowIndexStart);
                        setSelectedRowEnd(rowIndexEnd);
                    }
                }
            });
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        spIDFTable = new javax.swing.JScrollPane();
        tblIDF = new javax.swing.JTable();
        lblFrequency = new javax.swing.JLabel();
        lblDuration = new javax.swing.JLabel();
        lblHeaderSpace = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());

        spIDFTable.setPreferredSize(new java.awt.Dimension(452, 300));

        tblIDF.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                    { null, null, null, null },
                    { null, null, null, null },
                    { null, null, null, null },
                    { null, null, null, null }
                },
                new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
        tblIDF.addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mousePressed(final java.awt.event.MouseEvent evt) {
                    tblIDFMousePressed(evt);
                }
                @Override
                public void mouseReleased(final java.awt.event.MouseEvent evt) {
                    tblIDFMouseReleased(evt);
                }
            });
        spIDFTable.setViewportView(tblIDF);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(spIDFTable, gridBagConstraints);

        lblFrequency.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFrequency.setText(org.openide.util.NbBundle.getMessage(
                IDFTablePanel.class,
                "IDFTablePanel.lblFrequency.text")); // NOI18N
        lblFrequency.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblFrequency.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        add(lblFrequency, gridBagConstraints);

        lblDuration.setText(org.openide.util.NbBundle.getMessage(
                IDFTablePanel.class,
                "IDFTablePanel.lblDuration.text")); // NOI18N
        lblDuration.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblDuration.setMaximumSize(new java.awt.Dimension(23, 23));
        lblDuration.setMinimumSize(new java.awt.Dimension(23, 23));
        lblDuration.setOpaque(true);
        lblDuration.setPreferredSize(new java.awt.Dimension(23, 23));
        lblDuration.addComponentListener(new java.awt.event.ComponentAdapter() {

                @Override
                public void componentResized(final java.awt.event.ComponentEvent evt) {
                    lblDurationComponentResized(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weighty = 1.0;
        add(lblDuration, gridBagConstraints);

        lblHeaderSpace.setText(org.openide.util.NbBundle.getMessage(
                IDFTablePanel.class,
                "IDFTablePanel.lblHeaderSpace.text")); // NOI18N
        lblHeaderSpace.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblHeaderSpace.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        add(lblHeaderSpace, gridBagConstraints);
    }                                                  // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void tblIDFMouseReleased(final java.awt.event.MouseEvent evt) { //GEN-FIRST:event_tblIDFMouseReleased
        showPopup(evt);
    }                                                                       //GEN-LAST:event_tblIDFMouseReleased

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void tblIDFMousePressed(final java.awt.event.MouseEvent evt) { //GEN-FIRST:event_tblIDFMousePressed
        showPopup(evt);
    }                                                                      //GEN-LAST:event_tblIDFMousePressed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void lblDurationComponentResized(final java.awt.event.ComponentEvent evt) { //GEN-FIRST:event_lblDurationComponentResized
        final int width = lblDuration.getWidth() + lblDuration.getInsets().left + lblDuration.getInsets().right;
        lblDuration.setText(org.openide.util.NbBundle.getMessage(
                IDFTablePanel.class,
                "IDFTablePanel.lblDurationComponentResized(ComponentEvent).lblDuration.text"));
        final FontMetrics fm = lblDuration.getGraphics().getFontMetrics();
        final int height = -fm.getStringBounds(lblDuration.getText(), lblDuration.getGraphics()).getBounds().width;
        if ((width == 0) || (height == 0)) {
            return;
        }
        Font font = lblDuration.getFont();
        font = font.deriveFont(Font.BOLD);
        final AffineTransform at = new AffineTransform();
        at.rotate(-1.57d);
        at.translate(height / 2, width / 2);
        font = font.deriveFont(at);
        lblDuration.setFont(font);
    }                                                                                   //GEN-LAST:event_lblDurationComponentResized

    /**
     * DOCUMENT ME!
     *
     * @param  e  DOCUMENT ME!
     */
    private void showPopup(final java.awt.event.MouseEvent e) {
        if (e.isPopupTrigger()) {
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private static final class IDFTableModel implements TableModel {

        //~ Instance fields ----------------------------------------------------

        private Object[][] data;
        private Object[] columns;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new IDFTableModel object.
         *
         * @param  data     DOCUMENT ME!
         * @param  columns  DOCUMENT ME!
         */
        public IDFTableModel(final Object[][] data, final Object[] columns) {
            this.data = data;
            this.columns = columns;
        }

        //~ Methods ------------------------------------------------------------

        @Override
        public int getRowCount() {
            if ((data != null) && (data.length > 0)) {
                return data.length;
            }
            return 0;
        }

        @Override
        public int getColumnCount() {
            if ((columns != null) && (columns.length > 0)) {
                return columns.length;
            }
            return 0;
        }

        @Override
        public String getColumnName(final int i) {
            if ((columns != null) && (i < columns.length) && (i != 0)) {
                final String frequency = String.valueOf(columns[i]);
                return frequency;
            }
            return "";
        }

        @Override
        public Class<?> getColumnClass(final int i) {
            return String.class;
        }

        @Override
        public boolean isCellEditable(final int i, final int i1) {
            return false;
        }

        @Override
        public Object getValueAt(final int i, final int i1) {
            if ((data != null) && (i < data.length) && (i1 < data[i].length)) {
                final String value = String.valueOf(data[i][i1]);
                return value;
            }
            return "";
        }

        @Override
        public void setValueAt(final Object o, final int i, final int i1) {
        }

        @Override
        public void addTableModelListener(final TableModelListener tl) {
        }

        @Override
        public void removeTableModelListener(final TableModelListener tl) {
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private static final class IDFCellRenderer extends DefaultTableCellRenderer {

        //~ Methods ------------------------------------------------------------

        @Override
        public Component getTableCellRendererComponent(final JTable table,
                final Object value,
                final boolean isSelected,
                final boolean hasFocus,
                final int row,
                final int column) {
            final Component c;

            if ((value != null) && ((column == 0) || (row == -1))) {
                final JButton b = new JButton(value.toString());
                b.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
                b.setForeground(UIManager.getColor("TableHeader.foreground"));
                b.setBackground(UIManager.getColor("TableHeader.background"));
                b.setFont(UIManager.getFont("TableHeader.font"));

                if (column == 0) {
                    b.setHorizontalAlignment(JButton.RIGHT);
                } else {
                    b.setHorizontalAlignment(JButton.CENTER);
                }

                c = b;
            } else {
                c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (c instanceof JLabel) {
                    final JLabel label = (JLabel)c;

                    label.setHorizontalAlignment(JLabel.RIGHT);
                }
            }

            return c;
        }
    }
}
