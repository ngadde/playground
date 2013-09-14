/* --------------------
 * SmallNumberDemo.java
 * --------------------
 * (C) Copyright 2003, 2004, by Object Refinery Limited.
 *
 */

package demo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.StandardTickUnitSource;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing the use of very small numbers in a dataset.
 */
public class SmallNumberDemo extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public SmallNumberDemo(String title) {

        super(title);
        XYSeries series = new XYSeries("Small Numbers");
        series.add(1.0E-5, 1.0E-16); 
        series.add(5.0E-5, 2.0E-12); 
//        series.add(17.3E-5, 5.0E-7); 
//        series.add(21.2E-5, 9.0E-6); 
//        series.add(41.2E-5, 1.2E-5); 
        XYSeriesCollection data = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Small Number Demo",
            "X", 
            "Y", 
            data,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setStandardTickUnits(new StandardTickUnitSource());
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(new StandardTickUnitSource());
        rangeAxis.setAutoRangeMinimumSize(Double.MIN_VALUE);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        System.out.println("Min Double: " + Double.MIN_VALUE);
        SmallNumberDemo demo = new SmallNumberDemo("Small Number Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
