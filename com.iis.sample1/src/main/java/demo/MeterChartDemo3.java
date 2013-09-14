/* --------------------
 * MeterChartDemo3.java
 * --------------------
 * (C) Copyright 2005, 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

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
 * A demonstration showing the available dial shapes.
 */
public class MeterChartDemo3 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public MeterChartDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample chart.
     *
     * @param chartTitle  the chart title.
     * @param dataset  a dataset.
     * @param shape  the dial shape.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(String chartTitle,
            ValueDataset dataset, DialShape shape) {
        MeterPlot plot = new MeterPlot(dataset);
        plot.setDialShape(shape);
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
        plot.setMeterAngle(260);
        plot.setTickLabelsVisible(true);
        plot.setTickLabelFont(new Font("Dialog", Font.BOLD, 10));
        plot.setTickLabelPaint(Color.darkGray);
        plot.setTickSize(5.0);
        plot.setTickPaint(Color.lightGray);

        plot.setValuePaint(Color.black);
        plot.setValueFont(new Font("Dialog", Font.BOLD, 14));

        JFreeChart chart = new JFreeChart(chartTitle,
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
        JPanel panel = new JPanel(new GridLayout(1, 3));
        DefaultValueDataset dataset = new DefaultValueDataset(23.0);
        JPanel panel1 = new ChartPanel(createChart("DialShape.PIE", dataset,
                DialShape.PIE));
        JPanel panel2 = new ChartPanel(createChart("DialShape.CHORD", dataset,
                DialShape.CHORD));
        JPanel panel3 = new ChartPanel(createChart("DialShape.CIRCLE", dataset,
                DialShape.CIRCLE));
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        MeterChartDemo3 demo = new MeterChartDemo3(
                "JFreeChart: MeterChartDemo3.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
