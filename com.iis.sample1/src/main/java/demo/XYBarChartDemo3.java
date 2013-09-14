/* --------------------
 * XYBarChartDemo3.java
 * --------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a vertical bar chart.
 */
public class XYBarChartDemo3 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYBarChartDemo3(String title) {

        super(title);
        IntervalXYDataset dataset = new SimpleIntervalXYDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel);

    }

    /**
     * Creates a new chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    private static JFreeChart createChart(IntervalXYDataset dataset) {
    
        JFreeChart chart = ChartFactory.createXYBarChart(
            "Sample",  // chart title
            "X",       // domain axis label
            false,
            "Y",       // range axis label
            dataset,   // data
            PlotOrientation.VERTICAL,
            true,      // include legend
            true,
            false
        );

        return chart;
        
    }
    
    /**
     * Creates a panel for the demo.
     *  
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(new SimpleIntervalXYDataset()));
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        XYBarChartDemo3 demo = new XYBarChartDemo3("XY Bar Chart Demo 3");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
