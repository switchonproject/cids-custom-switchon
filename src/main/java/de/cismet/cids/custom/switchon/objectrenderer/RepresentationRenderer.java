/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objectrenderer;

import java.awt.Desktop;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.custom.switchon.gui.utils.ImageGetterUtils;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.tools.metaobjectrenderer.CidsBeanRenderer;

import de.cismet.tools.gui.downloadmanager.DownloadManager;
import de.cismet.tools.gui.downloadmanager.DownloadManagerDialog;
import de.cismet.tools.gui.downloadmanager.HttpDownload;

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

    private CidsBean cidsBean;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private org.jdesktop.swingx.JXHyperlink hypAddToCismap;
    private org.jdesktop.swingx.JXHyperlink hypDownload;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
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
        hypDownload = new org.jdesktop.swingx.JXHyperlink();
        hypAddToCismap = new org.jdesktop.swingx.JXHyperlink();
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

        txtaDescription.setColumns(20);
        txtaDescription.setLineWrap(true);
        txtaDescription.setRows(5);
        txtaDescription.setWrapStyleWord(true);

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
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(jPanel1, gridBagConstraints);

        spatialAndTemporalPropertiesPanel.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(spatialAndTemporalPropertiesPanel, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    RepresentationRenderer.class,
                    "RepresentationRenderer.jPanel3.border.title"))); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            hypDownload,
            org.openide.util.NbBundle.getMessage(
                RepresentationRenderer.class,
                "RepresentationRenderer.hypDownload.text")); // NOI18N
        hypDownload.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    hypDownloadActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        jPanel3.add(hypDownload, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            hypAddToCismap,
            org.openide.util.NbBundle.getMessage(
                RepresentationRenderer.class,
                "RepresentationRenderer.hypAddToCismap.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        jPanel3.add(hypAddToCismap, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        add(filler1, gridBagConstraints);

        bindingGroup.bind();
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void hypDownloadActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_hypDownloadActionPerformed
        final String urlString = (String)cidsBean.getProperty("contentlocation");
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException ex) {
            LOG.error(urlString + " is not a valid URL.", ex);
        }
        if (url != null) {
            final String function = (String)cidsBean.getProperty("function.name");
            if ("download".equalsIgnoreCase(function)) {                            // download the content
                if (DownloadManagerDialog.showAskingForUserTitle(RepresentationRenderer.this)) {
                    final String filename = urlString.substring(urlString.lastIndexOf("/") + 1);

                    DownloadManager.instance()
                            .add(
                                new HttpDownload(
                                    url,
                                    "",
                                    DownloadManagerDialog.getJobname(),
                                    cidsBean.toString(),
                                    filename.substring(0, filename.lastIndexOf(".")),
                                    filename.substring(filename.lastIndexOf("."))));
                }
            } else { // direct download not possible open it in browser
                final Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if ((desktop != null) && desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        desktop.browse(url.toURI());
                    } catch (Exception e) {
                        LOG.error("Could not open URI: " + urlString, e);
                    }
                } else {
                    LOG.info("Opening a website is not supported.");
                }
            }
        }
    }                //GEN-LAST:event_hypDownloadActionPerformed

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

            bindingGroup.bind();

            setHyperlinkIconAndText();
        }
    }

    @Override
    public void dispose() {
        bindingGroup.unbind();
    }

    @Override
    public String getTitle() {
        return cidsBean.toString();
    }

    @Override
    public void setTitle(final String title) {
    }

    /**
     * DOCUMENT ME!
     */
    private void setHyperlinkIconAndText() {
        Icon icon;
        final String function = (String)cidsBean.getProperty("function.name");
        String text = "Open browser";
        if ("download".equalsIgnoreCase(function)) {
            final String contentType = (String)cidsBean.getProperty("contenttype.name");
            icon = new ImageIcon(ImageGetterUtils.getImageForContentType(contentType));
            text = "Download File";
        } else {
            final String protocol = (String)cidsBean.getProperty("protocol.name");
            icon = new ImageIcon(ImageGetterUtils.getImageForProtocol(protocol));

            if ("order".equalsIgnoreCase(function)) {
                text = "Open order form";
            } else if ("service".equalsIgnoreCase(function)) {
                text = "Show Service URL";
            }
        }

        hypDownload.setText(text);
        hypDownload.setIcon(icon);
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
}
