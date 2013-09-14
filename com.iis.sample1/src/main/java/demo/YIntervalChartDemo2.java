/* ------------------------
 * YIntervalChartDemo2.java
 * ------------------------
 * (C) Copyright 2007, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A time series chart that displays each observation along with its
 * standard deviation.
 */
public class YIntervalChartDemo2 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public YIntervalChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private static void add(YIntervalSeries s, int y, int m, int d, double v,
            double std) {
        Calendar cal = Calendar.getInstance();
        cal.set(y, m, d);
        s.add(cal.getTime().getTime(), v, v - std, v + std);
    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private static IntervalXYDataset createDataset() {

        YIntervalSeries s1 = new YIntervalSeries("Series 1");
        add(s1, 2005, Calendar.MAY, 16, 1309.0, 13.0);
        add(s1, 2005, Calendar.OCTOBER, 18, 1312.0, 12.0);
        add(s1, 2005, Calendar.NOVEMBER, 7, 1309.0, 12.0);
        add(s1, 2006, Calendar.JANUARY, 12, 1311.0, 12.0);
        add(s1, 2006, Calendar.FEBRUARY, 7, 1311.0, 13.0);
        add(s1, 2006, Calendar.APRIL, 3, 1309.0, 13.0);
        add(s1, 2006, Calendar.APRIL, 4, 1307.0, 13.0);
        add(s1, 2006, Calendar.APRIL, 6, 1305.0, 13.0);
        add(s1, 2006, Calendar.APRIL, 13, 1303.0, 13.0);
        add(s1, 2006, Calendar.APRIL, 25, 1308.0, 13.0);
        add(s1, 2006, Calendar.APRIL, 28, 1311.0, 13.0);
        add(s1, 2006, Calendar.MAY, 02, 1306.0, 13.0);
        add(s1, 2006, Calendar.MAY, 15, 1303.0, 13.0);
        add(s1, 2006, Calendar.MAY, 18, 1311.0, 13.0);
        add(s1, 2006, Calendar.NOVEMBER, 16, 1301.0, 13.0);

        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(s1);
        return dataset;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  a dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(IntervalXYDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createXYLineChart(
            "YIntervalChartDemo2",   // chart title
            "Date",                    // domain axis label
            "Value",                   // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );


        // customise the plot
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        
        // replace the number axis with a date axis
        plot.setDomainAxis(new DateAxis("Date"));

        // customise the range axis...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(false);

        // customise the renderer...
        XYErrorRenderer renderer = new XYErrorRenderer();
        renderer.setBaseLinesVisible(true);
        renderer.setUseFillPaint(true);
        renderer.setBaseFillPaint(Color.white);
        plot.setRenderer(renderer);
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
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
        YIntervalChartDemo2 demo = new YIntervalChartDemo2(
                "JFreeChart: YIntervalChartDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
