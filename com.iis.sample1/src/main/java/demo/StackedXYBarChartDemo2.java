/* ---------------------------
 * StackedXYBarChartDemo2.java
 * ---------------------------
 * (C) Copyright 2005, 2007, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StackedXYBarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.TimeTableXYDataset;
import org.jfree.data.time.Year;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * A simple stacked bar chart using time series data.
 */
public class StackedXYBarChartDemo2 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public StackedXYBarChartDemo2(String title) {
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
        dataset.add(new Year(1983), 0, "Albatrosses");
        dataset.add(new Year(1984), 2, "Albatrosses");
        dataset.add(new Year(1985), 1, "Albatrosses");
        dataset.add(new Year(1986), 1, "Albatrosses");
        dataset.add(new Year(1987), 2, "Albatrosses");
        dataset.add(new Year(1988), 2, "Albatrosses");
        dataset.add(new Year(1989), 1, "Albatrosses");
        dataset.add(new Year(1990), 5, "Albatrosses");
        dataset.add(new Year(1991), 5, "Albatrosses");
        dataset.add(new Year(1992), 2, "Albatrosses");
        dataset.add(new Year(1993), 4, "Albatrosses");
        dataset.add(new Year(1994), 3, "Albatrosses");
        dataset.add(new Year(1995), 2, "Albatrosses");
        dataset.add(new Year(1996), 1, "Albatrosses");
        dataset.add(new Year(1997), 2, "Albatrosses");
        dataset.add(new Year(1998), 1, "Albatrosses");
        dataset.add(new Year(1999), 4, "Albatrosses");
        dataset.add(new Year(2000), 6, "Albatrosses");
        dataset.add(new Year(2001), 5, "Albatrosses");
        dataset.add(new Year(2002), 4, "Albatrosses");
        dataset.add(new Year(2003), 2, "Albatrosses");
        dataset.add(new Year(1983), 21, "Aces");
        dataset.add(new Year(1984), 24, "Aces");
        dataset.add(new Year(1985), 32, "Aces");
        dataset.add(new Year(1986), 20, "Aces");
        dataset.add(new Year(1987), 28, "Aces");
        dataset.add(new Year(1988), 17, "Aces");
        dataset.add(new Year(1989), 31, "Aces");
        dataset.add(new Year(1990), 32, "Aces");
        dataset.add(new Year(1991), 29, "Aces");
        dataset.add(new Year(1992), 31, "Aces");
        dataset.add(new Year(1993), 25, "Aces");
        dataset.add(new Year(1994), 44, "Aces");
        dataset.add(new Year(1995), 35, "Aces");
        dataset.add(new Year(1996), 40, "Aces");
        dataset.add(new Year(1997), 32, "Aces");
        dataset.add(new Year(1998), 32, "Aces");
        dataset.add(new Year(1999), 30, "Aces");
        dataset.add(new Year(2000), 29, "Aces");
        dataset.add(new Year(2001), 28, "Aces");
        dataset.add(new Year(2002), 39, "Aces");
        dataset.add(new Year(2003), 32, "Aces");
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
        DateAxis domainAxis = new DateAxis("Date");
        domainAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        domainAxis.setLowerMargin(0.01);
        domainAxis.setUpperMargin(0.01);
        NumberAxis rangeAxis = new NumberAxis("Count");
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setUpperMargin(0.10);  // leave some space for item labels
        StackedXYBarRenderer renderer = new StackedXYBarRenderer(0.15);
        renderer.setDrawBarOutline(false);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                "{0} : {1} = {2}", new SimpleDateFormat("yyyy"),
                new DecimalFormat("0")));

        XYPlot plot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        JFreeChart chart = new JFreeChart("Holes-In-One / Double Eagles", plot);
        chart.removeLegend();
        chart.addSubtitle(new TextTitle("PGA Tour, 1983 to 2003"));
        TextTitle source = new TextTitle("http://www.golfdigest.com/majors/masters/index.ssf?/majors/masters/gw20040402albatross.html", new Font("Dialog", Font.PLAIN, 8));
        chart.addSubtitle(source);
        chart.setTextAntiAlias(RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
        LegendTitle legend = new LegendTitle(plot);
        legend.setBackgroundPaint(Color.white);
        legend.setFrame(new BlockBorder());
        legend.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(legend);

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
        StackedXYBarChartDemo2 demo = new StackedXYBarChartDemo2(
                "JFreeChart: Stacked XY Bar Chart Demo 2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
