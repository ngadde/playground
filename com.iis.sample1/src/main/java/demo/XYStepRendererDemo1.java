/* ------------------------
 * XYStepRendererDemo1.java
 * ------------------------
 * (C) Copyright 2005, 2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYStepRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration of the {@link XYStepRenderer} class.
 */
public class XYStepRendererDemo1 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYStepRendererDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  a dataset for the chart.
     *
     * @return A sample chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
            "XYStepRenderer Demo 1",
            "X",
            "Y",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        XYStepRenderer renderer = new XYStepRenderer();
        renderer.setBaseShapesVisible(true);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
        renderer.setDefaultEntityRadius(6);
        plot.setRenderer(renderer);
        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return A dataset.
     */
    private static XYDataset createDataset() {
        XYSeries series1 = new XYSeries("Series 1");
        series1.add(1.0, 3.0);
        series1.add(2.0, 4.0);
        series1.add(3.0, 2.0);
        series1.add(6.0, 3.0);
        XYSeries series2 = new XYSeries("Series 2");
        series2.add(1.0, 7.0);
        series2.add(2.0, 6.0);
        series2.add(3.0, 9.0);
        series2.add(4.0, 5.0);
        series2.add(6.0, 4.0);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
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
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        XYStepRendererDemo1 demo = new XYStepRendererDemo1(
            "JFreeChart: XYStepRendererDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
