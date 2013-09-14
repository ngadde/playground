/* ----------------------------
 * MinMaxCategoryPlotDemo1.java
 * ----------------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.MinMaxCategoryRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a min/max category
 * plot.
 */
public class MinMaxCategoryPlotDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public MinMaxCategoryPlotDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset for the demo.
     *
     * @return A dataset.
     */
    public static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.0, "First", "C1");
        dataset.addValue(4.0, "First", "C2");
        dataset.addValue(3.0, "First", "C3");
        dataset.addValue(5.0, "First", "C4");
        dataset.addValue(5.0, "First", "C5");
        dataset.addValue(7.0, "First", "C6");
        dataset.addValue(7.0, "First", "C7");
        dataset.addValue(8.0, "First", "C8");
        dataset.addValue(5.0, "Second", "C1");
        dataset.addValue(7.0, "Second", "C2");
        dataset.addValue(6.0, "Second", "C3");
        dataset.addValue(8.0, "Second", "C4");
        dataset.addValue(4.0, "Second", "C5");
        dataset.addValue(4.0, "Second", "C6");
        dataset.addValue(2.0, "Second", "C7");
        dataset.addValue(1.0, "Second", "C8");
        dataset.addValue(4.0, "Third", "C1");
        dataset.addValue(3.0, "Third", "C2");
        dataset.addValue(2.0, "Third", "C3");
        dataset.addValue(3.0, "Third", "C4");
        dataset.addValue(6.0, "Third", "C5");
        dataset.addValue(3.0, "Third", "C6");
        dataset.addValue(4.0, "Third", "C7");
        dataset.addValue(3.0, "Third", "C8");
        return dataset;
    }

    /**
     * Creates the chart to display for the demo.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    public static JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createBarChart(
            "Min/Max Category Plot",  // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangePannable(true);
        MinMaxCategoryRenderer renderer = new MinMaxCategoryRenderer();
        renderer.setDrawLines(false);
        plot.setRenderer(renderer);
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
        MinMaxCategoryPlotDemo1 demo = new MinMaxCategoryPlotDemo1(
                "JFreeChart: MinMaxCategoryPlotDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
