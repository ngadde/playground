/* ------------------
 * PieChartDemo2.java
 * ------------------
 * (C) Copyright 2003-2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a pie chart using
 * data from a {@link DefaultPieDataset}.  This demo also shows an "exploded"
 * section in the chart.
 */
public class PieChartDemo2 extends ApplicationFrame {

    /**
     * Default constructor.
     *
     * @param title  the frame title.
     */
    public PieChartDemo2(String title) {
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
    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("One", 43.2);
        dataset.setValue("Two", 10.0);
        dataset.setValue("Three", 27.5);
        dataset.setValue("Four", 17.5);
        dataset.setValue("Five", 11.0);
        dataset.setValue("Six", 19.4);
        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
            "Pie Chart Demo 2",  // chart title
            dataset,             // dataset
            true,                // include legend
            true,
            false
        );
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("One", new Color(160, 160, 255));
        plot.setSectionPaint("Two", new Color(128, 128, 255 - 32));
        plot.setSectionPaint("Three", new Color(96, 96, 255 - 64));
        plot.setSectionPaint("Four", new Color(64, 64, 255 - 96));
        plot.setSectionPaint("Five", new Color(32, 32, 255 - 128));
        plot.setSectionPaint("Six", new Color(0, 0, 255 - 144));

        plot.setNoDataMessage("No data available");
        plot.setExplodePercent("Two", 0.20);

        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0} ({2} percent)"));
        plot.setLabelBackgroundPaint(new Color(220, 220, 220));

        plot.setLegendLabelToolTipGenerator(
                new StandardPieSectionLabelGenerator(
                        "Tooltip for legend item {0}"));
        plot.setSimpleLabels(true);
        plot.setInteriorGap(0.0);
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

        PieChartDemo2 demo = new PieChartDemo2(
                "JFreeChart: PieChartDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
