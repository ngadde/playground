/* -------------------------
 * StackedBarChartDemo1.java
 * -------------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a stacked bar chart
 * using data from a {@link CategoryDataset}.
 */
public class StackedBarChartDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public StackedBarChartDemo1(String title) {
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
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(32.4, "Series 1", "Category 1");
        dataset.addValue(17.8, "Series 2", "Category 1");
        dataset.addValue(27.7, "Series 3", "Category 1");
        dataset.addValue(43.2, "Series 1", "Category 2");
        dataset.addValue(15.6, "Series 2", "Category 2");
        dataset.addValue(18.3, "Series 3", "Category 2");
        dataset.addValue(23.0, "Series 1", "Category 3");
        dataset.addValue(11.3, "Series 2", "Category 3");
        dataset.addValue(25.5, "Series 3", "Category 3");
        dataset.addValue(13.0, "Series 1", "Category 4");
        dataset.addValue(11.8, "Series 2", "Category 4");
        dataset.addValue(29.5, "Series 3", "Category 4");
        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset for the chart.
     *
     * @return a sample chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createStackedBarChart(
            "Stacked Bar Chart Demo 1",  // chart title
            "Category",                  // domain axis label
            "Value",                     // range axis label
            dataset,                     // data
            PlotOrientation.VERTICAL,    // the plot orientation
            true,                        // legend
            true,                        // tooltips
            false                        // urls
        );
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        StackedBarRenderer renderer = (StackedBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator());
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
        StackedBarChartDemo1 demo = new StackedBarChartDemo1(
                "Stacked Bar Chart Demo 1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
