/* -----------------------
 * EventFrequencyDemo.java
 * -----------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.text.DateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.date.MonthConstants;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo application showing how to display category data against a date axis.
 */
public class EventFrequencyDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public EventFrequencyDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  a dataset.
     *
     * @return A sample chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createBarChart(
            "Event Frequency Demo",      // title
            "Category",                  // domain axis label
            "Value",                     // range axis label
            dataset,                     // dataset
            PlotOrientation.HORIZONTAL,  // orientation
            true,                        // include legend
            true,                        // tooltips
            false                        // URLs
        );

        // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangePannable(true);
        plot.getDomainAxis().setMaximumCategoryLabelWidthRatio(10.0f);
        plot.setRangeAxis(new DateAxis("Date"));
        CategoryToolTipGenerator toolTipGenerator
                = new StandardCategoryToolTipGenerator("{0}, {1} : {2}",
                        DateFormat.getDateInstance());
        CategoryItemRenderer renderer = new LineAndShapeRenderer(false, true);
        renderer.setBaseToolTipGenerator(toolTipGenerator);
        plot.setRenderer(renderer);

        ChartUtilities.applyCurrentTheme(chart);

        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // initialise the data...
        Day d1 = new Day(12, MonthConstants.JUNE, 2002);
        Day d2 = new Day(14, MonthConstants.JUNE, 2002);
        Day d3 = new Day(15, MonthConstants.JUNE, 2002);
        Day d4 = new Day(10, MonthConstants.JULY, 2002);
        Day d5 = new Day(20, MonthConstants.JULY, 2002);
        Day d6 = new Day(22, MonthConstants.AUGUST, 2002);

        dataset.setValue(new Long(d1.getMiddleMillisecond()), "Series 1",
                "Requirement 1");
        dataset.setValue(new Long(d1.getMiddleMillisecond()), "Series 1",
                "Requirement 2");
        dataset.setValue(new Long(d2.getMiddleMillisecond()), "Series 1",
                "Requirement 3");
        dataset.setValue(new Long(d3.getMiddleMillisecond()), "Series 2",
                "Requirement 1");
        dataset.setValue(new Long(d4.getMiddleMillisecond()), "Series 2",
                "Requirement 3");
        dataset.setValue(new Long(d5.getMiddleMillisecond()), "Series 3",
                "Requirement 2");
        dataset.setValue(new Long(d6.getMiddleMillisecond()), "Series 1",
                "Requirement 4");

        return dataset;
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

        EventFrequencyDemo1 demo = new EventFrequencyDemo1(
                "JFreeChart: EventFrequencyDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
