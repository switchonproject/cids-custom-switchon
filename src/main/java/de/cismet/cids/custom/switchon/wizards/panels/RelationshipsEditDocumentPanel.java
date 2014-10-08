/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import org.apache.commons.lang.StringUtils;

import org.openide.WizardDescriptor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.List;

import de.cismet.cids.custom.switchon.wizards.AdvancedFinishablePanel;
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
public class RelationshipsEditDocumentPanel extends GenericAbstractWizardPanel<AdditonalMetaDataEditDocumentVisualPanel>
        implements NameProvider,
            PropertyChangeListener,
            AdvancedFinishablePanel {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            RelationshipsEditDocumentPanel.class);

    //~ Instance fields --------------------------------------------------------

    private boolean finishPanel = false;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new RelationshipsEditDocumentPanel object.
     */
    public RelationshipsEditDocumentPanel() {
        super(AdditonalMetaDataEditDocumentVisualPanel.class);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean relationship = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_CREATED_RELATIONSHIP_BEAN);
        final List<CidsBean> metaDatas = relationship.getBeanCollectionProperty("metadata");     // NOI18N
        if (metaDatas.isEmpty()) {
            final CidsBean newMetaData = DefaultPropertySetter.createNewMetaDataForRelationshipCidsBean(relationship);
            if (newMetaData != null) {
                metaDatas.add(newMetaData);
            }
        }
        if (!metaDatas.isEmpty()) {
            final CidsBean metaData = relationship.getBeanCollectionProperty("metadata").get(0); // NOI18N
            // disable the content and content location components if RelationshipsImportDocumentPanel was already
            // open
            getComponent().changeAppearanceAsImportButtonWasPressed((boolean)wizard.getProperty(
                    MetaDataWizardAction.PROP_RelationshipsImportDocumentPanel_IMPORT_BUTTON_WAS_PRESSED));
            getComponent().setCidsBean(metaData);
            metaData.addPropertyChangeListener(this);
        }
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final CidsBean metaData = getComponent().getCidsBean();
        metaData.removePropertyChangeListener(this);
        getComponent().dispose();
    }

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(
                RelationshipsEditDocumentPanel.class,
                "RelationshipsEditDocumentPanel.name"); // NOI18N
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    @Override
    public boolean isValid() {
        final CidsBean metaData = getComponent().getCidsBean();
        final String content = (String)metaData.getProperty("content");                 // NOI18N
        final String contentlocation = (String)metaData.getProperty("contentlocation"); // NOI18N

        final boolean isValid = StringUtils.isNotBlank(content) || StringUtils.isNotBlank(contentlocation);

        if (isValid) {
            showGeneralInformation();
        } else {
            showWarning(org.openide.util.NbBundle.getMessage(
                    RelationshipsEditDocumentPanel.class,
                    "RelationshipsEditDocumentPanel.isValid().missingContent"));
        }

        return isValid;
    }
    @Override
    public void setFinishPanel(final boolean finishPanel) {
        this.finishPanel = finishPanel;
    }

    @Override
    public boolean isFinishPanel() {
        return finishPanel;
    }
}
