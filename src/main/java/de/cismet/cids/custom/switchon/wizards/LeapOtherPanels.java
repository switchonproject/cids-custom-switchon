/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards;

/**
 * A wizard panel can implement this, to modify the behavior of the next and previous button.
 * If the methods are implemented those buttons can leap over several other panels.
 * The classname of the destination panel should be returned because the {@link MetaDataWizardIterator} fetches the next panel of the classname.
 * This works, as every panel has its own class.
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public interface LeapOtherPanels {

    //~ Methods ----------------------------------------------------------------

    /**
     * Returns the classname of the panel which should be opened when the next button is pressed.
     * If the classname is not valid, the {@link MetaDataWizardIterator} will show the next panel.
     *
     * @return  DOCUMENT ME!
     */
    String nextPanelClassSimpleName();

    /**
     * Returns the classname of the panel which should be opened when the back button is pressed.
     * If the classname is not valid, the {@link MetaDataWizardIterator} will show the previous panel.
     *
     * @return  DOCUMENT ME!
     */
    String previousPanelClassSimpleName();
}
