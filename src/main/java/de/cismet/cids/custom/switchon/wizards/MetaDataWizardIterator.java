/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import org.openide.WizardDescriptor;

import java.lang.reflect.InvocationTargetException;

import java.util.HashMap;
import java.util.NoSuchElementException;

import javax.swing.event.ChangeListener;

import de.cismet.cids.custom.switchon.wizards.panels.AdditonalMetaDataBasicInformationPanel;
import de.cismet.cids.custom.switchon.wizards.panels.AdditonalMetaDataContactInformationPanel;
import de.cismet.cids.custom.switchon.wizards.panels.AdditonalMetaDataEditDocumentPanel;
import de.cismet.cids.custom.switchon.wizards.panels.AdditonalMetaDataImportDocumentPanel;
import de.cismet.cids.custom.switchon.wizards.panels.AdditonalMetaDataPanel;
import de.cismet.cids.custom.switchon.wizards.panels.ContactInformationPanel;
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
import de.cismet.cids.custom.switchon.wizards.panels.ResourceBasicInformationPanel;
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
                ResourceBasicInformationPanel.class,
                TopicCategoryAndKeywordsPanel.class,
                ContactInformationPanel.class,
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
                    ResourceBasicInformationPanel.class,
                    TopicCategoryAndKeywordsPanel.class,
                    /*ContactInformationPanel.class,*/
                    GeographicInformationPanel.class,
                    /*TemporalInformationPanel.class,*/
                    LicenseInformationPanel.class,
                    RepresentationsDataAccessInformationPanel.class);
            advancedSequence = createSequenceForClasses(
                    MetaDataWizardConfigurationPanel.class,
                    ResourceBasicInformationPanel.class,
                    TopicCategoryAndKeywordsPanel.class,
                    ContactInformationPanel.class,
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
                    RepresentationsDataAccessInformationPanel.class);
            expertSequence = createSequenceForClasses(
                    MetaDataWizardConfigurationPanel.class,
                    ResourceBasicInformationPanel.class,
                    TopicCategoryAndKeywordsPanel.class,
                    ContactInformationPanel.class,
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
                    MetaDataWizardConfigurationPanel.class);

            wizardDesc.putProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, index);
            wizardDesc.putProperty(WizardDescriptor.PROP_CONTENT_DATA, createSubtitlesForCurrentPanels());
        }
    }

    @Override
    public WizardDescriptor.Panel current() {
        initializePanels();

        return currentPanels[index];
    }

    @Override
    public String name() {
        String name = getPanelName(currentPanels[index]);

        if (index == 0) {
            name += " " + (index + 1) + " of ...";
        } else {
            name += " " + (index + 1) + " of " + currentPanels.length;
        }
        return name;
    }

    @Override
    public boolean hasNext() {
        final WizardDescriptor.Panel current = current();
        if ((current instanceof WizardDescriptor.FinishablePanel)
                    && ((WizardDescriptor.FinishablePanel)current).isFinishPanel()) {
            return false;
        }
        return current().isValid();
    }

    @Override
    public boolean hasPrevious() {
        return index > 1;
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
                    wizardDesc.putProperty(WizardDescriptor.PROP_CONTENT_DATA, createSubtitlesForCurrentPanels());
                    setFinishPanel(currentPanels[currentPanels.length - 1]);
                    break;
                }
                case "advanced": {
                    currentPanels = advancedSequence;
                    wizardDesc.putProperty(WizardDescriptor.PROP_CONTENT_DATA, createSubtitlesForCurrentPanels());
                    setFinishPanel(currentPanels[currentPanels.length - 1]);
                    break;
                }
                case "expert": {
                    currentPanels = expertSequence;
                    wizardDesc.putProperty(WizardDescriptor.PROP_CONTENT_DATA, createSubtitlesForCurrentPanels());
                    setFinishPanel(currentPanels[currentPanels.length - 1]);
                    break;
                }
            }
        }

        if ((current() instanceof LeapOtherPanels)) {
            final WizardDescriptor.Panel nextPanel = allPanelsHashMap.get(((LeapOtherPanels)current())
                            .nextPanelClassSimpleName());
            final int indexOfNextPanel = ArrayUtils.indexOf(currentPanels, nextPanel);
            if (indexOfNextPanel >= 0) {
                index = indexOfNextPanel;
            } else {
                LOG.info("Such a panel does not exists, going to next panel.");
                index++;
            }
        } else {
            index++;
        }

        wizardDesc.putProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, index);
    }

    @Override
    public void previousPanel() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }

        if ((current() instanceof LeapOtherPanels)) {
            final WizardDescriptor.Panel previousPanel = allPanelsHashMap.get(((LeapOtherPanels)current())
                            .previousPanelClassSimpleName());
            final int indexOfNextPanel = ArrayUtils.indexOf(currentPanels, previousPanel);
            if (indexOfNextPanel >= 0) {
                index = indexOfNextPanel;
            } else {
                LOG.info("Such a panel does not exists, going to previous panel.");
                index--;
            }
        } else {
            index--;
        }

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

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private String[] createSubtitlesForCurrentPanels() {
        final String[] subtitles = new String[currentPanels.length];
        for (int i = 0; i < currentPanels.length; i++) {
            subtitles[i] = getPanelName(currentPanels[i]);
        }
        return subtitles;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   panel  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private String getPanelName(final WizardDescriptor.Panel panel) {
        String name = "Unkown";
        if (panel instanceof NameProvider) {
            name = ((NameProvider)panel).getName();
        }
        return name;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  panel  DOCUMENT ME!
     */
    private void setFinishPanel(final WizardDescriptor.Panel panel) {
        if (panel instanceof AdvancedFinishablePanel) {
            ((AdvancedFinishablePanel)panel).setFinishPanel(true);
        } else {
            LOG.warn(panel.getClass().getSimpleName() + " is not an AdvancedFinishablePanel", null);
        }
    }
}
