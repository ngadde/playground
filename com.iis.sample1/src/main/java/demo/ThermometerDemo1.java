/* ---------------------
 * ThermometerDemo1.java
 * ---------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

/**
 * A simple demonstration application showing how to create a thermometer.
 */
public class ThermometerDemo1 extends ApplicationFrame {

    static class ContentPanel extends DemoPanel implements ChangeListener {

        JSlider slider;

        DefaultValueDataset dataset;

        /**
         * Default constructor.
         */
        public ContentPanel() {
            super(new BorderLayout());
            this.slider = new JSlider(0, 200, 100);
            this.slider.setPaintLabels(true);
            this.slider.setPaintTicks(true);
            this.slider.setMajorTickSpacing(25);
            this.slider.addChangeListener(this);
            add(this.slider, BorderLayout.SOUTH);
            this.dataset = new DefaultValueDataset(this.slider.getValue());
            JFreeChart chart = createChart(this.dataset);
            addChart(chart);
            add(new ChartPanel(chart));
        }

        private static JFreeChart createChart(ValueDataset dataset) {
            ThermometerPlot plot = new ThermometerPlot(dataset);
            JFreeChart chart = new JFreeChart(
                "Thermometer Demo 1",  // chart title
                JFreeChart.DEFAULT_TITLE_FONT,
                plot,                  // plot
                true);

            plot.setInsets(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
            plot.setPadding(new RectangleInsets(10.0, 10.0, 10.0, 10.0));
            plot.setThermometerStroke(new BasicStroke(2.0f));
            plot.setThermometerPaint(Color.lightGray);
            plot.setUnits(ThermometerPlot.UNITS_FAHRENHEIT);
            plot.setGap(3);
            plot.setRange(0.0, 200.0);
            plot.setSubrange(0, 0.0, 85.0);
            plot.setSubrangePaint(0, Color.red);
            plot.setSubrange(1, 85.0, 125.0);
            plot.setSubrangePaint(1, Color.green);
            plot.setSubrange(2, 125.0, 200.0);
            plot.setSubrangePaint(2, Color.red);
            ChartUtilities.applyCurrentTheme(chart);
            return chart;
        }

        /**
         * Handle a state change.
         *
         * @param e  the event.
         */
        public void stateChanged(ChangeEvent e) {
            this.dataset.setValue(new Integer(this.slider.getValue()));
        }

    }

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public ThermometerDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        setContentPane(chartPanel);
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        return new ContentPanel();
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        ThermometerDemo1 demo = new ThermometerDemo1("Thermometer Demo 1");
        demo.pack();
        demo.setVisible(true);
    }

}
