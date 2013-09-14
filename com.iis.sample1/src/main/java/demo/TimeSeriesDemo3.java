/* --------------------
 * TimeSeriesDemo3.java
 * --------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
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
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.labels.StandardXYSeriesLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A time series demo, with monthly data, where the tick unit on the axis is
 * set to one month also (this switches off the auto tick unit selection, and
 * *can* result in overlapping labels).
 */
public class TimeSeriesDemo3 extends ApplicationFrame {

    /**
     * A demonstration application showing a quarterly time series containing
     * a null value.
     *
     * @param title  the frame title.
     */
    public TimeSeriesDemo3(String title) {

        super(title);
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    private static XYDataset createDataset() {
        TimeSeries series1 = new TimeSeries("Series 1");
        series1.add(new Month(1, 2002), 500.2);
        series1.add(new Month(2, 2002), 694.1);
        series1.add(new Month(3, 2002), 734.4);
        series1.add(new Month(4, 2002), 453.2);
        series1.add(new Month(5, 2002), 500.2);
        series1.add(new Month(6, 2002), 345.6);
        series1.add(new Month(7, 2002), 500.2);
        series1.add(new Month(8, 2002), 694.1);
        series1.add(new Month(9, 2002), 734.4);
        series1.add(new Month(10, 2002), 453.2);
        series1.add(new Month(11, 2002), 500.2);
        series1.add(new Month(12, 2002), 345.6);

        TimeSeries series2 = new TimeSeries("Series 2");
        series2.add(new Month(1, 2002), 234.1);
        series2.add(new Month(2, 2002), 623.7);
        series2.add(new Month(3, 2002), 642.5);
        series2.add(new Month(4, 2002), 651.4);
        series2.add(new Month(5, 2002), 643.5);
        series2.add(new Month(6, 2002), 785.6);
        series2.add(new Month(7, 2002), 234.1);
        series2.add(new Month(8, 2002), 623.7);
        series2.add(new Month(9, 2002), 642.5);
        series2.add(new Month(10, 2002), 651.4);
        series2.add(new Month(11, 2002), 643.5);
        series2.add(new Month(12, 2002), 785.6);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    /**
     * Creates a new chart.
     *
     * @param dataset  the dataset.
     *
     * @return The dataset.
     */
    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Time Series Demo 3",
            "Time",
            "Value",
            dataset,
            true,
            true,
            false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setTickUnit(new DateTickUnit(DateTickUnitType.MONTH, 1,
                new SimpleDateFormat("MMM-yyyy")));
        axis.setVerticalTickLabels(true);

        XYLineAndShapeRenderer renderer
            = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseShapesVisible(true);
        renderer.setSeriesFillPaint(0, Color.red);
        renderer.setSeriesFillPaint(1, Color.white);
        renderer.setUseFillPaint(true);
        renderer.setLegendItemToolTipGenerator(
                new StandardXYSeriesLabelGenerator("Tooltip {0}"));
        return chart;
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

        TimeSeriesDemo3 demo = new TimeSeriesDemo3("Time Series Demo 3");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
