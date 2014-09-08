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
public class RelationshipsImportDocumentPanel extends AbstractWizardPanel implements NameProvider {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(RelationshipsImportDocumentPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new RelationshipsImportDocumentPanel object.
     */
    public RelationshipsImportDocumentPanel() {
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new RelationshipsImportDocumentVisualPanel();
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        LOG.fatal("RelationshipsImportDocumentPanel.read: Not supported yet.", new Exception()); // NOI18N
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        LOG.fatal("RelationshipsImportDocumentPanel.store: Not supported yet.", new Exception()); // NOI18N
    }

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(
                RelationshipsImportDocumentPanel.class,
                "RelationshipsImportDocumentPanel.name");
    }
}
