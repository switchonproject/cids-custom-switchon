/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objectactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.cismet.cids.utils.interfaces.CidsBeanAction;
import de.cismet.cids.utils.interfaces.CidsBeanActionsProvider;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public final class IdfCurveActionsProvider implements CidsBeanActionsProvider {

    //~ Methods ----------------------------------------------------------------

    @Override
    public Collection<CidsBeanAction> getActions() {
        final List<CidsBeanAction> ret = new ArrayList<CidsBeanAction>(0);

        return ret;
    }
}
