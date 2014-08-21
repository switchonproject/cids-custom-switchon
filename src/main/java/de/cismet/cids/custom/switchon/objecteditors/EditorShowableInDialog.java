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
 * These methods must be implemented if a CidsBean-Editor should be shown in the dialog ShowEditorInDialog.
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 * @see      ShowEditorInDialog
 */
public interface EditorShowableInDialog {

    //~ Methods ----------------------------------------------------------------

    /**
     * Get a set of the CidsBeans which were newly created in the editor-dialog.
     *
     * @return  DOCUMENT ME!
     */
    HashSet<CidsBean> getNewlyAddedCidsBeans();

    /**
     * Get a set of the CidsBeans which were persisted in the editor-dialog.
     *
     * @return  DOCUMENT ME!
     */
    HashSet<CidsBean> getPersistedCidsBeans();

    /**
     * Saves the modified cidsBeans. The newly added/modified cidsbeans will be persisted and the new cidsbeans will be
     * saved in a set.
     *
     * @throws  Exception  DOCUMENT ME!
     */
    void saveChanges() throws Exception;

    /**
     * Get the component which should be shown in the dialog. Normally in the editor itself.
     *
     * @return  DOCUMENT ME!
     */
    Component getComponent();
}
