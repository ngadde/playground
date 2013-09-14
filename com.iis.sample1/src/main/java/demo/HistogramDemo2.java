/* -------------------
 * HistogramDemo2.java
 * -------------------
 * (C) Copyright 2005-2009, by Object Refinery Limited.
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
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.SimpleHistogramBin;
import org.jfree.data.statistics.SimpleHistogramDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo of the {@link SimpleHistogramDataset} class.
 */
public class HistogramDemo2 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public HistogramDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample {@link HistogramDataset}.
     *
     * @return the dataset.
     */
    private static IntervalXYDataset createDataset() {
        SimpleHistogramDataset dataset = new SimpleHistogramDataset("Series 1");
        SimpleHistogramBin bin1 = new SimpleHistogramBin(0.0, 1.0, true, false);
        SimpleHistogramBin bin2 = new SimpleHistogramBin(1.0, 2.0, true, false);
        SimpleHistogramBin bin3 = new SimpleHistogramBin(2.0, 3.0, true, false);
        SimpleHistogramBin bin4 = new SimpleHistogramBin(3.0, 4.0, true, true);
        // set the bin counts manually (an alternative is to use the
        // addObservation() or addObservations() methods)
        bin1.setItemCount(1);
        bin2.setItemCount(10);
        bin3.setItemCount(15);
        bin4.setItemCount(20);
        dataset.addBin(bin1);
        dataset.addBin(bin2);
        dataset.addBin(bin3);
        dataset.addBin(bin4);
        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset  a dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createHistogram(
            "HistogramDemo2",
            null,
            null,
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setForegroundAlpha(0.85f);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    /**
     * The starting point for the demo.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        HistogramDemo2 demo = new HistogramDemo2(
                "JFreeChart: HistogramDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
