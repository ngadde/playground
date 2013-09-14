/* ----------------------------
 * StackedXYAreaChartDemo2.java
 * ----------------------------
 * (C) Copyright 2005, by Object Refinery Limited.
 *
 */

package demo;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StackedXYAreaRenderer2;
import org.jfree.data.xy.CategoryTableXYDataset;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing a stacked area chart created with the 
 * <code>CategoryTableXYDataset</code>.
 */
public class StackedXYAreaChartDemo2 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public StackedXYAreaChartDemo2(String title) {
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
        CategoryTableXYDataset dataset = new CategoryTableXYDataset();
        dataset.add(0.0, 0.0, "Series 1");
        dataset.add(10.0, 20.0, "Series 1");
        dataset.add(20.0, 15.0, "Series 1");
        dataset.add(30.0, 25.0, "Series 1");
        dataset.add(40.0, 21.0, "Series 1");
        dataset.add(10.0, 9.0, "Series 2");
        dataset.add(20.0, -7.0, "Series 2");
        dataset.add(30.0, 15.0, "Series 2");
        dataset.add(40.0, 11.0, "Series 2");
        dataset.add(45.0, -10.0, "Series 2");  // no matching value in series 1
        dataset.add(50.0, 0.0, "Series 2");  // likewise
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

        JFreeChart chart = ChartFactory.createStackedXYAreaChart(
            "Stacked XY Area Chart Demo 2",  // chart title
            "X Value",                       // domain axis label
            "Y Value",                       // range axis label
            dataset,                         // data
            PlotOrientation.VERTICAL,        // the plot orientation
            true,                            // legend
            true,                            // tooltips
            false                            // urls
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        StackedXYAreaRenderer2 renderer = new StackedXYAreaRenderer2(); 
        renderer.setRoundXCoordinates(true);
        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
        plot.setRenderer(0, renderer);        
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
        StackedXYAreaChartDemo2 demo = new StackedXYAreaChartDemo2(
            "Stacked XY Area Chart Demo 2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
