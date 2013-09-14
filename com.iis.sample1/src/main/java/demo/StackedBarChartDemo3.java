/* -------------------------
 * StackedBarChartDemo3.java
 * -------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 */

package demo;

import java.text.NumberFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a stacked bar chart
 * using data from a {@link CategoryDataset}.
 */
public class StackedBarChartDemo3 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public StackedBarChartDemo3(String title) {
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
        dataset.addValue(10.0, "Series 1", "Jan");
        dataset.addValue(12.0, "Series 1", "Feb");
        dataset.addValue(13.0, "Series 1", "Mar");
        dataset.addValue(4.0, "Series 2", "Jan");
        dataset.addValue(3.0, "Series 2", "Feb");
        dataset.addValue(2.0, "Series 2", "Mar");
        dataset.addValue(2.0, "Series 3", "Jan");
        dataset.addValue(3.0, "Series 3", "Feb");
        dataset.addValue(2.0, "Series 3", "Mar");
        dataset.addValue(2.0, "Series 4", "Jan");
        dataset.addValue(3.0, "Series 4", "Feb");
        dataset.addValue(4.0, "Series 4", "Mar");
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
            "Stacked Bar Chart Demo 3",  // chart title
            "Category",                  // domain axis label
            "Value",                     // range axis label
            dataset,                     // data
            PlotOrientation.VERTICAL,    // the plot orientation
            true,                        // legend
            false,                       // tooltips
            false                        // urls
        );
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryItemRenderer renderer = new ExtendedStackedBarRenderer();
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator());
        renderer.setBaseToolTipGenerator(
                new StandardCategoryToolTipGenerator());
        plot.setRenderer(renderer);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLowerMargin(0.15);
        rangeAxis.setUpperMargin(0.15);
        rangeAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());
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
        StackedBarChartDemo3 demo = new StackedBarChartDemo3(
                "Stacked Bar Chart Demo 3");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
