/* --------------------
 * TimeSeriesDemo6.java
 * --------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A time series chart with all zero data.  When the data range is zero, you
 * may want to modify the default behaviour of the range axis.
 */
public class TimeSeriesDemo6 extends ApplicationFrame {

    /**
     * Creates a new instance.
     *
     * @param title  the frame title.
     */
    public TimeSeriesDemo6(String title) {

        super(title);
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return a chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Time Series Demo 6",
            "Date",
            "Value",
            dataset,
            true,
            true,
            false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setAutoRangeMinimumSize(1.0);
        return chart;

    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return the dataset.
     */
    private static XYDataset createDataset() {

        double value = 0.0;
        TimeSeries s1 = new TimeSeries("Series 1");
        s1.add(new Month(2, 2001), value);
        s1.add(new Month(3, 2001), value);
        s1.add(new Month(4, 2001), value);
        s1.add(new Month(5, 2001), value);
        s1.add(new Month(6, 2001), value);
        s1.add(new Month(7, 2001), value);
        s1.add(new Month(8, 2001), value);
        s1.add(new Month(9, 2001), value);
        s1.add(new Month(10, 2001), value);
        s1.add(new Month(11, 2001), value);
        s1.add(new Month(12, 2001), value);
        s1.add(new Month(1, 2002), value);
        s1.add(new Month(2, 2002), value);
        s1.add(new Month(3, 2002), value);
        s1.add(new Month(4, 2002), value);
        s1.add(new Month(5, 2002), value);
        s1.add(new Month(6, 2002), value);
        s1.add(new Month(7, 2002), value);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

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

        TimeSeriesDemo6 demo = new TimeSeriesDemo6("Time Series Demo 6");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
