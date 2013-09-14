/* ---------------------
 * ScatterPlotDemo4.java
 * ---------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A scatter plot demo using the {@link XYDotRenderer} class.
 */
public class ScatterPlotDemo4 extends ApplicationFrame {

    /**
     * A demonstration application showing a scatter plot.
     *
     * @param title  the frame title.
     */
    public ScatterPlotDemo4(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        XYDataset dataset = new SampleXYDataset2();
        JFreeChart chart = ChartFactory.createScatterPlot("Scatter Plot Demo 4",
            "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = (XYPlot) chart.getPlot();

        //plot.setDomainTickBandPaint(new Color(200, 200, 100, 100));
        plot.setRangeTickBandPaint(new Color(200, 200, 100, 100));
        XYDotRenderer renderer = new XYDotRenderer();
        renderer.setDotWidth(4);
        renderer.setDotHeight(4);
        plot.setRenderer(renderer);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setAutoRangeIncludesZero(false);
        plot.getRangeAxis().setInverted(true);
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        ScatterPlotDemo4 demo = new ScatterPlotDemo4(
                "JFreeChart: ScatterPlotDemo4.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
