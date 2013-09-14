/* --------------------------
 * MultiplePieChartDemo1.java
 * --------------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Font;

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
 * A simple demonstration application showing how to create a chart consisting
 * of multiple pie charts.
 */
public class MultiplePieChartDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public MultiplePieChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
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
            {5.0, 7.0, Double.NaN, 3.0},
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
            TableOrder.BY_ROW,
            true,                  // include legend
            true,
            false
        );
        MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
        JFreeChart subchart = plot.getPieChart();
        PiePlot p = (PiePlot) subchart.getPlot();
        p.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}"));
        p.setLabelFont(new Font("SansSerif", Font.PLAIN, 8));
        //p.setInteriorGap(0.30);

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

        MultiplePieChartDemo1 demo = new MultiplePieChartDemo1(
            "JFreeChart: MultiplePieChartDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
