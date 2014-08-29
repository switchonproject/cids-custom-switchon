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
    protected HashSet<CidsBean> persistedCidsBeans = new HashSet<CidsBean>();

    protected CidsBean cidsBean;

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
    public HashSet<CidsBean> getPersistedCidsBeans() {
        return persistedCidsBeans;
    }

    @Override
    public void saveChanges() throws Exception {
        final CidsBean newCidsBean = cidsBean.persist();
        persistedCidsBeans.add(newCidsBean);
        if (cidsBean.getMetaObject().getStatus() == MetaObject.NEW) {
            newlyAddedCidsBeans.add(newCidsBean);
        }
    }

    @Override
    public Component getComponent() {
        return this;
    }
}
