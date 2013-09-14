/* ------------------
 * ImageMapDemo8.java
 * ------------------
 * (C) Copyright 2007, by Object Refinery Limited.
 *
 */

package demo.imagemap;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.imagemap.ImageMapUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;

/**
 * A demo showing how to create an HTML image map for a bubble chart.
 */
public class ImageMapDemo8 {

    /**
     * Default constructor.
     */
    public ImageMapDemo8() {
        super();
    }

    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    private static JFreeChart createChart(XYZDataset dataset) {
        JFreeChart chart = ChartFactory.createBubbleChart(
                "Bubble Chart Demo 1", "X", "Y", dataset, 
                PlotOrientation.HORIZONTAL, true, true, true);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setForegroundAlpha(0.65f);
        
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.blue);
        
        // increase the margins to account for the fact that the auto-range 
        // doesn't take into account the bubble size...
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setLowerMargin(0.15);
        domainAxis.setUpperMargin(0.15);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setLowerMargin(0.15);
        rangeAxis.setUpperMargin(0.15);
        return chart;
    }
    
    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    public static XYZDataset createDataset() {
        DefaultXYZDataset dataset = new DefaultXYZDataset(); 
        double[] x = {2.1, 2.3, 2.3, 2.2, 2.2, 1.8, 1.8, 1.9, 2.3, 3.8};
        double[] y = {14.1, 11.1, 10.0, 8.8, 8.7, 8.4, 5.4, 4.1, 4.1, 25};
        double[] z = {2.4, 2.7, 2.7, 2.2, 2.2, 2.2, 2.1, 2.2, 1.6, 4};
        double[][] series = new double[][] { x, y, z };
        dataset.addSeries("Series 1", series);
        return dataset;
    }

    /**
     * Starting point for the demo.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        JFreeChart chart = createChart(createDataset());

        // save it to an image
        try {
            ChartRenderingInfo info = new ChartRenderingInfo(
                    new StandardEntityCollection());
            File file1 = new File("bubble100.png");
            ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);

            // write an HTML page incorporating the image with an image map
            File file2 = new File("bubble100.html");
            OutputStream out = new BufferedOutputStream(new FileOutputStream(
                    file2));
            PrintWriter writer = new PrintWriter(out);
            writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"");
            writer.println("\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
            writer.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">");
            writer.println("<head><title>JFreeChart Image Map Demo 8</title></head>");
            writer.println("<body><p>");
            ImageMapUtilities.writeImageMap(writer, "chart", info);
            writer.println("<img src=\"bubble100.png\" "
                           + "width=\"600\" height=\"400\" usemap=\"#chart\" alt=\"bubble100.png\"/>");
            writer.println("</p></body>");
            writer.println("</html>");
            writer.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
