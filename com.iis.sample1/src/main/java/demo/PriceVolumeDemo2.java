/* ---------------------
 * PriceVolumeDemo2.java
 * ---------------------
 * (C) Copyright 2007-2009, by Object Refinery Limited.
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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demonstration application showing how to create a price-volume chart.
 */
public class PriceVolumeDemo2 extends ApplicationFrame {

    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public PriceVolumeDemo2(String title) {
        super(title);
        JFreeChart chart = createChart();
        ChartPanel panel = new ChartPanel(chart, true, true, true, false, true);
        panel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(panel);
    }

    /**
     * Creates a chart.
     *
     * @return a chart.
     */
    private static JFreeChart createChart() {

        OHLCDataset priceData = createPriceDataset();
        String title = "Sun Microsystems (SUNW)";
        JFreeChart chart = ChartFactory.createHighLowChart(title, "Date",
                "Price", priceData, true);
        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis xAxis = (DateAxis) plot.getDomainAxis();
        xAxis.setLowerMargin(0.01);
        xAxis.setUpperMargin(0.01);
        NumberAxis rangeAxis1 = (NumberAxis) plot.getRangeAxis();
        rangeAxis1.setLowerMargin(0.60);  // to leave room for volume bars
        rangeAxis1.setAutoRangeIncludesZero(false);

        XYItemRenderer renderer1 = plot.getRenderer();
        renderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));

        NumberAxis rangeAxis2 = new NumberAxis("Volume");
        rangeAxis2.setUpperMargin(1.00);  // to leave room for price line
        plot.setRangeAxis(1, rangeAxis2);
        plot.setDataset(1, createVolumeDataset());
        plot.setRangeAxis(1, rangeAxis2);
        plot.mapDatasetToRangeAxis(1, 1);
        XYBarRenderer renderer2 = new XYBarRenderer();
        renderer2.setDrawBarOutline(false);
        renderer2.setBaseToolTipGenerator(
            new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("d-MMM-yyyy"),
                new DecimalFormat("0,000.00")));
        plot.setRenderer(1, renderer2);
        ChartUtilities.applyCurrentTheme(chart);
        renderer2.setShadowVisible(false);
        renderer2.setBarPainter(new StandardXYBarPainter());
        return chart;

    }

    /**
     * Creates a sample dataset.  Here the data creation is hard-coded, but
     * in a real application you would normally read the data from a database
     * or some other source.
     *
     * @return A sample dataset.
     */
    private static OHLCDataset createPriceDataset() {

        // the following data is taken from http://finance.yahoo.com/
        // for demo purposes...
        OHLCSeries s1 = new OHLCSeries("SUNW");
        s1.add(new Day(12, 4, 2007), 5.90, 5.96, 5.87, 5.90);
        s1.add(new Day(13, 4, 2007), 5.89, 5.90, 5.78, 5.80);
        s1.add(new Day(16, 4, 2007), 5.81, 5.87, 5.79, 5.86);
        s1.add(new Day(17, 4, 2007), 5.87, 5.95, 5.82, 5.95);
        s1.add(new Day(18, 4, 2007), 5.89, 5.95, 5.87, 5.94);
        s1.add(new Day(19, 4, 2007), 5.87, 5.96, 5.86, 5.89);
        s1.add(new Day(20, 4, 2007), 5.94, 5.95, 5.87, 5.93);
        s1.add(new Day(23, 4, 2007), 5.93, 5.95, 5.89, 5.92);
        s1.add(new Day(24, 4, 2007), 5.93, 5.97, 5.88, 5.94);
        s1.add(new Day(25, 4, 2007), 5.58, 5.58, 5.17, 5.27);
        s1.add(new Day(26, 4, 2007), 5.25, 5.30, 5.20, 5.25);
        s1.add(new Day(27, 4, 2007), 5.23, 5.28, 5.19, 5.26);
        s1.add(new Day(30, 4, 2007), 5.25, 5.26, 5.20, 5.22);
        s1.add(new Day(1, 5, 2007), 5.23, 5.24, 4.99, 5.09);
        s1.add(new Day(2, 5, 2007), 5.09, 5.17, 5.08, 5.15);
        s1.add(new Day(3, 5, 2007), 5.14, 5.20, 5.11, 5.18);
        s1.add(new Day(4, 5, 2007), 5.21, 5.30, 5.20, 5.24);
        s1.add(new Day(7, 5, 2007), 5.22, 5.28, 5.21, 5.22);
        s1.add(new Day(8, 5, 2007), 5.24, 5.27, 5.21, 5.22);
        s1.add(new Day(9, 5, 2007), 5.21, 5.22, 5.15, 5.20);
        s1.add(new Day(10, 5, 2007), 5.16, 5.19, 5.13, 5.13);
        s1.add(new Day(11, 5, 2007), 5.14, 5.18, 5.12, 5.15);
        s1.add(new Day(14, 5, 2007), 5.16, 5.29, 5.16, 5.22);
        s1.add(new Day(15, 5, 2007), 5.22, 5.23, 5.13, 5.14);
        s1.add(new Day(16, 5, 2007), 5.14, 5.16, 5.07, 5.12);
        s1.add(new Day(17, 5, 2007), 5.35, 5.43, 5.30, 5.30);
        s1.add(new Day(18, 5, 2007), 5.35, 5.35, 5.26, 5.29);
        s1.add(new Day(21, 5, 2007), 5.29, 5.39, 5.24, 5.39);
        s1.add(new Day(22, 5, 2007), 5.39, 5.42, 5.34, 5.38);
        s1.add(new Day(23, 5, 2007), 5.37, 5.43, 5.36, 5.38);
        s1.add(new Day(24, 5, 2007), 5.36, 5.37, 5.15, 5.15);
        s1.add(new Day(25, 5, 2007), 5.18, 5.21, 5.15, 5.16);
        s1.add(new Day(29, 5, 2007), 5.16, 5.17, 5.00, 5.06);
        s1.add(new Day(30, 5, 2007), 5.01, 5.15, 4.96, 5.12);
        s1.add(new Day(31, 5, 2007), 5.16, 5.19, 5.07, 5.10);
        s1.add(new Day(1, 6, 2007), 5.12, 5.20, 5.12, 5.18);
        s1.add(new Day(4, 6, 2007), 5.15, 5.24, 5.07, 5.11);
        s1.add(new Day(5, 6, 2007), 5.08, 5.08, 4.97, 5.07);
        s1.add(new Day(6, 6, 2007), 5.03, 5.05, 4.99, 5.02);
        s1.add(new Day(7, 6, 2007), 5.00, 5.05, 4.97, 4.97);
        s1.add(new Day(8, 6, 2007), 4.98, 5.04, 4.95, 5.04);
        s1.add(new Day(11, 6, 2007), 5.05, 5.06, 4.95, 4.96);
        s1.add(new Day(12, 6, 2007), 4.95, 5.01, 4.92, 4.92);
        s1.add(new Day(13, 6, 2007), 4.95, 4.99, 4.83, 4.99);
        s1.add(new Day(14, 6, 2007), 5.05, 5.10, 5.02, 5.08);
        s1.add(new Day(15, 6, 2007), 5.13, 5.13, 5.03, 5.05);
        s1.add(new Day(18, 6, 2007), 5.06, 5.07, 5.01, 5.05);
        s1.add(new Day(19, 6, 2007), 5.03, 5.16, 5.03, 5.10);
        s1.add(new Day(20, 6, 2007), 5.14, 5.15, 5.05, 5.05);
        s1.add(new Day(21, 6, 2007), 5.07, 5.18, 5.06, 5.13);
        s1.add(new Day(22, 6, 2007), 5.11, 5.14, 5.08, 5.08);
        s1.add(new Day(25, 6, 2007), 5.08, 5.10, 4.99, 5.02);
        s1.add(new Day(26, 6, 2007), 5.04, 5.10, 4.99, 5.01);
        s1.add(new Day(27, 6, 2007), 5.00, 5.09, 4.99, 5.07);
        s1.add(new Day(28, 6, 2007), 5.08, 5.19, 5.07, 5.16);
        s1.add(new Day(29, 6, 2007), 5.19, 5.33, 5.16, 5.26);
        s1.add(new Day(2, 7, 2007), 5.13, 5.33, 5.12, 5.19);
        s1.add(new Day(3, 7, 2007), 5.20, 5.24, 5.17, 5.22);
        s1.add(new Day(5, 7, 2007), 5.28, 5.35, 5.24, 5.33);
        s1.add(new Day(6, 7, 2007), 5.36, 5.49, 5.34, 5.38);
        s1.add(new Day(9, 7, 2007), 5.40, 5.44, 5.31, 5.33);
        s1.add(new Day(10, 7, 2007), 5.29, 5.41, 5.28, 5.32);
        s1.add(new Day(11, 7, 2007), 5.29, 5.38, 5.29, 5.38);
        s1.add(new Day(12, 7, 2007), 5.38, 5.43, 5.33, 5.43);
        s1.add(new Day(13, 7, 2007), 5.42, 5.43, 5.34, 5.37);
        s1.add(new Day(16, 7, 2007), 5.33, 5.38, 5.30, 5.34);

        OHLCSeriesCollection dataset = new OHLCSeriesCollection();
        dataset.addSeries(s1);
        return dataset;

    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static IntervalXYDataset createVolumeDataset() {

        // create dataset 2...
        TimeSeries s1 = new TimeSeries("Volume");

        s1.add(new Day(12, 4, 2007), 49646800);
        s1.add(new Day(13, 4, 2007), 67319300);
        s1.add(new Day(16, 4, 2007), 56840200);
        s1.add(new Day(17, 4, 2007), 40752300);
        s1.add(new Day(18, 4, 2007), 42533800);
        s1.add(new Day(19, 4, 2007), 34232100);
        s1.add(new Day(20, 4, 2007), 32437600);
        s1.add(new Day(23, 4, 2007), 28755900);
        s1.add(new Day(24, 4, 2007), 74503300);
        s1.add(new Day(25, 4, 2007), 322214000);
        s1.add(new Day(26, 4, 2007), 96141700);
        s1.add(new Day(27, 4, 2007), 62326200);
        s1.add(new Day(30, 4, 2007), 52334400);
        s1.add(new Day(1, 5, 2007), 133096100);
        s1.add(new Day(2, 5, 2007), 93874500);
        s1.add(new Day(3, 5, 2007), 101011400);
        s1.add(new Day(4, 5, 2007), 56629700);
        s1.add(new Day(7, 5, 2007), 43302200);
        s1.add(new Day(8, 5, 2007), 51456500);
        s1.add(new Day(9, 5, 2007), 48230900);
        s1.add(new Day(10, 5, 2007), 65536000);
        s1.add(new Day(11, 5, 2007), 46469700);
        s1.add(new Day(14, 5, 2007), 118580000);
        s1.add(new Day(15, 5, 2007), 50774100);
        s1.add(new Day(16, 5, 2007), 44590200);
        s1.add(new Day(17, 5, 2007), 125482500);
        s1.add(new Day(18, 5, 2007), 49987500);
        s1.add(new Day(21, 5, 2007), 48387400);
        s1.add(new Day(22, 5, 2007), 67992600);
        s1.add(new Day(23, 5, 2007), 45629200);
        s1.add(new Day(24, 5, 2007), 123288600);
        s1.add(new Day(25, 5, 2007), 47345300);
        s1.add(new Day(29, 5, 2007), 90454000);
        s1.add(new Day(30, 5, 2007), 73049200);
        s1.add(new Day(31, 5, 2007), 60395400);
        s1.add(new Day(1, 6, 2007), 48792300);
        s1.add(new Day(4, 6, 2007), 109972800);
        s1.add(new Day(5, 6, 2007), 198201200);
        s1.add(new Day(6, 6, 2007), 121415700);
        s1.add(new Day(7, 6, 2007), 56637400);
        s1.add(new Day(8, 6, 2007), 64116600);
        s1.add(new Day(11, 6, 2007), 60274800);
        s1.add(new Day(12, 6, 2007), 93451300);
        s1.add(new Day(13, 6, 2007), 103309000);
        s1.add(new Day(14, 6, 2007), 103135400);
        s1.add(new Day(15, 6, 2007), 104415900);
        s1.add(new Day(18, 6, 2007), 51506900);
        s1.add(new Day(19, 6, 2007), 74592100);
        s1.add(new Day(20, 6, 2007), 69027600);
        s1.add(new Day(21, 6, 2007), 97212500);
        s1.add(new Day(22, 6, 2007), 51612000);
        s1.add(new Day(25, 6, 2007), 63845400);
        s1.add(new Day(26, 6, 2007), 84818400);
        s1.add(new Day(27, 6, 2007), 73512900);
        s1.add(new Day(28, 6, 2007), 75275100);
        s1.add(new Day(29, 6, 2007), 104203900);
        s1.add(new Day(2, 7, 2007), 66540400);
        s1.add(new Day(3, 7, 2007), 28520800);
        s1.add(new Day(5, 7, 2007), 47176300);
        s1.add(new Day(6, 7, 2007), 70084800);
        s1.add(new Day(9, 7, 2007), 91630800);
        s1.add(new Day(10, 7, 2007), 114071700);
        s1.add(new Day(11, 7, 2007), 34217900);
        s1.add(new Day(12, 7, 2007), 30298000);
        s1.add(new Day(13, 7, 2007), 40423500);
        s1.add(new Day(16, 7, 2007), 39238000);

        return new TimeSeriesCollection(s1);

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart();
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the price/volume chart demo application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        PriceVolumeDemo2 demo = new PriceVolumeDemo2(
                "JFreeChart: PriceVolumeDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
