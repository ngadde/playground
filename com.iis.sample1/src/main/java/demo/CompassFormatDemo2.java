/* -----------------------
 * CompassFormatDemo2.java
 * -----------------------
 * (C) Copyright 2004-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CompassFormat;
import org.jfree.chart.axis.ModuloAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo of the {@link CompassFormat} class.
 */
public class CompassFormatDemo2 extends ApplicationFrame {

    private static class MyDemoPanel extends DemoPanel
            implements ChangeListener {

        private JSlider directionSlider;

        private JSlider fieldSlider;

        private ModuloAxis rangeAxis;

        private double direction = 0.0;

        private double degrees = 45.0;

        /**
         * Creates a new demo panel.
         */
        public MyDemoPanel() {
            super(new BorderLayout());
            JPanel controlPanel = new JPanel(new GridLayout(1, 2));
            this.fieldSlider = new JSlider(SwingConstants.VERTICAL, 10, 180, 45);
            this.fieldSlider.setPaintLabels(true);
            this.fieldSlider.setPaintTicks(true);
            this.fieldSlider.setMajorTickSpacing(10);
            this.fieldSlider.setMinorTickSpacing(5);
            this.fieldSlider.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            this.fieldSlider.addChangeListener(this);

            this.directionSlider = new JSlider(SwingConstants.VERTICAL, 0, 360, 0);
            this.directionSlider.setMajorTickSpacing(30);
            this.directionSlider.setMinorTickSpacing(5);
            this.directionSlider.setPaintLabels(true);
            this.directionSlider.setPaintTicks(true);
            this.directionSlider.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            this.directionSlider.setPaintTrack(true);
            this.directionSlider.addChangeListener(this);
            controlPanel.add(this.fieldSlider);
            controlPanel.add(this.directionSlider);
            JFreeChart chart = createChart();
            addChart(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

            add(controlPanel, BorderLayout.WEST);
            add(chartPanel);

        }

        /**
         * Creates a sample dataset.
         *
         * @param count  the item count.
         *
         * @return the dataset.
         */
        private XYDataset createDirectionDataset(int count) {
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            TimeSeries s1 = new TimeSeries("Wind Direction");
            RegularTimePeriod start = new Minute();
            double direction = 0.0;
            for (int i = 0; i < count; i++) {
                s1.add(start, direction);
                start = start.next();
                direction = direction + (Math.random() - 0.5) * 15.0;
                if (direction < 0.0) {
                    direction = direction + 360.0;
                }
                else if (direction > 360.0) {
                    direction = direction - 360.0;
                }
            }
            dataset.addSeries(s1);
            return dataset;
        }

        /**
         * Creates a sample dataset.
         *
         * @param count  the item count.
         *
         * @return the dataset.
         */
        private XYDataset createForceDataset(int count) {
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            TimeSeries s1 = new TimeSeries("Wind Force");
            RegularTimePeriod start = new Minute();
            double force = 3.0;
            for (int i = 0; i < count; i++) {
                s1.add(start, force);
                start = start.next();
                force = Math.max(0.5, force + (Math.random() - 0.5) * 0.5);
            }
            dataset.addSeries(s1);
            return dataset;
        }

        /**
         * Creates a sample chart.
         *
         * @return a sample chart.
         */
        private JFreeChart createChart() {
            XYDataset direction = createDirectionDataset(100);
            JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Time",
                "Date",
                "Direction",
                direction,
                true,
                true,
                false
            );

            XYPlot plot = (XYPlot) chart.getPlot();
            plot.getDomainAxis().setLowerMargin(0.0);
            plot.getDomainAxis().setUpperMargin(0.0);
            // configure the range axis to display directions...
            //NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            this.rangeAxis = new ModuloAxis("Direction", new Range(0, 360));
            TickUnits units = new TickUnits();
            units.add(new NumberTickUnit(180.0, new CompassFormat()));
            units.add(new NumberTickUnit(90.0, new CompassFormat()));
            units.add(new NumberTickUnit(45.0, new CompassFormat()));
            units.add(new NumberTickUnit(22.5, new CompassFormat()));
            this.rangeAxis.setStandardTickUnits(units);

            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
            renderer.setBaseLinesVisible(false);
            renderer.setBaseShapesVisible(true);
            plot.setRenderer(renderer);
            // add the wind force with a secondary dataset/renderer/axis
            plot.setRangeAxis(this.rangeAxis);
            this.rangeAxis.setDisplayRange(-45, 45);
            XYItemRenderer renderer2 = new XYAreaRenderer();
            ValueAxis axis2 = new NumberAxis("Force");
            axis2.setRange(0.0, 12.0);
            renderer2.setSeriesPaint(0, new Color(0, 0, 255, 128));
            plot.setDataset(1, createForceDataset(100));
            plot.setRenderer(1, renderer2);
            plot.setRangeAxis(1, axis2);
            plot.mapDatasetToRangeAxis(1, 1);
            ChartUtilities.applyCurrentTheme(chart);
            return chart;
        }

        /**
         * Receives JSlider change events and updates the chart accordingly.
         *
         * @param event  the event.
         */
        public void stateChanged(ChangeEvent event) {
            if (event.getSource() == this.directionSlider) {
                this.direction = this.directionSlider.getValue();
                this.rangeAxis.setDisplayRange(this.direction - this.degrees, this.direction + this.degrees);
             }
            else if (event.getSource() == this.fieldSlider) {
                this.degrees = this.fieldSlider.getValue();
                this.rangeAxis.setDisplayRange(this.direction - this.degrees, this.direction + this.degrees);
            }
        }

    }
    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public CompassFormatDemo2(String title) {
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
        CompassFormatDemo2 demo = new CompassFormatDemo2(
                "JFreeChart: CompassFormatDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
