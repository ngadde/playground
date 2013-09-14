/* -----------------------
 * MouseListenerDemo3.java
 * -----------------------
 * (C) Copyright 2003-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.LegendItemEntity;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimePeriodAnchor;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * An example showing how to pick up mouse clicks on the chart legend.
 */
public class MouseListenerDemo3 extends ApplicationFrame
        implements ChartMouseListener {

    private JFreeChart chart;

    /**
     * A demonstration application showing how to pick up mouse clicks on the
     * legend.
     *
     * @param title  the frame title.
     */
    public MouseListenerDemo3(String title) {

        super(title);

        // create a title...
        String chartTitle = "Legal & General Unit Trust Prices";
        XYDataset dataset = createDataset();

        this.chart = ChartFactory.createTimeSeriesChart(chartTitle,
                "Date", "Price Per Unit", dataset, true, true, false);

        this.chart.addSubtitle(new TextTitle(
                "Click on the legend to see series highlighted..."));

        XYPlot plot = (XYPlot) this.chart.getPlot();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));

        ChartPanel chartPanel = new ChartPanel(this.chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setMouseZoomable(true);
        chartPanel.addChartMouseListener(this);
        setContentPane(chartPanel);

    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return the dataset.
     */
    public XYDataset createDataset() {

        TimeSeries s1 = new TimeSeries("L&G European Index Trust");
        s1.add(new Month(2, 2001), 181.8);
        s1.add(new Month(3, 2001), 167.3);
        s1.add(new Month(4, 2001), 153.8);
        s1.add(new Month(5, 2001), 167.6);
        s1.add(new Month(6, 2001), 158.8);
        s1.add(new Month(7, 2001), 148.3);
        s1.add(new Month(8, 2001), 153.9);
        s1.add(new Month(9, 2001), 142.7);
        s1.add(new Month(10, 2001), 123.2);
        s1.add(new Month(11, 2001), 131.8);
        s1.add(new Month(12, 2001), 139.6);
        s1.add(new Month(1, 2002), 142.9);
        s1.add(new Month(2, 2002), 138.7);
        s1.add(new Month(3, 2002), 137.3);
        s1.add(new Month(4, 2002), 143.9);
        s1.add(new Month(5, 2002), 139.8);
        s1.add(new Month(6, 2002), 137.0);
        s1.add(new Month(7, 2002), 132.8);

        TimeSeries s2 = new TimeSeries("L&G UK Index Trust");
        s2.add(new Month(2, 2001), 129.6);
        s2.add(new Month(3, 2001), 123.2);
        s2.add(new Month(4, 2001), 117.2);
        s2.add(new Month(5, 2001), 124.1);
        s2.add(new Month(6, 2001), 122.6);
        s2.add(new Month(7, 2001), 119.2);
        s2.add(new Month(8, 2001), 116.5);
        s2.add(new Month(9, 2001), 112.7);
        s2.add(new Month(10, 2001), 101.5);
        s2.add(new Month(11, 2001), 106.1);
        s2.add(new Month(12, 2001), 110.3);
        s2.add(new Month(1, 2002), 111.7);
        s2.add(new Month(2, 2002), 111.0);
        s2.add(new Month(3, 2002), 109.6);
        s2.add(new Month(4, 2002), 113.2);
        s2.add(new Month(5, 2002), 111.6);
        s2.add(new Month(6, 2002), 108.8);
        s2.add(new Month(7, 2002), 101.6);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);

        dataset.setXPosition(TimePeriodAnchor.MIDDLE);

        return dataset;

    }

    /**
     * Receives chart mouse click events.
     *
     * @param event  the event.
     */
    public void chartMouseClicked(ChartMouseEvent event) {
        ChartEntity e = event.getEntity();
        if (e != null) {
            if (e instanceof LegendItemEntity) {
                LegendItemEntity entity = (LegendItemEntity) e;
                Comparable seriesKey = entity.getSeriesKey();
                XYPlot plot = (XYPlot) this.chart.getPlot();
                XYDataset dataset = plot.getDataset();
                XYItemRenderer renderer = plot.getRenderer();
                for (int i = 0; i < dataset.getSeriesCount(); i++) {
                    renderer.setSeriesStroke(i, new BasicStroke(1.0f));
                    if (dataset.getSeriesKey(i).equals(seriesKey)) {
                        renderer.setSeriesStroke(i, new BasicStroke(2.0f));
                    }
                }
            }
        }
    }

    /**
     * Receives chart mouse moved events.
     *
     * @param event  the event.
     */
    public void chartMouseMoved(ChartMouseEvent event) {
        // ignore
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        MouseListenerDemo3 demo = new MouseListenerDemo3(
                "JFreeChart: MouseListenerDemo3.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
