/* -----------------
 * CompassDemo1.java
 * -----------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CompassPlot;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to...
 */
public class CompassDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo application.
     *
     * @param title  the frame title.
     */
    public CompassDemo1(String title) {
        super(title);
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setEnforceFileExtensions(false);
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(ValueDataset dataset) {
        CompassPlot plot = new CompassPlot(dataset);
        plot.setSeriesNeedle(7);
        plot.setSeriesPaint(0, Color.black);
        plot.setSeriesOutlinePaint(0, Color.black);
        plot.setRosePaint(Color.red);
        plot.setRoseHighlightPaint(Color.gray);
        plot.setRoseCenterPaint(Color.white);
        plot.setDrawBorder(false);
        JFreeChart chart = new JFreeChart(plot);
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(new DefaultValueDataset(
                new Double(45.0)));
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        CompassDemo1 demo = new CompassDemo1("JFreeChart: CompassDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
