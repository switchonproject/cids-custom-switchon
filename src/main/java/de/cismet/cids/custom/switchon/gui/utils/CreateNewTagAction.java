/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui.utils;

import Sirius.server.middleware.types.MetaObject;

import java.awt.event.ActionEvent;

import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.SwingWorker;

import de.cismet.cids.custom.switchon.objecteditors.ShowEditorInDialog;
import de.cismet.cids.custom.switchon.objecteditors.SimpleTagEditor;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.cids.editors.FastBindableReferenceCombo;

import de.cismet.tools.gui.StaticSwingTools;

/**
 * An Action to create a new tag for a certain taggroup. The action has to be used in combination with a
 * FastBindableReferenceCombo. It is assumed that that the FastBindableReferenceCombo contains tags from one taggroup.
 * After the creation of a new tag, the model of the combobox is reloaded and the new tag is selected in the combobox.
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class CreateNewTagAction extends AbstractAction {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CreateNewTagAction.class);

    //~ Instance fields --------------------------------------------------------

    FastBindableReferenceCombo combo;

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public FastBindableReferenceCombo getCombo() {
        return combo;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  combo  DOCUMENT ME!
     */
    public void setCombo(final FastBindableReferenceCombo combo) {
        this.combo = combo;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        try {
            final CidsBean taggroup = getTaggroupOfCombobox();
            final SimpleTagEditor simpleTagEditor = new SimpleTagEditor(taggroup);
            simpleTagEditor.setCidsBean(CidsBean.createNewCidsBeanFromTableName("SWITCHON", "tag"));
            final ShowEditorInDialog dialog = new ShowEditorInDialog(StaticSwingTools.getParentFrame(this.combo),
                    true,
                    simpleTagEditor);
            dialog.setTitle(taggroup.toString());
            dialog.showDialog();

            final Set<CidsBean> newlyAddedCidsBeans = simpleTagEditor.getNewlyAddedCidsBeans();
            if (!newlyAddedCidsBeans.isEmpty()) {
                final CidsBean beanToSelect = newlyAddedCidsBeans.iterator().next();
                new ReloadModelWorker(beanToSelect).execute();
            }
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    private CidsBean getTaggroupOfCombobox() throws Exception {
        final ComboBoxModel model = combo.getModel();
        CidsBean tag = null;
        for (int i = 0; i < model.getSize(); i++) {
            final Object element = model.getElementAt(i);
            if (element instanceof CidsBean) {
                tag = (CidsBean)element;
                break;
            } else if (element instanceof MetaObject) {
                tag = ((MetaObject)element).getBean();
                break;
            }
        }

        if (tag != null) {
            return (CidsBean)tag.getProperty("taggroup");
        } else {
            throw new Exception("Taggroup could not be determined.");
        }
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @version  $Revision$, $Date$
     */
    private class ReloadModelWorker extends SwingWorker<Void, Void> {

        //~ Instance fields ----------------------------------------------------

        CidsBean beanToSelect;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new ReloadModelWorker object.
         *
         * @param  beanToSelect  DOCUMENT ME!
         */
        public ReloadModelWorker(final CidsBean beanToSelect) {
            this.beanToSelect = beanToSelect;
            combo.setEnabled(false);
        }

        //~ Methods ------------------------------------------------------------

        @Override
        protected Void doInBackground() throws Exception {
            combo.setModel(combo.createModelForMetaClass(combo.isNullable()));
            return null;
        }

        @Override
        protected void done() {
            try {
                get();
                combo.setEnabled(true);
                if (beanToSelect != null) {
                    combo.setSelectedItem(beanToSelect);
                }
            } catch (InterruptedException ex) {
                LOG.error(ex);
            } catch (ExecutionException ex) {
                LOG.error(ex);
            }
        }
    }
}
