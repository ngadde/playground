/* --------------------
 * HideSeriesDemo1.java
 * --------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
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
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demonstration application showing a chart where you can show and hide
 * individual series.
 */
public class HideSeriesDemo1 extends ApplicationFrame {

    static class MyDemoPanel extends DemoPanel implements ActionListener {

        private XYItemRenderer renderer;

        /**
         * Creates a new self-contained demo panel.
         */
        public MyDemoPanel() {
            super(new BorderLayout());
            XYDataset dataset = createSampleDataset();
            JFreeChart chart = createChart(dataset);
            addChart(chart);
            ChartPanel chartPanel = new ChartPanel(chart, true);
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
        private XYDataset createSampleDataset() {
            XYSeries series1 = new XYSeries("Series 1");
            series1.add(1.0, 3.3);
            series1.add(2.0, 4.4);
            series1.add(3.0, 1.7);
            XYSeries series2 = new XYSeries("Series 2");
            series2.add(1.0, 7.3);
            series2.add(2.0, 6.8);
            series2.add(3.0, 9.6);
            series2.add(4.0, 5.6);
            XYSeries series3 = new XYSeries("Series 3");
            series3.add(1.0, 17.3);
            series3.add(2.0, 16.8);
            series3.add(3.0, 19.6);
            series3.add(4.0, 15.6);
            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series1);
            dataset.addSeries(series2);
            dataset.addSeries(series3);
            return dataset;
        }

        /**
         * Creates a sample chart.
         *
         * @param dataset  the dataset.
         *
         * @return A sample chart.
         */
        private JFreeChart createChart(XYDataset dataset) {
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Hide Series Demo 1", "X", "Y", dataset,
                    PlotOrientation.VERTICAL, true, true, false);
            XYPlot plot = (XYPlot) chart.getPlot();
            this.renderer = plot.getRenderer();
            return chart;
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
    public HideSeriesDemo1(String title) {
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
        HideSeriesDemo1 demo = new HideSeriesDemo1(
                "JFreeChart: HideSeriesDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
