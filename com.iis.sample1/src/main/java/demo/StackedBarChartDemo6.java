/* -------------------------
 * StackedBarChartDemo6.java
 * -------------------------
 * (C) Copyright 2005, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A stacked bar chart that uses a {@link DateAxis} for the y-axis.
 */
public class StackedBarChartDemo6 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public StackedBarChartDemo6(String title) {
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
        long day = 24 * 60 * 60 * 1000L;
        dataset.addValue(3 * day, "Series 1", "Category 1");
        dataset.addValue(1 * day, "Series 2", "Category 1");
        dataset.addValue(2 * day, "Series 3", "Category 1");
        dataset.addValue(4 * day, "Series 1", "Category 2");
        dataset.addValue(5 * day, "Series 2", "Category 2");
        dataset.addValue(1 * day, "Series 3", "Category 2");
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
            "Stacked Bar Chart Demo 6",  // chart title
            "Category",                  // domain axis label
            "Value",                     // range axis label
            dataset,                     // data
            PlotOrientation.HORIZONTAL,  // the plot orientation
            true,                        // legend
            true,                        // tooltips
            false                        // urls
        );
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        StackedBarRenderer renderer = (StackedBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        long millis = System.currentTimeMillis();
        renderer.setBase(millis);
        DateAxis rangeAxis = new DateAxis("Date");
        rangeAxis.setLowerMargin(0.0);
        plot.setRangeAxis(rangeAxis);

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

        StackedBarChartDemo6 demo = new StackedBarChartDemo6(
                "Stacked Bar Chart Demo 6");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
