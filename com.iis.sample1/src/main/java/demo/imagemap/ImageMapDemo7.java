/* ------------------
 * ImageMapDemo7.java
 * ------------------
 * (C) Copyright 2006, by Object Refinery Limited.
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
import org.jfree.chart.labels.CategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.util.ShapeUtilities;

/**
 * A demo showing how to create an HTML image map for a bar chart.
 */
public class ImageMapDemo7 {

    static class MyCategorySeriesLabelGenerator 
            implements CategorySeriesLabelGenerator {

        public String generateLabel(CategoryDataset dataset, int series) {
            return "series-" + series + ".html";
        }
        
    }
    
    /**
     * Default constructor.
     */
    public ImageMapDemo7() {
        super();
    }

    /**
     * Creates a sample dataset.
     * 
     * @return The dataset.
     */
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(21, "Series 1", "Category 1");
        dataset.addValue(50, "Series 1", "Category 2");
        dataset.addValue(152, "Series 1", "Category 3");
        dataset.addValue(184, "Series 1", "Category 4");
        dataset.addValue(299, "Series 1", "Category 5");
        dataset.addValue(275, "Series 2", "Category 1");
        dataset.addValue(121, "Series 2", "Category 2");
        dataset.addValue(98, "Series 2", "Category 3");
        dataset.addValue(103, "Series 2", "Category 4");
        dataset.addValue(210, "Series 2", "Category 5");
        dataset.addValue(198, "Series 3", "Category 1");
        dataset.addValue(165, "Series 3", "Category 2");
        dataset.addValue(55, "Series 3", "Category 3");
        dataset.addValue(34, "Series 3", "Category 4");
        dataset.addValue(77, "Series 3", "Category 5");
        return dataset;
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  a dataset.
     * 
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {
        
        // create the chart...
        JFreeChart chart = ChartFactory.createLineChart(
            "Line Chart Demo 7",             // chart title
            "Category",                      // domain axis label
            "Count",                         // range axis label
            dataset,                         // data
            PlotOrientation.VERTICAL,        // orientation
            true,                            // include legend
            true,                            // tooltips
            false                            // urls
        );

        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);

        // customise the range axis...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // customise the renderer...
        LineAndShapeRenderer renderer 
            = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesShapesVisible(2, true);
        renderer.setSeriesLinesVisible(2, false);
        renderer.setSeriesShape(2, ShapeUtilities.createDiamond(4.0f));
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        renderer.setBaseFillPaint(Color.white);
        
        CategorySeriesLabelGenerator myURLGenerator 
                = new MyCategorySeriesLabelGenerator();
        renderer.setLegendItemURLGenerator(myURLGenerator);
        renderer.setLegendItemToolTipGenerator(myURLGenerator);
        return chart;
    }

    /**
     * Starting point for the demo.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        CategoryDataset dataset = createDataset();

        JFreeChart chart = createChart(dataset);
        
        // save it to an image
        try {
            ChartRenderingInfo info = new ChartRenderingInfo(
                    new StandardEntityCollection());
            File file1 = new File("ImageMapDemo7.png");
            ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);

            // write an HTML page incorporating the image with an image map
            File file2 = new File("ImageMapDemo7.html");
            OutputStream out = new BufferedOutputStream(
                    new FileOutputStream(file2));
            PrintWriter writer = new PrintWriter(out);
            writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"");
            writer.println("\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
            writer.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">");
            writer.println("<head><title>JFreeChart Image Map Demo 7</title></head>");
            writer.println("<body><p>");
            ImageMapUtilities.writeImageMap(writer, "chart", info);
            writer.println("<img src=\"ImageMapDemo7.png\" "
                           + "width=\"600\" height=\"400\" usemap=\"#chart\" alt=\"ImageMapDemo7.png\"/>");
            writer.println("</p></body>");
            writer.println("</html>");
            writer.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
