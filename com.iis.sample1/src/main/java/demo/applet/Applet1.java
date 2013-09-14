/* ------------
 * Applet1.java
 * ------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 */

package demo.applet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.Timer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 * A simple applet demo.
 */
public class Applet1 extends JApplet {

    /** Time series for total memory used. */
    private TimeSeries total;

    /** Time series for free memory. */
    private TimeSeries free;

    /**
     * Creates a new instance.
     */
    public Applet1() {

        // create two series that automatically discard data more than
        // 30 seconds old...
        this.total = new TimeSeries("Total");
        this.total.setMaximumItemAge(30000);
        this.free = new TimeSeries("Free");
        this.free.setMaximumItemAge(30000);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(this.total);
        dataset.addSeries(this.free);

        DateAxis domain = new DateAxis("Time");
        NumberAxis range = new NumberAxis("Memory");

        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);

        XYPlot plot = new XYPlot(dataset, domain, range, renderer);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        renderer.setSeriesPaint(0, Color.red);
        renderer.setSeriesPaint(1, Color.green);
        renderer.setSeriesStroke(0, new BasicStroke(1.5f));
        renderer.setSeriesStroke(1, new BasicStroke(1.5f));

        domain.setAutoRange(true);
        domain.setLowerMargin(0.0);
        domain.setUpperMargin(0.0);
        domain.setTickLabelsVisible(true);

        range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        JFreeChart chart = new JFreeChart(
            "Memory Usage", JFreeChart.DEFAULT_TITLE_FONT, plot, true
        );
        chart.setBackgroundPaint(Color.white);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPopupMenu(null);

        getContentPane().add(chartPanel);
        new Applet1.DataGenerator().start();

    }

    /**
     * Adds an observation to the 'total memory' time series.
     *
     * @param y  the total memory used.
     */
    private void addTotalObservation(double y) {
        this.total.add(new Millisecond(), y);
    }

    /**
     * Adds an observation to the 'free memory' time series.
     *
     * @param y  the free memory.
     */
    private void addFreeObservation(double y) {
        this.free.add(new Millisecond(), y);
    }

    /**
     * The data generator.
     */
    class DataGenerator extends Timer implements ActionListener {

        /**
         * Constructor.
         */
        DataGenerator() {
            super(100, null);
            addActionListener(this);
        }

        /**
         * Adds a new free/total memory reading to the dataset.
         *
         * @param event  the action event.
         */
        public void actionPerformed(ActionEvent event) {
            long f = Runtime.getRuntime().freeMemory();
            long t = Runtime.getRuntime().totalMemory();
            addTotalObservation(t);
            addFreeObservation(f);
        }

    }

}
