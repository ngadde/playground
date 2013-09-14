/* ----------------------------
 * BoxAndWhiskerChartDemo1.java
 * ----------------------------
 * (C) Copyright 2005-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a box-and-whisker
 * chart (on a CategoryPlot).
 */
public class BoxAndWhiskerChartDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public BoxAndWhiskerChartDemo1(String title) {
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
    private static BoxAndWhiskerCategoryDataset createDataset() {
        int SERIES_COUNT = 3;
        int CATEGORY_COUNT = 5;
        int VALUE_COUNT = 20;
        DefaultBoxAndWhiskerCategoryDataset result
                = new DefaultBoxAndWhiskerCategoryDataset();
        for (int s = 0; s < SERIES_COUNT; s++) {
            for (int c = 0; c < CATEGORY_COUNT; c++) {
                List values = createValueList(0, 20.0, VALUE_COUNT);
                result.add(values, "Series " + s, "Category " + c);
            }
        }
        return result;
    }

    private static List createValueList(double lowerBound, double upperBound,
                                        int count) {
        List result = new java.util.ArrayList();
        for (int i = 0; i < count; i++) {
            double v = lowerBound + (Math.random() * (upperBound - lowerBound));
            result.add(new Double(v));
        }
        return result;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(
            BoxAndWhiskerCategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createBoxAndWhiskerChart(
                "Box and Whisker Chart Demo 1", "Category", "Value", dataset,
                true);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainGridlinesVisible(true);
        plot.setRangePannable(true);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
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

        BoxAndWhiskerChartDemo1 demo = new BoxAndWhiskerChartDemo1(
                "JFreeChart: BoxAndWhiskerChartDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
