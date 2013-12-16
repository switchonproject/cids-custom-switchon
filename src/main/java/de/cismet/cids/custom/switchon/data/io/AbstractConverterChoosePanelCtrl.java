/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.data.io;

import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;

/**
 * DOCUMENT ME!
 *
 * @author   bfriedrich
 * @version  $Revision$, $Date$
 */
public abstract class AbstractConverterChoosePanelCtrl extends AbstractWizardPanelCtrl {

    //~ Static fields/initializers ---------------------------------------------

    public static final String PROP_CONVERTER = "__prop_converter__"; // NOI18N

    //~ Methods ----------------------------------------------------------------

    @Override
    public abstract AbstractConverterChoosePanel getComponent();

    @Override
    protected void read(final WizardDescriptor wizard) {
        getComponent().init();
        wizard.putProperty(
            WizardDescriptor.PROP_INFO_MESSAGE,
            NbBundle.getMessage(
                AbstractConverterChoosePanelCtrl.class,
                "AbstractConverterChoosePanelCtrl.read(WizardDescriptor).wizard.putProperty(String,String)"));
    }

    @Override
    protected void store(final WizardDescriptor wizard) {
        wizard.putProperty(PROP_CONVERTER, getComponent().getConverter());
        wizard.putProperty(WizardDescriptor.PROP_INFO_MESSAGE, null);
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
