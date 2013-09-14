/* ---------------------
 * TimeSeriesDemo10.java
 * ---------------------
 * (C) Copyright 2003-2009, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing a time series with per minute data.
 */
public class TimeSeriesDemo10 extends ApplicationFrame {

    /**
     * A demonstration application.
     *
     * @param title  the frame title.
     */
    public TimeSeriesDemo10(String title) {

        super(title);
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Time Series Demo 10",
            "Time",
            "Value",
            dataset,
            true,
            true,
            false
        );
        return chart;
    }

    private static XYDataset createDataset() {
        TimeSeries series = new TimeSeries("Per Minute Data");
        Hour hour = new Hour();
        series.add(new Minute(1, hour), 10.2);
        series.add(new Minute(3, hour), 17.3);
        series.add(new Minute(9, hour), 14.6);
        series.add(new Minute(11, hour), 11.9);
        series.add(new Minute(15, hour), 13.5);
        series.add(new Minute(19, hour), 10.9);
        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        return dataset;
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

        TimeSeriesDemo10 demo = new TimeSeriesDemo10("Time Series Demo 10");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
