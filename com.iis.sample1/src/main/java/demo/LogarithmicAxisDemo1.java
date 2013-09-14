/* -------------------------
 * LogarithmicAxisDemo1.java
 * -------------------------
 * (C) Copyright 2005-2008, by Object Refinery Limited.
 *
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demo showing the use of the {@link LogarithmicAxis} class.
 */
public class LogarithmicAxisDemo1 extends ApplicationFrame {

    /**
     * Creates a new instance of the demo.
     *
     * @param title  the frame title.
     */
    public LogarithmicAxisDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createScatterPlot(
            "Logarithmic Axis Demo 1",
            "X",
            "Y",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        LogarithmicAxis xAxis = new LogarithmicAxis("X");
        LogarithmicAxis yAxis = new LogarithmicAxis("Y");
        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis);
        // make sure the current theme is applied to the axes just added
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static XYDataset createDataset() {
        XYSeries series = new XYSeries("Random Data");
        series.add(1.0, 500.2);
        series.add(5.0, 694.1);
        series.add(4.0, 100.0);
        series.add(12.5, 734.4);
        series.add(17.3, 453.2);
        series.add(21.2, 500.2);
        series.add(21.9, 9005.5);
        series.add(25.6, 734.4);
        series.add(3000.0, 453.2);
        return new XYSeriesCollection(series);
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        LogarithmicAxisDemo1 demo = new LogarithmicAxisDemo1(
                "JFreeChart: LogarithmicAxisDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
