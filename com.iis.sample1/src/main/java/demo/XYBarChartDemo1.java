/* --------------------
 * XYBarChartDemo1.java
 * --------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 */

package demo;

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
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a bar chart using
 * an {@link XYPlot}.
 */
public class XYBarChartDemo1 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYBarChartDemo1(String title) {

        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    private static JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYBarChart(
            "State Executions - USA",
            "Year",
            true,
            "Number of People",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            false,
            false
        );

        // then customise it a little...
        chart.addSubtitle(new TextTitle(
                "Source: http://www.amnestyusa.org/abolish/listbyyear.do",
                new Font("Dialog", Font.ITALIC, 10)));

        XYPlot plot = (XYPlot) chart.getPlot();
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        StandardXYToolTipGenerator generator = new StandardXYToolTipGenerator(
            "{1} = {2}", new SimpleDateFormat("yyyy"), new DecimalFormat("0"));
        renderer.setBaseToolTipGenerator(generator);
        renderer.setMargin(0.10);

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

        TimeSeries t1 = new TimeSeries("Executions", "Year", "Count");
        try {
            t1.add(new Year(1976), new Integer(0));
            t1.add(new Year(1977), new Integer(1));
            t1.add(new Year(1978), new Integer(0));
            t1.add(new Year(1979), new Integer(2));
            t1.add(new Year(1980), new Integer(0));
            t1.add(new Year(1981), new Integer(1));
            t1.add(new Year(1982), new Integer(2));
            t1.add(new Year(1983), new Integer(5));
            t1.add(new Year(1984), new Integer(21));
            t1.add(new Year(1985), new Integer(18));
            t1.add(new Year(1986), new Integer(18));
            t1.add(new Year(1987), new Integer(25));
            t1.add(new Year(1988), new Integer(11));
            t1.add(new Year(1989), new Integer(16));
            t1.add(new Year(1990), new Integer(23));
            t1.add(new Year(1991), new Integer(14));
            t1.add(new Year(1992), new Integer(31));
            t1.add(new Year(1993), new Integer(38));
            t1.add(new Year(1994), new Integer(31));
            t1.add(new Year(1995), new Integer(56));
            t1.add(new Year(1996), new Integer(45));
            t1.add(new Year(1997), new Integer(74));
            t1.add(new Year(1998), new Integer(68));
            t1.add(new Year(1999), new Integer(98));
            t1.add(new Year(2000), new Integer(85));
            t1.add(new Year(2001), new Integer(66));
            t1.add(new Year(2002), new Integer(71));
            t1.add(new Year(2003), new Integer(65));
            t1.add(new Year(2004), new Integer(59));
            t1.add(new Year(2005), new Integer(60));

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

        XYBarChartDemo1 demo = new XYBarChartDemo1("State Executions - USA");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
