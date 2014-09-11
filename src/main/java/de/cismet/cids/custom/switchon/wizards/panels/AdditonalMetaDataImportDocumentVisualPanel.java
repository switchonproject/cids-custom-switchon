/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class AdditonalMetaDataImportDocumentVisualPanel extends BasicImportDocumentVisualPanel {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            AdditonalMetaDataImportDocumentVisualPanel.class);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form AdditonalMetaDataImportDocumentVisualPanel.
     */
    public AdditonalMetaDataImportDocumentVisualPanel() {
        super();
        setGeneralInformation(org.openide.util.NbBundle.getMessage(
                AdditonalMetaDataImportDocumentVisualPanel.class,
                "AdditonalMetaDataImportDocumentVisualPanel.generalInformation"));
    }
}
