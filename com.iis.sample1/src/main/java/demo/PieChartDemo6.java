/* ------------------
 * PieChartDemo6.java
 * ------------------
 * (C) Copyright 2006, 2008, by Object Refinery Limited.
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
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing four pie charts of the same dataset which contains a null
 * value, a zero value and a negative value.  Each chart has a different
 * setting for the ignoreNullValues and ignoreZeroValues flags.
 */
public class PieChartDemo6 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public PieChartDemo6(String title) {
        super(title);
        JPanel panel = createDemoPanel();
        panel.setPreferredSize(new Dimension(800, 600));
        setContentPane(panel);
    }

    /**
     * Creates a sample dataset containing a null value, a zero value and a
     * negative value.
     *
     * @return A sample dataset.
     */
    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("S1", 7.0);
        dataset.setValue("S2", null);
        dataset.setValue("S3", 0.0);
        dataset.setValue("S4", 3.0);
        dataset.setValue("S5", -1.0);
        return dataset;
    }

    /**
     * Creates a chart with the specified title and dataset.
     *
     * @param title  the chart title.
     * @param dataset  the dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(String title, PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(title, dataset, true,
                true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0} = {1}"));
        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        DemoPanel panel = new DemoPanel(new GridLayout(2, 2));
        JFreeChart chart1 = createChart("Pie Chart 1", createDataset());
        Font font = new Font("Dialog", Font.PLAIN, 12);
        chart1.addSubtitle(new TextTitle(
                "Ignore nulls: false; Ignore zeros: false;", font));

        JFreeChart chart2 = createChart("Pie Chart 2", createDataset());
        chart2.addSubtitle(new TextTitle(
                "Ignore nulls: true; Ignore zeros: false;", font));
        PiePlot plot2 = (PiePlot) chart2.getPlot();
        plot2.setIgnoreNullValues(true);
        plot2.setIgnoreZeroValues(false);

        JFreeChart chart3 = createChart("Pie Chart 3", createDataset());
        chart3.addSubtitle(new TextTitle(
                "Ignore nulls: false; Ignore zeros: true;", font));
        PiePlot plot3 = (PiePlot) chart3.getPlot();
        plot3.setIgnoreNullValues(false);
        plot3.setIgnoreZeroValues(true);

        JFreeChart chart4 = createChart("Pie Chart 4", createDataset());
        chart4.addSubtitle(new TextTitle(
                "Ignore nulls: true; Ignore zeros: true;", font));
        PiePlot plot4 = (PiePlot) chart4.getPlot();
        plot4.setIgnoreNullValues(true);
        plot4.setIgnoreZeroValues(true);

        panel.add(new ChartPanel(chart1));
        panel.add(new ChartPanel(chart2));
        panel.add(new ChartPanel(chart3));
        panel.add(new ChartPanel(chart4));

        panel.addChart(chart1);
        panel.addChart(chart2);
        panel.addChart(chart3);
        panel.addChart(chart4);

        return panel;
    }

    /**
     * The starting point for the demo.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        PieChartDemo6 demo = new PieChartDemo6(
                "JFreeChart: PieChartDemo6.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
