/* --------------------
 * PolarChartDemo1.java
 * --------------------
 * (C) Copyright 2005, 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a polar chart.
 */
public class PolarChartDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public PolarChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Returns a sample dataset.
     *
     * @return The dataset.
     */
    private static XYDataset createDataset() {

        XYSeriesCollection result = new XYSeriesCollection();
        XYSeries s1 = new XYSeries("Series 1");
        s1.add(0.0, 2.0);
        s1.add(90.0, 13.0);
        s1.add(180.0, 9.0);
        s1.add(270.0, 8.0);
        result.addSeries(s1);

        XYSeries s2 = new XYSeries ("Series 2");
        s2.add(90.0, -11.2);
        s2.add(180.0, 21.4);
        s2.add(250.0, 17.3);
        s2.add(355.0, 10.9);
        result.addSeries(s2);
        return result;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createPolarChart(
            "Polar Chart Demo 1", dataset, true, false, false);


        // get a reference to the plot for further customisation...
        PolarPlot plot = (PolarPlot) chart.getPlot();
        plot.addCornerTextItem("Corner Item 1");
        plot.addCornerTextItem("Corner Item 2");

        plot.setAngleGridlinePaint(Color.white);
        plot.setRadiusGridlinePaint(Color.white);

        // set the range axis to display integers only...
        NumberAxis rangeAxis = (NumberAxis) plot.getAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        ChartUtilities.applyCurrentTheme(chart);

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
        panel.setMouseZoomable(false);
        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        PolarChartDemo1 demo = new PolarChartDemo1(
                "JFreeChart: PolarChartDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
