/* ------------------------
 * VectorRendererDemo1.java
 * ------------------------
 * (C) Copyright 2007, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.VectorRenderer;
import org.jfree.data.xy.VectorSeries;
import org.jfree.data.xy.VectorSeriesCollection;
import org.jfree.data.xy.VectorXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing a chart created using
 * the {@link VectorRenderer}.
 */
public class VectorRendererDemo1 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public VectorRendererDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    private static JFreeChart createChart(VectorXYDataset dataset) {
        NumberAxis xAxis = new NumberAxis("X");
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        xAxis.setLowerMargin(0.01);
        xAxis.setUpperMargin(0.01);
        xAxis.setAutoRangeIncludesZero(false);

        NumberAxis yAxis = new NumberAxis("Y");
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        yAxis.setLowerMargin(0.01);
        yAxis.setUpperMargin(0.01);
        yAxis.setAutoRangeIncludesZero(false);
        VectorRenderer renderer = new VectorRenderer();
        renderer.setSeriesPaint(0, Color.blue);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5, 5, 5, 5));
        plot.setOutlinePaint(Color.black);
        JFreeChart chart = new JFreeChart("Vector Renderer Demo 1", plot);
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return A dataset.
     */
    private static VectorXYDataset createDataset() {
        VectorSeries s1 = new VectorSeries("Series 1");
        for (double r = 0; r < 20.0; r++) {
            for (double c = 0; c < 20.0; c++) {
                s1.add(r + 10.0, c + 10.0,
                        Math.sin(r / 5.0) / 2, Math.cos(c / 5.0) / 2);
            }
        }
        VectorSeriesCollection dataset = new VectorSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    /**
     * Creates a panel for the demo.
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        ChartPanel panel = new ChartPanel(createChart(createDataset()));
        panel.getChartRenderingInfo().setEntityCollection(null);
        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        VectorRendererDemo1 demo = new VectorRendererDemo1(
                "JFreeChart : VectorRendererDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
