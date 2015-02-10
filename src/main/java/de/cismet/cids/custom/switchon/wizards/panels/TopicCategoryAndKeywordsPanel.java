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
public class TopicCategoryAndKeywordsPanel extends GenericAbstractWizardPanel<TopicCategoryAndKeywordsVisualPanel>
        implements NameProvider,
            PropertyChangeListener {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(TopicCategoryAndKeywordsPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new TopicCategoryAndKeywordsPanel object.
     */
    public TopicCategoryAndKeywordsPanel() {
        super(TopicCategoryAndKeywordsVisualPanel.class);
        setGeneralInformation(org.openide.util.NbBundle.getMessage(
                TopicCategoryAndKeywordsVisualPanel.class,
                "TopicCategoryAndKeywordsVisualPanel.infoBoxPanel.generalInformation")); // NOI18N
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected void read(final WizardDescriptor wizard) {
        final CidsBean resource = (CidsBean)wizard.getProperty(MetaDataWizardAction.PROP_RESOURCE_BEAN);
        resource.addPropertyChangeListener(this);
        getComponent().setCidsBean(resource);
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        final CidsBean resource = getComponent().getCidsBean();
        resource.removePropertyChangeListener(this);
        getComponent().dispose();
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(TopicCategoryAndKeywordsPanel.class, "TopicCategoryAndKeywordsPanel.getName()");
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        changeSupport.fireChange();
    }

    @Override
    public boolean isValid() {
        final CidsBean resource = getComponent().getCidsBean();
        final CidsBean topiccategory = (CidsBean)resource.getProperty("topiccategory"); // NOI18N

        if (topiccategory == null) {
            showWarning(org.openide.util.NbBundle.getMessage(
                    TopicCategoryAndKeywordsPanel.class,
                    "TopicCategoryAndKeywordsPanel.isValid().missingCategory"));
        } else {
            showGeneralInformation();
        }

        return topiccategory != null;
    }
}
