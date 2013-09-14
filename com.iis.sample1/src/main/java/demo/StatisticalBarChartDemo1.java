/* -----------------------------
 * StatisticalBarChartDemo1.java
 * -----------------------------
 * (C) Copyright 2005-2007, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StatisticalBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * A simple demonstration application showing how to create a "statistical"
 * bar chart using data from a {@link CategoryDataset}.
 */
public class StatisticalBarChartDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public StatisticalBarChartDemo1(String title) {
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

        DefaultStatisticalCategoryDataset dataset
            = new DefaultStatisticalCategoryDataset();
        dataset.add(10.0, 2.4, "Row 1", "Column 1");
        dataset.add(15.0, 4.4, "Row 1", "Column 2");
        dataset.add(13.0, 2.1, "Row 1", "Column 3");
        dataset.add(7.0, 1.3, "Row 1", "Column 4");
        dataset.add(22.0, 2.4, "Row 2", "Column 1");
        dataset.add(18.0, 4.4, "Row 2", "Column 2");
        dataset.add(28.0, 2.1, "Row 2", "Column 3");
        dataset.add(17.0, 1.3, "Row 2", "Column 4");
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
        JFreeChart chart = ChartFactory.createLineChart(
            "Statistical Bar Chart Demo 1", // chart title
            "Type",                         // domain axis label
            "Value",                        // range axis label
            dataset,                        // data
            PlotOrientation.VERTICAL,       // orientation
            true,                           // include legend
            true,                           // tooltips
            false                           // urls
        );


        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // customise the range axis...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(false);

        // customise the renderer...
        StatisticalBarRenderer renderer = new StatisticalBarRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setErrorIndicatorPaint(Color.black);
        renderer.setIncludeBaseInRange(false);
        plot.setRenderer(renderer);

        // ensure the current theme is applied to the renderer just added
        ChartUtilities.applyCurrentTheme(chart);

        renderer.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelPaint(Color.yellow);
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.INSIDE6, TextAnchor.BOTTOM_CENTER));

        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green,
                0.0f, 0.0f, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
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
        StatisticalBarChartDemo1 demo = new StatisticalBarChartDemo1(
                "Statistical Bar Chart Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
