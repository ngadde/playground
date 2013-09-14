/* ------------------
 * ImageMapDemo4.java
 * ------------------
 * (C) Copyright 2004-2006, by Object Refinery Limited.
 *
 */

package demo.imagemap;

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
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.imagemap.ImageMapUtilities;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.util.TableOrder;

/**
 * Creates an HTML image map for a multiple pie chart.
 */
public class ImageMapDemo4 {

    /**
     * Default constructor.
     */
    public ImageMapDemo4() {
        super();
    }
    
    /**
     * Saves the chart image and HTML.
     */
    public void saveImageAndHTML() {
        
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        // save it to an image
        try {
            ChartRenderingInfo info = new ChartRenderingInfo(
                    new StandardEntityCollection());
            File file1 = new File("multipiechart100.png");
            ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);

            // write an HTML page incorporating the image with an image map
            File file2 = new File("multipiechart100.html");
            OutputStream out = new BufferedOutputStream(new FileOutputStream(
                    file2));
            PrintWriter writer = new PrintWriter(out);
            writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"");
            writer.println("\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
            writer.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">");
            writer.println("<head><title>JFreeChart Image Map Demo</title></head>");
            writer.println("<body><p>");
            ImageMapUtilities.writeImageMap(writer, "chart", info);
            writer.println("<img src=\"multipiechart100.png\" "
                    + "width=\"600\" height=\"400\" usemap=\"#chart\" alt=\"multipiechart100.png\"/>");
            writer.println("</p></body>");
            writer.println("</html>");
            writer.close();

        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    private CategoryDataset createDataset() {
        double[][] data = new double[][] {
            {3.0, 4.0, 3.0, 5.0},
            {5.0, 7.0, 6.0, 8.0},
            {5.0, 7.0, 3.0, 8.0},
            {1.0, 2.0, 3.0, 4.0},
            {2.0, 3.0, 2.0, 3.0}
        };
        CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
            "Region ",
            "Sales/Q",
            data
        );
        return dataset;
    }
    /**
     * Creates a sample chart with the given dataset.
     * 
     * @param dataset  the dataset.
     * 
     * @return A sample chart.
     */
    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createMultiplePieChart(
            "Multiple Pie Chart",  // chart title
            dataset,               // dataset
            TableOrder.BY_ROW,
            true,                  // include legend
            true,
            true
        );
        MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
        JFreeChart subchart = plot.getPieChart();
        PiePlot p = (PiePlot) subchart.getPlot();
        p.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}"));
        p.setLabelFont(new Font("SansSerif", Font.PLAIN, 8));
        
        return chart;
    }

    /**
     * Starting point for the demo.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        ImageMapDemo4 demo = new ImageMapDemo4();
        demo.saveImageAndHTML();
    }

}
