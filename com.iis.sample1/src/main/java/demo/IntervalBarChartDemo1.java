/* --------------------------
 * IntervalBarChartDemo1.java
 * --------------------------
 * (C) Copyright 2005-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Dimension;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.IntervalBarRenderer;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create an interval bar
 * chart.
 */
public class IntervalBarChartDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public IntervalBarChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Returns a sample dataset.
     *
     * @return The dataset.
     */
    private static IntervalCategoryDataset createDataset() {
        double[] starts_S1 = new double[] {0.1, 0.2, 0.3};
        double[] starts_S2 = new double[] {0.3, 0.4, 0.5};
        double[] ends_S1 = new double[] {0.5, 0.6, 0.7};
        double[] ends_S2 = new double[] {0.7, 0.8, 0.9};
        double[][] starts = new double[][] {starts_S1, starts_S2};
        double[][] ends = new double[][] {ends_S1, ends_S2};
        DefaultIntervalCategoryDataset dataset
                = new DefaultIntervalCategoryDataset(starts, ends);
        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(IntervalCategoryDataset dataset) {

        CategoryAxis domainAxis = new CategoryAxis("Category");
        NumberAxis rangeAxis = new NumberAxis("Percentage");
        rangeAxis.setNumberFormatOverride(new DecimalFormat("0.00%"));
        IntervalBarRenderer renderer = new IntervalBarRenderer();
        CategoryPlot plot = new CategoryPlot(dataset, domainAxis, rangeAxis,
                renderer);
        JFreeChart chart = new JFreeChart("IntervalBarChartDemo1", plot);
        plot.setDomainGridlinesVisible(true);
        plot.setRangePannable(true);
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
        JFrame demo = new IntervalBarChartDemo1(
                "JFreeChart: IntervalBarChartDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
