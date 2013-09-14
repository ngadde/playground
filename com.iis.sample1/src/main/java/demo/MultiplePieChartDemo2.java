/* --------------------------
 * MultiplePieChartDemo2.java
 * --------------------------
 * (C) Copyright 2003-2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.text.NumberFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.TableOrder;

/**
 * This example is similar to {@link MultiplePieChartDemo1}, but slices the
 * dataset by column rather than by row.
 */
public class MultiplePieChartDemo2 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public MultiplePieChartDemo2(String title) {
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
        double[][] data = new double[][] {
            {3.0, 4.0, 3.0, 5.0},
            {5.0, 7.0, 6.0, 8.0},
            {5.0, 7.0, 3.0, 8.0},
            {1.0, 2.0, 3.0, 4.0},
            {2.0, 3.0, 2.0, 3.0}
        };
        CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
                "Region ", "Sales/Q", data);
        return dataset;
    }

    /**
     * Creates a sample chart with the given dataset.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createMultiplePieChart(
            "Multiple Pie Chart",  // chart title
            dataset,               // dataset
            TableOrder.BY_COLUMN,
            true,                  // include legend
            true,
            false
        );
        MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setOutlineStroke(new BasicStroke(1.0f));
        JFreeChart subchart = plot.getPieChart();
        PiePlot p = (PiePlot) subchart.getPlot();
        p.setBackgroundPaint(null);
        p.setOutlineStroke(null);
        p.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})",
                NumberFormat.getNumberInstance(),
                NumberFormat.getPercentInstance()));
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

        MultiplePieChartDemo2 demo = new MultiplePieChartDemo2(
                "JFreeChart: MultiplePieChartDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
