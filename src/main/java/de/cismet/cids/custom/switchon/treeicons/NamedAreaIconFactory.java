/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.treeicons;

import Sirius.navigator.types.treenode.ClassTreeNode;
import Sirius.navigator.types.treenode.ObjectTreeNode;
import Sirius.navigator.types.treenode.PureTreeNode;
import Sirius.navigator.ui.tree.CidsTreeObjectIconFactory;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;

import org.apache.log4j.Logger;

import org.geotools.geometry.jts.LiteShape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import de.cismet.cids.dynamics.CidsBean;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public final class NamedAreaIconFactory implements CidsTreeObjectIconFactory {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient Logger LOG = Logger.getLogger(NamedAreaIconFactory.class);

    //~ Methods ----------------------------------------------------------------

    @Override
    public Icon getClosedPureNodeIcon(final PureTreeNode ptn) {
        // default icon
        return null;
    }

    @Override
    public Icon getOpenPureNodeIcon(final PureTreeNode ptn) {
        // default icon
        return null;
    }

    @Override
    public Icon getLeafPureNodeIcon(final PureTreeNode ptn) {
        // default icon
        return null;
    }

    @Override
    public Icon getOpenObjectNodeIcon(final ObjectTreeNode otn) {
        return getLeafObjectNodeIcon(otn);
    }

    @Override
    public Icon getClosedObjectNodeIcon(final ObjectTreeNode otn) {
        return getLeafObjectNodeIcon(otn);
    }

    @Override
    public Icon getLeafObjectNodeIcon(final ObjectTreeNode otn) {
        try {
            final CidsBean bean = otn.getMetaObject().getBean();
            final Geometry geom = (Geometry)bean.getProperty("area.geo_field");

            final Envelope env = geom.getEnvelopeInternal();
            final double scale = Math.min(16 / env.getWidth(), 16 / env.getHeight());
            final double xoff = 0 - (scale * env.getMinX());
            final double yoff = env.getMaxY() * scale;
            final AffineTransform at = new AffineTransform(scale, 0, 0, -scale, xoff, yoff);

            final LiteShape shape = new LiteShape(geom, at, false);

            final int dw = (int)Math.ceil(geom.getEnvelopeInternal().getWidth() * scale);
            final int dh = (int)Math.ceil(geom.getEnvelopeInternal().getHeight() * scale);

            final BufferedImage biShape = new BufferedImage(dw, dh, BufferedImage.TYPE_INT_ARGB);
            final Graphics2D g2dShape = (Graphics2D)biShape.getGraphics();

            final Paint paint = new Color(153, 153, 255);
            g2dShape.setPaint(paint);
            g2dShape.fill(shape);

            g2dShape.setPaint(Color.BLACK);
            g2dShape.draw(shape);

            final BufferedImage bi = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

            final Graphics2D g2d = (Graphics2D)bi.getGraphics();
            final int x = (bi.getWidth() - biShape.getWidth()) / 2;
            final int y = (bi.getHeight() - biShape.getHeight()) / 2;
            g2d.drawImage(biShape, x, y, null);

            return new ImageIcon(bi);
        } catch (final Exception e) {
            LOG.error("cannot create catchment area icon", e);

            return null;
        }
    }

    @Override
    public Icon getClassNodeIcon(final ClassTreeNode dmtn) {
        // default icon
        return null;
    }
}
