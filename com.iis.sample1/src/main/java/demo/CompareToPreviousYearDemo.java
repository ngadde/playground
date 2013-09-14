/* ------------------------------
 * CompareToPreviousYearDemo.java
 * ------------------------------
 * (C) Copyright 2008, 2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimePeriodAnchor;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A chart that shows a time series for one year relative to a time series for
 * the previous year.
 */
public class CompareToPreviousYearDemo extends ApplicationFrame {

    /**
     * A demonstration application showing how to create a simple time series
     * chart.  This example uses monthly data.
     *
     * @param title  the frame title.
     */
    public CompareToPreviousYearDemo(String title) {
        super(title);
        JFreeChart chart = createChart();
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
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
    private static JFreeChart createChart() {

        XYDataset sales2006 = createDataset2006();
        XYDataset sales2007 = createDataset2007();

        DateAxis xAxis = new DateAxis("Date");
        Month jan2007 = new Month(1, 2007);
        Month dec2007 = new Month(12, 2007);
        xAxis.setRange(jan2007.getFirstMillisecond(),
                dec2007.getLastMillisecond());
        xAxis.setDateFormatOverride(new SimpleDateFormat("MMM"));
        xAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setUseFillPaint(true);
        renderer.setBaseFillPaint(Color.white);
        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                "{1}: {2}", new SimpleDateFormat("MMM-yyyy"),
                new DecimalFormat("0.00")));

        XYPlot plot = new XYPlot(sales2007, xAxis, new NumberAxis("Sales"),
                renderer);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        DateAxis hiddenAxis = new DateAxis();
        hiddenAxis.setVisible(false);
        plot.setDomainAxis(1, hiddenAxis);
        plot.setDataset(1, sales2006);
        plot.mapDatasetToDomainAxis(1, 1);
        XYLineAndShapeRenderer renderer2006 = new XYLineAndShapeRenderer();
        renderer2006.setSeriesPaint(0, Color.blue);
        renderer2006.setUseFillPaint(true);
        renderer2006.setBaseFillPaint(Color.white);
        renderer2006.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                "{1}: {2}", new SimpleDateFormat("MMM-yyyy"),
                new DecimalFormat("0.00")));
        plot.setRenderer(1, renderer2006);

        JFreeChart chart = new JFreeChart("Sales Comparison Chart", plot);

        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        ChartUtilities.applyCurrentTheme(chart);
        return chart;

    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return the dataset.
     */
    private static XYDataset createDataset2006() {

        TimeSeries s1 = new TimeSeries("Sales 2006");
        s1.add(new Month(1, 2006), 100.0);
        s1.add(new Month(2, 2006), 102.3);
        s1.add(new Month(3, 2006), 105.7);
        s1.add(new Month(4, 2006), 104.2);
        s1.add(new Month(5, 2006), 114.7);
        s1.add(new Month(6, 2006), 121.7);
        s1.add(new Month(7, 2006), 155.6);
        s1.add(new Month(8, 2006), 143.2);
        s1.add(new Month(9, 2006), 131.9);
        s1.add(new Month(10, 2006), 120.0);
        s1.add(new Month(11, 2006), 109.9);
        s1.add(new Month(12, 2006), 99.6);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.setXPosition(TimePeriodAnchor.MIDDLE);
        return dataset;

    }

    private static XYDataset createDataset2007() {

        TimeSeries s = new TimeSeries("Sales 2007");
        s.add(new Month(1, 2007), 163.9);
        s.add(new Month(2, 2007), 163.8);
        s.add(new Month(3, 2007), 162.0);
        s.add(new Month(4, 2007), 167.1);
        s.add(new Month(5, 2007), 170.0);
        s.add(new Month(6, 2007), 175.7);
        s.add(new Month(7, 2007), 171.9);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s);
        dataset.setXPosition(TimePeriodAnchor.MIDDLE);
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

        CompareToPreviousYearDemo demo = new CompareToPreviousYearDemo(
                "JFreeChart: CompareToPreviousYearDemo.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
