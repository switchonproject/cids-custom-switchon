/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.converter;

import org.apache.log4j.Logger;

import org.openide.WizardDescriptor;
import org.openide.util.Cancellable;
import org.openide.util.NbBundle;

import java.awt.Component;
import java.awt.EventQueue;

import java.util.SortedMap;
import java.util.concurrent.Future;

import de.cismet.cids.custom.switchon.AbstractWizardPanel;
import de.cismet.cids.custom.switchon.StatusPanel;
import de.cismet.cids.custom.switchon.concurrent.SwitchonConcurrency;

/**
 * DOCUMENT ME!
 *
 * @author   jlauter
 * @version  $Revision$, $Date$
 */
public class EulerComputationWizardPanelCompute extends AbstractWizardPanel implements Cancellable {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient Logger LOG = Logger.getLogger(EulerComputationWizardPanelCompute.class);

    //~ Instance fields --------------------------------------------------------

    private SortedMap<Integer, Double> result;
    private final transient Object lock;

    private transient Future exportTask;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new EulerComputationWizardPanelCompute object.
     */
    public EulerComputationWizardPanelCompute() {
        this.lock = new Object();
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new StatusPanel(NbBundle.getMessage(
                    EulerComputationWizardPanelCompute.class,
                    "EulerComputationWizardPanelCompute.createComponent().statusPanel.name"));
    }

    @Override
    protected void read(final WizardDescriptor wizard) {
        synchronized (lock) {
            final SortedMap<Integer, Double> raindata = (SortedMap<Integer, Double>)wizard.getProperty(
                    EulerComputationWizardAction.PROP_SELECTED_RAINDATA);

            final int interval = (Integer)wizard.getProperty(EulerComputationWizardAction.PROP_EULER2_INTERVAL);
            final String eulerArithmetic = (String)wizard.getProperty(
                    EulerComputationWizardAction.PROP_EULER_ARITHMETIC);
            assert raindata != null : "raindata must not be null";
            assert interval != 0 : "interval must not be 0";
            assert ((eulerArithmetic != null) || eulerArithmetic.isEmpty()) : "eulerArithmetic must not be null or not empty";

            exportTask = SwitchonConcurrency.getSwitchonGeneralPurposePool().submit(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                setStatusEDT(
                                    true,
                                    NbBundle.getMessage(
                                        EulerComputationWizardPanelCompute.class,
                                        "EulerComputationWizardPanelCompute.read(WizardDescriptor).exportTask.status.beginCompute"));

                                result = EulerComputationUtil.eulerComputation(raindata, interval, eulerArithmetic);

                                wizard.putProperty(EulerComputationWizardAction.PROP_EULER2_RESULT, result);

                                setStatusEDT(
                                    false,
                                    org.openide.util.NbBundle.getMessage(
                                        EulerComputationWizardPanelCompute.class,
                                        "EulerComputationWizardPanelCompute.read(WizardDescriptor).exportTask.status.computeSuccess"));

                                synchronized (lock) {
                                    EulerComputationWizardPanelCompute.this.exportTask = null;
                                }
                            } catch (Exception ex) {
                                LOG.error("can not create rainevent", ex);
                                setStatusEDT(
                                    false,
                                    org.openide.util.NbBundle.getMessage(
                                        EulerComputationWizardPanelCompute.class,
                                        "EulerComputationWizardPanelCompute.read(WizardDescriptor).exportTask.status.errorCreatingRainevent"));
                            } finally {
                                changeSupport.fireChange();
                            }
                        }
                    });
        }
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
    }

    @Override
    public boolean cancel() {
        synchronized (lock) {
            if (exportTask != null) {
                if (!exportTask.cancel(true)) {
                    if (exportTask.isDone()) {
                        // do nothing, its too late
                    } else {
                        LOG.warn("export task could not be cancelled"); // NOI18N

                        return false;
                    }
                }
            }

            return true;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param  busy     DOCUMENT ME!
     * @param  message  DOCUMENT ME!
     */
    protected void setStatusEDT(final boolean busy, final String message) {
        EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    ((StatusPanel)getComponent()).setBusy(busy);
                    ((StatusPanel)getComponent()).setStatusMessage(message);
                }
            });
    }
}
