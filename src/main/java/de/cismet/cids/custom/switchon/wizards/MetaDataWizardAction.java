/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards;

import org.apache.log4j.Logger;

import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.navigator.utils.CidsClientToolbarItem;

import de.cismet.cids.utils.interfaces.CidsBeanAction;

import de.cismet.cismap.commons.interaction.CismapBroker;

import de.cismet.tools.gui.StaticSwingTools;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
@org.openide.util.lookup.ServiceProvider(service = CidsClientToolbarItem.class)
public class MetaDataWizardAction extends AbstractAction implements CidsClientToolbarItem, CidsBeanAction {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(MetaDataWizardAction.class);

    public static final String PROP_CONFIGURATION = "__prop_configuration__"; // NOI18N

    public static final String PROP_FILEPATH = "__prop_filepath__";                 // NOI18N
    public static final String PROP_BEZEICHNUNG = "__prop_bezeichnung__";           // NOI18N
    public static final String PROP_BESCHREIBUNG = "__prop_beschreibung__";         // NOI18N
    public static final String PROP_PROJEKT = "__prop_projekt__";                   // NOI18N
    public static final String PROP_DOKUMENT = "__prop_dokument__";                 // NOI18N
    public static final String PROP_AS_BUILT = "__prop_as_built__";                 // NOI18N
    public static final String PROP_BETRIEB = "__prop_betrieb__";                   // NOI18N
    public static final String PROP_ANLAGE = "__prop_anlage__";                     // NOI18N
    public static final String PROP_TEILANLAGE = "__prop_teilanlage__";             // NOI18N
    public static final String PROP_GRUPPE = "__prop_gruppe__";                     // NOI18N
    public static final String PROP_GEOMETRY = "__prop_geometry__";                 // NOI18N
    public static final String PROP_VORGAENGER = "__prop_vorgaenger__";             // NOI18N
    public static final String PROP_ERSTELLUNGSDATUM = "__prop_erstellungsdatum__"; // NOI18N

    //~ Instance fields --------------------------------------------------------

    private CidsBean cidsBean;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new ImportDocumentWizardAction object.
     */
    public MetaDataWizardAction() {
        putValue(SHORT_DESCRIPTION, "open Meta-Data wizard");
//        final ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource(
//                    "/de/cismet/cids/custom/tados/res/wand.png"));
//        putValue(SMALL_ICON, icon);
        putValue(NAME, "open Meta-Data wizard");
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public void actionPerformed(final ActionEvent e) {
        final WizardDescriptor.Iterator iterator = new MetaDataWizardIterator();
        final WizardDescriptor wizard = new WizardDescriptor(iterator);
        ((MetaDataWizardIterator)iterator).initialize(wizard);
        if (cidsBean != null) {
            wizard.putProperty(PROP_PROJEKT, cidsBean);
        }

        final Frame parent = StaticSwingTools.getParentFrame(CismapBroker.getInstance().getMappingComponent());
        final Dialog wizardDialog = DialogDisplayer.getDefault().createDialog(wizard);
        wizardDialog.pack();
        wizardDialog.setLocationRelativeTo(parent);
        wizardDialog.setModal(false);
        wizardDialog.setAlwaysOnTop(true);
        wizardDialog.setVisible(true);
        wizardDialog.toFront();
    }

    @Override
    public String getSorterString() {
        return "Dokument Import Wizard";
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public CidsBean getCidsBean() {
        return cidsBean;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        this.cidsBean = cidsBean;
    }
}
