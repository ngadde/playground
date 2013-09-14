/* ----------------
 * MarkerDemo1.java
 * ----------------
 * (C) Copyright 2003-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.annotations.XYDrawableAnnotation;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * This demo shows the use of domain and range markers on a time series chart.
 */
public class MarkerDemo1 extends ApplicationFrame {

    /**
     * Creates a new instance.
     *
     * @param title  the frame title.
     */
    public MarkerDemo1(String title) {
        super(title);
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
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

        JFreeChart chart = ChartFactory.createScatterPlot(
            "Marker Demo 1",
            "X",
            "Y",
            data,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        LegendTitle legend = (LegendTitle) chart.getSubtitle(0);
        legend.setPosition(RectangleEdge.RIGHT);

        // customise...
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.getRenderer().setBaseToolTipGenerator(
                StandardXYToolTipGenerator.getTimeSeriesInstance());

        // set axis margins to allow space for marker labels...
        DateAxis domainAxis = new DateAxis("Time");
        domainAxis.setUpperMargin(0.50);
        plot.setDomainAxis(domainAxis);

        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setUpperMargin(0.30);
        rangeAxis.setLowerMargin(0.50);
        

        // add a labelled marker for the bid start price...
        Marker start = new ValueMarker(200.0);
        start.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        start.setPaint(Color.green);
        start.setLabel("Bid Start Price");
        start.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        start.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addRangeMarker(start);

        // add a labelled marker for the target price...
        Marker target = new ValueMarker(175.0);
        target.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        target.setPaint(Color.red);
        target.setLabel("Target Price");
        target.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
        target.setLabelTextAnchor(TextAnchor.BOTTOM_RIGHT);
        plot.addRangeMarker(target);

        // add a labelled marker for the original closing time...
        Hour hour = new Hour(2, new Day(22, 5, 2003));
        double millis = hour.getFirstMillisecond();
        Marker originalEnd = new ValueMarker(millis);
        originalEnd.setPaint(Color.orange);
        originalEnd.setLabel("Original Close (02:00)");
        originalEnd.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        originalEnd.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addDomainMarker(originalEnd);

        // add a labelled marker for the current closing time...
        Minute min = new Minute(15, hour);
        millis = min.getFirstMillisecond();
        Marker currentEnd = new ValueMarker(millis);
        currentEnd.setPaint(Color.red);
        currentEnd.setLabel("Close Date (02:15)");
        currentEnd.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
        currentEnd.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        plot.addDomainMarker(currentEnd);

        // label the best bid with an arrow and label...
        Hour h = new Hour(2, new Day(22, 5, 2003));
        Minute m = new Minute(10, h);
        millis = m.getFirstMillisecond();
        CircleDrawer cd = new CircleDrawer(Color.red, new BasicStroke(1.0f),
                null);
        XYAnnotation bestBid = new XYDrawableAnnotation(
                millis, 163.0, 11, 11, cd);
        plot.addAnnotation(bestBid);
        XYPointerAnnotation pointer = new XYPointerAnnotation(
                "Best Bid", millis, 163.0, 3.0 * Math.PI / 4.0);
        pointer.setBaseRadius(35.0);
        pointer.setTipRadius(10.0);
        pointer.setFont(new Font("SansSerif", Font.PLAIN, 9));
        pointer.setPaint(Color.blue);
        pointer.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);
        plot.addAnnotation(pointer);

        ChartUtilities.applyCurrentTheme(chart);

        return chart;

    }

    /**
     * Returns a sample dataset.
     *
     * @return A sample dataset.
     */
    private static XYDataset createDataset() {
        TimeSeriesCollection result = new TimeSeriesCollection();
        result.addSeries(createSupplier1Bids());
        result.addSeries(createSupplier2Bids());
        return result;
    }

    /**
     * Returns a sample data series (for supplier 1).
     *
     * @return A sample data series.
     */
    private static TimeSeries createSupplier1Bids() {

        Hour hour = new Hour(1, new Day(22, 5, 2003));
        TimeSeries series1 = new TimeSeries("Supplier 1");
        series1.add(new Minute(13, hour), 200.0);
        series1.add(new Minute(14, hour), 195.0);
        series1.add(new Minute(45, hour), 190.0);
        series1.add(new Minute(46, hour), 188.0);
        series1.add(new Minute(47, hour), 185.0);
        series1.add(new Minute(52, hour), 180.0);
        return series1;

    }

    /**
     * Returns a sample data series (for supplier 2).
     *
     * @return A sample data series.
     */
    private static TimeSeries createSupplier2Bids() {
        Hour hour1 = new Hour(1, new Day(22, 5, 2003));
        Hour hour2 = (Hour) hour1.next();
        TimeSeries series2 = new TimeSeries("Supplier 2");
        series2.add(new Minute(25, hour1), 185.0);
        series2.add(new Minute(0, hour2), 175.0);
        series2.add(new Minute(5, hour2), 170.0);
        series2.add(new Minute(6, hour2), 168.0);
        series2.add(new Minute(9, hour2), 165.0);
        series2.add(new Minute(10, hour2), 163.0);
        return series2;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    /**
     * Starting point for the demo application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        MarkerDemo1 demo = new MarkerDemo1(
                "JFreeChart: MarkerDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}

