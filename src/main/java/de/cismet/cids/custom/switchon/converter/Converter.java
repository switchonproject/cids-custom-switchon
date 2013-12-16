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
public interface Converter<FROM, TO> {

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   from    DOCUMENT ME!
     * @param   params  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  ConversionException  DOCUMENT ME!
     */
    TO convertForward(FROM from, final String... params) throws ConversionException;

    /**
     * DOCUMENT ME!
     *
     * @param   to      DOCUMENT ME!
     * @param   params  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  ConversionException  DOCUMENT ME!
     */
    FROM convertBackward(TO to, final String... params) throws ConversionException;
}
