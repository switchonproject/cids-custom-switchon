/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

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
public class RelationshipsEditDocumentPanel extends AbstractWizardPanel implements NameProvider {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            RelationshipsEditDocumentPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new RelationshipsEditDocumentPanel object.
     */
    public RelationshipsEditDocumentPanel() {
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new RelationshipsEditDocumentVisualPanel();
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        LOG.fatal("RelationshipsEditDocumentPanel.read: Not supported yet.", new Exception()); // NOI18N
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        LOG.fatal("RelationshipsEditDocumentPanel.store: Not supported yet.", new Exception()); // NOI18N
    }

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(
                RelationshipsEditDocumentPanel.class,
                "RelationshipsEditDocumentPanel.name");
    }
}
