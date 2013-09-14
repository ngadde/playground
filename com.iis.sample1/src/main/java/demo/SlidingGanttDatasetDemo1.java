/* -----------------------------
 * SlidingGanttDatasetDemo1.java
 * -----------------------------
 * (C) Copyright 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.renderer.category.GanttRenderer;
import org.jfree.data.gantt.GanttCategoryDataset;
import org.jfree.data.gantt.SlidingGanttCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo for the {@link SlidingGanttCategoryDataset} class.
 */
public class SlidingGanttDatasetDemo1 extends ApplicationFrame {

    static class DemoPanel extends JPanel implements ChangeListener {

        /** A scrollbar to update the dataset value. */
        JScrollBar scroller;

        /** The dataset. */
        SlidingGanttCategoryDataset dataset;

        /**
         * Creates a new demo panel.
         */
        public DemoPanel() {
            super(new BorderLayout());
            this.dataset = new SlidingGanttCategoryDataset(
                    createDataset(), 0, 15);

            // get data for diagrams
            JFreeChart chart = createChart(this.dataset);

            ChartPanel cp1 = new ChartPanel(chart);
            cp1.setPreferredSize(new Dimension(400, 400));
            this.scroller = new JScrollBar(SwingConstants.VERTICAL, 0, 15, 0,
                    50);
            add(cp1);
            this.scroller.getModel().addChangeListener(this);
            JPanel scrollPanel = new JPanel(new BorderLayout());
            scrollPanel.add(this.scroller);
            scrollPanel.setBorder(BorderFactory.createEmptyBorder(66, 2, 2, 2));
            add(scrollPanel, BorderLayout.EAST);
        }

        /**
         * Creates a sample dataset for a Gantt chart.
         *
         * @return The dataset.
         */
        public static GanttCategoryDataset createDataset() {
            // create a random set of scheduled tasks
            // this code is ugly, but it's only a demo
            TaskSeries s1 = new TaskSeries("Scheduled");
            Day t0 = new Day();
            Day t1 = new Day();
            for (int i = 0; i < 50; i++) {
                int days = (int) (Math.random() * 10.0) + 1;
                for (int j = 0; j < days; j++) {
                    t1 = (Day) t1.next();
                }
                s1.add(new Task("Task " + i,
                        new Date(t0.getMiddleMillisecond()),
                        new Date(t1.getMiddleMillisecond())));
                t0 = (Day) t1.next();
                t1 = (Day) t1.next();
            }

            TaskSeriesCollection collection = new TaskSeriesCollection();
            collection.add(s1);
            return collection;
        }

        /**
         * Creates a chart.
         *
         * @param dataset  the dataset.
         *
         * @return The chart.
         */
        private static JFreeChart createChart(SlidingGanttCategoryDataset dataset) {
            JFreeChart chart = ChartFactory.createGanttChart(
                "Gantt Chart Demo",  // chart title
                "Task",              // domain axis label
                "Date",              // range axis label
                dataset,             // data
                true,                // include legend
                true,                // tooltips
                false                // urls
            );
            CategoryPlot plot = (CategoryPlot) chart.getPlot();

            Hour h = new Hour(1, 14, 5, 2008);
            for (int i = 0; i < 12; i++) {
                Marker marker = new IntervalMarker(h.getFirstMillisecond(),
                        h.getLastMillisecond(), Color.lightGray);
                plot.addRangeMarker(marker, Layer.BACKGROUND);
                h = (Hour) h.next().next();  // notice we jump forward two
                                             // hours here
            }


            plot.getDomainAxis().setMaximumCategoryLabelWidthRatio(10.0f);
            DateAxis rangeAxis = (DateAxis) plot.getRangeAxis();
            rangeAxis.setRange(DatasetUtilities.findRangeBounds(
                    dataset.getUnderlyingDataset(), true));
            GanttRenderer renderer = (GanttRenderer) plot.getRenderer();
            renderer.setDrawBarOutline(false);
            renderer.setShadowVisible(false);
            return chart;
        }


        /**
         * Handle a change in the slider by updating the dataset value.  This
         * automatically triggers a chart repaint.
         *
         * @param e  the event.
         */
        public void stateChanged(ChangeEvent e) {
            this.dataset.setFirstCategoryIndex(this.scroller.getValue());
        }

    }

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public SlidingGanttDatasetDemo1(String title) {
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
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        SlidingGanttDatasetDemo1 demo = new SlidingGanttDatasetDemo1(
                "JFreeChart: SlidingGanttDatasetDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
