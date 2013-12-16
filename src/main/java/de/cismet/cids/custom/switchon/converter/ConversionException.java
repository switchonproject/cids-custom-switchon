/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.converter;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public final class ConversionException extends Exception {

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new instance of <code>ConversionException</code> without detail message.
     */
    public ConversionException() {
    }

    /**
     * Constructs an instance of <code>ConversionException</code> with the specified detail message.
     *
     * @param  msg  the detail message.
     */
    public ConversionException(final String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>ConversionException</code> with the specified detail message and the specified
     * cause.
     *
     * @param  msg    the detail message.
     * @param  cause  the exception cause
     */
    public ConversionException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}
