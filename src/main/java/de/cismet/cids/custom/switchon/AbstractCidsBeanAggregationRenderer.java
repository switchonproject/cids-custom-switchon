/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import java.util.Collection;

import javax.swing.JPanel;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.tools.metaobjectrenderer.CidsBeanAggregationRenderer;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public abstract class AbstractCidsBeanAggregationRenderer extends JPanel implements CidsBeanAggregationRenderer {

    //~ Instance fields --------------------------------------------------------

    protected transient Collection<CidsBean> cidsBeans;

    private transient String title;

    //~ Methods ----------------------------------------------------------------

    @Override
    public Collection<CidsBean> getCidsBeans() {
        return cidsBeans;
    }

    @Override
    public void setCidsBeans(final Collection<CidsBean> beans) {
        this.cidsBeans = beans;

        init();
    }

    /**
     * DOCUMENT ME!
     */
    protected abstract void init();

    @Override
    public void dispose() {
        title = null;
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
