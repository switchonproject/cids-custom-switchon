/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards;

import Sirius.navigator.connection.SessionManager;
import Sirius.navigator.exception.ConnectionException;
import Sirius.navigator.exception.ExceptionManager;
import Sirius.navigator.types.treenode.RootTreeNode;
import Sirius.navigator.ui.ComponentRegistry;

import Sirius.server.middleware.types.MetaClass;
import Sirius.server.middleware.types.MetaObject;

import org.apache.log4j.Logger;

import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.error.ErrorInfo;

import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.text.MessageFormat;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.navigator.utils.CidsClientToolbarItem;
import de.cismet.cids.navigator.utils.ClassCacheMultiple;

import de.cismet.cismap.commons.interaction.CismapBroker;

import de.cismet.tools.gui.StaticSwingTools;
import de.cismet.tools.gui.WaitDialog;

/**
 * A wizard which creates a new Resource CidsBean with its sub-cidsBeans. The wizard has three configurations Basic,
 * Advanced and Expert.
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
@org.openide.util.lookup.ServiceProvider(service = CidsClientToolbarItem.class)
public class MetaDataWizardAction extends AbstractAction implements CidsClientToolbarItem {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(MetaDataWizardAction.class);

    public static final String PROP_CONFIGURATION = "__prop_configuration__";                               // NOI18N
    public static final String PROP_RESOURCE_BEAN = "__prop_resource_bean__";                               // NOI18N
    public static final String PROP_CONTACT_BEAN = "__prop_contact_bean__";                                 // NOI18N
    public static final String PROP_SELECTED_REPRESENTATION_BEAN = "__prop_selected_representation_bean__"; // NOI18N
    public static final String PROP_SELECTED_METADATA_BEAN = "__prop_selected_metadata_bean__";             // NOI18N
    public static final String PROP_AdditonalMetaDataImportDocumentPanel_IMPORT_BUTTON_WAS_PRESSED =
        "__prop_AdditonalMetaDataImportDocumentPanel_was_opened__";                                         // NOI18N

    public static final String PROP_PROJEKT = "__prop_projekt__"; // NOI18N
    public static String PROP_RepresentationsDataImportPanel_IMPORT_BUTTON_PRESSED =
        "__prop_RepresentationsDataImportPanel_was_opened__";     // NOI18N
    public static String PROP_RelationshipsImportDocumentPanel_IMPORT_BUTTON_WAS_PRESSED =
        "__prop_RelationshipsImportDocumentPanel_was_opened__";   // NOI18N

    public static String PROP_CREATED_RELATIONSHIP_BEAN = "__prop_created_relationship__"; // NOI18N

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new ImportDocumentWizardAction object.
     */
    public MetaDataWizardAction() {
        putValue(SHORT_DESCRIPTION, "open Meta-Data wizard");
        final ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource(
                    "/de/cismet/cids/custom/switchon/wizards/wand.png"));
        putValue(SMALL_ICON, icon);
        putValue(NAME, "open Meta-Data wizard");
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public void actionPerformed(final ActionEvent e) {
        this.setEnabled(false);
        final WizardDescriptor.Iterator iterator = new MetaDataWizardIterator();
        final WizardDescriptor wizard = new WizardDescriptor(iterator);
        ((MetaDataWizardIterator)iterator).initialize(wizard);
        wizard.putProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, Boolean.TRUE);
        wizard.putProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, Boolean.TRUE);
        wizard.putProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, Boolean.TRUE);

        // additional properties
        wizard.putProperty(PROP_AdditonalMetaDataImportDocumentPanel_IMPORT_BUTTON_WAS_PRESSED, Boolean.FALSE);
        wizard.putProperty(PROP_RepresentationsDataImportPanel_IMPORT_BUTTON_PRESSED, Boolean.FALSE);
        wizard.putProperty(PROP_RelationshipsImportDocumentPanel_IMPORT_BUTTON_WAS_PRESSED, Boolean.FALSE);

        // set the subtitle. The String is retrieved from iterator.name()
        wizard.setTitleFormat(new MessageFormat("{1}"));
        wizard.setTitle("Meta-Data Wizard");

        wizard.addPropertyChangeListener(new PropertyChangeListener() {

                @Override
                public void propertyChange(final PropertyChangeEvent evt) {
                    // enable the action again on cancel, close and finish
                    if (WizardDescriptor.CANCEL_OPTION.equals(wizard.getValue())
                                || WizardDescriptor.CLOSED_OPTION.equals(wizard.getValue())
                                || WizardDescriptor.FINISH_OPTION.equals(wizard.getValue())) {
                        MetaDataWizardAction.this.setEnabled(true);
                    }
                    // WizardDescriptor.FINISH_OPTION.equals(wizard.getValue()) can not be used, as such an event is
                    // fired twice
                    if ((evt.getNewValue() != null)
                                && "org.openide.WizardDescriptor.FinishAction".equals(
                                    evt.getNewValue().getClass().getCanonicalName())) {
                        // persist the resource bean, when the wizard is finished
                        final WaitDialog waitDialog = new WaitDialog(
                                JFrame.getFrames()[0],
                                true,
                                "Please wait while the Resource is saved",
                                new javax.swing.ImageIcon(getClass().getResource("/images/3floppy_unmount.png")));

                        new CidsBeanPersistWorker(wizard, waitDialog).execute();
                        final Frame parent = StaticSwingTools.getParentFrame(
                                CismapBroker.getInstance().getMappingComponent());
                        if (waitDialog instanceof JDialog) {
                            StaticSwingTools.showDialog(parent, (JDialog)waitDialog, true);
                        } else {
                            waitDialog.setLocationRelativeTo(parent);
                            waitDialog.setVisible(true);
                        }
                        waitDialog.toFront();
                    }
                    if (PROP_CONFIGURATION.equals(evt.getPropertyName())) {
                        final Object newValue = evt.getNewValue();
                        // set the defaults based on the chosen profile
                        if ("basic".equals(newValue)) {
                            setBasicDefaults(wizard);
                        } else if ("advanced".equals(newValue)) {
                            setAdvancedDefaults(wizard);
                        } else if ("expert".equals(newValue)) {
                            setExpertDefaults(wizard);
                        }
                    }
                }
            });

        // create new cidsBeans
        try {
            final CidsBean resource = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "resource");
            wizard.putProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN, resource);
        } catch (Exception ex) {
            LOG.error(ex, ex);
            return;
        }

        final Frame parent = StaticSwingTools.getParentFrame(CismapBroker.getInstance().getMappingComponent());
        final Dialog wizardDialog = DialogDisplayer.getDefault().createDialog(wizard);
        wizardDialog.setSize(900, 620);
        wizardDialog.setModal(false);
        if (wizardDialog instanceof JDialog) {
            StaticSwingTools.showDialog(parent, (JDialog)wizardDialog, true);
        } else {
            wizardDialog.setLocationRelativeTo(parent);
            wizardDialog.setVisible(true);
        }
        wizardDialog.toFront();
    }

    @Override
    public String getSorterString() {
        return "Dokument Import Wizard";
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  wizard  DOCUMENT ME!
     */
    private void setExpertDefaults(final WizardDescriptor wizard) {
        setAdvancedDefaults(wizard);
    }

    /**
     * DOCUMENT ME!
     *
     * @param  wizard  DOCUMENT ME!
     */
    private void setAdvancedDefaults(final WizardDescriptor wizard) {
        final CidsBean resource = (CidsBean)wizard.getProperty(
                MetaDataWizardAction.PROP_RESOURCE_BEAN);
        DefaultPropertySetter.setDefaultsToResourceCidsBean(resource);

        setStandardMetaData(resource);
    }

    /**
     * DOCUMENT ME!
     *
     * @param  wizard  DOCUMENT ME!
     */
    private void setBasicDefaults(final WizardDescriptor wizard) {
        final CidsBean resource = (CidsBean)wizard.getProperty(
                MetaDataWizardAction.PROP_RESOURCE_BEAN);
        DefaultPropertySetter.setDefaultsToResourceCidsBean(resource);

        try {
            // don't set default dates, they may be wrong!
            // DefaultPropertySetter.setDefaultDatesToResourceCidsBean(resource);

            final CidsBean representation = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "representation");
            DefaultPropertySetter.setDefaultsToRepresentationCidsBean(representation);
            wizard.putProperty(MetaDataWizardAction.PROP_SELECTED_REPRESENTATION_BEAN, representation);
            resource.getBeanCollectionProperty("representation").add(representation);
        } catch (Exception ex) {
            LOG.error(ex, ex);
        }

        setStandardMetaData(resource);
    }

    /**
     * Fetches the standard meta data from the database in a SwingWorker. If it does not exist yet it will be created.
     *
     * @param  resource  DOCUMENT ME!
     */
    private void setStandardMetaData(final CidsBean resource) {
        new StandardMetaDataFetcher(resource).execute();
    }

    /**
     * DOCUMENT ME!
     *
     * @param   resource  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    private void setStandardContact(final CidsBean resource) throws Exception {
        if ((resource.getBeanCollectionProperty("metadata") != null)
                    && (resource.getBeanCollectionProperty("metadata").size() == 1)) {
            final CidsBean standardMetaData = resource.getBeanCollectionProperty("metadata").get(0);
            resource.setProperty("contact", standardMetaData.getProperty("contact"));
        } else {
            LOG.warn("could not set default contact information");
        }
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private class CidsBeanPersistWorker extends SwingWorker<CidsBean, Void> {

        //~ Instance fields ----------------------------------------------------

        final WizardDescriptor wizard;
        final WaitDialog waitDialog;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new CidsBeanPersistWorker object.
         *
         * @param  wizard      DOCUMENT ME!
         * @param  waitDialog  DOCUMENT ME!
         */
        public CidsBeanPersistWorker(final WizardDescriptor wizard, final WaitDialog waitDialog) {
            this.wizard = wizard;
            this.waitDialog = waitDialog;
        }

        //~ Methods ------------------------------------------------------------

        @Override
        protected CidsBean doInBackground() throws Exception {
            try {
                final CidsBean relationship = (CidsBean)wizard.getProperty(
                        MetaDataWizardAction.PROP_CREATED_RELATIONSHIP_BEAN);

                CidsBean persistedBean;
                if (relationship != null) {
                    LOG.info("saving new relationship object '" + relationship.getProperty("name") + "'");
                    persistedBean = relationship.persist();
                } else {
                    final CidsBean resource = (CidsBean)wizard.getProperty(
                            MetaDataWizardAction.PROP_RESOURCE_BEAN);

                    // set basic representation information
                    if (wizard.getProperty(MetaDataWizardAction.PROP_CONFIGURATION).equals("basic")) {
                        final List<CidsBean> representations = resource.getBeanCollectionProperty("representation");
                        if ((representations != null) && (representations.size() == 1)) {
                            final CidsBean representation = representations.get(0);
                            DefaultPropertySetter.setDefaultsToRepresentationCidsBeanDerivedByResource(
                                representation,
                                resource);
                        } else {
                            LOG.warn("could not set default representation information of resource");
                        }
                    }

                    LOG.info("saving new resource '" + resource.getProperty("name") + "'");
                    persistedBean = resource.persist();
                }

                return persistedBean;
            } catch (Exception ex) {
                LOG.error("The resource bean could not be persisted.", ex);
                waitDialog.dispose();
                ExceptionManager.getManager()
                        .showExceptionDialog(
                            ExceptionManager.ERROR,
                            "Error in Meta-Data Wizard",
                            "<html><strong>Could not save resource in Meta-Data Repository:</<strong><br>"
                            + "<p><i>"
                            + ex.getMessage()
                            + "</i></p>",
                            ex);
            }
            return null;
        }

        @Override
        protected void done() {
            try {
                get();
                // reload the tree
                try {
                    final TreePath selectionPath = ComponentRegistry.getRegistry()
                                .getCatalogueTree()
                                .getSelectionPath();
                    if ((selectionPath != null) && (selectionPath.getPath().length > 0)) {
                        final RootTreeNode rootTreeNode = new RootTreeNode(SessionManager.getProxy().getRoots());
                        ((DefaultTreeModel)ComponentRegistry.getRegistry().getCatalogueTree().getModel()).setRoot(
                            rootTreeNode);
                        ((DefaultTreeModel)ComponentRegistry.getRegistry().getCatalogueTree().getModel()).reload();
                        ComponentRegistry.getRegistry().getCatalogueTree().exploreSubtree(selectionPath);
                    }
                } catch (ConnectionException ex) {
                    LOG.error("Error while refreshing the tree", ex); // NOI18N
                } catch (RuntimeException ex) {
                    LOG.error("Error while refreshing the tree", ex); // NOI18N
                } finally {
                    waitDialog.dispose();
                }
            } catch (Exception ex) {
                LOG.warn(ex, ex);
                final ErrorInfo info = new ErrorInfo(
                        "Persist error",
                        "The resource bean could not be persisted.",
                        null,
                        "ERROR",
                        ex,
                        Level.SEVERE,
                        null);
                JXErrorPane.showDialog(ComponentRegistry.getRegistry().getMainWindow(), info);
            } finally {
                waitDialog.dispose();
            }
        }
    }

    /**
     * The main task of this class is to fetch the standard meta data from the database and add it to a resource. To
     * achieve this it tries to fetch the standard meta data-CidsBean and a Switch-On contact-CidsBean. If this fails it
     * creates a new CidsBean, using the methods from {@link DefaultPropertySetter}. Finally the standard meta
     * data-CidsBean is added to the resource-CidsBean.
     *
     * @version  $Revision$, $Date$
     */
    private class StandardMetaDataFetcher extends SwingWorker<CidsBean, Void> {

        //~ Instance fields ----------------------------------------------------

        CidsBean resource;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new StandardMetaDataFetcher object.
         *
         * @param  resource  DOCUMENT ME!
         */
        public StandardMetaDataFetcher(final CidsBean resource) {
            this.resource = resource;
        }

        //~ Methods ------------------------------------------------------------

        @Override
        protected CidsBean doInBackground() throws Exception {
            CidsBean standardMetaObject = null;
            try {
                standardMetaObject = fetchStandardMetaData();
            } catch (Exception ex) {
                LOG.error("Error while fetching standard meta object. Creating new meta object instead.", ex);
            }
            if (standardMetaObject == null) {
                LOG.warn("Error while fetching standard meta object. Creating new meta object instead.");
                standardMetaObject = createNewStandardMetaData();
            }
            CidsBean switchOnContact = null;
            try {
                switchOnContact = fetchSwitchOnContact();
            } catch (Exception ex) {
                LOG.error("Error while fetching switchon contact. Creating new meta object instead.", ex);
            }
            if (switchOnContact == null) {
                LOG.warn("Error while fetching standard contact object. Creating new contact object instead.");
                switchOnContact = createNewSwitchOnContact();
            }

            standardMetaObject.setProperty("contact", switchOnContact);
            return standardMetaObject;
        }

        /**
         * DOCUMENT ME!
         *
         * @return  DOCUMENT ME!
         *
         * @throws  Exception  DOCUMENT ME!
         */
        private CidsBean fetchStandardMetaData() throws Exception {
            final MetaClass MB_MC = ClassCacheMultiple.getMetaClass("SWITCHON", "metadata");
            String query = "SELECT " + MB_MC.getID() + ", " + MB_MC.getPrimaryKey() + " ";
            query += "FROM " + MB_MC.getTableName();
            query += " WHERE name ilike '" + DefaultPropertySetter.defaultNameMetaData + "' limit 1";
            if (LOG.isDebugEnabled()) {
                LOG.debug("fetching default meta-data '" + DefaultPropertySetter.defaultNameMetaData
                            + "' with query: \n" + query);
            }
            final MetaObject[] mos = SessionManager.getProxy()
                        .getMetaObjectByQuery(SessionManager.getSession().getUser(), query, "SWITCHON");
            if (mos.length >= 1) {
                return mos[0].getBean();
            } else {
                LOG.error("no default meta-data information found in meta-data repository for query: \n" + query);
                return null;
            }
        }

        /**
         * DOCUMENT ME!
         *
         * @return  DOCUMENT ME!
         *
         * @throws  Exception  DOCUMENT ME!
         */
        private CidsBean createNewStandardMetaData() throws Exception {
            final CidsBean standardMetaObject = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "metadata");
            DefaultPropertySetter.setDefaultsToMetaDataCidsBeanForBasicProfile(standardMetaObject);
            return standardMetaObject;
        }

        /**
         * DOCUMENT ME!
         *
         * @return  DOCUMENT ME!
         *
         * @throws  Exception  DOCUMENT ME!
         */
        private CidsBean fetchSwitchOnContact() throws Exception {
            final MetaClass MB_MC = ClassCacheMultiple.getMetaClass("SWITCHON", "contact");
            String query = "SELECT " + MB_MC.getID() + ", " + MB_MC.getPrimaryKey() + " ";
            query += "FROM " + MB_MC.getTableName();
            query += " WHERE name ilike '" + DefaultPropertySetter.defaultNameMetaDataContact + "'";
            query += " AND organisation ilike '" + DefaultPropertySetter.defaultOrganisationMetaDataContact + "'";
            query += " limit 1";
            if (LOG.isDebugEnabled()) {
                LOG.debug("fetching default contact '" + DefaultPropertySetter.defaultNameMetaDataContact
                            + "' with query: \n" + query);
            }
            final MetaObject[] mos = SessionManager.getProxy()
                        .getMetaObjectByQuery(SessionManager.getSession().getUser(), query, "SWITCHON");
            if (mos.length >= 1) {
                return mos[0].getBean();
            } else {
                LOG.error("no default contact information found in meta-data repository for query: \n" + query);
                return null;
            }
        }

        /**
         * DOCUMENT ME!
         *
         * @return  DOCUMENT ME!
         *
         * @throws  Exception  DOCUMENT ME!
         */
        private CidsBean createNewSwitchOnContact() throws Exception {
            final CidsBean metadataContact = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "contact");
            DefaultPropertySetter.setDefaultsToMetaDataContactCidsBean(metadataContact);
            return metadataContact;
        }

        @Override
        protected void done() {
            try {
                final CidsBean standardMetaObject = get();
                resource.getBeanCollectionProperty("metadata").add(standardMetaObject);
                setStandardContact(resource);
            } catch (InterruptedException ex) {
                LOG.error(ex, ex);
            } catch (ExecutionException ex) {
                LOG.error(ex, ex);
            } catch (Exception ex) {
                LOG.error(ex, ex);
            }
        }
    }
}
