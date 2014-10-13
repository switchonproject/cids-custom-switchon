/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import org.openide.WizardDescriptor;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.net.URI;
import java.net.URLEncoder;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.cismet.cids.custom.switchon.gui.InfoReceiver;
import de.cismet.cids.custom.switchon.utils.TagUtils;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;

import de.cismet.cismap.commons.util.DnDUtils;

import de.cismet.commons.security.WebDavClient;
import de.cismet.commons.security.WebDavHelper;

import de.cismet.netutil.Proxy;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class BasicImportDocumentVisualPanel extends javax.swing.JPanel implements CidsBeanStore, InfoReceiver {

    //~ Static fields/initializers ---------------------------------------------

    private static final long ONEHUNDRED_KILOBYTES = (long)1e5;
    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            BasicImportDocumentVisualPanel.class);
    private static String WEB_DAV_USER;
    private static String WEB_DAV_PASSWORD;
    private static String BASIC_IMPORT_URL;
    private static String RESOURCE_TYPE_FOLDER;
    private static String RESOURCE_TYPE_FOLDER_UNKOWN;
    private static String TAGGROUP_NOT_SET_FOLDER;

    private static final Future<CidsBean> GEOSERVER;

    private static CidsBean functionCidsBean;
    private static CidsBean protocolCidsBean;

    static {
        GEOSERVER = TagUtils.fetchFutureTagByName("geoserver");
        try {
            final ResourceBundle bundle = ResourceBundle.getBundle(
                    "de/cismet/cids/custom/switchon/wizards/panels/webdav/WebDav"); // NOI18N
            final String pass = bundle.getString("password");
            WEB_DAV_PASSWORD = pass;

            WEB_DAV_USER = bundle.getString("user");
            BASIC_IMPORT_URL = bundle.getString("url_basic_import");

            RESOURCE_TYPE_FOLDER = bundle.getString("resourceTypeFolder");
            RESOURCE_TYPE_FOLDER_UNKOWN = bundle.getString("resourceTypeFolderUnkown");
            TAGGROUP_NOT_SET_FOLDER = bundle.getString("taggroupNotSetFolder");
        } catch (Exception ex) {
            LOG.error(
                "Could not read WebDav properties from property file. The umleitungsmechanism for Vermessungrisse will not work", // NOI18N
                ex);
            WEB_DAV_PASSWORD = ""; // NOI18N
            WEB_DAV_USER = ""; // NOI18N
            BASIC_IMPORT_URL = ""; // NOI18N
            RESOURCE_TYPE_FOLDER = "WP3"; // NOI18N
            RESOURCE_TYPE_FOLDER_UNKOWN = "default"; // NOI18N
            TAGGROUP_NOT_SET_FOLDER = "default"; // NOI18N
        }
    }

    //~ Instance fields --------------------------------------------------------

    private boolean saveInContentAllowed = true;

    private CidsBean cidsBean;
    private CidsBean resource;
    private WizardDescriptor wizard;
    private String wizardPropertyToIndicateIfImportButtonWasPressed;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImport;
    private javax.swing.JCheckBox chbPublish;
    private de.cismet.cids.custom.switchon.wizards.WizardInfoBoxPanel infoBoxPanel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblFileChooser;
    private javax.swing.JLabel lblStatus;
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
        new DropTarget(txtLocation, new FileDropListener());
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

        pnlImport = new javax.swing.JPanel();
        txtLocation = new javax.swing.JTextArea();
        lblFileChooser = new javax.swing.JLabel();
        btnImport = new javax.swing.JButton();
        chbPublish = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        prbStatus = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        infoBoxPanel = new de.cismet.cids.custom.switchon.wizards.WizardInfoBoxPanel();

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

        org.openide.awt.Mnemonics.setLocalizedText(
            chbPublish,
            org.openide.util.NbBundle.getMessage(
                BasicImportDocumentVisualPanel.class,
                "BasicImportDocumentVisualPanel.chbPublish.text")); // NOI18N
        chbPublish.setEnabled(false);
        chbPublish.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    chbPublishActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        pnlImport.add(chbPublish, gridBagConstraints);
        chbPublish.setVisible(false);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
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
            lblStatus,
            org.openide.util.NbBundle.getMessage(
                BasicImportDocumentVisualPanel.class,
                "BasicImportDocumentVisualPanel.lblStatus.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel3.add(lblStatus, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        add(jPanel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
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

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void chbPublishActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_chbPublishActionPerformed
        try {
            // remove all publish styles
            final Collection<CidsBean> tags = getCidsBean().getBeanCollectionProperty("tags");
            tags.remove(GEOSERVER.get());

            if (chbPublish.isSelected()) {
                final String contentType = getCidsBean().getProperty("contenttype").toString();
                switch (contentType) {
                    case "image/tiff":
                    case "image/geotiff":
                    case "application/zip":
                    case "application/shp": {
                        tags.add(GEOSERVER.get());
                        break;
                    }
                }
            }
        } catch (InterruptedException ex) {
            LOG.error(ex, ex);
        } catch (ExecutionException ex) {
            LOG.error(ex, ex);
        }
    } //GEN-LAST:event_chbPublishActionPerformed

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
     * @return  DOCUMENT ME!
     */
    public CidsBean getResource() {
        return resource;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  resource  DOCUMENT ME!
     */
    public void setResource(final CidsBean resource) {
        this.resource = resource;
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
                cidsBean.setProperty("contenttype", contentInformation.contentType);         // NOI18N
            }
            if (contentInformation.contentLocation != null) {
                cidsBean.setProperty("contentlocation", contentInformation.contentLocation); // NOI18N
            }
            if (contentInformation.content != null) {
                cidsBean.setProperty("content", contentInformation.content);                 // NOI18N
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public boolean isSaveInContentAllowed() {
        return saveInContentAllowed;
    }

    /**
     * A boolean which enables or disables that the content of the chosen file is saved directly in the database. If
     * saveInContentAllowed is false, the file will always be uploaded to the WebDav.
     *
     * @param  saveInContentAllowed  DOCUMENT ME!
     */
    public void setSaveInContentAllowed(final boolean saveInContentAllowed) {
        this.saveInContentAllowed = saveInContentAllowed;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  contentType  DOCUMENT ME!
     */
    private void checkUploadToAdvancedDataRepositoryPossible(final String contentType) {
        try {
            boolean uploadPossible = false;
            if ("image/geotiff".equals(contentType) || "application/shp".equals(contentType)
                        || "image/tiff".equals(contentType) || "application/zip".equals(contentType)) {
                uploadPossible = true;
            }

            chbPublish.setEnabled(uploadPossible);
            if (!uploadPossible) {
                chbPublish.setSelected(false);
                // remove all publish styles
                final Collection<CidsBean> tags = getCidsBean().getBeanCollectionProperty("tags");
                tags.remove(GEOSERVER.get());
            }
        } catch (InterruptedException ex) {
            LOG.error(ex, ex);
        } catch (ExecutionException ex) {
            LOG.error(ex, ex);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param  visible  DOCUMENT ME!
     */
    protected void setCheckboxPublishToAdvancedDataRepositoryVisible(final boolean visible) {
        chbPublish.setVisible(visible);
    }

    /**
     * DOCUMENT ME!
     *
     * @param  wizard  DOCUMENT ME!
     */
    public void setWizard(final WizardDescriptor wizard) {
        this.wizard = wizard;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  wizardPropertyToIndicateIfImportButtonWasPressed  DOCUMENT ME!
     */
    public void setWizardPropertyToIndicateIfImportButtonWasPressed(
            final String wizardPropertyToIndicateIfImportButtonWasPressed) {
        this.wizardPropertyToIndicateIfImportButtonWasPressed = wizardPropertyToIndicateIfImportButtonWasPressed;
    }

    @Override
    public void setInformation(final String information) {
        infoBoxPanel.setInformation(information);
    }

    @Override
    public void setError(final String error) {
        infoBoxPanel.setError(error);
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private class CreateContent extends SwingWorker<ContentInformation, ProcessInformation> {

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
            publish(new ProcessInformation(
                    org.openide.util.NbBundle.getMessage(
                        BasicImportDocumentVisualPanel.class,
                        "BasicImportDocumentVisualPanel.CreateContent.fetchContent"), // NOI18N
                    0));
            final ContentInformation information = new ContentInformation();
            final String contentType = Files.probeContentType(path);
            fetchContentTypeTag(contentType, information);
            fetchFunctionAndProtocolTags();

            boolean upload = true;
            if (upload && contentType.startsWith("text")) {                       // NOI18N
                final long size = Files.size(path);
                upload = size > ONEHUNDRED_KILOBYTES;
            }
            if (saveInContentAllowed && !upload) {
                publish(new ProcessInformation(
                        org.openide.util.NbBundle.getMessage(
                            BasicImportDocumentVisualPanel.class,
                            "BasicImportDocumentVisualPanel.CreateContent.Save"), // NOI18N
                        25));
                saveContent(path, information);
            } else {
                final int responseCode = uploadContent(path, information);
                if (!((responseCode == 200) || (responseCode == 201))) {
                    throw new UploadNotSuccessfullException(
                        responseCode,
                        "The upload failed. Http response code is: "
                                + responseCode);                                  // NOI18N
                }
            }
            return information;
        }

        @Override
        protected void process(final List<ProcessInformation> chunks) {
            if (!chunks.isEmpty()) {
                showProcess(chunks.get(chunks.size() - 1));
            }
        }

        @Override
        protected void done() {
            String processMessage;
            try {
                final ContentInformation information = get();
                setContentInformationToCidsBean(information);
                checkUploadToAdvancedDataRepositoryPossible(information.contentType.toString());
                setFunctionAndProtocolForRepresentation();
                processMessage = org.openide.util.NbBundle.getMessage(
                        BasicImportDocumentVisualPanel.class,
                        "BasicImportDocumentVisualPanel.CreateContent.finished"); // NOI18N
                if (wizard != null) {
                    wizard.putProperty(wizardPropertyToIndicateIfImportButtonWasPressed, Boolean.TRUE);
                }
            } catch (InterruptedException ex) {
                LOG.error(ex, ex);
                processMessage = "Upload failed!";
            } catch (ExecutionException ex) {
                LOG.error(ex, ex);
                final Throwable cause = ex.getCause();
                if (cause instanceof UploadNotSuccessfullException) {
                    final int statusCode = ((UploadNotSuccessfullException)cause).responseCode;
                    processMessage = "Upload failed: " + statusCode + " - " + HttpStatus.getStatusText(statusCode);
                } else {
                    processMessage = "Upload failed!";
                }
            } catch (Exception ex) {
                LOG.error(ex, ex);
                processMessage = "Upload failed!";
            }
            showProcess(new ProcessInformation(processMessage,
                    100));
        }

        /**
         * DOCUMENT ME!
         *
         * @param  processInformation  DOCUMENT ME!
         */
        private void showProcess(final ProcessInformation processInformation) {
            prbStatus.setValue(processInformation.processInPercent);
            lblStatus.setText(processInformation.message);
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
         * @return  DOCUMENT ME!
         *
         * @throws  Exception  DOCUMENT ME!
         */
        private int uploadContent(final Path path, final ContentInformation information) throws Exception {
            final WebDavClient webdavclient = new WebDavClient(Proxy.fromPreferences(),
                    WEB_DAV_USER,
                    WEB_DAV_PASSWORD,
                    true);

            final String filename = FilenameUtils.getName(path.toString());
            final String url = determineUrl(webdavclient, filename);

            final String message = java.text.MessageFormat.format(java.util.ResourceBundle.getBundle(
                        "de/cismet/cids/custom/switchon/wizards/panels/Bundle").getString( // NOI18N
                        "BasicImportDocumentVisualPanel.CreateContent.uploadTo"),          // NOI18N
                    url);
            publish(new ProcessInformation(message, 25));

            final int responseCode = WebDavHelper.uploadFileToWebDAV(
                    "",
                    path.toFile(),
                    url,
                    webdavclient,
                    BasicImportDocumentVisualPanel.this);

            information.content = null;
            information.contentLocation = url;
            return responseCode;
        }

        /**
         * DOCUMENT ME!
         *
         * @param   webdavclient  DOCUMENT ME!
         * @param   filename      DOCUMENT ME!
         *
         * @return  DOCUMENT ME!
         */
        private String determineUrl(final WebDavClient webdavclient, final String filename) {
            String resourceTypeFolder = RESOURCE_TYPE_FOLDER_UNKOWN;
            String geographyFolder = TAGGROUP_NOT_SET_FOLDER;
            String hydrologicalConceptFolder = TAGGROUP_NOT_SET_FOLDER;

            if (resource != null) {
                final CidsBean resourceType = (CidsBean)resource.getProperty("type");              // NOI18N
                if (resourceType != null) {
                    final String resourceTypeName = (String)resourceType.getProperty("name");      // NOI18N
                    if (resourceTypeName.equals("repurposed data")
                                || resourceTypeName.equals("experiment result data")               // NOI18N
                                || resourceTypeName.equals("repurposed experiment result data")) { // NOI18N
                        resourceTypeFolder = RESOURCE_TYPE_FOLDER;
                    }
                }

                final List<CidsBean> tags = resource.getBeanCollectionProperty("tags"); // NOI18N

                final CidsBean geographyTag = TagUtils.returnFirstOccurrenceOfTaggroup(tags, "geography"); // NOI18N
                if (geographyTag != null) {
                    geographyFolder = (String)geographyTag.getProperty("name");                            // NOI18N
                }

                final CidsBean hydrologicalConceptTag = TagUtils.returnFirstOccurrenceOfTaggroup(
                        tags,
                        "hydrological concept");                                                    // NOI18N
                if (hydrologicalConceptTag != null) {
                    hydrologicalConceptFolder = (String)hydrologicalConceptTag.getProperty("name"); // NOI18N
                }
            }

            String urlBase = BASIC_IMPORT_URL + urlEncode(resourceTypeFolder);
            checkAndCreateFolder(webdavclient, urlBase);
            urlBase += "/" + urlEncode(geographyFolder);
            checkAndCreateFolder(webdavclient, urlBase);
            urlBase += "/" + urlEncode(hydrologicalConceptFolder);
            checkAndCreateFolder(webdavclient, urlBase);
            urlBase += "/";

            final String baseName = urlEncode(FilenameUtils.getBaseName(filename));
            final String extension = FilenameUtils.getExtension(filename);

            String tmpFileName = urlEncode(filename);
            int i = 2;
            while (WebDavHelper.isUrlAccessible(webdavclient, urlBase + tmpFileName)) {
                tmpFileName = baseName + "(" + i + ")" + "." + extension;
                i += 1;
            }

            return urlBase + tmpFileName;
        }

        /**
         * Encodes a URL with the class URLEncoder and the encoding UTF-8. If the encoding is not supported a standard
         * encoding will be used.
         *
         * @param   string  DOCUMENT ME!
         *
         * @return  DOCUMENT ME!
         */
        private String urlEncode(String string) {
            try {
                string = URLEncoder.encode(string, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                LOG.error("The encoding UTF-8 is not supported, use standard encoding instead.", ex);
                string = URLEncoder.encode(string);
            }
            return string.replace("+", "%20");
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
                LOG.error("Could not read content of:" + path, ex); // NOI18N
                information.content = "";                           // NOI18N
            }
        }

        /**
         * DOCUMENT ME!
         *
         * @param  webdavclient  DOCUMENT ME!
         * @param  urlBase       DOCUMENT ME!
         */
        private void checkAndCreateFolder(final WebDavClient webdavclient, final String urlBase) {
            if (!WebDavHelper.isUrlAccessible(webdavclient, urlBase)) {
                try {
                    webdavclient.mkCol(urlBase);
                } catch (IOException ex) {
                    LOG.error(ex, ex);
                }
            }
        }

        /**
         * DOCUMENT ME!
         */
        private void setFunctionAndProtocolForRepresentation() {
            if ("representation".equalsIgnoreCase(cidsBean.getClass().getSimpleName())) {
                final String contentLocation = (String)cidsBean.getProperty("contentlocation");

                if (contentLocation == null) {
                    try {
                        // content is set
                        cidsBean.setProperty("function", null);
                        cidsBean.setProperty("protocol", null);
                    } catch (Exception ex) {
                        LOG.error(ex, ex);
                    }
                } else {
                    try {
                        // content is set
                        cidsBean.setProperty("function", functionCidsBean);
                        cidsBean.setProperty("protocol", protocolCidsBean);
                    } catch (Exception ex) {
                        LOG.error(ex, ex);
                    }
                }
            }
        }

        /**
         * DOCUMENT ME!
         */
        private synchronized void fetchFunctionAndProtocolTags() {
            if (functionCidsBean == null) {
                functionCidsBean = TagUtils.fetchTagByName("download");
            }
            if (protocolCidsBean == null) {
                protocolCidsBean = TagUtils.fetchTagByName("WWW:DOWNLOAD-1.0-http--download");
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private class UploadNotSuccessfullException extends Exception {

        //~ Instance fields ----------------------------------------------------

        int responseCode;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new UploadNotSuccessfullException object.
         *
         * @param  responseCode  DOCUMENT ME!
         * @param  message       DOCUMENT ME!
         */
        public UploadNotSuccessfullException(final int responseCode, final String message) {
            super(message);
            this.responseCode = responseCode;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    public class ProcessInformation {

        //~ Instance fields ----------------------------------------------------

        String message;
        int processInPercent;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new ProcessInformation object.
         *
         * @param  message           DOCUMENT ME!
         * @param  processInPercent  DOCUMENT ME!
         */
        public ProcessInformation(final String message, final int processInPercent) {
            this.message = message;
            this.processInPercent = processInPercent;
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
