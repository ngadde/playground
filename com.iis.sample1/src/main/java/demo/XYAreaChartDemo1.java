/* --------------------
 * XYAreaChartDemo.java
 * --------------------
 * (C) Copyright 2002-2004, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * A simple demonstration application showing how to create an area chart.
 */
public class XYAreaChartDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public XYAreaChartDemo1(String title) {

        super(title);
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    private static XYDataset createDataset() {
        XYSeries series1 = new XYSeries("Random 1");
        series1.add(new Integer(1), new Double(500.2));
        series1.add(new Integer(2), new Double(694.1));
        series1.add(new Integer(3), new Double(-734.4));
        series1.add(new Integer(4), new Double(453.2));
        series1.add(new Integer(5), new Double(500.2));
        series1.add(new Integer(6), new Double(300.7));
        series1.add(new Integer(7), new Double(734.4));
        series1.add(new Integer(8), new Double(453.2));

        XYSeries series2 = new XYSeries("Random 2");
        series2.add(new Integer(1), new Double(700.2));
        series2.add(new Integer(2), new Double(534.1));
        series2.add(new Integer(3), new Double(323.4));
        series2.add(new Integer(4), new Double(125.2));
        series2.add(new Integer(5), new Double(653.2));
        series2.add(new Integer(6), new Double(432.7));
        series2.add(new Integer(7), new Double(564.4));
        series2.add(new Integer(8), new Double(322.2));

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.setIntervalWidth(0.0);
        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYAreaChart(
            "XY Area Chart Demo",
            "Domain (X)", "Range (Y)",
            dataset,
            PlotOrientation.VERTICAL,
            true,  // legend
            true,  // tool tips
            false  // URLs
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setForegroundAlpha(0.65f);

        ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickMarkPaint(Color.black);
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);

        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setTickMarkPaint(Color.black);

        XYPointerAnnotation pointer = new XYPointerAnnotation(
                "Test", 5.0, -500.0, 3.0 * Math.PI / 4.0);
        pointer.setTipRadius(0.0);
        pointer.setBaseRadius(35.0);
        pointer.setFont(new Font("SansSerif", Font.PLAIN, 9));
        pointer.setPaint(Color.blue);
        pointer.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);
        plot.addAnnotation(pointer);
        return chart;

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

        XYAreaChartDemo1 demo = new XYAreaChartDemo1("XY Area Chart Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
