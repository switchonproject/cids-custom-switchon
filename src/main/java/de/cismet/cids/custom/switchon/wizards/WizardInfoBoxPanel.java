
package de.cismet.cids.custom.switchon.wizards;

import de.cismet.cids.custom.switchon.gui.InfoBoxPanel;

/**
 *
 * @author Gilles Baatz
 */
public class WizardInfoBoxPanel extends InfoBoxPanel{

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(WizardInfoBoxPanel.class);

    public WizardInfoBoxPanel() {
        super();
        setIcon(null);
        setTitledBorder("Completion Guide");
    }
    

}
