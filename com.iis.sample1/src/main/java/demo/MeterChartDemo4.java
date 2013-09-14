/* --------------------
 * MeterChartDemo4.java
 * --------------------
 * (C) Copyright 2004, 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;

/**
 * In this demo, a meter chart is saved to a scaled image file.
 */
public class MeterChartDemo4 {

    /**
     * Default constructor.
     */
    public MeterChartDemo4() {
        super();
    }

    /**
     * Starting point for the demo.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        ValueDataset dataset = new DefaultValueDataset(75.0);
        MeterPlot plot = new MeterPlot(dataset);
        JFreeChart chart = new JFreeChart("Scaled Image Test", plot);
        ChartUtilities.applyCurrentTheme(chart);

        // save it to an image
        try {
            File file1 = new File("meterchart100.png");
            OutputStream out = new BufferedOutputStream(new FileOutputStream(file1));
            BufferedImage image = chart.createBufferedImage(200, 200, 400, 400, null);
            ChartUtilities.writeBufferedImageAsPNG(out, image);
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }

    }

}
