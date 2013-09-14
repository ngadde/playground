/* ------------------
 * BarChartDemo6.java
 * ------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * Another horizontal bar chart demo.  This time all the extras (titles,
 * legend and axes) are removed, to display just a single bar.
 */
public class BarChartDemo6 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public BarChartDemo6(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return A dataset.
     */
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(83.0, "First", "Factor 1");
        return dataset;
    }

    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
            null,  // chart title
            "Category",             // domain axis label
            "Score (%)",            // range axis label
            dataset,                // data
            PlotOrientation.HORIZONTAL,
            false,                  // include legend
            true,
            false
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.yellow);  // not seen
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setInsets(new RectangleInsets(0.0, 0.0, 0.0, 0.0));
        plot.setAxisOffset(RectangleInsets.ZERO_INSETS);
        plot.setRangeGridlinesVisible(false);
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLowerMargin(0.20);
        domainAxis.setUpperMargin(0.20);
        domainAxis.setVisible(false);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0.0, 100.0);
        rangeAxis.setVisible(false);
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
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        BarChartDemo6 demo = new BarChartDemo6(
                "JFreeChart: BarChartDemo6.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
