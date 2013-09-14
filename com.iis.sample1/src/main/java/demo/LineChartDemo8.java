/* -------------------
 * LineChartDemo8.java
 * -------------------
 * (C) Copyright 2006, 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.ShapeUtilities;

/**
 * In this line chart, a SymbolAxis is used for the range axis.
 */
public class LineChartDemo8 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public LineChartDemo8(String title) {
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
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(0, "Series 1", "Category 1");
        dataset.addValue(2, "Series 1", "Category 2");
        dataset.addValue(1, "Series 1", "Category 3");
        dataset.addValue(3, "Series 1", "Category 4");
        dataset.addValue(5, "Series 1", "Category 5");
        dataset.addValue(2, "Series 2", "Category 1");
        dataset.addValue(4, "Series 2", "Category 2");
        dataset.addValue(4, "Series 2", "Category 3");
        dataset.addValue(5, "Series 2", "Category 4");
        dataset.addValue(2, "Series 2", "Category 5");
        dataset.addValue(1, "Series 3", "Category 1");
        dataset.addValue(3, "Series 3", "Category 2");
        dataset.addValue(5, "Series 3", "Category 3");
        dataset.addValue(2, "Series 3", "Category 4");
        dataset.addValue(0, "Series 3", "Category 5");
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
            "Line Chart Demo 8",             // chart title
            "Category",                      // domain axis label
            "Count",                         // range axis label
            dataset,                         // data
            PlotOrientation.VERTICAL,        // orientation
            true,                            // include legend
            true,                            // tooltips
            false                            // urls
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // customise the range axis...
        SymbolAxis rangeAxis = new SymbolAxis("Group", new String[] {"A", "B",
                "C", "D", "E", "F"});
        plot.setRangeAxis(rangeAxis);

        // make sure the current theme is applied to the axis just added
        ChartUtilities.applyCurrentTheme(chart);

        // customise the renderer...
        LineAndShapeRenderer renderer
                = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesShapesVisible(2, true);
        renderer.setSeriesLinesVisible(2, false);
        renderer.setSeriesShape(2, ShapeUtilities.createDiamond(4.0f));
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        renderer.setBaseFillPaint(Color.white);

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
        LineChartDemo8 demo = new LineChartDemo8(
                "JFreeChart: LineChartDemo8.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
