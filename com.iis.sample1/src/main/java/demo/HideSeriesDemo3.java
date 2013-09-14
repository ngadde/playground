/* --------------------
 * HideSeriesDemo3.java
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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demonstration application showing a chart where you can show and hide
 * individual series.
 */
public class HideSeriesDemo3 extends ApplicationFrame {

    static class MyDemoPanel extends DemoPanel implements ActionListener {

        private XYItemRenderer renderer;

        /**
         * Creates a new self-contained demo panel.
         */
        public MyDemoPanel() {
            super(new BorderLayout());
            XYZDataset dataset = createSampleDataset();
            JFreeChart chart = createChart(dataset);
            addChart(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setMouseWheelEnabled(true);
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
        private XYZDataset createSampleDataset() {
            DefaultXYZDataset dataset = new DefaultXYZDataset();
            double[] x = {2.1, 2.3, 2.3};
            double[] y = {14.1, 11.1, 10.0};
            double[] z = {2.4, 2.7, 2.7};
            double[][] series = new double[][] { x, y, z };
            dataset.addSeries("Series 1", series);

            x = new double[] {2.2, 2.2, 1.8};
            y = new double[] {14.1, 11.1, 10.0};
            z = new double[] {2.2, 2.2, 2.2};
            series = new double[][] { x, y, z };
            dataset.addSeries("Series 2", series);

            x = new double[] {1.8, 1.9, 2.3, 3.8};
            y = new double[] {5.4, 4.1, 4.1, 25};
            z = new double[] {2.1, 2.2, 1.6, 4};
            series = new double[][] { x, y, z };
            dataset.addSeries("Series 3", series);

            return dataset;
        }

        /**
         * Creates a sample chart.
         *
         * @param dataset  the dataset.
         *
         * @return A sample chart.
         */
        private JFreeChart createChart(XYZDataset dataset) {
            JFreeChart result = ChartFactory.createBubbleChart(
                "Hide Series Demo 3",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
            );
            XYPlot plot = (XYPlot) result.getPlot();
            plot.setDomainPannable(true);
            plot.setRangePannable(true);
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
    public HideSeriesDemo3(String title) {
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
        HideSeriesDemo3 demo = new HideSeriesDemo3(
                "JFreeChart: HideSeriesDemo3.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
