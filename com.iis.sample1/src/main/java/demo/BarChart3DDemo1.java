/* --------------------
 * BarChart3DDemo1.java
 * --------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a vertical 3D bar
 * chart using data from a {@link CategoryDataset}.
 */
public class BarChart3DDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public BarChart3DDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10.0, "Series 1", "Category 1");
        dataset.addValue(4.0, "Series 1", "Category 2");
        dataset.addValue(15.0, "Series 1", "Category 3");
        dataset.addValue(14.0, "Series 1", "Category 4");
        dataset.addValue(-5.0, "Series 2", "Category 1");
        dataset.addValue(-7.0, "Series 2", "Category 2");
        dataset.addValue(14.0, "Series 2", "Category 3");
        dataset.addValue(-3.0, "Series 2", "Category 4");
        dataset.addValue(6.0, "Series 3", "Category 1");
        dataset.addValue(17.0, "Series 3", "Category 2");
        dataset.addValue(-12.0, "Series 3", "Category 3");
        dataset.addValue(7.0, "Series 3", "Category 4");
        dataset.addValue(7.0, "Series 4", "Category 1");
        dataset.addValue(15.0, "Series 4", "Category 2");
        dataset.addValue(11.0, "Series 4", "Category 3");
        dataset.addValue(0.0, "Series 4", "Category 4");
        dataset.addValue(-8.0, "Series 5", "Category 1");
        dataset.addValue(-6.0, "Series 5", "Category 2");
        dataset.addValue(10.0, "Series 5", "Category 3");
        dataset.addValue(-9.0, "Series 5", "Category 4");
        dataset.addValue(9.0, "Series 6", "Category 1");
        dataset.addValue(8.0, "Series 6", "Category 2");
        dataset.addValue(0.0, "Series 6", "Category 3");
        dataset.addValue(6.0, "Series 6", "Category 4");
        dataset.addValue(-10.0, "Series 7", "Category 1");
        dataset.addValue(9.0, "Series 7", "Category 2");
        dataset.addValue(7.0, "Series 7", "Category 3");
        dataset.addValue(7.0, "Series 7", "Category 4");
        dataset.addValue(11.0, "Series 8", "Category 1");
        dataset.addValue(13.0, "Series 8", "Category 2");
        dataset.addValue(9.0, "Series 8", "Category 3");
        dataset.addValue(9.0, "Series 8", "Category 4");
        dataset.addValue(-3.0, "Series 9", "Category 1");
        dataset.addValue(7.0, "Series 9", "Category 2");
        dataset.addValue(11.0, "Series 9", "Category 3");
        dataset.addValue(-10.0, "Series 9", "Category 4");
        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createBarChart3D(
            "3D Bar Chart Demo",      // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips
            false);                   // urls

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setOutlineVisible(false);
        plot.setDomainGridlinesVisible(true);
        CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(
                        Math.PI / 8.0));
        axis.setCategoryMargin(0.0);
        BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        return chart;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        chartPanel.setMouseWheelEnabled(true);
        return chartPanel;
    }

    /**
     * Starting point for the demonstration application when it is run as
     * a stand-alone application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        BarChart3DDemo1 demo = new BarChart3DDemo1(
                "JFreeChart: BarChart3DDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
