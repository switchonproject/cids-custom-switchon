/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.data.io;

import Sirius.navigator.ui.ComponentRegistry;

import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.util.Cancellable;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import java.text.MessageFormat;

import javax.swing.Action;
import javax.swing.JComponent;

import de.cismet.cids.custom.switchon.IDFCurve;

import de.cismet.cids.utils.abstracts.AbstractCidsBeanAction;

/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public final class IDFExportWizardAction extends AbstractCidsBeanAction {

    //~ Static fields/initializers ---------------------------------------------

    public static final String PROP_IDF_CURVE = "__prop_idf_curve__"; // NOI18N

    //~ Instance fields --------------------------------------------------------

    private transient WizardDescriptor.Panel[] panels;

    private transient IDFCurve idfCurve;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new RainfallDownscalingWizardAction object.
     */
    public IDFExportWizardAction() {
        super("", ImageUtilities.loadImageIcon("de/cismet/cids/custom/switchon/data/io/idf_export.png", false)); // NOI18N

        putValue(
            Action.SHORT_DESCRIPTION,
            NbBundle.getMessage(IDFExportWizardAction.class, "IDFExportWizardAction.shortDescription")); // NOI18N
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * EDT only !
     *
     * @return  DOCUMENT ME!
     */
    private WizardDescriptor.Panel[] getPanels() {
        assert EventQueue.isDispatchThread() : "can only be called from EDT"; // NOI18N

        if (panels == null) {
            panels = new WizardDescriptor.Panel[] {
                    new WizardPanelFileExport(),
                    new IDFImportWizardPanelChooseConverter(),
                    new IDFExportWizardPanelConvert()
                };

            final String[] steps = new String[panels.length];
            for (int i = 0; i < panels.length; i++) {
                final Component c = panels[i].getComponent();
                // Default step name to component name of panel. Mainly useful
                // for getting the name of the target chooser to appear in the
                // list of steps.
                steps[i] = c.getName();
                if (c instanceof JComponent) {
                    // assume Swing components
                    final JComponent jc = (JComponent)c;
                    // Sets step number of a component
                    jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, Integer.valueOf(i));
                    // Sets steps names for a panel
                    jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
                    // Turn on subtitle creation on each step
                    jc.putClientProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, Boolean.TRUE);
                    // Show steps on the left side with the image on the
                    // background
                    jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, Boolean.TRUE);
                    // Turn on numbering of all steps
                    jc.putClientProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, Boolean.TRUE);
                }
            }
        }

        return panels;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public IDFCurve getIdfCurve() {
        return idfCurve;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  curve  DOCUMENT ME!
     */
    public void setIdfCurve(final IDFCurve curve) {
        this.idfCurve = curve;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  e  DOCUMENT ME!
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        final WizardDescriptor wizard = new WizardDescriptor(getPanels());
        wizard.setTitleFormat(new MessageFormat("{0}"));                             // NOI18N
        wizard.setTitle(NbBundle.getMessage(
                IDFExportWizardAction.class,
                "IDFExportWizardAction.actionPerformed(ActionEvent).wizard.title")); // NOI18N

        assert idfCurve != null : "idfCurve is null"; // NOI18N

        wizard.putProperty(PROP_IDF_CURVE, idfCurve);

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
        }
    }
}
