/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards;

import org.apache.log4j.Logger;

import org.openide.WizardDescriptor;

import java.awt.Component;

import java.lang.reflect.InvocationTargetException;

import java.util.HashMap;
import java.util.NoSuchElementException;

import javax.swing.JComponent;
import javax.swing.event.ChangeListener;

import de.cismet.cids.custom.switchon.wizards.panels.AdditonalMetaDataBasicInformationPanel;
import de.cismet.cids.custom.switchon.wizards.panels.AdditonalMetaDataContactInformationPanel;
import de.cismet.cids.custom.switchon.wizards.panels.AdditonalMetaDataEditDocumentPanel;
import de.cismet.cids.custom.switchon.wizards.panels.AdditonalMetaDataImportDocumentPanel;
import de.cismet.cids.custom.switchon.wizards.panels.AdditonalMetaDataPanel;
import de.cismet.cids.custom.switchon.wizards.panels.BasicResourcePropertiesPanel;
import de.cismet.cids.custom.switchon.wizards.panels.GeographicInformationPanel;
import de.cismet.cids.custom.switchon.wizards.panels.LicenseInformationPanel;
import de.cismet.cids.custom.switchon.wizards.panels.RelationshipsBasicInformationPanel;
import de.cismet.cids.custom.switchon.wizards.panels.RelationshipsEditDocumentPanel;
import de.cismet.cids.custom.switchon.wizards.panels.RelationshipsImportDocumentPanel;
import de.cismet.cids.custom.switchon.wizards.panels.RelationshipsPanel;
import de.cismet.cids.custom.switchon.wizards.panels.RepresentationsAdditionalInformationPanel;
import de.cismet.cids.custom.switchon.wizards.panels.RepresentationsBasicInformationPanel;
import de.cismet.cids.custom.switchon.wizards.panels.RepresentationsDataAccessInformationPanel;
import de.cismet.cids.custom.switchon.wizards.panels.RepresentationsDataImportPanel;
import de.cismet.cids.custom.switchon.wizards.panels.RepresentationsPanel;
import de.cismet.cids.custom.switchon.wizards.panels.ResourceContactInformationPanel;
import de.cismet.cids.custom.switchon.wizards.panels.TemporalInformationPanel;
import de.cismet.cids.custom.switchon.wizards.panels.TopicCategoryAndKeywordsPanel;

