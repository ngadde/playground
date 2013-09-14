/* -------------------
 * SparklineDemo1.java
 * -------------------
 * (C) Copyright 2006, 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

/**
 * A sparkline demo.
 */
public class SparklineDemo1 {

    /**
     * The starting point for the demo app.
     *
     * @param args  command line arguments (ignored).
     */
    public static void main(String[] args) {

        // create a dataset
        XYSeries series1 = new XYSeries("Series 1");
        series1.add(1.0, 1.0);
        series1.add(2.0, 3.0);
        series1.add(3.0, 2.0);
        series1.add(4.0, 4.0);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);

        // create a chart
        JFreeChart chart = ChartFactory.createXYLineChart(null, "X", "Y",
                dataset, PlotOrientation.VERTICAL, false, false, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setInsets(RectangleInsets.ZERO_INSETS);
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinesVisible(false);
        plot.setOutlinePaint(null);
        plot.getDomainAxis().setVisible(false);
        plot.getRangeAxis().setVisible(false);

        // save to a file in PNG format
        try {
            ChartUtilities.saveChartAsPNG(new File("Sparky.png"), chart,
                    100, 20);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
