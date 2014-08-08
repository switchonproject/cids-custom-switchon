/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.tostringconverter.switchon;

import org.apache.commons.lang.StringUtils;

import de.cismet.cids.tools.CustomToStringConverter;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class ContactToStringConverter extends CustomToStringConverter {

    //~ Methods ----------------------------------------------------------------

    @Override
    public String createString() {
        final String organisation = (String)cidsBean.getProperty("organisation");
        if (StringUtils.isNotBlank(organisation)) {
            return organisation;
        } else {
            return "n/a";
        }
    }
}
