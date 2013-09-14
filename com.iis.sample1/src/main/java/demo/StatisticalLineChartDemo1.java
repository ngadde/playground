/* ------------------------------
 * StatisticalLineChartDemo1.java
 * ------------------------------
 * (C) Copyright 2005-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StatisticalLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a "statistical"
 * line chart using data from a {@link CategoryDataset}.
 */
public class StatisticalLineChartDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public StatisticalLineChartDemo1(String title) {
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
            "Statistical Line Chart Demo 1",   // chart title
            "Type",                    // domain axis label
            "Value",                   // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );


        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangePannable(true);
        
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setUpperMargin(0.0);
        domainAxis.setLowerMargin(0.0);

        // customise the range axis...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);

        // customise the renderer...
        StatisticalLineAndShapeRenderer renderer
                = new StatisticalLineAndShapeRenderer(true, false);
        renderer.setUseSeriesOffset(true);
        plot.setRenderer(renderer);

        // OPTIONAL CUSTOMISATION COMPLETED.
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
        StatisticalLineChartDemo1 demo = new StatisticalLineChartDemo1(
                "JFreeChart: StatisticalLineChartDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
