/* ---------------------------
 * DeviationRendererDemo3.java
 * ---------------------------
 * (C) Copyright 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo of the {@link DeviationRenderer} class.  Here we overlay three
 * datasets to display the extent of three data bands through time.
 */
public class DeviationRendererDemo3 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public DeviationRendererDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private static XYDataset createDataset() {

        YIntervalSeries series1 = new YIntervalSeries("Band A");
        YIntervalSeries series2 = new YIntervalSeries("Band B");
        YIntervalSeries series3 = new YIntervalSeries("Band C");
        RegularTimePeriod t = new Quarter(1, 2005);
        double y1 = 0.0;
        for (int i = 0; i <= 12; i++) {
            y1 = y1 + (Math.random() - 0.50) * 15;
            series1.add(t.getMiddleMillisecond(), y1, y1 + 10, Math.max(50, y1 + 30.0));
            series2.add(t.getMiddleMillisecond(), y1, y1 - 10, y1 + 10);
            series3.add(t.getMiddleMillisecond(), y1, Math.min(-50.0, y1 - 30), y1 - 10);
            t = t.next();
        }

        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);

        return dataset;

    }

    /**
     * Creates a chart.
     *
     * @param dataset  the data for the chart.
     *
     * @return a chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createXYLineChart(
            "DeviationRenderer - Demo 3",      // chart title
            "X",                      // x axis label
            "Y",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            false,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        // get a reference to the plot for further customisation...
        XYPlot plot = (XYPlot) chart.getPlot();

        DeviationRenderer renderer = new DeviationRenderer(false, false);
        renderer.setSeriesStroke(0, new BasicStroke(3.0f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        renderer.setSeriesStroke(0, new BasicStroke(3.0f,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        renderer.setSeriesFillPaint(0, Color.red);
        renderer.setSeriesFillPaint(1, Color.orange);
        renderer.setSeriesFillPaint(2, Color.green);
        plot.setRenderer(renderer);

        DateAxis xAxis = new DateAxis("Date");
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);
        plot.setDomainAxis(xAxis);

        // change the auto tick unit selection to integer units only...
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setRange(-40.0, 40.0);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

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
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        DeviationRendererDemo3 demo = new DeviationRendererDemo3(
                "JFreeChart : DeviationRendererDemo3.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
