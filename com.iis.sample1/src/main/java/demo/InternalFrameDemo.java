/* ----------------------
 * InternalFrameDemo.java
 * ----------------------
 * (C) Copyright 2003-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Dimension;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple internal frame demo.
 */
public class InternalFrameDemo extends ApplicationFrame {

    /**
     * Creates a new instance of the demo.
     *
     * @param title  the title.
     */
    public InternalFrameDemo(String title) {
        super(title);
        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setPreferredSize(new Dimension(600, 400));
        JInternalFrame frame1 = createFrame1();
        desktopPane.add(frame1);
        frame1.pack();
        frame1.setVisible(true);
        JInternalFrame frame2 = createFrame2();
        desktopPane.add(frame2);
        frame2.pack();
        frame2.setLocation(100, 200);
        frame2.setVisible(true);
        getContentPane().add(desktopPane);
    }

    /**
     * Creates an internal frame.
     *
     * @return An internal frame.
     */
    private JInternalFrame createFrame1() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(34.0, "Series 1", "Category 1");
        dataset.addValue(23.0, "Series 1", "Category 2");
        dataset.addValue(54.0, "Series 1", "Category 3");
        JFreeChart chart = ChartFactory.createBarChart(
            "Bar Chart",
            "Category",
            "Series",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(200, 100));
        JInternalFrame frame = new JInternalFrame("Frame 1", true);
        frame.getContentPane().add(chartPanel);
        return frame;

    }

    /**
     * Creates an internal frame.
     *
     * @return An internal frame.
     */
    private JInternalFrame createFrame2() {
        XYDataset dataset1 = createDataset("Series 1", 100.0, new Minute(), 200);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Time Series Chart",
            "Time of Day",
            "Value",
            dataset1,
            true,
            true,
            false
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(200, 100));
        JInternalFrame frame = new JInternalFrame("Frame 2", true);
        frame.getContentPane().add(chartPanel);
        return frame;
    }

    /**
     * Creates a sample dataset.
     *
     * @param name  the dataset name.
     * @param base  the starting value.
     * @param start  the starting period.
     * @param count  the number of values to generate.
     *
     * @return The dataset.
     */
    private XYDataset createDataset(String name,
                                    double base,
                                    RegularTimePeriod start,
                                    int count) {

        TimeSeries series = new TimeSeries(name);
        RegularTimePeriod period = start;
        double value = base;
        for (int i = 0; i < count; i++) {
            series.add(period, value);
            period = period.next();
            value = value * (1 + (Math.random() - 0.495) / 10.0);
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        return dataset;

    }

    /**
     * The starting point for the demo.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        InternalFrameDemo demo = new InternalFrameDemo("Internal Frame Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
