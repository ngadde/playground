/* --------------------------
 * MultiplePieChartDemo4.java
 * --------------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
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
public class MultiplePieChartDemo4 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public MultiplePieChartDemo4(String title) {
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
        dataset.addValue(3.2, "Row 0", "Column 1");
        dataset.addValue(1.8, "Row 0", "Column 2");
        dataset.addValue(0.2, "Row 0", "Column 3");
        dataset.addValue(4.1, "Row 0", "Column 4");

        dataset.addValue(9.8, "Row 1", "Column 0");
        dataset.addValue(6.3, "Row 1", "Column 1");
        dataset.addValue(0.1, "Row 1", "Column 2");
        dataset.addValue(1.9, "Row 1", "Column 3");
        dataset.addValue(9.6, "Row 1", "Column 4");

        dataset.addValue(7.0, "Row 2", "Column 0");
        dataset.addValue(5.2, "Row 2", "Column 1");
        dataset.addValue(2.8, "Row 2", "Column 2");
        dataset.addValue(8.8, "Row 2", "Column 3");
        dataset.addValue(7.2, "Row 2", "Column 4");

        dataset.addValue(9.5, "Row 3", "Column 0");
        dataset.addValue(1.2, "Row 3", "Column 1");
        dataset.addValue(4.5, "Row 3", "Column 2");
        dataset.addValue(4.4, "Row 3", "Column 3");
        dataset.addValue(0.2, "Row 3", "Column 4");

        dataset.addValue(3.5, "Row 4", "Column 0");
        dataset.addValue(6.7, "Row 4", "Column 1");
        dataset.addValue(9.0, "Row 4", "Column 2");
        dataset.addValue(1.0, "Row 4", "Column 3");
        dataset.addValue(5.2, "Row 4", "Column 4");

        dataset.addValue(5.1, "Row 5", "Column 0");
        dataset.addValue(6.7, "Row 5", "Column 1");
        dataset.addValue(0.9, "Row 5", "Column 2");
        dataset.addValue(3.3, "Row 5", "Column 3");
        dataset.addValue(3.9, "Row 5", "Column 4");

        dataset.addValue(5.6, "Row 6", "Column 0");
        dataset.addValue(5.6, "Row 6", "Column 1");
        dataset.addValue(5.6, "Row 6", "Column 2");
        dataset.addValue(5.6, "Row 6", "Column 3");
        dataset.addValue(5.6, "Row 6", "Column 4");

        dataset.addValue(7.5, "Row 7", "Column 0");
        dataset.addValue(9.0, "Row 7", "Column 1");
        dataset.addValue(3.4, "Row 7", "Column 2");
        dataset.addValue(4.1, "Row 7", "Column 3");
        dataset.addValue(0.5, "Row 7", "Column 4");

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
                "Multiple Pie Chart Demo 4", dataset, TableOrder.BY_COLUMN,
                false, true, false);
        MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
        plot.setLimit(0.10);
        JFreeChart subchart = plot.getPieChart();
        PiePlot p = (PiePlot) subchart.getPlot();
        p.setIgnoreNullValues(true);
        p.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}"));
        p.setMaximumLabelWidth(0.20);
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

        MultiplePieChartDemo4 demo = new MultiplePieChartDemo4(
                "JFreeChart: MultiplePieChartDemo4.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
