/* ------------------
 * GridBandDemo1.java
 * ------------------
 * (C) Copyright 2007-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing the grid band feature in {@link XYPlot}.
 */
public class GridBandDemo1 extends ApplicationFrame {

    /**
     * A demonstration application showing a scatter plot.
     *
     * @param title  the frame title.
     */
    public GridBandDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createScatterPlot("Grid Band Demo 1",
                "X", "Y", dataset, PlotOrientation.VERTICAL, true, false,
                false);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setNoDataMessage("NO DATA");
        plot.setRangeZeroBaselineVisible(true);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);

        plot.setDomainTickBandPaint(new Color(0, 100, 0, 50));
        plot.setRangeTickBandPaint(new Color(0, 100, 0, 50));

        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        XYSeries series = new XYSeries("Random Data");
        for (int i = 0; i < 100; i++) {
            series.add(Math.random() + 1.0, Math.random() + 1.0);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        return chartPanel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        GridBandDemo1 demo = new GridBandDemo1(
                "JFreeChart: GridBandDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
