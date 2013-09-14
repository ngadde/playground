/* -------------------
 * PDFExportDemo1.java
 * -------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 */

package demo.pdf;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.FontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 * A simple demonstration showing how to write a chart to PDF format using
 * JFreeChart and iText.
 * <P>
 * You can download iText from http://www.lowagie.com/iText.
 */
public class PDFExportDemo2 {

    /**
     * Saves a chart to a PDF file.
     *
     * @param file  the file.
     * @param chart  the chart.
     * @param width  the chart width.
     * @param height  the chart height.
     * @param mapper  the font mapper.
     *
     * @throws IOException if there is an I/O problem.
     */
    public static void saveChartAsPDF(File file,
                                      JFreeChart chart,
                                      int width,
                                      int height,
                                      FontMapper mapper) throws IOException {

        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        writeChartAsPDF(out, chart, width, height, mapper);
        out.close();

    }

    /**
     * Writes a chart to an output stream in PDF format.
     *
     * @param out  the output stream.
     * @param chart  the chart.
     * @param width  the chart width.
     * @param height  the chart height.
     * @param mapper  the font mapper.
     *
     * @throws IOException if there is an I/O problem.
     */
    public static void writeChartAsPDF(OutputStream out,
                                       JFreeChart chart,
                                       int width,
                                       int height,
                                       FontMapper mapper) throws IOException {

        Rectangle pagesize = new Rectangle(width, height);
        Document document = new Document(pagesize, 50, 50, 50, 50);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.addAuthor("JFreeChart");
            document.addSubject("Demonstration");
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            PdfTemplate tp = cb.createTemplate(width, height);
            Graphics2D g2 = tp.createGraphics(width, height, mapper);
            Rectangle2D r2D = new Rectangle2D.Double(0, 0, width, height);
            chart.draw(g2, r2D);
            g2.dispose();
            cb.addTemplate(tp, 0, 0);
        }
        catch (DocumentException de) {
            System.err.println(de.getMessage());
        }
        document.close();
    }

    /**
     * Creates a dataset, consisting of two series of monthly data. * *
     *
     * @return the dataset.
     */
    public static XYDataset createDataset() {

        TimeSeries s1 = new TimeSeries("L&G European Index Trust");
        s1.add(new Month(2, 2001), 181.8);
        s1.add(new Month(3, 2001), 167.3);
        s1.add(new Month(4, 2001), 153.8);
        s1.add(new Month(5, 2001), 167.6);
        s1.add(new Month(6, 2001), 158.8);
        s1.add(new Month(7, 2001), 148.3);
        s1.add(new Month(8, 2001), 153.9);
        s1.add(new Month(9, 2001), 142.7);
        s1.add(new Month(10, 2001), 123.2);
        s1.add(new Month(11, 2001), 131.8);
        s1.add(new Month(12, 2001), 139.6);
        s1.add(new Month(1, 2002), 142.9);
        s1.add(new Month(2, 2002), 138.7);
        s1.add(new Month(3, 2002), 137.3);
        s1.add(new Month(4, 2002), 143.9);
        s1.add(new Month(5, 2002), 139.8);
        s1.add(new Month(6, 2002), 137.0);
        s1.add(new Month(7, 2002), 132.8);

        TimeSeries s2 = new TimeSeries("L&G UK Index Trust");
        s2.add(new Month(2, 2001), 129.6);
        s2.add(new Month(3, 2001), 123.2);
        s2.add(new Month(4, 2001), 117.2);
        s2.add(new Month(5, 2001), 124.1);
        s2.add(new Month(6, 2001), 122.6);
        s2.add(new Month(7, 2001), 119.2);
        s2.add(new Month(8, 2001), 116.5);
        s2.add(new Month(9, 2001), 112.7);
        s2.add(new Month(10, 2001), 101.5);
        s2.add(new Month(11, 2001), 106.1);
        s2.add(new Month(12, 2001), 110.3);
        s2.add(new Month(1, 2002), 111.7);
        s2.add(new Month(2, 2002), 111.0);
        s2.add(new Month(3, 2002), 109.6);
        s2.add(new Month(4, 2002), 113.2);
        s2.add(new Month(5, 2002), 111.6);
        s2.add(new Month(6, 2002), 108.8);
        s2.add(new Month(7, 2002), 101.6);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);

        return dataset;
    }

    /**
     * Starting point for the demo application.
     *
     * @param args  command line arguments (ignored).
     */
    public static void main(String[] args) {
        try {
            // create a chart...
            TimeSeries series = new TimeSeries("Random Data");
            Day current = new Day(1, 1, 2000);
            double value = 100.0;
            for (int i = 0; i < 1000; i++) {
                try {
                    value = value + Math.random() - 0.5;
                    series.add(current, new Double(value));
                    current = (Day) current.next();
                }
                catch (SeriesException e) {
                    System.err.println("Error adding to series");
                }
            }
            XYDataset data = new TimeSeriesCollection(series);
            JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Test",
                "Date",
                "Value",
                data,
                true,
                false,
                false
            );

            // Unicode test...
            String text = "\u278A\u20A0\u20A1\u20A2\u20A3\u20A4\u20A5\u20A6\u20A7\u20A8\u20A9";
            Font font = new Font("Tahoma", Font.PLAIN, 12);
            TextTitle subtitle = new TextTitle(text, font);
            chart.addSubtitle(subtitle);

            DefaultFontMapper mapper = new DefaultFontMapper();
            mapper.insertDirectory("/opt/sun-jdk-1.4.2.08/jre/lib/fonts");
            DefaultFontMapper.BaseFontParameters pp =
                                  mapper.getBaseFontParameters("Tahoma");
            if (pp!=null) {
                pp.encoding = BaseFont.IDENTITY_H;
            }

            // write the chart to a PDF file...
            File fileName = new File(System.getProperty("user.home")
                                     + "/jfreechart2.pdf");
            saveChartAsPDF(fileName, chart, 400, 300, mapper);
        }
        catch (IOException e) {
                System.out.println(e.getMessage());
        }
    }

}
