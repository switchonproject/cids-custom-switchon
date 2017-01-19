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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.Date;

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
public class TemporalInformationPanel extends GenericAbstractWizardPanel<TemporalInformationVisualPanel>
        implements NameProvider,
            PropertyChangeListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(TemporalInformationPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new TemporalInformationPanel object.
     */
    public TemporalInformationPanel() {
        super(TemporalInformationVisualPanel.class);
        setGeneralInformation(org.openide.util.NbBundle.getMessage(
                TemporalInformationVisualPanel.class,
                "TemporalInformationVisualPanel.infoBoxPanel.generalInformation")); // NOI18N
    }

    //~ Methods ----------------------------------------------------------------

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
        return NbBundle.getMessage(TemporalInformationPanel.class, "TemporalInformationPanel.name");
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    @Override
    public boolean isValid() {
        final CidsBean resource = getComponent().getCidsBean();
        final Date fromDate = (Date)resource.getProperty("fromdate"); // NOI18N
        final Date toDate = (Date)resource.getProperty("todate");     // NOI18N

        boolean valid = false;
        if (fromDate == null) {
            showWarning(org.openide.util.NbBundle.getMessage(
                    TemporalInformationPanel.class,
                    "TemporalInformationPanel.isValid().missingFromDate"));
        } else if ((toDate != null) && fromDate.after(toDate)) {
            showWarning(org.openide.util.NbBundle.getMessage(
                    TemporalInformationPanel.class,
                    "TemporalInformationPanel.isValid().FromDateAfterToDate"));
        } else {
            valid = true;
            showGeneralInformation();
        }

        return valid;
    }
}
