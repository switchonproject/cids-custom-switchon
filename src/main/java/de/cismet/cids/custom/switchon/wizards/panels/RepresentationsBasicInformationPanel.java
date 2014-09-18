/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards.panels;

import org.apache.log4j.Logger;

import java.awt.Component;

import de.cismet.cids.custom.switchon.utils.Taggroups;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class RepresentationsBasicInformationPanel extends BasicInformationPanel {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(RepresentationsBasicInformationPanel.class);

    //~ Methods ----------------------------------------------------------------

    @Override
    protected Component createComponent() {
        return new RepresentationsBasicInformationVisualPanel(Taggroups.REPRESENTATION_TYPE);
    }

    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(
                RepresentationsBasicInformationPanel.class,
                "RepresentationsBasicInformationPanel.name");
    }
}
