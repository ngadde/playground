/* --------------------
 * PieChart3DDemo2.java
 * --------------------
 * (C) Copyright 2002-2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;

/**
 * A rotating 3D pie chart.
 */
public class PieChart3DDemo2 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public PieChart3DDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a demo chart.
     *
     * @param dataset  the dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(PieDataset dataset) {
        // create the chart...
        JFreeChart chart = ChartFactory.createPieChart3D(
            "Pie Chart 3D Demo 2",  // chart title
            dataset,                // dataset
            true,                   // include legend
            false,
            false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(270);
        plot.setDirection(Rotation.ANTICLOCKWISE);
        plot.setForegroundAlpha(0.60f);
        return chart;
    }

    /**
     * Creates a sample dataset for the demo.
     *
     * @return A sample dataset.
     */
    private static PieDataset createDataset() {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Java", new Double(43.2));
        result.setValue("Visual Basic", new Double(10.0));
        result.setValue("C/C++", new Double(17.5));
        result.setValue("PHP", new Double(32.5));
        result.setValue("Perl", new Double(12.5));
        return result;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        Rotator rotator = new Rotator((PiePlot3D) chart.getPlot());
        rotator.start();
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        PieChart3DDemo2 demo = new PieChart3DDemo2(
                "JFreeChart: PieChart3DDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}

/**
 * The rotator.
 */
class Rotator extends Timer implements ActionListener {

    /** The plot. */
    private PiePlot3D plot;

    /** The angle. */
    private int angle = 270;

    /**
     * Constructor.
     *
     * @param plot  the plot.
     */
    Rotator(PiePlot3D plot) {
        super(100, null);
        this.plot = plot;
        addActionListener(this);
    }

    /**
     * Modifies the starting angle.
     *
     * @param event  the action event.
     */
    public void actionPerformed(ActionEvent event) {
        this.plot.setStartAngle(this.angle);
        this.angle = this.angle + 1;
        if (this.angle == 360) {
            this.angle = 0;
        }
    }

}
