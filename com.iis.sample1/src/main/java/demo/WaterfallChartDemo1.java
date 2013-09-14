/* -----------------------
 * WaterfallChartDemo.java
 * -----------------------
 * (C) Copyright 2003-2008, by Object Refinery Limited.
 *
 */

package demo;

import java.text.DecimalFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A sample waterfall chart.
 */
public class WaterfallChartDemo1 extends ApplicationFrame {

    /**
     * Creates a new WaterFall Chart demo.
     *
     * @param title  the frame title.
     */
    public WaterfallChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset for the demo.
     *
     * @return A sample dataset.
     */
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(15.76, "Product 1", "Labour");
        dataset.addValue(8.66, "Product 1", "Administration");
        dataset.addValue(4.71, "Product 1", "Marketing");
        dataset.addValue(3.51, "Product 1", "Distribution");
        dataset.addValue(32.64, "Product 1", "Total Expense");
        return dataset;
    }

    /**
     * Returns the chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createWaterfallChart(
            "Product Cost Breakdown",
            "Expense Category",
            "Cost Per Unit",
            dataset,
            PlotOrientation.VERTICAL,
            false,
            true,
            false
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        ValueAxis rangeAxis = plot.getRangeAxis();

        // create a custom tick unit collection...
        DecimalFormat formatter = new DecimalFormat("##,###");
        formatter.setNegativePrefix("(");
        formatter.setNegativeSuffix(")");
        TickUnits standardUnits = new TickUnits();
        standardUnits.add(new NumberTickUnit(5, formatter));
        standardUnits.add(new NumberTickUnit(10, formatter));
        standardUnits.add(new NumberTickUnit(20, formatter));
        standardUnits.add(new NumberTickUnit(50, formatter));
        standardUnits.add(new NumberTickUnit(100, formatter));
        standardUnits.add(new NumberTickUnit(200, formatter));
        standardUnits.add(new NumberTickUnit(500, formatter));
        standardUnits.add(new NumberTickUnit(1000, formatter));
        standardUnits.add(new NumberTickUnit(2000, formatter));
        standardUnits.add(new NumberTickUnit(5000, formatter));
        rangeAxis.setStandardTickUnits(standardUnits);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setBase(5.0);

        DecimalFormat labelFormatter = new DecimalFormat("$##,###.00");
        labelFormatter.setNegativePrefix("(");
        labelFormatter.setNegativeSuffix(")");
        renderer.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator("{2}", labelFormatter));
        renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator(
                "{0}, {1}) = {2}", new DecimalFormat("$##,###.00")));
        renderer.setBaseItemLabelsVisible(true);

        return chart;
    }

    /**
     * Creates a panel for the demo.
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()));
    }

    /**
     * Starting point for the demo.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        WaterfallChartDemo1 demo = new WaterfallChartDemo1(
                "JFreeChart: WaterfallChartDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
