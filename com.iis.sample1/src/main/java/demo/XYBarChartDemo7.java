/* --------------------
 * XYBarChartDemo7.java
 * --------------------
 * (C) Copyright 2007, 2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYIntervalSeries;
import org.jfree.data.xy.XYIntervalSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing an {@link XYBarRenderer}
 * plotting data from a {@link XYIntervalSeriesCollection}.
 */
public class XYBarChartDemo7 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYBarChartDemo7(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel);
    }

    private static JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYBarChart("XYBarChartDemo7",
                "Date", true, "Y", dataset, PlotOrientation.HORIZONTAL,
                true, false, false);


        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setRangePannable(true);
        plot.setRangeAxis(new DateAxis("Date"));
        SymbolAxis xAxis = new SymbolAxis("Series", new String[] {"S1", "S2",
                "S3"});
        xAxis.setGridBandsVisible(false);
        plot.setDomainAxis(xAxis);
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setUseYInterval(true);
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        ChartUtilities.applyCurrentTheme(chart);

        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return A dataset.
     */
    private static IntervalXYDataset createDataset() {
        Day d0 = new Day(12, 6, 2007);
        Day d1 = new Day(13, 6, 2007);
        Day d2 = new Day(14, 6, 2007);
        Day d3 = new Day(15, 6, 2007);
        Day d4 = new Day(16, 6, 2007);
        Day d5 = new Day(17, 6, 2007);
        XYIntervalSeriesCollection dataset = new XYIntervalSeriesCollection();
        XYIntervalSeries s1 = new XYIntervalSeries("S1");
        XYIntervalSeries s2 = new XYIntervalSeries("S2");
        XYIntervalSeries s3 = new XYIntervalSeries("S3");
        addItem(s1, d0, d1, 0);
        addItem(s1, d3, d3, 0);
        addItem(s2, d0, d5, 1);
        addItem(s3, d2, d4, 2);
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        dataset.addSeries(s3);
        return dataset;
    }

    private static void addItem(XYIntervalSeries s, RegularTimePeriod p0,
            RegularTimePeriod p1, int index) {
        s.add(index, index - 0.45, index + 0.45, p0.getFirstMillisecond(),
                p0.getFirstMillisecond(), p1.getLastMillisecond());
    }

    /**
     * Creates a panel for the demo.
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()));
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        XYBarChartDemo7 demo = new XYBarChartDemo7(
                "JFreeChart : XYBarChartDemo7.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
