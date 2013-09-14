/* -------------------------
 * TimePeriodValuesDemo.java
 * -------------------------
 * (C) Copyright 2003-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.text.SimpleDateFormat;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.data.time.TimePeriodValues;
import org.jfree.data.time.TimePeriodValuesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * An example of....
 */
public class TimePeriodValuesDemo1 extends ApplicationFrame {

    /**
     * A demonstration application showing how to....
     *
     * @param title  the frame title.
     */
    public TimePeriodValuesDemo1(String title) {

        super(title);

        XYDataset data1 = createDataset1();
        XYItemRenderer renderer1 = new XYBarRenderer();

        DateAxis domainAxis = new DateAxis("Date");
        domainAxis.setVerticalTickLabels(true);
        domainAxis.setTickUnit(new DateTickUnit(DateTickUnitType.HOUR, 1));
        domainAxis.setDateFormatOverride(new SimpleDateFormat("hh:mm"));
        domainAxis.setLowerMargin(0.01);
        domainAxis.setUpperMargin(0.01);
        ValueAxis rangeAxis = new NumberAxis("Value");

        XYPlot plot = new XYPlot(data1, domainAxis, rangeAxis, renderer1);

        XYDataset data2 = createDataset2();
        StandardXYItemRenderer renderer2 = new StandardXYItemRenderer(
                StandardXYItemRenderer.SHAPES_AND_LINES);
        renderer2.setBaseShapesFilled(true);

        plot.setDataset(1, data2);
        plot.setRenderer(1, renderer2);

        JFreeChart chart = new JFreeChart("Supply and Demand", plot);
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
    public XYDataset createDataset1() {

        TimePeriodValues s1 = new TimePeriodValues("Supply");
        TimePeriodValues s2 = new TimePeriodValues("Demand");
        Day today = new Day();
        for (int i = 0; i < 24; i++) {
            Minute m0 = new Minute(0, new Hour(i, today));
            Minute m1 = new Minute(15, new Hour(i, today));
            Minute m2 = new Minute(30, new Hour(i, today));
            Minute m3 = new Minute(45, new Hour(i, today));
            Minute m4 = new Minute(0, new Hour(i + 1, today));
            s1.add(new SimpleTimePeriod(m0.getStart(), m1.getStart()), Math.random());
            s2.add(new SimpleTimePeriod(m1.getStart(), m2.getStart()), Math.random());
            s1.add(new SimpleTimePeriod(m2.getStart(), m3.getStart()), Math.random());
            s2.add(new SimpleTimePeriod(m3.getStart(), m4.getStart()), Math.random());
        }

        TimePeriodValuesCollection dataset = new TimePeriodValuesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);

        return dataset;

    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return the dataset.
     */
    public XYDataset createDataset2() {

        TimePeriodValues s1 = new TimePeriodValues("WebCOINS");
        Day today = new Day();
        for (int i = 0; i < 24; i++) {
            Minute m0 = new Minute(0, new Hour(i, today));
            Minute m1 = new Minute(30, new Hour(i, today));
            Minute m2 = new Minute(0, new Hour(i + 1, today));
            s1.add(new SimpleTimePeriod(m0.getStart(), m1.getStart()), Math.random() * 2.0);
            s1.add(new SimpleTimePeriod(m1.getStart(), m2.getStart()), Math.random() * 2.0);
        }

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

        TimePeriodValuesDemo1 demo = new TimePeriodValuesDemo1(
                "Time Period Values Demo 1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
