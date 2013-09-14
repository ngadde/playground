/* -----------------------
 * SurveyResultsDemo3.java
 * -----------------------
 * (C) Copyright 2003, 2004, 2006, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPosition;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ExtendedCategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.text.TextBlockAnchor;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * A chart showing...
 */
public class SurveyResultsDemo3 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public SurveyResultsDemo3(String title) {
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
        dataset.addValue(2.61, "Results", "Sm.");
        dataset.addValue(2.70, "Results", "Med.");
        dataset.addValue(2.90, "Results", "Lg.");
        dataset.addValue(2.74, "Results", "All");
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
            PlotOrientation.HORIZONTAL,   // orientation
            false,                        // include legend
            true,
            false
        );
        
        chart.setBackgroundPaint(Color.white);
        chart.getPlot().setOutlinePaint(null);
        TextTitle title = new TextTitle("Figure 6 | Overall SEO Rating");
        title.setHorizontalAlignment(HorizontalAlignment.LEFT);
        title.setBackgroundPaint(Color.red);
        title.setPaint(Color.white);
        
        chart.setTitle(title);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setRange(0.0, 4.0);
        rangeAxis.setVisible(false);
        
        ExtendedCategoryAxis domainAxis = new ExtendedCategoryAxis(null);
        domainAxis.setTickLabelFont(new Font("SansSerif", Font.BOLD, 12));
        domainAxis.setCategoryMargin(0.30);        
        domainAxis.addSubLabel("Sm.", "(10)");
        domainAxis.addSubLabel("Med.", "(10)");
        domainAxis.addSubLabel("Lg.", "(10)");
        domainAxis.addSubLabel("All", "(10)");
        CategoryLabelPositions p = domainAxis.getCategoryLabelPositions();
        CategoryLabelPosition left = new CategoryLabelPosition(
                RectangleAnchor.LEFT, TextBlockAnchor.CENTER_LEFT);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.replaceLeftPosition(p, left));
        plot.setDomainAxis(domainAxis);
        
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0x9C, 0xA4, 0x4A));
        renderer.setDrawBarOutline(false);
        
        StandardCategoryItemLabelGenerator generator 
                = new StandardCategoryItemLabelGenerator("{2}", 
                        new DecimalFormat("0.00"));
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelFont(new Font("SansSerif", Font.PLAIN, 18));
        ItemLabelPosition position = new ItemLabelPosition(
                ItemLabelAnchor.INSIDE3, TextAnchor.CENTER_RIGHT);
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

        SurveyResultsDemo3 demo = new SurveyResultsDemo3("Survey Results Demo 3");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
