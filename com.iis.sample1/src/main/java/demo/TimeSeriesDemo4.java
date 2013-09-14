/* --------------------
 * TimeSeriesDemo4.java
 * --------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * An example of a time series chart using hourly data and including a null
 * value.  The plot has an image set for the background, and a blue range
 * marker is added to the plot.
 */
public class TimeSeriesDemo4 extends ApplicationFrame {

    /**
     * A demonstration application showing a quarterly time series containing a
     * null value.
     *
     * @param title  the frame title.
     */
    public TimeSeriesDemo4(String title) {

        super(title);
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setMouseZoomable(true);
        setContentPane(chartPanel);

    }

    private static XYDataset createDataset() {
        TimeSeries series = new TimeSeries("Random Data");
        Day today = new Day();
        series.add(new Hour(0, today), 500.2);
        series.add(new Hour(2, today), 694.1);
        series.add(new Hour(3, today), 734.4);
        series.add(new Hour(4, today), 453.2);
        series.add(new Hour(7, today), 500.2);
        series.add(new Hour(8, today), null);
        series.add(new Hour(12, today), 734.4);
        series.add(new Hour(16, today), 453.2);
        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        return dataset;
    }

    private static JFreeChart createChart(XYDataset dataset) {
        // create a title with Unicode characters (currency symbols in this case)...
        String chartTitle = "\u20A2\u20A2\u20A3\u20A4\u20A5\u20A6\u20A7\u20A8\u20A9\u20AA";
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            chartTitle,
            "Time",
            "Value",
            dataset,
            true,
            true,
            false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setInsets(new RectangleInsets(0.0, 0.0, 0.0, 20.0));
        Marker marker = new ValueMarker(700.0);
        marker.setPaint(Color.blue);
        marker.setAlpha(0.8f);
        plot.addRangeMarker(marker);
        plot.setBackgroundPaint(null);
        plot.setBackgroundImage(JFreeChart.INFO.getLogo());
        plot.getDomainAxis().setLowerMargin(0.0);
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

        TimeSeriesDemo4 demo = new TimeSeriesDemo4("Time Series Demo 4");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
