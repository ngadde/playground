/* --------------------
 * TimeSeriesDemo9.java
 * --------------------
 * (C) Copyright 2003-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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
 * An example of a time series chart.
 */
public class TimeSeriesDemo9 extends ApplicationFrame {

    /**
     * A demonstration application showing how to create a simple time series
     * chart.  This example uses monthly data.
     *
     * @param title  the frame title.
     */
    public TimeSeriesDemo9(String title) {

        super(title);
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Time Series Demo 9",
            "Date",
            "Price Per Unit",
            dataset,
            true,
            true,
            false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setSeriesShape(0, new Ellipse2D.Double(-3.0, -3.0, 6.0,
                    6.0));
            renderer.setSeriesShape(1, new Rectangle2D.Double(-3.0, -3.0, 6.0,
                    6.0));
            GeneralPath s2 = new GeneralPath();
            s2.moveTo(0.0f, -3.0f);
            s2.lineTo(3.0f, 3.0f);
            s2.lineTo(-3.0f, 3.0f);
            s2.closePath();
            renderer.setSeriesShape(2, s2);
            GeneralPath s3 = new GeneralPath();
            s3.moveTo(-1.0f, -3.0f);
            s3.lineTo(1.0f, -3.0f);
            s3.lineTo(1.0f, -1.0f);
            s3.lineTo(3.0f, -1.0f);
            s3.lineTo(3.0f, 1.0f);
            s3.lineTo(1.0f, 1.0f);
            s3.lineTo(1.0f, 3.0f);
            s3.lineTo(-1.0f, 3.0f);
            s3.lineTo(-1.0f, 1.0f);
            s3.lineTo(-3.0f, 1.0f);
            s3.lineTo(-3.0f, -1.0f);
            s3.lineTo(-1.0f, -1.0f);
            s3.closePath();
            renderer.setSeriesShape(3, s3);
        }

        plot.getDomainAxis().setVisible(false);
        plot.getRangeAxis().setVisible(false);
        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private static XYDataset createDataset() {

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        for (int i = 0; i < 4; i++) {
            dataset.addSeries(createTimeSeries(i, 10));
        }
        return dataset;

    }

    /**
     * Creates a time series containing random daily data.
     *
     * @param series  the series index.
     * @param count  the number of items for the series.
     *
     * @return the dataset.
     */
    private static TimeSeries createTimeSeries(int series, int count) {

        TimeSeries result = new TimeSeries("Series " + series);

        Day start = new Day();
        for (int i = 0; i < count; i++) {
            result.add(start, Math.random());
            start = (Day) start.next();
        }

        return result;

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

        TimeSeriesDemo9 demo = new TimeSeriesDemo9("Time Series Demo 9");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
