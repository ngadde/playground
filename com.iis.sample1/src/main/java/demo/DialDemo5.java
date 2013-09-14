/* --------------
 * DialDemo5.java
 * --------------
 * (C) Copyright 2006, 2008, by Object Refinery Limited.
 */

package demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialPointer;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

/**
 * A sample application showing the use of a {@link DialPlot}.
 */
public class DialDemo5 extends JFrame {

    static class DemoPanel extends JPanel implements ChangeListener {

        /** The first dataset. */
        DefaultValueDataset hoursDataset;

        /** The second dataset. */
        DefaultValueDataset dataset2;

        /** A slider to update the first dataset value. */
        JSlider slider1;

        /** A slider to update the second dataset value. */
        JSlider slider2;

        /**
         * Creates a new demo panel.
         */
        public DemoPanel() {
            super(new BorderLayout());
            this.hoursDataset = new DefaultValueDataset(6.0);
            this.dataset2 = new DefaultValueDataset(15.0);

            // get data for diagrams
            DialPlot plot = new DialPlot();
            plot.setView(0.0, 0.0, 1.0, 1.0);
            plot.setDataset(0, this.hoursDataset);
            plot.setDataset(1, this.dataset2);
            StandardDialFrame dialFrame = new StandardDialFrame();
            dialFrame.setBackgroundPaint(Color.lightGray);
            dialFrame.setForegroundPaint(Color.darkGray);
            plot.setDialFrame(dialFrame);

            DialBackground db = new DialBackground(Color.white);
            db.setGradientPaintTransformer(new StandardGradientPaintTransformer(
                    GradientPaintTransformType.VERTICAL));
            plot.setBackground(db);

            StandardDialScale hourScale = new StandardDialScale(0, 12, 90, -360,
                    10.0, 4);
            hourScale.setFirstTickLabelVisible(false);
            hourScale.setMajorTickIncrement(1.0);
            hourScale.setTickRadius(0.88);
            hourScale.setTickLabelOffset(0.15);
            hourScale.setTickLabelFont(new Font("Dialog", Font.PLAIN, 14));
            plot.addScale(0, hourScale);

            StandardDialScale scale2 = new StandardDialScale(0, 60, 90, -360,
                    10.0, 4);
            scale2.setVisible(false);
            scale2.setMajorTickIncrement(5.0);
            scale2.setTickRadius(0.68);
            scale2.setTickLabelOffset(0.15);
            scale2.setTickLabelFont(new Font("Dialog", Font.PLAIN, 14));

            plot.addScale(1, scale2);

            DialPointer needle2 = new DialPointer.Pointer(0);
            needle2.setRadius(0.55);
            plot.addLayer(needle2);

            plot.mapDatasetToScale(1, 1);

            DialPointer needle = new DialPointer.Pointer(1);
            plot.addLayer(needle);

            DialCap cap = new DialCap();
            cap.setRadius(0.10);
            plot.setCap(cap);

            JFreeChart chart1 = new JFreeChart(plot);
            chart1.setTitle("Dial Demo 5");
            ChartPanel cp1 = new ChartPanel(chart1);
            cp1.setPreferredSize(new Dimension(400, 400));

            JPanel sliderPanel = new JPanel(new GridLayout(2, 2));
            sliderPanel.add(new JLabel("Hours:"));
            sliderPanel.add(new JLabel("Minutes:"));
            this.slider1 = new JSlider(0, 12);
            this.slider1.setMajorTickSpacing(2);
            this.slider1.setPaintTicks(true);
            this.slider1.setPaintLabels(true);
            this.slider1.addChangeListener(this);
            sliderPanel.add(this.slider1);
            sliderPanel.add(this.slider1);
            this.slider2 = new JSlider(0, 60);
            this.slider2.setValue(15);
            this.slider2.setMajorTickSpacing(10);
            this.slider2.setPaintTicks(true);
            this.slider2.setPaintLabels(true);
            this.slider2.addChangeListener(this);
            sliderPanel.add(this.slider2);
            add(cp1);
            add(sliderPanel, BorderLayout.SOUTH);
        }

        /**
         * Handle a change in the slider by updating the dataset value.  This
         * automatically triggers a chart repaint.
         *
         * @param e  the event.
         */
        public void stateChanged(ChangeEvent e) {
            this.hoursDataset.setValue(new Integer(this.slider1.getValue()));
            this.dataset2.setValue(new Integer(this.slider2.getValue()));
        }

    }

    /**
     * Creates a new instance.
     *
     * @param title  the frame title.
     */
    public DialDemo5(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(createDemoPanel());
    }

    /**
     * Creates a demo panel.  This method is called by SuperDemo.java.
     *
     * @return A demo panel.
     */
    public static JPanel createDemoPanel() {
        return new DemoPanel();
    }

    /**
     * Starting point for the demo application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        DialDemo5 app = new DialDemo5("JFreeChart: DialDemo5.java");
        app.pack();
        app.setVisible(true);
    }

}
