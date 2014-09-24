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

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.navigator.utils.CidsClientToolbarItem;
import de.cismet.cids.navigator.utils.ClassCacheMultiple;

import de.cismet.cismap.commons.interaction.CismapBroker;

import de.cismet.tools.gui.StaticSwingTools;

/**
 * A wizard which creates a new Resource CidsBean with its sub-cidsBeans. The wizard has four configurations Basic,
 * Advanced, Expert and Custom.
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
    public static final String PROP_AdditonalMetaDataImportDocumentPanel_WAS_OPENED =
        "__prop_AdditonalMetaDataImportDocumentPanel_was_opened__";                                         // NOI18N

    public static final String PROP_PROJEKT = "__prop_projekt__"; // NOI18N
    public static String PROP_RepresentationsDataImportPanel_WAS_OPENED =
        "__prop_RepresentationsDataImportPanel_was_opened__";     // NOI18N
    public static String PROP_RelationshipsImportDocumentPanel_WAS_OPENED =
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
        final WizardDescriptor.Iterator iterator = new MetaDataWizardIterator();
        final WizardDescriptor wizard = new WizardDescriptor(iterator);
        ((MetaDataWizardIterator)iterator).initialize(wizard);
        wizard.putProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, Boolean.TRUE);
        wizard.putProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, Boolean.TRUE);
        wizard.putProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, Boolean.TRUE);

        // additional properties
        wizard.putProperty(PROP_AdditonalMetaDataImportDocumentPanel_WAS_OPENED, Boolean.FALSE);
        wizard.putProperty(PROP_RepresentationsDataImportPanel_WAS_OPENED, Boolean.FALSE);
        wizard.putProperty(PROP_RelationshipsImportDocumentPanel_WAS_OPENED, Boolean.FALSE);

        // set the subtitle. The String is retrieved from iterator.name()
        wizard.setTitleFormat(new MessageFormat("{1}"));
        wizard.setTitle("Meta-Data Wizard");

        wizard.addPropertyChangeListener(new PropertyChangeListener() {

                @Override
                public void propertyChange(final PropertyChangeEvent evt) {
                    if (evt.getNewValue() != null) {
                        if ("org.openide.WizardDescriptor.FinishAction".equals(
                                        evt.getNewValue().getClass().getCanonicalName())) {
                            // persist the resource bean, when the wizard finished
                            new CidsBeanPersistWorker(wizard).execute();
                        } else if ((evt.getPropertyName() != null)
                                    && evt.getPropertyName().equals(PROP_CONFIGURATION)) {
                            // set the defaults based on the chosen profile
                            if (evt.getNewValue().equals("basic")) {
                                setBasicDefaults(wizard);
                            } else if (evt.getNewValue().equals("advanced")) {
                                setAdvancedDefaults(wizard);
                            } else if (evt.getNewValue().equals("expert")) {
                                setExpertDefaults(wizard);
                            }
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
        wizardDialog.setSize(860, 560);
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

        setStandardMetaData(resource);
    }

    /**
     * DOCUMENT ME!
     *
     * @param  resource  DOCUMENT ME!
     */
    private void setStandardMetaData(final CidsBean resource) {
        new StandardMetaDataFetcher(resource).execute();
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private class CidsBeanPersistWorker extends SwingWorker<CidsBean, Void> {

        //~ Instance fields ----------------------------------------------------

        WizardDescriptor wizard;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new CidsBeanPersistWorker object.
         *
         * @param  wizard  DOCUMENT ME!
         */
        public CidsBeanPersistWorker(final WizardDescriptor wizard) {
            this.wizard = wizard;
        }

        //~ Methods ------------------------------------------------------------

        @Override
        protected CidsBean doInBackground() throws Exception {
            try {
                final CidsBean resource = (CidsBean)wizard.getProperty(
                        MetaDataWizardAction.PROP_RESOURCE_BEAN);
                return resource.persist();
            } catch (Exception ex) {
                LOG.error("The resource bean could not be persisted.", ex);
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
                standardMetaObject = createNewStandardMetaData();
            }
            CidsBean switchOnContact = null;
            try {
                switchOnContact = fetchSwitchOnContact();
            } catch (Exception ex) {
                LOG.error("Error while fetching switchon contact. Creating new meta object instead.", ex);
            }
            if (switchOnContact == null) {
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

            final MetaObject[] mos = SessionManager.getProxy()
                        .getMetaObjectByQuery(SessionManager.getSession().getUser(), query, "SWITCHON");
            if (mos.length >= 1) {
                return mos[0].getBean();
            } else {
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

            final MetaObject[] mos = SessionManager.getProxy()
                        .getMetaObjectByQuery(SessionManager.getSession().getUser(), query, "SWITCHON");
            if (mos.length >= 1) {
                return mos[0].getBean();
            } else {
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
            } catch (InterruptedException ex) {
                LOG.error(ex, ex);
            } catch (ExecutionException ex) {
                LOG.error(ex, ex);
            }
        }
    }
}
