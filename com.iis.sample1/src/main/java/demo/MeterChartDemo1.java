/* --------------------
 * MeterChartDemo1.java
 * --------------------
 * (C) Copyright 2005, 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a meter chart.
 */
public class MeterChartDemo1 extends ApplicationFrame {

    private static DefaultValueDataset dataset;

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public MeterChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  a dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(ValueDataset dataset) {
        MeterPlot plot = new MeterPlot(dataset);
        plot.setRange(new Range(0, 60));
        plot.addInterval(new MeterInterval("Normal", new Range(0.0, 35.0),
                Color.lightGray, new BasicStroke(2.0f),
                new Color(0, 255, 0, 64)));
        plot.addInterval(new MeterInterval("Warning", new Range(35.0, 50.0),
                Color.lightGray, new BasicStroke(2.0f), new Color(255, 255, 0, 64)));
        plot.addInterval(new MeterInterval("Critical", new Range(50.0, 60.0),
                Color.lightGray, new BasicStroke(2.0f),
                new Color(255, 0, 0, 128)));
        plot.setNeedlePaint(Color.darkGray);
        plot.setDialBackgroundPaint(Color.white);
        plot.setDialOutlinePaint(Color.gray);
        plot.setDialShape(DialShape.CHORD);
        plot.setMeterAngle(260);
        plot.setTickLabelsVisible(true);
        plot.setTickLabelFont(new Font("Dialog", Font.BOLD, 10));
        plot.setTickLabelPaint(Color.darkGray);
        plot.setTickSize(5.0);
        plot.setTickPaint(Color.lightGray);

        plot.setValuePaint(Color.black);
        plot.setValueFont(new Font("Dialog", Font.BOLD, 14));

        JFreeChart chart = new JFreeChart("Meter Chart 1",
                JFreeChart.DEFAULT_TITLE_FONT, plot, true);

        ChartUtilities.applyCurrentTheme(chart);

        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        dataset = new DefaultValueDataset(23.0);
        JFreeChart chart = createChart(dataset);
        JPanel panel = new ChartPanel(chart);
        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        MeterChartDemo1 demo = new MeterChartDemo1(
                "JFreeChart: MeterChartDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
