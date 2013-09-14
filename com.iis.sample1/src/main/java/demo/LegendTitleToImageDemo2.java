/* ----------------------------
 * LegendTitleToImageDemo2.java
 * ----------------------------
 * (C) Copyright 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.Size2D;

/**
 * Here we save a legend to a PNG file...the legend has a lot of items, so we
 * apply a width constraint so it doesn't get too wide.
 */
public class LegendTitleToImageDemo2 {

    /**
     * Entry point.
     *
     * @param args  command line arguments (ignored).
     *
     * @throws IOException if there is an input/output problem.
     */
    public static void main(String[] args) throws IOException {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("England", 1.0);
        dataset.setValue("France", 2.0);
        dataset.setValue("Germany", 3.0);
        dataset.setValue("Italy", 4.0);
        dataset.setValue("Scotland", 5.0);
        dataset.setValue("Belgium", 6.0);
        dataset.setValue("Poland", 7.0);
        dataset.setValue("Spain", 8.0);
        dataset.setValue("Portugal", 9.0);
        dataset.setValue("Switzerland", 10.0);
        dataset.setValue("Austria", 11.0);
        dataset.setValue("Luxembourg", 12.0);
        JFreeChart chart = ChartFactory.createPieChart("Test", dataset, true,
                false, false);

        // get the legend and save it to an image on its own
        LegendTitle legend = chart.getLegend();
        legend.setMargin(0, 0, 1, 1);

        // we need to layout the legend to know how much space it requires
        // note that it is also possible to call arrange() with some
        // constraints on the layout
        BufferedImage img = new BufferedImage(1, 1,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        Size2D size = legend.arrange(g2, new RectangleConstraint(250.0,
                new Range(0.0, 10000.0)));
        g2.dispose();

        // now create an image of the required size for the legend
        int w = (int) Math.rint(size.width);
        int h = (int) Math.rint(size.height);
        BufferedImage img2 = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g22 = img2.createGraphics();
        legend.draw(g22, new Rectangle2D.Double(0, 0, w, h));
        g22.dispose();

        // ...and save it to a PNG image
        OutputStream out = new BufferedOutputStream(new FileOutputStream(
                new File("LegendTitleToImageDemo2.png")));
        ChartUtilities.writeBufferedImageAsPNG(out, img2);
        out.close();
    }
}
