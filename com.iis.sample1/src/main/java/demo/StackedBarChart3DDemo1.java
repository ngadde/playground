/* ---------------------------
 * StackedBarChart3DDemo1.java
 * ---------------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * A simple demonstration application showing how to create a stacked 3D bar 
 * chart using data from a {@link CategoryDataset}.
 */
public class StackedBarChart3DDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public StackedBarChart3DDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates and returns a {@link CategoryDataset} for the demo chart.
     *
     * @return a sample dataset.
     */
    public static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10.0, "Series 1", "C1");
        dataset.addValue(5.0, "Series 1", "C2");
        dataset.addValue(6.0, "Series 1", "C3");
        dataset.addValue(7.0, "Series 1", "C4");
        dataset.addValue(8.0, "Series 1", "C5");
        dataset.addValue(9.0, "Series 1", "C6");
        dataset.addValue(10.0, "Series 1", "C7");
        dataset.addValue(11.0, "Series 1", "C8");
        dataset.addValue(3.0, "Series 1", "C9");
        
        dataset.addValue(4.0, "Series 2", "C1");
        dataset.addValue(7.0, "Series 2", "C2");
        dataset.addValue(17.0, "Series 2", "C3");
        dataset.addValue(15.0, "Series 2", "C4");
        dataset.addValue(6.0, "Series 2", "C5");
        dataset.addValue(8.0, "Series 2", "C6");
        dataset.addValue(9.0, "Series 2", "C7");
        dataset.addValue(13.0, "Series 2", "C8");
        dataset.addValue(7.0, "Series 2", "C9");

        dataset.addValue(15.0, "Series 3", "C1");
        dataset.addValue(14.0, "Series 3", "C2");
        dataset.addValue(12.0, "Series 3", "C3");
        dataset.addValue(11.0, "Series 3", "C4");
        dataset.addValue(10.0, "Series 3", "C5");
        dataset.addValue(0.0, "Series 3", "C6");
        dataset.addValue(7.0, "Series 3", "C7");
        dataset.addValue(9.0, "Series 3", "C8");
        dataset.addValue(11.0, "Series 3", "C9");
        
        dataset.addValue(14.0, "Series 4", "C1");
        dataset.addValue(3.0, "Series 4", "C2");
        dataset.addValue(7.0, "Series 4", "C3");
        dataset.addValue(0.0, "Series 4", "C4");
        dataset.addValue(9.0, "Series 4", "C5");
        dataset.addValue(6.0, "Series 4", "C6");
        dataset.addValue(7.0, "Series 4", "C7");
        dataset.addValue(9.0, "Series 4", "C8");
        dataset.addValue(10.0, "Series 4", "C9");

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

        JFreeChart chart = ChartFactory.createStackedBarChart3D(
            "Stacked Bar Chart 3D Demo 1",  // chart title
            "Category",                   // domain axis label
            "Value",                      // range axis label
            dataset,                      // data
            PlotOrientation.VERTICAL,     // the plot orientation
            true,                         // include legend
            true,                         // tooltips
            false                         // urls
        );
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        IntervalMarker m = new IntervalMarker(5.0, 10.0, Color.gray, new BasicStroke(0.5f), Color.blue, new BasicStroke(0.5f), 0.5f);
        plot.addRangeMarker(m);
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        renderer.setBaseNegativeItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER));
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
        StackedBarChart3DDemo1 demo = new StackedBarChart3DDemo1(
                "Stacked Bar Chart 3D Demo 1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
