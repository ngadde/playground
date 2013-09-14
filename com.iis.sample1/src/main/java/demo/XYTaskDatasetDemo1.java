/* -----------------------
 * XYTaskDatasetDemo1.java
 * -----------------------
 * (C) Copyright 2008, 2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.gantt.XYTaskDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demo of the {@link XYTaskDataset} class which permits a type of
 * Gantt chart to be created using an {@link XYPlot}.
 */
public class XYTaskDatasetDemo1 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYTaskDatasetDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel);
    }

    /**
     * Creates the chart.
     *
     * @param dataset  the dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYBarChart("XYTaskDatasetDemo1",
                "Resource", false, "Timing", dataset,
                PlotOrientation.HORIZONTAL,
                true, false, false);

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setRangePannable(true);
        SymbolAxis xAxis = new SymbolAxis("Series", new String[] {"Team A",
                "Team B", "Team C", "Team D"});
        xAxis.setGridBandsVisible(false);
        plot.setDomainAxis(xAxis);
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setUseYInterval(true);
        plot.setRangeAxis(new DateAxis("Timing"));
        ChartUtilities.applyCurrentTheme(chart);

        return chart;
    }

    /**
     * Creates a panel for the demo.
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
     * Creates the sample dataset.
     *
     * @return The sample dataset.
     */
    private static IntervalXYDataset createDataset() {
        return new XYTaskDataset(createTasks());
    }

    /**
     * Creates a collection of tasks.
     *
     * @return A collection of tasks.
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
        dataset.add(s1);
        dataset.add(s2);
        dataset.add(s3);
        dataset.add(s4);
        return dataset;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        XYTaskDatasetDemo1 demo = new XYTaskDatasetDemo1(
                "JFreeChart : XYTaskDatasetDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
