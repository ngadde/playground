/* ---------------------
 * TimeSeriesDemo12.java
 * ---------------------
 * (C) Copyright 2003-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo.
 */
public class TimeSeriesDemo12 extends ApplicationFrame {

    /**
     * A demo.
     *
     * @param title  the frame title.
     */
    public TimeSeriesDemo12(String title) {

        super(title);
        XYDataset dataset = createDataset();
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

        JFreeChart chart = ChartFactory.createTimeSeriesChart("Sample Chart",
                "Date", "Value", dataset, true, true, false);


        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(false);

        XYItemRenderer renderer = plot.getRenderer();
        if (renderer instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer rr = (XYLineAndShapeRenderer) renderer;
            rr.setBaseShapesVisible(true);
            rr.setBaseShapesFilled(true);
            renderer.setSeriesStroke(0, new BasicStroke(2.0f));
            renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("hh:mma"));

        return chart;

    }

    /**
     * Creates a sample dataset.
     *
     * @return the dataset.
     */
    private static XYDataset createDataset() {

        TimeSeriesCollection dataset = new TimeSeriesCollection();

        TimeSeries s1 = new TimeSeries("Series 1");
        s1.add(new Minute(0, 0, 7, 12, 2003), 1.2);
        s1.add(new Minute(30, 12, 7, 12, 2003), 3.0);
        s1.add(new Minute(15, 14, 7, 12, 2003), 8.0);

        TimeSeries s2 = new TimeSeries("Series 2");
        s2.add(new Minute(0, 3, 7, 12, 2003), 0.0);
        s2.add(new Minute(30, 9, 7, 12, 2003), 0.0);
        s2.add(new Minute(15, 10, 7, 12, 2003), 0.0);
        dataset.addSeries(s1);
        dataset.addSeries(s2);

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
        TimeSeriesDemo12 demo = new TimeSeriesDemo12("Time Series Demo 12");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
