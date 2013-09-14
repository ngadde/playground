/* ---------------------------
 * DeviationRendererDemo1.java
 * ---------------------------
 * (C) Copyright 2007-2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo of the {@link DeviationRenderer} class.
 */
public class DeviationRendererDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public DeviationRendererDemo1(String title) {
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

        YIntervalSeries series1 = new YIntervalSeries("Series 1");
        YIntervalSeries series2 = new YIntervalSeries("Series 2");

        double y1 = 100.0;
        double y2 = 100.0;
        for (int i = 0; i <= 100; i++) {
            y1 = y1 + Math.random() - 0.48;
            double dev1 = (0.05 * i);
            if (i == 50) {
                series1.add(i, y1, y1 - dev1, y1 + dev1);
            }
            else {
                series1.add(i, y1, y1 - dev1, y1 + dev1);
            }

            y2 = y2 + Math.random() - 0.50;
            double dev2 = (0.07 * i);
            series2.add(i, y2, y2 - dev2, y2 + dev2);
        }


        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

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
            "DeviationRenderer - Demo 1",      // chart title
            "X",                      // x axis label
            "Y",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        // get a reference to the plot for further customisation...
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        DeviationRenderer renderer = new DeviationRenderer(true, false);
        renderer.setSeriesStroke(0, new BasicStroke(3.0f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        renderer.setSeriesStroke(0, new BasicStroke(3.0f,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        renderer.setSeriesFillPaint(0, new Color(255, 200, 200));
        renderer.setSeriesFillPaint(1, new Color(200, 200, 255));
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(false);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

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

        DeviationRendererDemo1 demo = new DeviationRendererDemo1(
                "JFreeChart : DeviationRendererDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
