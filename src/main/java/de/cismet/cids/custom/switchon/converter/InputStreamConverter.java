/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.converter;

import java.io.InputStream;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public interface InputStreamConverter<TO extends Object> extends Converter<InputStream, TO> {

    //~ Methods ----------------------------------------------------------------

    /**
     * Creates an object <code>TO</code> from the given <code>InputStream</code>. The <code>InputStream</code> shall not
     * be closed by implementations. The users of this operation have to take care of the <code>InputStream</code> that
     * is provided to this operation, thus they must take care of their resources themselves.
     *
     * @param   from    the <code>InputStream</code> that provides the information to create <code>TO</code>
     * @param   params  DOCUMENT ME!
     *
     * @return  the desired object, created from the <code>InputStream</code>
     *
     * @throws  ConversionException  if any error occurs during conversion
     */
    @Override
    TO convertForward(InputStream from, final String... params) throws ConversionException;

    /**
     * Creates an <code>InputStream</code> with the given object <code>TO</code> as source. The <code>InputStream</code>
     * shall not be closed by implementations. The users of this operation have to take care of the returned <code>
     * InputStream</code>, thus they must take care of the resources themselves.
     *
     * @param   to      the object <code>TO</code> that shall be turned into an <code>InputStream</code>
     * @param   params  DOCUMENT ME!
     *
     * @return  an <code>InputStream</code> providing the data of the given object <code>TO</code>
     *
     * @throws  ConversionException  if any error occurs during conversion
     */
    @Override
    InputStream convertBackward(TO to, final String... params) throws ConversionException;
}
