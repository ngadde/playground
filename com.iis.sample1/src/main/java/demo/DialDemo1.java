/* --------------
 * DialDemo1.java
 * --------------
 * (C) Copyright 2006-2008, by Object Refinery Limited.
 */

package demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
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
 * A sample application showing the use of a {@link DialPlot}.
 */
public class DialDemo1 extends JFrame {

    static class DemoPanel extends JPanel implements ChangeListener {

        /** A slider to update the dataset value. */
        JSlider slider;

        /** The dataset. */
        DefaultValueDataset dataset;

        /**
         * Creates a new demo panel.
         */
        public DemoPanel() {
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
            DialPointer.Pointer p = new DialPointer.Pointer();
            plot.addPointer(p);

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

    /**
     * Creates a new instance.
     *
     * @param title  the frame title.
     */
    public DialDemo1(String title) {
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
        DialDemo1 app = new DialDemo1("JFreeChart: DialDemo1.java");
        app.pack();
        app.setVisible(true);
    }

}
