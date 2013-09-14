/* -------------------------
 * DifferenceChartDemo2.java
 * -------------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDifferenceRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.ui.TextAnchor;

/**
 * A simple demonstration of the {@link XYDifferenceRenderer}.  The sunrise
 * and sunset times for this demo were obtained from:
 *
 *     http://www.sunrisesunset.com/
 *
 */
public class DifferenceChartDemo2 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public DifferenceChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static XYDataset createDataset() {
        TimeSeries series1 = createSunriseSeries();
        TimeSeries series2 = createSunsetSeries();
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    /**
     * A utility method for creating a value based on a time.
     *
     * @param hour  the hour.
     * @param min  the minute.
     *
     * @return a value.
     */
    private static Long time(int hour, int min) {
        Minute m = new Minute(min, hour, 1, 1, 1970);
        return new Long(m.getFirstMillisecond());
    }

    /**
     * Creates a time series containing sunrise times for London in 2004.
     *
     * @return A time series containing sunrise times.
     */
    public static TimeSeries createSunriseSeries() {

        TimeSeries series = new TimeSeries("Sunrise");

        series.add(new Day(1, 1, 2004), time(8, 5));
        series.add(new Day(2, 1, 2004), time(8, 5));
        series.add(new Day(3, 1, 2004), time(8, 5));
        series.add(new Day(4, 1, 2004), time(8, 5));
        series.add(new Day(5, 1, 2004), time(8, 5));
        series.add(new Day(6, 1, 2004), time(8, 4));
        series.add(new Day(7, 1, 2004), time(8, 4));
        series.add(new Day(8, 1, 2004), time(8, 4));
        series.add(new Day(9, 1, 2004), time(8, 3));
        series.add(new Day(10, 1, 2004), time(8, 3));
        series.add(new Day(11, 1, 2004), time(8, 2));
        series.add(new Day(12, 1, 2004), time(8, 1));
        series.add(new Day(13, 1, 2004), time(8, 1));
        series.add(new Day(14, 1, 2004), time(8, 0));
        series.add(new Day(15, 1, 2004), time(7, 59));
        series.add(new Day(16, 1, 2004), time(7, 58));
        series.add(new Day(17, 1, 2004), time(7, 58));
        series.add(new Day(18, 1, 2004), time(7, 57));
        series.add(new Day(19, 1, 2004), time(7, 56));
        series.add(new Day(20, 1, 2004), time(7, 55));
        series.add(new Day(21, 1, 2004), time(7, 54));
        series.add(new Day(22, 1, 2004), time(7, 53));
        series.add(new Day(23, 1, 2004), time(7, 51));
        series.add(new Day(24, 1, 2004), time(7, 50));
        series.add(new Day(25, 1, 2004), time(7, 49));
        series.add(new Day(26, 1, 2004), time(7, 48));
        series.add(new Day(27, 1, 2004), time(7, 46));
        series.add(new Day(28, 1, 2004), time(7, 45));
        series.add(new Day(29, 1, 2004), time(7, 44));
        series.add(new Day(30, 1, 2004), time(7, 42));
        series.add(new Day(31, 1, 2004), time(7, 41));

        series.add(new Day(1, 2, 2004), time(7, 39));
        series.add(new Day(2, 2, 2004), time(7, 38));
        series.add(new Day(3, 2, 2004), time(7, 36));
        series.add(new Day(4, 2, 2004), time(7, 35));
        series.add(new Day(5, 2, 2004), time(7, 33));
        series.add(new Day(6, 2, 2004), time(7, 31));
        series.add(new Day(7, 2, 2004), time(7, 30));
        series.add(new Day(8, 2, 2004), time(7, 28));
        series.add(new Day(9, 2, 2004), time(7, 26));
        series.add(new Day(10, 2, 2004), time(7, 25));
        series.add(new Day(11, 2, 2004), time(7, 23));
        series.add(new Day(12, 2, 2004), time(7, 21));
        series.add(new Day(13, 2, 2004), time(7, 19));
        series.add(new Day(14, 2, 2004), time(7, 17));
        series.add(new Day(15, 2, 2004), time(7, 15));
        series.add(new Day(16, 2, 2004), time(7, 13));
        series.add(new Day(17, 2, 2004), time(7, 11));
        series.add(new Day(18, 2, 2004), time(7, 10));
        series.add(new Day(19, 2, 2004), time(7, 8));
        series.add(new Day(20, 2, 2004), time(7, 6));
        series.add(new Day(21, 2, 2004), time(7, 4));
        series.add(new Day(22, 2, 2004), time(7, 2));
        series.add(new Day(23, 2, 2004), time(6, 59));
        series.add(new Day(24, 2, 2004), time(6, 57));
        series.add(new Day(25, 2, 2004), time(6, 55));
        series.add(new Day(26, 2, 2004), time(6, 53));
        series.add(new Day(27, 2, 2004), time(6, 51));
        series.add(new Day(28, 2, 2004), time(6, 49));
        series.add(new Day(29, 2, 2004), time(6, 47));

        series.add(new Day(1, 3, 2004), time(6, 45));
        series.add(new Day(2, 3, 2004), time(6, 43));
        series.add(new Day(3, 3, 2004), time(6, 40));
        series.add(new Day(4, 3, 2004), time(6, 38));
        series.add(new Day(5, 3, 2004), time(6, 36));
        series.add(new Day(6, 3, 2004), time(6, 34));
        series.add(new Day(7, 3, 2004), time(6, 32));
        series.add(new Day(8, 3, 2004), time(6, 29));
        series.add(new Day(9, 3, 2004), time(6, 27));
        series.add(new Day(10, 3, 2004), time(6, 25));
        series.add(new Day(11, 3, 2004), time(6, 23));
        series.add(new Day(12, 3, 2004), time(6, 20));
        series.add(new Day(13, 3, 2004), time(6, 18));
        series.add(new Day(14, 3, 2004), time(6, 16));
        series.add(new Day(15, 3, 2004), time(6, 14));
        series.add(new Day(16, 3, 2004), time(6, 11));
        series.add(new Day(17, 3, 2004), time(6, 9));
        series.add(new Day(18, 3, 2004), time(6, 7));
        series.add(new Day(19, 3, 2004), time(6, 5));
        series.add(new Day(20, 3, 2004), time(6, 2));
        series.add(new Day(21, 3, 2004), time(6, 0));
        series.add(new Day(22, 3, 2004), time(5, 58));
        series.add(new Day(23, 3, 2004), time(5, 55));
        series.add(new Day(24, 3, 2004), time(5, 53));
        series.add(new Day(25, 3, 2004), time(5, 51));
        series.add(new Day(26, 3, 2004), time(5, 49));
        series.add(new Day(27, 3, 2004), time(5, 46));
        series.add(new Day(28, 3, 2004), time(6, 44));
        series.add(new Day(29, 3, 2004), time(6, 42));
        series.add(new Day(30, 3, 2004), time(6, 39));
        series.add(new Day(31, 3, 2004), time(6, 37));

        series.add(new Day(1, 4, 2004), time(6, 35));
        series.add(new Day(2, 4, 2004), time(6, 33));
        series.add(new Day(3, 4, 2004), time(6, 30));
        series.add(new Day(4, 4, 2004), time(6, 28));
        series.add(new Day(5, 4, 2004), time(6, 26));
        series.add(new Day(6, 4, 2004), time(6, 24));
        series.add(new Day(7, 4, 2004), time(6, 21));
        series.add(new Day(8, 4, 2004), time(6, 19));
        series.add(new Day(9, 4, 2004), time(6, 17));
        series.add(new Day(10, 4, 2004), time(6, 15));
        series.add(new Day(11, 4, 2004), time(6, 12));
        series.add(new Day(12, 4, 2004), time(6, 10));
        series.add(new Day(13, 4, 2004), time(6, 8));
        series.add(new Day(14, 4, 2004), time(6, 6));
        series.add(new Day(15, 4, 2004), time(6, 4));
        series.add(new Day(16, 4, 2004), time(6, 2));
        series.add(new Day(17, 4, 2004), time(5, 59));
        series.add(new Day(18, 4, 2004), time(5, 57));
        series.add(new Day(19, 4, 2004), time(5, 55));
        series.add(new Day(20, 4, 2004), time(5, 53));
        series.add(new Day(21, 4, 2004), time(5, 51));
        series.add(new Day(22, 4, 2004), time(5, 49));
        series.add(new Day(23, 4, 2004), time(5, 47));
        series.add(new Day(24, 4, 2004), time(5, 45));
        series.add(new Day(25, 4, 2004), time(5, 43));
        series.add(new Day(26, 4, 2004), time(5, 41));
        series.add(new Day(27, 4, 2004), time(5, 39));
        series.add(new Day(28, 4, 2004), time(5, 37));
        series.add(new Day(29, 4, 2004), time(5, 35));
        series.add(new Day(30, 4, 2004), time(5, 33));

        series.add(new Day(1, 5, 2004), time(5, 31));
        series.add(new Day(2, 5, 2004), time(5, 29));
        series.add(new Day(3, 5, 2004), time(5, 28));
        series.add(new Day(4, 5, 2004), time(5, 26));
        series.add(new Day(5, 5, 2004), time(5, 24));
        series.add(new Day(6, 5, 2004), time(5, 22));
        series.add(new Day(7, 5, 2004), time(5, 20));
        series.add(new Day(8, 5, 2004), time(5, 19));
        series.add(new Day(9, 5, 2004), time(5, 17));
        series.add(new Day(10, 5, 2004), time(5, 15));
        series.add(new Day(11, 5, 2004), time(5, 14));
        series.add(new Day(12, 5, 2004), time(5, 12));
        series.add(new Day(13, 5, 2004), time(5, 11));
        series.add(new Day(14, 5, 2004), time(5, 9));
        series.add(new Day(15, 5, 2004), time(5, 8));
        series.add(new Day(16, 5, 2004), time(5, 6));
        series.add(new Day(17, 5, 2004), time(5, 5));
        series.add(new Day(18, 5, 2004), time(5, 3));
        series.add(new Day(19, 5, 2004), time(5, 2));
        series.add(new Day(20, 5, 2004), time(5, 1));
        series.add(new Day(21, 5, 2004), time(4, 59));
        series.add(new Day(22, 5, 2004), time(4, 58));
        series.add(new Day(23, 5, 2004), time(4, 57));
        series.add(new Day(24, 5, 2004), time(4, 56));
        series.add(new Day(25, 5, 2004), time(4, 55));
        series.add(new Day(26, 5, 2004), time(4, 53));
        series.add(new Day(27, 5, 2004), time(4, 52));
        series.add(new Day(28, 5, 2004), time(4, 51));
        series.add(new Day(29, 5, 2004), time(4, 50));
        series.add(new Day(30, 5, 2004), time(4, 50));
        series.add(new Day(31, 5, 2004), time(4, 49));

        series.add(new Day(1, 6, 2004), time(4, 48));
        series.add(new Day(2, 6, 2004), time(4, 47));
        series.add(new Day(3, 6, 2004), time(4, 46));
        series.add(new Day(4, 6, 2004), time(4, 46));
        series.add(new Day(5, 6, 2004), time(4, 45));
        series.add(new Day(6, 6, 2004), time(4, 45));
        series.add(new Day(7, 6, 2004), time(4, 44));
        series.add(new Day(8, 6, 2004), time(4, 44));
        series.add(new Day(9, 6, 2004), time(4, 43));
        series.add(new Day(10, 6, 2004), time(4, 43));
        series.add(new Day(11, 6, 2004), time(4, 42));
        series.add(new Day(12, 6, 2004), time(4, 42));
        series.add(new Day(13, 6, 2004), time(4, 42));
        series.add(new Day(14, 6, 2004), time(4, 42));
        series.add(new Day(15, 6, 2004), time(4, 42));
        series.add(new Day(16, 6, 2004), time(4, 42));
        series.add(new Day(17, 6, 2004), time(4, 42));
        series.add(new Day(18, 6, 2004), time(4, 42));
        series.add(new Day(19, 6, 2004), time(4, 42));
        series.add(new Day(20, 6, 2004), time(4, 42));
        series.add(new Day(21, 6, 2004), time(4, 42));
        series.add(new Day(22, 6, 2004), time(4, 42));
        series.add(new Day(23, 6, 2004), time(4, 43));
        series.add(new Day(24, 6, 2004), time(4, 43));
        series.add(new Day(25, 6, 2004), time(4, 43));
        series.add(new Day(26, 6, 2004), time(4, 44));
        series.add(new Day(27, 6, 2004), time(4, 44));
        series.add(new Day(28, 6, 2004), time(4, 45));
        series.add(new Day(29, 6, 2004), time(4, 45));
        series.add(new Day(30, 6, 2004), time(4, 46));

        series.add(new Day(1, 7, 2004), time(4, 47));
        series.add(new Day(2, 7, 2004), time(4, 47));
        series.add(new Day(3, 7, 2004), time(4, 48));
        series.add(new Day(4, 7, 2004), time(4, 49));
        series.add(new Day(5, 7, 2004), time(4, 50));
        series.add(new Day(6, 7, 2004), time(4, 51));
        series.add(new Day(7, 7, 2004), time(4, 51));
        series.add(new Day(8, 7, 2004), time(4, 52));
        series.add(new Day(9, 7, 2004), time(4, 53));
        series.add(new Day(10, 7, 2004), time(4, 54));
        series.add(new Day(11, 7, 2004), time(4, 55));
        series.add(new Day(12, 7, 2004), time(4, 57));
        series.add(new Day(13, 7, 2004), time(4, 58));
        series.add(new Day(14, 7, 2004), time(4, 59));
        series.add(new Day(15, 7, 2004), time(5, 0));
        series.add(new Day(16, 7, 2004), time(5, 1));
        series.add(new Day(17, 7, 2004), time(5, 2));
        series.add(new Day(18, 7, 2004), time(5, 4));
        series.add(new Day(19, 7, 2004), time(5, 5));
        series.add(new Day(20, 7, 2004), time(5, 6));
        series.add(new Day(21, 7, 2004), time(5, 8));
        series.add(new Day(22, 7, 2004), time(5, 9));
        series.add(new Day(23, 7, 2004), time(5, 10));
        series.add(new Day(24, 7, 2004), time(5, 12));
        series.add(new Day(25, 7, 2004), time(5, 13));
        series.add(new Day(26, 7, 2004), time(5, 14));
        series.add(new Day(27, 7, 2004), time(5, 16));
        series.add(new Day(28, 7, 2004), time(5, 17));
        series.add(new Day(29, 7, 2004), time(5, 19));
        series.add(new Day(30, 7, 2004), time(5, 20));
        series.add(new Day(31, 7, 2004), time(5, 22));

        series.add(new Day(1, 8, 2004), time(5, 23));
        series.add(new Day(2, 8, 2004), time(5, 25));
        series.add(new Day(3, 8, 2004), time(5, 26));
        series.add(new Day(4, 8, 2004), time(5, 28));
        series.add(new Day(5, 8, 2004), time(5, 29));
        series.add(new Day(6, 8, 2004), time(5, 31));
        series.add(new Day(7, 8, 2004), time(5, 32));
        series.add(new Day(8, 8, 2004), time(5, 34));
        series.add(new Day(9, 8, 2004), time(5, 36));
        series.add(new Day(10, 8, 2004), time(5, 37));
        series.add(new Day(11, 8, 2004), time(5, 39));
        series.add(new Day(12, 8, 2004), time(5, 40));
        series.add(new Day(13, 8, 2004), time(5, 42));
        series.add(new Day(14, 8, 2004), time(5, 43));
        series.add(new Day(15, 8, 2004), time(5, 45));
        series.add(new Day(16, 8, 2004), time(5, 47));
        series.add(new Day(17, 8, 2004), time(5, 48));
        series.add(new Day(18, 8, 2004), time(5, 50));
        series.add(new Day(19, 8, 2004), time(5, 51));
        series.add(new Day(20, 8, 2004), time(5, 53));
        series.add(new Day(21, 8, 2004), time(5, 55));
        series.add(new Day(22, 8, 2004), time(5, 56));
        series.add(new Day(23, 8, 2004), time(5, 58));
        series.add(new Day(24, 8, 2004), time(5, 59));
        series.add(new Day(25, 8, 2004), time(6, 1));
        series.add(new Day(26, 8, 2004), time(6, 3));
        series.add(new Day(27, 8, 2004), time(6, 4));
        series.add(new Day(28, 8, 2004), time(6, 6));
        series.add(new Day(29, 8, 2004), time(6, 7));
        series.add(new Day(30, 8, 2004), time(6, 9));
        series.add(new Day(31, 8, 2004), time(6, 10));

        series.add(new Day(1, 9, 2004), time(6, 12));
        series.add(new Day(2, 9, 2004), time(6, 14));
        series.add(new Day(3, 9, 2004), time(6, 15));
        series.add(new Day(4, 9, 2004), time(6, 17));
        series.add(new Day(5, 9, 2004), time(6, 18));
        series.add(new Day(6, 9, 2004), time(6, 20));
        series.add(new Day(7, 9, 2004), time(6, 22));
        series.add(new Day(8, 9, 2004), time(6, 23));
        series.add(new Day(9, 9, 2004), time(6, 25));
        series.add(new Day(10, 9, 2004), time(6, 26));
        series.add(new Day(11, 9, 2004), time(6, 28));
        series.add(new Day(12, 9, 2004), time(6, 30));
        series.add(new Day(13, 9, 2004), time(6, 31));
        series.add(new Day(14, 9, 2004), time(6, 33));
        series.add(new Day(15, 9, 2004), time(6, 34));
        series.add(new Day(16, 9, 2004), time(6, 36));
        series.add(new Day(17, 9, 2004), time(6, 38));
        series.add(new Day(18, 9, 2004), time(6, 39));
        series.add(new Day(19, 9, 2004), time(6, 41));
        series.add(new Day(20, 9, 2004), time(6, 42));
        series.add(new Day(21, 9, 2004), time(6, 44));
        series.add(new Day(22, 9, 2004), time(6, 46));
        series.add(new Day(23, 9, 2004), time(6, 47));
        series.add(new Day(24, 9, 2004), time(6, 49));
        series.add(new Day(25, 9, 2004), time(6, 50));
        series.add(new Day(26, 9, 2004), time(6, 52));
        series.add(new Day(27, 9, 2004), time(6, 54));
        series.add(new Day(28, 9, 2004), time(6, 55));
        series.add(new Day(29, 9, 2004), time(6, 57));
        series.add(new Day(30, 9, 2004), time(6, 58));

        series.add(new Day(1, 10, 2004), time(7, 0));
        series.add(new Day(2, 10, 2004), time(7, 2));
        series.add(new Day(3, 10, 2004), time(7, 3));
        series.add(new Day(4, 10, 2004), time(7, 5));
        series.add(new Day(5, 10, 2004), time(7, 7));
        series.add(new Day(6, 10, 2004), time(7, 8));
        series.add(new Day(7, 10, 2004), time(7, 10));
        series.add(new Day(8, 10, 2004), time(7, 12));
        series.add(new Day(9, 10, 2004), time(7, 13));
        series.add(new Day(10, 10, 2004), time(7, 15));
        series.add(new Day(11, 10, 2004), time(7, 17));
        series.add(new Day(12, 10, 2004), time(7, 18));
        series.add(new Day(13, 10, 2004), time(7, 20));
        series.add(new Day(14, 10, 2004), time(7, 22));
        series.add(new Day(15, 10, 2004), time(7, 23));
        series.add(new Day(16, 10, 2004), time(7, 25));
        series.add(new Day(17, 10, 2004), time(7, 27));
        series.add(new Day(18, 10, 2004), time(7, 29));
        series.add(new Day(19, 10, 2004), time(7, 30));
        series.add(new Day(20, 10, 2004), time(7, 32));
        series.add(new Day(21, 10, 2004), time(7, 34));
        series.add(new Day(22, 10, 2004), time(7, 35));
        series.add(new Day(23, 10, 2004), time(7, 37));
        series.add(new Day(24, 10, 2004), time(7, 39));
        series.add(new Day(25, 10, 2004), time(7, 41));
        series.add(new Day(26, 10, 2004), time(7, 42));
        series.add(new Day(27, 10, 2004), time(7, 44));
        series.add(new Day(28, 10, 2004), time(7, 46));
        series.add(new Day(29, 10, 2004), time(7, 48));
        series.add(new Day(30, 10, 2004), time(7, 49));
        series.add(new Day(31, 10, 2004), time(6, 51));

        series.add(new Day(1, 11, 2004), time(6, 53));
        series.add(new Day(2, 11, 2004), time(6, 55));
        series.add(new Day(3, 11, 2004), time(6, 57));
        series.add(new Day(4, 11, 2004), time(6, 58));
        series.add(new Day(5, 11, 2004), time(7, 0));
        series.add(new Day(6, 11, 2004), time(7, 2));
        series.add(new Day(7, 11, 2004), time(7, 4));
        series.add(new Day(8, 11, 2004), time(7, 5));
        series.add(new Day(9, 11, 2004), time(7, 7));
        series.add(new Day(10, 11, 2004), time(7, 9));
        series.add(new Day(11, 11, 2004), time(7, 11));
        series.add(new Day(12, 11, 2004), time(7, 12));
        series.add(new Day(13, 11, 2004), time(7, 14));
        series.add(new Day(14, 11, 2004), time(7, 16));
        series.add(new Day(15, 11, 2004), time(7, 17));
        series.add(new Day(16, 11, 2004), time(7, 19));
        series.add(new Day(17, 11, 2004), time(7, 21));
        series.add(new Day(18, 11, 2004), time(7, 23));
        series.add(new Day(19, 11, 2004), time(7, 24));
        series.add(new Day(20, 11, 2004), time(7, 26));
        series.add(new Day(21, 11, 2004), time(7, 28));
        series.add(new Day(22, 11, 2004), time(7, 29));
        series.add(new Day(23, 11, 2004), time(7, 31));
        series.add(new Day(24, 11, 2004), time(7, 32));
        series.add(new Day(25, 11, 2004), time(7, 34));
        series.add(new Day(26, 11, 2004), time(7, 35));
        series.add(new Day(27, 11, 2004), time(7, 37));
        series.add(new Day(28, 11, 2004), time(7, 38));
        series.add(new Day(29, 11, 2004), time(7, 40));
        series.add(new Day(30, 11, 2004), time(7, 41));

        series.add(new Day(1, 12, 2004), time(7, 43));
        series.add(new Day(2, 12, 2004), time(7, 44));
        series.add(new Day(3, 12, 2004), time(7, 45));
        series.add(new Day(4, 12, 2004), time(7, 47));
        series.add(new Day(5, 12, 2004), time(7, 48));
        series.add(new Day(6, 12, 2004), time(7, 49));
        series.add(new Day(7, 12, 2004), time(7, 51));
        series.add(new Day(8, 12, 2004), time(7, 52));
        series.add(new Day(9, 12, 2004), time(7, 53));
        series.add(new Day(10, 12, 2004), time(7, 54));
        series.add(new Day(11, 12, 2004), time(7, 55));
        series.add(new Day(12, 12, 2004), time(7, 56));
        series.add(new Day(13, 12, 2004), time(7, 57));
        series.add(new Day(14, 12, 2004), time(7, 58));
        series.add(new Day(15, 12, 2004), time(7, 59));
        series.add(new Day(16, 12, 2004), time(7, 59));
        series.add(new Day(17, 12, 2004), time(8, 0));
        series.add(new Day(18, 12, 2004), time(8, 1));
        series.add(new Day(19, 12, 2004), time(8, 2));
        series.add(new Day(20, 12, 2004), time(8, 2));
        series.add(new Day(21, 12, 2004), time(8, 3));
        series.add(new Day(22, 12, 2004), time(8, 3));
        series.add(new Day(23, 12, 2004), time(8, 4));
        series.add(new Day(24, 12, 2004), time(8, 4));
        series.add(new Day(25, 12, 2004), time(8, 4));
        series.add(new Day(26, 12, 2004), time(8, 5));
        series.add(new Day(27, 12, 2004), time(8, 5));
        series.add(new Day(28, 12, 2004), time(8, 5));
        series.add(new Day(29, 12, 2004), time(8, 5));
        series.add(new Day(30, 12, 2004), time(8, 5));
        series.add(new Day(31, 12, 2004), time(8, 5));

        return series;
    }

