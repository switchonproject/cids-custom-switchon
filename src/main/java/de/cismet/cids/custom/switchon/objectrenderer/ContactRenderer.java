/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objectrenderer;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.custom.switchon.objecteditors.ContactEditor;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class ContactRenderer extends ContactEditor {

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new ContactRenderer object.
     */
    public ContactRenderer() {
        super(false);
        hideRoleComponents();
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   args  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    public static void main(final String[] args) throws Exception {
        DevelopmentTools.createRendererInFrameFromRMIConnectionOnLocalhost(
            "SWITCHON",
            "Administratoren",
            "admin",
            "cismet",
            "contact",
            11,
            "Contact",
            1280,
            1024);
    }
}
