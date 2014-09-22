/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.objecteditors;

import Sirius.navigator.ui.RequestsFullSizeComponent;

import java.util.ArrayList;

import de.cismet.cids.client.tools.DevelopmentTools;

import de.cismet.cids.custom.switchon.utils.Taggroups;

import de.cismet.cids.dynamics.CidsBean;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class RelationshipEditor extends AbstractEditorShowableInDialog implements RequestsFullSizeComponent {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(MetadataEditor.class);

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.cismet.cids.custom.switchon.objecteditors.AdditionalTagsPanel additionalTagsPanel;
    private de.cismet.cids.custom.switchon.objecteditors.BasicPropertiesPanel basicPropertiesPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private de.cismet.cids.custom.switchon.objecteditors.MetaDataPanel metaDataPanel;
    private de.cismet.cids.custom.switchon.objecteditors.SourceResourceRelationshipPanel
        sourceResourceRelationshipPanel;
    private de.cismet.cids.custom.switchon.objecteditors.TargetResourceRelationshipPanel
        targetResourceRelationshipPanel;
        // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form RelationshipEditor.
     */
    public RelationshipEditor() {
        initComponents();
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        basicPropertiesPanel = new BasicPropertiesPanel(Taggroups.RELATIONSHIP_TYPE);
        final ArrayList<Taggroups> taggroups = new ArrayList<Taggroups>();
        taggroups.add(Taggroups.GEOGRAPHY);
        taggroups.add(Taggroups.HYDROLOGICAL_CONCEPT);
        taggroups.add(Taggroups.KEYWORDS_INSPIRE_THEMES_1_0);
        taggroups.add(Taggroups.KEYWORDS_OPEN);
        additionalTagsPanel = new de.cismet.cids.custom.switchon.objecteditors.AdditionalTagsPanel(taggroups);
        jPanel3 = new javax.swing.JPanel();
        targetResourceRelationshipPanel =
            new de.cismet.cids.custom.switchon.objecteditors.TargetResourceRelationshipPanel();
        jPanel1 = new javax.swing.JPanel();
        sourceResourceRelationshipPanel =
            new de.cismet.cids.custom.switchon.objecteditors.SourceResourceRelationshipPanel();
        metaDataPanel = new de.cismet.cids.custom.switchon.objecteditors.MetaDataPanel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(950, 700));
        setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        basicPropertiesPanel.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(basicPropertiesPanel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(additionalTagsPanel, gridBagConstraints);

        add(jPanel2);

        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(344, 332));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        targetResourceRelationshipPanel.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel3.add(targetResourceRelationshipPanel, gridBagConstraints);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(0, 1, 0, 10));

        sourceResourceRelationshipPanel.setOpaque(false);
        jPanel1.add(sourceResourceRelationshipPanel);

        metaDataPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
                org.openide.util.NbBundle.getMessage(
                    RelationshipEditor.class,
                    "RelationshipEditor.metaDataPanel.border.title"))); // NOI18N
        metaDataPanel.setOpaque(false);
        jPanel1.add(metaDataPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        jPanel3.add(jPanel1, gridBagConstraints);

        add(jPanel3);
    } // </editor-fold>//GEN-END:initComponents

    @Override
    public void setCidsBean(final CidsBean cidsBean) {
        if (cidsBean != null) {
            this.cidsBean = cidsBean;

            basicPropertiesPanel.setCidsBean(cidsBean);
            additionalTagsPanel.setCidsBean(cidsBean);
            metaDataPanel.setCidsBean(cidsBean);
            targetResourceRelationshipPanel.setCidsBean(cidsBean);
            sourceResourceRelationshipPanel.setCidsBean(cidsBean);
        }
    }

    @Override
    public void dispose() {
        additionalTagsPanel.dispose();
        metaDataPanel.dispose();
        basicPropertiesPanel.dispose();
        targetResourceRelationshipPanel.dispose();
        sourceResourceRelationshipPanel.dispose();
    }

    /**
     * DOCUMENT ME!
     *
     * @param   args  DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    public static void main(final String[] args) throws Exception {
        DevelopmentTools.createEditorInFrameFromRMIConnectionOnLocalhost(
            "SWITCHON",
            "Administratoren",
            "admin",
            "cismet",
            "relationship",
            1,
            1280,
            1024);
    }
}
