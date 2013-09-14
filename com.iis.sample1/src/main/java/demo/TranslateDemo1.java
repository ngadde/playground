/* -------------------
 * TranslateDemo1.java
 * -------------------
 * (C) Copyright 2006, 2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo that uses a "wrapper" dataset that provides a translation of the
 * underlying dataset.
 */
public class TranslateDemo1 extends ApplicationFrame {

    private static class MyDemoPanel extends DemoPanel
                                   implements ChangeListener {

        private TimeSeries series;

        private ChartPanel chartPanel;

        private JFreeChart chart;

        private JSlider slider;

        private TranslatingXYDataset dataset;

        static class TranslatingXYDataset extends AbstractXYDataset
                implements XYDataset, DatasetChangeListener {
            private XYDataset underlying;
            private double translate;

            /**
             * Creates a new <code>TranslatingXYDataset</code> class that
             * applies a dynamically updateable translation to the underlying
             * dataset.
             *
             * @param underlying  the underlying dataset (<code>null</code> not
             *     permitted).
             */
            public TranslatingXYDataset(XYDataset underlying) {
                this.underlying = underlying;
                this.underlying.addChangeListener(this);
                this.translate = 0.0;
            }

            /**
             * Returns the current translation factor.
             *
             * @return The translation factor.
             */
            public double getTranslate() {
                return this.translate;
            }

            /**
             * Sets the translation factor.
             *
             * @param t  the translation factor.
             */
            public void setTranslate(double t) {
                this.translate = t;
                fireDatasetChanged();
            }
            /**
             * Returns the item count.
             *
             * @param series  the series.
             *
             * @return The item count.
             */
            public int getItemCount(int series) {
                return this.underlying.getItemCount(series);
            }

            /**
             * Returns the x-value.
             *
             * @param series  the series index.
             * @param item  the item index.
             *
             * @return The x-value.
             */
            public double getXValue(int series, int item) {
                return this.underlying.getXValue(series, item) + this.translate;
            }

            /**
             * Returns the x-value.
             *
             * @param series  the series index.
             * @param item  the item index.
             *
             * @return The x-value.
             */
            public Number getX(int series, int item) {
                return new Double(getXValue(series, item));
            }

            /**
             * Returns the y-value.
             *
             * @param series  the series index.
             * @param item  the item index.
             *
             * @return The y-value.
             */
            public Number getY(int series, int item) {
                return new Double(getYValue(series, item));
            }

            /**
             * Returns the y-value.
             *
             * @param series  the series index.
             * @param item  the item index.
             *
             * @return The y-value.
             */
            public double getYValue(int series, int item) {
                return this.underlying.getYValue(series, item);
            }

            /**
             * Returns the series count.
             *
             * @return The series count.
             */
            public int getSeriesCount() {
                return this.underlying.getSeriesCount();
            }

            /**
             * Returns the key for a series.
             *
             * @param series  the series index.
             *
             * @return The key.
             */
            public Comparable getSeriesKey(int series) {
                return this.underlying.getSeriesKey(series);
            }

            /**
             * Handles a dataset change.
             *
             * @param event  info about the event.
             */
            public void datasetChanged(DatasetChangeEvent event) {
                // underlying dataset has changed, so notify our listeners
                fireDatasetChanged();
            }
        }

        /**
         * Creates a new demo panel.
         */
        public MyDemoPanel() {
            super(new BorderLayout());
            this.chart = createChart();
            addChart(this.chart);
            this.chartPanel = new ChartPanel(this.chart);
            this.chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));
            this.chartPanel.setDomainZoomable(true);
            this.chartPanel.setRangeZoomable(true);
            Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(4, 4, 4, 4),
                BorderFactory.createEtchedBorder()
            );
            this.chartPanel.setBorder(border);
            add(this.chartPanel);

            JPanel dashboard = new JPanel(new BorderLayout());
            dashboard.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
            // make the slider units "minutes"
            this.slider = new JSlider(-200, 200, 0);
            this.slider.setPaintLabels(true);
            this.slider.setMajorTickSpacing(50);
            this.slider.setPaintTicks(true);
            this.slider.addChangeListener(this);
            dashboard.add(this.slider);
            add(dashboard, BorderLayout.SOUTH);
        }

        /**
         * Creates the demo chart.
         *
         * @return The chart.
         */
        private JFreeChart createChart() {

            XYDataset dataset1 = createDataset("Random 1", 100.0, new Minute(),
                    200);

            JFreeChart chart1 = ChartFactory.createTimeSeriesChart(
                "Translate Demo 1",
                "Time of Day",
                "Value",
                dataset1,
                true,
                true,
                false
            );

            XYPlot plot = (XYPlot) chart1.getPlot();
            plot.setOrientation(PlotOrientation.VERTICAL);

            plot.setDomainCrosshairVisible(true);
            plot.setDomainCrosshairLockedOnData(false);
            plot.setRangeCrosshairVisible(false);

            // fix the range
            DateAxis axis = (DateAxis) plot.getDomainAxis();
            Range range = DatasetUtilities.findDomainBounds(this.dataset);
            axis.setRange(range);
            return chart1;
        }


        /**
         * Creates a sample dataset.
         *
         * @param name  the dataset name.
         * @param base  the starting value.
         * @param start  the starting period.
         * @param count  the number of values to generate.
         *
         * @return The dataset.
         */
        private XYDataset createDataset(String name, double base,
                                        RegularTimePeriod start, int count) {

            this.series = new TimeSeries(name);
            RegularTimePeriod period = start;
            double value = base;
            for (int i = 0; i < count; i++) {
                this.series.add(period, value);
                period = period.next();
                value = value * (1 + (Math.random() - 0.495) / 10.0);
            }

            TimeSeriesCollection tsc = new TimeSeriesCollection();
            tsc.addSeries(this.series);
            this.dataset = new TranslatingXYDataset(tsc);
            return this.dataset;

        }

        /**
         * Handles a state change event.
         *
         * @param event  the event.
         */
        public void stateChanged(ChangeEvent event) {
            int value = this.slider.getValue();
            // value is in minutes
            this.dataset.setTranslate(value * 60 * 1000.0);
        }

    }

    /**
     * A demonstration application showing how to control a crosshair using an
     * external UI component.
     *
     * @param title  the frame title.
     */
    public TranslateDemo1(String title) {
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

        TranslateDemo1 demo = new TranslateDemo1("Translate Demo 1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}

