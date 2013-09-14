/* ---------------------------
 * StackedXYBarChartDemo3.java
 * ---------------------------
 * (C) Copyright 2007, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.GradientPaint;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StackedXYBarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.TimeTableXYDataset;
import org.jfree.data.time.Year;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.StandardGradientPaintTransformer;

/**
 * A simple stacked bar chart using time series data.
 */
public class StackedXYBarChartDemo3 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public StackedXYBarChartDemo3(String title) {
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
        dataset.add(new Year(2002), 41287, "Landfilled");
        dataset.add(new Year(2003), 41096, "Landfilled");
        dataset.add(new Year(2004), 39603, "Landfilled");
        dataset.add(new Year(2005), 39693, "Landfilled");
        dataset.add(new Year(2006), 37227, "Landfilled");
        dataset.add(new Year(2002), 7717, "Recycled");
        dataset.add(new Year(2003), 8317, "Recycled");
        dataset.add(new Year(2004), 9493, "Recycled");
        dataset.add(new Year(2005), 11228, "Recycled");
        dataset.add(new Year(2006), 14941, "Recycled");
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

        DateAxis domainAxis = new DateAxis("Year");
        domainAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        domainAxis.setLowerMargin(0.01);
        domainAxis.setUpperMargin(0.01);

        NumberAxis rangeAxis = new NumberAxis("Tonnes");
        //rangeAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
        rangeAxis.setNumberFormatOverride(new DecimalFormat("0.0%"));
        StackedXYBarRenderer renderer = new StackedXYBarRenderer(0.30);
        renderer.setRenderAsPercentages(true);
        renderer.setDrawBarOutline(false);

        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                "{0} : {1} = {2} tonnes", new SimpleDateFormat("yyyy"),
                new DecimalFormat("#,##0")));

        XYPlot plot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        JFreeChart chart = new JFreeChart("Waste Management", plot);
        chart.setBackgroundPaint(Color.white);
        chart.addSubtitle(new TextTitle("St Albans City and District Council"));

        ChartUtilities.applyCurrentTheme(chart);

        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, new Color(64, 0, 0),
                0.0f, 0.0f, Color.red);
        GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, new Color(0, 64, 0),
                0.0f, 0.0f, Color.green);
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setGradientPaintTransformer(
                new StandardGradientPaintTransformer(
                        GradientPaintTransformType.HORIZONTAL));

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
        StackedXYBarChartDemo3 demo = new StackedXYBarChartDemo3(
                "JFreeChart: StackedXYBarChartDemo3");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
