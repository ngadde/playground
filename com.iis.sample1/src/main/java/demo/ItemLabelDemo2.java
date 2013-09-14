/* -------------------
 * ItemLabelDemo2.java
 * -------------------
 * (C) Copyright 2005-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Dimension;
import java.text.NumberFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.AbstractCategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demo showing a label generator that displays labels that include
 * a percentage calculation.
 */
public class ItemLabelDemo2 extends ApplicationFrame {

    /**
     * A custom label generator.
     */
    static class LabelGenerator extends AbstractCategoryItemLabelGenerator
                                implements CategoryItemLabelGenerator {

        /**
         * The index of the category on which to base the percentage
         * (null = use series total).
         */
        private Integer category;

        /** A percent formatter. */
        private NumberFormat formatter = NumberFormat.getPercentInstance();

        /**
         * Creates a new label generator that displays the item value and a
         * percentage relative to the value in the same series for the
         * specified category.
         *
         * @param category  the category index (zero-based).
         */
        public LabelGenerator(int category) {
            this(new Integer(category));
        }

        /**
         * Creates a new label generator that displays the item value and
         * a percentage relative to the value in the same series for the
         * specified category.  If the category index is <code>null</code>,
         * the total of all items in the series is used.
         *
         * @param category  the category index (<code>null</code> permitted).
         */
        public LabelGenerator(Integer category) {
            super("", NumberFormat.getInstance());
            this.category = category;
        }

        /**
         * Generates a label for the specified item. The label is typically
         * a formatted version of the data value, but any text can be used.
         *
         * @param dataset  the dataset (<code>null</code> not permitted).
         * @param series  the series index (zero-based).
         * @param category  the category index (zero-based).
         *
         * @return the label (possibly <code>null</code>).
         */
        public String generateLabel(CategoryDataset dataset,
                                    int series,
                                    int category) {

            String result = null;
            double base = 0.0;
            if (this.category != null) {
                Number b = dataset.getValue(series, this.category.intValue());
                base = b.doubleValue();
            }
            else {
                base = calculateSeriesTotal(dataset, series);
            }
            Number value = dataset.getValue(series, category);
            if (value != null) {
                double v = value.doubleValue();
                // you could apply some formatting here
                result = value.toString()
                       + " (" + this.formatter.format(v / base) + ")";
            }
            return result;

        }

        /**
         * Calculates a series total.
         *
         * @param dataset  the dataset.
         * @param series  the series index.
         *
         * @return The total.
         */
        private double calculateSeriesTotal(CategoryDataset dataset, int series) {
            double result = 0.0;
            for (int i = 0; i < dataset.getColumnCount(); i++) {
                Number value = dataset.getValue(series, i);
                if (value != null) {
                    result = result + value.doubleValue();
                }
            }
            return result;
        }

    }

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public ItemLabelDemo2(String title) {

        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * Returns a sample dataset.
     *
     * @return the dataset.
     */
    private static CategoryDataset createDataset() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100.0, "S1", "C1");
        dataset.addValue(44.3, "S1", "C2");
        dataset.addValue(93.0, "S1", "C3");
        dataset.addValue(80.0, "S2", "C1");
        dataset.addValue(75.1, "S2", "C2");
        dataset.addValue(15.1, "S2", "C3");
        return dataset;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return the chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
            "Item Label Demo 2",        // chart title
            "Category",                 // domain axis label
            "Value",                    // range axis label
            dataset,                    // data
            PlotOrientation.HORIZONTAL, // orientation
            true,                       // include legend
            true,                       // tooltips?
            false                       // URLs?
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        plot.setRangePannable(true);
        plot.setRangeZeroBaselineVisible(true);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setUpperMargin(0.25);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBaseItemLabelsVisible(true);
        renderer.setItemLabelAnchorOffset(7.0);
        // use one or the other of the following lines to see the
        // different modes for the label generator...
        renderer.setBaseItemLabelGenerator(new LabelGenerator(null));
        //renderer.setLabelGenerator(new LabelGenerator(0));

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

        ItemLabelDemo2 demo = new ItemLabelDemo2(
                "JFreeChart: ItemLabelDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
