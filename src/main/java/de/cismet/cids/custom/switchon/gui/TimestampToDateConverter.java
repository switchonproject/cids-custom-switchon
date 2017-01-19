/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui;

import org.jdesktop.beansbinding.Converter;

import java.sql.Timestamp;

import java.util.Date;

/**
 * DOCUMENT ME!
 *
 * @author   thorsten
 * @version  $Revision$, $Date$
 */
public class TimestampToDateConverter extends Converter<Timestamp, Date> {

    //~ Methods ----------------------------------------------------------------

    @Override
    public Date convertForward(final Timestamp value) {
        if (value != null) {
            return new Date(value.getTime());
        } else {
            return null;
        }
    }

    @Override
    public Timestamp convertReverse(final Date value) {
        if (value != null) {
            return new Timestamp(value.getTime());
        } else {
            return null;
        }
    }
}
