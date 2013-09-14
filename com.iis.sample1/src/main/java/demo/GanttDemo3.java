/* ---------------
 * GanttDemo3.java
 * ---------------
 * (C) Copyright 2007-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.GanttRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * Based on GanttDemo1, this adds text labels to each bar.
 */
public class GanttDemo3 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public GanttDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 370));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset for a Gantt chart.
     *
     * @return The dataset.
     */
    public static IntervalCategoryDataset createDataset() {

        TaskSeries s1 = new TaskSeries("Scheduled");
        s1.add(new Task("Write Proposal",
               new SimpleTimePeriod(date(1, Calendar.APRIL, 2001),
                                    date(5, Calendar.APRIL, 2001))));
        s1.add(new Task("Obtain Approval", new SimpleTimePeriod(date(9,
                Calendar.APRIL, 2001), date(9, Calendar.APRIL, 2001))));
        s1.add(new Task("Requirements Analysis",
               new SimpleTimePeriod(date(10, Calendar.APRIL, 2001),
                                    date(5, Calendar.MAY, 2001))));
        s1.add(new Task("Design Phase",
               new SimpleTimePeriod(date(6, Calendar.MAY, 2001),
                                    date(30, Calendar.MAY, 2001))));
        s1.add(new Task("Design Signoff",
               new SimpleTimePeriod(date(2, Calendar.JUNE, 2001),
                                    date(2, Calendar.JUNE, 2001))));
        s1.add(new Task("Alpha Implementation",
               new SimpleTimePeriod(date(3, Calendar.JUNE, 2001),
                                    date(31, Calendar.JULY, 2001))));
        s1.add(new Task("Design Review",
               new SimpleTimePeriod(date(1, Calendar.AUGUST, 2001),
                                    date(8, Calendar.AUGUST, 2001))));
        s1.add(new Task("Revised Design Signoff",
               new SimpleTimePeriod(date(10, Calendar.AUGUST, 2001),
                                    date(10, Calendar.AUGUST, 2001))));
        s1.add(new Task("Beta Implementation",
               new SimpleTimePeriod(date(12, Calendar.AUGUST, 2001),
                                    date(12, Calendar.SEPTEMBER, 2001))));
        s1.add(new Task("Testing",
               new SimpleTimePeriod(date(13, Calendar.SEPTEMBER, 2001),
                                    date(31, Calendar.OCTOBER, 2001))));
        s1.add(new Task("Final Implementation",
               new SimpleTimePeriod(date(1, Calendar.NOVEMBER, 2001),
                                    date(15, Calendar.NOVEMBER, 2001))));
        s1.add(new Task("Signoff",
               new SimpleTimePeriod(date(28, Calendar.NOVEMBER, 2001),
                                    date(30, Calendar.NOVEMBER, 2001))));

        TaskSeries s2 = new TaskSeries("Actual");
        s2.add(new Task("Write Proposal",
               new SimpleTimePeriod(date(1, Calendar.APRIL, 2001),
                                    date(5, Calendar.APRIL, 2001))));
        s2.add(new Task("Obtain Approval",
               new SimpleTimePeriod(date(9, Calendar.APRIL, 2001),
                                    date(9, Calendar.APRIL, 2001))));
        s2.add(new Task("Requirements Analysis",
               new SimpleTimePeriod(date(10, Calendar.APRIL, 2001),
                                    date(15, Calendar.MAY, 2001))));
        s2.add(new Task("Design Phase",
               new SimpleTimePeriod(date(15, Calendar.MAY, 2001),
                                    date(17, Calendar.JUNE, 2001))));
        s2.add(new Task("Design Signoff",
               new SimpleTimePeriod(date(30, Calendar.JUNE, 2001),
                                    date(30, Calendar.JUNE, 2001))));
        s2.add(new Task("Alpha Implementation",
               new SimpleTimePeriod(date(1, Calendar.JULY, 2001),
                                    date(12, Calendar.SEPTEMBER, 2001))));
        s2.add(new Task("Design Review",
               new SimpleTimePeriod(date(12, Calendar.SEPTEMBER, 2001),
                                    date(22, Calendar.SEPTEMBER, 2001))));
        s2.add(new Task("Revised Design Signoff",
               new SimpleTimePeriod(date(25, Calendar.SEPTEMBER, 2001),
                                    date(27, Calendar.SEPTEMBER, 2001))));
        s2.add(new Task("Beta Implementation",
               new SimpleTimePeriod(date(27, Calendar.SEPTEMBER, 2001),
                                    date(30, Calendar.OCTOBER, 2001))));
        s2.add(new Task("Testing",
               new SimpleTimePeriod(date(31, Calendar.OCTOBER, 2001),
                                    date(17, Calendar.NOVEMBER, 2001))));
        s2.add(new Task("Final Implementation",
               new SimpleTimePeriod(date(18, Calendar.NOVEMBER, 2001),
                                    date(5, Calendar.DECEMBER, 2001))));
        s2.add(new Task("Signoff",
               new SimpleTimePeriod(date(10, Calendar.DECEMBER, 2001),
                                    date(11, Calendar.DECEMBER, 2001))));

        TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        collection.add(s2);

        return collection;
    }

    /**
     * Utility method for creating <code>Date</code> objects.
     *
     * @param day  the date.
     * @param month  the month.
     * @param year  the year.
     *
     * @return a date.
     */
    private static Date date(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date result = calendar.getTime();
        return result;
    }

    /**
     * A custom item label generator.
     */
    static class MyLabelGenerator implements CategoryItemLabelGenerator {

        DateFormat df;

        /**
         * Creates a new instance.
         *
         * @param df  the date formatter.
         */
        public MyLabelGenerator(DateFormat df) {
            this.df = df;
        }

        /**
         * Generate a custom label.
         *
         * @param dataset  the dataset.
         * @param row  the row index.
         * @param column  the column index.
         *
         * @return The label text.
         */
        public String generateLabel(CategoryDataset dataset, int row,
                int column) {
            Number n = null;
            if (dataset instanceof IntervalCategoryDataset) {
                IntervalCategoryDataset icd = (IntervalCategoryDataset) dataset;
                n = icd.getEndValue(row, column);
            }
            else {
                n = dataset.getValue(row, column);
            }
            if (n == null) {
                return "null";
            }
            long millis = n.longValue();
            Date d = new Date(millis);

            return this.df.format(d);
        }

        /**
         * This won't be used in the demo.
         *
         * @param dataset  the dataset.
         * @param column  the column index.
         *
         * @return The label text.
         */
        public String generateColumnLabel(CategoryDataset dataset, int column) {
            return dataset.getColumnKey(column).toString();
        }

        /**
         * This won't be used in the demo.
         *
         * @param dataset  the dataset.
         * @param row  the row index.
         *
         * @return The label text.
         */
        public String generateRowLabel(CategoryDataset dataset, int row) {
            return dataset.getRowKey(row).toString();
        }

    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(IntervalCategoryDataset dataset) {
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
        plot.setRangePannable(true);
        plot.getDomainAxis().setMaximumCategoryLabelWidthRatio(10.0f);

        DateAxis rangeAxis = (DateAxis) plot.getRangeAxis();
        rangeAxis.setUpperMargin(0.20);  // make room for labels

        GanttRenderer renderer = (GanttRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setBaseItemLabelGenerator(
                new MyLabelGenerator(new SimpleDateFormat("d-MMM")));
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT));
        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        GanttDemo3 demo = new GanttDemo3("JFreeChart: GanttDemo3.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
