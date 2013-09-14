/* --------------------------
 * TimePeriodValuesDemo2.java
 * --------------------------
 * (C) Copyright 2003, 2004, by Object Refinery Limited.
 *
 */

package demo;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.data.time.TimePeriodValues;
import org.jfree.data.time.TimePeriodValuesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * An example of....
 */
public class TimePeriodValuesDemo2 extends ApplicationFrame {

    /**
     * A demonstration application showing how to....
     *
     * @param title  the frame title.
     */
    public TimePeriodValuesDemo2(String title) {

        super(title);

        XYDataset data1 = createDataset();
        XYItemRenderer renderer1 = new XYBarRenderer();

        DateAxis domainAxis = new DateAxis("Date");
        ValueAxis rangeAxis = new NumberAxis("Value");

        XYPlot plot = new XYPlot(data1, domainAxis, rangeAxis, renderer1);

        JFreeChart chart = new JFreeChart("Time Period Values Demo", plot);
        ChartUtilities.applyCurrentTheme(chart);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setMouseZoomable(true);
        setContentPane(chartPanel);

    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return the dataset.
     */
    public XYDataset createDataset() {

        TimePeriodValues s1 = new TimePeriodValues("Series 1");
        Day d1 = new Day();
        Day d2 = (Day) d1.next();
        Day d3 = (Day) d2.next();
        Day d4 = (Day) d3.next();
        Day d5 = (Day) d4.next();
        Day d6 = (Day) d5.next();
        Day d7 = (Day) d6.next();

        s1.add(new SimpleTimePeriod(d6.getStart(), d6.getEnd()), 74.95);
        s1.add(new SimpleTimePeriod(d1.getStart(), d2.getEnd()), 55.75);
        s1.add(new SimpleTimePeriod(d7.getStart(), d7.getEnd()), 90.45);
        s1.add(new SimpleTimePeriod(d3.getStart(), d5.getEnd()), 105.75);

        TimePeriodValuesCollection dataset = new TimePeriodValuesCollection();
        dataset.addSeries(s1);

        return dataset;

    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        TimePeriodValuesDemo2 demo = new TimePeriodValuesDemo2("Time Period Values Demo 2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
