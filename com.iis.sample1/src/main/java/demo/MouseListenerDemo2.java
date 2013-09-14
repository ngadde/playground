/* -----------------------
 * MouseListenerDemo2.java
 * -----------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 */

package demo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application for chart mouse events with a bar chart.
 */
public class MouseListenerDemo2 extends ApplicationFrame
                                implements ChartMouseListener {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public MouseListenerDemo2(String title) {

        super(title);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.0, "S1", "C1");
        dataset.addValue(4.0, "S1", "C2");
        dataset.addValue(3.0, "S1", "C3");
        dataset.addValue(5.0, "S1", "C4");
        dataset.addValue(5.0, "S1", "C5");
        dataset.addValue(6.0, "S1", "C6");
        dataset.addValue(7.0, "S1", "C7");
        dataset.addValue(8.0, "S1", "C8");
        dataset.addValue(5.0, "S2", "C1");
        dataset.addValue(7.0, "S2", "C2");
        dataset.addValue(6.0, "S2", "C3");
        dataset.addValue(8.0, "S2", "C4");
        dataset.addValue(4.0, "S2", "C5");
        dataset.addValue(4.0, "S2", "C6");
        dataset.addValue(3.0, "S2", "C7");
        dataset.addValue(1.0, "S2", "C8");
        dataset.addValue(4.0, "S3", "C1");
        dataset.addValue(3.0, "S3", "C2");
        dataset.addValue(2.0, "S3", "C3");
        dataset.addValue(3.0, "S3", "C4");
        dataset.addValue(6.0, "S3", "C5");
        dataset.addValue(3.0, "S3", "C6");
        dataset.addValue(4.0, "S3", "C7");
        dataset.addValue(3.0, "S3", "C8");

        JFreeChart chart = ChartFactory.createBarChart(
            "MouseListenerDemo2",
            "Category",
            "Value",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        // add the chart to a panel...
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.addChartMouseListener(this);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * Receives chart mouse click events.
     *
     * @param event  the event.
     */
    public void chartMouseClicked(ChartMouseEvent event) {
        ChartEntity entity = event.getEntity();
        if (entity != null) {
            System.out.println("Mouse clicked: " + entity.toString());
        }
        else {
            System.out.println("Mouse clicked: null entity.");
        }
    }

    /**
     * Receives chart mouse moved events.
     *
     * @param event  the event.
     */
    public void chartMouseMoved(ChartMouseEvent event) {
        int x = event.getTrigger().getX();
        int y = event.getTrigger().getY();
        ChartEntity entity = event.getEntity();
        if (entity != null) {
            System.out.println("Mouse moved: " + x + ", " + y + ": "
                    + entity.toString());
        }
        else {
            System.out.println("Mouse moved: " + x + ", " + y
                    + ": null entity.");
        }
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        MouseListenerDemo2 demo = new MouseListenerDemo2(
                "JFreeChart: MouseListenerDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
