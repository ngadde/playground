/* -------------------------
 * MultipleDatasetDemo1.java
 * -------------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing the addition and removal of multiple datasets / renderers.
 */
public class MultipleDatasetDemo1 extends ApplicationFrame {

    static class MyDemoPanel extends DemoPanel implements ActionListener {

        /** The plot. */
        private XYPlot plot;

        /** The index of the last dataset added. */
        private int datasetIndex = 0;

        /**
         * Creates a new self-contained demo panel.
         */
        public MyDemoPanel() {
            super(new BorderLayout());
            TimeSeriesCollection dataset1 = createRandomDataset("Series 1");
            JFreeChart chart = ChartFactory.createTimeSeriesChart(
                    "Multiple Dataset Demo 1", "Time", "Value", dataset1,
                    true, true, false);
            chart.setBackgroundPaint(null);
            addChart(chart);
            this.plot = (XYPlot) chart.getPlot();
            ValueAxis axis = this.plot.getDomainAxis();
            axis.setAutoRange(true);

            NumberAxis rangeAxis2 = new NumberAxis("Range Axis 2");
            rangeAxis2.setAutoRangeIncludesZero(false);

            ChartUtilities.applyCurrentTheme(chart);

            JPanel content = new JPanel(new BorderLayout());

            ChartPanel chartPanel = new ChartPanel(chart);
            content.add(chartPanel);

            JButton button1 = new JButton("Add Dataset");
            button1.setActionCommand("ADD_DATASET");
            button1.addActionListener(this);

            JButton button2 = new JButton("Remove Dataset");
            button2.setActionCommand("REMOVE_DATASET");
            button2.addActionListener(this);

            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.add(button1);
            buttonPanel.add(button2);

            content.add(buttonPanel, BorderLayout.SOUTH);
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
            add(content);
        }

        /**
         * Creates a random dataset.
         *
         * @param name  the series name.
         *
         * @return The dataset.
         */
        private TimeSeriesCollection createRandomDataset(String name) {
            TimeSeries series = new TimeSeries(name);
            double value = 100.0;
            RegularTimePeriod t = new Day();
            for (int i = 0; i < 50; i++) {
                series.add(t, value);
                t = t.next();
                value = value * (1.0 + Math.random() / 100);
            }
            return new TimeSeriesCollection(series);
        }

        /**
         * Handles a click on the button by adding new (random) data.
         *
         * @param e  the action event.
         */
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equals("ADD_DATASET")) {
                if (this.datasetIndex < 20) {
                    this.datasetIndex++;
                    this.plot.setDataset(
                        this.datasetIndex, createRandomDataset(
                            "S" + this.datasetIndex
                        )
                    );
                    this.plot.setRenderer(
                        this.datasetIndex, new StandardXYItemRenderer()
                    );
                }
            }
            else if (e.getActionCommand().equals("REMOVE_DATASET")) {
                if (this.datasetIndex >= 1) {
                    this.plot.setDataset(this.datasetIndex, null);
                    this.plot.setRenderer(this.datasetIndex, null);
                    this.datasetIndex--;
                }
            }

        }

    }


    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public MultipleDatasetDemo1(String title) {
        super(title);
        setContentPane(createDemoPanel());
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

        MultipleDatasetDemo1 demo = new MultipleDatasetDemo1(
                "JFreeChart: MultipleDatasetDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
