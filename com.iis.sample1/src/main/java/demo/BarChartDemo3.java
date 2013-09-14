/* ------------------
 * BarChartDemo3.java
 * ------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Paint;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * A bar chart that uses a custom renderer to display different colors within a
 * series.  No legend is displayed because there is only one series but the
 * colors are not consistent.  Also, a domain marker is used to highlight one
 * category.
 */
public class BarChartDemo3 extends ApplicationFrame {

    /**
     * A custom renderer that returns a different color for each item in a
     * single series.
     */
    static class CustomRenderer extends BarRenderer {

        /** The colors. */
        private Paint[] colors;

        /**
         * Creates a new renderer.
         *
         * @param colors  the colors.
         */
        public CustomRenderer(Paint[] colors) {
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
    public BarChartDemo3(String title) {
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
        double[][] data = new double[][] {{4.0, 3.0, -2.0, 3.0, 6.0}};
        return DatasetUtilities.createCategoryDataset("Series ", "Category ",
            data);
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
            "Bar Chart Demo 3",       // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // the plot orientation
            false,                    // include legend
            true,
            false
        );

        // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setNoDataMessage("NO DATA!");
        plot.setRangePannable(true);
        Paint[] customColours = new Paint[] {
                new Color(196, 215, 216), new Color(78, 137, 139),
                new Color(138, 177, 178), new Color(19, 97, 100)};
        CategoryItemRenderer renderer = new CustomRenderer(customColours);
        renderer.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER,
                TextAnchor.CENTER, TextAnchor.CENTER, 0.0);
        renderer.setBasePositiveItemLabelPosition(p);
        plot.setRenderer(renderer);
        CategoryMarker marker = new CategoryMarker("Category 3");
        marker.setLabel("Special");
        marker.setPaint(new Color(0xDD, 0xFF, 0xDD, 0x80));
        marker.setAlpha(0.5f);
        marker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        marker.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        marker.setLabelOffsetType(LengthAdjustmentType.CONTRACT);
        plot.addDomainMarker(marker, Layer.BACKGROUND);

        // change the margin at the top of the range axis...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLowerMargin(0.15);
        rangeAxis.setUpperMargin(0.15);

        NumberAxis rangeAxis2 = new NumberAxis(null);
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis2.setLowerMargin(0.15);
        rangeAxis2.setUpperMargin(0.15);
        plot.setRangeAxis(1, rangeAxis2);

        CategoryAxis domainAxis2 = new CategoryAxis(null);
        plot.setDomainAxis(1, domainAxis2);

        List axisIndices = Arrays.asList(new Integer[] {new Integer(0),
                new Integer(1)});
        plot.mapDatasetToDomainAxes(0, axisIndices);
        plot.mapDatasetToRangeAxes(0, axisIndices);
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
        BarChartDemo3 demo = new BarChartDemo3(
                "JFreeChart: BarChartDemo3.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
