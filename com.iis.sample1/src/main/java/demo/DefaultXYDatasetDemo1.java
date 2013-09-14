/* --------------------------
 * DefaultXYDatasetDemo1.java
 * --------------------------
 * (C) Copyright 2006, 2008, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing a simple usage of the
 * <code>DefaultXYDataset</code> class.
 */
public class DefaultXYDatasetDemo1 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public DefaultXYDatasetDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a simple chart for the specified dataset.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {
        return ChartFactory.createScatterPlot("DefaultXYDatasetDemo1", "X",
                "Y", dataset, PlotOrientation.VERTICAL, true, false, false);
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static XYDataset createDataset() {
        DefaultXYDataset dataset = new DefaultXYDataset();

        double[] x1 = new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0};
        double[] y1 = new double[] {8.0, 7.0, 6.0, 5.0, 4.0, 3.0, 2.0, 1.0};
        double[][] data1 = new double[][] {x1, y1};
        dataset.addSeries("Series 1", data1);

        double[] x2 = new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0};
        double[] y2 = new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0};
        double[][] data2 = new double[][] {x2, y2};
        dataset.addSeries("Series 2", data2);

        return dataset;
    }

    /**
     * Creates a panel for the demo.  This method exists only to allow
     * aggregration within the JFreeChart <code>SuperDemo</code>.
     *
     * @return A panel containing the demo chart.
     */
    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()));
    }

    /**
     * Starting point for the demonstration application when it is run as
     * a stand-alone application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        DefaultXYDatasetDemo1 demo = new DefaultXYDatasetDemo1(
                "JFreeChart: DefautlXYDatasetDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
