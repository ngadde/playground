/* -----------------
 * ChartTiming2.java
 * -----------------
 * (C) Copyright 2002-2004, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.data.xy.XYDataset;

/**
 * Draws a scatter plot over and over for 10 seconds.  Reports on how many
 * redraws were achieved.
 * <p>
 * On my PC (SuSE Linux 8.2, JDK 1.4, 256mb RAM, 2.66ghz Pentium) I get
 * 14 charts per second.
 */
public class ChartTiming2 implements ActionListener {

    /** A flag that indicates when time is up. */
    private boolean finished;

    /**
     * Creates a new application.
     */
    public ChartTiming2() {
        // nothing to do
    }

    /**
     * Runs the test.
     */
    public void run() {

        this.finished = false;

        // create a dataset...
        XYDataset data = new SampleXYDataset2(1, 1440);

        // create a scatter chart...
        boolean withLegend = true;
        JFreeChart chart = ChartFactory.createScatterPlot(
            "Scatter plot timing", "X", "Y",
            data,
            PlotOrientation.VERTICAL,
            withLegend, false, false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setRenderer(new XYDotRenderer());

        BufferedImage image = new BufferedImage(400, 300,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        Rectangle2D chartArea = new Rectangle2D.Double(0, 0, 400, 300);

        // set up the timer...
        Timer timer = new Timer(10000, this);
        timer.setRepeats(false);
        int count = 0;
        timer.start();
        while (!this.finished) {
            chart.draw(g2, chartArea, null, null);
            System.out.println("Charts drawn..." + count);
            if (!this.finished) {
                count++;
            }
        }
        System.out.println("DONE");

    }

    /**
     * Receives notification of action events (in this case, from the Timer).
     *
     * @param event  the event.
     */
    public void actionPerformed(ActionEvent event) {
        this.finished = true;
    }

    /**
     * Starting point for the application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        ChartTiming2 app = new ChartTiming2();
        app.run();

    }

}
