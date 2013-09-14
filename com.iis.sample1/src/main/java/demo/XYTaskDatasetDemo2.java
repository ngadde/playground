/* -----------------------
 * XYTaskDatasetDemo2.java
 * -----------------------
 * (C) Copyright 2008, 2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.gantt.XYTaskDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demonstration of the {@link XYTaskDataset} class.
 */
public class XYTaskDatasetDemo2 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYTaskDatasetDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel);
    }

    /**
     * Creates a subplot.
     *
     * @param dataset  the dataset.
     *
     * @return A subplot.
     */
    private static XYPlot createSubplot1(XYDataset dataset) {
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setUseFillPaint(true);
        renderer.setBaseFillPaint(Color.white);
        renderer.setBaseShape(new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0));
        renderer.setAutoPopulateSeriesShape(false);
        NumberAxis yAxis = new NumberAxis("Y");
        yAxis.setLowerMargin(0.1);
        yAxis.setUpperMargin(0.1);
        XYPlot plot = new XYPlot(dataset, new DateAxis("Time"), yAxis,
                renderer);
        return plot;
    }

    /**
     * Creates a subplot.
     *
     * @param dataset  the dataset.
     *
     * @return A subplot.
     */
    private static XYPlot createSubplot2(IntervalXYDataset dataset) {
        DateAxis xAxis = new DateAxis("Date/Time");
        SymbolAxis yAxis = new SymbolAxis("Resources", new String[] {"Team A",
                "Team B", "Team C", "Team D", "Team E"});
        yAxis.setGridBandsVisible(false);
        XYBarRenderer renderer = new XYBarRenderer();
        renderer.setUseYInterval(true);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        return plot;
    }

    /**
     * Creates a demo chart.
     *
     * @return A demo chart.
     */
    private static JFreeChart createChart() {
        CombinedDomainXYPlot plot = new CombinedDomainXYPlot(
                new DateAxis("Date/Time"));
        plot.setDomainPannable(true);
        plot.add(createSubplot1(createDataset1()));
        plot.add(createSubplot2(createDataset2()));
        JFreeChart chart = new JFreeChart("XYTaskDatasetDemo2", plot);
        chart.setBackgroundPaint(Color.white);
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    /**
     * Creates a panel for the demo.
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart());
    }

    /**
     * Creates a dataset for the demo.  Normally a dataset wouldn't be hard
     * coded like this - it would be read from a file or a database or some
     * other source.
     *
     * @return A dataset.
     */
    private static XYDataset createDataset1() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries s1 = new TimeSeries("Time Series 1");
        s1.add(new Hour(0, new Day()), 20214.5);
        s1.add(new Hour(4, new Day()), 73346.5);
        s1.add(new Hour(8, new Day()), 54643.6);
        s1.add(new Hour(12, new Day()), 92683.8);
        s1.add(new Hour(16, new Day()), 110235.4);
        s1.add(new Hour(20, new Day()), 120742.5);
        s1.add(new Hour(24, new Day()), 90654.5);
        dataset.addSeries(s1);
        return dataset;
    }

    /**
     * Creates a dataset for the demo.  Normally a dataset wouldn't be hard
     * coded like this - it would be read from a file or a database or some
     * other source.
     *
     * @return A dataset.
     */
    private static IntervalXYDataset createDataset2() {
        XYTaskDataset dataset = new XYTaskDataset(createTasks());
        dataset.setTransposed(true);
        dataset.setSeriesWidth(0.6);
        return dataset;
    }

    /**
     * Creates a task series collection.
     *
     * @return A task series collection.
     */
    private static TaskSeriesCollection createTasks() {
        TaskSeriesCollection dataset = new TaskSeriesCollection();
        TaskSeries s1 = new TaskSeries("Team A");
        s1.add(new Task("T1a", new Hour(11, new Day())));
        s1.add(new Task("T1b", new Hour(14, new Day())));
        s1.add(new Task("T1c", new Hour(16, new Day())));
        TaskSeries s2 = new TaskSeries("Team B");
        s2.add(new Task("T2a", new Hour(13, new Day())));
        s2.add(new Task("T2b", new Hour(19, new Day())));
        s2.add(new Task("T2c", new Hour(21, new Day())));
        TaskSeries s3 = new TaskSeries("Team C");
        s3.add(new Task("T3a", new Hour(13, new Day())));
        s3.add(new Task("T3b", new Hour(19, new Day())));
        s3.add(new Task("T3c", new Hour(21, new Day())));
        TaskSeries s4 = new TaskSeries("Team D");
        s4.add(new Task("T4a", new Day()));
        TaskSeries s5 = new TaskSeries("Team E");
        s5.add(new Task("T5a", new Day()));
        dataset.add(s1);
        dataset.add(s2);
        dataset.add(s3);
        dataset.add(s4);
        dataset.add(s5);
        return dataset;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        XYTaskDatasetDemo2 demo = new XYTaskDatasetDemo2(
                "JFreeChart : XYTaskDatasetDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
