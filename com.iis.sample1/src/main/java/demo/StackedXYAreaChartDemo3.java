/* ----------------------------
 * StackedXYAreaChartDemo3.java
 * ----------------------------
 * (C) Copyright 2007, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeTableXYDataset;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing a stacked area chart created with the
 * <code>TimeTableXYDataset</code>.
 */
public class StackedXYAreaChartDemo3 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public StackedXYAreaChartDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private static TableXYDataset createDataset() {
        TimeTableXYDataset dataset = new TimeTableXYDataset();
        dataset.add(new Day(14, 2, 2007), 87, "Series 1");
        dataset.add(new Day(15, 2, 2007), 67, "Series 1");
        dataset.add(new Day(16, 2, 2007), 78, "Series 1");
        dataset.add(new Day(17, 2, 2007), 55, "Series 1");
        dataset.add(new Day(18, 2, 2007), 58, "Series 1");
        dataset.add(new Day(19, 2, 2007), 60, "Series 1");

        dataset.add(new Day(14, 2, 2007), 45, "Series 2");
        dataset.add(new Day(15, 2, 2007), 67, "Series 2");
        dataset.add(new Day(16, 2, 2007), 61, "Series 2");
        dataset.add(new Day(17, 2, 2007), 58, "Series 2");
        dataset.add(new Day(18, 2, 2007), 73, "Series 2");
        dataset.add(new Day(19, 2, 2007), 64, "Series 2");

        dataset.add(new Day(14, 2, 2007), 32, "Series 3");
        dataset.add(new Day(15, 2, 2007), 38, "Series 3");
        dataset.add(new Day(16, 2, 2007), 43, "Series 3");
        dataset.add(new Day(17, 2, 2007), 12, "Series 3");
        dataset.add(new Day(18, 2, 2007), 19, "Series 3");
        dataset.add(new Day(19, 2, 2007), 26, "Series 3");

        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset for the chart.
     *
     * @return a sample chart.
     */
    private static JFreeChart createChart(TableXYDataset dataset) {

        JFreeChart chart = ChartFactory.createStackedXYAreaChart(
            "Stacked XY Area Chart Demo 3",  // chart title
            "X Value",                       // domain axis label
            "Y Value",                       // range axis label
            dataset,                         // data
            PlotOrientation.VERTICAL,        // the plot orientation
            true,                            // legend
            true,                            // tooltips
            false                            // urls
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis dateAxis = new DateAxis("Date");
        plot.setDomainAxis(dateAxis);
        dateAxis.setLowerMargin(0.0);
        dateAxis.setUpperMargin(0.0);
//        StackedXYAreaRenderer2 renderer
//                = (StackedXYAreaRenderer2) plot.getRenderer();
//        renderer.setRoundXCoordinates(true);

        ChartUtilities.applyCurrentTheme(chart);
        return chart;

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
        StackedXYAreaChartDemo3 demo = new StackedXYAreaChartDemo3(
            "Stacked XY Area Chart Demo 3");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
