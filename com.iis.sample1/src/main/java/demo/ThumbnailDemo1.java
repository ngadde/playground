/* -------------------
 * ThumbnailDemo1.java
 * -------------------
 * (C) Copyright 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Week;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;

/**
 * This demo shows how to create thumbnail images of charts using the
 * JFreeChart.createBufferedImage(int, int, double, double, ChartRenderingInfo)
 * method.
 */
public class ThumbnailDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public ThumbnailDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a datsaet for chart 1.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset1() {

        // row keys...
        String series1 = "First";
        String series2 = "Second";
        String series3 = "Third";

        // column keys...
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        String category5 = "Category 5";

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1.0, series1, category1);
        dataset.addValue(4.0, series1, category2);
        dataset.addValue(3.0, series1, category3);
        dataset.addValue(5.0, series1, category4);
        dataset.addValue(5.0, series1, category5);

        dataset.addValue(5.0, series2, category1);
        dataset.addValue(7.0, series2, category2);
        dataset.addValue(6.0, series2, category3);
        dataset.addValue(8.0, series2, category4);
        dataset.addValue(4.0, series2, category5);

        dataset.addValue(4.0, series3, category1);
        dataset.addValue(3.0, series3, category2);
        dataset.addValue(2.0, series3, category3);
        dataset.addValue(3.0, series3, category4);
        dataset.addValue(6.0, series3, category5);

        return dataset;

    }

    /**
     * Creates chart 1.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart1(CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
            "Bar Chart Demo 1",       // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // set the range axis to display integers only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green,
                0.0f, 0.0f, new Color(0, 64, 0));
        GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        renderer.setLegendItemToolTipGenerator(
                new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(
                        Math.PI / 6.0));

        return chart;

    }

    /**
     * Creates a dataset for chart 2.
     *
     * @return A dataset.
     */
    private static PieDataset createDataset2() {

        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Java", new Double(43.2));
        result.setValue("Visual Basic", new Double(10.0));
        result.setValue("C/C++", new Double(17.5));
        result.setValue("PHP", new Double(32.5));
        result.setValue("Perl", null);  //new Double(1.0));
        return result;

    }

    /**
     * Creates chart 2.
     *
     * @param dataset  the dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart2(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart3D(
            "Pie Chart 3D Demo 1",  // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false
        );
        chart.setBackgroundPaint(Color.white);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setDarkerSides(true);
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setOutlinePaint(null);
        plot.setNoDataMessage("No data to display");
        return chart;

    }

    /**
     * Creates a dataset for chart 3.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset3() {

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(81.0, "Against all torture", "Italy");
        dataset.addValue(72.0, "Against all torture", "Great Britain");
        dataset.addValue(58.0, "Against all torture", "USA");
        dataset.addValue(48.0, "Against all torture", "Israel");
        dataset.addValue(43.0, "Against all torture", "Russia");
        dataset.addValue(23.0, "Against all torture", "India");
        dataset.addValue(59.0, "Against all torture", "Average (*)");

        dataset.addValue(5.0, "-", "Italy");
        dataset.addValue(4.0, "-", "Great Britain");
        dataset.addValue(6.0, "-", "USA");
        dataset.addValue(9.0, "-", "Israel");
        dataset.addValue(20.0, "-", "Russia");
        dataset.addValue(45.0, "-", "India");
        dataset.addValue(12.0, "-", "Average (*)");

        dataset.addValue(14.0, "Some degree permissible", "Italy");
        dataset.addValue(24.0, "Some degree permissible", "Great Britain");
        dataset.addValue(36.0, "Some degree permissible", "USA");
        dataset.addValue(43.0, "Some degree permissible", "Israel");
        dataset.addValue(37.0, "Some degree permissible", "Russia");
        dataset.addValue(32.0, "Some degree permissible", "India");
        dataset.addValue(29.0, "Some degree permissible", "Average (*)");

        return dataset;

    }

    /**
     * Creates chart 3.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart3(CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createStackedBarChart(
            "Public Opinion : Torture of Prisoners",
            "Country",                    // domain axis label
            "%",                 // range axis label
            dataset,                      // data
            PlotOrientation.HORIZONTAL,   // orientation
            false,                        // include legend
            true,                         // tooltips?
            false                         // URLs?
        );

        chart.getTitle().setMargin(2.0, 0.0, 0.0, 0.0);

        TextTitle tt = new TextTitle(
                "Source: http://news.bbc.co.uk/1/hi/world/6063386.stm",
                new Font("Dialog", Font.PLAIN, 11));
        tt.setPosition(RectangleEdge.BOTTOM);
        tt.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        tt.setMargin(0.0, 0.0, 4.0, 4.0);
        chart.addSubtitle(tt);

        TextTitle t = new TextTitle(
                "(*) Across 27,000 respondents in 25 countries",
                new Font("Dialog", Font.PLAIN, 11));
        t.setPosition(RectangleEdge.BOTTOM);
        t.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        t.setMargin(4.0, 0.0, 2.0, 4.0);
        chart.addSubtitle(t);

        // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        LegendItemCollection items = new LegendItemCollection();
        items.add(new LegendItem("Against all torture", null, null, null,
                new Rectangle2D.Double(-6.0, -3.0, 12.0, 6.0), Color.green));
        items.add(new LegendItem("Some degree permissible", null, null, null,
                new Rectangle2D.Double(-6.0, -3.0, 12.0, 6.0), Color.red));
        plot.setFixedLegendItems(items);
        plot.setInsets(new RectangleInsets(5, 5, 5, 20));
        LegendTitle legend = new LegendTitle(plot);
        legend.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(legend);

        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setUpperMargin(0.0);

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.green,
                0.0f, 0.0f, new Color(0, 64, 0));
        Paint gp1 = new Color(0, 0, 0, 0);
        GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        return chart;

    }

    /**
     * Creates a dataset for chart 4.
     *
     * @return A dataset.
     */
    private static XYDataset createDataset4() {

        YIntervalSeries series1 = new YIntervalSeries("Series 1");
        YIntervalSeries series2 = new YIntervalSeries("Series 2");
        RegularTimePeriod t = new Week();
        double y1 = 100.0;
        double y2 = 100.0;
        for (int i = 0; i <= 52; i++) {
            double dev1 = (0.05 * i);
            series1.add(t.getFirstMillisecond(), y1, y1 - dev1, y1 + dev1);
            y1 = y1 + Math.random() - 0.45;

            double dev2 = (0.07 * i);
            series2.add(t.getFirstMillisecond(), y2, y2 - dev2, y2 + dev2);
            y2 = y2 + Math.random() - 0.55;
            t = t.next();
        }

        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;

    }

    /**
     * Creates chart 4.
     *
     * @param dataset  the data for the chart.
     *
     * @return a chart.
     */
    private static JFreeChart createChart4(XYDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Projected Values - Test",          // chart title
            "Date",                   // x axis label
            "Index Projection",       // y axis label
            dataset,                  // data
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setInsets(new RectangleInsets(5, 5, 5, 20));
        plot.setBackgroundPaint(Color.lightGray);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        DeviationRenderer renderer = new DeviationRenderer(true, false);
        renderer.setSeriesStroke(0, new BasicStroke(3.0f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        renderer.setSeriesStroke(0, new BasicStroke(3.0f,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        renderer.setSeriesFillPaint(0, new Color(255, 200, 200));
        renderer.setSeriesFillPaint(1, new Color(200, 200, 255));
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(false);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;

    }

    /**
     * Creates a dataset for chart 5.
     *
     * @return The dataset.
     */
    private static IntervalXYDataset createDataset5() {
        HistogramDataset dataset = new HistogramDataset();
        double[] values = new double[1000];
        Random generator = new Random(12345678L);
        for (int i = 0; i < 1000; i++) {
            values[i] = generator.nextGaussian() + 5;
        }
        dataset.addSeries("H1", values, 100, 2.0, 8.0);
        values = new double[1000];
        for (int i = 0; i < 1000; i++) {
            values[i] = generator.nextGaussian() + 7;
        }
        dataset.addSeries("H2", values, 100, 4.0, 10.0);
        return dataset;
    }

    /**
     * Creates chart 5.
     *
     * @param dataset  a dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart5(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createHistogram(
            "Histogram Demo 1",
            null,
            null,
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        chart.setBackgroundPaint(Color.white);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setForegroundAlpha(0.85f);
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        return chart;
    }

    /**
     * Creates a dataset for chart 6.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset6() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(212, "Classes", "JDK 1.0");
        dataset.addValue(504, "Classes", "JDK 1.1");
        dataset.addValue(1520, "Classes", "SDK 1.2");
        dataset.addValue(1842, "Classes", "SDK 1.3");
        dataset.addValue(2991, "Classes", "SDK 1.4");
        return dataset;
    }

    /**
     * Creates chart 6.
     *
     * @param dataset  a dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart6(CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createLineChart(
            "Java Standard Class Library",   // chart title
            "Release",                       // domain axis label
            "Class Count",                   // range axis label
            dataset,                         // data
            PlotOrientation.VERTICAL,        // orientation
            false,                           // include legend
            true,                            // tooltips
            false                            // urls
        );

        chart.addSubtitle(new TextTitle("Number of Classes By Release"));
        TextTitle source = new TextTitle(
            "Source: Java In A Nutshell (4th Edition) "
            + "by David Flanagan (O'Reilly)"
        );
        source.setFont(new Font("SansSerif", Font.PLAIN, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);

        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);

        // customise the range axis...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setUpperMargin(0.15);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // customise the renderer...
        LineAndShapeRenderer renderer
            = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseShapesVisible(true);
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        renderer.setBaseFillPaint(Color.white);
        renderer.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);

        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 3));

        // here we just create thumbnail images and display them within
        // JButton instances in a grid layout...the buttons aren't wired up
        // to do anything
        JFreeChart chart1 = createChart1(createDataset1());
        ChartUtilities.applyCurrentTheme(chart1);
        BufferedImage thumb1 = chart1.createBufferedImage(120, 80, 360, 240,
                null);
        ImageIcon image1 = new ImageIcon(thumb1);
        mainPanel.add(new JButton(image1));


        JFreeChart chart2 = createChart2(createDataset2());
        ChartUtilities.applyCurrentTheme(chart2);
        BufferedImage thumb2 = chart2.createBufferedImage(120, 80, 360, 240,
                null);
        ImageIcon image2 = new ImageIcon(thumb2);
        mainPanel.add(new JButton(image2));

        JFreeChart chart3 = createChart3(createDataset3());
        ChartUtilities.applyCurrentTheme(chart3);
        BufferedImage thumb3 = chart3.createBufferedImage(120, 80, 360, 240,
                null);
        ImageIcon image3 = new ImageIcon(thumb3);
        mainPanel.add(new JButton(image3));

        JFreeChart chart4 = createChart4(createDataset4());
        ChartUtilities.applyCurrentTheme(chart4);
        BufferedImage thumb4 = chart4.createBufferedImage(120, 80, 360, 240,
                null);
        ImageIcon image4 = new ImageIcon(thumb4);
        mainPanel.add(new JButton(image4));

        JFreeChart chart5 = createChart5(createDataset5());
        ChartUtilities.applyCurrentTheme(chart5);
        BufferedImage thumb5 = chart5.createBufferedImage(120, 80, 360, 240,
                null);
        ImageIcon image5 = new ImageIcon(thumb5);
        mainPanel.add(new JButton(image5));

        JFreeChart chart6 = createChart6(createDataset6());
        ChartUtilities.applyCurrentTheme(chart6);
        BufferedImage thumb6 = chart6.createBufferedImage(120, 80, 360, 240,
                null);
        ImageIcon image6 = new ImageIcon(thumb6);
        mainPanel.add(new JButton(image6));

        return mainPanel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ThumbnailDemo1 demo = new ThumbnailDemo1(
                "JFreeChart: ThumbnailDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
