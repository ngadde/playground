/* ------------------------------------
 * CustomCategoryURLGeneratorDemo1.java
 * ------------------------------------
 * (C) Copyright 2008, by Object Refinery Limited.
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
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.imagemap.ImageMapUtilities;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.urls.CustomCategoryURLGenerator;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * This demo creates an HTML image map where the URLs for the hyperlinks on 
 * the chart are specified using a {@link CustomCategoryURLGenerator}).
 */
public class CustomCategoryURLGeneratorDemo1 {

    /**
     * Default constructor.
     */
    public CustomCategoryURLGeneratorDemo1() {
        super();
    }

    /**
     * Starting point for the demo.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(38.6, "Google", "Q1/2008");
        dataset.addValue(37.6, "Google", "Q2/2008");
        dataset.addValue(48.9, "Yahoo", "Q1/2008");
        dataset.addValue(49.9, "Yahoo", "Q2/2008");
        JFreeChart chart = ChartFactory.createBarChart(
                "CustomCategoryURLGeneratorDemo1",
                null, "Strength", dataset, PlotOrientation.VERTICAL,
                true, true, false);
        
        chart.setBackgroundPaint(java.awt.Color.white);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);

        CategoryItemRenderer renderer = plot.getRenderer();
        
        // prespecify the URLs for each data item...
        CustomCategoryURLGenerator urls = new CustomCategoryURLGenerator();
        List list = new java.util.ArrayList();
        list.add("http://www.google.com/");
        list.add("http://www.google.com/Q2/");
        urls.addURLSeries(list);

        List list2 = new java.util.ArrayList();
        list2.add("http://www.yahoo.com/");
        list2.add("http://www.yahoo.com/Q2/");
        urls.addURLSeries(list2);
        
        renderer.setBaseItemURLGenerator(urls);
        
        // save it to an image
        try {
            ChartRenderingInfo info = new ChartRenderingInfo(
                    new StandardEntityCollection());
            File file1 = new File("customCategoryURLDemo1.png");
            ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);

            // write an HTML page incorporating the image with an image map
            File file2 = new File("customCategoryURLDemo1.html");
            OutputStream out = new BufferedOutputStream(new FileOutputStream(
                    file2));
            PrintWriter writer = new PrintWriter(out);
            writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"");
            writer.println("\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
            writer.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">");
            writer.println("<head><title>JFreeChart: CustomCategoryURLGeneratorDemo1</title></head>");
            writer.println("<body><p>");
            ImageMapUtilities.writeImageMap(writer, "chart", info);
            writer.println("<img src=\"customCategoryURLDemo1.png\" "
                           + "width=\"600\" height=\"400\" usemap=\"#chart\" alt=\"customCategoryURLDemo1.png\"/>");
            writer.println("</p></body>");
            writer.println("</html>");
            writer.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
