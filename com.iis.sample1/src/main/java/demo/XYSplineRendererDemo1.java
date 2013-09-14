/* --------------------------
 * XYSplineRendererDemo1.java
 * --------------------------
 * (C) Copyright 2007, 2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
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
public class XYSplineRendererDemo1 extends ApplicationFrame {

    static class MyDemoPanel extends DemoPanel {

        /** Dataset 1. */
        private XYDataset data1;

        /**
         * Creates a new instance.
         */
        public MyDemoPanel() {
            super(new BorderLayout());
            this.data1 = createSampleData();
            add(createContent());
        }

        /**
         * Creates and returns a sample dataset.  The data was randomly
         * generated.
         *
         * @return a sample dataset.
         */
        private XYDataset createSampleData() {
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
         * Creates a tabbed pane for displaying sample charts.
         *
         * @return the tabbed pane.
         */
        private JTabbedPane createContent() {
            JTabbedPane tabs = new JTabbedPane();
            tabs.add("Splines:", createChartPanel1());
            tabs.add("Lines:", createChartPanel2());
            return tabs;
        }

        /**
         * Creates a chart based on the first dataset, with a fitted linear regression line.
         *
         * @return the chart panel.
         */
        private ChartPanel createChartPanel1() {

            // create plot...
            NumberAxis xAxis = new NumberAxis("X");
            xAxis.setAutoRangeIncludesZero(false);
            NumberAxis yAxis = new NumberAxis("Y");
            yAxis.setAutoRangeIncludesZero(false);

            XYSplineRenderer renderer1 = new XYSplineRenderer();
            XYPlot plot = new XYPlot(this.data1, xAxis, yAxis, renderer1);
            plot.setBackgroundPaint(Color.lightGray);
            plot.setDomainGridlinePaint(Color.white);
            plot.setRangeGridlinePaint(Color.white);
            plot.setAxisOffset(new RectangleInsets(4, 4, 4, 4));

            // create and return the chart panel...
            JFreeChart chart = new JFreeChart("XYSplineRenderer",
                    JFreeChart.DEFAULT_TITLE_FONT, plot, true);
            addChart(chart);
            ChartUtilities.applyCurrentTheme(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            return chartPanel;

        }

        /**
         * Creates a chart based on the second dataset, with a fitted power
         * regression line.
         *
         * @return the chart panel.
         */
        private ChartPanel createChartPanel2() {

            // create subplot 1...
            NumberAxis xAxis = new NumberAxis("X");
            xAxis.setAutoRangeIncludesZero(false);
            NumberAxis yAxis = new NumberAxis("Y");
            yAxis.setAutoRangeIncludesZero(false);

            XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
            XYPlot plot = new XYPlot(this.data1, xAxis, yAxis, renderer1);
            plot.setBackgroundPaint(Color.lightGray);
            plot.setDomainGridlinePaint(Color.white);
            plot.setRangeGridlinePaint(Color.white);
            plot.setAxisOffset(new RectangleInsets(4, 4, 4, 4));

            // create and return the chart panel...
            JFreeChart chart = new JFreeChart("XYLineAndShapeRenderer",
                    JFreeChart.DEFAULT_TITLE_FONT, plot, true);
            addChart(chart);
            ChartUtilities.applyCurrentTheme(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            return chartPanel;

        }

    }

    /**
     * Creates a new instance of the demo application.
     *
     * @param title  the frame title.
     */
    public XYSplineRendererDemo1(String title) {
        super(title);
        JPanel content = createDemoPanel();
        getContentPane().add(content);
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    /**
     * The starting point for the regression demo.
     *
     * @param args  ignored.
     */
    public static void main(String args[]) {
        XYSplineRendererDemo1 appFrame = new XYSplineRendererDemo1(
                "JFreeChart: XYSplineRendererDemo1.java");
        appFrame.pack();
        RefineryUtilities.centerFrameOnScreen(appFrame);
        appFrame.setVisible(true);
    }

}
