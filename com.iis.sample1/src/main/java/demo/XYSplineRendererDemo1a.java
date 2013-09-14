/* ---------------------------
 * XYSplineRendererDemo1a.java
 * ---------------------------
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
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing a line chart drawn using spline curves. 
 */
public class XYSplineRendererDemo1a extends ApplicationFrame {

    /**
     * Creates a new instance of the demo application.
     *
     * @param title  the frame title.
     */
    public XYSplineRendererDemo1a(String title) {
        super(title);
        JPanel content = createDemoPanel();
        content.setPreferredSize(new java.awt.Dimension(500, 270));
        getContentPane().add(content);
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {

        // create plot...
        NumberAxis xAxis = new NumberAxis("X");
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis("Y");
        yAxis.setAutoRangeIncludesZero(false);

        XYSplineRenderer renderer1 = new XYSplineRenderer();
        XYPlot plot = new XYPlot(createSampleData(), xAxis, yAxis, renderer1);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(4, 4, 4, 4));

        // create and return the chart panel...
        JFreeChart chart = new JFreeChart("XYSplineRenderer",
                JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        ChartUtilities.applyCurrentTheme(chart);
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

    /**
     * Creates and returns a sample dataset.  The data was randomly
     * generated.
     *
     * @return a sample dataset.
     */
    private static XYDataset createSampleData() {
        XYSeries series = new XYSeries("Series 1");
        series.add(2.0, 56.27);
        series.add(3.0, 41.32);
        series.add(4.0, 31.45);
        series.add(5.0, 30.05);
        series.add(6.0, 24.69);
        series.add(7.0, 19.78);
        series.add(8.0, 20.94);
        series.add(9.0, 16.73);
        series.add(10.0, 14.21);
        series.add(11.0, 12.44);
        XYSeriesCollection result = new XYSeriesCollection(series);
        XYSeries series2 = new XYSeries("Series 2");
        series2.add(11.0, 56.27);
        series2.add(10.0, 41.32);
        series2.add(9.0, 31.45);
        series2.add(8.0, 30.05);
        series2.add(7.0, 24.69);
        series2.add(6.0, 19.78);
        series2.add(5.0, 20.94);
        series2.add(4.0, 16.73);
        series2.add(3.0, 14.21);
        series2.add(2.0, 12.44);
        result.addSeries(series2);
        return result;
    }

    /**
     * The starting point for the regression demo.
     *
     * @param args  ignored.
     */
    public static void main(String args[]) {
        XYSplineRendererDemo1a appFrame = new XYSplineRendererDemo1a(
                "JFreeChart: XYSplineRendererDemo1a.java");
        appFrame.pack();
        RefineryUtilities.centerFrameOnScreen(appFrame);
        appFrame.setVisible(true);
    }

}
