/* ------------------
 * PieChartDemo3.java
 * ------------------
 * (C) Copyright 2003-2006, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A pie chart with no data, to demonstrate the use of the setNoDataMessage() 
 * method.
 */
public class PieChartDemo3 extends ApplicationFrame {

    /**
     * Default constructor.
     *
     * @param title  the frame title.
     */
    public PieChartDemo3(String title) {
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
        JFreeChart chart = ChartFactory.createPieChart(
           "Pie Chart Demo 3",  // chart title
            dataset,                // data
            true,                // include legend
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setNoDataMessage("No data available so we go into this really "
            + "long spiel about what that means and it runs off the end of the "
            + "line but what can you do about that!");
        plot.setNoDataMessageFont(new Font("Serif", Font.ITALIC, 10));
        plot.setNoDataMessagePaint(Color.red);
        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * 
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(new DefaultPieDataset());
        return new ChartPanel(chart);
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        PieChartDemo3 demo = new PieChartDemo3("Pie Chart Demo 3");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
