/* ------------------------
 * CombinedXYPlotDemo4.java
 * ------------------------
 * (C) Copyright 2003-2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demonstration application showing how to create a combined chart.
 */
public class CombinedXYPlotDemo4 extends ApplicationFrame {

    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public CombinedXYPlotDemo4(String title) {

        super(title);
        JFreeChart chart = createCombinedChart();
        ChartPanel panel = (ChartPanel) createDemoPanel();
        panel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(panel);

    }

    /**
     * Creates a combined chart.
     *
     * @return The combined chart.
     */
    private static JFreeChart createCombinedChart() {

        // create subplot 1...
        XYDataset data1 = createDataset1();
        XYItemRenderer renderer1 = new StandardXYItemRenderer();
        NumberAxis rangeAxis1 = new NumberAxis("Range 1");
        XYPlot subplot1 = new XYPlot(data1, null, rangeAxis1, renderer1);
        subplot1.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);

        // add secondary axis
        subplot1.setDataset(1, createDataset2());
        NumberAxis axis2 = new NumberAxis("Range Axis 2");
        axis2.setAutoRangeIncludesZero(false);
        subplot1.setRangeAxis(1, axis2);
        subplot1.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
        subplot1.setRenderer(1, new StandardXYItemRenderer());
        subplot1.mapDatasetToRangeAxis(1, 1);

        XYTextAnnotation annotation = new XYTextAnnotation("Hello!", 50.0, 10000.0);
        annotation.setFont(new Font("SansSerif", Font.PLAIN, 9));
        annotation.setRotationAngle(Math.PI / 4.0);
        subplot1.addAnnotation(annotation);

        // create subplot 2...
        XYDataset data2 = createDataset2();
        XYItemRenderer renderer2 = new StandardXYItemRenderer();
        NumberAxis rangeAxis2 = new NumberAxis("Range 2");
        rangeAxis2.setAutoRangeIncludesZero(false);
        XYPlot subplot2 = new XYPlot(data2, null, rangeAxis2, renderer2);
        subplot2.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);

        // parent plot...
        CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new NumberAxis("Domain"));
        plot.setGap(10.0);

        // add the subplots...
        plot.add(subplot1, 1);
        plot.add(subplot2, 1);
        plot.setOrientation(PlotOrientation.VERTICAL);

        // return a new chart containing the overlaid plot...
        JFreeChart chart = new JFreeChart("CombinedDomainXYPlot Demo",
                JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        ChartUtilities.applyCurrentTheme(chart);
        return chart;

    }

    /**
     * Creates a sample dataset.
     *
     * @return Series 1.
     */
    private static XYDataset createDataset1() {

        // create dataset 1...
        XYSeries series1 = new XYSeries("Series 1a");
        series1.add(10.0, 12353.3);
        series1.add(20.0, 13734.4);
        series1.add(30.0, 14525.3);
        series1.add(40.0, 13984.3);
        series1.add(50.0, 12999.4);
        series1.add(60.0, 14274.3);
        series1.add(70.0, 15943.5);
        series1.add(80.0, 14845.3);
        series1.add(90.0, 14645.4);
        series1.add(100.0, 16234.6);
        series1.add(110.0, 17232.3);
        series1.add(120.0, 14232.2);
        series1.add(130.0, 13102.2);
        series1.add(140.0, 14230.2);
        series1.add(150.0, 11235.2);

        XYSeries series1b = new XYSeries("Series 1b");
        series1b.add(10.0, 15000.3);
        series1b.add(20.0, 11000.4);
        series1b.add(30.0, 17000.3);
        series1b.add(40.0, 15000.3);
        series1b.add(50.0, 14000.4);
        series1b.add(60.0, 12000.3);
        series1b.add(70.0, 11000.5);
        series1b.add(80.0, 12000.3);
        series1b.add(90.0, 13000.4);
        series1b.add(100.0, 12000.6);
        series1b.add(110.0, 13000.3);
        series1b.add(120.0, 17000.2);
        series1b.add(130.0, 18000.2);
        series1b.add(140.0, 16000.2);
        series1b.add(150.0, 17000.2);

        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series1);
        collection.addSeries(series1b);
        return collection;

    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static XYDataset createDataset2() {

        // create dataset 2...
        XYSeries series2 = new XYSeries("Series 2");

        series2.add(10.0, 6853.2);
        series2.add(20.0, 9642.3);
        series2.add(30.0, 8253.5);
        series2.add(40.0, 5352.3);
        series2.add(50.0, 3532.0);
        series2.add(60.0, 2635.3);
        series2.add(70.0, 3998.2);
        series2.add(80.0, 1943.2);
        series2.add(90.0, 6943.9);
        series2.add(100.0, 7843.2);
        series2.add(105.0, 6495.3);
        series2.add(110.0, 7943.6);
        series2.add(115.0, 8500.7);
        series2.add(120.0, 9595.9);

        return new XYSeriesCollection(series2);

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createCombinedChart();
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        CombinedXYPlotDemo4 demo = new CombinedXYPlotDemo4(
                "JFreeChart: CombinedDomainXYPlotDemo4.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
