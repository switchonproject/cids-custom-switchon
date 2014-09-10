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

import java.awt.Component;

import de.cismet.cids.custom.switchon.wizards.NameProvider;

import de.cismet.commons.gui.wizard.AbstractWizardPanel;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class ResourceContactInformationPanel extends AbstractWizardPanel implements NameProvider {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(ResourceContactInformationPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new ResourceContactInformationPanel object.
     */
    public ResourceContactInformationPanel() {
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new ResourceContactInformationVisualPanel();
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        LOG.fatal("ResourceContactInformationPanel.read: Not supported yet.", new Exception()); // NOI18N
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        LOG.fatal("ResourceContactInformationPanel.store: Not supported yet.", new Exception()); // NOI18N
    }

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(
                ResourceContactInformationPanel.class,
                "ResourceContactInformationPanel.name");
    }
}
