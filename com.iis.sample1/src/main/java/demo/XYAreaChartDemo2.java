/* ---------------------
 * XYAreaChartDemo2.java
 * ---------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create an area chart with
 * a date axis for the domain values.
 */
public class XYAreaChartDemo2 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public XYAreaChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private static XYDataset createDataset() {
        TimeSeries series1 = new TimeSeries("Random 1");
        double value = 0.0;
        Day day = new Day();
        for (int i = 0; i < 200; i++) {
            value = value + Math.random() - 0.5;
            series1.add(day, value);
            day = (Day) day.next();
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection(series1);
        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYAreaChart(
            "XY Area Chart Demo 2",
            "Time", "Value",
            dataset,
            PlotOrientation.VERTICAL,
            true,  // legend
            true,  // tool tips
            false  // URLs
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        ValueAxis domainAxis = new DateAxis("Time");
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        plot.setDomainAxis(domainAxis);
        plot.setForegroundAlpha(0.5f);

        XYItemRenderer renderer = plot.getRenderer();
        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("d-MMM-yyyy"),
                new DecimalFormat("#,##0.00")));

        ChartUtilities.applyCurrentTheme(chart);
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
        XYAreaChartDemo2 demo = new XYAreaChartDemo2("XY Area Chart Demo 2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
