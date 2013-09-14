/* ------------------------------
 * CustomXYURLGeneratorDemo1.java
 * ------------------------------
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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.urls.CustomXYURLGenerator;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * This demo creates an HTML image map where the URLs for the hyperlinks on 
 * the chart are specified using a {@link CustomXYURLGenerator}).
 */
public class CustomXYURLGeneratorDemo1 {

    /**
     * Default constructor.
     */
    public CustomXYURLGeneratorDemo1() {
        super();
    }

    /**
     * Starting point for the demo.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries s1 = new XYSeries("Series 1");
        s1.add(1.0, 3.0);
        s1.add(2.0, 5.0);
        s1.add(3.0, 4.0);
        XYSeries s2 = new XYSeries("Series 2");
        s2.add(1.0, 7.0);
        s2.add(2.0, 6.0);
        s2.add(3.0, 8.0);
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "CustomXYURLGeneratorDemo1",
                null, "Strength", dataset, PlotOrientation.VERTICAL,
                true, true, false);
        
        chart.setBackgroundPaint(java.awt.Color.white);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setUseFillPaint(true);
        renderer.setBaseFillPaint(Color.white);
        renderer.setBaseShapesVisible(true);
        
        
        // prespecify the URLs for each data item...
        CustomXYURLGenerator urls = new CustomXYURLGenerator();
        List list = new java.util.ArrayList();
        list.add("http://www.bbc.co.uk/");
        list.add("http://www.javalobby.org/");
        list.add("http://www.jfree.org/");
        urls.addURLSeries(list);
        
        List list2 = new java.util.ArrayList();
        list2.add("http://www.theonion.com/");
        list2.add("http://www.cafeaulait.org/");
        urls.addURLSeries(list2);
        
        renderer.setURLGenerator(urls);
        
        // save it to an image
        try {
            ChartRenderingInfo info = new ChartRenderingInfo(
                    new StandardEntityCollection());
            File file1 = new File("customXYURLDemo1.png");
            ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);

            // write an HTML page incorporating the image with an image map
            File file2 = new File("customXYURLDemo1.html");
            OutputStream out = new BufferedOutputStream(new FileOutputStream(
                    file2));
            PrintWriter writer = new PrintWriter(out);
            writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"");
            writer.println("\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
            writer.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">");
            writer.println("<head><title>JFreeChart: CustomXYURLGeneratorDemo1</title></head>");
            writer.println("<body><p>");
            ImageMapUtilities.writeImageMap(writer, "chart", info);
            writer.println("<img src=\"customXYURLDemo1.png\" "
                           + "width=\"600\" height=\"400\" usemap=\"#chart\" alt=\"customXYURLDemo1.png\"/>");
            writer.println("</p></body>");
            writer.println("</html>");
            writer.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
