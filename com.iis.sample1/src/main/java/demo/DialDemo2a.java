/* ---------------
 * DialDemo2a.java
 * ---------------
 * (C) Copyright 2006-2008, by Object Refinery Limited.
 */

package demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JFrame;
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
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

/**
 * Two dials on one panel.
 */
public class DialDemo2a extends JFrame {

    static class DemoPanelA extends JPanel implements ChangeListener {

        /** A slider to update the dataset value. */
        JSlider slider;

        /** The dataset. */
        DefaultValueDataset dataset;

        /**
         * Creates a new demo panel.
         */
        public DemoPanelA() {
            super(new BorderLayout());
            this.dataset = new DefaultValueDataset(10.0);

            // get data for diagrams
            JFreeChart chart = createStandardDialChart("Dial Demo 1",
                    "Temperature", this.dataset, -40, 60, 10, 4);
            DialPlot plot = (DialPlot) chart.getPlot();

            StandardDialRange range = new StandardDialRange(40.0, 60.0,
                    Color.red);
            range.setInnerRadius(0.52);
            range.setOuterRadius(0.55);
            plot.addLayer(range);

            StandardDialRange range2 = new StandardDialRange(10.0, 40.0,
                    Color.orange);
            range2.setInnerRadius(0.52);
            range2.setOuterRadius(0.55);
            plot.addLayer(range2);

            StandardDialRange range3 = new StandardDialRange(-40.0, 10.0,
                   Color.green);
            range3.setInnerRadius(0.52);
            range3.setOuterRadius(0.55);
            plot.addLayer(range3);

            GradientPaint gp = new GradientPaint(new Point(),
                    new Color(255, 255, 255), new Point(),
                    new Color(170, 170, 220));
            DialBackground db = new DialBackground(gp);
            db.setGradientPaintTransformer(new StandardGradientPaintTransformer(
                    GradientPaintTransformType.VERTICAL));
            plot.setBackground(db);

            plot.removePointer(0);
            plot.addPointer(new DialPointer.Pointer());

            ChartPanel cp1 = new ChartPanel(chart);
            cp1.setPreferredSize(new Dimension(400, 400));
            this.slider = new JSlider(-40, 60);
            this.slider.setMajorTickSpacing(10);
            this.slider.setPaintLabels(true);
            this.slider.addChangeListener(this);
            add(cp1);
            add(this.slider, BorderLayout.SOUTH);
        }

        /**
         * Creates a chart displaying a circular dial.
         *
         * @param chartTitle  the chart title.
         * @param dialLabel  the dial label.
         * @param dataset  the dataset.
         * @param lowerBound  the lower bound.
         * @param upperBound  the upper bound.
         * @param increment  the major tick increment.
         * @param minorTickCount  the minor tick count.
         *
         * @return A chart that displays a value as a dial.
         */
        public static JFreeChart createStandardDialChart(String chartTitle,
                String dialLabel, ValueDataset dataset, double lowerBound,
                double upperBound, double increment, int minorTickCount) {
            DialPlot plot = new DialPlot();
            plot.setDataset(dataset);
            plot.setDialFrame(new StandardDialFrame());

            plot.setBackground(new DialBackground());
            DialTextAnnotation annotation1 = new DialTextAnnotation(dialLabel);
            annotation1.setFont(new Font("Dialog", Font.BOLD, 14));
            annotation1.setRadius(0.7);

            plot.addLayer(annotation1);

            DialValueIndicator dvi = new DialValueIndicator(0);
            plot.addLayer(dvi);

            StandardDialScale scale = new StandardDialScale(lowerBound,
                    upperBound, -120, -300, 10.0, 4);
            scale.setMajorTickIncrement(increment);
            scale.setMinorTickCount(minorTickCount);
            scale.setTickRadius(0.88);
            scale.setTickLabelOffset(0.15);
            scale.setTickLabelFont(new Font("Dialog", Font.PLAIN, 14));
            plot.addScale(0, scale);

            plot.addPointer(new DialPointer.Pin());

            DialCap cap = new DialCap();
            plot.setCap(cap);

            return new JFreeChart(chartTitle, plot);
        }

        /**
         * Handle a change in the slider by updating the dataset value.  This
         * automatically triggers a chart repaint.
         *
         * @param e  the event.
         */
        public void stateChanged(ChangeEvent e) {
            this.dataset.setValue(new Integer(this.slider.getValue()));
        }

    }

    static class DemoPanelB extends JPanel implements ChangeListener {

        /** The first dataset. */
        DefaultValueDataset dataset1;

        /** The second dataset. */
        DefaultValueDataset dataset2;

        /** A slider to update the first dataset value. */
        JSlider slider1;

        /** A slider to update the second dataset value. */
        JSlider slider2;

