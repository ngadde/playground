/* ----------------
 * MarkerDemo2.java
 * ----------------
 * (C) Copyright 2005-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.PeriodAxis;
import org.jfree.chart.axis.PeriodAxisLabelInfo;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * This demo shows the use of domain and range markers on a time series chart.
 */
public class MarkerDemo2 extends ApplicationFrame {

    /**
     * Creates a new instance.
     *
     * @param title  the frame title.
     */
    public MarkerDemo2(String title) {

        super(title);
        XYDataset data = createDataset();
        JFreeChart chart = createChart(data);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
        setContentPane(chartPanel);

    }

    /**
     * Creates a sample chart.
     *
     * @param data  the sample data.
     *
     * @return A configured chart.
     */
    private static JFreeChart createChart(XYDataset data) {

        JFreeChart chart = ChartFactory.createXYLineChart(
            "Marker Demo 2",
            "X",
            "Temperature",
            data,
            PlotOrientation.VERTICAL,
            false,
            true,
            false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        // set axis margins to allow space for marker labels...
        PeriodAxis domainAxis = new PeriodAxis(null, new Hour(0, 30, 6, 2005),
                new Hour(23, 30, 6, 2005));
        PeriodAxisLabelInfo[] info = new PeriodAxisLabelInfo[2];
        info[0] = new PeriodAxisLabelInfo(Hour.class,
                new SimpleDateFormat("HH"));
        info[1] = new PeriodAxisLabelInfo(Day.class,
                new SimpleDateFormat("dd-MMM"));
        domainAxis.setLabelInfo(info);
        plot.setDomainAxis(domainAxis);

        ChartUtilities.applyCurrentTheme(chart);

        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setDomainGridlineStroke(new BasicStroke(1.0f));
        plot.setRangeGridlinePaint(Color.lightGray);
        plot.setRangeGridlineStroke(new BasicStroke(1.0f));
        plot.setRangeTickBandPaint(new Color(240, 240, 240));

        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setRange(0, 100);

        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.green);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        // add a labelled marker for the safety threshold...
        Marker threshold = new ValueMarker(80.0);
        threshold.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        threshold.setPaint(Color.red);
        threshold.setStroke(new BasicStroke(2.0f));
        threshold.setLabel("Temperature Threshold");
        threshold.setLabelFont(new Font("SansSerif", Font.PLAIN, 11));
        threshold.setLabelPaint(Color.red);
        threshold.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        threshold.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
        plot.addRangeMarker(threshold);

        // add range marker for the cooling period...
        Hour hour1 = new Hour(18, 30, 6, 2005);
        Hour hour2 = new Hour(20, 30, 6, 2005);
        double millis1 = hour1.getFirstMillisecond();
        double millis2 = hour2.getFirstMillisecond();
        Marker cooling = new IntervalMarker(millis1, millis2);
        cooling.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        cooling.setPaint(new Color(150, 150, 255));
        cooling.setLabel("Automatic Cooling");
        cooling.setLabelFont(new Font("SansSerif", Font.PLAIN, 11));
        cooling.setLabelPaint(Color.blue);
        cooling.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        cooling.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addDomainMarker(cooling, Layer.BACKGROUND);

        Marker coolingStart = new ValueMarker(millis1, Color.blue,
                new BasicStroke(2.0f));
        Marker coolingEnd = new ValueMarker(millis2, Color.blue,
                new BasicStroke(2.0f));
        plot.addDomainMarker(coolingStart, Layer.BACKGROUND);
        plot.addDomainMarker(coolingEnd, Layer.BACKGROUND);
        return chart;

    }

    /**
     * Returns a sample dataset.
     *
     * @return A sample dataset.
     */
    private static XYDataset createDataset() {
        TimeSeriesCollection result = new TimeSeriesCollection();
        TimeSeries series1 = new TimeSeries("Temperature");
        series1.add(new Hour(0, 30, 6, 2005), 45.3);
        series1.add(new Hour(1, 30, 6, 2005), 48.9);
        series1.add(new Hour(2, 30, 6, 2005), 52.1);
        series1.add(new Hour(3, 30, 6, 2005), 44.8);
        series1.add(new Hour(4, 30, 6, 2005), 49.9);
        series1.add(new Hour(5, 30, 6, 2005), 55.5);
        series1.add(new Hour(6, 30, 6, 2005), 58.2);
        series1.add(new Hour(7, 30, 6, 2005), 58.1);
        series1.add(new Hour(8, 30, 6, 2005), 63.7);
        series1.add(new Hour(9, 30, 6, 2005), 66.3);
        series1.add(new Hour(10, 30, 6, 2005), 69.8);
        series1.add(new Hour(11, 30, 6, 2005), 70.1);
        series1.add(new Hour(12, 30, 6, 2005), 72.4);
        series1.add(new Hour(13, 30, 6, 2005), 69.7);
        series1.add(new Hour(14, 30, 6, 2005), 68.6);
        series1.add(new Hour(15, 30, 6, 2005), 70.9);
        series1.add(new Hour(16, 30, 6, 2005), 73.4);
        series1.add(new Hour(17, 30, 6, 2005), 77.5);
        series1.add(new Hour(18, 30, 6, 2005), 82.9);
        series1.add(new Hour(19, 30, 6, 2005), 62.1);
        series1.add(new Hour(20, 30, 6, 2005), 37.3);
        series1.add(new Hour(21, 30, 6, 2005), 40.7);
        series1.add(new Hour(22, 30, 6, 2005), 44.2);
        series1.add(new Hour(23, 30, 6, 2005), 49.8);
        result.addSeries(series1);
        return result;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demo application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        MarkerDemo2 demo = new MarkerDemo2("JFreeChart: MarkerDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}

