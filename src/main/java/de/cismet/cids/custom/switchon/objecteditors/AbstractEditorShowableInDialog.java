/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objecteditors;

import Sirius.server.middleware.types.MetaObject;

import java.awt.Component;

import java.util.HashSet;

import de.cismet.cids.custom.switchon.gui.InfoProviderJPanel;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.tools.metaobjectrenderer.CidsBeanRenderer;

/**
 * An abstract class of for a editor which can be shown inside ShowEditorInDialog. This class can be used to avoid
 * boilerplate-code.
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 * @see      ShowEditorInDialog
 */
public abstract class AbstractEditorShowableInDialog extends InfoProviderJPanel implements CidsBeanRenderer,
    EditorShowableInDialog {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(MetadataEditor.class);

    //~ Instance fields --------------------------------------------------------

    protected HashSet<CidsBean> newlyAddedCidsBeans = new HashSet<CidsBean>();
    protected HashSet<CidsBean> modifiedCidsBeans = new HashSet<CidsBean>();

    protected CidsBean cidsBean;
    private boolean avoidPersist = false;

    //~ Methods ----------------------------------------------------------------

    @Override
    public CidsBean getCidsBean() {
        return cidsBean;
    }

    @Override
    public String getTitle() {
        if (cidsBean != null) {
            return cidsBean.toString();
        } else {
            return "new Object";
        }
    }

    @Override
    public void setTitle(final String title) {
    }

    @Override
    public HashSet<CidsBean> getNewlyAddedCidsBeans() {
        return newlyAddedCidsBeans;
    }

    @Override
    public HashSet<CidsBean> getModifiedCidsBeans() {
        return modifiedCidsBeans;
    }

    @Override
    public void saveChanges() throws Exception {
        CidsBean newCidsBean = cidsBean;
        if (!avoidPersist) {
            newCidsBean = cidsBean.persist();
        }
        modifiedCidsBeans.add(newCidsBean);
        if (cidsBean.getMetaObject().getStatus() == MetaObject.NEW) {
            newlyAddedCidsBeans.add(newCidsBean);
        }
    }

    @Override
    public Component getComponent() {
        return this;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public boolean isAvoidPersist() {
        return avoidPersist;
    }

    /**
     * The persist of the CidsBean can be avoided in the saveChanges() method.
     *
     * @param  avoidPersist  DOCUMENT ME!
     */
    public void setAvoidPersist(final boolean avoidPersist) {
        this.avoidPersist = avoidPersist;
    }
}
