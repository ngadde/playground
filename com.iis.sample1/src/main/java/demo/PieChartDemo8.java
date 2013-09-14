/* ------------------
 * PieChartDemo8.java
 * ------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.font.TextAttribute;
import java.text.AttributedString;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A pie chart with a custom label generator.
 */
public class PieChartDemo8 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public PieChartDemo8(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("One", new Double(43.2));
        dataset.setValue("Two", new Double(10.0));
        dataset.setValue("Three", new Double(27.5));
        dataset.setValue("Four", new Double(17.5));
        dataset.setValue("Five", new Double(11.0));
        dataset.setValue("Six", new Double(19.4));
        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return a chart.
     */
    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
            "Pie Chart Demo 8",  // chart title
            dataset,             // data
            false,               // include legend
            true,
            false
        );
        PiePlot plot = (PiePlot) chart.getPlot();
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
        PieChartDemo8 demo = new PieChartDemo8(
                "JFreeChart: PieChartDemo8.java");
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
         * @return The label (possibly <code>null</code>).
         */
        public String generateSectionLabel(PieDataset dataset, Comparable key) {
            String result = null;
            if (dataset != null) {
                if (!key.equals("Two")) {
                    result = key.toString();
                }
            }
            return result;
        }

        /**
         * Generates an attributed string for the section label.
         *
         * @param dataset  the dataset.
         * @param key  the section key.
         *
         * @return The section label.
         */
        public AttributedString generateAttributedSectionLabel(
                PieDataset dataset, Comparable key) {
            AttributedString result = null;
            String keyStr = key.toString();
            String text = keyStr + " : " + String.valueOf(dataset.getValue(key));
            result = new AttributedString(text);
            result.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD,
                    0, keyStr.length() - 1);
            return result;
        }

    }

}
