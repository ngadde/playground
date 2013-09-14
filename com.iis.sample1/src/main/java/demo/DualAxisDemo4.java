/* ------------------
 * DualAxisDemo4.java
 * ------------------
 * (C) Copyright 2003-2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a dual axis chart
 * based on data from two {@link CategoryDataset} instances.
 */
public class DualAxisDemo4 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public DualAxisDemo4(String title) {
        super(title);
        JPanel panel = createDemoPanel();
        panel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(panel);
    }

    private static JFreeChart createChart() {
        CategoryDataset dataset1 = createDataset1();

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart3D(
            "Dual Axis Chart",  // chart title
            "Category",         // domain axis label
            "Value",            // range axis label
            dataset1,           // data
            PlotOrientation.VERTICAL,
            true,               // include legend
            true,
            false);

        // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        plot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
        CategoryItemRenderer renderer1 = plot.getRenderer();
        renderer1.setSeriesPaint(0, Color.red);
        renderer1.setSeriesPaint(1, Color.yellow);
        renderer1.setSeriesPaint(2, Color.green);
        CategoryDataset dataset2 = createDataset2();
        ValueAxis axis2 = new NumberAxis3D("Secondary");
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1, dataset2);
        plot.mapDatasetToRangeAxis(1, 1);
        CategoryItemRenderer renderer2 = new LineRenderer3D();
        renderer2.setSeriesPaint(0, Color.blue);
        plot.setRenderer(1, renderer2);

        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        ChartUtilities.applyCurrentTheme(chart);

        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return  The dataset.
     */
    private static CategoryDataset createDataset1() {

        // row keys...
        String series1 = "First";
        String series2 = "Second";
        String series3 = "Third";

        // column keys...
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        String category5 = "Category 5";

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1.0, series1, category1);
        dataset.addValue(4.0, series1, category2);
        dataset.addValue(3.0, series1, category3);
        dataset.addValue(5.0, series1, category4);
        dataset.addValue(5.0, series1, category5);

        dataset.addValue(5.0, series2, category1);
        dataset.addValue(7.0, series2, category2);
        dataset.addValue(6.0, series2, category3);
        dataset.addValue(8.0, series2, category4);
        dataset.addValue(4.0, series2, category5);

        dataset.addValue(4.0, series3, category1);
        dataset.addValue(3.0, series3, category2);
        dataset.addValue(2.0, series3, category3);
        dataset.addValue(3.0, series3, category4);
        dataset.addValue(6.0, series3, category5);

        return dataset;

    }

    /**
     * Creates a sample dataset.
     *
     * @return  The dataset.
     */
    private static CategoryDataset createDataset2() {

        // row keys...
        String series1 = "Fourth";

        // column keys...
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        String category5 = "Category 5";

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(15.0, series1, category1);
        dataset.addValue(24.0, series1, category2);
        dataset.addValue(31.0, series1, category3);
        dataset.addValue(25.0, series1, category4);
        dataset.addValue(56.0, series1, category5);

        return dataset;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart();
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        DualAxisDemo4 demo = new DualAxisDemo4(
                "JFreeChart: DualAxisDemo4.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
