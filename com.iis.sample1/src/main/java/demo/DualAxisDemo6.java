/* ------------------
 * DualAxisDemo6.java
 * ------------------
 * (C) Copyright 2008, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * This demo shows how to create a dual axis bar chart that combines a stacked
 * bar chart and a regular bar chart.  A workaround is used to prevent the
 * two renderers from overlapping the bars for the two datasets - see
 * DualAxisDemo5.java for a simpler case.
 */
public class DualAxisDemo6 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public DualAxisDemo6(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return  The dataset.
     */
    private static CategoryDataset createDataset1() {

        // row keys...
        String series1 = "Series 1A";
        String series2 = "Series 1B";

        // column keys...
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1.0, series1, category1);
        dataset.addValue(4.0, series1, category2);
        dataset.addValue(3.0, series1, category3);
        dataset.addValue(5.0, series1, category4);

        dataset.addValue(3.0, series2, category1);
        dataset.addValue(6.0, series2, category2);
        dataset.addValue(1.0, series2, category3);
        dataset.addValue(5.0, series2, category4);

        return dataset;

    }

    /**
     * Creates a sample dataset.
     *
     * @return  The dataset.
     */
    private static CategoryDataset createDataset2() {

        // row keys...
        String series1 = "Dummy 2";
        String series2 = "Series 2";

        // column keys...
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(null, series1, category1);
        dataset.addValue(null, series1, category2);
        dataset.addValue(null, series1, category3);
        dataset.addValue(null, series1, category4);

        dataset.addValue(75.0, series2, category1);
        dataset.addValue(87.0, series2, category2);
        dataset.addValue(96.0, series2, category3);
        dataset.addValue(68.0, series2, category4);

        return dataset;

    }

    /**
     * Creates a chart.
     *
     * @param dataset1  the first dataset.
     * @param dataset2  the second dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset1,
                                          CategoryDataset dataset2) {

        CategoryAxis domainAxis = new CategoryAxis("Category");
        NumberAxis rangeAxis = new NumberAxis("Value");
        GroupedStackedBarRenderer renderer1 = new GroupedStackedBarRenderer();
        KeyToGroupMap map = new KeyToGroupMap("G1");
        map.mapKeyToGroup("Series 1A", "G1");
        map.mapKeyToGroup("Series 1B", "G1");
        // define a second group, but nothing maps to it
        map.mapKeyToGroup("NOTHING", "G2");
        renderer1.setSeriesToGroupMap(map);
        CategoryPlot plot = new CategoryPlot(dataset1, domainAxis, rangeAxis,
                renderer1) {

            /**
             * Override the getLegendItems() method to handle special case.
             *
             * @return the legend items.
             */
            public LegendItemCollection getLegendItems() {

                LegendItemCollection result = new LegendItemCollection();

                // we can use all the series from renderer 1
                result.addAll(getRenderer().getLegendItems());

                // the JDK 1.2.2 compiler complained about the name of this
                // variable
                CategoryDataset dset2 = getDataset(1);
                if (dset2 != null) {
                    CategoryItemRenderer renderer2 = getRenderer(1);
                    if (renderer2 != null) {
                        LegendItem item = renderer2.getLegendItem(1, 1);
                        result.add(item);
                    }
                }

                return result;

            }

        };

        JFreeChart chart = new JFreeChart("Dual Axis Bar Chart", plot);
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        plot.setDataset(1, dataset2);
        plot.mapDatasetToRangeAxis(1, 1);
        ValueAxis axis2 = new NumberAxis("Secondary");
        plot.setRangeAxis(1, axis2);
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
        BarRenderer renderer2 = new BarRenderer();
        plot.setRenderer(1, renderer2);
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset1(), createDataset2());
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        DualAxisDemo6 demo = new DualAxisDemo6(
                "JFreeChart: DualAxisDemo6.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
