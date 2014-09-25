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
public class RepresentationsAdditionalInformationPanel
        extends GenericAbstractWizardPanel<RepresentationsAdditionalInformationVisualPanel> implements NameProvider {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(RepresentationsAdditionalInformationPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new RepresentationsAdditionalInformationPanel object.
     */
    public RepresentationsAdditionalInformationPanel() {
        super(RepresentationsAdditionalInformationVisualPanel.class);
        setGeneralInformation(org.openide.util.NbBundle.getMessage(
                RepresentationsAdditionalInformationVisualPanel.class,
                "RepresentationsAdditionalInformationVisualPanel.infoBoxPanel.generalInformation")); // NOI18N
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_SELECTED_REPRESENTATION_BEAN);
        getComponent().setCidsBean(resource);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
    }

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(
                RepresentationsAdditionalInformationPanel.class,
                "RepresentationsAdditionalInformationPanel.name");
    }
}
