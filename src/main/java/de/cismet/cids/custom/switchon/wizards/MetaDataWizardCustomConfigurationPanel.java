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
public class MetaDataWizardCustomConfigurationPanel extends AbstractWizardPanel {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(MetaDataWizardCustomConfigurationPanel.class);

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new MetaDataWizardCustomConfigurationVisualPanel();
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
    }
}
