
package de.cismet.cids.custom.switchon.gui.utils;

import Sirius.navigator.connection.SessionManager;
import Sirius.server.middleware.types.MetaClass;
import Sirius.server.middleware.types.MetaObject;
import de.cismet.cids.navigator.utils.ClassCacheMultiple;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Gilles Baatz
 */
public class QueryComboBox extends JComboBox{
        private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(QueryJList.class);

        public QueryComboBox(final String query) {
        super();

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
            model.addElement(null);
            for (final MetaObject mo : lwmos) {
                model.addElement(mo);
            }
        } catch (Exception ex) {
            LOG.warn("Problem while loading the LightWeightMetaObjects.", ex);
        }
        this.setModel(model);
        this.setSelectedIndex(0);
    }
    
}
