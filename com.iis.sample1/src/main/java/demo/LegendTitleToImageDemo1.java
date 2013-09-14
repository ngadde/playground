/* ----------------------------
 * LegendTitleToImageDemo1.java
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
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.Size2D;

/**
 * A demo.
 */
public class LegendTitleToImageDemo1 {

    /**
     * Entry point.
     *
     * @param args  command line arguments (ignored).
     *
     * @throws IOException if there is an input/output problem.
     */
    public static void main(String[] args) throws IOException {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("A", 1.0);
        dataset.setValue("B", 2.0);
        dataset.setValue("C", 3.0);
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
        Size2D size = legend.arrange(g2);
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
                new File("LegendTitleToImageDemo1.png")));
        ChartUtilities.writeBufferedImageAsPNG(out, img2);
        out.close();
    }
}
