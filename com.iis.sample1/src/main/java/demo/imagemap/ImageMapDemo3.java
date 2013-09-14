/* ------------------
 * ImageMapDemo3.java
 * ------------------
 * (C) Copyright 2004-2006, by Object Refinery Limited.
 * 
 */

package demo.imagemap;

import java.awt.Color;
import java.awt.Font;
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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.imagemap.ImageMapUtilities;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.VerticalAlignment;
import org.jfree.util.UnitType;

/**
 * Creates an HTML image map for an area chart.  This demo was put together as 
 * a test for bug report 815817.
 */
public class ImageMapDemo3 {

    /**
     * Default constructor.
     */
    public ImageMapDemo3() {
        super();
    }
    
    /**
     * Saves the chart image and HTML.
     */
    public void saveImageAndHTML() {
        
        // create a dataset
        double[][] data = new double[][] {
                {56.0, -12.0, 34.0, 76.0, 56.0, 100.0, 67.0, 45.0},
                {37.0, 45.0, 67.0, 25.0, 34.0, 34.0, 100.0, 53.0},
                {43.0, 54.0, 34.0, 34.0, 87.0, 64.0, 73.0, 12.0}
        };
        CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
            "Series ", "Type ", data
        );

        JFreeChart chart = createChart(dataset);

        // save it to an image
        try {
            ChartRenderingInfo info = new ChartRenderingInfo(
                new StandardEntityCollection()
            );
            File file1 = new File("areachart100.png");
            ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);

            // write an HTML page incorporating the image with an image map
            File file2 = new File("areachart100.html");
            OutputStream out = new BufferedOutputStream(
                new FileOutputStream(file2)
            );
            PrintWriter writer = new PrintWriter(out);
            writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"");
            writer.println("\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
            writer.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">");
            writer.println(
                "<head><title>JFreeChart Image Map Demo</title></head>"
            );
            writer.println("<body><p>");
            ImageMapUtilities.writeImageMap(writer, "chart", info);
            writer.println(
                "<img src=\"areachart100.png\" "
                + "width=\"600\" height=\"400\" usemap=\"#chart\" alt=\"areachart100.png\"/>"
            );
            writer.println("</p></body>");
            writer.println("</html>");
            writer.close();

        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    private JFreeChart createChart(CategoryDataset dataset) {
        
        JFreeChart chart = ChartFactory.createAreaChart(
            "Area Chart",             // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips
            true                      // urls
        );
        
        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);
        TextTitle subtitle = new TextTitle(
            "An area chart demonstration.  We use this subtitle  as an example"
            + " of what happens when you get a really long title or subtitle."
        );
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitle.setPosition(RectangleEdge.TOP);
        subtitle.setPadding(
            new RectangleInsets(UnitType.RELATIVE, 0.05, 0.05, 0.05, 0.05)
        );
        subtitle.setVerticalAlignment(VerticalAlignment.BOTTOM);
        chart.addSubtitle(subtitle);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setForegroundAlpha(0.5f);
        
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);
        
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLabelAngle(0 * Math.PI / 2.0);
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
        
    }

    /**
     * Starting point for the demo.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        ImageMapDemo3 demo = new ImageMapDemo3();
        demo.saveImageAndHTML();
    }

}
