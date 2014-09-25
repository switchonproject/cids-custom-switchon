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

import de.cismet.cids.custom.switchon.wizards.AdvancedFinishablePanel;
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
public class RepresentationsDataAccessInformationPanel
        extends GenericAbstractWizardPanel<RepresentationsDataAccessInformationVisualPanel> implements NameProvider,
        PropertyChangeListener,
        AdvancedFinishablePanel {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(RepresentationsDataAccessInformationPanel.class);

    //~ Instance fields --------------------------------------------------------

    private boolean finishPanel = false;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new RepresentationsDataAccessInformationPanel object.
     */
    public RepresentationsDataAccessInformationPanel() {
        super(RepresentationsDataAccessInformationVisualPanel.class);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean representation = (CidsBean)wizard.getProperty(
                MetaDataWizardAction.PROP_SELECTED_REPRESENTATION_BEAN);

        final boolean panelWasOpen = (boolean)wizard.getProperty(
                MetaDataWizardAction.PROP_RepresentationsDataImportPanel_WAS_OPENED);
        getComponent().changeAppearanceAsImportDocumentPanelWasOpen(panelWasOpen);
        if (panelWasOpen) {
            setGeneralInformation(org.openide.util.NbBundle.getMessage(
                    RepresentationsDataAccessInformationVisualPanel.class,
                    "RepresentationsDataAccessInformationVisualPanel.changeAppearanceAsImportDocumentPanelWasOpen().panelWasOpen.info"));    // NOI18N
        } else {
            setGeneralInformation(org.openide.util.NbBundle.getMessage(
                    RepresentationsDataAccessInformationVisualPanel.class,
                    "RepresentationsDataAccessInformationVisualPanel.changeAppearanceAsImportDocumentPanelWasOpen().panelWasNotOpen.info")); // NOI18N
        }
        showGeneralInformation();

        getComponent().setCidsBean(representation);
        representation.addPropertyChangeListener(this);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final CidsBean representation = getComponent().getCidsBean();
        representation.removePropertyChangeListener(this);

        getComponent().dispose();
    }

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(
                RepresentationsDataAccessInformationPanel.class,
                "RepresentationsDataAccessInformationPanel.name"); // NOI18N
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    @Override
    public boolean isValid() {
        final CidsBean representation = getComponent().getCidsBean();
        final String contentLocation = (String)representation.getProperty("contentlocation"); // NOI18N
        final Object contentType = representation.getProperty("contenttype");                 // NOI18N
        final Object function = representation.getProperty("function");                       // NOI18N
        final Object protocol = representation.getProperty("protocol");                       // NOI18N

        if (StringUtils.isBlank(contentLocation)) {
            showWarning(org.openide.util.NbBundle.getMessage(
                    RepresentationsDataAccessInformationPanel.class,
                    "RepresentationsDataAccessInformationPanel.isValid().missingContentLocation"));
        } else if (contentType == null) {
            showWarning(org.openide.util.NbBundle.getMessage(
                    RepresentationsDataAccessInformationPanel.class,
                    "RepresentationsDataAccessInformationPanel.isValid().missingType"));
        } else if (function == null) {
            showWarning(org.openide.util.NbBundle.getMessage(
                    RepresentationsDataAccessInformationPanel.class,
                    "RepresentationsDataAccessInformationPanel.isValid().missingFunction"));
        } else if (protocol == null) {
            showWarning(org.openide.util.NbBundle.getMessage(
                    RepresentationsDataAccessInformationPanel.class,
                    "RepresentationsDataAccessInformationPanel.isValid().missingProtocol"));
        } else {
            showGeneralInformation();
        }

        return StringUtils.isNotBlank(contentLocation) && (contentType != null)
                    && (function != null) && (protocol != null);
    }

    @Override
    public boolean isFinishPanel() {
        return finishPanel;
    }

    @Override
    public void setFinishPanel(final boolean finishPanel) {
        this.finishPanel = finishPanel;
    }
}
