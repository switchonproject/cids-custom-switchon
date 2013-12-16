/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.data.io;

import org.openide.util.Lookup;

import java.util.ArrayList;
import java.util.List;

import de.cismet.cids.custom.switchon.converter.IDFConverter;

/**
 * DOCUMENT ME!
 *
 * @author   Martin Scholl
 * @version  $Revision$, $Date$
 */
public class IDFImportVisualPanelChooseConverter extends AbstractConverterChoosePanel<IDFConverter> {

    //~ Instance fields --------------------------------------------------------

    private final transient List<IDFConverter> converters;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new IDFImportVisualPanelChooseConverter object.
     *
     * @param  ctrl  DOCUMENT ME!
     */
    public IDFImportVisualPanelChooseConverter(final AbstractConverterChoosePanelCtrl ctrl) {
        super(ctrl);

        converters = new ArrayList<IDFConverter>();
        converters.addAll(Lookup.getDefault().lookupAll(IDFConverter.class));
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public List<IDFConverter> getConverters() {
        return converters;
    }
}
