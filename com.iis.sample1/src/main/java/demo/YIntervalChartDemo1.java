/* ------------------------
 * YIntervalChartDemo1.java
 * ------------------------
 * (C) Copyright 2003-2009, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.YIntervalRenderer;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a Y Interval Chart.
 */
public class YIntervalChartDemo1 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public YIntervalChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
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

        JFreeChart chart = ChartFactory.createScatterPlot(
            "Y Interval Chart Demo 1",  // chart title
            "X",                      // domain axis label
            "Y",                      // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,
            false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRenderer(new YIntervalRenderer());
        return chart;

    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static IntervalXYDataset createDataset() {
        double y = 100.0;
        YIntervalSeries series1 = new YIntervalSeries("Series 1");
        for (int i = 0; i < 100; i++) {
            y = y + (Math.random() - 0.49);
            series1.add(i, y, y - 3.0, y + 3.0);
        }
        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(series1);
        return dataset;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        YIntervalChartDemo1 demo = new YIntervalChartDemo1(
                "JFreeChart: YIntervalChartDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
