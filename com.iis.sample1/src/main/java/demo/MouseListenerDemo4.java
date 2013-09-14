/* -----------------------
 * MouseListenerDemo4.java
 * -----------------------
 * (C) Copyright 2005, 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

/**
 * An example showing how to convert the mouse location to chart coordinates.
 */
public class MouseListenerDemo4 extends ApplicationFrame
                                implements ChartMouseListener {

    private JFreeChart chart;

    private ChartPanel chartPanel;

    /**
     * A demonstration application showing how to pick up mouse clicks on the
     * legend.
     *
     * @param title  the frame title.
     */
    public MouseListenerDemo4(String title) {
        super(title);
        String chartTitle = "Mouse Listener Demo 4";
        XYDataset dataset = createDataset();
        this.chart = ChartFactory.createXYLineChart(chartTitle, "X", "Y",
                dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) this.chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        this.chartPanel = new ChartPanel(this.chart);
        this.chartPanel.setMouseWheelEnabled(true);
        this.chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        this.chartPanel.setMouseZoomable(true);
        this.chartPanel.addChartMouseListener(this);
        setContentPane(this.chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    public XYDataset createDataset() {
        XYSeries series = new XYSeries("Series 1");
        series.add(12.5, 11.0);
        series.add(15.0, 9.3);
        series.add(20.0, 21.0);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    /**
     * Receives chart mouse click events.
     *
     * @param event  the event.
     */
    public void chartMouseClicked(ChartMouseEvent event) {
        int mouseX = event.getTrigger().getX();
        int mouseY = event.getTrigger().getY();
        System.out.println("x = " + mouseX + ", y = " + mouseY);
        Point2D p = this.chartPanel.translateScreenToJava2D(
                new Point(mouseX, mouseY));
        XYPlot plot = (XYPlot) this.chart.getPlot();
        ChartRenderingInfo info = this.chartPanel.getChartRenderingInfo();
        Rectangle2D dataArea = info.getPlotInfo().getDataArea();

        ValueAxis domainAxis = plot.getDomainAxis();
        RectangleEdge domainAxisEdge = plot.getDomainAxisEdge();
        ValueAxis rangeAxis = plot.getRangeAxis();
        RectangleEdge rangeAxisEdge = plot.getRangeAxisEdge();
        double chartX = domainAxis.java2DToValue(p.getX(), dataArea,
                domainAxisEdge);
        double chartY = rangeAxis.java2DToValue(p.getY(), dataArea,
                rangeAxisEdge);
        System.out.println("Chart: x = " + chartX + ", y = " + chartY);
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
        MouseListenerDemo4 demo = new MouseListenerDemo4(
            "JFreeChart: MouseListenerDemo4.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
