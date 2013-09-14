/* --------------------
 * BarChart3DDemo4.java
 * --------------------
 * (C) Copyright 2005-2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.text.NumberFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * This chart shows how to override the getItemPaint() method to set a color
 * that depends on the data value.
 */
public class BarChart3DDemo4 extends ApplicationFrame {

    /**
     * A custom renderer that returns a different color for each item in a
     * single series.
     */
    static class CustomBarRenderer3D extends BarRenderer3D {

        /**
         * Creates a new renderer.
         */
        public CustomBarRenderer3D() {
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
            CategoryDataset dataset = getPlot().getDataset();
            double value = dataset.getValue(row, column).doubleValue();
            if (value >= 0.70) {
                return Color.green;
            }
            else {
                return Color.red;
            }
        }
    }
    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public BarChart3DDemo4(String title) {
        super(title);
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
   private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(0.77, "Series 1", "Robert");
        dataset.addValue(0.93, "Series 1", "Mary");
        dataset.addValue(0.59, "Series 1", "John");
        dataset.addValue(0.75, "Series 1", "Ellen");
        dataset.addValue(0.63, "Series 1", "Jack");
        dataset.addValue(0.95, "Series 1", "David");
        dataset.addValue(0.71, "Series 1", "Mark");
        dataset.addValue(0.65, "Series 1", "Andy");
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
            "Student Grades",      // chart title
            "Students",               // domain axis label
            "Grade",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            false,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CustomBarRenderer3D renderer = new CustomBarRenderer3D();
        renderer.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        renderer.setItemLabelAnchorOffset(10.0);
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
        plot.setRenderer(renderer);

        ValueMarker marker = new ValueMarker(0.70, new Color(200, 200, 255),
                new BasicStroke(1.0f), new Color(200, 200, 255),
                new BasicStroke(1.0f), 1.0f);
//        marker.setLabel("Minimum grade to pass");
//        marker.setLabelPaint(Color.red);
//        marker.setLabelAnchor(RectangleAnchor.BOTTOM_LEFT);
//        marker.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        plot.addRangeMarker(marker, Layer.BACKGROUND);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setMaximumBarWidth(0.05);

        // couldn't get the label above to appear in front, so using an
        // annotation instead...
        CategoryTextAnnotation a = new CategoryTextAnnotation(
                "Minimum grade to pass", "Robert", 0.71);
        a.setCategoryAnchor(CategoryAnchor.START);
        a.setFont(new Font("SansSerif", Font.PLAIN, 12));
        a.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        plot.addAnnotation(a);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());
        rangeAxis.setUpperMargin(0.10);

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

        BarChart3DDemo4 demo = new BarChart3DDemo4(
                "JFreeChart: BarChart3DDemo4.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
