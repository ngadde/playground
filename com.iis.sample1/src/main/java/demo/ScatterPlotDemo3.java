/* ---------------------
 * ScatterPlotDemo3.java
 * ---------------------
 * (C) Copyright 2004-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo scatter plot with some code showing how to convert between Java2D
 * coordinates and (x, y) coordinates.
 */
public class ScatterPlotDemo3 extends ApplicationFrame {

    static class MyChartMouseListener implements ChartMouseListener {

        ChartPanel panel;

        /**
         * Creates a new mouse listener.
         *
         * @param panel  the panel.
         */
        public MyChartMouseListener(ChartPanel panel) {
            this.panel = panel;
        }
        /**
         * Callback method for receiving notification of a mouse click on a
         * chart.
         *
         * @param event  information about the event.
         */
        public void chartMouseClicked(ChartMouseEvent event) {
            int x = event.getTrigger().getX();
            int y = event.getTrigger().getY();

            // the following translation takes account of the fact that the
            // chart image may have been scaled up or down to fit the panel...
            Point2D p = this.panel.translateScreenToJava2D(new Point(x, y));

            // now convert the Java2D coordinate to axis coordinates...
            XYPlot plot = (XYPlot) this.panel.getChart().getPlot();
            ChartRenderingInfo info = this.panel.getChartRenderingInfo();
            Rectangle2D dataArea = info.getPlotInfo().getDataArea();
            double xx = plot.getDomainAxis().java2DToValue(p.getX(), dataArea,
                    plot.getDomainAxisEdge());
            double yy = plot.getRangeAxis().java2DToValue(p.getY(), dataArea,
                    plot.getRangeAxisEdge());

            // just for fun, lets convert the axis coordinates back to component
            // coordinates...
            ValueAxis domainAxis = plot.getDomainAxis();
            ValueAxis rangeAxis = plot.getRangeAxis();
            double xxx = domainAxis.valueToJava2D(xx, dataArea,
                    plot.getDomainAxisEdge());
            double yyy = rangeAxis.valueToJava2D(yy, dataArea,
                    plot.getRangeAxisEdge());

            Point2D p2 = this.panel.translateJava2DToScreen(
                    new Point2D.Double(xxx, yyy));
            System.out.println("Mouse coordinates are (" + x + ", " + y
                + "), in data space = (" + xx + ", " + yy + ").");
            System.out.println("--> (" + p2.getX() + ", " + p2.getY() + ")");
        }

        /**
         * Callback method for receiving notification of a mouse movement on a
         * chart.
         *
         * @param event  information about the event.
         */
        public void chartMouseMoved(ChartMouseEvent event) {
            // ignore
        }

    }


    /**
     * A demonstration application showing a scatter plot.
     *
     * @param title  the frame title.
     */
    public ScatterPlotDemo3(String title) {
        super(title);
        JPanel demoPanel = createDemoPanel();
        demoPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(demoPanel);
    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createScatterPlot("Scatter Plot Demo 3",
                "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setDomainCrosshairLockedOnData(true);
        plot.setRangeCrosshairVisible(true);
        plot.setRangeCrosshairLockedOnData(true);
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setAutoRangeIncludesZero(false);
        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(new SampleXYDataset2());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        panel.addChartMouseListener(new MyChartMouseListener(panel));
        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        ScatterPlotDemo3 demo = new ScatterPlotDemo3(
                "JFreeChart: ScatterPlotDemo3.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
