/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards;

import org.openide.WizardDescriptor;

/**
 * A Wizard Panel implements this can be set as finish panel.
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public interface AdvancedFinishablePanel extends WizardDescriptor.FinishablePanel {

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  finishPanel  DOCUMENT ME!
     */
    void setFinishPanel(boolean finishPanel);
}
