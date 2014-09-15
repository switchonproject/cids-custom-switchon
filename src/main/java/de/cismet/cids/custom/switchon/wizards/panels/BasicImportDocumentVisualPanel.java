/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import java.io.File;
import java.io.IOException;

import java.net.URI;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.cismet.cids.custom.switchon.utils.TagUtils;
import de.cismet.cids.custom.switchon.utils.WebDavHelper;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;

import de.cismet.cismap.commons.util.DnDUtils;

import de.cismet.netutil.Proxy;

import de.cismet.tools.PasswordEncrypter;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class BasicImportDocumentVisualPanel extends javax.swing.JPanel implements CidsBeanStore {

    //~ Static fields/initializers ---------------------------------------------

    private static final long ONEHUNDRED_KILOBYTES = (long)1e5;
    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            BasicImportDocumentVisualPanel.class);
    private static String WEB_DAV_USER;
    private static String WEB_DAV_PASSWORD;
    private static String BASIC_IMPORT_URL;

    static {
        try {
            final ResourceBundle bundle = ResourceBundle.getBundle(
                    "de/cismet/cids/custom/switchon/wizards/panels/webdav/WebDav");
            final String pass = bundle.getString("password");
            WEB_DAV_PASSWORD = pass;

            WEB_DAV_USER = bundle.getString("user");
            BASIC_IMPORT_URL = bundle.getString("url_basic_import");
        } catch (Exception ex) {
            LOG.error(
                "Could not read WebDav properties from property file. The umleitungsmechanism for Vermessungrisse will not work",
                ex);
            WEB_DAV_PASSWORD = "";
            WEB_DAV_USER = "";
            BASIC_IMPORT_URL = "";
        }
    }

    //~ Instance fields --------------------------------------------------------

    private WebDavHelper webDavHelper;

    private CidsBean cidsBean;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImport;
    private de.cismet.cids.custom.switchon.gui.InfoBoxPanel infoBoxPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblDestination;
    private javax.swing.JLabel lblFileChooser;
    private javax.swing.JPanel pnlImport;
    private javax.swing.JProgressBar prbStatus;
    private javax.swing.JTextArea txtLocation;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form ImportDocumentVisualPanel.
     */
    public BasicImportDocumentVisualPanel() {
        initComponents();
        txtLocation.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(final DocumentEvent e) {
                    isFile();
                }

                @Override
                public void removeUpdate(final DocumentEvent e) {
                    isFile();
                }

                @Override
                public void changedUpdate(final DocumentEvent e) {
                    isFile();
                }

                private void isFile() {
                    final Path path = Paths.get(txtLocation.getText());
                    final boolean canImport = Files.isRegularFile(path) && Files.isReadable(path);
                    btnImport.setEnabled(canImport);
                }
            });

        new DropTarget(pnlImport, new FileDropListener());

        webDavHelper = new WebDavHelper(Proxy.fromPreferences(), WEB_DAV_USER, WEB_DAV_PASSWORD, true);
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  generalInformation  DOCUMENT ME!
     */
    public void setGeneralInformation(final String generalInformation) {
        infoBoxPanel.setGeneralInformation(generalInformation);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlImport = new javax.swing.JPanel();
        txtLocation = new javax.swing.JTextArea();
        lblFileChooser = new javax.swing.JLabel();
        btnImport = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        prbStatus = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblDestination = new javax.swing.JLabel();
        infoBoxPanel = new de.cismet.cids.custom.switchon.gui.InfoBoxPanel();

        setLayout(new java.awt.GridBagLayout());

        pnlImport.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    BasicImportDocumentVisualPanel.class,
                    "BasicImportDocumentVisualPanel.pnlImport.border.title"))); // NOI18N
        pnlImport.setLayout(new java.awt.GridBagLayout());

        txtLocation.setColumns(20);
        txtLocation.setLineWrap(true);
        txtLocation.setRows(1);
        txtLocation.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtLocation.setEnabled(false);
        txtLocation.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlImport.add(txtLocation, gridBagConstraints);

        lblFileChooser.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/de/cismet/cids/custom/switchon/document_import.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(
            lblFileChooser,
            org.openide.util.NbBundle.getMessage(
                BasicImportDocumentVisualPanel.class,
                "BasicImportDocumentVisualPanel.lblFileChooser.text"));                          // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlImport.add(lblFileChooser, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            btnImport,
            org.openide.util.NbBundle.getMessage(
                BasicImportDocumentVisualPanel.class,
                "BasicImportDocumentVisualPanel.btnImport.text")); // NOI18N
        btnImport.setEnabled(false);
        btnImport.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    btnImportActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlImport.add(btnImport, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(pnlImport, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(prbStatus, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel1,
            org.openide.util.NbBundle.getMessage(
                BasicImportDocumentVisualPanel.class,
                "BasicImportDocumentVisualPanel.jLabel1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel3.add(jLabel1, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            lblDestination,
            org.openide.util.NbBundle.getMessage(
                BasicImportDocumentVisualPanel.class,
                "BasicImportDocumentVisualPanel.lblDestination.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel3.add(lblDestination, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        add(jPanel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        add(infoBoxPanel, gridBagConstraints);
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void btnImportActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnImportActionPerformed
        final String pathStr = txtLocation.getText();
        new CreateContent(Paths.get(pathStr)).execute();
    }                                                                             //GEN-LAST:event_btnImportActionPerformed

    @Override
    public CidsBean getCidsBean() {
        return cidsBean;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        this.cidsBean = cidsBean;
    }
    /**
     * DOCUMENT ME!
     *
     * @param   contentInformation  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    private void setContentInformationToCidsBean(final ContentInformation contentInformation) throws Exception {
        if ((cidsBean != null) && (contentInformation != null)) {
            if (contentInformation.contentType != null) {
                cidsBean.setProperty("contenttype", contentInformation.contentType);
            }
            if (contentInformation.contentLocation != null) {
                cidsBean.setProperty("contentlocation", contentInformation.contentLocation);
            }
            if (contentInformation.content != null) {
                cidsBean.setProperty("content", contentInformation.content);
            }
        }
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private class CreateContent extends SwingWorker<ContentInformation, Object> {

        //~ Instance fields ----------------------------------------------------

        private final Path path;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new CreateContent object.
         *
         * @param  path  DOCUMENT ME!
         */
        public CreateContent(final Path path) {
            this.path = path;
        }

        //~ Methods ------------------------------------------------------------

        @Override
        protected ContentInformation doInBackground() throws Exception {
            final ContentInformation information = new ContentInformation();
            final String contentType = Files.probeContentType(path);
            fetchContentTypeTag(contentType, information);
            boolean upload = true;
            if (contentType.startsWith("text")) {
                final long size = Files.size(path);
                upload = size > ONEHUNDRED_KILOBYTES;
            }
            if (upload) {
                uploadContent(path, information);
            } else {
                saveContent(path, information);
            }
            return information;
        }

        @Override
        protected void done() {
            try {
                setContentInformationToCidsBean(get());
            } catch (InterruptedException ex) {
                LOG.error(ex, ex);
            } catch (ExecutionException ex) {
                LOG.error(ex, ex);
            } catch (Exception ex) {
                LOG.error(ex, ex);
            }
        }

        /**
         * DOCUMENT ME!
         *
         * @param  contentType  DOCUMENT ME!
         * @param  information  DOCUMENT ME!
         */
        private void fetchContentTypeTag(final String contentType, final ContentInformation information) {
            information.contentType = TagUtils.fetchTagByName(contentType);
        }

        /**
         * DOCUMENT ME!
         *
         * @param   path         DOCUMENT ME!
         * @param   information  DOCUMENT ME!
         *
         * @throws  Exception  DOCUMENT ME!
         */
        private void uploadContent(final Path path, final ContentInformation information) throws Exception {
            final String filename = FilenameUtils.getName(path.toString());

            webDavHelper.uploadFileToWebDAV(
                filename,
                path.toFile(),
                BASIC_IMPORT_URL,
                BasicImportDocumentVisualPanel.this);

            information.content = null;
            information.contentLocation = BASIC_IMPORT_URL + filename;
        }

        /**
         * DOCUMENT ME!
         *
         * @param  path         DOCUMENT ME!
         * @param  information  DOCUMENT ME!
         */
        private void saveContent(final Path path, final ContentInformation information) {
            try {
                information.content = IOUtils.toString(Files.newBufferedReader(path, Charset.defaultCharset()));
                information.contentLocation = null;
            } catch (IOException ex) {
                LOG.error("Could not read content of:" + path, ex);
                information.content = "";
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    public class ContentInformation {

        //~ Instance fields ----------------------------------------------------

        public CidsBean contentType;
        public String content;
        public String contentLocation;
    }

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private class FileDropListener implements DropTargetListener {

        //~ Methods ------------------------------------------------------------

        @Override
        public void dragEnter(final DropTargetDragEvent dtde) {
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)
                        || dtde.isDataFlavorSupported(DnDUtils.URI_LIST_FLAVOR)) {
                dtde.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
            } else {
                dtde.rejectDrag();
            }
        }

        @Override
        public void dragOver(final DropTargetDragEvent dtde) {
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)
                        || dtde.isDataFlavorSupported(DnDUtils.URI_LIST_FLAVOR)) {
                dtde.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
            } else {
                dtde.rejectDrag();
            }
        }

        @Override
        public void dropActionChanged(final DropTargetDragEvent dtde) {
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)
                        || dtde.isDataFlavorSupported(DnDUtils.URI_LIST_FLAVOR)) {
                dtde.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
            } else {
                dtde.rejectDrag();
            }
        }

        @Override
        public void dragExit(final DropTargetEvent dte) {
            // noop
        }

        @Override
        public void drop(final DropTargetDropEvent dtde) {
            try {
                if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)
                            || dtde.isDataFlavorSupported(DnDUtils.URI_LIST_FLAVOR)) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);

                    final File file;
                    if (dtde.isDataFlavorSupported(DnDUtils.URI_LIST_FLAVOR)) {
                        // unix drop
                        final String uriList = (String)dtde.getTransferable().getTransferData(DnDUtils.URI_LIST_FLAVOR);
                        final String[] uris = uriList.split(System.getProperty("line.separator")); // NOI18N
                        if (uris.length >= 1) {
                            file = new File(new URI(uris[0].trim()));                              // NOI18N
                            dtde.dropComplete(true);
                        } else {
                            file = null;
                            dtde.dropComplete(false);
                        }
                    } else {
                        // win drop
                        @SuppressWarnings("unchecked")
                        final List<File> data = (List)dtde.getTransferable()
                                    .getTransferData(DataFlavor.javaFileListFlavor);
                        if (data.size() == 1) {
                            file = data.get(0);
                            dtde.dropComplete(true);
                        } else {
                            file = null;
                            dtde.dropComplete(false);
                        }
                    }

                    if (file != null) {
                        final String path = file.getPath();
                        txtLocation.setText(path);
//                    txtBezeichnung.setText(FilenameUtils.getBaseName(path));
//                    showPreview(path);
                    }
                } else {
                    dtde.rejectDrop();
                }
            } catch (final Exception e) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("reject drop: " + dtde, e); // NOI18N
                }

                dtde.dropComplete(false);
            }
        }
    }
}
