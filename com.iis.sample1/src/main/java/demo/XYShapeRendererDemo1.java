/* -------------------------
 * XYShapeRendererDemo1.java
 * -------------------------
 * (C) Copyright 2008, 2009, by Object Refinery Limited.
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.xy.XYShapeRenderer;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demo for the {@link XYShapeRenderer} class.
 */
public class XYShapeRendererDemo1 extends ApplicationFrame {

    /**
     * A demonstration application showing a bubble chart.
     *
     * @param title  the frame title.
     */
    public XYShapeRendererDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(XYZDataset dataset) {
        NumberAxis xAxis = new NumberAxis("X");
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis("Y");
        yAxis.setAutoRangeIncludesZero(false);
        XYShapeRenderer renderer = new XYShapeRenderer();
        LookupPaintScale ps = new LookupPaintScale(1.0, 4.0, new Color(0, 0, 255));
        ps.add(2.0, new Color(100, 100, 255));
        ps.add(3.0, new Color(200, 200, 255));
        renderer.setPaintScale(ps);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        JFreeChart chart = new JFreeChart("XYShapeRendererDemo1", plot);
        chart.removeLegend();
        NumberAxis zAxis = new NumberAxis("Score");
        zAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        PaintScaleLegend psl = new PaintScaleLegend(ps, zAxis);
        psl.setPosition(RectangleEdge.RIGHT);
        psl.setMargin(4, 4, 40, 4);
        psl.setAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        chart.addSubtitle(psl);
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    public static XYZDataset createDataset() {
        DefaultXYZDataset dataset = new DefaultXYZDataset();
        double[] x = {2.1, 2.3, 2.3, 2.2, 2.2, 1.8, 1.8, 1.9, 2.3, 2.8};
        double[] y = {14.1, 17.1, 10.0, 8.8, 8.7, 8.4, 5.4, 4.1, 4.1, 25};
        double[] z = {2.4, 2.7, 1.7, 2.2, 1.3, 2.2, 2.1, 3.2, 1.6, 3.4};
        double[][] series = new double[][] { x, y, z };
        dataset.addSeries("Series 1", series);
        return dataset;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        return chartPanel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        XYShapeRendererDemo1 demo = new XYShapeRendererDemo1(
                "JFreeChart: XYShapeRendererDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
