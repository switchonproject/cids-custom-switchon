/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objectrenderer;

import Sirius.navigator.plugin.PluginRegistry;

import org.apache.commons.io.FilenameUtils;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.custom.switchon.gui.utils.CismapUtils;
import de.cismet.cids.custom.switchon.gui.utils.ImageGetterUtils;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.tools.metaobjectrenderer.CidsBeanRenderer;

import de.cismet.cismap.navigatorplugin.CismapPlugin;

import de.cismet.tools.gui.downloadmanager.DownloadManager;
import de.cismet.tools.gui.downloadmanager.DownloadManagerDialog;
import de.cismet.tools.gui.downloadmanager.HttpOrFtpDownload;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class RepresentationRenderer extends javax.swing.JPanel implements CidsBeanRenderer {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            RepresentationRenderer.class);

    //~ Instance fields --------------------------------------------------------

    private final InitiateDownloadActionListener initiateDownloadActionListener = new InitiateDownloadActionListener();
    private final OpenInBrowserActionListener openInBrowserActionListener = new OpenInBrowserActionListener();
    private CidsBean cidsBean;
    private ActionListener hyperlinkActionListener = null;
    private final ResourceBundle functionBundle = ResourceBundle.getBundle(
            "de/cismet/cids/custom/switchon/tagBundles/function");

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private org.jdesktop.swingx.JXHyperlink hypAddToCismap;
    private org.jdesktop.swingx.JXHyperlink hypDownload;
    private org.jdesktop.swingx.JXHyperlink hypOpenInBrowser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddToCismapIcon;
    private javax.swing.JLabel lblDownloadIcon;
    private javax.swing.JLabel lblUrl;
    private de.cismet.cids.custom.switchon.objectrenderer.RepresentationUploadFinishedPanel
        representationUploadFinishedPanel;
    private de.cismet.cids.custom.switchon.objecteditors.SpatialAndTemporalPropertiesPanel
        spatialAndTemporalPropertiesPanel;
    private javax.swing.JTextArea txtaDescription;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form RepresentationRenderer.
     */
    public RepresentationRenderer() {
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaDescription = new javax.swing.JTextArea();
        spatialAndTemporalPropertiesPanel =
            new de.cismet.cids.custom.switchon.objecteditors.SpatialAndTemporalPropertiesPanel();
        jPanel3 = new javax.swing.JPanel();
        lblDownloadIcon = new javax.swing.JLabel();
        lblUrl = new javax.swing.JLabel();
        hypAddToCismap = new org.jdesktop.swingx.JXHyperlink();
        lblAddToCismapIcon = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        hypOpenInBrowser = new org.jdesktop.swingx.JXHyperlink();
        hypDownload = new org.jdesktop.swingx.JXHyperlink();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(32767, 0));
        representationUploadFinishedPanel =
            new de.cismet.cids.custom.switchon.objectrenderer.RepresentationUploadFinishedPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));

        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    RepresentationRenderer.class,
                    "RepresentationRenderer.jPanel1.border.title"))); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        txtaDescription.setEditable(false);
        txtaDescription.setColumns(20);
        txtaDescription.setLineWrap(true);
        txtaDescription.setRows(6);
        txtaDescription.setWrapStyleWord(true);
        txtaDescription.setMinimumSize(new java.awt.Dimension(220, 91));
        txtaDescription.setPreferredSize(new java.awt.Dimension(220, 91));

        final org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.description}"),
                txtaDescription,
                org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(txtaDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(jPanel1, gridBagConstraints);

        spatialAndTemporalPropertiesPanel.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(spatialAndTemporalPropertiesPanel, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    RepresentationRenderer.class,
                    "RepresentationRenderer.jPanel3.border.title"))); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        lblDownloadIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(
            lblDownloadIcon,
            org.openide.util.NbBundle.getMessage(
                RepresentationRenderer.class,
                "RepresentationRenderer.lblDownloadIcon.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 5);
        jPanel3.add(lblDownloadIcon, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            lblUrl,
            org.openide.util.NbBundle.getMessage(RepresentationRenderer.class, "RepresentationRenderer.lblUrl.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 10, 10);
        jPanel3.add(lblUrl, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            hypAddToCismap,
            org.openide.util.NbBundle.getMessage(
                RepresentationRenderer.class,
                "RepresentationRenderer.hypAddToCismap.text")); // NOI18N
        hypAddToCismap.setIconTextGap(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 10);
        jPanel3.add(hypAddToCismap, gridBagConstraints);

        lblAddToCismapIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAddToCismapIcon.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/de/cismet/cids/custom/switchon/objectrenderer/add.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(
            lblAddToCismapIcon,
            org.openide.util.NbBundle.getMessage(
                RepresentationRenderer.class,
                "RepresentationRenderer.lblAddToCismapIcon.text"));                                 // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 5);
        jPanel3.add(lblAddToCismapIcon, gridBagConstraints);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            hypOpenInBrowser,
            org.openide.util.NbBundle.getMessage(
                RepresentationRenderer.class,
                "RepresentationRenderer.hypOpenInBrowser.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 2, 10);
        jPanel2.add(hypOpenInBrowser, gridBagConstraints);
        hypOpenInBrowser.addActionListener(openInBrowserActionListener);

        org.openide.awt.Mnemonics.setLocalizedText(
            hypDownload,
            org.openide.util.NbBundle.getMessage(
                RepresentationRenderer.class,
                "RepresentationRenderer.hypDownload.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 2, 10);
        jPanel2.add(hypDownload, gridBagConstraints);
        hypDownload.addActionListener(initiateDownloadActionListener);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(filler2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jPanel2, gridBagConstraints);

        representationUploadFinishedPanel.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 10);
        jPanel3.add(representationUploadFinishedPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        add(filler1, gridBagConstraints);

        bindingGroup.bind();
    } // </editor-fold>//GEN-END:initComponents

    @Override
    public CidsBean getCidsBean() {
        return cidsBean;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        bindingGroup.unbind();
        if (cidsBean != null) {
            this.cidsBean = cidsBean;

            spatialAndTemporalPropertiesPanel.setCidsBean(cidsBean);
            representationUploadFinishedPanel.setCidsBean(cidsBean);

            bindingGroup.bind();

            setHyperlinkIconAndText();
            setHyperlinkAddToCismap();
        }
    }

    @Override
    public void dispose() {
        bindingGroup.unbind();
        spatialAndTemporalPropertiesPanel.dispose();
        representationUploadFinishedPanel.dispose();
    }

    @Override
    public String getTitle() {
        return cidsBean.toString();
    }

    @Override
    public void setTitle(final String title) {
    }

    /**
     * Configures the download or open in browser hyperlink.
     */
    private void setHyperlinkIconAndText() {
        Icon icon;
        final String function = (String)cidsBean.getProperty("function.name");

        if ("download".equalsIgnoreCase(function)) {
            hypDownload.setVisible(true);
            hypOpenInBrowser.setText("Open in Browser");

            final String contentType = (String)cidsBean.getProperty("contenttype.name");
            final String urlString = (String)cidsBean.getProperty("contentlocation");
            final String extension = FilenameUtils.getExtension(urlString);
            icon = new ImageIcon(ImageGetterUtils.getImageForContentType(
                        contentType,
                        ImageGetterUtils.ImageSize.PIXEL_32,
                        extension));
        } else {
            hypDownload.setVisible(false);

            String text = "Open in browser";
            try {
                text = functionBundle.getString(function + ".action");
            } catch (MissingResourceException ex) {
                LOG.warn(ex, ex);
            }
            hypOpenInBrowser.setText(text);

            final String protocol = (String)cidsBean.getProperty("protocol.name");
            icon = new ImageIcon(ImageGetterUtils.getImageForProtocol(protocol));
        }

        lblDownloadIcon.setIcon(icon);
        lblDownloadIcon.setText("");

        final String url = String.valueOf(cidsBean.getProperty("contentlocation"));
        lblUrl.setText(url);
        lblUrl.setToolTipText(url);
    }

    /**
     * Configures the Add-to-Cismap hyperlink. It is visible if the protocol of the Representation is WMS or WFS. On
     * click on the hyperlink the contentlocation is added as capability to the capability widget of the cismap.
     * Afterwards a switch to the Cismap happens.
     */
    private void setHyperlinkAddToCismap() {
        final String protocol = (String)cidsBean.getProperty("protocol.name");
        if ((protocol != null) && (protocol.startsWith("OGC:WFS") || protocol.startsWith("OGC:WMS"))) {
            hypAddToCismap.setVisible(true);
            lblAddToCismapIcon.setVisible(true);
            representationUploadFinishedPanel.setVisible(true);

            hypAddToCismap.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        final CismapPlugin cismapPlugin = (CismapPlugin)PluginRegistry.getRegistry()
                                    .getPlugin("cismap");
                        final String url = String.valueOf(cidsBean.getProperty("contentlocation"));
                        cismapPlugin.getCapabilities().processUrl(url, null);
                        CismapUtils.switchToCismapMap();
                    }
                });
        } else {
            hypAddToCismap.setVisible(false);
            lblAddToCismapIcon.setVisible(false);
            representationUploadFinishedPanel.setVisible(false);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param   args  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    public static void main(final String[] args) throws Exception {
        DevelopmentTools.createRendererInFrameFromRMIConnectionOnLocalhost(
            "SWITCHON",
            "Administratoren",
            "admin",
            "cismet",
            "representation",
            21,
            "Representation",
            1280,
            1024);
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private class InitiateDownloadActionListener implements ActionListener {

        //~ Methods ------------------------------------------------------------

        @Override
        public void actionPerformed(final ActionEvent e) {
            final String urlString = (String)cidsBean.getProperty("contentlocation");
            URL url = null;
            try {
                url = new URL(urlString);
            } catch (MalformedURLException ex) {
                LOG.error(urlString + " is not a valid URL.", ex);
            }
            if (url != null) {
                if (DownloadManagerDialog.showAskingForUserTitle(RepresentationRenderer.this)) {
                    final String filename = FilenameUtils.getBaseName(urlString);
                    final String extension = FilenameUtils.getExtension(urlString);

                    DownloadManager.instance()
                            .add(
                                new HttpOrFtpDownload(
                                    url,
                                    "",
                                    DownloadManagerDialog.getJobname(),
                                    cidsBean.toString(),
                                    filename,
                                    "."
                                    + extension));
                }
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private class OpenInBrowserActionListener implements ActionListener {

        //~ Methods ------------------------------------------------------------

        @Override
        public void actionPerformed(final ActionEvent e) {
            final String urlString = (String)cidsBean.getProperty("contentlocation");
            URL url = null;
            try {
                url = new URL(urlString);
            } catch (MalformedURLException ex) {
                LOG.error(urlString + " is not a valid URL.", ex);
            }
            if (url != null) {
                final Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if ((desktop != null) && desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        desktop.browse(url.toURI());
                    } catch (Exception ex) {
                        LOG.error("Could not open URI: " + urlString, ex);
                    }
                } else {
                    LOG.info("Opening a website is not supported.");
                }
            }
        }
    }
}
