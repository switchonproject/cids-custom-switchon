/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.data.io;

import Sirius.navigator.connection.SessionManager;

import Sirius.server.middleware.types.MetaClass;

import org.apache.log4j.Logger;

import org.openide.WizardDescriptor;

import java.awt.Component;

import de.cismet.cids.custom.switchon.Utils;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.navigator.utils.ClassCacheMultiple;

/**
 * DOCUMENT ME!
 *
 * @author   bfriedrich
 * @version  $Revision$, $Date$
 */
public class WizardPanelMetadata extends AbstractWizardPanelCtrl {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(WizardPanelMetadata.class);

    public static final String PROP_BEAN = "__prop_bean__"; // NOI18N

    //~ Instance fields --------------------------------------------------------

    private final transient VisualPanelMetadata comp;
    private final transient String tableName;

    private transient CidsBean cidsBean;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new TimeSeriesImportFileChoosePanelCtrl object.
     *
     * @param  tableName  DOCUMENT ME!
     */
    public WizardPanelMetadata(final String tableName) {
        this.comp = new VisualPanelMetadata(this);
        this.tableName = tableName;
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public Component getComponent() {
        return this.comp;
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Entering read(WizardDescriptor)"); // NOI18N
        }

        final String domain = SessionManager.getSession().getUser().getDomain();
        final MetaClass mc = ClassCacheMultiple.getMetaClass(domain, tableName);

        if (mc == null) {
            LOG.error(
                "Was not able to retrieve MetaClass for domain '" // NOI18N
                        + domain
                        + "' and table '"                         // NOI18N
                        + tableName
                        + "'");                                   // NOI18N
        } else {
            this.cidsBean = mc.getEmptyInstance().getBean();
            this.comp.init();
        }
        if (LOG.isTraceEnabled()) {
            LOG.trace("Leaving read(WizardDescriptor)");          // NOI18N
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public CidsBean getCidsBean() {
        return this.cidsBean;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public String getTableName() {
        return tableName;
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Entering store(WizardDescriptor)"); // NOI18N
        }

        // FIXME: quick and dirty
        if (Utils.TABLENAME_IDFCURVE.equals(tableName)) {
            try {
                cidsBean.setProperty("year", comp.getYear());  // NOI18N
            } catch (final Exception ex) {
                LOG.warn("cannot set year for idf curve", ex); // NOI18N
            }
        }

        wizard.putProperty(PROP_BEAN, this.cidsBean);

        comp.finalise();

        wizard.putProperty(WizardDescriptor.PROP_INFO_MESSAGE, null);
        if (LOG.isTraceEnabled()) {
            LOG.trace("Leaving store(WizardDescriptor)"); // NOI18N
        }
    }

    @Override
    public boolean isValid() {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Entering isValid()"); // NOI18N
        }

        return true;
    }
}
