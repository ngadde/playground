/* ---------------------
 * PerformanceTest1.java
 * ---------------------
 * (C) Copyright 2004-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * This test has been put together to investigate a performance problem
 * reported for the TimeSeries class.  It concerns the time taken (increasing
 * over time) to add values to a time series in batches (200 in the report).
 */
public class PerformanceTest1 extends ApplicationFrame {

    private TimeSeries timings;

    /**
     * A performance test.
     *
     * @param title  the frame title.
     */
    public PerformanceTest1(String title) {
        super(title);
        this.timings = new TimeSeries("Timings");
        TimeSeriesCollection dataset = new TimeSeriesCollection(this.timings);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setMouseZoomable(true);
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart.
     *
     * @param dataset  a dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Performance Test 1",
            "Time", "Milliseconds",
            dataset,
            true,
            true,
            false
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer renderer = plot.getRenderer();
        if (renderer instanceof StandardXYItemRenderer) {
            StandardXYItemRenderer rr = (StandardXYItemRenderer) renderer;
            rr.setSeriesStroke(0, new BasicStroke(1.1f));
        }

        return chart;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(null);
        return new ChartPanel(chart);
    }

    /**
     * Adds an observation.
     *
     * @param timeTaken  the time taken (in milliseconds).
     */
    public void addObservation(long timeTaken) {
        this.timings.addOrUpdate(new Millisecond(), timeTaken);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main2(String[] args) {

        PerformanceTest1 demo = new PerformanceTest1("Performance Test 1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        TimeSeries series = new TimeSeries("Test");
        series.setMaximumItemAge(200);
        while (true) {
            Millisecond t = new Millisecond();
            long start = System.currentTimeMillis();
            for (int i = 0; i < 200; i++) {
                t = (Millisecond) t.next();
                series.addOrUpdate(t, 1.0);
            }
            long end = System.currentTimeMillis();
            demo.addObservation(end - start);
        }
    }

    /**
     * Entry point 4.
     *
     * @param args ignored.
     */
    public static void main4(String[] args) {
        TimeSeries test = new TimeSeries("Test");
        test.setMaximumItemCount(4000);
        FixedMillisecond t = new FixedMillisecond();
        for (int i = 0; i < 40000; i++) {
            long start = System.currentTimeMillis();
            for (int j = 0; j < 400; j++) {
                t = (FixedMillisecond) t.next();

                test.add(t, Math.random());
            }
            long end = System.currentTimeMillis();
            System.out.println(i + " --> " + (end - start) + " (" + Runtime.getRuntime().freeMemory() + " / " + Runtime.getRuntime().totalMemory() + ")");
        }
    }

    /**
     * Entry point 5.
     *
     * @param args ignored.
     */
    public static void main5(String[] args) {
        XYSeries test = new XYSeries("Test");
        test.setMaximumItemCount(4000);
        int t = 0;
        for (int i = 0; i < 40000; i++) {
            long start = System.currentTimeMillis();
            for (int j = 0; j < 4000; j++) {
                test.add(t++, Math.random());
            }
            long end = System.currentTimeMillis();
            System.out.println(i + " --> " + (end - start) + " (" + Runtime.getRuntime().freeMemory() + " / " + Runtime.getRuntime().totalMemory() + ")");
        }
    }

    /**
     * Entry point 1.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        final int LIST_SIZE = 4000;
        final int REPETITIONS = 1000000;
        final int LOOP_COUNT = 20000;
        List list = new ArrayList();

        for (int i = 0; i < LIST_SIZE; i++) {
            list.add(new Double(Math.random()));
        }
        int x = 0;
        for (int i = 0; i < LOOP_COUNT; i++) {
            long start = System.currentTimeMillis();
            for (int j = 0; j < REPETITIONS; j++) {
                //double d = Math.random();
                //list.set(399, new Double(Math.random()));
                //list.remove(0);
                x = x + j;
            }
            long end = System.currentTimeMillis();
            System.out.println(i + " --> " + (end - start) + " (" + Runtime.getRuntime().freeMemory() + " / " + Runtime.getRuntime().totalMemory() + ")");
        }
   }

    /**
     * Entry point 3.
     *
     * @param args ignored.
     */
    public static void main3(String[] args) {
        List list = new ArrayList();
        Millisecond t = new Millisecond();
        for (int j = 0; j < 200; j++) {
            t = (Millisecond) t.next();
            list.add(t);
        }
       for (int i = 0; i < 2000; i++) {
            long start = System.currentTimeMillis();
            /* int x = */ Collections.binarySearch(list, new Millisecond());
            long end = System.currentTimeMillis();
            System.out.println(i + " --> " + (end - start) + " (" + Runtime.getRuntime().freeMemory() + " / " + Runtime.getRuntime().totalMemory() + ")");
        }
    }

}
