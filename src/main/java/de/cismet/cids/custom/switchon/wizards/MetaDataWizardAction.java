/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards;

import org.apache.log4j.Logger;

import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.text.MessageFormat;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.navigator.utils.CidsClientToolbarItem;

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

    public static final String PROP_PROJEKT = "__prop_projekt__"; // NOI18N
    private static String PROP_SELECTED_METADATA_BEAN;

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
                            try {
                                final CidsBean resource = (CidsBean)wizard.getProperty(
                                        MetaDataWizardAction.PROP_RESOURCE_BEAN);
                                resource.persist();
                            } catch (Exception ex) {
                                LOG.error("The resource bean could not be persisted.", ex);
                            }
                        } else if ((evt.getPropertyName() != null)
                                    && evt.getPropertyName().equals("__prop_configuration__")
                                    && evt.getNewValue().equals("basic")) {
                            setBasicDefaults(wizard);
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
        wizardDialog.setAlwaysOnTop(true);
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
    private void setBasicDefaults(final WizardDescriptor wizard) {
        DefaultPropertySetter.setDefaultsToResourceCidsBean((CidsBean)wizard.getProperty(
                MetaDataWizardAction.PROP_RESOURCE_BEAN));
        try {
            final CidsBean metadata = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "metadata");
            DefaultPropertySetter.setDefaultsToMetaDataCidsBean(metadata);
            wizard.putProperty(MetaDataWizardAction.PROP_SELECTED_METADATA_BEAN, metadata);
            final CidsBean metadataContact = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "contact");
            DefaultPropertySetter.setDefaultsToMetaDataContactCidsBean(metadataContact);
            metadata.setProperty("contact", metadataContact);
        } catch (Exception ex) {
            LOG.error(ex, ex);
        }
    }
}
