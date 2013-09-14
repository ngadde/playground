/* --------------------
 * PeriodAxisDemo2.java
 * --------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.PeriodAxis;
import org.jfree.chart.axis.PeriodAxisLabelInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimePeriodAnchor;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo of the {@link PeriodAxis} class.  In this demo, the New Zealand time
 * zone is used for both the dataset and the axis.
 */
public class PeriodAxisDemo2 extends ApplicationFrame {

    /**
     * A demonstration application showing how to create a simple time series
     * chart.  This example uses monthly data.
     *
     * @param title  the frame title.
     */
    public PeriodAxisDemo2(String title) {
        super(title);
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setMouseZoomable(true, true);
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
            "Legal & General Unit Trust Prices",
            "Date", "Price Per Unit",
            dataset,
            true,
            true,
            false
        );


        XYPlot plot = (XYPlot) chart.getPlot();

        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer renderer = plot.getRenderer();
        if (renderer instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer rr = (XYLineAndShapeRenderer) renderer;
            rr.setBaseShapesVisible(true);
            rr.setBaseShapesFilled(true);
            rr.setBaseItemLabelsVisible(true);
        }

        PeriodAxis domainAxis = new PeriodAxis("Date");
        domainAxis.setTimeZone(TimeZone.getTimeZone("Pacific/Auckland"));
        domainAxis.setAutoRangeTimePeriodClass(Day.class);
        PeriodAxisLabelInfo[] info = new PeriodAxisLabelInfo[3];
        info[0] = new PeriodAxisLabelInfo(Day.class, new SimpleDateFormat("d"));
        info[1] = new PeriodAxisLabelInfo(Month.class,
                new SimpleDateFormat("MMM"), new RectangleInsets(2, 2, 2, 2),
                new Font("SansSerif", Font.BOLD, 10), Color.blue, false,
                new BasicStroke(0.0f), Color.lightGray);
        info[2] = new PeriodAxisLabelInfo(Year.class,
                new SimpleDateFormat("yyyy"));
        domainAxis.setLabelInfo(info);
        plot.setDomainAxis(domainAxis);

        ChartUtilities.applyCurrentTheme(chart);

        return chart;

    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return the dataset.
     */
    private static XYDataset createDataset() {

        TimeSeries s1 = new TimeSeries("L&G European Index Trust");
        s1.add(new Day(24, 1, 2004), 181.8);
        s1.add(new Day(25, 1, 2004), 167.3);
        s1.add(new Day(26, 1, 2004), 153.8);
        s1.add(new Day(27, 1, 2004), 167.6);
        s1.add(new Day(28, 1, 2004), 158.8);
        s1.add(new Day(29, 1, 2004), 148.3);
        s1.add(new Day(30, 1, 2004), 153.9);
        s1.add(new Day(31, 1, 2004), 142.7);
        s1.add(new Day(1, 2, 2004), 123.2);
        s1.add(new Day(2, 2, 2004), 131.8);
        s1.add(new Day(3, 2, 2004), 139.6);
        s1.add(new Day(4, 2, 2004), 142.9);
        s1.add(new Day(5, 2, 2004), 138.7);
        s1.add(new Day(6, 2, 2004), 137.3);
        s1.add(new Day(7, 2, 2004), 143.9);
        s1.add(new Day(8, 2, 2004), 139.8);
        s1.add(new Day(9, 2, 2004), 137.0);
        s1.add(new Day(10, 2, 2004), 132.8);

        TimeZone zone = TimeZone.getTimeZone("Pacific/Auckland");
        TimeSeriesCollection dataset = new TimeSeriesCollection(zone);
        dataset.addSeries(s1);
        dataset.setXPosition(TimePeriodAnchor.MIDDLE);
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

        PeriodAxisDemo2 demo = new PeriodAxisDemo2(
                "JFreeChart: PeriodAxisDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
