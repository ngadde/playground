/* --------------------
 * AnnotationDemo2.java
 * --------------------
 * (C) Copyright 2005-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.EmptyBlock;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.CompositeTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * This demo shows annotations being added to a renderer, so as to use a
 * secondary axis rather than the primary axis (as for annotations added
 * directly to the plot).
 */
public class AnnotationDemo2 extends ApplicationFrame {

    /**
     * Creates a new instance.
     *
     * @param title  the frame title.
     */
    public AnnotationDemo2(String title) {
        super(title);
        setContentPane(createDemoPanel());
    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private static XYDataset createDataset1() {

        XYSeries s1 = new XYSeries("Random Data 1");
        s1.add(1.0, 181.8);
        s1.add(2.0, 167.3);
        s1.add(3.0, 153.8);
        s1.add(4.0, 167.6);
        s1.add(5.0, 158.8);
        s1.add(6.0, 148.3);
        s1.add(7.0, 153.9);
        s1.add(8.0, 142.7);
        s1.add(9.0, 123.2);
        s1.add(10.0, 131.8);
        s1.add(11.0, 139.6);
        s1.add(12.0, 142.9);
        s1.add(13.0, 138.7);
        s1.add(14.0, 137.3);
        s1.add(15.0, 143.9);
        s1.add(16.0, 139.8);
        s1.add(17.0, 137.0);
        s1.add(18.0, 132.8);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s1);

        return dataset;

    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private static XYDataset createDataset2() {

        XYSeries s2 = new XYSeries("Random Data 2");
        s2.add(1.0, 429.6);
        s2.add(2.0, 323.2);
        s2.add(3.0, 417.2);
        s2.add(4.0, 624.1);
        s2.add(5.0, 422.6);
        s2.add(6.0, 619.2);
        s2.add(7.0, 416.5);
        s2.add(8.0, 512.7);
        s2.add(9.0, 501.5);
        s2.add(10.0, 306.1);
        s2.add(11.0, 410.3);
        s2.add(12.0, 511.7);
        s2.add(13.0, 611.0);
        s2.add(14.0, 709.6);
        s2.add(15.0, 613.2);
        s2.add(16.0, 711.6);
        s2.add(17.0, 708.8);
        s2.add(18.0, 501.6);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s2);

        return dataset;

    }

    private static JFreeChart createChart() {
        XYDataset dataset = createDataset1();
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Annotation Demo 2",
            "Date",
            "Price Per Unit",
            dataset,
            PlotOrientation.VERTICAL,
            false,     // legend? no, we'll create our own
            true,      // tooltips?
            false);    // URLs?


        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        NumberAxis axis1 = (NumberAxis) plot.getRangeAxis();
        axis1.setAutoRangeIncludesZero(false);

        NumberAxis axis2 = new NumberAxis("Secondary");
        axis2.setAutoRangeIncludesZero(false);
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1, createDataset2());
        plot.mapDatasetToRangeAxis(1, 1);
        XYLineAndShapeRenderer renderer
                = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseToolTipGenerator(
                StandardXYToolTipGenerator.getTimeSeriesInstance());
        renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled(true);
        XYPointerAnnotation annotation1 = new XYPointerAnnotation(
                "Annotation 1 (2.0, 167.3)", 2.0, 167.3, -Math.PI / 4.0);
        annotation1.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        annotation1.setPaint(Color.red);
        annotation1.setArrowPaint(Color.red);
        renderer.addAnnotation(annotation1);

        XYLineAndShapeRenderer renderer2
                = new XYLineAndShapeRenderer(true, true);
        renderer2.setSeriesPaint(0, Color.black);
        renderer.setBaseToolTipGenerator(
                StandardXYToolTipGenerator.getTimeSeriesInstance());
        XYPointerAnnotation annotation2 = new XYPointerAnnotation(
                "Annotation 2 (15.0, 613.2)", 15.0, 613.2, Math.PI / 2.0);
        annotation2.setTextAnchor(TextAnchor.TOP_CENTER);
        renderer2.addAnnotation(annotation2);
        plot.setRenderer(1, renderer2);

        // create two legends and put them into a composite title
        LegendTitle legend1 = new LegendTitle(renderer);
        LegendTitle legend2 = new LegendTitle(renderer2);
        BlockContainer container = new BlockContainer(new BorderArrangement());
        container.add(legend1, RectangleEdge.LEFT);
        container.add(legend2, RectangleEdge.RIGHT);
        container.add(new EmptyBlock(2000, 0));
        CompositeTitle legends = new CompositeTitle(container);
        legends.setFrame(new BlockBorder(Color.red));
        legends.setBackgroundPaint(Color.yellow);
        legends.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(legends);
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart();
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    /**
     * Starting point for the demonstration application when it is run as
     * a stand-alone application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        AnnotationDemo2 demo = new AnnotationDemo2(
                "JFreeChart: AnnotationDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
