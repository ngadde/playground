/* -----------------------
 * CompassFormatDemo1.java
 * -----------------------
 * (C) Copyright 2004-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CompassFormat;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo of the {@link CompassFormat} class.
 */
public class CompassFormatDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public CompassFormatDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @param count  the item count.
     *
     * @return the dataset.
     */
    private static XYDataset createDirectionDataset(int count) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries s1 = new TimeSeries("Wind Direction");
        RegularTimePeriod start = new Minute();
        double direction = 180.0;
        for (int i = 0; i < count; i++) {
            s1.add(start, direction);
            start = start.next();
            direction = direction + (Math.random() - 0.5) * 15.0;
            if (direction < 0.0) {
                direction = direction + 360.0;
            }
            else if (direction > 360.0) {
                direction = direction - 360.0;
            }
        }
        dataset.addSeries(s1);
        return dataset;
    }

    /**
     * Creates a sample dataset.
     *
     * @param count  the item count.
     *
     * @return the dataset.
     */
    private static XYDataset createForceDataset(int count) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries s1 = new TimeSeries("Wind Force");
        RegularTimePeriod start = new Minute();
        double force = 3.0;
        for (int i = 0; i < count; i++) {
            s1.add(start, force);
            start = start.next();
            force = Math.max(0.5, force + (Math.random() - 0.5) * 0.5);
        }
        dataset.addSeries(s1);
        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @return a sample chart.
     */
    private static JFreeChart createChart() {
        XYDataset direction = createDirectionDataset(600);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Time",
            "Date",
            "Direction",
            direction,
            true,
            true,
            false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.getDomainAxis().setLowerMargin(0.0);
        plot.getDomainAxis().setUpperMargin(0.0);

        // configure the range axis to display directions...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(false);
        TickUnits units = new TickUnits();
        units.add(new NumberTickUnit(180.0, new CompassFormat()));
        units.add(new NumberTickUnit(90.0, new CompassFormat()));
        units.add(new NumberTickUnit(45.0, new CompassFormat()));
        units.add(new NumberTickUnit(22.5, new CompassFormat()));
        rangeAxis.setStandardTickUnits(units);

        // add the wind force with a secondary dataset/renderer/axis
        plot.setRangeAxis(rangeAxis);
        XYItemRenderer renderer2 = new XYAreaRenderer();
        ValueAxis axis2 = new NumberAxis("Force");
        axis2.setRange(0.0, 12.0);
        renderer2.setSeriesPaint(0, new Color(0, 0, 255, 128));
        plot.setDataset(1, createForceDataset(600));
        plot.setRenderer(1, renderer2);
        plot.setRangeAxis(1, axis2);
        plot.mapDatasetToRangeAxis(1, 1);

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
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        CompassFormatDemo1 demo = new CompassFormatDemo1("JFreeChart: CompassFormatDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
