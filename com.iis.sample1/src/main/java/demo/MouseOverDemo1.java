/* -------------------
 * MouseOverDemo1.java
 * -------------------
 * (C) Copyright 2007, 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Paint;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * This demo uses a custom renderer to provide a mouseover highlight of an item
 * in the chart.
 */
public class MouseOverDemo1 extends ApplicationFrame {

    /**
     * A custom renderer to provide mouseover highlights.
     */
    static class MyBarRenderer extends BarRenderer {

        /** The row to highlight (-1 for none). */
        private int highlightRow = -1;

        /** The column to highlight (-1 for none). */
        private int highlightColumn = -1;

        /**
         * Sets the item to be highlighted (use (-1, -1) for no highlight).
         *
         * @param r  the row index.
         * @param c  the column index.
         */
        public void setHighlightedItem(int r, int c) {
            if (this.highlightRow == r && this.highlightColumn == c) {
                return;  // nothing to do
            }
            this.highlightRow = r;
            this.highlightColumn = c;
            notifyListeners(new RendererChangeEvent(this));
        }

        /**
         * Return a special colour for the highlighted item.
         *
         * @param row  the row index.
         * @param column  the column index.
         *
         * @return The outline paint.
         */
        public Paint getItemOutlinePaint(int row, int column) {
            if (row == this.highlightRow && column == this.highlightColumn) {
                return Color.yellow;
            }
            return super.getItemOutlinePaint(row, column);
        }

    }

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public MouseOverDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Returns a sample dataset.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset() {

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
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
            "Mouseover Demo 1",       // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainGridlinesVisible(true);

        // set the range axis to display integers only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        MyBarRenderer renderer = new MyBarRenderer();
        renderer.setDrawBarOutline(true);
        plot.setRenderer(renderer);

        ChartUtilities.applyCurrentTheme(chart);

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

        return chart;

    }

    /**
     * A panel for the demo, encapsulating the chart mouse listener.
     */
    static class MyDemoPanel extends DemoPanel implements ChartMouseListener {

        /**
         * A reference to the renderer (so we can update the item to
         * highlight).
         */
        private MyBarRenderer renderer;

        /**
         * Creates a new demo panel.
         *
         * @param renderer  the renderer.
         */
        public MyDemoPanel(MyBarRenderer renderer) {
            super(new BorderLayout());
            this.renderer = renderer;
        }

        /**
         * On a mouse move event, we check what is under the mouse pointer - if
         * it is a data item, we update the renderer so that it highlights this
         * item.
         *
         * @param event  the mouse event.
         */
        public void chartMouseMoved(ChartMouseEvent event) {
            ChartEntity entity = event.getEntity();
            if (!(entity instanceof CategoryItemEntity)) {
                this.renderer.setHighlightedItem(-1, -1);
                return;
            }
            CategoryItemEntity cie = (CategoryItemEntity) entity;
            CategoryDataset dataset = cie.getDataset();
            this.renderer.setHighlightedItem(dataset.getRowIndex(cie.getRowKey()),
                    dataset.getColumnIndex(cie.getColumnKey()));

        }

        /**
         * Handles a mouse click by ignoring it.
         *
         * @param event  info about the mouse event.
         */
        public void chartMouseClicked(ChartMouseEvent event) {
            // in this demo, we don't care about mouse clicks
        }
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());

        // we need to fetch a reference to the renderer
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        MyBarRenderer renderer = (MyBarRenderer) plot.getRenderer();
        MyDemoPanel demoPanel = new MyDemoPanel(renderer);
        ChartPanel chartPanel = new ChartPanel(chart);
        demoPanel.addChart(chart);
        chartPanel.addChartMouseListener(demoPanel);
        demoPanel.add(chartPanel);
        return demoPanel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        MouseOverDemo1 demo = new MouseOverDemo1(
                "JFreeChart: MouseoverDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
