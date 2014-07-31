/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui.utils;

import Sirius.navigator.connection.SessionManager;

import Sirius.server.middleware.types.MetaClass;
import Sirius.server.middleware.types.MetaObject;

import org.apache.commons.lang.StringUtils;

import java.awt.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;

import de.cismet.cids.navigator.utils.ClassCacheMultiple;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class QueryComboBox extends JComboBox {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(QueryJList.class);

    //~ Instance fields --------------------------------------------------------

    private final boolean nullable;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new QueryComboBox object.
     *
     * @param  query  DOCUMENT ME!
     */
    public QueryComboBox(final String query) {
        this(query, true);
    }

    /**
     * Creates a new QueryComboBox object.
     *
     * @param  query     DOCUMENT ME!
     * @param  nullable  DOCUMENT ME!
     */
    public QueryComboBox(final String query, final boolean nullable) {
        super();
        this.nullable = nullable;
        // if value null then show a message
        final DefaultListCellRenderer cellRenderer = new DefaultListCellRenderer() {

                @Override
                public Component getListCellRendererComponent(final JList<?> list,
                        final Object value,
                        final int index,
                        final boolean isSelected,
                        final boolean cellHasFocus) {
                    final Component c = super.getListCellRendererComponent(
                            list,
                            value,
                            index,
                            isSelected,
                            cellHasFocus);
                    if (value == null) {
                        ((JLabel)c).setText("None");
                    }
                    return c;
                }
            };
        this.setRenderer(cellRenderer);

        if (StringUtils.isBlank(query)) {
            final DefaultComboBoxModel<MetaObject> model = new DefaultComboBoxModel<MetaObject>();

            this.setModel(model);
        } else {
            executeQueryAndSetModel(query);
        }
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  query  DOCUMENT ME!
     */
    public final void executeQueryAndSetModel(final String query) {
        final DefaultComboBoxModel<MetaObject> model = new DefaultComboBoxModel<MetaObject>();
        try {
            final MetaClass mc = ClassCacheMultiple.getMetaClass("SWITCHON", "Tag");
            final MetaObject[] lwmos = SessionManager.getProxy()
                        .getLightweightMetaObjectsByQuery(mc.getID(),
                            SessionManager.getSession().getUser(),
                            query,
                            new String[] { "NAME" },
                            "%1$2s");
            if (nullable) {
                model.addElement(null);
            }
            for (final MetaObject mo : lwmos) {
                model.addElement(mo);
            }
        } catch (Exception ex) {
            LOG.warn("Problem while loading the LightWeightMetaObjects.", ex);
        }
        this.setModel(model);
        if (model.getSize() > 0) {
            this.setSelectedIndex(0);
        }
    }
}
