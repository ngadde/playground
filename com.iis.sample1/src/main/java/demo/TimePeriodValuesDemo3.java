/* --------------------------
 * TimePeriodValuesDemo3.java
 * --------------------------
 * (C) Copyright 2004, 2007, by Object Refinery Limited.
 *
 */

package demo;

import java.text.DateFormat;
import java.util.Date;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.data.time.TimePeriodValues;
import org.jfree.data.time.TimePeriodValuesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * This demo shows a bar chart with time based data where the time periods are
 * slightly irregular.
 */
public class TimePeriodValuesDemo3 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public TimePeriodValuesDemo3(String title) {

        super(title);

        XYDataset data1 = createDataset();
        XYBarRenderer renderer = new XYBarRenderer();
        renderer.setDrawBarOutline(false);
        DateAxis domainAxis = new DateAxis("Date");
        ValueAxis rangeAxis = new NumberAxis("Value");

        XYPlot plot = new XYPlot(data1, domainAxis, rangeAxis, renderer);

        JFreeChart chart = new JFreeChart("Time Period Values Demo 3", plot);
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

        DateFormat df = DateFormat.getInstance();
        try {
            Date d0 = df.parse("11/5/2003 0:00:00.000");
            Date d1 = df.parse("11/5/2003 0:15:00.000");
            Date d2 = df.parse("11/5/2003 0:30:00.000");
            Date d3 = df.parse("11/5/2003 0:45:00.000");
            Date d4 = df.parse("11/5/2003 1:00:00.001");
            Date d5 = df.parse("11/5/2003 1:14:59.999");
            Date d6 = df.parse("11/5/2003 1:30:00.000");
            Date d7 = df.parse("11/5/2003 1:45:00.000");
            Date d8 = df.parse("11/5/2003 2:00:00.000");
            Date d9 = df.parse("11/5/2003 2:15:00.000");

            s1.add(new SimpleTimePeriod(d0, d1), 0.39);
            //s1.add(new SimpleTimePeriod(d1, d2), 0.338);
            s1.add(new SimpleTimePeriod(d2, d3), 0.225);
            s1.add(new SimpleTimePeriod(d3, d4), 0.235);
            s1.add(new SimpleTimePeriod(d4, d5), 0.238);
            s1.add(new SimpleTimePeriod(d5, d6), 0.236);
            s1.add(new SimpleTimePeriod(d6, d7), 0.25);
            s1.add(new SimpleTimePeriod(d7, d8), 0.238);
            s1.add(new SimpleTimePeriod(d8, d9), 0.215);
        }
        catch (Exception e) {
            e.printStackTrace();
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

        TimePeriodValuesDemo3 demo = new TimePeriodValuesDemo3(
                "JFreeChart: TimePeriodValuesDemo3.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
