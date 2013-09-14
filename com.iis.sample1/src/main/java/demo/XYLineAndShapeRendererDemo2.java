/* --------------------------------
 * XYLineAndShapeRendererDemo2.java
 * --------------------------------
 * (C) Copyright 2007, 2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * This demo illustrates the {@link XYLineAndShapeRenderer#setUseFillPaint}
 * and {@link XYLineAndShapeRenderer#setUseOutlinePaint} methods.
 */
public class XYLineAndShapeRendererDemo2 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYLineAndShapeRendererDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample chart.
     *
     * @return A sample chart.
     */
    private static JFreeChart createChart() {
        XYDataset dataset = createDataset(1, 1.0);
        JFreeChart chart = ChartFactory.createXYLineChart(
            "XYLineAndShapeRenderer Demo 2",
            "X",
            "Y",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        TextTitle subtitle = new TextTitle("This chart shows various "
                + "combinations of the useFillPaint and useOutlinePaint flags.");
        subtitle.setFont(new Font("Dialog", Font.PLAIN, 10));
        chart.addSubtitle(subtitle);

        ChartUtilities.applyCurrentTheme(chart);

        XYPlot plot = (XYPlot) chart.getPlot();

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(false);

        Shape shape = new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0);
        XYLineAndShapeRenderer renderer1 = (XYLineAndShapeRenderer)
                plot.getRenderer();
        renderer1.setBaseShapesVisible(true);
        renderer1.setSeriesShape(0, shape);
        renderer1.setSeriesPaint(0, Color.red);
        renderer1.setSeriesFillPaint(0, Color.yellow);
        renderer1.setSeriesOutlinePaint(0, Color.gray);

        XYDataset dataset2 = createDataset(2, 2.0);
        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        plot.setDataset(1, dataset2);
        plot.setRenderer(1, renderer2);
        renderer2.setSeriesShape(0, shape);
        renderer2.setSeriesPaint(0, Color.red);
        renderer2.setSeriesFillPaint(0, Color.yellow);
        renderer2.setSeriesOutlinePaint(0, Color.gray);
        renderer2.setUseFillPaint(true);

        XYDataset dataset3 = createDataset(3, 3.0);
        XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer();
        plot.setDataset(2, dataset3);
        plot.setRenderer(2, renderer3);
        renderer3.setSeriesShape(0, shape);
        renderer3.setSeriesPaint(0, Color.red);
        renderer3.setSeriesFillPaint(0, Color.yellow);
        renderer3.setSeriesOutlinePaint(0, Color.gray);
        renderer3.setUseOutlinePaint(true);

        XYDataset dataset4 = createDataset(4, 4.0);
        XYLineAndShapeRenderer renderer4 = new XYLineAndShapeRenderer();
        plot.setDataset(3, dataset4);
        plot.setRenderer(3, renderer4);
        renderer4.setSeriesShape(0, shape);
        renderer4.setSeriesPaint(0, Color.red);
        renderer4.setSeriesFillPaint(0, Color.yellow);
        renderer4.setSeriesOutlinePaint(0, Color.gray);
        renderer4.setUseOutlinePaint(true);
        renderer4.setUseFillPaint(true);

        XYDataset dataset5 = createDataset(5, 5.0);
        XYLineAndShapeRenderer renderer5 = new XYLineAndShapeRenderer();
        plot.setDataset(4, dataset5);
        plot.setRenderer(4, renderer5);
        renderer5.setSeriesShape(0, shape);
        renderer5.setSeriesPaint(0, Color.red);
        renderer5.setSeriesFillPaint(0, Color.yellow);
        renderer5.setSeriesOutlinePaint(0, Color.gray);
        renderer5.setUseOutlinePaint(true);
        renderer5.setUseFillPaint(true);
        renderer5.setDrawOutlines(false);

        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @param series  the series index.
     * @param value  the value.
     *
     * @return A dataset.
     */
    private static XYDataset createDataset(int series, double value) {
        XYSeries series1 = new XYSeries("Series " + series);
        series1.add(1.0, value);
        series1.add(2.0, value);
        series1.add(3.0, value);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        return dataset;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart();
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
        XYLineAndShapeRendererDemo2 demo = new XYLineAndShapeRendererDemo2(
                "JFreeChart: XYLineAndShapeRendererDemo2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
