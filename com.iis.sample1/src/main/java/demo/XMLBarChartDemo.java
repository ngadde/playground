/* --------------------
 * XMLBarChartDemo.java
 * --------------------
 * (C) Copyright 2002-2005, by Object Refinery Limited.
 *
 */

package demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xml.DatasetReader;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a bar chart using 
 * data from an XML data file.
 */
public class XMLBarChartDemo extends ApplicationFrame {

    /**
     * Default constructor.
     *
     * @param title  the frame title.
     */
    public XMLBarChartDemo(String title) {

        super(title);

        // create a dataset...
        CategoryDataset dataset = null;
        URL url = getClass().getResource("/demo/categorydata.xml");

        try {
            InputStream in = url.openStream();
            dataset = DatasetReader.readCategoryDatasetFromXML(in);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
            "Bar Chart",  // chart title
            "Domain", 
            "Range",
            dataset,      // data
            PlotOrientation.VERTICAL,
            true,         // include legend
            true,
            false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        XMLBarChartDemo demo = new XMLBarChartDemo("XML Bar Chart Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
