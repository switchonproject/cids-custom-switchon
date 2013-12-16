/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import javax.swing.JPanel;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.tools.metaobjectrenderer.CidsBeanRenderer;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public abstract class AbstractCidsBeanRenderer extends JPanel implements CidsBeanRenderer {

    //~ Instance fields --------------------------------------------------------

    protected transient CidsBean cidsBean;

    private transient String title;

    //~ Methods ----------------------------------------------------------------

    @Override
    public CidsBean getCidsBean() {
        return cidsBean;
    }

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        this.cidsBean = cidsBean;

        init();
    }

    /**
     * The init method shall be used to initialise the renderer. It is called after the {@link CidsBean} has been set.
     *
     * @see  #setCidsBean(de.cismet.cids.dynamics.CidsBean)
     */
    protected abstract void init();

    @Override
    public void dispose() {
        // do nothing
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(final String title) {
        this.title = title;
    }
}
