/* -----------------------------------
 * CategoryPointerAnnotationDemo1.java
 * -----------------------------------
 * (C) Copyright 2006-2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryPointerAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * A simple demonstration application showing how to create a line chart using
 * data from a {@link CategoryDataset}.
 */
public class CategoryPointerAnnotationDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public CategoryPointerAnnotationDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(212, "Classes", "JDK 1.0");
        dataset.addValue(504, "Classes", "JDK 1.1");
        dataset.addValue(1520, "Classes", "JDK 1.2");
        dataset.addValue(1842, "Classes", "JDK 1.3");
        dataset.addValue(2991, "Classes", "JDK 1.4");
        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  a dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createLineChart(
            "Java Standard Class Library",   // chart title
            "Release",                       // domain axis label
            "Class Count",                   // range axis label
            dataset,                         // data
            PlotOrientation.VERTICAL,        // orientation
            false,                           // include legend
            true,                            // tooltips
            false                            // urls
        );

        chart.addSubtitle(new TextTitle("Number of Classes By Release"));
        TextTitle source = new TextTitle(
            "Source: Java In A Nutshell (4th Edition) "
            + "by David Flanagan (O'Reilly)"
        );
        source.setFont(new Font("SansSerif", Font.PLAIN, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);


        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // customise the range axis...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // customise the renderer...
        LineAndShapeRenderer renderer
            = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseShapesVisible(true);
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        renderer.setBaseFillPaint(Color.white);

        // add a pointer annotation
        CategoryPointerAnnotation cpa = new CategoryPointerAnnotation(
                "Released 4-Dec-1998", "JDK 1.2", 1530, - 3 * Math.PI / 4);
        cpa.setTextAnchor(TextAnchor.BOTTOM_RIGHT);
        plot.addAnnotation(cpa);
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
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        CategoryPointerAnnotationDemo1 demo
                = new CategoryPointerAnnotationDemo1(
                "JFreeChart: CategoryPointerAnnotationDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
