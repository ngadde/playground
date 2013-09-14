/* ------------------
 * XYSeriesDemo2.java
 * ------------------
 * (C) Copyright 2002-2004, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * Demo for {@link XYSeries}, where all the y values are the same.
 */
public class XYSeriesDemo2 extends ApplicationFrame {

    /**
     * A demonstration application showing an {@link XYSeries} where all the y-values are the same.
     *
     * @param title  the frame title.
     */
    public XYSeriesDemo2(String title) {

        super(title);
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
            "XY Series Demo 2",
            "X",
            "Y",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis axis = (NumberAxis) plot.getRangeAxis();
        axis.setAutoRangeIncludesZero(false);
        axis.setAutoRangeMinimumSize(1.0);
        return chart;
    }

    private static XYDataset createDataset() {
        XYSeries series = new XYSeries("Flat Data");
        series.add(1.0, 100.0);
        series.add(5.0, 100.0);
        series.add(4.0, 100.0);
        series.add(12.5, 100.0);
        series.add(17.3, 100.0);
        series.add(21.2, 100.0);
        series.add(21.9, 100.0);
        series.add(25.6, 100.0);
        series.add(30.0, 100.0);
        XYSeriesCollection dataset = new XYSeriesCollection(series);
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

        XYSeriesDemo2 demo = new XYSeriesDemo2("JFreeChart: XYSeriesDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
