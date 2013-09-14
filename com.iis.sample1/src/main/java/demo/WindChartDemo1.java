/* -------------------
 * WindChartDemo1.java
 * -------------------
 * (C) Copyright 2003-2006, by Object Refinery Limited.
 *
 */

package demo;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultWindDataset;
import org.jfree.data.xy.WindDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a wind chart.
 */
public class WindChartDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public WindChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private static long millisForDate(int day, int month, int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day, 12, 0);
        return c.getTimeInMillis();
    }

    private static Object[] createItem(long millis, int dir, int force) {
        return new Object[] {new Date(millis), new Integer(dir),
                new Integer(force)};
    }

    /**
     * Creates a sample dataset.  The code here is intended to illustrate the
     * structure of the array, rather than the most flexible way to create
     * a new dataset.
     *
     * @return A sample dataset.
     */
    public static WindDataset createDataset() {
        Object[] item1 = createItem(millisForDate(3, 1, 1999), 0, 10);
        Object[] item2 = createItem(millisForDate(4, 1, 1999), 1, 8);
        Object[] item3 = createItem(millisForDate(5, 1, 1999), 2, 10);
        Object[] item4 = createItem(millisForDate(6, 1, 1999), 3, 10);
        Object[] item5 = createItem(millisForDate(7, 1, 1999), 4, 7);
        Object[] item6 = createItem(millisForDate(8, 1, 1999), 5, 10);
        Object[] item7 = createItem(millisForDate(9, 1, 1999), 6, 8);
        Object[] item8 = createItem(millisForDate(10, 1, 1999), 7, 11);
        Object[] item9 = createItem(millisForDate(11, 1, 1999), 8, 10);
        Object[] item10 = createItem(millisForDate(12, 1, 1999), 9, 11);
        Object[] item11 = createItem(millisForDate(13, 1, 1999), 10, 3);
        Object[] item12 = createItem(millisForDate(14, 1, 1999), 11, 9);
        Object[] item13 = createItem(millisForDate(15, 1, 1999), 12, 11);
        Object[] item14 = createItem(millisForDate(16, 1, 1999), 0, 0);
        Object[][] series1 = new Object[][] {item1, item2, item3, item4, item5,
                item6, item7, item8, item9, item10, item11, item12, item13,
                item14};

        Object[][][] data = new Object[][][] {series1};
        return new DefaultWindDataset(data);
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(WindDataset dataset) {
        JFreeChart chart = ChartFactory.createWindPlot(
            "Wind Chart Demo",
            "Date",
            "Direction / Force",
            dataset,
            true,
            false,
            false);
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
        WindChartDemo1 demo = new WindChartDemo1("Wind Chart Demo 1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
