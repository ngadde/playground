/* ---------------------------
 * CombinedTimeSeriesDemo.java
 * ---------------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CombinedRangeXYPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.date.MonthConstants;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing a combined time series chart where one of the subplots is an
 * overlaid chart.
 */
public class CombinedTimeSeriesDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo application.
     *
     * @param title  the frame title.
     */
    public CombinedTimeSeriesDemo1(String title) {
        super(title);
        setContentPane(createDemoPanel());
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        // create the first dataset...
        TimeSeries series1 = new TimeSeries("Annual");
        series1.add(new Year(1998), 80.0);
        series1.add(new Year(1999), 85.0);
        series1.add(new Year(2000), 87.6);
        TimeSeriesCollection dataset1 = new TimeSeriesCollection(series1);

        // create dataset 2...
        TimeSeries series2A = new TimeSeries("Monthly A");
        series2A.add(new Month(MonthConstants.JULY, 2000), 85.8);
        series2A.add(new Month(MonthConstants.AUGUST, 2000), 85.8);
        series2A.add(new Month(MonthConstants.SEPTEMBER, 2000), 85.8);
        series2A.add(new Month(MonthConstants.OCTOBER, 2000), 86.5);
        series2A.add(new Month(MonthConstants.NOVEMBER, 2000), 86.5);
        series2A.add(new Month(MonthConstants.DECEMBER, 2000), 86.5);
        series2A.add(new Month(MonthConstants.JANUARY, 2001), 87.7);
        series2A.add(new Month(MonthConstants.FEBRUARY, 2001), 87.7);
        series2A.add(new Month(MonthConstants.MARCH, 2001), 87.7);
        series2A.add(new Month(MonthConstants.APRIL, 2001), 88.5);
        series2A.add(new Month(MonthConstants.MAY, 2001), 88.5);
        series2A.add(new Month(MonthConstants.JUNE, 2001), 88.5);
        series2A.add(new Month(MonthConstants.JULY, 2001), 90.0);
        series2A.add(new Month(MonthConstants.AUGUST, 2001), 90.0);
        series2A.add(new Month(MonthConstants.SEPTEMBER, 2001), 90.0);
        series2A.add(new Month(MonthConstants.OCTOBER, 2001), 90.0);
        series2A.add(new Month(MonthConstants.NOVEMBER, 2001), 90.0);
        series2A.add(new Month(MonthConstants.DECEMBER, 2001), 90.0);
        series2A.add(new Month(MonthConstants.JANUARY, 2002), 90.0);
        series2A.add(new Month(MonthConstants.FEBRUARY, 2002), 90.0);
        series2A.add(new Month(MonthConstants.MARCH, 2002), 90.0);
        series2A.add(new Month(MonthConstants.APRIL, 2002), 90.0);
        series2A.add(new Month(MonthConstants.MAY, 2002), 90.0);
        series2A.add(new Month(MonthConstants.JUNE, 2002), 90.0);

        TimeSeries series2B = new TimeSeries("Monthly B");
        series2B.add(new Month(MonthConstants.JULY, 2000), 83.8);
        series2B.add(new Month(MonthConstants.AUGUST, 2000), 83.8);
        series2B.add(new Month(MonthConstants.SEPTEMBER, 2000), 83.8);
        series2B.add(new Month(MonthConstants.OCTOBER, 2000), 84.5);
        series2B.add(new Month(MonthConstants.NOVEMBER, 2000), 84.5);
        series2B.add(new Month(MonthConstants.DECEMBER, 2000), 84.5);
        series2B.add(new Month(MonthConstants.JANUARY, 2001), 85.7);
        series2B.add(new Month(MonthConstants.FEBRUARY, 2001), 85.7);
        series2B.add(new Month(MonthConstants.MARCH, 2001), 85.7);
        series2B.add(new Month(MonthConstants.APRIL, 2001), 86.5);
        series2B.add(new Month(MonthConstants.MAY, 2001), 86.5);
        series2B.add(new Month(MonthConstants.JUNE, 2001), 86.5);
        series2B.add(new Month(MonthConstants.JULY, 2001), 88.0);
        series2B.add(new Month(MonthConstants.AUGUST, 2001), 88.0);
        series2B.add(new Month(MonthConstants.SEPTEMBER, 2001), 88.0);
        series2B.add(new Month(MonthConstants.OCTOBER, 2001), 88.0);
        series2B.add(new Month(MonthConstants.NOVEMBER, 2001), 88.0);
        series2B.add(new Month(MonthConstants.DECEMBER, 2001), 88.0);
        series2B.add(new Month(MonthConstants.JANUARY, 2002), 88.0);
        series2B.add(new Month(MonthConstants.FEBRUARY, 2002), 88.0);
        series2B.add(new Month(MonthConstants.MARCH, 2002), 88.0);
        series2B.add(new Month(MonthConstants.APRIL, 2002), 88.0);
        series2B.add(new Month(MonthConstants.MAY, 2002), 88.0);
        series2B.add(new Month(MonthConstants.JUNE, 2002), 88.0);

        TimeSeriesCollection dataset2 = new TimeSeriesCollection();
        dataset2.addSeries(series2A);
        dataset2.addSeries(series2B);

        // create dataset 3...
        TimeSeries series3 = new TimeSeries("XXX");
        series3.add(new Month(MonthConstants.JULY, 2000), 81.5);
        series3.add(new Month(MonthConstants.AUGUST, 2000), 86.0);
        series3.add(new Month(MonthConstants.SEPTEMBER, 2000), 82.0);
        series3.add(new Month(MonthConstants.OCTOBER, 2000), 89.5);
        series3.add(new Month(MonthConstants.NOVEMBER, 2000), 88.0);
        series3.add(new Month(MonthConstants.DECEMBER, 2000), 88.0);
        series3.add(new Month(MonthConstants.JANUARY, 2001), 90.0);
        series3.add(new Month(MonthConstants.FEBRUARY, 2001), 89.5);
        series3.add(new Month(MonthConstants.MARCH, 2001), 90.2);
        series3.add(new Month(MonthConstants.APRIL, 2001), 90.6);
        series3.add(new Month(MonthConstants.MAY, 2001), 87.5);
        series3.add(new Month(MonthConstants.JUNE, 2001), 91.0);
        series3.add(new Month(MonthConstants.JULY, 2001), 89.7);
        series3.add(new Month(MonthConstants.AUGUST, 2001), 87.0);
        series3.add(new Month(MonthConstants.SEPTEMBER, 2001), 91.2);
        series3.add(new Month(MonthConstants.OCTOBER, 2001), 84.0);
        series3.add(new Month(MonthConstants.NOVEMBER, 2001), 90.0);
        series3.add(new Month(MonthConstants.DECEMBER, 2001), 92.0);
        TimeSeriesCollection dataset3 = new TimeSeriesCollection(series3);

        XYItemRenderer renderer1 = new XYBarRenderer(0.20);
        renderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                "{0} ({1}, {2})", new SimpleDateFormat("yyyy"),
                new DecimalFormat("0.00")));
        XYPlot plot1 = new XYPlot(
            dataset1,
            new DateAxis("Date"),
            null,  // will use shared range axis
            renderer1
        );

        XYItemRenderer renderer2 = new XYAreaRenderer();

        XYPlot plot2 = new XYPlot(
            dataset2,
            new DateAxis("Date"),
            null,  // will use shared range axis
            renderer2
        );

        StandardXYItemRenderer renderer3 = new StandardXYItemRenderer(
                StandardXYItemRenderer.SHAPES_AND_LINES);
        renderer3.setBaseShapesFilled(true);

        plot2.setDataset(1, dataset3);
        plot2.setRenderer(1, renderer3);
        plot2.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        NumberAxis rangeAxis = new NumberAxis("Value");
        rangeAxis.setAutoRangeIncludesZero(false);
        CombinedRangeXYPlot combinedPlot = new CombinedRangeXYPlot(rangeAxis);
        combinedPlot.add(plot1, 1);
        combinedPlot.add(plot2, 4);

        // create the chart...
        JFreeChart chart = new JFreeChart(
            "Sample Combined Plot",
            JFreeChart.DEFAULT_TITLE_FONT,
            combinedPlot,
            true  // legend
        );
        ChartUtilities.applyCurrentTheme(chart);
        // add the chart to a panel...
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.addChartMouseListener(new ChartMouseListener() {
            public void chartMouseClicked(ChartMouseEvent event) {
                System.out.println(event.getEntity());
            }
            public void chartMouseMoved(ChartMouseEvent event) {
                System.out.println(event.getEntity());
            }

        });
        return chartPanel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        CombinedTimeSeriesDemo1 demo = new CombinedTimeSeriesDemo1(
                "JFreeChart: CombinedTimeSeriesDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
