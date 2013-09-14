/* -----------------------
 * CylinderChartDemo2.java
 * -----------------------
 * (C) Copyright 2007-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.StandardGradientPaintTransformer;

/**
 * A simple demonstration application showing how to create a cylinder
 * chart using data from a {@link CategoryDataset}.
 */
public class CylinderChartDemo2 extends ApplicationFrame {

    /**
     * A custom renderer that returns a different color for each item in a
     * single series.
     */
    static class CustomCylinderRenderer extends CylinderRenderer {

        /** The colors. */
        private Paint[] colors;

        /**
         * Creates a new renderer.
         *
         * @param colors  the colors.
         */
        public CustomCylinderRenderer(Paint[] colors) {
            this.colors = colors;
        }

        /**
         * Returns the paint for an item.  Overrides the default behaviour
         * inherited from AbstractSeriesRenderer.
         *
         * @param row  the series.
         * @param column  the category.
         *
         * @return The item color.
         */
        public Paint getItemPaint(int row, int column) {
            return this.colors[column % this.colors.length];
        }
    }

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public CylinderChartDemo2(String title) {
        super(title);
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
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
        dataset.addValue(4.0, "S1", "Monday");
        dataset.addValue(5.0, "S1", "Tuesday");
        dataset.addValue(-7.0, "S1", "Wednesday");
        dataset.addValue(6.0, "S1", "Thursday");
        dataset.addValue(4.0, "S1", "Friday");
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
            "Cylinder Chart Demo 2",  // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.HORIZONTAL, // orientation
            false,                    // include legend
            true,                     // tooltips
            false                     // urls
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangePannable(true);
        Paint[] colors = createPaint();
        CustomCylinderRenderer renderer = new CustomCylinderRenderer(colors);
        renderer.setGradientPaintTransformer(
                new StandardGradientPaintTransformer(
                        GradientPaintTransformType.CENTER_VERTICAL));
        renderer.setBaseOutlinePaint(Color.gray);
        renderer.setBaseOutlineStroke(new BasicStroke(0.3f));
        renderer.setBaseToolTipGenerator(
                new StandardCategoryToolTipGenerator());
        plot.setRenderer(renderer);
        ChartUtilities.applyCurrentTheme(chart);
        return chart;

    }

    /**
     * Returns an array of paint objects that will be used for the bar colors.
     *
     * @return An array of paint objects.
     */
    private static Paint[] createPaint() {
        Paint[] colors = new Paint[5];
        colors[0] = new GradientPaint(0f, 0f, Color.white, 0f, 0f, Color.red);
        colors[1] = new GradientPaint(0f, 0f, Color.white, 0f, 0f, Color.green);
        colors[2] = new GradientPaint(0f, 0f, Color.white, 0f, 0f, Color.blue);
        colors[3] = new GradientPaint(0f, 0f, Color.white, 0f, 0f, Color.orange);
        colors[4] = new GradientPaint(0f, 0f, Color.white, 0f, 0f, Color.magenta);
        return colors;
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
        CylinderChartDemo2 demo = new CylinderChartDemo2(
                "JFreeChart: CylinderChartDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
