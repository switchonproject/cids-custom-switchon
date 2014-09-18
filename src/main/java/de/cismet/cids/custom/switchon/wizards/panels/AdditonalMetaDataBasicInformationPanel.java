/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import org.apache.log4j.Logger;

import org.openide.util.NbBundle;

import java.awt.Component;

import de.cismet.cids.custom.switchon.utils.Taggroups;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class AdditonalMetaDataBasicInformationPanel extends BasicInformationPanel {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(AdditonalMetaDataBasicInformationPanel.class);

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new AdditonalMetaDataBasicInformationVisualPanel(Taggroups.META_DATA_TYPE);
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(
                AdditonalMetaDataBasicInformationPanel.class,
                "AdditonalMetaDataBasicInformationPanel.name");
    }
}
