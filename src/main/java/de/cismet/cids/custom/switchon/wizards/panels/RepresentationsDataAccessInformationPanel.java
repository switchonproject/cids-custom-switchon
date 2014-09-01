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
import org.openide.util.Exceptions;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.concurrent.Future;

import de.cismet.cids.custom.switchon.utils.CidsBeanUtils;
import de.cismet.cids.custom.switchon.utils.TagUtils;
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
public class RepresentationsDataAccessInformationPanel
        extends GenericAbstractWizardPanel<RepresentationsDataAccessInformationVisualPanel> implements NameProvider,
        PropertyChangeListener,
        WizardDescriptor.FinishablePanel {

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
        // check if a representation was selected previously
        CidsBean representation = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_SELECTED_REPRESENTATION_BEAN);
        if (representation == null) {
            try {
                // no representation selected, thus create a new representation and add it to the resource
                representation = CidsBean.createNewCidsBeanFromTableName("SWITCHON", "representation");
                DefaultPropertySetter.setDefaultsToRepresentationCidsBean(representation);
                final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
                resource.getBeanCollectionProperty("representation").add(representation);
            } catch (Exception ex) {
                LOG.error(ex, ex);
                return;
            }
        }

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
        return "Resource Representation:  Data Access Properties";
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    @Override
    public boolean isValid() {
        final CidsBean representation = getComponent().getCidsBean();
        final String contentLocation = (String)representation.getProperty("contentlocation");
        final Object contentType = representation.getProperty("contenttype");
        final Object function = representation.getProperty("function");
        final Object protocol = representation.getProperty("protocol");
        final Object applicationprofile = representation.getProperty("applicationprofile");

        return StringUtils.isNotBlank(contentLocation) && (contentLocation != null) && (contentType != null)
                    && (function != null) && (protocol != null) && (applicationprofile != null);
    }

    @Override
    public boolean isFinishPanel() {
        return finishPanel;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  finishPanel  DOCUMENT ME!
     */
    public void setFinishPanel(final boolean finishPanel) {
        this.finishPanel = finishPanel;
    }
}
