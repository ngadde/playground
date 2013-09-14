/* --------------------
 * PeriodAxisDemo3.java
 * --------------------
 * (C) Copyright 2006, 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.PeriodAxis;
import org.jfree.chart.axis.PeriodAxisLabelInfo;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * In this demo, the {@link PeriodAxis} class is used to display both date and
 * day-of-the-week labels on a bar chart.
 */
public class PeriodAxisDemo3 extends ApplicationFrame {

    /**
     * A demonstration application showing how to create a simple time series
     * chart.  This example uses monthly data.
     *
     * @param title  the frame title.
     */
    public PeriodAxisDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart.
     *
     * @param dataset  a dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(IntervalXYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYBarChart("Maximum Temperature",
                "Day", true, "Temperature", dataset, PlotOrientation.VERTICAL,
                true, true, false);


        XYPlot plot = (XYPlot) chart.getPlot();

        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        PeriodAxis domainAxis = new PeriodAxis("Day");
        domainAxis.setAutoRangeTimePeriodClass(Day.class);
        PeriodAxisLabelInfo[] info = new PeriodAxisLabelInfo[3];
        info[0] = new PeriodAxisLabelInfo(Day.class, new SimpleDateFormat("d"));
        info[1] = new PeriodAxisLabelInfo(Day.class, new SimpleDateFormat("E"),
            new RectangleInsets(2, 2, 2, 2), new Font("SansSerif", Font.BOLD,
            10), Color.blue, false, new BasicStroke(0.0f), Color.lightGray);
        info[2] = new PeriodAxisLabelInfo(Month.class,
            new SimpleDateFormat("MMM"));
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
    private static IntervalXYDataset createDataset() {
        TimeSeries s1 = new TimeSeries("Temperature");
        s1.add(new Day(1, 4, 2006), 14.5);
        s1.add(new Day(2, 4, 2006), 11.5);
        s1.add(new Day(3, 4, 2006), 13.7);
        s1.add(new Day(4, 4, 2006), 10.5);
        s1.add(new Day(5, 4, 2006), 14.9);
        s1.add(new Day(6, 4, 2006), 15.7);
        s1.add(new Day(7, 4, 2006), 11.5);
        s1.add(new Day(8, 4, 2006), 9.5);
        s1.add(new Day(9, 4, 2006), 10.9);
        s1.add(new Day(10, 4, 2006), 14.1);
        s1.add(new Day(11, 4, 2006), 12.3);
        s1.add(new Day(12, 4, 2006), 14.3);
        s1.add(new Day(13, 4, 2006), 19.0);
        s1.add(new Day(14, 4, 2006), 17.9);
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
        PeriodAxisDemo3 demo = new PeriodAxisDemo3(
                "JFreeChart: PeriodAxisDemo3.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
