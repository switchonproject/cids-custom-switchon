/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public final class WizardInitialisationException extends Exception {

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new instance of <code>WizardInitialisationException</code> without detail message.
     */
    public WizardInitialisationException() {
    }

    /**
     * Constructs an instance of <code>WizardInitialisationException</code> with the specified detail message.
     *
     * @param  msg  the detail message.
     */
    public WizardInitialisationException(final String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>WizardInitialisationException</code> with the specified detail message and the
     * specified cause.
     *
     * @param  msg    the detail message.
     * @param  cause  the exception cause
     */
    public WizardInitialisationException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}
