/* --------------------------
 * OverlaidBarChartDemo2.java
 * --------------------------
 * (C) Copyright 2004-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LevelRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * Another demo of an overlaid bar chart.
 */
public class OverlaidBarChartDemo2 extends ApplicationFrame {

    /**
     * Default constructor.
     *
     * @param title  the frame title.
     */
    public OverlaidBarChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample chart.
     *
     * @return A sample chart.
     */
    private static JFreeChart createChart() {

        // create the first dataset...
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        dataset1.addValue(1.0, "S1", "Category 1");
        dataset1.addValue(4.0, "S1", "Category 2");
        dataset1.addValue(3.0, "S1", "Category 3");
        dataset1.addValue(5.0, "S1", "Category 4");
        dataset1.addValue(5.0, "S1", "Category 5");
        dataset1.addValue(5.0, "S2", "Category 1");
        dataset1.addValue(7.0, "S2", "Category 2");
        dataset1.addValue(6.0, "S2", "Category 3");
        dataset1.addValue(8.0, "S2", "Category 4");
        dataset1.addValue(4.0, "S2", "Category 5");

        // create the first plot...
        CategoryItemRenderer renderer = new BarRenderer();
        renderer.setBaseToolTipGenerator(
                new StandardCategoryToolTipGenerator());
        CategoryPlot plot = new CategoryPlot();
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);

        plot.setDomainAxis(new CategoryAxis("Category"));
        plot.setRangeAxis(new NumberAxis("Value"));

        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeZeroBaselineVisible(true);
        plot.setRangePannable(true);

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        dataset2.addValue(6.0, "Prior 1", "Category 1");
        dataset2.addValue(7.0, "Prior 1", "Category 2");
        dataset2.addValue(2.0, "Prior 1", "Category 3");
        dataset2.addValue(6.0, "Prior 1", "Category 4");
        dataset2.addValue(6.0, "Prior 1", "Category 5");
        dataset2.addValue(4.0, "Prior 2", "Category 1");
        dataset2.addValue(2.0, "Prior 2", "Category 2");
        dataset2.addValue(1.0, "Prior 2", "Category 3");
        dataset2.addValue(3.0, "Prior 2", "Category 4");
        dataset2.addValue(2.0, "Prior 2", "Category 5");

        CategoryItemRenderer renderer2 = new LevelRenderer();
        plot.setDataset(1, dataset2);
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("OverlaidBarChartDemo2");
        ChartUtilities.applyCurrentTheme(chart);

        // applies these attributes *after* the theme...
        renderer2.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer2.setSeriesStroke(1, new BasicStroke(2.0f));

        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart();
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
        OverlaidBarChartDemo2 demo = new OverlaidBarChartDemo2(
            "JFreeChart: OverlaidBarChartDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
