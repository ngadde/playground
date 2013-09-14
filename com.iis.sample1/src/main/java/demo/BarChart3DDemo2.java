/* --------------------
 * BarChart3DDemo2.java
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
import org.jfree.chart.axis.CategoryLabelPosition;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.CategoryLabelWidthType;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.text.TextBlockAnchor;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * A simple demonstration application showing how to create a horizontal 3D bar
 * chart using data from a {@link CategoryDataset}.
 */
public class BarChart3DDemo2 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public BarChart3DDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(23.0, "Series 1", "London");
        dataset.addValue(14.0, "Series 1", "New York");
        dataset.addValue(14.0, "Series 1", "Istanbul");
        dataset.addValue(14.0, "Series 1", "Cairo");
        dataset.addValue(13.0, "Series 2", "London");
        dataset.addValue(19.0, "Series 2", "New York");
        dataset.addValue(19.0, "Series 2", "Istanbul");
        dataset.addValue(19.0, "Series 2", "Cairo");
        dataset.addValue(7.0, "Series 3", "London");
        dataset.addValue(9.0, "Series 3", "New York");
        dataset.addValue(9.0, "Series 3", "Istanbul");
        dataset.addValue(9.0, "Series 3", "Cairo");
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
            "3D Bar Chart Demo 2",       // chart title
            "Category",                  // domain axis label
            "Value",                     // range axis label
            dataset,                     // data
            PlotOrientation.HORIZONTAL,  // orientation
            true,                        // include legend
            true,                        // tooltips
            false);                      // urls


        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setForegroundAlpha(1.0f);
        plot.setRangePannable(true);

        // left align the category labels...
        CategoryAxis axis = plot.getDomainAxis();
        CategoryLabelPositions p = axis.getCategoryLabelPositions();

        CategoryLabelPosition left = new CategoryLabelPosition(
                RectangleAnchor.LEFT, TextBlockAnchor.CENTER_LEFT,
                TextAnchor.CENTER_LEFT, 0.0,
                CategoryLabelWidthType.RANGE, 0.30f);
        axis.setCategoryLabelPositions(
                CategoryLabelPositions.replaceLeftPosition(p, left));

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
        BarChart3DDemo2 demo = new BarChart3DDemo2(
                "JFreeChart: BarChart3DDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
