/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.converter;

import Sirius.navigator.connection.SessionManager;
import Sirius.navigator.ui.ComponentRegistry;

import org.apache.log4j.Logger;

import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.util.Cancellable;
import org.openide.util.NbBundle;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;

import java.text.MessageFormat;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import de.cismet.cids.custom.switchon.IDFCurve;
import de.cismet.cids.custom.switchon.IDFTablePanel;
import de.cismet.cids.custom.switchon.Utils;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.utils.abstracts.AbstractCidsBeanAction;

/**
 * DOCUMENT ME!
 *
 * @author   jlauter
 * @version  $Revision$, $Date$
 */
public class EulerComputationWizardAction extends AbstractCidsBeanAction {

    //~ Static fields/initializers ---------------------------------------------

    public static final String PROP_SELECTED_RAINDATA = "__prop_selected_raindata__";
    public static final String PROP_EULER2_NAME = "__prop_euler2_name__";
    public static final String PROP_EULER2_DESC = "__prop_euler2_desc__";
    public static final String PROP_EULER2_RESULT = "__prop_euler2_result__";
    public static final String PROP_EULER2_INTERVAL = "__prop_euler2_interval__";
    public static final String PROP_EULER_ARITHMETIC = "__prop_euler_arithmetic__";

    /** LOGGER. */
    private static final transient Logger LOG = Logger.getLogger(EulerComputationWizardAction.class);

    //~ Instance fields --------------------------------------------------------

    private transient WizardDescriptor.Panel[] panels;

    private final transient IDFTablePanel model;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new EulerComputationWizardAction object.
     *
     * @param  model  rainData DOCUMENT ME!
     */
    public EulerComputationWizardAction(final IDFTablePanel model) {
        this.model = model;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private WizardDescriptor.Panel[] getPanels() {
        panels = new WizardDescriptor.Panel[] {
                new EulerComputationWizardPanelArithmeticChooser(),
                new EulerComputationWizardPanelMetadata(),
                new EulerComputationWizardPanelCompute()
            };
        final String[] steps = new String[panels.length];
        for (int i = 0; i < panels.length; i++) {
            final Component c = panels[i].getComponent();
            steps[i] = c.getName();
            if (c instanceof JComponent) {
                final JComponent jc = (JComponent)c;
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, Integer.valueOf(i));
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
                jc.putClientProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, Boolean.TRUE);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, Boolean.TRUE);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, Boolean.TRUE);
            }
        }
        return panels;
    }

    @Override
    public void actionPerformed(final ActionEvent ae) {
        final IDFCurve idfCurve = model.getIDFcurve();
        assert idfCurve != null : "idfCurve not set"; // NOI18N

        final boolean forecast;

        if (idfCurve.getForecast() == null) {
            forecast = false;
        } else {
            forecast = idfCurve.getForecast();
        }

        final int interval = idfCurve.getData().firstKey();
        assert interval > 0 : "interval cannot be negative or 0";

        final WizardDescriptor wizard = new WizardDescriptor(getPanels());

        wizard.setTitleFormat(new MessageFormat("{0}"));
        wizard.setTitle(NbBundle.getMessage(
                EulerComputationWizardAction.class,
                "EulerComputationWizardAction.actionPerformed(ActionEvent).wizard.title"));

        final int colIndexEnd = model.getSelectedColIndex();
        final int rowIndexStart = model.getSelectedRowStart();
        final int rowIndexEnd = model.getSelectedRowEnd();

        final SortedMap<Integer, Double> raindata = new TreeMap<Integer, Double>();

        for (int i = rowIndexStart; i <= rowIndexEnd; i++) {
            final Integer duration = (Integer)idfCurve.getDurationIntensityRows()[i][0];
            final Double value = (Double)idfCurve.getDurationIntensityRows()[i][colIndexEnd];
            if ((duration != null) && (value != null)) {
                raindata.put(duration, value);
            }
        }

        wizard.putProperty(PROP_SELECTED_RAINDATA, raindata);
        wizard.putProperty(PROP_EULER2_INTERVAL, interval);

        final Dialog dialog = DialogDisplayer.getDefault().createDialog(wizard);
        dialog.pack();
        dialog.setLocationRelativeTo(ComponentRegistry.getRegistry().getMainWindow());
        dialog.setVisible(true);
        dialog.toFront();

        if (wizard.getValue() != WizardDescriptor.FINISH_OPTION) {
            for (final WizardDescriptor.Panel panel : this.panels) {
                if (panel instanceof Cancellable) {
                    ((Cancellable)panel).cancel();
                }
            }
        } else {
            final String name = String.valueOf(wizard.getProperty(
                        PROP_EULER2_NAME));
            final String desc = String.valueOf(wizard.getProperty(
                        PROP_EULER2_DESC));
            final SortedMap<Integer, Double> result = (SortedMap<Integer, Double>)wizard.getProperty(
                    PROP_EULER2_RESULT);

            assert name != null : "name must not be null";
            assert desc != null : "desc must not be null";
            assert result != null : "result must not be null";

            final String domain = SessionManager.getSession().getUser().getDomain();
            final String table = Utils.TABLENAME_RAINEVENT;

            try {
                CidsBean rainevent = CidsBean.createNewCidsBeanFromTableName(domain, table);

                rainevent.setProperty("name", name);
                rainevent.setProperty("description", desc);
                rainevent.setProperty("interval", interval);
                rainevent.setProperty("forecast", forecast);

                final StringBuilder data = new StringBuilder();
                final Iterator iterator = result.keySet().iterator();
                while (iterator.hasNext()) {
                    final int key = (Integer)iterator.next();
                    final double value = result.get(key);
                    data.append(value);
                    if (key != result.lastKey()) {
                        data.append(":"); // NOI18N
                    }
                }

                rainevent.setProperty("data", data.toString()); // NOI18N

                rainevent = rainevent.persist();
                final ComponentRegistry reg = ComponentRegistry.getRegistry();
                reg.getDescriptionPane().gotoMetaObject(rainevent.getMetaObject(), null);

                ComponentRegistry.getRegistry().getCatalogueTree().requestRefreshNode("rainfall.rainevent");
            } catch (final Exception ex) {
                final String title = "Cannot perform Euler-Computation";
                final String message = org.openide.util.NbBundle.getMessage(
                        EulerComputationWizardPanelCompute.class,
                        "EulerComputationWizardAction.actionPerformed(ActionEvent).errorCreatingRainevent");
                LOG.error(message, ex);
                JOptionPane.showMessageDialog(ComponentRegistry.getRegistry().getMainWindow(),
                    title,
                    message,
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
