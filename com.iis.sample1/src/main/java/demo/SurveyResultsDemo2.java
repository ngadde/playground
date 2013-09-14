/* -----------------------
 * SurveyResultsDemo2.java
 * -----------------------
 * (C) Copyright 2003, 2004, 2006, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ExtendedCategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * A bar chart with category labels and sublabels.
 */
public class SurveyResultsDemo2 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public SurveyResultsDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(300, 270));
        setContentPane(chartPanel);
    }
    
    /**
     * Creates a dataset.
     * 
     * @return The dataset.
     */
    private static CategoryDataset createDataset() { 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.32, "Results", "Sm.");
        dataset.addValue(0.40, "Results", "Med.");
        dataset.addValue(2.62, "Results", "Lg.");
        dataset.addValue(1.44, "Results", "All");
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

        JFreeChart chart = ChartFactory.createBarChart(
            null,                         // chart title
            null,                         // domain axis label
            null,                         // range axis label
            dataset,                      // data
            PlotOrientation.VERTICAL,     // orientation
            false,                        // include legend
            true,
            false
        );
        
        chart.setBackgroundPaint(Color.white);
        chart.getPlot().setOutlinePaint(null);
        TextTitle title = new TextTitle(
                "Figure 8.5 - Case studies are available");
        title.setHorizontalAlignment(HorizontalAlignment.LEFT);
        title.setBackgroundPaint(Color.red);
        title.setPaint(Color.white);
        
        chart.setTitle(title);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setRange(0.0, 5.0);
        rangeAxis.setVisible(false);
        
        ExtendedCategoryAxis domainAxis = new ExtendedCategoryAxis(null);
        domainAxis.setTickLabelFont(new Font("SansSerif", Font.BOLD, 12));
        domainAxis.setCategoryMargin(0.30);        
        domainAxis.addSubLabel("Sm.", "(10)");
        domainAxis.addSubLabel("Med.", "(10)");
        domainAxis.addSubLabel("Lg.", "(10)");
        domainAxis.addSubLabel("All", "(10)");
        plot.setDomainAxis(domainAxis);
        
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0x9C, 0xA4, 0x4A));
        renderer.setDrawBarOutline(false);
        
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelFont(new Font("SansSerif", Font.PLAIN, 18));
        ItemLabelPosition position = new ItemLabelPosition(
                ItemLabelAnchor.INSIDE12, TextAnchor.TOP_CENTER);
        renderer.setBasePositiveItemLabelPosition(position);
        renderer.setPositiveItemLabelPositionFallback(new ItemLabelPosition());
        
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
        SurveyResultsDemo2 demo = new SurveyResultsDemo2(
                "Survey Results Demo 2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
