/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objectrenderer;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;

import java.lang.ref.SoftReference;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import de.cismet.cids.dynamics.CidsBean;
import de.cismet.cids.dynamics.CidsBeanStore;

import de.cismet.security.WebAccessManager;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class RepresentationPreview extends javax.swing.JPanel implements CidsBeanStore, ComponentListener {

    //~ Static fields/initializers ---------------------------------------------

    public static BufferedImage ERROR_IMAGE;

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            RepresentationPreview.class);

    private static final int CACHE_SIZE = 100;

    private static final Map<URL, SoftReference<Image>> IMAGE_CACHE = new LinkedHashMap<URL, SoftReference<Image>>(
            CACHE_SIZE) {

            @Override
            protected boolean removeEldestEntry(final Map.Entry<URL, SoftReference<Image>> eldest) {
                return size() >= CACHE_SIZE;
            }
        };

    static {
        try {
            ERROR_IMAGE = ImageIO.read(RepresentationPreview.class.getResource(
                        "/de/cismet/cids/custom/switchon/objectrenderer/no_image.png"));
        } catch (IOException ex) {
            LOG.error("Could not fetch ERROR_IMAGE", ex);
        }
    }

    //~ Instance fields --------------------------------------------------------

    CidsBean representation;
    private JComponent sizeReference;

    private final Timer timer = new Timer(30, new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
                    if (originalImage != null) {
                        scaledimage = adjustScale(originalImage, 20, 20);
                        lblPicture.setIcon(new ImageIcon(scaledimage));
                    }
                }
            });
    private Image scaledimage;
    private Image originalImage;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel4;
    private org.jdesktop.swingx.JXBusyLabel lblBusy;
    private javax.swing.JLabel lblPicture;
    private javax.swing.JPanel pnlPicture;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form RepresentationPreview.
     */
    public RepresentationPreview() {
        initComponents();
        timer.setRepeats(false);
        this.addComponentListener(this);
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  wait  DOCUMENT ME!
     */
    private void showWait(final boolean wait) {
        if (wait) {
            if (!lblBusy.isBusy()) {
                ((CardLayout)pnlPicture.getLayout()).show(pnlPicture, "busy");
                lblBusy.setBusy(true);
            }
        } else {
            ((CardLayout)pnlPicture.getLayout()).show(pnlPicture, "image");
            lblBusy.setBusy(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlPicture = new javax.swing.JPanel();
        lblBusy = new org.jdesktop.swingx.JXBusyLabel(new Dimension(75, 75));
        jPanel4 = new javax.swing.JPanel();
        lblPicture = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        pnlPicture.setLayout(new java.awt.CardLayout());

        lblBusy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBusy.setMaximumSize(new java.awt.Dimension(140, 40));
        lblBusy.setMinimumSize(new java.awt.Dimension(140, 60));
        lblBusy.setPreferredSize(new java.awt.Dimension(140, 60));
        pnlPicture.add(lblBusy, "busy");

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            lblPicture,
            org.openide.util.NbBundle.getMessage(RepresentationPreview.class, "RepresentationPreview.lblPicture.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(lblPicture, gridBagConstraints);

        pnlPicture.add(jPanel4, "image");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(pnlPicture, gridBagConstraints);
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  tooltip  DOCUMENT ME!
     */
    public void indicateNotAvailable(final String tooltip) {
        indicateNotAvailable(tooltip, new ImageIcon(ERROR_IMAGE), "Kein Vorschaubild vorhanden.");
    }

    /**
     * DOCUMENT ME!
     *
     * @param  tooltip  DOCUMENT ME!
     * @param  icon     DOCUMENT ME!
     * @param  text     DOCUMENT ME!
     */
    public void indicateNotAvailable(final String tooltip, final Icon icon, final String text) {
        lblPicture.setIcon(icon);
        lblPicture.setText(text);
        lblPicture.setToolTipText(tooltip);
        showWait(false);
    }

    /**
     * DOCUMENT ME!
     *
     * @param  tooltip  DOCUMENT ME!
     */
    private void indicateError(final String tooltip) {
        lblPicture.setIcon(new ImageIcon(ERROR_IMAGE));
        lblPicture.setText("Fehler beim Übertragen des Bildes!");
        lblPicture.setToolTipText(tooltip);
        showWait(false);
    }

    @Override
    public CidsBean getCidsBean() {
        return representation;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        representation = cidsBean;
        final String urlString = (String)representation.getProperty("contentlocation");
        try {
            final URL url = new URL(urlString);
            new LoadSelectedImageWorker(url).execute();
        } catch (MalformedURLException ex) {
            LOG.error(ex, ex);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param   imageUrl  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    public static Image downloadImageOfUrl(final URL imageUrl) throws Exception {
        final SoftReference<Image> cachedImageRef = IMAGE_CACHE.get(imageUrl);
        if (cachedImageRef != null) {
            return cachedImageRef.get();
        }

        if (imageUrl != null) {
            InputStream is = null;
            try {
                is = WebAccessManager.getInstance().doRequest(imageUrl);
                final Image img = ImageIO.read(is);
                if (img != null) {
                    IMAGE_CACHE.put(imageUrl, new SoftReference<Image>(img));
                }
                return img;
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException ex) {
                        LOG.warn("Error during closing InputStream.", ex);
                    }
                }
            }
        }
        return null;
    }
    /**
     * DOCUMENT ME!
     *
     * @param   bi      DOCUMENT ME!
     * @param   insetX  DOCUMENT ME!
     * @param   insetY  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public Image adjustScale(final Image bi,
            final int insetX,
            final int insetY) {
        final Dimension size;
        if (sizeReference != null) {
            size = sizeReference.getSize();
            size.width = (int)(size.width * 0.75);
        } else {
            size = this.getSize();
        }
        final double scalex = size.getWidth() / bi.getWidth(null);
        final double scaley = size.getHeight() / bi.getHeight(null);
        final double scale = Math.min(scalex, scaley);
        if (scale <= 1d) {
            return bi.getScaledInstance((int)(bi.getWidth(null) * scale) - insetX,
                    (int)(bi.getHeight(null) * scale)
                            - insetY,
                    Image.SCALE_SMOOTH);
        } else {
            return bi;
        }
    }

    @Override
    public void componentResized(final ComponentEvent e) {
        if (!timer.isRunning()) {
            timer.setInitialDelay(300);
            timer.start();
        }
    }

    @Override
    public void componentMoved(final ComponentEvent e) {
    }

    @Override
    public void componentShown(final ComponentEvent e) {
        timer.setInitialDelay(0);
        timer.start();
    }

    @Override
    public void componentHidden(final ComponentEvent e) {
    }

    /**
     * DOCUMENT ME!
     *
     * @param  sizeReference  DOCUMENT ME!
     */
    public void setSizeReference(final JComponent sizeReference) {
        this.sizeReference = sizeReference;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public JComponent getSizeReference() {
        return sizeReference;
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    final class LoadSelectedImageWorker extends SwingWorker<Image, Void> {

        //~ Instance fields ----------------------------------------------------

        private final URL imageUrl;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new LoadSelectedImageWorker object.
         *
         * @param  imageUrl  toLoad DOCUMENT ME!
         */
        public LoadSelectedImageWorker(final URL imageUrl) {
            this.imageUrl = imageUrl;
            lblPicture.setText("");
            lblPicture.setToolTipText(null);
            showWait(!IMAGE_CACHE.containsKey(imageUrl));
        }

        //~ Methods ------------------------------------------------------------

        /**
         * DOCUMENT ME!
         *
         * @return  DOCUMENT ME!
         *
         * @throws  Exception  DOCUMENT ME!
         */
        @Override
        protected Image doInBackground() throws Exception {
            if (imageUrl != null) {
                return downloadImageOfUrl(imageUrl);
            }
            return null;
        }

        /**
         * DOCUMENT ME!
         */
        @Override
        protected void done() {
            try {
                originalImage = get();
                if (originalImage == null) {
                    indicateNotAvailable("");
                }
                lblPicture.setText("");
                lblPicture.setToolTipText(null);
                lblPicture.setIcon(new ImageIcon(adjustScale(originalImage, 20, 20)));
            } catch (InterruptedException ex) {
                originalImage = null;
                LOG.warn(ex, ex);
            } catch (ExecutionException ex) {
                originalImage = null;
                LOG.error(ex, ex);
                indicateNotAvailable("");
            } finally {
                showWait(false);
            }
        }
    }
}
