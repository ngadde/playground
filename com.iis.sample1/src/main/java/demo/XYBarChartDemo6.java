/* --------------------
 * XYBarChartDemo6.java
 * --------------------
 * (C) Copyright 2006, 2009, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.xy.DefaultIntervalXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing an {@link XYBarRenderer}
 * plotting data from a {@link DefaultIntervalXYDataset}.
 */
public class XYBarChartDemo6 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYBarChartDemo6(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel);
    }

    private static JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYBarChart(
            "XYBarChartDemo6",
            "X",
            false,
            "Y",
            dataset,
            PlotOrientation.HORIZONTAL,
            false,
            false,
            false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setUseYInterval(true);
        plot.setRenderer(renderer);  // workaround to update axis range

        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return A dataset.
     */
    private static IntervalXYDataset createDataset() {
        DefaultIntervalXYDataset dataset = new DefaultIntervalXYDataset();
        double[] x = {1.0, 2.0, 3.0, 4.0};
        double[] startx = {0.9, 1.8, 2.7, 3.6};
        double[] endx = {1.1, 2.2, 3.3, 4.4};
        double[] y = {1.0, 2.0, 3.0, 4.0};
        double[] starty = {0.9, 1.8, 2.7, 3.6};
        double[] endy = {1.1, 2.2, 3.3, 4.4};
        double[][] data = new double[][] {x, startx, endx, y, starty, endy};
        dataset.addSeries("Series 1", data);
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
        XYBarChartDemo6 demo = new XYBarChartDemo6("JFreeChart : XYBarChartDemo6");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
