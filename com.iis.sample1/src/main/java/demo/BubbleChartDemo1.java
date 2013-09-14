/* ---------------------
 * BubbleChartDemo1.java
 * ---------------------
 * (C) Copyright 2003-2009, by Object Refinery Limited.
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A bubble chart demo.
 */
public class BubbleChartDemo1 extends ApplicationFrame {

    /**
     * A demonstration application showing a bubble chart.
     *
     * @param title  the frame title.
     */
    public BubbleChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(XYZDataset dataset) {
        JFreeChart chart = ChartFactory.createBubbleChart(
                "Bubble Chart Demo 1", "X", "Y", dataset,
                PlotOrientation.HORIZONTAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setForegroundAlpha(0.65f);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.blue);

        // increase the margins to account for the fact that the auto-range
        // doesn't take into account the bubble size...
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setLowerMargin(0.15);
        domainAxis.setUpperMargin(0.15);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setLowerMargin(0.15);
        rangeAxis.setUpperMargin(0.15);
        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    public static XYZDataset createDataset() {
        DefaultXYZDataset dataset = new DefaultXYZDataset();
        double[] x = {2.1, 2.3, 2.3, 2.2, 2.2, 1.8, 1.8, 1.9, 2.3, 3.8};
        double[] y = {14.1, 11.1, 10.0, 8.8, 8.7, 8.4, 5.4, 4.1, 4.1, 25};
        double[] z = {2.4, 2.7, 2.7, 2.2, 2.2, 2.2, 2.1, 2.2, 1.6, 4};
        double[][] series = new double[][] { x, y, z };
        dataset.addSeries("Series 1", series);
        return dataset;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
        return chartPanel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        BubbleChartDemo1 demo = new BubbleChartDemo1(
                "JFreeChart: BubbleChartDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
