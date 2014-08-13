/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objecteditors;

import java.awt.Component;

import java.util.HashSet;

import de.cismet.cids.dynamics.CidsBean;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public interface EditorShowableInDialog {

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    HashSet<CidsBean> getNewlyAddedCidsBeans();

    /**
     * Saves the modified cidsBeans. The newly added/modified cidsbeans will be persisted and the new cidsbeans will be
     * saved in a set.
     *
     * @throws  Exception  DOCUMENT ME!
     */
    void saveChanges() throws Exception;

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    Component getComponent();
}
