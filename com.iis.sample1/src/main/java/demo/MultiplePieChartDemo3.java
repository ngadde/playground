/* --------------------------
 * MultiplePieChartDemo3.java
 * --------------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.TableOrder;

/**
 * A simple demonstration application showing how to create a chart consisting
 * of multiple pie charts.
 */
public class MultiplePieChartDemo3 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public MultiplePieChartDemo3(String title) {

        super(title);
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, true, true, true, false,
                true);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 380));
        setContentPane(chartPanel);

    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(5.6, "Row 0", "Column 0");
        dataset.addValue(4.3, "Row 0", "Column 1");
        dataset.addValue(6.7, "Row 0", "Column 2");
        dataset.addValue(4.4, "Row 0", "Column 3");
        dataset.addValue(6.1, "Row 0", "Column 4");

        dataset.addValue(5.8, "Row 1", "Column 0");
        dataset.addValue(3.2, "Row 1", "Column 1");
        dataset.addValue(4.5, "Row 1", "Column 2");
        dataset.addValue(7.0, "Row 1", "Column 3");
        dataset.addValue(5.8, "Row 1", "Column 4");

        dataset.addValue(5.3, "Row 2", "Column 0");
        dataset.addValue(6.7, "Row 2", "Column 1");
        dataset.addValue(7.1, "Row 2", "Column 2");
        dataset.addValue(4.2, "Row 2", "Column 3");
        dataset.addValue(9.0, "Row 2", "Column 4");

        dataset.addValue(5.6, "Row 3", "Column 0");
        dataset.addValue(5.6, "Row 3", "Column 1");
        dataset.addValue(5.6, "Row 3", "Column 2");
        dataset.addValue(5.6, "Row 3", "Column 3");
        dataset.addValue(5.6, "Row 3", "Column 4");

        dataset.addValue(5.6, "Row 4", "Column 0");
        dataset.addValue(5.6, "Row 4", "Column 1");
        dataset.addValue(5.6, "Row 4", "Column 2");
        dataset.addValue(5.6, "Row 4", "Column 3");
        dataset.addValue(5.6, "Row 4", "Column 4");

        return dataset;
    }

    /**
     * Creates a sample chart for the given dataset.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createMultiplePieChart3D(
            "Multiple Pie Chart Demo 3", dataset, TableOrder.BY_COLUMN, true,
            true, false);
        MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
        PiePlot p = (PiePlot) plot.getPieChart().getPlot();
        p.setMaximumLabelWidth(0.18);
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

        MultiplePieChartDemo3 demo = new MultiplePieChartDemo3(
                "JFreeChart: MultiplePieChartDemo3.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
