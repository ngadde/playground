/* -----------
 * Second.java
 * -----------
 * (C) Copyright 2002-2004, by Object Refinery Limited.
 *
 */

package demo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * A simple demo.
 */
public class Second {

    /**
     * Starting point for the demo.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        // create some data...
        XYSeries series1 = new XYSeries("Advisory Range");
        series1.add(new Integer(1200), new Integer(1));
        series1.add(new Integer(1500), new Integer(1));

        XYSeries series2 = new XYSeries("Normal Range");
        series2.add(new Integer(2000), new Integer(4));
        series2.add(new Integer(2300), new Integer(4));

        XYSeries series3 = new XYSeries("Recommended");
        series3.add(new Integer(2100), new Integer(2));

        XYSeries series4 = new XYSeries("Current");
        series4.add(new Integer(2400), new Integer(3));

        XYSeriesCollection data = new XYSeriesCollection();
        data.addSeries(series1);
        data.addSeries(series2);
        data.addSeries(series3);
        data.addSeries(series4);

        // create a chart...
        JFreeChart chart = ChartFactory.createXYLineChart(
            "My Chart", 
            "Calories", 
            "Y", 
            data,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        XYItemRenderer renderer = new StandardXYItemRenderer(
                StandardXYItemRenderer.SHAPES_AND_LINES, null);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setRenderer(renderer);
        ValueAxis axis = plot.getRangeAxis();
        axis.setTickLabelsVisible(false);
        axis.setRange(0.0, 5.0);

        // create and display a frame...
        ChartFrame frame = new ChartFrame("Test", chart);
        frame.pack();
        frame.setVisible(true);

    }

}
