/* ----------------------------
 * RelativeDateFormatDemo2.java
 * ----------------------------
 * (C) Copyright 2007, 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.text.DecimalFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.util.RelativeDateFormat;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo for the RelativeDateFormat class.
 */
public class RelativeDateFormatDemo2 extends ApplicationFrame {

    /**
     * A demo.
     *
     * @param title  the frame title.
     */
    public RelativeDateFormatDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart.
     *
     * @param dataset  a dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(IntervalXYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYBarChart(
            "RelativeDateFormat Demo 2",        // title
            "Date ",             // x-axis label
            true,
            "Time To Complete",   // y-axis label
            dataset,            // data
            PlotOrientation.VERTICAL,
            true,               // create legend?
            true,               // generate tooltips?
            false               // generate URLs?
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYBarRenderer r = (XYBarRenderer) plot.getRenderer();
        r.setDrawBarOutline(false);

        DateAxis rangeAxis = new DateAxis();
        RelativeDateFormat rdf = new RelativeDateFormat();
        rdf.setShowZeroDays(false);
        rdf.setSecondFormatter(new DecimalFormat("00"));
        rangeAxis.setDateFormatOverride(rdf);
        plot.setRangeAxis(rangeAxis);

        ChartUtilities.applyCurrentTheme(chart);

        return chart;

    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return the dataset.
     */
    private static IntervalXYDataset createDataset() {

        TimeSeries s1 = new TimeSeries("Completion");
        s1.add(new Day(19, 1, 2007), 3343000);
        s1.add(new Day(20, 1, 2007), 3420000);
        s1.add(new Day(21, 1, 2007), 3515000);
        s1.add(new Day(22, 1, 2007), 3315000);
        s1.add(new Day(23, 1, 2007), 3490000);
        s1.add(new Day(24, 1, 2007), 3556000);
        s1.add(new Day(25, 1, 2007), 3383000);
        s1.add(new Day(26, 1, 2007), 3575000);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

        return dataset;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        RelativeDateFormatDemo2 demo = new RelativeDateFormatDemo2(
                "JFreeChart: RelativeDateFormatDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
