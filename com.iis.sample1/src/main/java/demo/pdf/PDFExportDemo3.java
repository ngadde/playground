/* -------------------
 * PDFExportDemo3.java
 * -------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 */

package demo.pdf;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
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
public class PDFExportDemo3 {

    /**
     * Saves a chart to a PDF file.
     *
     * @param file  the file.
     * @param charts  the charts.
     * @param width  the chart width.
     * @param height  the chart height.
     * @param mapper  the font mapper.
     *
     * @throws IOException if there is an I/O problem.
     */
    public static void saveChartsAsPDF(File file,
                                      JFreeChart[] charts,
                                      int width,
                                      int height,
                                      FontMapper mapper) throws IOException {

        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        writeChartsAsPDF(out, charts, width, height, mapper);
        out.close();

    }

    /**
     * Writes a chart to an output stream in PDF format.
     *
     * @param out  the output stream.
     * @param charts  the chart.
     * @param width  the chart width.
     * @param height  the chart height.
     * @param mapper  the font mapper.
     *
     * @throws IOException if there is an I/O problem.
     */
    public static void writeChartsAsPDF(OutputStream out,
                                       JFreeChart[] charts,
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
            Rectangle2D r2D = new Rectangle2D.Double(0, 0, width, height / 2);
            charts[0].draw(g2, r2D);
            r2D = new Rectangle2D.Double(0, height/ 2, width, height / 2);
            charts[1].draw(g2, r2D);
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
            XYDataset dataset = createDataset();
            JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Legal & General Unit Trust Prices",
                "Date",
                "Price Per Unit",
                dataset,
                true,
                true,
                false);
            // some additional chart customisation here...
            XYPlot plot = (XYPlot) chart.getPlot();
            XYLineAndShapeRenderer renderer
                = (XYLineAndShapeRenderer) plot.getRenderer();
            renderer.setBaseShapesVisible(true);
            DateAxis axis = (DateAxis) plot.getDomainAxis();
            axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));


            // create a second chart
            DefaultPieDataset dataset2 = new DefaultPieDataset();
            dataset2.setValue("A", 74.3);
            dataset2.setValue("B", 25.2);
            JFreeChart chart2 = ChartFactory.createPieChart("Pie Chart",
                    dataset2, false, false, false);


            // write both charts to a single page in a PDF file...
            JFreeChart[] charts = new JFreeChart[] { chart, chart2 };
            File fileName = new File(System.getProperty("user.home")
                                     + "/jfreechart1.pdf");
            saveChartsAsPDF(fileName, charts, 400, 300, new DefaultFontMapper());
        }
        catch (IOException e) {
                System.out.println(e.getMessage());
        }
    }

}
