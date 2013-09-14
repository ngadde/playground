/* --------------------
 * SymbolAxisDemo1.java
 * --------------------
 * (C) Copyright 2005-2008, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to use the
 * {@link SymbolAxis} class with an {@link XYPlot}.
 */
public class SymbolAxisDemo1 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public SymbolAxisDemo1(String title) {

        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel);

    }

    private static JFreeChart createChart(XYDataset dataset) {
        SymbolAxis domainAxis = new SymbolAxis("Domain",
                new String[] {"A", "B", "C", "DDDDDDDDDDDDDDDDDDD"});
        SymbolAxis rangeAxis = new SymbolAxis("Range",
                new String[] {"V", "X", "Y", "Z"});
        rangeAxis.setUpperMargin(0.0);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(false, true);
        XYPlot plot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        JFreeChart chart = new JFreeChart("SymbolAxis Demo 1", plot);
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return A dataset.
     */
    private static XYDataset createDataset() {
        XYSeries s1 = new XYSeries("Series 1");
        s1.add(0, 3);
        s1.add(1, 3);
        s1.add(2, 0);
        s1.add(3, 1);
        XYSeries s2 = new XYSeries("Series 2");
        s2.add(0, 1);
        s2.add(1, 2);
        s2.add(2, 1);
        s2.add(3, 3);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        return dataset;
    }

    /**
     * Creates a panel for the demo.
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()));
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        SymbolAxisDemo1 demo = new SymbolAxisDemo1(
                "JFreeChart: SymbolAxisDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
