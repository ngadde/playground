/* -------------------
 * LineChart3DDemo1.java
 * -------------------
 * (C) Copyright 2006, 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a line chart with
 * a pseudo 3D effect, using data from a {@link CategoryDataset}.
 */
public class LineChart3DDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public LineChart3DDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(143.2, "S1", "C1");
        dataset.addValue(120.2, "S1", "C2");
        dataset.addValue(135.0, "S1", "C3");
        dataset.addValue(115.0, "S1", "C4");
        dataset.addValue(98.7, "S2", "C1");
        dataset.addValue(63.2, "S2", "C2");
        dataset.addValue(71.4, "S2", "C3");
        dataset.addValue(55.0, "S2", "C4");
        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  a dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createLineChart3D(
            "Line Chart 3D Demo 1",   // chart title
            null,                       // domain axis label
            "Class Count",                   // range axis label
            dataset,                         // data
            PlotOrientation.VERTICAL,        // orientation
            false,                           // include legend
            true,                            // tooltips
            false                            // urls
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // customise the range axis...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(false);
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
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        LineChart3DDemo1 demo = new LineChart3DDemo1(
                "JFreeChart: LineChart3DDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
