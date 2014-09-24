/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards;

import java.awt.Color;

import de.cismet.cids.custom.switchon.gui.InfoBoxPanel;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class WizardInfoBoxPanel extends InfoBoxPanel {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(WizardInfoBoxPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new WizardInfoBoxPanel object.
     */
    public WizardInfoBoxPanel() {
        super();
        setIcon(null);
        setTitledBorder("Completion Guide");
        setForeground(new Color(51, 51, 51));
    }
}
