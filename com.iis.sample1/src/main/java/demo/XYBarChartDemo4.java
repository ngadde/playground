/* --------------------
 * XYBarChartDemo4.java
 * --------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
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
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demonstration of the {@link XYBarDataset} wrapper class.
 */
public class XYBarChartDemo4 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYBarChartDemo4(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel);
    }

    private static JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYBarChart(
            "XYBarChartDemo4",
            "X",
            false,
            "Y",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            false,
            false
        );

        // then customise it a little...
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        return chart;
    }

    /**
     * Creates a sample dataset.  We create the dataset using a standard
     * {@link XYSeriesCollection}, then wrap it in an {@link XYBarDataset}.
     *
     * @return A sample dataset.
     */
    private static IntervalXYDataset createDataset() {
        XYSeries series = new XYSeries("Series 1");
        series.add(1.0, 5.0);
        series.add(2.0, 70.8);
        series.add(3.0, 48.3);
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series);
        return new XYBarDataset(collection, 0.9);
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
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        XYBarChartDemo4 demo = new XYBarChartDemo4("XY Bar Chart Demo 4");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
