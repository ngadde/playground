/* --------------------
 * TimeSeriesDemo2.java
 * --------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing a time series (quarterly data) with a <code>null</code>
 * value.  The <code>null</code> value causes a gap in the line connecting the
 * data points.
 */
public class TimeSeriesDemo2 extends ApplicationFrame {

    /**
     * A demonstration application showing a quarterly time series containing a null value.
     *
     * @param title  the frame title.
     */
    public TimeSeriesDemo2(String title) {

        super(title);
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    private static XYDataset createDataset() {
        TimeSeries series = new TimeSeries("Quarterly Data");
        series.add(new Quarter(1, 2001), 500.2);
        series.add(new Quarter(2, 2001), 694.1);
        series.add(new Quarter(3, 2001), 734.4);
        series.add(new Quarter(4, 2001), 453.2);
        series.add(new Quarter(1, 2002), 500.2);
        series.add(new Quarter(2, 2002), null);
        series.add(new Quarter(3, 2002), 734.4);
        series.add(new Quarter(4, 2002), 453.2);
        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        return dataset;
    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Time Series Demo 2",
            "Time",
            "Value",
            dataset,
            true,
            true,
            false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.addRangeMarker(new ValueMarker(550.0));
        Quarter q = new Quarter(2, 2002);
        plot.addDomainMarker(new ValueMarker(q.getMiddleMillisecond()));
        return chart;
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

        TimeSeriesDemo2 demo = new TimeSeriesDemo2("Time Series Demo 2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
