/* -----------------
 * CircleDrawer.java
 * -----------------
 * (C) Copyright 2003, 2004, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import org.jfree.ui.Drawable;

/**
 * An implementation of the {@link Drawable} interface, to illustrate the use
 * of the {@link org.jfree.chart.annotations.XYDrawableAnnotation} class.  Used
 * by MarkerDemo1.java.
 */
public class CircleDrawer implements Drawable {

    /** The outline paint. */
    private Paint outlinePaint;

    /** The outline stroke. */
    private Stroke outlineStroke;

    /** The fill paint. */
    private Paint fillPaint;

    /**
     * Creates a new instance.
     *
     * @param outlinePaint  the outline paint.
     * @param outlineStroke  the outline stroke.
     * @param fillPaint  the fill paint.
     */
    public CircleDrawer(Paint outlinePaint,
                        Stroke outlineStroke,
                        Paint fillPaint) {
        this.outlinePaint = outlinePaint;
        this.outlineStroke = outlineStroke;
        this.fillPaint = fillPaint;
    }

    /**
     * Draws the circle.
     *
     * @param g2  the graphics device.
     * @param area  the area in which to draw.
     */
    public void draw(Graphics2D g2, Rectangle2D area) {
        Ellipse2D ellipse = new Ellipse2D.Double(area.getX(), area.getY(),
                                                 area.getWidth(), area.getHeight());
        if (this.fillPaint != null) {
            g2.setPaint(this.fillPaint);
            g2.fill(ellipse);
        }
        if (this.outlinePaint != null && this.outlineStroke != null) {
            g2.setPaint(this.outlinePaint);
            g2.setStroke(this.outlineStroke);
            g2.draw(ellipse);
        }

        g2.setPaint(Color.black);
        g2.setStroke(new BasicStroke(1.0f));
        Line2D line1 = new Line2D.Double(area.getCenterX(), area.getMinY(),
                                         area.getCenterX(), area.getMaxY());
        Line2D line2 = new Line2D.Double(area.getMinX(), area.getCenterY(),
                                         area.getMaxX(), area.getCenterY());
        g2.draw(line1);
        g2.draw(line2);
    }
}
