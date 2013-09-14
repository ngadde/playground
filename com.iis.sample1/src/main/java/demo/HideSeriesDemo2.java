/* --------------------
 * HideSeriesDemo2.java
 * --------------------
 * (C) Copyright 2007-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StatisticalLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demonstration application showing a chart where you can show and hide
 * individual series.
 */
public class HideSeriesDemo2 extends ApplicationFrame {

    static class MyDemoPanel extends DemoPanel implements ActionListener {

        private CategoryItemRenderer renderer;

        /**
         * Creates a new self-contained demo panel.
         */
        public MyDemoPanel() {
            super(new BorderLayout());
            CategoryDataset dataset = createSampleDataset();
            JFreeChart chart = createChart(dataset);
            addChart(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            JPanel boxPanel = new JPanel();
            JCheckBox box1 = new JCheckBox("Series 1");
            box1.setActionCommand("S1");
            box1.addActionListener(this);
            box1.setSelected(true);
            JCheckBox box2 = new JCheckBox("Series 2");
            box2.setActionCommand("S2");
            box2.addActionListener(this);
            box2.setSelected(true);
            JCheckBox box3 = new JCheckBox("Series 3");
            box3.setActionCommand("S3");
            box3.addActionListener(this);
            box3.setSelected(true);
            boxPanel.add(box1);
            boxPanel.add(box2);
            boxPanel.add(box3);

            add(chartPanel);
            add(boxPanel, BorderLayout.SOUTH);
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        }

        /**
         * Creates a sample dataset.
         *
         * @return A dataset.
         */
        private CategoryDataset createSampleDataset() {
            DefaultStatisticalCategoryDataset dataset
                    = new DefaultStatisticalCategoryDataset();
            dataset.add(10.0, 2.4, "Row 1", "Column 1");
            dataset.add(15.0, 4.4, "Row 1", "Column 2");
            dataset.add(13.0, 2.1, "Row 1", "Column 3");
            dataset.add(7.0, 1.3, "Row 1", "Column 4");
            dataset.add(22.0, 2.4, "Row 2", "Column 1");
            dataset.add(18.0, 4.4, "Row 2", "Column 2");
            dataset.add(28.0, 2.1, "Row 2", "Column 3");
            dataset.add(7.0, 1.3, "Row 2", "Column 4");
            dataset.add(2.0, 2.4, "Row 3", "Column 1");
            dataset.add(8.0, 4.4, "Row 3", "Column 2");
            dataset.add(8.0, 2.1, "Row 3", "Column 3");
            dataset.add(7.0, 1.3, "Row 3", "Column 4");
            return dataset;
        }

        /**
         * Creates a sample chart.
         *
         * @param dataset  the dataset.
         *
         * @return A sample chart.
         */
        private JFreeChart createChart(CategoryDataset dataset) {
            JFreeChart result = ChartFactory.createAreaChart(
                "Hide Series Demo 2",
                "Category",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
            );
            CategoryPlot plot = (CategoryPlot) result.getPlot();
            plot.setRenderer(new StatisticalLineAndShapeRenderer());
            this.renderer = plot.getRenderer(0);
            return result;
        }
        /**
         * Handles a click on the button by adding new (random) data.
         *
         * @param e  the action event.
         */
        public void actionPerformed(ActionEvent e) {
            int series = -1;
            if (e.getActionCommand().equals("S1")) {
                series = 0;
            }
            else if (e.getActionCommand().equals("S2")) {
                series = 1;
            }
            else if (e.getActionCommand().equals("S3")) {
                series = 2;
            }
            if (series >= 0) {
                boolean visible = this.renderer.getItemVisible(series, 0);
                this.renderer.setSeriesVisible(series, new Boolean(!visible));
            }
        }

    }

    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public HideSeriesDemo2(String title) {
        super(title);
        setContentPane(new MyDemoPanel());
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
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        HideSeriesDemo2 demo = new HideSeriesDemo2(
                "JFreeChart: HideSeriesDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
