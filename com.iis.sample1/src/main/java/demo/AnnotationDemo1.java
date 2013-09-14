/* --------------------
 * AnnotationDemo1.java
 * --------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * A demo showing chart annotations, in this case several instances of
 * {@link XYTextAnnotation}.
 */
public class AnnotationDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo application.
     *
     * @param title  the frame title.
     */
    public AnnotationDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(360, 500));
        setContentPane(chartPanel);
    }

    /**
     * Creates a dataset.
     *
     * @return a dataset.
     */
    private static XYSeriesCollection createDataset() {

        XYSeriesCollection result = new XYSeriesCollection();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    XYSeriesCollection.class.getClassLoader()
                    .getResourceAsStream("demo/wtageinf.txt")));
            String data = in.readLine();  // ignore first line
            data = in.readLine();  // ignore second line
            data = in.readLine();  // ignore third line
            data = in.readLine();  // headings

            XYSeries s3 = new XYSeries("P3", true, false);
            XYSeries s5 = new XYSeries("P5", true, false);
            XYSeries s10 = new XYSeries("P10", true, false);
            XYSeries s25 = new XYSeries("P25", true, false);
            XYSeries s50 = new XYSeries("P50", true, false);
            XYSeries s75 = new XYSeries("P75", true, false);
            XYSeries s90 = new XYSeries("P90", true, false);
            XYSeries s95 = new XYSeries("P95", true, false);
            XYSeries s97 = new XYSeries("P97", true, false);

            data = in.readLine();
            while (data != null) {
                int sex = Integer.parseInt(data.substring(1, 8).trim());
                float age = Float.parseFloat(data.substring(9, 17).trim());
                float p3 = Float.parseFloat(data.substring(69, 86).trim());
                float p5 = Float.parseFloat(data.substring(87, 103).trim());
                float p10 = Float.parseFloat(data.substring(104, 122).trim());
                float p25 = Float.parseFloat(data.substring(123, 140).trim());
                float p50 = Float.parseFloat(data.substring(141, 158).trim());
                float p75 = Float.parseFloat(data.substring(159, 176).trim());
                float p90 = Float.parseFloat(data.substring(177, 193).trim());
                float p95 = Float.parseFloat(data.substring(194, 212).trim());
                float p97 = Float.parseFloat(data.substring(212,
                        data.length()).trim());
                if (sex == 1) {
                    s3.add(age, p3);
                    s5.add(age, p5);
                    s10.add(age, p10);
                    s25.add(age, p25);
                    s50.add(age, p50);
                    s75.add(age, p75);
                    s90.add(age, p90);
                    s95.add(age, p95);
                    s97.add(age, p97);
                }
                data = in.readLine();
            }

            result.addSeries(s3);
            result.addSeries(s5);
            result.addSeries(s10);
            result.addSeries(s25);
            result.addSeries(s50);
            result.addSeries(s75);
            result.addSeries(s90);
            result.addSeries(s95);
            result.addSeries(s97);
        }
        catch (FileNotFoundException e) {
            System.err.println(e);
        }
        catch (IOException e) {
            System.err.println(e);
        }

        return result;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
            null,
            "Age in Months",
            "Weight (kg)",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false);
        TextTitle t1 = new TextTitle("Growth Charts: United States",
                new Font("SansSerif", Font.BOLD, 14));
        TextTitle t2 = new TextTitle(
                "Weight-for-age percentiles: boys, birth to 36 months",
                new Font("SansSerif", Font.PLAIN, 11));
        chart.addSubtitle(t1);
        chart.addSubtitle(t2);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setUpperMargin(0.12);
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(false);

        // add some annotations...
        XYTextAnnotation annotation = null;
        Font font = new Font("SansSerif", Font.PLAIN, 9);

        annotation = new XYTextAnnotation("3rd", 36.5, 11.76);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);

        annotation = new XYTextAnnotation("5th", 36.5, 12.04);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);

        annotation = new XYTextAnnotation("10th", 36.5, 12.493);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);

        annotation = new XYTextAnnotation("25th", 36.5, 13.313);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);

        annotation = new XYTextAnnotation("50th", 36.5, 14.33);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);

        annotation = new XYTextAnnotation("75th", 36.5, 15.478);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);

        annotation = new XYTextAnnotation("90th", 36.5, 16.642);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);

        annotation = new XYTextAnnotation("95th", 36.5, 17.408);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);

        annotation = new XYTextAnnotation("97th", 36.5, 17.936);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);

        // ensure the theme is applied to the extras
        ChartUtilities.applyCurrentTheme(chart);
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
     * Starting point for the demonstration application when it is run as
     * a stand-alone application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        AnnotationDemo1 demo = new AnnotationDemo1(
                "JFreeChart: AnnotationDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}

