/* ----------------------
 * XYBlockChartDemo3.java
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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing another use for the
 * {@link XYBlockRenderer} class.
 */
public class XYBlockChartDemo3 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYBlockChartDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart for the specified dataset.
     *
     * @param dataset  the dataset.
     *
     * @return A chart instance.
     */
    private static JFreeChart createChart(XYZDataset dataset) {
        NumberAxis xAxis = new NumberAxis("X");
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);
        NumberAxis yAxis = new NumberAxis("Y");
        yAxis.setAutoRangeIncludesZero(false);
        yAxis.setInverted(true);
        yAxis.setLowerMargin(0.0);
        yAxis.setUpperMargin(0.0);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYBlockRenderer renderer = new XYBlockRenderer();
        LookupPaintScale paintScale = new LookupPaintScale(0.5, 3.5,
                Color.black);
        paintScale.add(0.5, Color.green);
        paintScale.add(1.5, Color.orange);
        paintScale.add(2.5, Color.red);
        renderer.setPaintScale(paintScale);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setForegroundAlpha(0.66f);
        plot.setAxisOffset(new RectangleInsets(5, 5, 5, 5));
        JFreeChart chart = new JFreeChart("XYBlockChartDemo3", plot);
        chart.removeLegend();
        SymbolAxis scaleAxis = new SymbolAxis(null, new String[] {"", "OK",
                "Uncertain", "Bad"});
        scaleAxis.setRange(0.5, 3.5);
        scaleAxis.setPlot(new PiePlot());
        scaleAxis.setGridBandsVisible(false);
        PaintScaleLegend psl = new PaintScaleLegend(paintScale, scaleAxis);
        psl.setAxisOffset(5.0);
        psl.setPosition(RectangleEdge.BOTTOM);
        psl.setMargin(new RectangleInsets(5, 5, 5, 5));
        chart.addSubtitle(psl);
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    /**
     * Utility method called by createDataset().
     *
     * @param data  the data array.
     * @param c  the column.
     * @param r  the row.
     * @param value  the value.
     */
    private static void setValue(double[][] data,
                                 int c, int r, double value) {

        data[0][(r - 8) * 60 + c] = c;
        data[1][(r - 8) * 60 + c] = r;
        data[2][(r - 8) * 60 + c] = value;

    }

    /**
     * Creates a sample dataset.
     *
     * @return A dataset.
     */
    private static XYZDataset createDataset() {

        double[] xvalues = new double[14 * 60];
        double[] yvalues = new double[14 * 60];
        double[] zvalues = new double[14 * 60];
        double[][] data = new double[][] {xvalues, yvalues, zvalues};

        // set the default z-value to zero throughout the data array.
        for (int c = 0; c < 60; c++) {
            for (int r = 8; r < 22; r++) {
                setValue(data, c, r, 0.0);
            }
        }

        for (int r = 8; r < 12; r++) {
            for (int c = 13; c < 48; c++) {
                setValue(data, c, r, 1.0);
            }
        }
        for (int r = 12; r < 20; r++) {
            for (int c = 23; c < 43; c++) {
                setValue(data, c, r, 1.0);
            }
        }
        setValue(data, 2, 20, 2);
        setValue(data, 5, 20, 3);
        setValue(data, 6, 20, 3);
        setValue(data, 7, 20, 3);
        setValue(data, 8, 20, 3);
        setValue(data, 9, 20, 3);
        setValue(data, 11, 20, 3);
        setValue(data, 17, 20, 2);
        setValue(data, 18, 20, 2);
        setValue(data, 19, 20, 2);
        setValue(data, 20, 20, 2);
        setValue(data, 22, 20, 2);
        setValue(data, 25, 20, 2);
        setValue(data, 28, 20, 2);
        setValue(data, 35, 20, 2);
        for (int c = 40; c < 60; c++) {
            setValue(data, c, 20, 3.0);
        }

        for (int c = 23; c < 43; c++) {
            setValue(data, c, 21, 1.0);
        }
        DefaultXYZDataset dataset = new DefaultXYZDataset();
        dataset.addSeries("Series 1", data);
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
        XYBlockChartDemo3 demo = new XYBlockChartDemo3("Block Chart Demo 3");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
