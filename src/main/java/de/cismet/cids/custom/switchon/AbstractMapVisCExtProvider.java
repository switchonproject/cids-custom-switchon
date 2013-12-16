/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import de.cismet.cismap.navigatorplugin.MapVisualisationProvider;

import de.cismet.ext.CExtProvider;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public abstract class AbstractMapVisCExtProvider implements CExtProvider {

    //~ Instance fields --------------------------------------------------------

    private final String ifaceClass;
    private final String concreteClass;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new HydroCExtProvider object.
     */
    public AbstractMapVisCExtProvider() {
        ifaceClass = "de.cismet.cismap.navigatorplugin.MapVisualisationProvider";           // NOI18N
        concreteClass = "de.cismet.cismap.navigatorplugin.DefaultMapVisualisationProvider"; // NOI18N
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public Class getType() {
        return MapVisualisationProvider.class;
    }

    @Override
    public boolean canProvide(final Class c) {
        if (c == null) {
            return false;
        }

        final String cName = c.getCanonicalName();

        return (cName == null) ? false : (ifaceClass.equals(cName) || concreteClass.equals(cName));
    }
}
