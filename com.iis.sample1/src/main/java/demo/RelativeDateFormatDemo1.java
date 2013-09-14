/* ----------------------------
 * RelativeDateFormatDemo1.java
 * ----------------------------
 * (C) Copyright 2006-2009, by Object Refinery Limited.
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
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.util.RelativeDateFormat;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo for the RelativeDateFormat class.
 */
public class RelativeDateFormatDemo1 extends ApplicationFrame {

    /**
     * A demo.
     *
     * @param title  the frame title.
     */
    public RelativeDateFormatDemo1(String title) {
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
    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Exercise Chart",        // title
            "Elapsed Time",             // x-axis label
            "Beats Per Minute",   // y-axis label
            dataset,            // data
            true,               // create legend?
            true,               // generate tooltips?
            false               // generate URLs?
        );


        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        Minute base = new Minute(0, 9, 1, 10, 2006);
        RelativeDateFormat rdf = new RelativeDateFormat(base.getFirstMillisecond());
        rdf.setSecondFormatter(new DecimalFormat("00"));
        axis.setDateFormatOverride(rdf);

        ChartUtilities.applyCurrentTheme(chart);

        return chart;

    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return the dataset.
     */
    private static XYDataset createDataset() {

        TimeSeries s1 = new TimeSeries("Heart Rate");
        s1.add(new Second(45, 6, 9, 1, 10, 2006), 143);
        s1.add(new Second(33, 8, 9, 1, 10, 2006), 167);
        s1.add(new Second(10, 10, 9, 1, 10, 2006), 189);
        s1.add(new Second(19, 12, 9, 1, 10, 2006), 156);
        s1.add(new Second(5, 15, 9, 1, 10, 2006), 176);
        s1.add(new Second(12, 16, 9, 1, 10, 2006), 183);
        s1.add(new Second(6, 18, 9, 1, 10, 2006), 138);
        s1.add(new Second(11, 20, 9, 1, 10, 2006), 102);


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

        RelativeDateFormatDemo1 demo = new RelativeDateFormatDemo1(
                "JFreeChart: RelativeDateFormatDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
