/* --------------------
 * XMLPieChartDemo.java
 * --------------------
 * (C) Copyright 2002-2004, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xml.DatasetReader;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a pie chart from data in an
 * XML file.
 */
public class XMLPieChartDemo extends ApplicationFrame {

    /**
     * Default constructor.
     *
     * @param title  the frame title.
     */
    public XMLPieChartDemo(String title) {

        super(title);

        // create a dataset...
        PieDataset dataset = null;
        URL url = getClass().getResource("/org/jfree/chart/demo/piedata.xml");

        try {
            InputStream in = url.openStream();
            dataset = DatasetReader.readPieDatasetFromXML(in);
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        // create the chart...
        JFreeChart chart = ChartFactory.createPieChart(
            "Pie Chart Demo 1",  // chart title
            dataset,             // data
            true,                // include legend
            true,
            false
        );

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.yellow);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {2}",
                NumberFormat.getNumberInstance(), 
                NumberFormat.getPercentInstance()));
        plot.setNoDataMessage("No data available");

        // add the chart to a panel...
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

        XMLPieChartDemo demo = new XMLPieChartDemo("XML Pie Chart Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
