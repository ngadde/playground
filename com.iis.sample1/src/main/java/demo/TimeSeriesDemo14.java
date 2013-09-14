/* ---------------------
 * TimeSeriesDemo14.java
 * ---------------------
 * (C) Copyright 2006, 2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A time series chart.
 */
public class TimeSeriesDemo14 extends ApplicationFrame {

    /**
     * A demonstration application...
     *
     * @param title  the frame title.
     */
    public TimeSeriesDemo14(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
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
            "Bug Report Submissions for Java",  // title
            "Date",             // x-axis label
            "Evaluation ID",   // y-axis label
            dataset,            // data
            true,               // create legend?
            true,               // generate tooltips?
            false               // generate URLs?
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setUseFillPaint(true);
            renderer.setBaseFillPaint(Color.white);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));

        return chart;

    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return the dataset.
     */
    private static XYDataset createDataset() {

        TimeSeries s1 = new TimeSeries("Bugs");
        s1.add(new Day(27, 6, 2005), 478474);
        s1.add(new Day(24, 1, 2006), 633804);
        s1.add(new Day(28, 4, 2006), 694096);
        s1.add(new Day(12, 5, 2006), 704680);
        //s1.add(new Day(16, 5, 2005), 709592);
        s1.add(new Day(16, 5, 2006), 709599);
        s1.add(new Day(21, 6, 2006), 734754);
        s1.add(new Day(27, 7, 2006), 760008);

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

        TimeSeriesDemo14 demo = new TimeSeriesDemo14("Time Series Demo 14");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
