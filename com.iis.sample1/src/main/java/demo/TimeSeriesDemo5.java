/* --------------------
 * TimeSeriesDemo5.java
 * --------------------
 * (C) Copyright 2001-2009, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.SamplingXYLineRenderer;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A time series chart with 4000 data points, to get an idea of how JFreeChart
 * performs with a larger dataset.  To boost the performance, we've used the
 * new XYSamplingLineRenderer.
 */
public class TimeSeriesDemo5 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public TimeSeriesDemo5(String title) {
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

        TimeSeries series = new TimeSeries("Random Data");
        Day current = new Day(1, 1, 1990);
        double value = 100.0;
        for (int i = 0; i < 100000; i++) {
            try {
                value = value + Math.random() - 0.5;
                series.add(current, new Double(value));
                current = (Day) current.next();
            }
            catch (SeriesException e) {
                System.err.println("Error adding to series");
            }
        }
        return new TimeSeriesCollection(series);
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Test",
            "Day",
            "Value",
            dataset,
            false,
            false,
            false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        SamplingXYLineRenderer renderer = new SamplingXYLineRenderer();
        plot.setRenderer(renderer);
        return chart;
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
     * Starting point for the application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        String title = "\u20A2\u20A2\u20A2\u20A3\u20A4\u20A5\u20A6\u20A7\u20A8\u20A9\u20AA";
        TimeSeriesDemo5 demo = new TimeSeriesDemo5(title);
        demo.pack();
        RefineryUtilities.positionFrameRandomly(demo);
        demo.setVisible(true);
    }

}
