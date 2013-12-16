/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.converter;

/**
 * Interface to be implemented by {@link Converter} implementations to hint the user about the supported format so that
 * they can decide which <code>Converter</code> to use for import or export of their data.
 *
 * @author   martin.scholl@cismet.de
 * @version  1.0, 2012/08/30
 */
public interface FormatHint {

    //~ Methods ----------------------------------------------------------------

    /**
     * The name of the format, usually a machine readable format identifier.
     *
     * @return  the name of the format
     */
    String getFormatName();

    /**
     * Shall return a human readable display name for the format.
     *
     * @return  a human readable display name for the format
     */
    String getFormatDisplayName();

    /**
     * Shall return the format name as <code>HTML String</code> or <code>null</code> if there is no <code>HTML</code>
     * formatted name <code>String</code>.
     *
     * @return  the <code>HTML</code> display name or <code>null</code> if there is none available
     */
    String getFormatHtmlName();

    /**
     * Shall return a human readable description of the supported format.
     *
     * @return  a human readable description of the supported format
     */
    String getFormatDescription();

    /**
     * Shall return a human readable <code>HTML String</code> or <code>null</code> if there is no <code>HTML</code>
     * formatted description <code>String</code>.
     *
     * @return  a human readable <code>HTML</code> description or <code>null</code> if there is none available
     */
    String getFormatHtmlDescription();

    /**
     * Shall return a depiction of the format so that the user may see on a concrete example what the supported format
     * looks like.
     *
     * @return  a format example or <code>null</code> if no example is available
     */
    Object getFormatExample();
}
