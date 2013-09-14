/* -------------------------
 * StackedBarChartDemo7.java
 * -------------------------
 * (C) Copyright 2006, by Object Refinery Limited.
 *
 */

package demo;

import java.text.NumberFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A stacked bar chart where the values are displayed as percentages adding
 * to 100%.
 */
public class StackedBarChartDemo7 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public StackedBarChartDemo7(String title) {
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
        dataset.addValue(111.3, "Series 2", "Category 3");
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
            "Stacked Bar Chart Demo 7",  // chart title
            "Category",                  // domain axis label
            "Value",                     // range axis label
            dataset,                     // data
            PlotOrientation.VERTICAL,    // the plot orientation
            true,                        // legend
            true,                        // tooltips
            false                        // urls
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());
        StackedBarRenderer renderer = (StackedBarRenderer) plot.getRenderer();
        renderer.setRenderAsPercentages(true);
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

        StackedBarChartDemo7 demo = new StackedBarChartDemo7(
                "Stacked Bar Chart Demo 7");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
