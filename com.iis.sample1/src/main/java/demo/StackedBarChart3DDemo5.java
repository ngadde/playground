/* -------------------------
 * StackedBarChart3DDemo5.java
 * -------------------------
 * (C) Copyright 2007, by Object Refinery Limited.
 *
 * Changes
 * -------
 * 18-Jan-2007 : Version 1, based on PlotOrientationDemo1 (DG);
 *
 */

package demo;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing four plots with various inverted axis and plot orientation
 * combinations.
 */
public class StackedBarChart3DDemo5 extends ApplicationFrame {

    /** The number of charts. */
    private static int CHART_COUNT = 4;

    static class MyDemoPanel extends DemoPanel {

        /** The datasets. */
        private CategoryDataset[] datasets = new CategoryDataset[CHART_COUNT];

        /** The charts. */
        private JFreeChart[] charts = new JFreeChart[CHART_COUNT];

        /** The chart panels. */
        private ChartPanel[] panels = new ChartPanel[CHART_COUNT];

        /**
         * Creates a new self-contained demo panel.
         */
        public MyDemoPanel() {
            super(new GridLayout(2, 2));
            for (int i = 0; i < CHART_COUNT; i++) {
                this.datasets[i] = createDataset(i);
                this.charts[i] = createChart(i, this.datasets[i]);
                addChart(this.charts[i]);
                this.panels[i] = new ChartPanel(this.charts[i]);
            }
            CategoryPlot plot1 = (CategoryPlot) this.charts[1].getPlot();
            CategoryPlot plot2 = (CategoryPlot) this.charts[2].getPlot();
            CategoryPlot plot3 = (CategoryPlot) this.charts[3].getPlot();
            plot1.getRangeAxis().setInverted(true);
            plot3.getRangeAxis().setInverted(true);
            plot2.setOrientation(PlotOrientation.HORIZONTAL);
            plot3.setOrientation(PlotOrientation.HORIZONTAL);

            add(this.panels[0]);
            add(this.panels[1]);
            add(this.panels[2]);
            add(this.panels[3]);
            setPreferredSize(new Dimension(800, 600));
        }
    }

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public StackedBarChart3DDemo5(String title) {
        super(title);
        setContentPane(createDemoPanel());
    }

    /**
     * Creates a sample dataset.
     *
     * @param index  the dataset index.
     *
     * @return A dataset.
     */
    private static CategoryDataset createDataset(int index) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.0, "Series 1", "Category 1");
        dataset.addValue(2.0, "Series 1", "Category 2");
        dataset.addValue(1.5, "Series 1", "Category 3");
        dataset.addValue(1.5, "Series 1", "Category 4");
        dataset.addValue(-1.0, "Series 2", "Category 1");
        dataset.addValue(-1.9, "Series 2", "Category 2");
        dataset.addValue(-1.5, "Series 2", "Category 3");
        dataset.addValue(-1.5, "Series 2", "Category 4");
        dataset.addValue(1.0, "Series 3", "Category 1");
        dataset.addValue(1.9, "Series 3", "Category 2");
        dataset.addValue(1.5, "Series 3", "Category 3");
        dataset.addValue(1.5, "Series 3", "Category 4");
        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @param index  the chart index.
     * @param dataset  the dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(int index, CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createStackedBarChart3D(
            "Chart " + (index + 1),   // chart title
            "Category",                      // x axis label
            "Value",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            false,                    // include legend
            false,                    // tooltips
            false                     // urls
        );


        // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.getDomainAxis().setMaximumCategoryLabelLines(2);

        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    /**
     * The starting point for the demo.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        StackedBarChart3DDemo5 demo = new StackedBarChart3DDemo5(
                "JFreeChart - Stacked Bar Chart 3D Demo 5");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}

