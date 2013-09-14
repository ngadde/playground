/* --------------------
 * PieChart3DDemo3.java
 * --------------------
 * (C) Copyright 2004, 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.text.AttributedString;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;

/**
 * A pie chart with a custom label generator.
 */
public class PieChart3DDemo3 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public PieChart3DDemo3(String title) {

        super(title);
        PieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * Creates a sample dataset for the demo.
     *
     * @return A sample dataset.
     */
    private static PieDataset createDataset() {

        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Java", new Double(43.2));
        result.setValue("Visual Basic", new Double(10.0));
        result.setValue("C/C++", new Double(17.5));
        result.setValue("PHP", new Double(32.5));
        result.setValue("Perl", new Double(1.0));
        return result;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart3D(
            "Pie Chart 3D Demo 3",  // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        plot.setLabelGenerator(new CustomLabelGenerator());
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

        PieChart3DDemo3 demo = new PieChart3DDemo3(
                "JFreeChart: PieChart3DDemo3.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

    /**
     * A custom label generator (returns null for one item as a test).
     */
    static class CustomLabelGenerator implements PieSectionLabelGenerator {

        /**
         * Generates a label for a pie section.
         *
         * @param dataset  the dataset (<code>null</code> not permitted).
         * @param key  the section key (<code>null</code> not permitted).
         *
         * @return the label (possibly <code>null</code>).
         */
        public String generateSectionLabel(PieDataset dataset, Comparable key) {
            String result = null;
            if (dataset != null) {
                if (!key.equals("PHP")) {
                    result = key.toString();
                }
            }
            return result;
        }

        /**
         * Not used.
         *
         * @param dataset  the dataset.
         * @param key  the key.
         *
         * @return null.
         */
        public AttributedString generateAttributedSectionLabel(
                PieDataset dataset, Comparable key) {
            return null;
        }

    }

}
