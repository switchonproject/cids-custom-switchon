/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import org.jdesktop.beansbinding.Converter;

import java.sql.Timestamp;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public final class SqlTimestampToStringConverter extends Converter<Timestamp, String> {

    //~ Methods ----------------------------------------------------------------

    @Override
    public String convertForward(final Timestamp value) {
        return value.toString();
    }

    @Override
    public Timestamp convertReverse(final String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
