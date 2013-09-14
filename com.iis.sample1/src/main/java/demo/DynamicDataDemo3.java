/* ---------------------
 * DynamicDataDemo3.java
 * ---------------------
 * (C) Copyright 2004-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.UnitType;

/**
 * A demonstration application showing a time series chart where you can
 * dynamically add (random) data by clicking on a button.
 */
public class DynamicDataDemo3 extends ApplicationFrame {

    static class MyDemoPanel extends DemoPanel implements ActionListener {

        /** The number of subplots. */
        public static final int SUBPLOT_COUNT = 3;

        /** The datasets. */
        private TimeSeriesCollection[] datasets;

        /** The most recent value added to series 1. */
        private double[] lastValue = new double[SUBPLOT_COUNT];

        /**
         * Creates a new self-contained demo panel.
         */
        public MyDemoPanel() {
            super(new BorderLayout());
            CombinedDomainXYPlot plot = new CombinedDomainXYPlot(
                    new DateAxis("Time"));
            this.datasets = new TimeSeriesCollection[SUBPLOT_COUNT];

            for (int i = 0; i < SUBPLOT_COUNT; i++) {
                this.lastValue[i] = 100.0;
                TimeSeries series = new TimeSeries("Random " + i);
                this.datasets[i] = new TimeSeriesCollection(series);
                NumberAxis rangeAxis = new NumberAxis("Y" + i);
                rangeAxis.setAutoRangeIncludesZero(false);
                XYPlot subplot = new XYPlot(this.datasets[i], null, rangeAxis,
                        new StandardXYItemRenderer());
                subplot.setBackgroundPaint(Color.lightGray);
                subplot.setDomainGridlinePaint(Color.white);
                subplot.setRangeGridlinePaint(Color.white);
                plot.add(subplot);
            }

            JFreeChart chart = new JFreeChart("Dynamic Data Demo 3", plot);
            addChart(chart);
            LegendTitle legend = (LegendTitle) chart.getSubtitle(0);
            legend.setPosition(RectangleEdge.RIGHT);
            legend.setMargin(new RectangleInsets(UnitType.ABSOLUTE, 0, 4, 0,
                    4));
            chart.setBorderPaint(Color.black);
            chart.setBorderVisible(true);

            ValueAxis axis = plot.getDomainAxis();
            axis.setAutoRange(true);
            axis.setFixedAutoRange(20000.0);  // 20 seconds

            ChartUtilities.applyCurrentTheme(chart);

            ChartPanel chartPanel = new ChartPanel(chart);
            add(chartPanel);

            JPanel buttonPanel = new JPanel(new FlowLayout());

            for (int i = 0; i < SUBPLOT_COUNT; i++) {
                JButton button = new JButton("Series " + i);
                button.setActionCommand("ADD_DATA_" + i);
                button.addActionListener(this);
                buttonPanel.add(button);
            }
            JButton buttonAll = new JButton("ALL");
            buttonAll.setActionCommand("ADD_ALL");
            buttonAll.addActionListener(this);
            buttonPanel.add(buttonAll);

            add(buttonPanel, BorderLayout.SOUTH);
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 470));
            chartPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }

        /**
         * Handles a click on the button by adding new (random) data.
         *
         * @param e  the action event.
         */
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < SUBPLOT_COUNT; i++) {
                if (e.getActionCommand().endsWith(String.valueOf(i))) {
                    Millisecond now = new Millisecond();
                    System.out.println("Now = " + now.toString());
                    this.lastValue[i] = this.lastValue[i]
                                        * (0.90 + 0.2 * Math.random());
                    this.datasets[i].getSeries(0).add(
                        new Millisecond(), this.lastValue[i]
                    );
                }
            }

            if (e.getActionCommand().equals("ADD_ALL")) {
                Millisecond now = new Millisecond();
                System.out.println("Now = " + now.toString());
                for (int i = 0; i < SUBPLOT_COUNT; i++) {
                    this.lastValue[i] = this.lastValue[i]
                                        * (0.90 + 0.2 * Math.random());
                    this.datasets[i].getSeries(0).add(
                        new Millisecond(), this.lastValue[i]
                    );
                }
            }

        }

    }

    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public DynamicDataDemo3(String title) {
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

        DynamicDataDemo3 demo = new DynamicDataDemo3(
                "JFreeChart: DynamicDataDemo3.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
