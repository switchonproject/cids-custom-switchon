/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objecteditors;

import java.awt.Frame;

import java.util.HashSet;

/**
 * A non-modal version of ShowEditorInDialog, this dialog fires an event if the changes were saved successfully. This is
 * needed as the dialog is not modal and showDialog() returns immediately.
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class NonModalShowEditorInDialog extends ShowEditorInDialog {

    //~ Instance fields --------------------------------------------------------

    private HashSet<ChangesSavedListener> changesSavedListeners = new HashSet<ChangesSavedListener>();

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new NonModalShowEditorInDialog object.
     *
     * @param  parent  DOCUMENT ME!
     * @param  editor  DOCUMENT ME!
     */
    public NonModalShowEditorInDialog(final Frame parent, final EditorShowableInDialog editor) {
        super(parent, editor);
        this.setModal(false);
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * This dialog can not be set to modal.
     *
     * @param  modal  DOCUMENT ME!
     */
    @Override
    public void setModal(final boolean modal) {
        super.setModal(false);
    }

    @Override
    protected boolean saveChanges() {
        final boolean changesWereSaved = super.saveChanges();
        if (changesWereSaved) {
            for (final ChangesSavedListener listener : changesSavedListeners) {
                listener.changesWereSaved();
            }
        }
        return changesWereSaved;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  listener  DOCUMENT ME!
     */
    public void addListener(final ChangesSavedListener listener) {
        changesSavedListeners.add(listener);
    }

    //~ Inner Interfaces -------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    public interface ChangesSavedListener {

        //~ Methods ------------------------------------------------------------

        /**
         * DOCUMENT ME!
         */
        void changesWereSaved();
    }
}
