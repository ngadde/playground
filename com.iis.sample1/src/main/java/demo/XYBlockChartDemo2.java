/* ----------------------
 * XYBlockChartDemo2.java
 * ----------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing the experimental XYBlockRenderer
 * in action.
 */
public class XYBlockChartDemo2 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYBlockChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart for the given dataset.
     *
     * @param dataset  the dataset.
     *
     * @return A chart instance.
     */
    private static JFreeChart createChart(XYZDataset dataset) {
        DateAxis xAxis = new DateAxis("Date");
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);
        xAxis.setInverted(true);
        NumberAxis yAxis = new NumberAxis("Hour");
        yAxis.setUpperMargin(0.0);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYBlockRenderer renderer = new XYBlockRenderer();
        renderer.setBlockWidth(1000.0 * 60.0 * 60.0 * 24.0);
        renderer.setBlockAnchor(RectangleAnchor.BOTTOM_LEFT);
        LookupPaintScale paintScale = new LookupPaintScale(0.5, 4.5,
                Color.white);
        paintScale.add(0.5, Color.red);
        paintScale.add(1.5, Color.green);
        paintScale.add(2.5, Color.blue);
        paintScale.add(3.5, Color.yellow);
        renderer.setPaintScale(paintScale);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5, 5, 5, 5));
        JFreeChart chart = new JFreeChart("XYBlockChartDemo2", plot);
        chart.removeLegend();
        chart.setBackgroundPaint(Color.white);
        SymbolAxis scaleAxis = new SymbolAxis(null, new String[] {"",
                "Unavailable", "Free", "Group 1", "Group 2"});
        scaleAxis.setRange(0.5, 4.5);
        scaleAxis.setPlot(new PiePlot());
        scaleAxis.setGridBandsVisible(false);
        PaintScaleLegend psl = new PaintScaleLegend(paintScale, scaleAxis);
        psl.setMargin(new RectangleInsets(3, 10, 3, 10));
        psl.setPosition(RectangleEdge.BOTTOM);
        psl.setAxisOffset(5.0);
        chart.addSubtitle(psl);
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static XYZDataset createDataset() {
        double[] xvalues = new double[2400];
        double[] yvalues = new double[2400];
        double[] zvalues = new double[2400];
        RegularTimePeriod t = new Day();
        for (int days = 0; days < 100; days++) {
            double value = 1.0;
            for (int hour = 0; hour < 24; hour++) {
                if (Math.random() < 0.1) {
                    value = Math.random() * 4.0;
                }
                xvalues[days * 24 + hour] = t.getFirstMillisecond();
                yvalues[days * 24 + hour] = hour;
                zvalues[days * 24 + hour] = value;
            }
            t = t.next();
        }
        DefaultXYZDataset dataset = new DefaultXYZDataset();
        dataset.addSeries("Series 1",
                new double[][] { xvalues, yvalues, zvalues });
        return dataset;
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
        XYBlockChartDemo2 demo = new XYBlockChartDemo2("Block Chart Demo 2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}

