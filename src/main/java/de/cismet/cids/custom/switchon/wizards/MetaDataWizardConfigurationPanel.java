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

import de.cismet.commons.gui.wizard.AbstractWizardPanel;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class MetaDataWizardConfigurationPanel extends AbstractWizardPanel {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(MetaDataWizardConfigurationPanel.class);

    //~ Instance fields --------------------------------------------------------

    private String configuration;

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new MetaDataWizardConfigurationVisualPanel();
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        configuration = (String)wizard.getProperty(MetaDataWizardAction.PROP_CONFIGURATION);
        final MetaDataWizardConfigurationVisualPanel panel = (MetaDataWizardConfigurationVisualPanel)getComponent();
        panel.setConfiguration(configuration);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final MetaDataWizardConfigurationVisualPanel panel = (MetaDataWizardConfigurationVisualPanel)getComponent();
        configuration = panel.getConfigurationString();
        wizard.putProperty(MetaDataWizardAction.PROP_CONFIGURATION, configuration);
    }
}
