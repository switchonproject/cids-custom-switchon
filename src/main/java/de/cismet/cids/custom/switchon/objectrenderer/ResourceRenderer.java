/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objectrenderer;

import Sirius.navigator.ui.RequestsFullSizeComponent;

import org.apache.commons.lang.StringUtils;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.jdesktop.swingx.painter.MattePainter;

import java.awt.Color;
import java.awt.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.custom.switchon.gui.utils.ImageGetterUtils;
import de.cismet.cids.custom.switchon.gui.utils.Taggroups;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.tools.metaobjectrenderer.CidsBeanRenderer;

import de.cismet.tools.gui.TitleComponentProvider;

import static de.cismet.cids.custom.switchon.gui.utils.ResourceUtils.filterTagsOfResource;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class ResourceRenderer extends javax.swing.JPanel implements CidsBeanRenderer,
    TitleComponentProvider,
    RequestsFullSizeComponent {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ResourceRenderer.class);

    //~ Instance fields --------------------------------------------------------

    private CidsBean cidsBean;
    private final ResourceBundle topicBundle = ResourceBundle.getBundle(
            "de/cismet/cids/custom/switchon/tagBundles/topic");
    private final ResourceBundle roleBundle = ResourceBundle.getBundle(
            "de/cismet/cids/custom/switchon/tagBundles/role");

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.cismet.cids.custom.switchon.objectrenderer.ContactRenderer contactRenderer;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private de.cismet.cids.custom.switchon.objectrenderer.GeographicInformationPanel geographicInformationPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblKeywords;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTopic;
    private de.cismet.cids.custom.switchon.objecteditors.LicenseInformationPanel licenseInformationPanel;
    private javax.swing.JPanel panTitle;
    private javax.swing.JPanel panTitleString;
    private javax.swing.JPanel pnlContact;
    private javax.swing.JPanel pnlDataAccess;
    private javax.swing.JPanel pnlDescription;
    private javax.swing.JPanel pnlGeographic;
    private javax.swing.JPanel pnlLicense;
    private javax.swing.JPanel pnlMetaData;
    private javax.swing.JPanel pnlTemporal;
    private org.jdesktop.swingx.JXTaskPaneContainer taskPaneContainerDataAccess;
    private org.jdesktop.swingx.JXTaskPaneContainer taskPaneContainerMetaData;
    private de.cismet.cids.custom.switchon.objecteditors.TemporalInformationPanel temporalInformationPanel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form ResourceRenderer.
     */
    public ResourceRenderer() {
        // set the background of the JXTaskPaneContainer to the backround color of a panel, otherwise the background
        // will be blue in Windows
        final Color panelBackgroundColor = UIManager.getColor("Panel.background");
        UIManager.put("TaskPaneContainer.backgroundPainter", new MattePainter(panelBackgroundColor));
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

        panTitle = new javax.swing.JPanel();
        panTitleString = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlDescription = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        lblKeywords = new javax.swing.JLabel();
        lblTopic = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        pnlContact = new javax.swing.JPanel();
        contactRenderer = new de.cismet.cids.custom.switchon.objectrenderer.ContactRenderer();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(0, 32767));
        pnlGeographic = new javax.swing.JPanel();
        geographicInformationPanel = new de.cismet.cids.custom.switchon.objectrenderer.GeographicInformationPanel();
        pnlTemporal = new javax.swing.JPanel();
        temporalInformationPanel = new de.cismet.cids.custom.switchon.objecteditors.TemporalInformationPanel();
        pnlLicense = new javax.swing.JPanel();
        licenseInformationPanel = new de.cismet.cids.custom.switchon.objecteditors.LicenseInformationPanel();
        pnlMetaData = new javax.swing.JPanel();
        taskPaneContainerMetaData = new org.jdesktop.swingx.JXTaskPaneContainer();
        pnlDataAccess = new javax.swing.JPanel();
        taskPaneContainerDataAccess = new org.jdesktop.swingx.JXTaskPaneContainer();

        panTitle.setOpaque(false);
        panTitle.setLayout(new java.awt.BorderLayout());

        panTitleString.setOpaque(false);
        panTitleString.setLayout(new java.awt.GridBagLayout());

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18));                                                // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(
            lblTitle,
            org.openide.util.NbBundle.getMessage(ResourceRenderer.class, "ResourceRenderer.lblTitle.text")); // NOI18N
        lblTitle.setIconTextGap(5);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panTitleString.add(lblTitle, gridBagConstraints);

        panTitle.add(panTitleString, java.awt.BorderLayout.CENTER);

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        pnlDescription.setOpaque(false);
        pnlDescription.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(ResourceRenderer.class, "ResourceRenderer.jPanel1.border.title"))); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);

        final org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                this,
                org.jdesktop.beansbinding.ELProperty.create("${cidsBean.description}"),
                jTextArea1,
                org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(jTextArea1);

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
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        pnlDescription.add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(ResourceRenderer.class, "ResourceRenderer.jPanel2.border.title"))); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(
            lblKeywords,
            org.openide.util.NbBundle.getMessage(ResourceRenderer.class, "ResourceRenderer.lblKeywords.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 5, 10);
        jPanel2.add(lblKeywords, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 5, 10);
        jPanel2.add(lblTopic, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel1,
            org.openide.util.NbBundle.getMessage(ResourceRenderer.class, "ResourceRenderer.jLabel1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        jPanel2.add(jLabel1, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(
            jLabel2,
            org.openide.util.NbBundle.getMessage(ResourceRenderer.class, "ResourceRenderer.jLabel2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        jPanel2.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        pnlDescription.add(jPanel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        pnlDescription.add(filler1, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceRenderer.class,
                "ResourceRenderer.pnlDescription.TabConstraints.tabTitle"),
            null,
            pnlDescription,
            org.openide.util.NbBundle.getMessage(
                ResourceRenderer.class,
                "ResourceRenderer.pnlDescription.TabConstraints.tabToolTip")); // NOI18N

        pnlContact.setOpaque(false);
        pnlContact.setLayout(new java.awt.GridBagLayout());

        contactRenderer.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    ResourceRenderer.class,
                    "ResourceRenderer.contactRenderer.border.title"))); // NOI18N
        contactRenderer.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlContact.add(contactRenderer, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        pnlContact.add(filler2, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceRenderer.class,
                "ResourceRenderer.pnlContact.TabConstraints.tabTitle"),
            pnlContact); // NOI18N

        pnlGeographic.setOpaque(false);
        pnlGeographic.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlGeographic.add(geographicInformationPanel, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceRenderer.class,
                "ResourceRenderer.pnlGeographic.TabConstraints.tabTitle"),
            pnlGeographic); // NOI18N

        pnlTemporal.setOpaque(false);
        pnlTemporal.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlTemporal.add(temporalInformationPanel, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceRenderer.class,
                "ResourceRenderer.pnlTemporal.TabConstraints.tabTitle"),
            pnlTemporal); // NOI18N

        pnlLicense.setOpaque(false);
        pnlLicense.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlLicense.add(licenseInformationPanel, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceRenderer.class,
                "ResourceRenderer.pnlLicense.TabConstraints.tabTitle"),
            pnlLicense); // NOI18N

        pnlMetaData.setOpaque(false);
        pnlMetaData.setLayout(new java.awt.GridBagLayout());

        taskPaneContainerMetaData.setOpaque(false);
        final org.jdesktop.swingx.VerticalLayout verticalLayout1 = new org.jdesktop.swingx.VerticalLayout();
        verticalLayout1.setGap(14);
        taskPaneContainerMetaData.setLayout(verticalLayout1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlMetaData.add(taskPaneContainerMetaData, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceRenderer.class,
                "ResourceRenderer.pnlMetaData.TabConstraints.tabTitle"),
            pnlMetaData); // NOI18N

        pnlDataAccess.setOpaque(false);
        pnlDataAccess.setLayout(new java.awt.GridBagLayout());

        taskPaneContainerDataAccess.setOpaque(false);
        final org.jdesktop.swingx.VerticalLayout verticalLayout2 = new org.jdesktop.swingx.VerticalLayout();
        verticalLayout2.setGap(14);
        taskPaneContainerDataAccess.setLayout(verticalLayout2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlDataAccess.add(taskPaneContainerDataAccess, gridBagConstraints);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(
                ResourceRenderer.class,
                "ResourceRenderer.pnlDataAccess.TabConstraints.tabTitle"),
            pnlDataAccess); // NOI18N

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);

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

            final CidsBean contact = (CidsBean)cidsBean.getProperty("contact");
            contactRenderer.setCidsBean(contact);
            if (contact != null) {
                final TitledBorder contactBorder = (TitledBorder)contactRenderer.getBorder();
                final String role = (String)contact.getProperty("role.name");
                if (StringUtils.isNotBlank(role)) {
                    final String borderTitle = roleBundle.getString(role);
                    contactBorder.setTitle(borderTitle);
                }
            }

            geographicInformationPanel.setCidsBean(cidsBean);

            temporalInformationPanel.setCidsBean(cidsBean);

            licenseInformationPanel.setCidsBean(cidsBean);

            generateMetadataPanels();

            generateDataAccessPanels();

            bindingGroup.bind();
            generateListWithKeywords();

            final String topic = (String)cidsBean.getProperty("topiccategory.name");
            if (StringUtils.isNotBlank(topic)) {
                final String labelText = topicBundle.getString(topic.replace(' ', '_'));
                lblTopic.setText(labelText);
            } else {
                lblTopic.setText("");
            }

            setTitle();
        }
    }

    /**
     * DOCUMENT ME!
     */
    private void generateListWithKeywords() {
        final List<CidsBean> keywords = filterTagsOfResource(cidsBean, Taggroups.KEYWORDS_INSPIRE_THEMES_1_0);
        keywords.addAll(filterTagsOfResource(cidsBean, Taggroups.KEYWORDS_OPEN));
        Collections.sort(keywords, new Comparator<CidsBean>() {

                @Override
                public int compare(final CidsBean o1, final CidsBean o2) {
                    return o1.toString().compareTo(o2.toString());
                }
            });
        lblKeywords.setText("<html>" + StringUtils.join(keywords, ", ") + "</html>");
    }

    @Override
    public void dispose() {
        bindingGroup.unbind();
    }

    @Override
    public String getTitle() {
        return lblTitle.getText();
    }

    @Override
    public void setTitle(final String title) {
        setTitle();
    }

    /**
     * DOCUMENT ME!
     */
    public void setTitle() {
        String title = "new Resource";
        ImageIcon icon = null;

        if (cidsBean != null) {
            title = cidsBean.toString();
            final String resourceType = (String)cidsBean.getProperty("type.name");
            if ("experiment result data".equalsIgnoreCase(resourceType)) {
                icon = new ImageIcon(ImageGetterUtils.getImageForLetter('x', ImageGetterUtils.CIRCLE_LETTER_PATH));
            } else {
                icon = new ImageIcon(ImageGetterUtils.getImageForString(
                            resourceType,
                            ImageGetterUtils.CIRCLE_LETTER_PATH));
            }
        }

        lblTitle.setIcon(icon);
        lblTitle.setText(title);
    }

    @Override
    public JComponent getTitleComponent() {
        return panTitle;
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
            "resource",
            243,
            "Resource",
            1280,
            1024);
    }

    /**
     * DOCUMENT ME!
     */
    private void generateMetadataPanels() {
        final List<CidsBean> metadatas = cidsBean.getBeanCollectionProperty("metadata");
        boolean firstPane = true;
        for (final CidsBean metadata : metadatas) {
            final String metadataType = (String)metadata.getProperty("type.name");
            if ((metadataType != null) && !"basic meta-data".equalsIgnoreCase(metadataType)) {
                final JXTaskPane taskPane = new JXTaskPane();
                taskPane.setTitle(metadataType);
                taskPane.setIcon(new ImageIcon(
                        ImageGetterUtils.getImageForString(metadataType, ImageGetterUtils.DOCUMENT_LETTER_PATH)));

                final MetadataRenderer metadataRenderer = new MetadataRenderer();
                metadataRenderer.setCidsBean(metadata);
                taskPane.add(metadataRenderer);

                taskPane.setCollapsed(!firstPane);
                firstPane = false;
                taskPane.addPropertyChangeListener(new CollapseListener(taskPaneContainerMetaData, taskPane));

                taskPaneContainerMetaData.add(taskPane);
            }
        }
    }

    /**
     * DOCUMENT ME!
     */
    private void generateDataAccessPanels() {
        final List<CidsBean> representations = cidsBean.getBeanCollectionProperty("representation");
        boolean firstPane = true;
        for (final CidsBean representation : representations) {
            final String representationType = (String)representation.getProperty("type.name");
            if ("original data".equalsIgnoreCase(representationType)) {
                final String function = (String)representation.getProperty("function.name");

                final JXTaskPane taskPane = new JXTaskPane();
                taskPane.setTitle(representation.toString());
                taskPane.setIcon(new ImageIcon(
                        ImageGetterUtils.getImageForString(function, ImageGetterUtils.DOCUMENT_LETTER_PATH)));

                final RepresentationRenderer representationRenderer = new RepresentationRenderer();
                representationRenderer.setCidsBean(representation);
                taskPane.add(representationRenderer);

                taskPane.setCollapsed(!firstPane);
                firstPane = false;
                taskPane.addPropertyChangeListener(new CollapseListener(taskPaneContainerDataAccess, taskPane));

                taskPaneContainerDataAccess.add(taskPane);
            }
        }
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * A listener for JXTaskPanes which makes sure that only one JXTaskPane in a JXTaskPaneContainer is not collapsed.
     *
     * @version  $Revision$, $Date$
     */
    private static class CollapseListener implements PropertyChangeListener {

        //~ Instance fields ----------------------------------------------------

        private final JXTaskPaneContainer taskPaneContainer;
        private final JXTaskPane pane;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new CollapseListener object.
         *
         * @param  taskPaneContainer  DOCUMENT ME!
         * @param  pane               DOCUMENT ME!
         */
        public CollapseListener(final JXTaskPaneContainer taskPaneContainer, final JXTaskPane pane) {
            this.taskPaneContainer = taskPaneContainer;
            this.pane = pane;
        }

        //~ Methods ------------------------------------------------------------

        @Override
        public void propertyChange(final PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("collapsed") && evt.getNewValue().toString().equals("false")) {
                for (final Component c : taskPaneContainer.getComponents()) {
                    if ((c instanceof JXTaskPane) && !c.equals(pane)) {
                        ((JXTaskPane)c).setCollapsed(true);
                    }
                }
            }
        }
    }
}
