/* ------------------
 * PieChartDemo5.java
 * ------------------
 * (C) Copyright 2004, 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing four pie charts.
 */
public class PieChartDemo5 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public PieChartDemo5(String title) {
        super(title);
        setContentPane(createDemoPanel());
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        DemoPanel panel = new DemoPanel(new GridLayout(2, 2));
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Section 1", 23.3);
        dataset.setValue("Section 2", 56.5);
        dataset.setValue("Section 3", 43.3);
        dataset.setValue("Section 4", 11.1);

        JFreeChart chart1 = ChartFactory.createPieChart("Chart 1", dataset,
                false, false, false);
        chart1.addSubtitle(new TextTitle("setCircular(true);",
                new Font("Dialog", Font.PLAIN, 12)));
        PiePlot plot1 = (PiePlot) chart1.getPlot();
        plot1.setCircular(true);
        plot1.setInteriorGap(0.04);
        plot1.setMaximumLabelWidth(0.20);

        JFreeChart chart2 = ChartFactory.createPieChart("Chart 2", dataset,
                false, false, false);
        chart2.addSubtitle(new TextTitle("setCircular(false);",
                new Font("Dialog", Font.PLAIN, 12)));
        PiePlot plot2 = (PiePlot) chart2.getPlot();
        plot2.setCircular(false);
        plot2.setInteriorGap(0.04);
        plot2.setMaximumLabelWidth(0.20);

        JFreeChart chart3 = ChartFactory.createPieChart3D("Chart 3", dataset,
                false, false, false);
        chart3.addSubtitle(new TextTitle("setCircular(true);",
                new Font("Dialog", Font.PLAIN, 12)));
        PiePlot3D plot3 = (PiePlot3D) chart3.getPlot();
        plot3.setForegroundAlpha(0.6f);
        plot3.setCircular(true);
        plot3.setInteriorGap(0.04);
        plot3.setMaximumLabelWidth(0.20);

        JFreeChart chart4 = ChartFactory.createPieChart3D("Chart 4", dataset,
                false, false, false);
        chart4.addSubtitle(new TextTitle("setCircular(false);",
                new Font("Dialog", Font.PLAIN, 12)));
        PiePlot3D plot4 = (PiePlot3D) chart4.getPlot();
        plot4.setForegroundAlpha(0.6f);
        plot4.setCircular(false);
        plot4.setInteriorGap(0.04);
        plot4.setMaximumLabelWidth(0.20);

        panel.add(new ChartPanel(chart1));
        panel.add(new ChartPanel(chart2));
        panel.add(new ChartPanel(chart3));
        panel.add(new ChartPanel(chart4));

        panel.addChart(chart1);
        panel.addChart(chart2);
        panel.addChart(chart3);
        panel.addChart(chart4);

        panel.setPreferredSize(new Dimension(800, 600));
        return panel;
    }

    /**
     * The starting point for the demo.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        PieChartDemo5 demo = new PieChartDemo5(
                "JFreeChart: PieChartDemo5.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
