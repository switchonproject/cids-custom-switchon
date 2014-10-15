/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards;

/**
 * WizardDescriptor.Panel can implement this to return a name to the wizard iterator. The name will be shown in the
 * wizard as title.
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public interface NameProvider {

    //~ Methods ----------------------------------------------------------------

    /**
     * The returned string will be shown in the wizard as title of the panel.
     *
     * @return  DOCUMENT ME!
     */
    String getName();
}
