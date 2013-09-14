/* -----------------------------
 * XYPointerAnnotationDemo1.java
 * -----------------------------
 * (C) Copyright 2006, 2009, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * This demo illustrates the use of the {@link XYPointerAnnotation} class.
 */
public class XYPointerAnnotationDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public XYPointerAnnotationDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    public static XYDataset createDataset() {
        DefaultXYDataset d = new DefaultXYDataset();
        double[] x1 = new double[] {1.7, 2.2, 2.7, 3.0, 3.2};
        double[] y1 = new double[] {4.0, 3.0, 6.0, 1.0, 9.0};
        double[][] data1 = new double[][] {x1, y1};
        d.addSeries("Series 1", data1);
        double[] x2 = new double[] {2.1, 2.2, 2.4, 2.7, 3.2};
        double[] y2 = new double[] {4.5, 6.0, 4.0, 8.0, 5.1};
        double[][] data2 = new double[][] {x2, y2};
        d.addSeries("Series 2", data2);
        return d;
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createScatterPlot(
                "XYPointerAnnotationDemo1", "X", "Y", dataset,
                PlotOrientation.HORIZONTAL, true, true, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        XYLineAndShapeRenderer renderer
                = (XYLineAndShapeRenderer) plot.getRenderer();

        XYPointerAnnotation a = new XYPointerAnnotation("Special point", 2.2,
                6.0, 5 * Math.PI / 4);
        a.setTextAnchor(TextAnchor.BOTTOM_RIGHT);
        a.setToolTipText("The pointer has a tool tip!");
        renderer.addAnnotation(a, Layer.BACKGROUND);
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
        XYPointerAnnotationDemo1 demo = new XYPointerAnnotationDemo1(
                "XYPointerAnnotationDemo1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
