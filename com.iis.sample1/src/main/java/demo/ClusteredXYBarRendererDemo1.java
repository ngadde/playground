/* --------------------------------
 * ClusteredXYBarRendererDemo1.java
 * --------------------------------
 * (C) Copyright 2007-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.GridLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.ClusteredXYBarRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.StandardGradientPaintTransformer;

/**
 * A demo for the ClusteredXYBarRenderer.
 */
public class ClusteredXYBarRendererDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo app.
     *
     * @param title  the frame title.
     */
    public ClusteredXYBarRendererDemo1(String title) {
        super(title);
        setContentPane(createDemoPanel());
    }

    /**
     * Creates the demo chart.
     *
     * @param title  the title.
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(String title,
            IntervalXYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYBarChart(
                title,                      // chart title
                null,                       // domain axis label
                true,
                "Y",                        // range axis label
                dataset,                    // data
                PlotOrientation.VERTICAL,
                true,                       // include legend
                true,
                false
            );
        XYPlot plot = (XYPlot) chart.getPlot();
        ClusteredXYBarRenderer r = new ClusteredXYBarRenderer(0.20, false);
        plot.setRenderer(r);
        return chart;

    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private static IntervalXYDataset createDataset() {
        TimeSeries series1 = new TimeSeries("Series 1");
        series1.add(new Day(1, 1, 2003), 54.3);
        series1.add(new Day(2, 1, 2003), 20.3);
        series1.add(new Day(3, 1, 2003), 43.4);
        series1.add(new Day(4, 1, 2003), -12.0);

        TimeSeries series2 = new TimeSeries("Series 2");
        series2.add(new Day(1, 1, 2003), 8.0);
        series2.add(new Day(2, 1, 2003), 16.0);
        series2.add(new Day(3, 1, 2003), 21.0);
        series2.add(new Day(4, 1, 2003), 5.0);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
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
        DemoPanel panel = new DemoPanel(new GridLayout(2, 2));
        panel.setPreferredSize(new java.awt.Dimension(800, 600));

        IntervalXYDataset dataset = createDataset();

        JFreeChart chart1 = createChart("Vertical", dataset);
        XYPlot plot1 = (XYPlot) chart1.getPlot();
        XYBarRenderer renderer1 = (XYBarRenderer) plot1.getRenderer();
        renderer1.setDrawBarOutline(false);
        renderer1.setSeriesPaint(0, new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, Color.yellow));
        renderer1.setSeriesPaint(1, new GradientPaint(0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, Color.green));
        renderer1.setGradientPaintTransformer(
                new StandardGradientPaintTransformer(
                        GradientPaintTransformType.VERTICAL));

        ChartPanel chartPanel1 = new ChartPanel(chart1);
        panel.add(chartPanel1);

        JFreeChart chart2 = createChart("Vertical / Inverted Axis", dataset);
        XYPlot plot2 = (XYPlot) chart2.getPlot();
        XYBarRenderer renderer2 = (XYBarRenderer) plot2.getRenderer();
        renderer2.setDrawBarOutline(false);
        renderer2.setSeriesPaint(0, new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, Color.yellow));
        renderer2.setSeriesPaint(1, new GradientPaint(0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, Color.green));
        renderer2.setGradientPaintTransformer(
                new StandardGradientPaintTransformer(
                        GradientPaintTransformType.HORIZONTAL));
        plot2.getDomainAxis().setInverted(true);
        ChartPanel chartPanel2 = new ChartPanel(chart2);
        panel.add(chartPanel2);


        JFreeChart chart3 = createChart("Horizontal", dataset);
        XYPlot plot3 = (XYPlot) chart3.getPlot();
        plot3.setOrientation(PlotOrientation.HORIZONTAL);
        XYBarRenderer renderer3 = (XYBarRenderer) plot3.getRenderer();
        renderer3.setDrawBarOutline(false);
        renderer3.setSeriesPaint(0, new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, Color.yellow));
        renderer3.setSeriesPaint(1, new GradientPaint(0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, Color.green));
        renderer3.setGradientPaintTransformer(
                new StandardGradientPaintTransformer(
                        GradientPaintTransformType.CENTER_VERTICAL));
        ChartPanel chartPanel3 = new ChartPanel(chart3);
        panel.add(chartPanel3);

        JFreeChart chart4 = createChart("Horizontal / Inverted Axis", dataset);
        XYPlot plot4 = (XYPlot) chart4.getPlot();
        plot4.setOrientation(PlotOrientation.HORIZONTAL);
        XYBarRenderer renderer4 = (XYBarRenderer) plot4.getRenderer();
        renderer4.setDrawBarOutline(false);
        renderer4.setSeriesPaint(0, new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, Color.yellow));
        renderer4.setSeriesPaint(1, new GradientPaint(0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, Color.green));
        renderer4.setGradientPaintTransformer(
                new StandardGradientPaintTransformer(
                        GradientPaintTransformType.CENTER_HORIZONTAL));
        plot4.getDomainAxis().setInverted(true);
        ChartPanel chartPanel4 = new ChartPanel(chart4);
        panel.add(chartPanel4);
        panel.addChart(chart1);
        panel.addChart(chart2);
        panel.addChart(chart3);
        panel.addChart(chart4);
        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        ClusteredXYBarRendererDemo1 demo = new ClusteredXYBarRendererDemo1(
                "JFreeChart: ClusteredXYBarRendererDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