/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public final class MetaDataWizardIterator implements WizardDescriptor.Iterator {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(AdditonalMetaDataContactInformationPanel.class);

    //~ Instance fields --------------------------------------------------------

    HashMap<String, WizardDescriptor.Panel> allPanelsHashMap;

    private int index;

    private WizardDescriptor wizardDesc;
    private WizardDescriptor.Panel[] allPanels;

    private WizardDescriptor.Panel[] currentPanels;

    private WizardDescriptor.Panel[] basicSequence;
    private WizardDescriptor.Panel[] advancedSequence;
    private WizardDescriptor.Panel[] expertSequence;
    private WizardDescriptor.Panel[] customSequence;

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  wizardDescriptor  DOCUMENT ME!
     */
    public void initialize(final WizardDescriptor wizardDescriptor) {
        wizardDesc = wizardDescriptor;
    }

    /**
     * Initialize panels representing individual wizard's steps and sets.
     *
     * <p>various properties for them influencing wizard appearance.</p>
     */
    private void initializePanels() {
        if (allPanels == null) {
            createAllPanelsHashMap(
                MetaDataWizardConfigurationPanel.class,
                MetaDataWizardCustomConfigurationPanel.class,
                BasicResourcePropertiesPanel.class,
                TopicCategoryAndKeywordsPanel.class,
                ResourceContactInformationPanel.class,
                GeographicInformationPanel.class,
                TemporalInformationPanel.class,
                LicenseInformationPanel.class,
                AdditonalMetaDataPanel.class,
                AdditonalMetaDataBasicInformationPanel.class,
                AdditonalMetaDataContactInformationPanel.class,
                AdditonalMetaDataImportDocumentPanel.class,
                AdditonalMetaDataEditDocumentPanel.class,
                RepresentationsPanel.class,
                RepresentationsBasicInformationPanel.class,
                RepresentationsAdditionalInformationPanel.class,
                RepresentationsDataImportPanel.class,
                RepresentationsDataAccessInformationPanel.class,
                RelationshipsPanel.class,
                RelationshipsBasicInformationPanel.class,
                RelationshipsImportDocumentPanel.class,
                RelationshipsEditDocumentPanel.class);

            allPanels = allPanelsHashMap.values().toArray(new WizardDescriptor.Panel[allPanelsHashMap.size()]);

            basicSequence = createSequenceForClasses(
                    MetaDataWizardConfigurationPanel.class,
                    MetaDataWizardCustomConfigurationPanel.class,
                    BasicResourcePropertiesPanel.class,
                    TopicCategoryAndKeywordsPanel.class,
                    ResourceContactInformationPanel.class,
                    GeographicInformationPanel.class,
                    TemporalInformationPanel.class,
                    LicenseInformationPanel.class,
                    RepresentationsPanel.class,
                    RepresentationsDataAccessInformationPanel.class);
            advancedSequence = createSequenceForClasses(
                    MetaDataWizardConfigurationPanel.class,
                    MetaDataWizardCustomConfigurationPanel.class,
                    BasicResourcePropertiesPanel.class,
                    TopicCategoryAndKeywordsPanel.class,
                    ResourceContactInformationPanel.class,
                    GeographicInformationPanel.class,
                    TemporalInformationPanel.class,
                    LicenseInformationPanel.class,
                    AdditonalMetaDataPanel.class,
                    AdditonalMetaDataBasicInformationPanel.class,
                    AdditonalMetaDataContactInformationPanel.class,
                    AdditonalMetaDataImportDocumentPanel.class,
                    AdditonalMetaDataEditDocumentPanel.class,
                    RepresentationsPanel.class,
                    RepresentationsBasicInformationPanel.class,
                    RepresentationsAdditionalInformationPanel.class,
                    RepresentationsDataAccessInformationPanel.class);
            expertSequence = createSequenceForClasses(
                    MetaDataWizardConfigurationPanel.class,
                    MetaDataWizardCustomConfigurationPanel.class,
                    BasicResourcePropertiesPanel.class,
                    TopicCategoryAndKeywordsPanel.class,
                    ResourceContactInformationPanel.class,
                    GeographicInformationPanel.class,
                    TemporalInformationPanel.class,
                    LicenseInformationPanel.class,
                    AdditonalMetaDataPanel.class,
                    AdditonalMetaDataBasicInformationPanel.class,
                    AdditonalMetaDataContactInformationPanel.class,
                    AdditonalMetaDataImportDocumentPanel.class,
                    AdditonalMetaDataEditDocumentPanel.class,
                    RepresentationsPanel.class,
                    RepresentationsBasicInformationPanel.class,
                    RepresentationsAdditionalInformationPanel.class,
                    RepresentationsDataImportPanel.class,
                    RepresentationsDataAccessInformationPanel.class,
                    RelationshipsPanel.class,
                    RelationshipsBasicInformationPanel.class,
                    RelationshipsImportDocumentPanel.class,
                    RelationshipsEditDocumentPanel.class);

            currentPanels = createSequenceForClasses(
                    MetaDataWizardConfigurationPanel.class,
                    MetaDataWizardCustomConfigurationPanel.class);
        }
    }

    @Override
    public WizardDescriptor.Panel current() {
        initializePanels();

        return currentPanels[index];
    }

    @Override
    public String name() {
        if (index == 0) {
            return index + 1 + " of ...";
        }

        return index + 1 + " of " + currentPanels.length;
    }

    @Override
    public boolean hasNext() {
        initializePanels();

        return index < (currentPanels.length - 1);
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public void nextPanel() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (index == 0) {
            final String configuration = (String)wizardDesc.getProperty(MetaDataWizardAction.PROP_CONFIGURATION);
            switch (configuration) {
                case "basic": {
                    currentPanels = basicSequence;
                    break;
                }
                case "advanced": {
                    currentPanels = advancedSequence;
                    break;
                }
                case "expert": {
                    currentPanels = expertSequence;
                    break;
                }
            }
        }

        index++;

        wizardDesc.putProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, index);
    }

    @Override
    public void previousPanel() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }

        index--;

        wizardDesc.putProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, index);
    }

    @Override
    public void addChangeListener(final ChangeListener l) {
    }

    @Override
    public void removeChangeListener(final ChangeListener l) {
    }

    /**
     * DOCUMENT ME!
     *
     * @param  classes  DOCUMENT ME!
     */
    private void createAllPanelsHashMap(final Class<? extends WizardDescriptor.Panel>... classes) {
        allPanelsHashMap = new HashMap<String, WizardDescriptor.Panel>(classes.length);
        for (final Class<? extends WizardDescriptor.Panel> clazz : classes) {
            try {
                final WizardDescriptor.Panel panel = clazz.getConstructor().newInstance();
                allPanelsHashMap.put(clazz.getSimpleName(), panel);
            } catch (NoSuchMethodException ex) {
                LOG.error(ex, ex);
            } catch (SecurityException ex) {
                LOG.error(ex, ex);
            } catch (InstantiationException ex) {
                LOG.error(ex, ex);
            } catch (IllegalAccessException ex) {
                LOG.error(ex, ex);
            } catch (IllegalArgumentException ex) {
                LOG.error(ex, ex);
            } catch (InvocationTargetException ex) {
                LOG.error(ex, ex);
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param   classes  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private WizardDescriptor.Panel[] createSequenceForClasses(
            final Class<? extends WizardDescriptor.Panel>... classes) {
        final WizardDescriptor.Panel[] panels = new WizardDescriptor.Panel[classes.length];
        for (int i = 0; i < classes.length; i++) {
            final Class clazz = classes[i];
            panels[i] = allPanelsHashMap.get(clazz.getSimpleName());
        }
        return panels;
    }
}
