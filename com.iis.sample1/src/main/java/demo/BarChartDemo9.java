/* ------------------
 * BarChartDemo9.java
 * ------------------
 * (C) Copyright 2005-2008, by Object Refinery Limited.
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
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.StandardGradientPaintTransformer;

/**
 * A bar chart that uses a custom renderer to display different colors within a
 * single series.  The colors use GradientPaint.
 */
public class BarChartDemo9 extends ApplicationFrame {

    /**
     * A custom renderer that returns a different color for each item in a
     * single series.
     */
    static class CustomBarRenderer extends BarRenderer {

        /** The colors. */
        private Paint[] colors;

        /**
         * Creates a new renderer.
         *
         * @param colors  the colors.
         */
        public CustomBarRenderer(Paint[] colors) {
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
    public BarChartDemo9(String title) {
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
        dataset.addValue(410.0, "Network Traffic", "Monday");
        dataset.addValue(680.0, "Network Traffic", "Tuesday");
        dataset.addValue(530.0, "Network Traffic", "Wednesday");
        dataset.addValue(570.0, "Network Traffic", "Thursday");
        dataset.addValue(330.0, "Network Traffic", "Friday");
        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return a sample chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createBarChart(
            "Bar Chart Demo 9",       // chart title
            null,                     // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // the plot orientation
            false,                    // include legend
            true,
            false
        );

        TextTitle title = chart.getTitle();
        title.setBorder(0, 0, 1, 0);
        title.setBackgroundPaint(new GradientPaint(0f, 0f, Color.red, 350f,
                0f, Color.white, true));
        title.setExpandToFitSpace(true);

        chart.setBackgroundPaint(new GradientPaint(0f, 0f, Color.yellow, 350f,
                0f, Color.white, true));

        // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setNoDataMessage("NO DATA!");
        plot.setBackgroundPaint(null);
        plot.setInsets(new RectangleInsets(10, 5, 5, 5));
        plot.setOutlinePaint(Color.black);
        plot.setRangeGridlinePaint(Color.gray);
        plot.setRangeGridlineStroke(new BasicStroke(1.0f));
        Paint[] colors = createPaint();
        CustomBarRenderer renderer = new CustomBarRenderer(colors);
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDrawBarOutline(true);
        renderer.setGradientPaintTransformer(
                new StandardGradientPaintTransformer(
                        GradientPaintTransformType.CENTER_HORIZONTAL));
        plot.setRenderer(renderer);

        // change the margin at the top of the range axis...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setRange(0.0, 800.0);
        rangeAxis.setTickMarkPaint(Color.black);

        return chart;

    }

    /**
     * Returns an array of paint objects that will be used for the bar colors.
     *
     * @return An array of paint objects.
     */
    private static Paint[] createPaint() {
        Paint[] colors = new Paint[5];
        colors[0] = new GradientPaint(0f, 0f, Color.red, 0f, 0f, Color.white);
        colors[1] = new GradientPaint(0f, 0f, Color.green, 0f, 0f, Color.white);
        colors[2] = new GradientPaint(0f, 0f, Color.blue, 0f, 0f, Color.white);
        colors[3] = new GradientPaint(0f, 0f, Color.orange, 0f, 0f, Color.white);
        colors[4] = new GradientPaint(0f, 0f, Color.magenta, 0f, 0f, Color.white);
        return colors;
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
        BarChartDemo9 demo = new BarChartDemo9(
                "JFreeChart: BarChartDemo9.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
