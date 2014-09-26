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
public class GeographicInformationPanel extends GenericAbstractWizardPanel<GeographicInformationVisualPanel>
        implements NameProvider,
            PropertyChangeListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(GeographicInformationPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new GeographicInformationPanel object.
     */
    public GeographicInformationPanel() {
        super(GeographicInformationVisualPanel.class);
        setGeneralInformation(org.openide.util.NbBundle.getMessage(
                GeographicInformationVisualPanel.class,
                "GeographicInformationVisualPanel.infoBoxPanel.generalInformation")); // NOI18N
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new GeographicInformationVisualPanel(wizard);
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
        getComponent().setCidsBean(resource);
        resource.addPropertyChangeListener(this);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final CidsBean resource = getComponent().getCidsBean();
        resource.removePropertyChangeListener(this);
        getComponent().dispose();
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(GeographicInformationPanel.class, "GeographicInformationPanel.name");
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    @Override
    public boolean isValid() {
        final CidsBean resource = getComponent().getCidsBean();
        final CidsBean spatialCoverage = (CidsBean)resource.getProperty("spatialcoverage"); // NOI18N

        final boolean isValid = spatialCoverage != null;
        if (isValid) {
            showGeneralInformation();
        } else {
            showWarning(org.openide.util.NbBundle.getMessage(
                    GeographicInformationPanel.class,
                    "GeographicInformationPanel.isValid().missingSpatialCoverage"));
        }

        return isValid;
    }
}
