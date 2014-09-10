/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui;

import javax.swing.JPanel;

/**
 * A JPanel which is also an InfoProvider. This is only a convenience class to avoid boilerplate code.
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class InfoProviderJPanel extends JPanel implements InfoProvider {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            InfoProviderJPanel.class);

    //~ Instance fields --------------------------------------------------------

    private InfoReceiver infoReceiver;

    //~ Methods ----------------------------------------------------------------

    @Override
    public void provideInformation(final String information) {
        if (infoReceiver != null) {
            infoReceiver.setInformation(information);
        }
    }

    @Override
    public InfoReceiver getInfoReceiver() {
        return infoReceiver;
    }

    @Override
    public void setInfoReceiver(final InfoReceiver infoReceiver) {
        this.infoReceiver = infoReceiver;
    }
}
