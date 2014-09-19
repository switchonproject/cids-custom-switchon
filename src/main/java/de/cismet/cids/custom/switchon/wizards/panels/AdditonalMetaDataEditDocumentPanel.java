/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import org.openide.WizardDescriptor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
public class AdditonalMetaDataEditDocumentPanel
        extends GenericAbstractWizardPanel<AdditonalMetaDataEditDocumentVisualPanel> implements NameProvider,
        PropertyChangeListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(AdditonalMetaDataEditDocumentPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new AdditonalMetaDataEditDocumentPanel object.
     */
    public AdditonalMetaDataEditDocumentPanel() {
        super(AdditonalMetaDataEditDocumentVisualPanel.class);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean metaData = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_SELECTED_METADATA_BEAN);
        // disable the content and content location components if AdditonalMetaDataImportDocumentPanel was already open
        getComponent().changeAppearanceAsImportDocumentPanelWasOpen((boolean)wizard.getProperty(
                MetaDataWizardAction.PROP_AdditonalMetaDataImportDocumentPanel_WAS_OPENED));
        getComponent().setCidsBean(metaData);
        metaData.addPropertyChangeListener(this);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
    }

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(
                AdditonalMetaDataEditDocumentPanel.class,
                "AdditonalMetaDataEditDocumentPanel.name");
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    @Override
    public boolean isValid() {
        final CidsBean metaData = getComponent().getCidsBean();
        final String content = (String)metaData.getProperty("content");
        final String contentlocation = (String)metaData.getProperty("contentlocation");

        return StringUtils.isNotBlank(content) || StringUtils.isNotBlank(contentlocation);
    }
}
