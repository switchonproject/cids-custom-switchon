/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.gui.utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 * MySwing: Advanced Swing Utilites Copyright (C) 2005 Santhosh Kumar T
 *
 * <p/>This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.</p>
 *
 * <p>This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.</p>
 *
 * @version  $Revision$, $Date$
 */

public class ComponentTitledBorder implements Border, MouseListener, SwingConstants {

    //~ Instance fields --------------------------------------------------------

    int offset = 5;

    Component comp;
    JComponent container;
    Rectangle rect;
    Border border;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new ComponentTitledBorder object.
     *
     * @param  comp       DOCUMENT ME!
     * @param  container  DOCUMENT ME!
     * @param  border     DOCUMENT ME!
     */
    public ComponentTitledBorder(final Component comp, final JComponent container, final Border border) {
        this.comp = comp;
        this.container = container;
        this.border = border;
        container.addMouseListener(this);
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public void paintBorder(final Component c,
            final Graphics g,
            final int x,
            final int y,
            final int width,
            final int height) {
        final Insets borderInsets = border.getBorderInsets(c);
        final Insets insets = getBorderInsets(c);
        final int temp = (insets.top - borderInsets.top) / 2;
        border.paintBorder(c, g, x, y + temp, width, height - temp);
        final Dimension size = comp.getPreferredSize();
        rect = new Rectangle(offset, 0, size.width, size.height);
        SwingUtilities.paintComponent(g, comp, (Container)c, rect);
    }

    @Override
    public Insets getBorderInsets(final Component c) {
        final Dimension size = comp.getPreferredSize();
        final Insets insets = border.getBorderInsets(c);
        insets.top = Math.max(insets.top, size.height);
        return insets;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  me  DOCUMENT ME!
     */
    private void dispatchEvent(final MouseEvent me) {
        if ((rect != null) && rect.contains(me.getX(), me.getY())) {
            final Point pt = me.getPoint();
            pt.translate(-offset, 0);
            comp.setBounds(rect);
            comp.dispatchEvent(new MouseEvent(
                    comp,
                    me.getID(),
                    me.getWhen(),
                    me.getModifiers(),
                    pt.x,
                    pt.y,
                    me.getClickCount(),
                    me.isPopupTrigger(),
                    me.getButton()));
            if (!comp.isValid()) {
                container.repaint();
            }
        }
    }

    @Override
    public void mouseClicked(final MouseEvent me) {
        dispatchEvent(me);
    }

    @Override
    public void mouseEntered(final MouseEvent me) {
        dispatchEvent(me);
    }

    @Override
    public void mouseExited(final MouseEvent me) {
        dispatchEvent(me);
    }

    @Override
    public void mousePressed(final MouseEvent me) {
        dispatchEvent(me);
    }

    @Override
    public void mouseReleased(final MouseEvent me) {
        dispatchEvent(me);
    }
}