    /**
     * Creates a time series containing sunset times for London in 2004.
     *
     * @return A time series containing sunset times.
     */
    public static TimeSeries createSunsetSeries() {
        TimeSeries series = new TimeSeries("Sunset");

        series.add(new Day(1, 1, 2004), time(16, 0));
        series.add(new Day(2, 1, 2004), time(16, 1));
        series.add(new Day(3, 1, 2004), time(16, 2));
        series.add(new Day(4, 1, 2004), time(16, 3));
        series.add(new Day(5, 1, 2004), time(16, 4));
        series.add(new Day(6, 1, 2004), time(16, 5));
        series.add(new Day(7, 1, 2004), time(16, 7));
        series.add(new Day(8, 1, 2004), time(16, 8));
        series.add(new Day(9, 1, 2004), time(16, 9));
        series.add(new Day(10, 1, 2004), time(16, 11));
        series.add(new Day(11, 1, 2004), time(16, 12));
        series.add(new Day(12, 1, 2004), time(16, 13));
        series.add(new Day(13, 1, 2004), time(16, 15));
        series.add(new Day(14, 1, 2004), time(16, 16));
        series.add(new Day(15, 1, 2004), time(16, 18));
        series.add(new Day(16, 1, 2004), time(16, 19));
        series.add(new Day(17, 1, 2004), time(16, 21));
        series.add(new Day(18, 1, 2004), time(16, 22));
        series.add(new Day(19, 1, 2004), time(16, 24));
        series.add(new Day(20, 1, 2004), time(16, 26));
        series.add(new Day(21, 1, 2004), time(16, 27));
        series.add(new Day(22, 1, 2004), time(16, 29));
        series.add(new Day(23, 1, 2004), time(16, 31));
        series.add(new Day(24, 1, 2004), time(16, 32));
        series.add(new Day(25, 1, 2004), time(16, 34));
        series.add(new Day(26, 1, 2004), time(16, 36));
        series.add(new Day(27, 1, 2004), time(16, 38));
        series.add(new Day(28, 1, 2004), time(16, 39));
        series.add(new Day(29, 1, 2004), time(16, 41));
        series.add(new Day(30, 1, 2004), time(16, 43));
        series.add(new Day(31, 1, 2004), time(16, 45));

        series.add(new Day(1, 2, 2004), time(16, 47));
        series.add(new Day(2, 2, 2004), time(16, 48));
        series.add(new Day(3, 2, 2004), time(16, 50));
        series.add(new Day(4, 2, 2004), time(16, 52));
        series.add(new Day(5, 2, 2004), time(16, 54));
        series.add(new Day(6, 2, 2004), time(16, 56));
        series.add(new Day(7, 2, 2004), time(16, 57));
        series.add(new Day(8, 2, 2004), time(16, 59));
        series.add(new Day(9, 2, 2004), time(17, 1));
        series.add(new Day(10, 2, 2004), time(17, 3));
        series.add(new Day(11, 2, 2004), time(17, 5));
        series.add(new Day(12, 2, 2004), time(17, 7));
        series.add(new Day(13, 2, 2004), time(17, 8));
        series.add(new Day(14, 2, 2004), time(17, 10));
        series.add(new Day(15, 2, 2004), time(17, 12));
        series.add(new Day(16, 2, 2004), time(17, 14));
        series.add(new Day(17, 2, 2004), time(17, 16));
        series.add(new Day(18, 2, 2004), time(17, 17));
        series.add(new Day(19, 2, 2004), time(17, 19));
        series.add(new Day(20, 2, 2004), time(17, 21));
        series.add(new Day(21, 2, 2004), time(17, 23));
        series.add(new Day(22, 2, 2004), time(17, 25));
        series.add(new Day(23, 2, 2004), time(17, 27));
        series.add(new Day(24, 2, 2004), time(17, 28));
        series.add(new Day(25, 2, 2004), time(17, 30));
        series.add(new Day(26, 2, 2004), time(17, 32));
        series.add(new Day(27, 2, 2004), time(17, 34));
        series.add(new Day(28, 2, 2004), time(17, 35));
        series.add(new Day(29, 2, 2004), time(17, 37));

        series.add(new Day(1, 3, 2004), time(17, 39));
        series.add(new Day(2, 3, 2004), time(17, 41));
        series.add(new Day(3, 3, 2004), time(17, 43));
        series.add(new Day(4, 3, 2004), time(17, 44));
        series.add(new Day(5, 3, 2004), time(17, 46));
        series.add(new Day(6, 3, 2004), time(17, 48));
        series.add(new Day(7, 3, 2004), time(17, 50));
        series.add(new Day(8, 3, 2004), time(17, 51));
        series.add(new Day(9, 3, 2004), time(17, 53));
        series.add(new Day(10, 3, 2004), time(17, 55));
        series.add(new Day(11, 3, 2004), time(17, 56));
        series.add(new Day(12, 3, 2004), time(17, 58));
        series.add(new Day(13, 3, 2004), time(18, 0));
        series.add(new Day(14, 3, 2004), time(18, 2));
        series.add(new Day(15, 3, 2004), time(18, 3));
        series.add(new Day(16, 3, 2004), time(18, 5));
        series.add(new Day(17, 3, 2004), time(18, 7));
        series.add(new Day(18, 3, 2004), time(18, 8));
        series.add(new Day(19, 3, 2004), time(18, 10));
        series.add(new Day(20, 3, 2004), time(18, 12));
        series.add(new Day(21, 3, 2004), time(18, 13));
        series.add(new Day(22, 3, 2004), time(18, 15));
        series.add(new Day(23, 3, 2004), time(18, 17));
        series.add(new Day(24, 3, 2004), time(18, 19));
        series.add(new Day(25, 3, 2004), time(18, 20));
        series.add(new Day(26, 3, 2004), time(18, 22));
        series.add(new Day(27, 3, 2004), time(18, 24));
        series.add(new Day(28, 3, 2004), time(19, 25));
        series.add(new Day(29, 3, 2004), time(19, 27));
        series.add(new Day(30, 3, 2004), time(19, 29));
        series.add(new Day(31, 3, 2004), time(19, 30));

        series.add(new Day(1, 4, 2004), time(19, 32));
        series.add(new Day(2, 4, 2004), time(19, 34));
        series.add(new Day(3, 4, 2004), time(19, 35));
        series.add(new Day(4, 4, 2004), time(19, 37));
        series.add(new Day(5, 4, 2004), time(19, 39));
        series.add(new Day(6, 4, 2004), time(19, 40));
        series.add(new Day(7, 4, 2004), time(19, 42));
        series.add(new Day(8, 4, 2004), time(19, 44));
        series.add(new Day(9, 4, 2004), time(19, 45));
        series.add(new Day(10, 4, 2004), time(19, 47));
        series.add(new Day(11, 4, 2004), time(19, 49));
        series.add(new Day(12, 4, 2004), time(19, 50));
        series.add(new Day(13, 4, 2004), time(19, 52));
        series.add(new Day(14, 4, 2004), time(19, 54));
        series.add(new Day(15, 4, 2004), time(19, 55));
        series.add(new Day(16, 4, 2004), time(19, 57));
        series.add(new Day(17, 4, 2004), time(19, 59));
        series.add(new Day(18, 4, 2004), time(20, 0));
        series.add(new Day(19, 4, 2004), time(20, 2));
        series.add(new Day(20, 4, 2004), time(20, 4));
        series.add(new Day(21, 4, 2004), time(20, 5));
        series.add(new Day(22, 4, 2004), time(20, 7));
        series.add(new Day(23, 4, 2004), time(20, 9));
        series.add(new Day(24, 4, 2004), time(20, 10));
        series.add(new Day(25, 4, 2004), time(20, 12));
        series.add(new Day(26, 4, 2004), time(20, 14));
        series.add(new Day(27, 4, 2004), time(20, 15));
        series.add(new Day(28, 4, 2004), time(20, 17));
        series.add(new Day(29, 4, 2004), time(20, 19));
        series.add(new Day(30, 4, 2004), time(20, 20));

        series.add(new Day(1, 5, 2004), time(20, 22));
        series.add(new Day(2, 5, 2004), time(20, 24));
        series.add(new Day(3, 5, 2004), time(20, 25));
        series.add(new Day(4, 5, 2004), time(20, 27));
        series.add(new Day(5, 5, 2004), time(20, 28));
        series.add(new Day(6, 5, 2004), time(20, 30));
        series.add(new Day(7, 5, 2004), time(20, 32));
        series.add(new Day(8, 5, 2004), time(20, 33));
        series.add(new Day(9, 5, 2004), time(20, 35));
        series.add(new Day(10, 5, 2004), time(20, 36));
        series.add(new Day(11, 5, 2004), time(20, 38));
        series.add(new Day(12, 5, 2004), time(20, 40));
        series.add(new Day(13, 5, 2004), time(20, 41));
        series.add(new Day(14, 5, 2004), time(20, 43));
        series.add(new Day(15, 5, 2004), time(20, 44));
        series.add(new Day(16, 5, 2004), time(20, 46));
        series.add(new Day(17, 5, 2004), time(20, 47));
        series.add(new Day(18, 5, 2004), time(20, 49));
        series.add(new Day(19, 5, 2004), time(20, 50));
        series.add(new Day(20, 5, 2004), time(20, 51));
        series.add(new Day(21, 5, 2004), time(20, 53));
        series.add(new Day(22, 5, 2004), time(20, 54));
        series.add(new Day(23, 5, 2004), time(20, 56));
        series.add(new Day(24, 5, 2004), time(20, 57));
        series.add(new Day(25, 5, 2004), time(20, 58));
        series.add(new Day(26, 5, 2004), time(21, 0));
        series.add(new Day(27, 5, 2004), time(21, 1));
        series.add(new Day(28, 5, 2004), time(21, 2));
        series.add(new Day(29, 5, 2004), time(21, 3));
        series.add(new Day(30, 5, 2004), time(21, 4));
        series.add(new Day(31, 5, 2004), time(21, 6));

        series.add(new Day(1, 6, 2004), time(21, 7));
        series.add(new Day(2, 6, 2004), time(21, 8));
        series.add(new Day(3, 6, 2004), time(21, 9));
        series.add(new Day(4, 6, 2004), time(21, 10));
        series.add(new Day(5, 6, 2004), time(21, 11));
        series.add(new Day(6, 6, 2004), time(21, 12));
        series.add(new Day(7, 6, 2004), time(21, 13));
        series.add(new Day(8, 6, 2004), time(21, 13));
        series.add(new Day(9, 6, 2004), time(21, 14));
        series.add(new Day(10, 6, 2004), time(21, 15));
        series.add(new Day(11, 6, 2004), time(21, 16));
        series.add(new Day(12, 6, 2004), time(21, 16));
        series.add(new Day(13, 6, 2004), time(21, 17));
        series.add(new Day(14, 6, 2004), time(21, 18));
        series.add(new Day(15, 6, 2004), time(21, 18));
        series.add(new Day(16, 6, 2004), time(21, 19));
        series.add(new Day(17, 6, 2004), time(21, 19));
        series.add(new Day(18, 6, 2004), time(21, 19));
        series.add(new Day(19, 6, 2004), time(21, 20));
        series.add(new Day(20, 6, 2004), time(21, 20));
        series.add(new Day(21, 6, 2004), time(21, 20));
        series.add(new Day(22, 6, 2004), time(21, 21));
        series.add(new Day(23, 6, 2004), time(21, 21));
        series.add(new Day(24, 6, 2004), time(21, 21));
        series.add(new Day(25, 6, 2004), time(21, 21));
        series.add(new Day(26, 6, 2004), time(21, 21));
        series.add(new Day(27, 6, 2004), time(21, 21));
        series.add(new Day(28, 6, 2004), time(21, 21));
        series.add(new Day(29, 6, 2004), time(21, 21));
        series.add(new Day(30, 6, 2004), time(21, 21));

        series.add(new Day(1, 7, 2004), time(21, 20));
        series.add(new Day(2, 7, 2004), time(21, 20));
        series.add(new Day(3, 7, 2004), time(21, 19));
        series.add(new Day(4, 7, 2004), time(21, 19));
        series.add(new Day(5, 7, 2004), time(21, 18));
        series.add(new Day(6, 7, 2004), time(21, 18));
        series.add(new Day(7, 7, 2004), time(21, 17));
        series.add(new Day(8, 7, 2004), time(21, 17));
        series.add(new Day(9, 7, 2004), time(21, 16));
        series.add(new Day(10, 7, 2004), time(21, 15));
        series.add(new Day(11, 7, 2004), time(21, 14));
        series.add(new Day(12, 7, 2004), time(21, 14));
        series.add(new Day(13, 7, 2004), time(21, 13));
        series.add(new Day(14, 7, 2004), time(21, 12));
        series.add(new Day(15, 7, 2004), time(21, 11));
        series.add(new Day(16, 7, 2004), time(21, 10));
        series.add(new Day(17, 7, 2004), time(21, 9));
        series.add(new Day(18, 7, 2004), time(21, 8));
        series.add(new Day(19, 7, 2004), time(21, 7));
        series.add(new Day(20, 7, 2004), time(21, 5));
        series.add(new Day(21, 7, 2004), time(21, 4));
        series.add(new Day(22, 7, 2004), time(21, 3));
        series.add(new Day(23, 7, 2004), time(21, 2));
        series.add(new Day(24, 7, 2004), time(21, 0));
        series.add(new Day(25, 7, 2004), time(20, 59));
        series.add(new Day(26, 7, 2004), time(20, 58));
        series.add(new Day(27, 7, 2004), time(20, 56));
        series.add(new Day(28, 7, 2004), time(20, 55));
        series.add(new Day(29, 7, 2004), time(20, 53));
        series.add(new Day(30, 7, 2004), time(20, 52));
        series.add(new Day(31, 7, 2004), time(20, 50));

        series.add(new Day(1, 8, 2004), time(20, 48));
        series.add(new Day(2, 8, 2004), time(20, 47));
        series.add(new Day(3, 8, 2004), time(20, 45));
        series.add(new Day(4, 8, 2004), time(20, 43));
        series.add(new Day(5, 8, 2004), time(20, 42));
        series.add(new Day(6, 8, 2004), time(20, 40));
        series.add(new Day(7, 8, 2004), time(20, 38));
        series.add(new Day(8, 8, 2004), time(20, 36));
        series.add(new Day(9, 8, 2004), time(20, 34));
        series.add(new Day(10, 8, 2004), time(20, 33));
        series.add(new Day(11, 8, 2004), time(20, 31));
        series.add(new Day(12, 8, 2004), time(20, 29));
        series.add(new Day(13, 8, 2004), time(20, 27));
        series.add(new Day(14, 8, 2004), time(20, 25));
        series.add(new Day(15, 8, 2004), time(20, 23));
        series.add(new Day(16, 8, 2004), time(20, 21));
        series.add(new Day(17, 8, 2004), time(20, 19));
        series.add(new Day(18, 8, 2004), time(20, 17));
        series.add(new Day(19, 8, 2004), time(20, 15));
        series.add(new Day(20, 8, 2004), time(20, 13));
        series.add(new Day(21, 8, 2004), time(20, 11));
        series.add(new Day(22, 8, 2004), time(20, 9));
        series.add(new Day(23, 8, 2004), time(20, 7));
        series.add(new Day(24, 8, 2004), time(20, 4));
        series.add(new Day(25, 8, 2004), time(20, 2));
        series.add(new Day(26, 8, 2004), time(20, 0));
        series.add(new Day(27, 8, 2004), time(19, 58));
        series.add(new Day(28, 8, 2004), time(19, 56));
        series.add(new Day(29, 8, 2004), time(19, 54));
        series.add(new Day(30, 8, 2004), time(19, 51));
        series.add(new Day(31, 8, 2004), time(19, 49));

        series.add(new Day(1, 9, 2004), time(19, 47));
        series.add(new Day(2, 9, 2004), time(19, 45));
        series.add(new Day(3, 9, 2004), time(19, 42));
        series.add(new Day(4, 9, 2004), time(19, 40));
        series.add(new Day(5, 9, 2004), time(19, 38));
        series.add(new Day(6, 9, 2004), time(19, 36));
        series.add(new Day(7, 9, 2004), time(19, 33));
        series.add(new Day(8, 9, 2004), time(19, 31));
        series.add(new Day(9, 9, 2004), time(19, 29));
        series.add(new Day(10, 9, 2004), time(19, 27));
        series.add(new Day(11, 9, 2004), time(19, 24));
        series.add(new Day(12, 9, 2004), time(19, 22));
        series.add(new Day(13, 9, 2004), time(19, 20));
        series.add(new Day(14, 9, 2004), time(19, 17));
        series.add(new Day(15, 9, 2004), time(19, 15));
        series.add(new Day(16, 9, 2004), time(19, 13));
        series.add(new Day(17, 9, 2004), time(19, 11));
        series.add(new Day(18, 9, 2004), time(19, 8));
        series.add(new Day(19, 9, 2004), time(19, 6));
        series.add(new Day(20, 9, 2004), time(19, 4));
        series.add(new Day(21, 9, 2004), time(19, 1));
        series.add(new Day(22, 9, 2004), time(18, 59));
        series.add(new Day(23, 9, 2004), time(18, 57));
        series.add(new Day(24, 9, 2004), time(18, 54));
        series.add(new Day(25, 9, 2004), time(18, 52));
        series.add(new Day(26, 9, 2004), time(18, 50));
        series.add(new Day(27, 9, 2004), time(18, 47));
        series.add(new Day(28, 9, 2004), time(18, 45));
        series.add(new Day(29, 9, 2004), time(18, 43));
        series.add(new Day(30, 9, 2004), time(18, 41));

        series.add(new Day(1, 10, 2004), time(18, 38));
        series.add(new Day(2, 10, 2004), time(18, 36));
        series.add(new Day(3, 10, 2004), time(18, 34));
        series.add(new Day(4, 10, 2004), time(18, 31));
        series.add(new Day(5, 10, 2004), time(18, 29));
        series.add(new Day(6, 10, 2004), time(18, 27));
        series.add(new Day(7, 10, 2004), time(18, 25));
        series.add(new Day(8, 10, 2004), time(18, 22));
        series.add(new Day(9, 10, 2004), time(18, 20));
        series.add(new Day(10, 10, 2004), time(18, 18));
        series.add(new Day(11, 10, 2004), time(18, 16));
        series.add(new Day(12, 10, 2004), time(18, 14));
        series.add(new Day(13, 10, 2004), time(18, 11));
        series.add(new Day(14, 10, 2004), time(18, 9));
        series.add(new Day(15, 10, 2004), time(18, 7));
        series.add(new Day(16, 10, 2004), time(18, 5));
        series.add(new Day(17, 10, 2004), time(18, 3));
        series.add(new Day(18, 10, 2004), time(18, 1));
        series.add(new Day(19, 10, 2004), time(17, 59));
        series.add(new Day(20, 10, 2004), time(17, 57));
        series.add(new Day(21, 10, 2004), time(17, 55));
        series.add(new Day(22, 10, 2004), time(17, 53));
        series.add(new Day(23, 10, 2004), time(17, 50));
        series.add(new Day(24, 10, 2004), time(17, 48));
        series.add(new Day(25, 10, 2004), time(17, 46));
        series.add(new Day(26, 10, 2004), time(17, 45));
        series.add(new Day(27, 10, 2004), time(17, 43));
        series.add(new Day(28, 10, 2004), time(17, 41));
        series.add(new Day(29, 10, 2004), time(17, 39));
        series.add(new Day(30, 10, 2004), time(17, 37));
        series.add(new Day(31, 10, 2004), time(16, 35));

        series.add(new Day(1, 11, 2004), time(16, 33));
        series.add(new Day(2, 11, 2004), time(16, 31));
        series.add(new Day(3, 11, 2004), time(16, 30));
        series.add(new Day(4, 11, 2004), time(16, 28));
        series.add(new Day(5, 11, 2004), time(16, 26));
        series.add(new Day(6, 11, 2004), time(16, 24));
        series.add(new Day(7, 11, 2004), time(16, 23));
        series.add(new Day(8, 11, 2004), time(16, 21));
        series.add(new Day(9, 11, 2004), time(16, 20));
        series.add(new Day(10, 11, 2004), time(16, 18));
        series.add(new Day(11, 11, 2004), time(16, 16));
        series.add(new Day(12, 11, 2004), time(16, 15));
        series.add(new Day(13, 11, 2004), time(16, 13));
        series.add(new Day(14, 11, 2004), time(16, 12));
        series.add(new Day(15, 11, 2004), time(16, 11));
        series.add(new Day(16, 11, 2004), time(16, 9));
        series.add(new Day(17, 11, 2004), time(16, 8));
        series.add(new Day(18, 11, 2004), time(16, 7));
        series.add(new Day(19, 11, 2004), time(16, 5));
        series.add(new Day(20, 11, 2004), time(16, 4));
        series.add(new Day(21, 11, 2004), time(16, 3));
        series.add(new Day(22, 11, 2004), time(16, 2));
        series.add(new Day(23, 11, 2004), time(16, 1));
        series.add(new Day(24, 11, 2004), time(16, 0));
        series.add(new Day(25, 11, 2004), time(15, 59));
        series.add(new Day(26, 11, 2004), time(15, 58));
        series.add(new Day(27, 11, 2004), time(15, 57));
        series.add(new Day(28, 11, 2004), time(15, 56));
        series.add(new Day(29, 11, 2004), time(15, 56));
        series.add(new Day(30, 11, 2004), time(15, 55));

        series.add(new Day(1, 12, 2004), time(15, 54));
        series.add(new Day(2, 12, 2004), time(15, 54));
        series.add(new Day(3, 12, 2004), time(15, 53));
        series.add(new Day(4, 12, 2004), time(15, 53));
        series.add(new Day(5, 12, 2004), time(15, 52));
        series.add(new Day(6, 12, 2004), time(15, 52));
        series.add(new Day(7, 12, 2004), time(15, 51));
        series.add(new Day(8, 12, 2004), time(15, 51));
        series.add(new Day(9, 12, 2004), time(15, 51));
        series.add(new Day(10, 12, 2004), time(15, 51));
        series.add(new Day(11, 12, 2004), time(15, 50));
        series.add(new Day(12, 12, 2004), time(15, 50));
        series.add(new Day(13, 12, 2004), time(15, 50));
        series.add(new Day(14, 12, 2004), time(15, 50));
        series.add(new Day(15, 12, 2004), time(15, 51));
        series.add(new Day(16, 12, 2004), time(15, 51));
        series.add(new Day(17, 12, 2004), time(15, 51));
        series.add(new Day(18, 12, 2004), time(15, 51));
        series.add(new Day(19, 12, 2004), time(15, 51));
        series.add(new Day(20, 12, 2004), time(15, 52));
        series.add(new Day(21, 12, 2004), time(15, 52));
        series.add(new Day(22, 12, 2004), time(15, 53));
        series.add(new Day(23, 12, 2004), time(15, 53));
        series.add(new Day(24, 12, 2004), time(15, 54));
        series.add(new Day(25, 12, 2004), time(15, 55));
        series.add(new Day(26, 12, 2004), time(15, 55));
        series.add(new Day(27, 12, 2004), time(15, 56));
        series.add(new Day(28, 12, 2004), time(15, 57));
        series.add(new Day(29, 12, 2004), time(15, 58));
        series.add(new Day(30, 12, 2004), time(15, 59));
        series.add(new Day(31, 12, 2004), time(16, 0));

        return series;

    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Daylight Hours - London, UK",
            "Date",
            "Time",
            dataset,
            true,  // legend
            true,  // tool tips
            false  // URLs
        );
        chart.addSubtitle(new TextTitle(
                "Data source: http://www.sunrisesunset.com/",
                new Font("SansSerif", Font.PLAIN, 12)));
        XYDifferenceRenderer renderer = new XYDifferenceRenderer(Color.blue,
                Color.blue, false);
        renderer.setRoundXCoordinates(true);
        renderer.setBaseStroke(new BasicStroke(2.0f));
        renderer.setSeriesPaint(0, Color.yellow);
        renderer.setSeriesPaint(1, Color.red);
        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                "{0} : {1} = {2}", new SimpleDateFormat("d-MMM-yyyy"),
                new SimpleDateFormat("kk:mm")));

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setRenderer(renderer);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        DateAxis domainAxis = new DateAxis("Time");
        domainAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        plot.setDomainAxis(domainAxis);
        plot.setForegroundAlpha(0.5f);

        GradientPaint c = new GradientPaint(0.0f, 0.0f, Color.red, 1.0f, 1.0f,
                Color.orange);
        IntervalMarker bst = new IntervalMarker(
                new Day(28, 3, 2004).getFirstMillisecond(),
                new Day(30, 10, 2004).getFirstMillisecond(),
                c, new BasicStroke(2.0f), null, null, 1.0f);
        bst.setLabel("British Summer Time");
        bst.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        bst.setLabelFont(new Font("SansSerif", Font.ITALIC + Font.BOLD, 10));
        bst.setLabelTextAnchor(TextAnchor.BASELINE_RIGHT);
        bst.setGradientPaintTransformer(new StandardGradientPaintTransformer(
                GradientPaintTransformType.HORIZONTAL));
        plot.addDomainMarker(bst, Layer.BACKGROUND);

        DateAxis rangeAxis = new DateAxis("Time");
        rangeAxis.setLowerMargin(0.15);
        rangeAxis.setUpperMargin(0.15);
        plot.setRangeAxis(rangeAxis);
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
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
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        DifferenceChartDemo2 demo = new DifferenceChartDemo2(
                "JFreeChart: DifferenceChartDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
