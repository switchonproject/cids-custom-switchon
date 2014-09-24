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

import java.util.List;

import de.cismet.cids.custom.switchon.wizards.DefaultPropertySetter;
import de.cismet.cids.custom.switchon.wizards.GenericAbstractWizardPanel;
import de.cismet.cids.custom.switchon.wizards.MetaDataWizardAction;
import de.cismet.cids.custom.switchon.wizards.NameProvider;

import de.cismet.cids.dynamics.CidsBean;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class RelationshipsImportDocumentPanel extends GenericAbstractWizardPanel<RelationshipsImportDocumentVisualPanel>
        implements NameProvider {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(RelationshipsImportDocumentPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new RelationshipsImportDocumentPanel object.
     */
    public RelationshipsImportDocumentPanel() {
        super(RelationshipsImportDocumentVisualPanel.class);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean relationship = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_CREATED_RELATIONSHIP_BEAN);
        final List<CidsBean> metaDatas = relationship.getBeanCollectionProperty("metadata");
        if (metaDatas.isEmpty()) {
            final CidsBean newMetaData = DefaultPropertySetter.createNewMetaDataForRelationshipCidsBean(relationship);
            if (newMetaData != null) {
                metaDatas.add(newMetaData);
            }
        }
        if (!metaDatas.isEmpty()) {
            getComponent().setCidsBean(metaDatas.get(0));
        }
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
    }

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(
                RelationshipsImportDocumentPanel.class,
                "RelationshipsImportDocumentPanel.name");
    }
}
