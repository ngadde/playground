/* --------------------
 * TimeSeriesDemo8.java
 * --------------------
 * (C) Copyright 2003, 2004, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.date.MonthConstants;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A time series chart.
 */
public class TimeSeriesDemo8 extends ApplicationFrame {

    /**
     * A demonstration application showing how to create a simple time series
     * chart.
     *
     * @param title  the frame title.
     */
    public TimeSeriesDemo8(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private static XYDataset createDataset() {
        TimeSeries eur = createEURTimeSeries();
        TimeSeries mav = MovingAverage.createMovingAverage(eur,
                "30 day moving average", 30, 30);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(eur);
        dataset.addSeries(mav);
        return dataset;
    }

    /**
     * Returns a time series of the daily EUR/GBP exchange rates in 2001 (to
     * date), for use in the JFreeChart demonstration application.
     * <P>
     * You wouldn't normally create a time series in this way.  Typically,
     * values would be read from a database.
     *
     * @return A time series.
     */
    private static TimeSeries createEURTimeSeries() {

        TimeSeries t1 = new TimeSeries("EUR/GBP");
        try {
            t1.add(new Day(2, MonthConstants.JANUARY, 2001), new Double(1.5788));
            t1.add(new Day(3, MonthConstants.JANUARY, 2001), new Double(1.5913));
            t1.add(new Day(4, MonthConstants.JANUARY, 2001), new Double(1.5807));
            t1.add(new Day(5, MonthConstants.JANUARY, 2001), new Double(1.5711));
            t1.add(new Day(8, MonthConstants.JANUARY, 2001), new Double(1.5778));
            t1.add(new Day(9, MonthConstants.JANUARY, 2001), new Double(1.5851));
            t1.add(new Day(10, MonthConstants.JANUARY, 2001), new Double(1.5846));
            t1.add(new Day(11, MonthConstants.JANUARY, 2001), new Double(1.5727));
            t1.add(new Day(12, MonthConstants.JANUARY, 2001), new Double(1.5585));
            t1.add(new Day(15, MonthConstants.JANUARY, 2001), new Double(1.5694));
            t1.add(new Day(16, MonthConstants.JANUARY, 2001), new Double(1.5629));
            t1.add(new Day(17, MonthConstants.JANUARY, 2001), new Double(1.5831));
            t1.add(new Day(18, MonthConstants.JANUARY, 2001), new Double(1.5624));
            t1.add(new Day(19, MonthConstants.JANUARY, 2001), new Double(1.5694));
            t1.add(new Day(22, MonthConstants.JANUARY, 2001), new Double(1.5615));
            t1.add(new Day(23, MonthConstants.JANUARY, 2001), new Double(1.5656));
            t1.add(new Day(24, MonthConstants.JANUARY, 2001), new Double(1.5795));
            t1.add(new Day(25, MonthConstants.JANUARY, 2001), new Double(1.5852));
            t1.add(new Day(26, MonthConstants.JANUARY, 2001), new Double(1.5797));
            t1.add(new Day(29, MonthConstants.JANUARY, 2001), new Double(1.5862));
            t1.add(new Day(30, MonthConstants.JANUARY, 2001), new Double(1.5803));
            t1.add(new Day(31, MonthConstants.JANUARY, 2001), new Double(1.5714));
            t1.add(new Day(1, MonthConstants.FEBRUARY, 2001), new Double(1.5717));
            t1.add(new Day(2, MonthConstants.FEBRUARY, 2001), new Double(1.5735));
            t1.add(new Day(5, MonthConstants.FEBRUARY, 2001), new Double(1.5691));
            t1.add(new Day(6, MonthConstants.FEBRUARY, 2001), new Double(1.5676));
            t1.add(new Day(7, MonthConstants.FEBRUARY, 2001), new Double(1.5677));
            t1.add(new Day(8, MonthConstants.FEBRUARY, 2001), new Double(1.5737));
            t1.add(new Day(9, MonthConstants.FEBRUARY, 2001), new Double(1.5654));
            t1.add(new Day(12, MonthConstants.FEBRUARY, 2001), new Double(1.5621));
            t1.add(new Day(13, MonthConstants.FEBRUARY, 2001), new Double(1.5761));
            t1.add(new Day(14, MonthConstants.FEBRUARY, 2001), new Double(1.5898));
            t1.add(new Day(15, MonthConstants.FEBRUARY, 2001), new Double(1.6045));
            t1.add(new Day(16, MonthConstants.FEBRUARY, 2001), new Double(1.5852));
            t1.add(new Day(19, MonthConstants.FEBRUARY, 2001), new Double(1.5704));
            t1.add(new Day(20, MonthConstants.FEBRUARY, 2001), new Double(1.5892));
            t1.add(new Day(21, MonthConstants.FEBRUARY, 2001), new Double(1.5844));
            t1.add(new Day(22, MonthConstants.FEBRUARY, 2001), new Double(1.5934));
            t1.add(new Day(23, MonthConstants.FEBRUARY, 2001), new Double(1.5951));
            t1.add(new Day(26, MonthConstants.FEBRUARY, 2001), new Double(1.5848));
            t1.add(new Day(27, MonthConstants.FEBRUARY, 2001), new Double(1.5706));
            t1.add(new Day(28, MonthConstants.FEBRUARY, 2001), new Double(1.5680));
            t1.add(new Day(1, MonthConstants.MARCH, 2001), new Double(1.5645));
            t1.add(new Day(2, MonthConstants.MARCH, 2001), new Double(1.5754));
            t1.add(new Day(5, MonthConstants.MARCH, 2001), new Double(1.5808));
            t1.add(new Day(6, MonthConstants.MARCH, 2001), new Double(1.5766));
            t1.add(new Day(7, MonthConstants.MARCH, 2001), new Double(1.5756));
            t1.add(new Day(8, MonthConstants.MARCH, 2001), new Double(1.5760));
            t1.add(new Day(9, MonthConstants.MARCH, 2001), new Double(1.5748));
            t1.add(new Day(12, MonthConstants.MARCH, 2001), new Double(1.5779));
            t1.add(new Day(13, MonthConstants.MARCH, 2001), new Double(1.5837));
            t1.add(new Day(14, MonthConstants.MARCH, 2001), new Double(1.5886));
            t1.add(new Day(15, MonthConstants.MARCH, 2001), new Double(1.5931));
            t1.add(new Day(16, MonthConstants.MARCH, 2001), new Double(1.5945));
            t1.add(new Day(19, MonthConstants.MARCH, 2001), new Double(1.5880));
            t1.add(new Day(20, MonthConstants.MARCH, 2001), new Double(1.5817));
            t1.add(new Day(21, MonthConstants.MARCH, 2001), new Double(1.5927));
            t1.add(new Day(22, MonthConstants.MARCH, 2001), new Double(1.6065));
            t1.add(new Day(23, MonthConstants.MARCH, 2001), new Double(1.6006));
            t1.add(new Day(26, MonthConstants.MARCH, 2001), new Double(1.6007));
            t1.add(new Day(27, MonthConstants.MARCH, 2001), new Double(1.5989));
            t1.add(new Day(28, MonthConstants.MARCH, 2001), new Double(1.6135));
            t1.add(new Day(29, MonthConstants.MARCH, 2001), new Double(1.6282));
            t1.add(new Day(30, MonthConstants.MARCH, 2001), new Double(1.6090));
            t1.add(new Day(2, MonthConstants.APRIL, 2001), new Double(1.6107));
            t1.add(new Day(3, MonthConstants.APRIL, 2001), new Double(1.6093));
            t1.add(new Day(4, MonthConstants.APRIL, 2001), new Double(1.5880));
            t1.add(new Day(5, MonthConstants.APRIL, 2001), new Double(1.5931));
            t1.add(new Day(6, MonthConstants.APRIL, 2001), new Double(1.5968));
            t1.add(new Day(9, MonthConstants.APRIL, 2001), new Double(1.6072));
            t1.add(new Day(10, MonthConstants.APRIL, 2001), new Double(1.6167));
            t1.add(new Day(11, MonthConstants.APRIL, 2001), new Double(1.6214));
            t1.add(new Day(12, MonthConstants.APRIL, 2001), new Double(1.6120));
            t1.add(new Day(17, MonthConstants.APRIL, 2001), new Double(1.6229));
            t1.add(new Day(18, MonthConstants.APRIL, 2001), new Double(1.6298));
            t1.add(new Day(19, MonthConstants.APRIL, 2001), new Double(1.6159));
            t1.add(new Day(20, MonthConstants.APRIL, 2001), new Double(1.5996));
            t1.add(new Day(23, MonthConstants.APRIL, 2001), new Double(1.6042));
            t1.add(new Day(24, MonthConstants.APRIL, 2001), new Double(1.6061));
            t1.add(new Day(25, MonthConstants.APRIL, 2001), new Double(1.6045));
            t1.add(new Day(26, MonthConstants.APRIL, 2001), new Double(1.5970));
            t1.add(new Day(27, MonthConstants.APRIL, 2001), new Double(1.6095));
            t1.add(new Day(30, MonthConstants.APRIL, 2001), new Double(1.6141));
            t1.add(new Day(1, MonthConstants.MAY, 2001), new Double(1.6076));
            t1.add(new Day(2, MonthConstants.MAY, 2001), new Double(1.6077));
            t1.add(new Day(3, MonthConstants.MAY, 2001), new Double(1.6035));
            t1.add(new Day(4, MonthConstants.MAY, 2001), new Double(1.6060));
            t1.add(new Day(8, MonthConstants.MAY, 2001), new Double(1.6178));
            t1.add(new Day(9, MonthConstants.MAY, 2001), new Double(1.6083));
            t1.add(new Day(10, MonthConstants.MAY, 2001), new Double(1.6107));
            t1.add(new Day(11, MonthConstants.MAY, 2001), new Double(1.6209));
            t1.add(new Day(14, MonthConstants.MAY, 2001), new Double(1.6228));
            t1.add(new Day(15, MonthConstants.MAY, 2001), new Double(1.6184));
            t1.add(new Day(16, MonthConstants.MAY, 2001), new Double(1.6167));
            t1.add(new Day(17, MonthConstants.MAY, 2001), new Double(1.6223));
            t1.add(new Day(18, MonthConstants.MAY, 2001), new Double(1.6305));
            t1.add(new Day(21, MonthConstants.MAY, 2001), new Double(1.6420));
            t1.add(new Day(22, MonthConstants.MAY, 2001), new Double(1.6484));
            t1.add(new Day(23, MonthConstants.MAY, 2001), new Double(1.6547));
            t1.add(new Day(24, MonthConstants.MAY, 2001), new Double(1.6444));
            t1.add(new Day(25, MonthConstants.MAY, 2001), new Double(1.6577));
            t1.add(new Day(29, MonthConstants.MAY, 2001), new Double(1.6606));
            t1.add(new Day(30, MonthConstants.MAY, 2001), new Double(1.6604));
            t1.add(new Day(31, MonthConstants.MAY, 2001), new Double(1.6772));
            t1.add(new Day(1, MonthConstants.JUNE, 2001), new Double(1.6717));
            t1.add(new Day(4, MonthConstants.JUNE, 2001), new Double(1.6685));
            t1.add(new Day(5, MonthConstants.JUNE, 2001), new Double(1.6621));
            t1.add(new Day(6, MonthConstants.JUNE, 2001), new Double(1.6460));
            t1.add(new Day(7, MonthConstants.JUNE, 2001), new Double(1.6333));
            t1.add(new Day(8, MonthConstants.JUNE, 2001), new Double(1.6265));
            t1.add(new Day(11, MonthConstants.JUNE, 2001), new Double(1.6311));
            t1.add(new Day(12, MonthConstants.JUNE, 2001), new Double(1.6238));
            t1.add(new Day(13, MonthConstants.JUNE, 2001), new Double(1.6300));
            t1.add(new Day(14, MonthConstants.JUNE, 2001), new Double(1.6289));
            t1.add(new Day(15, MonthConstants.JUNE, 2001), new Double(1.6276));
            t1.add(new Day(18, MonthConstants.JUNE, 2001), new Double(1.6299));
            t1.add(new Day(19, MonthConstants.JUNE, 2001), new Double(1.6353));
            t1.add(new Day(20, MonthConstants.JUNE, 2001), new Double(1.6378));
            t1.add(new Day(21, MonthConstants.JUNE, 2001), new Double(1.6567));
            t1.add(new Day(22, MonthConstants.JUNE, 2001), new Double(1.6523));
            t1.add(new Day(25, MonthConstants.JUNE, 2001), new Double(1.6418));
            t1.add(new Day(26, MonthConstants.JUNE, 2001), new Double(1.6429));
            t1.add(new Day(27, MonthConstants.JUNE, 2001), new Double(1.6439));
            t1.add(new Day(28, MonthConstants.JUNE, 2001), new Double(1.6605));
            t1.add(new Day(29, MonthConstants.JUNE, 2001), new Double(1.6599));
            t1.add(new Day(2, MonthConstants.JULY, 2001), new Double(1.6727));
            t1.add(new Day(3, MonthConstants.JULY, 2001), new Double(1.6620));
            t1.add(new Day(4, MonthConstants.JULY, 2001), new Double(1.6628));
            t1.add(new Day(5, MonthConstants.JULY, 2001), new Double(1.6730));
            t1.add(new Day(6, MonthConstants.JULY, 2001), new Double(1.6649));
            t1.add(new Day(9, MonthConstants.JULY, 2001), new Double(1.6603));
            t1.add(new Day(10, MonthConstants.JULY, 2001), new Double(1.6489));
            t1.add(new Day(11, MonthConstants.JULY, 2001), new Double(1.6421));
            t1.add(new Day(12, MonthConstants.JULY, 2001), new Double(1.6498));
            t1.add(new Day(13, MonthConstants.JULY, 2001), new Double(1.6447));
            t1.add(new Day(16, MonthConstants.JULY, 2001), new Double(1.6373));
            t1.add(new Day(17, MonthConstants.JULY, 2001), new Double(1.6443));
            t1.add(new Day(18, MonthConstants.JULY, 2001), new Double(1.6246));
            t1.add(new Day(19, MonthConstants.JULY, 2001), new Double(1.6295));
            t1.add(new Day(20, MonthConstants.JULY, 2001), new Double(1.6362));
            t1.add(new Day(23, MonthConstants.JULY, 2001), new Double(1.6348));
            t1.add(new Day(24, MonthConstants.JULY, 2001), new Double(1.6242));
            t1.add(new Day(25, MonthConstants.JULY, 2001), new Double(1.6241));
            t1.add(new Day(26, MonthConstants.JULY, 2001), new Double(1.6281));
            t1.add(new Day(27, MonthConstants.JULY, 2001), new Double(1.6296));
            t1.add(new Day(30, MonthConstants.JULY, 2001), new Double(1.6279));
            t1.add(new Day(31, MonthConstants.JULY, 2001), new Double(1.6300));
            t1.add(new Day(1, MonthConstants.AUGUST, 2001), new Double(1.6290));
            t1.add(new Day(2, MonthConstants.AUGUST, 2001), new Double(1.6237));
            t1.add(new Day(3, MonthConstants.AUGUST, 2001), new Double(1.6138));
            t1.add(new Day(6, MonthConstants.AUGUST, 2001), new Double(1.6121));
            t1.add(new Day(7, MonthConstants.AUGUST, 2001), new Double(1.6170));
            t1.add(new Day(8, MonthConstants.AUGUST, 2001), new Double(1.6135));
            t1.add(new Day(9, MonthConstants.AUGUST, 2001), new Double(1.5996));
            t1.add(new Day(10, MonthConstants.AUGUST, 2001), new Double(1.5931));
            t1.add(new Day(13, MonthConstants.AUGUST, 2001), new Double(1.5828));
            t1.add(new Day(14, MonthConstants.AUGUST, 2001), new Double(1.5824));
            t1.add(new Day(15, MonthConstants.AUGUST, 2001), new Double(1.5783));
            t1.add(new Day(16, MonthConstants.AUGUST, 2001), new Double(1.5810));
            t1.add(new Day(17, MonthConstants.AUGUST, 2001), new Double(1.5761));
            t1.add(new Day(20, MonthConstants.AUGUST, 2001), new Double(1.5831));
            t1.add(new Day(21, MonthConstants.AUGUST, 2001), new Double(1.5870));
            t1.add(new Day(22, MonthConstants.AUGUST, 2001), new Double(1.5808));
            t1.add(new Day(23, MonthConstants.AUGUST, 2001), new Double(1.5845));
            t1.add(new Day(24, MonthConstants.AUGUST, 2001), new Double(1.5844));
            t1.add(new Day(28, MonthConstants.AUGUST, 2001), new Double(1.5924));
            t1.add(new Day(29, MonthConstants.AUGUST, 2001), new Double(1.5950));
            t1.add(new Day(30, MonthConstants.AUGUST, 2001), new Double(1.5941));
            t1.add(new Day(31, MonthConstants.AUGUST, 2001), new Double(1.5968));
            t1.add(new Day(3, MonthConstants.SEPTEMBER, 2001), new Double(1.6020));
            t1.add(new Day(4, MonthConstants.SEPTEMBER, 2001), new Double(1.6236));
            t1.add(new Day(5, MonthConstants.SEPTEMBER, 2001), new Double(1.6352));
            t1.add(new Day(6, MonthConstants.SEPTEMBER, 2001), new Double(1.6302));
            t1.add(new Day(7, MonthConstants.SEPTEMBER, 2001), new Double(1.6180));
            t1.add(new Day(10, MonthConstants.SEPTEMBER, 2001), new Double(1.6218));
            t1.add(new Day(11, MonthConstants.SEPTEMBER, 2001), new Double(1.6182));
            t1.add(new Day(12, MonthConstants.SEPTEMBER, 2001), new Double(1.6157));
            t1.add(new Day(13, MonthConstants.SEPTEMBER, 2001), new Double(1.6171));
            t1.add(new Day(14, MonthConstants.SEPTEMBER, 2001), new Double(1.5960));
            t1.add(new Day(17, MonthConstants.SEPTEMBER, 2001), new Double(1.5952));
            t1.add(new Day(18, MonthConstants.SEPTEMBER, 2001), new Double(1.5863));
            t1.add(new Day(19, MonthConstants.SEPTEMBER, 2001), new Double(1.5790));
            t1.add(new Day(20, MonthConstants.SEPTEMBER, 2001), new Double(1.5811));
            t1.add(new Day(21, MonthConstants.SEPTEMBER, 2001), new Double(1.5917));
            t1.add(new Day(24, MonthConstants.SEPTEMBER, 2001), new Double(1.6005));
            t1.add(new Day(25, MonthConstants.SEPTEMBER, 2001), new Double(1.5915));
            t1.add(new Day(26, MonthConstants.SEPTEMBER, 2001), new Double(1.6012));
            t1.add(new Day(27, MonthConstants.SEPTEMBER, 2001), new Double(1.6032));
            t1.add(new Day(28, MonthConstants.SEPTEMBER, 2001), new Double(1.6133));
            t1.add(new Day(1, MonthConstants.OCTOBER, 2001), new Double(1.6147));
            t1.add(new Day(2, MonthConstants.OCTOBER, 2001), new Double(1.6002));
            t1.add(new Day(3, MonthConstants.OCTOBER, 2001), new Double(1.6041));
            t1.add(new Day(4, MonthConstants.OCTOBER, 2001), new Double(1.6172));
            t1.add(new Day(5, MonthConstants.OCTOBER, 2001), new Double(1.6121));
            t1.add(new Day(8, MonthConstants.OCTOBER, 2001), new Double(1.6044));
            t1.add(new Day(9, MonthConstants.OCTOBER, 2001), new Double(1.5974));
            t1.add(new Day(10, MonthConstants.OCTOBER, 2001), new Double(1.5915));
            t1.add(new Day(11, MonthConstants.OCTOBER, 2001), new Double(1.6022));
            t1.add(new Day(12, MonthConstants.OCTOBER, 2001), new Double(1.6014));
            t1.add(new Day(15, MonthConstants.OCTOBER, 2001), new Double(1.5942));
            t1.add(new Day(16, MonthConstants.OCTOBER, 2001), new Double(1.5925));
            t1.add(new Day(17, MonthConstants.OCTOBER, 2001), new Double(1.6007));
            t1.add(new Day(18, MonthConstants.OCTOBER, 2001), new Double(1.6000));
            t1.add(new Day(19, MonthConstants.OCTOBER, 2001), new Double(1.6030));
            t1.add(new Day(22, MonthConstants.OCTOBER, 2001), new Double(1.6014));
            t1.add(new Day(23, MonthConstants.OCTOBER, 2001), new Double(1.5995));
            t1.add(new Day(24, MonthConstants.OCTOBER, 2001), new Double(1.5951));
            t1.add(new Day(25, MonthConstants.OCTOBER, 2001), new Double(1.5953));
            t1.add(new Day(26, MonthConstants.OCTOBER, 2001), new Double(1.6057));
            t1.add(new Day(29, MonthConstants.OCTOBER, 2001), new Double(1.6051));
            t1.add(new Day(30, MonthConstants.OCTOBER, 2001), new Double(1.6027));
            t1.add(new Day(31, MonthConstants.OCTOBER, 2001), new Double(1.6144));
            t1.add(new Day(1, MonthConstants.NOVEMBER, 2001), new Double(1.6139));
            t1.add(new Day(2, MonthConstants.NOVEMBER, 2001), new Double(1.6189));
            t1.add(new Day(5, MonthConstants.NOVEMBER, 2001), new Double(1.6248));
            t1.add(new Day(6, MonthConstants.NOVEMBER, 2001), new Double(1.6267));
            t1.add(new Day(7, MonthConstants.NOVEMBER, 2001), new Double(1.6281));
            t1.add(new Day(8, MonthConstants.NOVEMBER, 2001), new Double(1.6310));
            t1.add(new Day(9, MonthConstants.NOVEMBER, 2001), new Double(1.6313));
            t1.add(new Day(12, MonthConstants.NOVEMBER, 2001), new Double(1.6272));
            t1.add(new Day(13, MonthConstants.NOVEMBER, 2001), new Double(1.6361));
            t1.add(new Day(14, MonthConstants.NOVEMBER, 2001), new Double(1.6323));
            t1.add(new Day(15, MonthConstants.NOVEMBER, 2001), new Double(1.6252));
            t1.add(new Day(16, MonthConstants.NOVEMBER, 2001), new Double(1.6141));
            t1.add(new Day(19, MonthConstants.NOVEMBER, 2001), new Double(1.6086));
            t1.add(new Day(20, MonthConstants.NOVEMBER, 2001), new Double(1.6055));
            t1.add(new Day(21, MonthConstants.NOVEMBER, 2001), new Double(1.6132));
            t1.add(new Day(22, MonthConstants.NOVEMBER, 2001), new Double(1.6074));
            t1.add(new Day(23, MonthConstants.NOVEMBER, 2001), new Double(1.6065));
            t1.add(new Day(26, MonthConstants.NOVEMBER, 2001), new Double(1.6061));
            t1.add(new Day(27, MonthConstants.NOVEMBER, 2001), new Double(1.6039));
            t1.add(new Day(28, MonthConstants.NOVEMBER, 2001), new Double(1.6069));
            t1.add(new Day(29, MonthConstants.NOVEMBER, 2001), new Double(1.6044));
            t1.add(new Day(30, MonthConstants.NOVEMBER, 2001), new Double(1.5928));
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return t1;
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return a chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Time Series Demo 8",
            "Date",
            "Value",
            dataset,
            true,
            true,
            false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis yAxis2 = new NumberAxis(null);
        yAxis2.setAutoRangeIncludesZero(false);
        plot.setRangeAxis(1, yAxis2);
        List axisIndices = Arrays.asList(new Integer[] {new Integer(0),
                new Integer(1)});
        plot.mapDatasetToRangeAxes(0, axisIndices);

        XYLineAndShapeRenderer renderer
                = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setAutoPopulateSeriesStroke(false);
        renderer.setBaseStroke(new BasicStroke(1.5f,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        renderer.setDrawSeriesLineAsPath(true);
        StandardXYToolTipGenerator g = new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00"));
        renderer.setBaseToolTipGenerator(g);
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

        TimeSeriesDemo8 demo = new TimeSeriesDemo8("Time Series Demo 8");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
