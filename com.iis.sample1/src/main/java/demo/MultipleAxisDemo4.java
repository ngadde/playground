/* ----------------------
 * MultipleAxisDemo4.java
 * ----------------------
 * (C) Copyright 2007-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * An example of two time series for different time periods, plotted against
 * each other using two x-axes and two y-axes.
 */
public class MultipleAxisDemo4 extends ApplicationFrame {

    /**
     * A demonstration application showing how to create a time series chart
     * with multiple axes.
     *
     * @param title  the frame title.
     */
    public MultipleAxisDemo4(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));
        setContentPane(chartPanel);

    }

    /**
     * Creates the demo chart.
     *
     * @return The chart.
     */
    private static JFreeChart createChart() {

        XYDataset dataset1 = createDataset("March 2007", 100.0,
                new Day(1, 3, 2007), 31);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Multiple Axis Demo 4",
            "Date",
            "Value",
            dataset1,
            true,
            true,
            false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setOrientation(PlotOrientation.VERTICAL);
        DateAxis xAxis1 = (DateAxis) plot.getDomainAxis();
        xAxis1.setDateFormatOverride(new SimpleDateFormat("d-MMM-yyyy"));

        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.red);

        NumberAxis yAxis1 = (NumberAxis) plot.getRangeAxis();
        yAxis1.setTickLabelPaint(Color.red);

        // DOMAIN AXIS 2
        DateAxis xAxis2 = new DateAxis("Date");
        xAxis2.setDateFormatOverride(new SimpleDateFormat("d-MMM-yyyy"));
        plot.setDomainAxis(1, xAxis2);
        plot.setDomainAxisLocation(1, AxisLocation.TOP_OR_LEFT);

        // RANGE AXIS 2
        NumberAxis yAxis2 = new NumberAxis("Value");
        yAxis2.setAutoRangeIncludesZero(false);
        yAxis2.setTickLabelPaint(Color.blue);
        plot.setRangeAxis(1, yAxis2);
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);

        XYDataset dataset2 = createDataset("July 2007", 1000.0,
                new Day(1, 7, 2007), 31);
        plot.setDataset(1, dataset2);
        plot.mapDatasetToDomainAxis(1, 1);
        plot.mapDatasetToRangeAxis(1, 1);

        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer(true,
                false);
        renderer2.setSeriesPaint(0, Color.blue);
        plot.setRenderer(1, renderer2);

        ChartUtilities.applyCurrentTheme(chart);
        return chart;

    }

    /**
     * Creates a sample dataset.
     *
     * @param name  the dataset name.
     * @param base  the starting value.
     * @param start  the starting period.
     * @param count  the number of values to generate.
     *
     * @return The dataset.
     */
    private static XYDataset createDataset(String name, double base,
                                    RegularTimePeriod start, int count) {

        TimeSeries series = new TimeSeries(name);
        RegularTimePeriod period = start;
        double value = base;
        for (int i = 0; i < count; i++) {
            series.add(period, value);
            period = period.next();
            value = value * (1 + (Math.random() - 0.495) / 10.0);
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        return dataset;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart();
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        MultipleAxisDemo4 demo = new MultipleAxisDemo4(
                "JFreeChart: MultipleAxisDemo4.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
