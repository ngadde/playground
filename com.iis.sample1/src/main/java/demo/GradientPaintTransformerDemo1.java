/* ----------------------------------
 * GradientPaintTransformerDemo1.java
 * ----------------------------------
 * (C) Copyright 2007, 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.GridLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.StandardGradientPaintTransformer;

/**
 * An example of the various types of
 * <code>StandardGradientPaintTransformer</code>.
 */
public class GradientPaintTransformerDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo app.
     *
     * @param title  the frame title.
     */
    public GradientPaintTransformerDemo1(String title) {
        super(title);
        setContentPane(createDemoPanel());
    }

    /**
     * Creates the demo chart.
     *
     * @param title  the title.
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(String title,
            CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createBarChart(
            title,
            null,
            "Value",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer r = (BarRenderer) plot.getRenderer();
        r.setItemMargin(0.02);
        return chart;

    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(7.0, "Series 1", "Category 1");
        dataset.addValue(5.0, "Series 2", "Category 1");
        return dataset;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        DemoPanel panel = new DemoPanel(new GridLayout(2, 2));
        panel.setPreferredSize(new java.awt.Dimension(800, 600));

        CategoryDataset dataset = createDataset();

        JFreeChart chart1 = createChart("Type: VERTICAL", dataset);
        CategoryPlot plot1 = (CategoryPlot) chart1.getPlot();
        BarRenderer renderer1 = (BarRenderer) plot1.getRenderer();
        renderer1.setBarPainter(new StandardBarPainter());
        renderer1.setDrawBarOutline(false);
        renderer1.setSeriesPaint(0, new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, Color.yellow));
        renderer1.setSeriesPaint(1, new GradientPaint(0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, Color.green));
        renderer1.setGradientPaintTransformer(
                new StandardGradientPaintTransformer(
                        GradientPaintTransformType.VERTICAL));

        ChartPanel chartPanel1 = new ChartPanel(chart1);
        panel.add(chartPanel1);

        JFreeChart chart2 = createChart("Type: HORIZONTAL", dataset);
        CategoryPlot plot2 = (CategoryPlot) chart2.getPlot();
        BarRenderer renderer2 = (BarRenderer) plot2.getRenderer();
        renderer2.setBarPainter(new StandardBarPainter());
        renderer2.setDrawBarOutline(false);
        renderer2.setSeriesPaint(0, new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, Color.yellow));
        renderer2.setSeriesPaint(1, new GradientPaint(0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, Color.green));
        renderer2.setGradientPaintTransformer(
                new StandardGradientPaintTransformer(
                        GradientPaintTransformType.HORIZONTAL));
        ChartPanel chartPanel2 = new ChartPanel(chart2);
        panel.add(chartPanel2);


        JFreeChart chart3 = createChart("Type: CENTER_VERTICAL", dataset);
        CategoryPlot plot3 = (CategoryPlot) chart3.getPlot();
        BarRenderer renderer3 = (BarRenderer) plot3.getRenderer();
        renderer3.setBarPainter(new StandardBarPainter());
        renderer3.setDrawBarOutline(false);
        renderer3.setSeriesPaint(0, new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, Color.yellow));
        renderer3.setSeriesPaint(1, new GradientPaint(0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, Color.green));
        renderer3.setGradientPaintTransformer(
                new StandardGradientPaintTransformer(
                        GradientPaintTransformType.CENTER_VERTICAL));
        ChartPanel chartPanel3 = new ChartPanel(chart3);
        panel.add(chartPanel3);

        JFreeChart chart4 = createChart("Type: CENTER_HORIZONTAL", dataset);
        CategoryPlot plot4 = (CategoryPlot) chart4.getPlot();
        BarRenderer renderer4 = (BarRenderer) plot4.getRenderer();
        renderer4.setBarPainter(new StandardBarPainter());
        renderer4.setDrawBarOutline(false);
        renderer4.setSeriesPaint(0, new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, Color.yellow));
        renderer4.setSeriesPaint(1, new GradientPaint(0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, Color.green));
        renderer4.setGradientPaintTransformer(
                new StandardGradientPaintTransformer(
                        GradientPaintTransformType.CENTER_HORIZONTAL));
        ChartPanel chartPanel4 = new ChartPanel(chart4);
        panel.add(chartPanel4);

        panel.addChart(chart1);
        panel.addChart(chart2);
        panel.addChart(chart3);
        panel.addChart(chart4);

        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        GradientPaintTransformerDemo1 demo = new GradientPaintTransformerDemo1(
                "JFreeChart: GradientPaintTransformerDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
