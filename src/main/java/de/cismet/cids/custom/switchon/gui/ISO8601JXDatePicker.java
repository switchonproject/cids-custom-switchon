/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui;

import org.apache.commons.lang.ArrayUtils;

import org.jdesktop.swingx.JXDatePicker;

import java.text.DateFormat;

import java.util.Date;
import java.util.Locale;

import de.cismet.cids.custom.switchon.utils.ISO8601DateFormat;

/**
 * A JXDatePicker which uses the ISO-8601 as main format (YYYY-MM-DD).
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 * @see      ISO8601DateFormat
 */
public class ISO8601JXDatePicker extends JXDatePicker {

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new ISO8601JXDatePicker object.
     */
    public ISO8601JXDatePicker() {
        super();
        setISO8601AsMainFormat();
    }

    /**
     * Creates a new ISO8601JXDatePicker object.
     *
     * @param  selected  DOCUMENT ME!
     */
    public ISO8601JXDatePicker(final Date selected) {
        super(selected);
        setISO8601AsMainFormat();
    }

    /**
     * Creates a new ISO8601JXDatePicker object.
     *
     * @param  locale  DOCUMENT ME!
     */
    public ISO8601JXDatePicker(final Locale locale) {
        super(locale);
        setISO8601AsMainFormat();
    }

    /**
     * Creates a new ISO8601JXDatePicker object.
     *
     * @param  selection  DOCUMENT ME!
     * @param  locale     DOCUMENT ME!
     */
    public ISO8601JXDatePicker(final Date selection, final Locale locale) {
        super(selection, locale);
        setISO8601AsMainFormat();
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     */
    private void setISO8601AsMainFormat() {
        final DateFormat[] isoArr = new DateFormat[] { new ISO8601DateFormat() };
        this.setFormats((DateFormat[])ArrayUtils.addAll(isoArr, this.getFormats()));
    }
}
