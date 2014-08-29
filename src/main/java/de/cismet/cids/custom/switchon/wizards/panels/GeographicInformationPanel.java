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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.concurrent.Future;

import de.cismet.cids.custom.switchon.utils.CidsBeanUtils;
import de.cismet.cids.custom.switchon.utils.TagUtils;
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
    private static final Future<CidsBean> defaultSrid = TagUtils.fetchFutureTagByName("EPSG:4326"); // NOI18N
    private static final Future<CidsBean> defaultLocation = TagUtils.fetchFutureTagByName("World"); // NOI18N

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new GeographicInformationPanel object.
     */
    public GeographicInformationPanel() {
        super(GeographicInformationVisualPanel.class);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
        setDefaults(resource);
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
        return java.util.ResourceBundle.getBundle("de/cismet/cids/custom/switchon/wizards/panels/Bundle")
                    .getString("GeographicInformationPanel.name");
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    @Override
    public boolean isValid() {
        final CidsBean resource = getComponent().getCidsBean();
        final CidsBean spatialCoverage = (CidsBean)resource.getProperty("spatialcoverage"); // NOI18N

        return spatialCoverage != null;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  resource  DOCUMENT ME!
     */
    private void setDefaults(final CidsBean resource) {
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(
            defaultSrid,
            resource,
            "srid");     // NOI18N
        CidsBeanUtils.setPropertyFromFutureIfStillEmpty(
            defaultLocation,
            resource,
            "location"); // NOI18N
    }
}
