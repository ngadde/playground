/* ---------------------
 * AxisOffsetsDemo1.java
 * ---------------------
 * (C) Copyright 2007, 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.GridLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * This demo shows two charts, one with the default (zero) axis offsets, and
 * the other with the axes offset by 5 Java2D units.
 */
public class AxisOffsetsDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public AxisOffsetsDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Returns a sample dataset.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset() {

        // row keys...
        String series1 = "S1";
        String series2 = "S2";
        String series3 = "S3";

        // column keys...
        String category1 = "C1";
        String category2 = "C2";
        String category3 = "C3";
        String category4 = "C4";
        String category5 = "C5";

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
     * Creates a sample chart.
     *
     * @param title  the chart title.
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(String title,
            CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
            title,                    // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            false,                    // include legend
            true,                     // tooltips?
            false);                   // URLs?


        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainGridlinesVisible(true);

        // set the range axis to display integers only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green,
                0.0f, 0.0f, new Color(0, 64, 0));
        GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        return chart;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart1 = createChart("Axis Offsets: 0", createDataset());
        CategoryPlot plot1 = (CategoryPlot) chart1.getPlot();
        plot1.setAxisOffset(RectangleInsets.ZERO_INSETS);
        ChartPanel panel1 = new ChartPanel(chart1);
        panel1.setMinimumDrawWidth(0);
        panel1.setMinimumDrawHeight(0);
        JFreeChart chart2 = createChart("Axis Offsets: 5", createDataset());
        ChartPanel panel2 = new ChartPanel(chart2);
        panel2.setMinimumDrawWidth(0);
        panel2.setMinimumDrawHeight(0);
        CategoryPlot plot2 = (CategoryPlot) chart2.getPlot();
        plot2.setAxisOffset(new RectangleInsets(5, 5, 5, 5));
        DemoPanel demoPanel = new DemoPanel(new GridLayout(2, 1));
        demoPanel.add(panel1);
        demoPanel.add(panel2);
        demoPanel.addChart(chart1);
        demoPanel.addChart(chart2);
        return demoPanel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        AxisOffsetsDemo1 demo = new AxisOffsetsDemo1(
                "JFreeChart: AxisOffsetsDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
