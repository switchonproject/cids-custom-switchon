/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import org.apache.log4j.Logger;

import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;

import java.awt.Component;

import de.cismet.cids.custom.switchon.wizards.NameProvider;

import de.cismet.commons.gui.wizard.AbstractWizardPanel;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class AdditonalMetaDataBasicInformationPanel extends AbstractWizardPanel implements NameProvider {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(AdditonalMetaDataBasicInformationPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new AdditonalMetaDataBasicInformationPanel object.
     */
    public AdditonalMetaDataBasicInformationPanel() {
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new AdditonalMetaDataBasicInformationVisualPanel();
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        LOG.fatal("AdditonalMetaDataBasicInformationPanel.read: Not supported yet.", new Exception()); // NOI18N
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        LOG.fatal("AdditonalMetaDataBasicInformationPanel.store: Not supported yet.", new Exception()); // NOI18N
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(
                AdditonalMetaDataBasicInformationPanel.class,
                "AdditonalMetaDataBasicInformationPanel.name");
    }
}