        /**
         * Creates a new demo panel.
         */
        public DemoPanelB() {
            super(new BorderLayout());
            this.dataset1 = new DefaultValueDataset(10.0);
            this.dataset2 = new DefaultValueDataset(50.0);

            // get data for diagrams
            DialPlot plot = new DialPlot();
            plot.setView(0.0, 0.0, 1.0, 1.0);
            plot.setDataset(0, this.dataset1);
            plot.setDataset(1, this.dataset2);
            StandardDialFrame dialFrame = new StandardDialFrame();
            dialFrame.setBackgroundPaint(Color.lightGray);
            dialFrame.setForegroundPaint(Color.darkGray);
            plot.setDialFrame(dialFrame);

            GradientPaint gp = new GradientPaint(new Point(),
                    new Color(255, 255, 255), new Point(),
                    new Color(170, 170, 220));
            DialBackground db = new DialBackground(gp);
            db.setGradientPaintTransformer(new StandardGradientPaintTransformer(
                    GradientPaintTransformType.VERTICAL));
            plot.setBackground(db);

            DialTextAnnotation annotation1 = new DialTextAnnotation(
                    "Temperature");
            annotation1.setFont(new Font("Dialog", Font.BOLD, 14));
            annotation1.setRadius(0.7);

            plot.addLayer(annotation1);

            DialValueIndicator dvi = new DialValueIndicator(0);
            dvi.setFont(new Font("Dialog", Font.PLAIN, 10));
            dvi.setOutlinePaint(Color.darkGray);
            dvi.setRadius(0.60);
            dvi.setAngle(-103.0);
            plot.addLayer(dvi);

            DialValueIndicator dvi2 = new DialValueIndicator(1);
            dvi2.setFont(new Font("Dialog", Font.PLAIN, 10));
            dvi2.setOutlinePaint(Color.red);
            dvi2.setRadius(0.60);
            dvi2.setAngle(-77.0);
            plot.addLayer(dvi2);

            StandardDialScale scale = new StandardDialScale(-40, 60, -120, -300,
                    10.0, 4);
            scale.setTickRadius(0.88);
            scale.setTickLabelOffset(0.15);
            scale.setTickLabelFont(new Font("Dialog", Font.PLAIN, 14));
            plot.addScale(0, scale);

            StandardDialScale scale2 = new StandardDialScale(0, 100, -120, -300,
                    10.0, 4);
            scale2.setTickRadius(0.50);
            scale2.setTickLabelOffset(0.15);
            scale2.setTickLabelFont(new Font("Dialog", Font.PLAIN, 10));
            scale2.setMajorTickPaint(Color.red);
            scale2.setMinorTickPaint(Color.red);
            plot.addScale(1, scale2);
            plot.mapDatasetToScale(1, 1);

            StandardDialRange r1 = new StandardDialRange(90.0, 100.0,
                    Color.blue);
            r1.setScaleIndex(1);
            r1.setInnerRadius(0.59);
            r1.setOuterRadius(0.59);

            plot.addLayer(r1);

            DialPointer needle2 = new DialPointer.Pin(1);
            needle2.setRadius(0.55);
            plot.addPointer(needle2);

            DialPointer needle = new DialPointer.Pointer(0);
            plot.addPointer(needle);

            DialCap cap = new DialCap();
            cap.setRadius(0.10);
            plot.setCap(cap);

            JFreeChart chart1 = new JFreeChart(plot);
            chart1.setTitle("Dial Demo 2");
            ChartPanel cp1 = new ChartPanel(chart1);
            cp1.setPreferredSize(new Dimension(400, 400));

            JPanel sliderPanel = new JPanel(new GridLayout(1, 2));
            //sliderPanel.add(new JLabel("Outer Needle:"));
            //sliderPanel.add(new JLabel("Inner Needle:"));
            this.slider1 = new JSlider(-40, 60);
            this.slider1.setMajorTickSpacing(20);
            this.slider1.setPaintTicks(false);
            this.slider1.setPaintLabels(true);
            this.slider1.addChangeListener(this);
            sliderPanel.add(this.slider1);
            sliderPanel.add(this.slider1);
            this.slider2 = new JSlider(0, 100);
            this.slider2.setMajorTickSpacing(20);
            this.slider2.setPaintTicks(false);
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
            this.dataset1.setValue(new Integer(this.slider1.getValue()));
            this.dataset2.setValue(new Integer(this.slider2.getValue()));
        }
    }

    /**
     * Creates a new instance.
     *
     * @param title  the frame title.
     */
    public DialDemo2a(String title) {
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
        JPanel demoPanel = new JPanel(new GridLayout(1, 2));
        demoPanel.add(new DemoPanelA());
        demoPanel.add(new DemoPanelB());
        return demoPanel;
    }

    /**
     * Starting point for the demo application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        DialDemo2a app = new DialDemo2a("JFreeChart: DialDemo2a.java");
        app.pack();
        app.setVisible(true);
    }

}
