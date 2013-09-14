/* -------------------------
 * StackedBarChartDemo5.java
 * -------------------------
 * (C) Copyright 2005, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * A grouped stacked bar chart with item labels.
 */
public class StackedBarChartDemo5 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public StackedBarChartDemo5(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        result.addValue(3396.0, "S1", "C1");
        result.addValue(1580.0, "S2", "C1");
        result.addValue(76.0, "S3", "C1");
        result.addValue(10100.0, "S4", "C1");
        result.addValue(3429.0, "S1", "C2");
        result.addValue(1562.0, "S2", "C2");
        result.addValue(61.0, "S3", "C2");
        result.addValue(-10100.0, "S4", "C2");
        return result;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset for the chart.
     *
     * @return A sample chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createStackedBarChart(
            "Stacked Bar Chart Demo 5",  // chart title
            "Category",                  // domain axis label
            "Value",                     // range axis label
            dataset,                     // data
            PlotOrientation.VERTICAL,    // the plot orientation
            true,                        // legend
            true,                        // tooltips
            false                        // urls
        );

        GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();
        KeyToGroupMap map = new KeyToGroupMap("G1");
        map.mapKeyToGroup("S1", "G1");
        map.mapKeyToGroup("S2", "G1");
        map.mapKeyToGroup("S3", "G2");
        map.mapKeyToGroup("S4", "G3");
        renderer.setSeriesToGroupMap(map);
        renderer.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        renderer.setPositiveItemLabelPositionFallback(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

        renderer.setItemMargin(0.10);
        SubCategoryAxis domainAxis = new SubCategoryAxis("Category / Group");
        domainAxis.setCategoryMargin(0.05);
        domainAxis.addSubCategory("G1");
        domainAxis.addSubCategory("G2");
        domainAxis.addSubCategory("G3");

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainAxis(domainAxis);
        plot.setRenderer(renderer);

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
        StackedBarChartDemo5 demo = new StackedBarChartDemo5(
                "Stacked Bar Chart Demo 5");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
