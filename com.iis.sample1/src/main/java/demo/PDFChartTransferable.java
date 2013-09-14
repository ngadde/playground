/* -------------------------
 * PDFChartTransferable.java
 * -------------------------
 * (C) Copyright 2009, by Object Refinery Limited.
 *
 */

package demo;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.FontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.jfree.chart.JFreeChart;

public class PDFChartTransferable implements Transferable {

    /** The data flavor. */
    final DataFlavor pdfFlavor = new DataFlavor("application/pdf", "PDF");

    /** The chart. */
    private JFreeChart chart;

    /** The width of the chart on the clipboard. */
    private int width;

    /** The height of the chart on the clipboard. */
    private int height;

    /**
     * Creates a new chart selection.
     *
     * @param chart  the chart.
     * @param width  the chart width.
     * @param height  the chart height.
     */
    public PDFChartTransferable(JFreeChart chart, int width, int height) {
        this(chart, width, height, true);
    }

    /**
     * Creates a new chart selection.
     *
     * @param chart  the chart.
     * @param width  the chart width.
     * @param height  the chart height.
     * @param cloneData  clone the dataset(s)?
     */
    public PDFChartTransferable(JFreeChart chart, int width, int height,
            boolean cloneData) {

        // we clone the chart because presumably there can be some delay
        // between putting this instance on the system clipboard and
        // actually having the getTransferData() method called...
        try {
            this.chart = (JFreeChart) chart.clone();
        }
        catch (CloneNotSupportedException e) {
            this.chart = chart;
        }
        this.width = width;
        this.height = height;
        // FIXME: we've cloned the chart, but the dataset(s) aren't cloned
        // and we should do that
    }

    /**
     * Returns the data flavors supported.
     *
     * @return The data flavors supported.
     */
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] {this.pdfFlavor};
    }

    /**
     * Returns <code>true</code> if the specified flavor is supported.
     *
     * @param flavor  the flavor.
     *
     * @return A boolean.
     */
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return this.pdfFlavor.equals(flavor);
    }

    /**
     * Returns the content for the requested flavor, if it is supported.
     *
     * @param flavor  the requested flavor.
     *
     * @return The content.
     *
     * @throws java.awt.datatransfer.UnsupportedFlavorException
     * @throws java.io.IOException
     */
    public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException, IOException {

        if (this.pdfFlavor.equals(flavor)) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            writeChartAsPDF(out, this.chart, this.width, this.height, new DefaultFontMapper());
            return new ByteArrayInputStream(out.toByteArray());
        }
        else {
            throw new UnsupportedFlavorException(flavor);
        }
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
    public static void writeChartAsPDF(ByteArrayOutputStream out,
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

}
