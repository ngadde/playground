/* -------------------------
 * XYErrorRendererDemo1.java
 * -------------------------
 * (C) Copyright 2006, 2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYIntervalSeries;
import org.jfree.data.xy.XYIntervalSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing the use of the
 * {@link XYErrorRenderer} class.
 */
public class XYErrorRendererDemo1 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYErrorRendererDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private static JFreeChart createChart(IntervalXYDataset dataset) {
        NumberAxis xAxis = new NumberAxis("X");
        NumberAxis yAxis = new NumberAxis("Y");
        XYErrorRenderer renderer = new XYErrorRenderer();
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        JFreeChart chart = new JFreeChart("XYErrorRenderer Demo 1", plot);
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return A dataset.
     */
    private static IntervalXYDataset createDataset() {
        XYIntervalSeriesCollection dataset = new XYIntervalSeriesCollection();
        XYIntervalSeries s1 = new XYIntervalSeries("Series 1");
        s1.add(1.0, 0.5, 1.5, 10.0, 9.0, 11.0);
        s1.add(10.0, 8.7, 11.21, 6.1, 4.34, 7.54);
        s1.add(17.8, 16.0, 18.9, 4.5, 3.1, 5.8);
        XYIntervalSeries s2 = new XYIntervalSeries("Series 2");
        s2.add(3.0, 2.5, 3.5, 7.0, 6.0, 8.0);
        s2.add(13.0, 11.5, 14.5, 13.0, 11.5, 14.5);
        s2.add(24.0, 22.7, 25.21, 16.1, 14.34, 17.54);
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        return dataset;
    }

    /**
     * Creates a panel for the demo.
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
        XYErrorRendererDemo1 demo = new XYErrorRendererDemo1(
                "JFreeChart: XYErrorRendererDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
