/* ----------------------
 * XYBlockChartDemo1.java
 * ----------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.chart.renderer.PaintScale;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing a chart created using
 * the {@link XYBlockRenderer}.
 */
public class XYBlockChartDemo1 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYBlockChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    private static JFreeChart createChart(XYZDataset dataset) {
        NumberAxis xAxis = new NumberAxis("X");
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);
        xAxis.setAxisLinePaint(Color.white);
        xAxis.setTickMarkPaint(Color.white);

        NumberAxis yAxis = new NumberAxis("Y");
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        yAxis.setLowerMargin(0.0);
        yAxis.setUpperMargin(0.0);
        yAxis.setAxisLinePaint(Color.white);
        yAxis.setTickMarkPaint(Color.white);
        XYBlockRenderer renderer = new XYBlockRenderer();
        PaintScale scale = new GrayPaintScale(-2.0, 1.0);
        renderer.setPaintScale(scale);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5, 5, 5, 5));
        plot.setOutlinePaint(Color.blue);
        JFreeChart chart = new JFreeChart("XYBlockChartDemo1", plot);
        chart.removeLegend();
        NumberAxis scaleAxis = new NumberAxis("Scale");
        scaleAxis.setAxisLinePaint(Color.white);
        scaleAxis.setTickMarkPaint(Color.white);
        scaleAxis.setTickLabelFont(new Font("Dialog", Font.PLAIN, 7));
        PaintScaleLegend legend = new PaintScaleLegend(new GrayPaintScale(),
                scaleAxis);
        legend.setStripOutlineVisible(false);
        legend.setSubdivisionCount(20);
        legend.setAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        legend.setAxisOffset(5.0);
        legend.setMargin(new RectangleInsets(5, 5, 5, 5));
        legend.setFrame(new BlockBorder(Color.red));
        legend.setPadding(new RectangleInsets(10, 10, 10, 10));
        legend.setStripWidth(10);
        legend.setPosition(RectangleEdge.LEFT);
        //legend.setBackgroundPaint(new Color(120, 120, 180));
        chart.addSubtitle(legend);
        //chart.setBackgroundPaint(new Color(180, 180, 250));
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return A dataset.
     */
    private static XYZDataset createDataset() {
        return new XYZDataset() {
            public int getSeriesCount() {
                return 1;
            }
            public int getItemCount(int series) {
                return 10000;
            }
            public Number getX(int series, int item) {
                return new Double(getXValue(series, item));
            }
            public double getXValue(int series, int item) {
                return item / 100 - 50;
            }
            public Number getY(int series, int item) {
                return new Double(getYValue(series, item));
            }
            public double getYValue(int series, int item) {
                return item - (item / 100) * 100 - 50;
            }
            public Number getZ(int series, int item) {
                return new Double(getZValue(series, item));
            }
            public double getZValue(int series, int item) {
                double x = getXValue(series, item);
                double y = getYValue(series, item);
                return Math.sin(Math.sqrt(x * x + y * y) / 5.0);
            }
            public void addChangeListener(DatasetChangeListener listener) {
                // ignore - this dataset never changes
            }
            public void removeChangeListener(DatasetChangeListener listener) {
                // ignore
            }
            public DatasetGroup getGroup() {
                return null;
            }
            public void setGroup(DatasetGroup group) {
                // ignore
            }
            public Comparable getSeriesKey(int series) {
                return "sin(sqrt(x + y))";
            }
            public int indexOf(Comparable seriesKey) {
                return 0;
            }
            public DomainOrder getDomainOrder() {
                return DomainOrder.ASCENDING;
            }
        };
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
        XYBlockChartDemo1 demo = new XYBlockChartDemo1(
                "JFreeChart: XYBlockChartDemo1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
