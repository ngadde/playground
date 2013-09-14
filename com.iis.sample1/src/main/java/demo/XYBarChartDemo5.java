/* --------------------
 * XYBarChartDemo5.java
 * --------------------
 * (C) Copyright 2006, 2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a bar chart using
 * an {@link XYPlot}.
 */
public class XYBarChartDemo5 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYBarChartDemo5(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel);
    }

    private static JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYBarChart(
            "US Budget Deficit",
            "Year",
            true,
            "$ Billion",
            dataset,
            PlotOrientation.VERTICAL,
            false,
            false,
            false
        );

        // then customise it a little...
        TextTitle source = new TextTitle("Source: http://www.cbo.gov/showdoc.cfm?index=1821&sequence=0#table12");
        source.setFont(new Font("Dialog", Font.PLAIN, 8));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);

        XYPlot plot = (XYPlot) chart.getPlot();

        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(true);
        renderer.setSeriesOutlinePaint(0, Color.red);
        StandardXYToolTipGenerator generator = new StandardXYToolTipGenerator(
                "{1} = {2}",
                new SimpleDateFormat("yyyy"), new DecimalFormat("0"));
        renderer.setBaseToolTipGenerator(generator);

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        axis.setLowerMargin(0.01);
        axis.setUpperMargin(0.01);

        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return A dataset.
     */
    private static IntervalXYDataset createDataset() {

        TimeSeries t1 = new TimeSeries("Budget", "Year", "$ Million");
        try {
            t1.add(new Year(1980), -74);
            t1.add(new Year(1981), -79);
            t1.add(new Year(1982), -128);
            t1.add(new Year(1983), -208);
            t1.add(new Year(1984), -185);
            t1.add(new Year(1985), -212);
            t1.add(new Year(1986), -221);
            t1.add(new Year(1987), -150);
            t1.add(new Year(1988), -155);
            t1.add(new Year(1989), -153);
            t1.add(new Year(1990), -221);
            t1.add(new Year(1991), -269);
            t1.add(new Year(1992), -290);
            t1.add(new Year(1993), -255);
            t1.add(new Year(1994), -203);
            t1.add(new Year(1995), -164);
            t1.add(new Year(1996), -107);
            t1.add(new Year(1997), -22);
            t1.add(new Year(1998), 69);
            t1.add(new Year(1999), 126);
            t1.add(new Year(2000), 236);
            t1.add(new Year(2001), 128);
            t1.add(new Year(2002), -158);
            t1.add(new Year(2003), -378);
            t1.add(new Year(2004), -412);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        TimeSeriesCollection tsc = new TimeSeriesCollection(t1);
        return tsc;

    }

    /**
     * Creates a panel for the demo.
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()));
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        XYBarChartDemo5 demo = new XYBarChartDemo5("US Budget Deficit");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
