/* ---------------------
 * TimeSeriesDemo11.java
 * ---------------------
 * (C) Copyright 2003-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.date.SerialDate;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * An example of....
 */
public class TimeSeriesDemo11 extends ApplicationFrame {

    /**
     * A demonstration application showing how to...
     *
     * @param title  the frame title.
     */
    public TimeSeriesDemo11(String title) {

        super(title);
        setContentPane(createDemoPanel());

    }

    /**
     * Creates the demo chart.
     *
     * @param title  the title.
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(String title, XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            title,
            "Date",
            "Price",
            dataset,
            true,
            true,
            false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setOrientation(PlotOrientation.VERTICAL);

        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.blue);

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
    private static XYDataset createDataset(String name,
                                           double base,
                                           RegularTimePeriod start,
                                           int count) {

        TimeSeries series = new TimeSeries(name);
        RegularTimePeriod period = start;
        double value = base;
        for (int i = 0; i < count; i++) {
            series.add(period, value);
            period = period.previous();
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
        DemoPanel panel = new DemoPanel(new GridLayout(2, 2));
        panel.setPreferredSize(new java.awt.Dimension(800, 600));

        Day today = new Day();
        XYDataset dataset = createDataset("Series 1", 100.0, today, 365);

        JFreeChart chart1 = createChart("Chart 1 : 1 Year", dataset);
        ChartPanel chartPanel1 = new ChartPanel(chart1);
        panel.add(chartPanel1);

        JFreeChart chart2 = createChart("Chart 2 : 6 Months", dataset);
        SerialDate t = today.getSerialDate();
        SerialDate t6m = SerialDate.addMonths(-6, t);
        Day sixMonthsAgo = new Day(t6m);
        XYPlot plot2 = (XYPlot) chart2.getPlot();
        DateAxis axis2 = (DateAxis) plot2.getDomainAxis();
        axis2.setRange(sixMonthsAgo.getStart(), today.getEnd());
        ChartPanel chartPanel2 = new ChartPanel(chart2);
        panel.add(chartPanel2);

        JFreeChart chart3 = createChart("Chart 3 : 3 Months", dataset);
        SerialDate t3m = SerialDate.addMonths(-3, t);
        Day threeMonthsAgo = new Day(t3m);
        XYPlot plot3 = (XYPlot) chart3.getPlot();
        DateAxis axis3 = (DateAxis) plot3.getDomainAxis();
        axis3.setRange(threeMonthsAgo.getStart(), today.getEnd());
        ChartPanel chartPanel3 = new ChartPanel(chart3);
        panel.add(chartPanel3);

        JFreeChart chart4 = createChart("Chart 4 : 1 Month", dataset);
        SerialDate t1m = SerialDate.addMonths(-1, t);
        Day oneMonthsAgo = new Day(t1m);
        XYPlot plot4 = (XYPlot) chart4.getPlot();
        DateAxis axis4 = (DateAxis) plot4.getDomainAxis();
        axis4.setRange(oneMonthsAgo.getStart(), today.getEnd());
        ChartPanel chartPanel4 = new ChartPanel(chart4);
        panel.add(chartPanel4);
        panel.addChart(chart1);
        panel.addChart(chart2);
        panel.addChart(chart3);
        panel.addChart(chart4);
        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        TimeSeriesDemo11 demo = new TimeSeriesDemo11("Time Series Demo 11");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
