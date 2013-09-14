/* ----------------------
 * MultipleAxisDemo1.java
 * ----------------------
 * (C) Copyright 2003-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * This demo shows a time series chart that has multiple range axes.
 */
public class MultipleAxisDemo1 extends ApplicationFrame {

    /**
     * A demonstration application showing how to create a time series chart
     * with multiple axes.
     *
     * @param title  the frame title.
     */
    public MultipleAxisDemo1(String title) {
        super(title);
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
        setContentPane(chartPanel);
    }

    /**
     * Creates the demo chart.
     *
     * @return The chart.
     */
    private static JFreeChart createChart() {

        XYDataset dataset1 = createDataset("Series 1", 100.0, new Minute(),
                200);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Multiple Axis Demo 1",
            "Time of Day",
            "Primary Range Axis",
            dataset1,
            true,
            true,
            false
        );

        chart.addSubtitle(new TextTitle("Four datasets and four range axes."));
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.getRangeAxis().setFixedDimension(15.0);

        // AXIS 2
        NumberAxis axis2 = new NumberAxis("Range Axis 2");
        axis2.setFixedDimension(10.0);
        axis2.setAutoRangeIncludesZero(false);
        plot.setRangeAxis(1, axis2);
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_LEFT);

        XYDataset dataset2 = createDataset("Series 2", 1000.0, new Minute(),
                170);
        plot.setDataset(1, dataset2);
        plot.mapDatasetToRangeAxis(1, 1);
        XYItemRenderer renderer2 = new StandardXYItemRenderer();
        plot.setRenderer(1, renderer2);

        // AXIS 3
        NumberAxis axis3 = new NumberAxis("Range Axis 3");
        plot.setRangeAxis(2, axis3);

        XYDataset dataset3 = createDataset("Series 3", 10000.0, new Minute(),
                170);
        plot.setDataset(2, dataset3);
        plot.mapDatasetToRangeAxis(2, 2);
        XYItemRenderer renderer3 = new StandardXYItemRenderer();
        plot.setRenderer(2, renderer3);

        // AXIS 4
        NumberAxis axis4 = new NumberAxis("Range Axis 4");
        plot.setRangeAxis(3, axis4);

        XYDataset dataset4 = createDataset("Series 4", 25.0, new Minute(), 200);
        plot.setDataset(3, dataset4);
        plot.mapDatasetToRangeAxis(3, 3);

        XYItemRenderer renderer4 = new StandardXYItemRenderer();
        plot.setRenderer(3, renderer4);

        ChartUtilities.applyCurrentTheme(chart);

        // change the series and axis colours after the theme has
        // been applied...
        plot.getRenderer().setSeriesPaint(0, Color.black);
        renderer2.setSeriesPaint(0, Color.red);
        axis2.setLabelPaint(Color.red);
        axis2.setTickLabelPaint(Color.red);
        renderer3.setSeriesPaint(0, Color.blue);
        axis3.setLabelPaint(Color.blue);
        axis3.setTickLabelPaint(Color.blue);
        renderer4.setSeriesPaint(0, Color.green);
        axis4.setLabelPaint(Color.green);
        axis4.setTickLabelPaint(Color.green);

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
        MultipleAxisDemo1 demo = new MultipleAxisDemo1(
                "JFreeChart: MultipleAxisDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}

