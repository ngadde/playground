/* ------------------
 * XYSeriesDemo3.java
 * ------------------
 * (C) Copyright 2004, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * This demo shows a simple bar chart created using the
 * {@link XYSeriesCollection} dataset.
 */
public class XYSeriesDemo3 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public XYSeriesDemo3(String title) {
        super(title);
        IntervalXYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static IntervalXYDataset createDataset() {
        XYSeries series = new XYSeries("Random Data");
        series.add(1.0, 400.2);
        series.add(5.0, 294.1);
        series.add(4.0, 100.0);
        series.add(12.5, 734.4);
        series.add(17.3, 453.2);
        series.add(21.2, 500.2);
        series.add(21.9, null);
        series.add(25.6, 734.4);
        series.add(30.0, 453.2);
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    private static JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYBarChart(
            "XY Series Demo 3",
            "X",
            false,
            "Y",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        IntervalMarker target = new IntervalMarker(400.0, 700.0);
        target.setLabel("Target Range");
        target.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
        target.setLabelAnchor(RectangleAnchor.LEFT);
        target.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        target.setPaint(new Color(222, 222, 255, 128));
        plot.addRangeMarker(target, Layer.BACKGROUND);
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

        XYSeriesDemo3 demo = new XYSeriesDemo3("JFreeChart: XYSeriesDemo3.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
