/* ---------------------
 * TimeSeriesDemo13.java
 * ---------------------
 * (C) Copyright 2004-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * This demo shows two charts that use weekly data.  A custom tick unit
 * collection is defined to control the domain axis formatting.
 */
public class TimeSeriesDemo13 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public TimeSeriesDemo13(String title) {

        super(title);
        setContentPane(createDemoPanel());

    }

    /**
     * Creates a chart.
     *
     * @param dataset  a dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Weekly Data",
            "Date",
            "Value",
            dataset,
            true,
            true,
            false
        );


        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer renderer = plot.getRenderer();
        if (renderer instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer rr = (XYLineAndShapeRenderer) renderer;
            rr.setBaseShapesVisible(true);
            rr.setBaseShapesFilled(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        TickUnits standardUnits = new TickUnits();
        standardUnits.add(new DateTickUnit(DateTickUnitType.DAY, 1,
                new SimpleDateFormat("MMM dd ''yy")));
        standardUnits.add(new DateTickUnit(DateTickUnitType.DAY, 7,
                new SimpleDateFormat("MMM dd ''yy")));
        standardUnits.add(new DateTickUnit(DateTickUnitType.MONTH, 1,
                new SimpleDateFormat("MMM ''yy")));
        axis.setStandardTickUnits(standardUnits);

        return chart;

    }

    /**
     * Creates a dataset containing random values at weekly intervals.
     *
     * @param items  the number of items in the dataset.
     *
     * @return the dataset.
     */
    private static XYDataset createDataset(int items) {

        TimeSeries s1 = new TimeSeries("Random Data");
        RegularTimePeriod t = new Week();
        double v = 100.0;
        for (int i = 0; i < items; i++) {
            s1.add(t, v);
            v = v * (1 + ((Math.random() - 0.499) / 100.0));
            t = t.next();
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(s1);
        return dataset;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        XYDataset dataset1 = createDataset(26);
        JFreeChart chart1 = createChart(dataset1);
        ChartPanel chartPanel1 = new ChartPanel(chart1);

        XYDataset dataset2 = createDataset(1);
        JFreeChart chart2 = createChart(dataset2);
        ChartPanel chartPanel2 = new ChartPanel(chart2);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Chart 1", chartPanel1);
        tabs.add("Chart 2", chartPanel2);
        JPanel content = new JPanel(new BorderLayout());
        content.setPreferredSize(new java.awt.Dimension(500, 270));
        content.add(tabs);
        return content;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        TimeSeriesDemo13 demo = new TimeSeriesDemo13("Time Series Demo 13");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
