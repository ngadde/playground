/* -------------------------
 * XYBoxAnnotationDemo1.java
 * -------------------------
 * (C) Copyright 2006, 2009, by Object Refinery Limited and Contributors.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Rectangle;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYBoxAnnotation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing the use of an <code>XYBoxAnnotation</code>.
 */
public class XYBoxAnnotationDemo1 extends ApplicationFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6417805489307300590L;

	/**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public XYBoxAnnotationDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    public static XYDataset createDataset() {

        XYSeries series1 = new XYSeries("Old", false, true);
        XYSeries series2 = new XYSeries("New", false, true);

        series1.add(new Day(06, 11, 2003).getMiddleMillisecond(), 617);
        series1.add(new Day(07, 11, 2003).getMiddleMillisecond(), 3065);
        series1.add(new Day(14, 11, 2003).getMiddleMillisecond(), 1964);
        series1.add(new Day(17, 12, 2003).getMiddleMillisecond(), 204);
        series1.add(new Day(13, 11, 2003).getMiddleMillisecond(), 10320);
        series1.add(new Day(05, 11, 2003).getMiddleMillisecond(), 17892);
        series1.add(new Day(11, 12, 2003).getMiddleMillisecond(), 4269);
        series1.add(new Day(12, 12, 2003).getMiddleMillisecond(), 48);
        series1.add(new Day(19, 12, 2003).getMiddleMillisecond(), 7335);
        series1.add(new Day(06, 11, 2003).getMiddleMillisecond(), 30887);
        series1.add(new Day(8, 12, 2003).getMiddleMillisecond(), 7767);
        series1.add(new Day(29, 11, 2003).getMiddleMillisecond(), 11701);
        series1.add(new Day(07, 11, 2003).getMiddleMillisecond(), 2202);
        series1.add(new Day(02, 02, 2004).getMiddleMillisecond(), 233);
        series1.add(new Day(20, 01, 2004).getMiddleMillisecond(), 8572);
        series1.add(new Day(26, 11, 2003).getMiddleMillisecond(), 16607);
        series1.add(new Day(20, 11, 2003).getMiddleMillisecond(), 5945);
        series1.add(new Day(16, 01, 2004).getMiddleMillisecond(), 1322);
        series1.add(new Day(12, 01, 2004).getMiddleMillisecond(), 6600);
        series1.add(new Day(27, 11, 2003).getMiddleMillisecond(), 19080);
        series1.add(new Day(05, 11, 2003).getMiddleMillisecond(), 39000);
        series1.add(new Day(25, 11, 2003).getMiddleMillisecond(), 28871);
        series1.add(new Day(04, 02, 2004).getMiddleMillisecond(), 1387);
        series1.add(new Day(19, 01, 2004).getMiddleMillisecond(), 1750);
        series1.add(new Day(10, 12, 2003).getMiddleMillisecond(), 340);
        series1.add(new Day(02, 02, 2004).getMiddleMillisecond(), 1100);
        series1.add(new Day(11, 02, 2004).getMiddleMillisecond(), 1586);
        series1.add(new Day(23, 01, 2004).getMiddleMillisecond(), 482);
        series1.add(new Day(20, 02, 2004).getMiddleMillisecond(), 1520);
        series1.add(new Day(19, 01, 2004).getMiddleMillisecond(), 213);
        series1.add(new Day(8, 12, 2003).getMiddleMillisecond(), 7398);
        series1.add(new Day(24, 01, 2004).getMiddleMillisecond(), 2903);
        series1.add(new Day(14, 02, 2004).getMiddleMillisecond(), 724);
        series1.add(new Day(03, 11, 2003).getMiddleMillisecond(), 58936);
        series1.add(new Day(07, 11, 2003).getMiddleMillisecond(), 24494);
        series1.add(new Day(25, 01, 2004).getMiddleMillisecond(), 994);
        series1.add(new Day(15, 01, 2004).getMiddleMillisecond(), 6610);
        series1.add(new Day(02, 02, 2004).getMiddleMillisecond(), 1081);
        series1.add(new Day(22, 01, 2004).getMiddleMillisecond(), 4350);
        series1.add(new Day(27, 11, 2003).getMiddleMillisecond(), 45009);
        series1.add(new Day(03, 11, 2003).getMiddleMillisecond(), 23746);
        series1.add(new Day(25, 11, 2003).getMiddleMillisecond(), 3082);
        series1.add(new Day(24, 01, 2004).getMiddleMillisecond(), 12425);
        series1.add(new Day(04, 12, 2003).getMiddleMillisecond(), 90);
        series1.add(new Day(07, 11, 2003).getMiddleMillisecond(), 6500);
        series1.add(new Day(02, 12, 2003).getMiddleMillisecond(), 36350);
        series1.add(new Day(10, 02, 2004).getMiddleMillisecond(), 7983);
        series1.add(new Day(16, 01, 2004).getMiddleMillisecond(), 7250);
        series1.add(new Day(10, 02, 2004).getMiddleMillisecond(), 4881);
        series1.add(new Day(25, 02, 2004).getMiddleMillisecond(), 6616);
        series1.add(new Day(20, 01, 2004).getMiddleMillisecond(), 4700);
        series1.add(new Day(12, 12, 2003).getMiddleMillisecond(), 47317);
        series1.add(new Day(24, 03, 2004).getMiddleMillisecond(), 314);
        series1.add(new Day(04, 12, 2003).getMiddleMillisecond(), 20885);
        series1.add(new Day(07, 01, 2004).getMiddleMillisecond(), 13477);
        series1.add(new Day(19, 01, 2004).getMiddleMillisecond(), 23527);
        series1.add(new Day(26, 01, 2004).getMiddleMillisecond(), 22224);
        series1.add(new Day(02, 02, 2004).getMiddleMillisecond(), 17467);
        series1.add(new Day(20, 02, 2004).getMiddleMillisecond(), 5474);
        series1.add(new Day(18, 11, 2003).getMiddleMillisecond(), 14897);
        series1.add(new Day(9, 01, 2004).getMiddleMillisecond(), 2459);
        series1.add(new Day(20, 02, 2004).getMiddleMillisecond(), 5712);
        series1.add(new Day(13, 03, 2004).getMiddleMillisecond(), 1796);
        series1.add(new Day(06, 04, 2004).getMiddleMillisecond(), 258);
        series1.add(new Day(31, 01, 2004).getMiddleMillisecond(), 19701);
        series1.add(new Day(03, 02, 2004).getMiddleMillisecond(), 5936);
        series1.add(new Day(02, 02, 2004).getMiddleMillisecond(), 22601);
        series1.add(new Day(8, 04, 2004).getMiddleMillisecond(), 1977);
        series1.add(new Day(02, 02, 2004).getMiddleMillisecond(), 17801);
        series1.add(new Day(02, 02, 2004).getMiddleMillisecond(), 17321);
        series1.add(new Day(06, 02, 2004).getMiddleMillisecond(), 17261);
        series1.add(new Day(02, 04, 2004).getMiddleMillisecond(), 3091);
        series1.add(new Day(02, 02, 2004).getMiddleMillisecond(), 17901);
        series1.add(new Day(02, 02, 2004).getMiddleMillisecond(), 14236);
        series1.add(new Day(04, 02, 2004).getMiddleMillisecond(), 18601);
        series1.add(new Day(07, 02, 2004).getMiddleMillisecond(), 18901);
        series1.add(new Day(9, 02, 2004).getMiddleMillisecond(), 18722);
        series1.add(new Day(04, 03, 2004).getMiddleMillisecond(), 8812);
        series1.add(new Day(18, 11, 2003).getMiddleMillisecond(), 42080);
        series1.add(new Day(8, 03, 2004).getMiddleMillisecond(), 8988);
        series1.add(new Day(15, 03, 2004).getMiddleMillisecond(), 654);
        series1.add(new Day(25, 03, 2004).getMiddleMillisecond(), 704);
        series1.add(new Day(24, 01, 2004).getMiddleMillisecond(), 267);
        series1.add(new Day(01, 03, 2004).getMiddleMillisecond(), 770);
        series1.add(new Day(16, 02, 2004).getMiddleMillisecond(), 19855);
        series1.add(new Day(15, 12, 2003).getMiddleMillisecond(), 40548);
        series1.add(new Day(21, 02, 2004).getMiddleMillisecond(), 23701);
        series1.add(new Day(19, 11, 2003).getMiddleMillisecond(), 2604);
        series1.add(new Day(26, 01, 2004).getMiddleMillisecond(), 31154);
        series1.add(new Day(11, 02, 2004).getMiddleMillisecond(), 21423);
        series1.add(new Day(10, 12, 2003).getMiddleMillisecond(), 22132);
        series1.add(new Day(22, 04, 2004).getMiddleMillisecond(), 5423);
        series1.add(new Day(19, 11, 2003).getMiddleMillisecond(), 2626);
        series1.add(new Day(02, 03, 2004).getMiddleMillisecond(), 6700);
        series1.add(new Day(14, 01, 2004).getMiddleMillisecond(), 332);
        series1.add(new Day(05, 04, 2004).getMiddleMillisecond(), 4980);
        series1.add(new Day(31, 01, 2004).getMiddleMillisecond(), 22655);
        series1.add(new Day(9, 02, 2004).getMiddleMillisecond(), 26510);
        series1.add(new Day(27, 11, 2003).getMiddleMillisecond(), 44228);
        series1.add(new Day(04, 03, 2004).getMiddleMillisecond(), 2406);
        series1.add(new Day(02, 02, 2004).getMiddleMillisecond(), 34801);
        series1.add(new Day(13, 02, 2004).getMiddleMillisecond(), 13332);
        series1.add(new Day(19, 04, 2004).getMiddleMillisecond(), 2722);
        series1.add(new Day(18, 02, 2004).getMiddleMillisecond(), 31212);
        series1.add(new Day(04, 03, 2004).getMiddleMillisecond(), 17351);
        series1.add(new Day(15, 03, 2004).getMiddleMillisecond(), 12784);
        series1.add(new Day(05, 12, 2003).getMiddleMillisecond(), 24157);
        series1.add(new Day(12, 03, 2004).getMiddleMillisecond(), 1053);
        series1.add(new Day(18, 12, 2003).getMiddleMillisecond(), 56533);
        series1.add(new Day(19, 12, 2003).getMiddleMillisecond(), 38394);
        series1.add(new Day(19, 12, 2003).getMiddleMillisecond(), 21997);
        series1.add(new Day(02, 02, 2004).getMiddleMillisecond(), 31113);
        series1.add(new Day(17, 12, 2003).getMiddleMillisecond(), 54535);
        series1.add(new Day(8, 03, 2004).getMiddleMillisecond(), 17451);
        series1.add(new Day(8, 03, 2004).getMiddleMillisecond(), 17451);
        series1.add(new Day(04, 02, 2004).getMiddleMillisecond(), 1070);
        series1.add(new Day(04, 11, 2003).getMiddleMillisecond(), 14501);
        series1.add(new Day(04, 02, 2004).getMiddleMillisecond(), 11119);
        series1.add(new Day(19, 04, 2004).getMiddleMillisecond(), 2059);
        series1.add(new Day(16, 01, 2004).getMiddleMillisecond(), 12268);
        series1.add(new Day(22, 12, 2003).getMiddleMillisecond(), 44210);
        series1.add(new Day(07, 11, 2003).getMiddleMillisecond(), 1240);
        series1.add(new Day(10, 02, 2004).getMiddleMillisecond(), 32734);
        series1.add(new Day(19, 12, 2003).getMiddleMillisecond(), 85800);
        series1.add(new Day(18, 11, 2003).getMiddleMillisecond(), 21576);
        series1.add(new Day(24, 05, 2004).getMiddleMillisecond(), 3978);
        series1.add(new Day(13, 01, 2004).getMiddleMillisecond(), 4526);
        series1.add(new Day(8, 03, 2004).getMiddleMillisecond(), 12463);
        series1.add(new Day(02, 02, 2004).getMiddleMillisecond(), 48991);
        series1.add(new Day(15, 12, 2003).getMiddleMillisecond(), 72193);
        series1.add(new Day(04, 12, 2003).getMiddleMillisecond(), 89024);
        series1.add(new Day(06, 05, 2004).getMiddleMillisecond(), 2001);
        series1.add(new Day(02, 02, 2004).getMiddleMillisecond(), 30610);
        series1.add(new Day(04, 03, 2004).getMiddleMillisecond(), 246);
        series1.add(new Day(27, 11, 2003).getMiddleMillisecond(), 63135);
        series1.add(new Day(01, 03, 2004).getMiddleMillisecond(), 38865);
        series1.add(new Day(03, 05, 2004).getMiddleMillisecond(), 389);
        series1.add(new Day(28, 04, 2004).getMiddleMillisecond(), 3723);
        series1.add(new Day(10, 12, 2003).getMiddleMillisecond(), 58745);
        series1.add(new Day(18, 05, 2004).getMiddleMillisecond(), 2635);
        series1.add(new Day(11, 03, 2004).getMiddleMillisecond(), 348);
        series1.add(new Day(31, 03, 2004).getMiddleMillisecond(), 5085);
        series1.add(new Day(02, 02, 2004).getMiddleMillisecond(), 40608);
        series1.add(new Day(25, 02, 2004).getMiddleMillisecond(), 9864);
        series1.add(new Day(06, 05, 2004).getMiddleMillisecond(), 20239);
        series1.add(new Day(14, 06, 2004).getMiddleMillisecond(), 280);
        series1.add(new Day(28, 04, 2004).getMiddleMillisecond(), 14873);
        series1.add(new Day(16, 12, 2003).getMiddleMillisecond(), 30845);
        series1.add(new Day(19, 05, 2004).getMiddleMillisecond(), 729);
        series1.add(new Day(29, 01, 2004).getMiddleMillisecond(), 983);
        series1.add(new Day(8, 04, 2004).getMiddleMillisecond(), 619);
        series1.add(new Day(04, 11, 2003).getMiddleMillisecond(), 18037);
        series1.add(new Day(02, 12, 2003).getMiddleMillisecond(), 9772);
        series1.add(new Day(15, 01, 2004).getMiddleMillisecond(), 125837);
        series1.add(new Day(27, 01, 2004).getMiddleMillisecond(), 84704);
        series1.add(new Day(27, 01, 2004).getMiddleMillisecond(), 10400);
        series1.add(new Day(18, 05, 2004).getMiddleMillisecond(), 16425);
        series1.add(new Day(06, 04, 2004).getMiddleMillisecond(), 30);
        series1.add(new Day(27, 02, 2004).getMiddleMillisecond(), 104978);
        series1.add(new Day(05, 03, 2004).getMiddleMillisecond(), 17359);
        series1.add(new Day(07, 04, 2004).getMiddleMillisecond(), 55074);
        series1.add(new Day(19, 12, 2003).getMiddleMillisecond(), 55630);
        series1.add(new Day(10, 12, 2003).getMiddleMillisecond(), 47055);
        series1.add(new Day(8, 03, 2004).getMiddleMillisecond(), 16900);
        series1.add(new Day(26, 01, 2004).getMiddleMillisecond(), 34819);
        series1.add(new Day(21, 06, 2004).getMiddleMillisecond(), 514);
        series1.add(new Day(28, 05, 2004).getMiddleMillisecond(), 8195);
        series1.add(new Day(20, 03, 2004).getMiddleMillisecond(), 61870);
        series1.add(new Day(13, 03, 2004).getMiddleMillisecond(), 13000);
        series1.add(new Day(20, 01, 2004).getMiddleMillisecond(), 43152);
        series1.add(new Day(05, 03, 2004).getMiddleMillisecond(), 49653);
        series1.add(new Day(25, 01, 2004).getMiddleMillisecond(), 23311);
        series1.add(new Day(14, 11, 2003).getMiddleMillisecond(), 40166);
        series1.add(new Day(01, 12, 2003).getMiddleMillisecond(), 59504);
        series1.add(new Day(15, 01, 2004).getMiddleMillisecond(), 113349);
        series1.add(new Day(05, 06, 2004).getMiddleMillisecond(), 32110);
        series1.add(new Day(25, 01, 2004).getMiddleMillisecond(), 24693);
        series1.add(new Day(25, 02, 2004).getMiddleMillisecond(), 11668);
        series1.add(new Day(23, 04, 2004).getMiddleMillisecond(), 651);
        series1.add(new Day(03, 03, 2004).getMiddleMillisecond(), 24409);
        series1.add(new Day(11, 03, 2004).getMiddleMillisecond(), 43478);
        series1.add(new Day(24, 03, 2004).getMiddleMillisecond(), 13000);
        series1.add(new Day(9, 07, 2004).getMiddleMillisecond(), 4370);
        series1.add(new Day(12, 01, 2004).getMiddleMillisecond(), 67648);
        series1.add(new Day(05, 04, 2004).getMiddleMillisecond(), 44427);
        series1.add(new Day(28, 04, 2004).getMiddleMillisecond(), 35210);
        series1.add(new Day(30, 06, 2004).getMiddleMillisecond(), 784);
        series1.add(new Day(8, 04, 2004).getMiddleMillisecond(), 41645);
        series1.add(new Day(01, 12, 2003).getMiddleMillisecond(), 46919);
        series1.add(new Day(04, 06, 2004).getMiddleMillisecond(), 19853);
        series1.add(new Day(06, 05, 2004).getMiddleMillisecond(), 18070);
        series1.add(new Day(15, 01, 2004).getMiddleMillisecond(), 5213);
        series1.add(new Day(14, 11, 2003).getMiddleMillisecond(), 47288);
        series1.add(new Day(24, 03, 2004).getMiddleMillisecond(), 18262);
        series1.add(new Day(02, 12, 2003).getMiddleMillisecond(), 63186);
        series1.add(new Day(30, 07, 2004).getMiddleMillisecond(), 8980);
        series1.add(new Day(22, 06, 2004).getMiddleMillisecond(), 1627);
        series1.add(new Day(8, 12, 2003).getMiddleMillisecond(), 33833);
        series1.add(new Day(05, 03, 2004).getMiddleMillisecond(), 68608);
        series1.add(new Day(12, 05, 2004).getMiddleMillisecond(), 785);
        series1.add(new Day(30, 07, 2004).getMiddleMillisecond(), 12055);
        series1.add(new Day(11, 06, 2004).getMiddleMillisecond(), 11902);
        series1.add(new Day(9, 8, 2004).getMiddleMillisecond(), 102);
        series1.add(new Day(19, 01, 2004).getMiddleMillisecond(), 69549);
        series1.add(new Day(27, 05, 2004).getMiddleMillisecond(), 4037);
        series1.add(new Day(21, 11, 2003).getMiddleMillisecond(), 94967);
        series1.add(new Day(19, 11, 2003).getMiddleMillisecond(), 99063);
        series1.add(new Day(26, 04, 2004).getMiddleMillisecond(), 365);
        series1.add(new Day(05, 04, 2004).getMiddleMillisecond(), 30681);
        series1.add(new Day(06, 07, 2004).getMiddleMillisecond(), 15798);
        series1.add(new Day(13, 03, 2004).getMiddleMillisecond(), 111394);
        series1.add(new Day(27, 05, 2004).getMiddleMillisecond(), 781);
        series1.add(new Day(9, 06, 2004).getMiddleMillisecond(), 30862);
        series1.add(new Day(07, 04, 2004).getMiddleMillisecond(), 58378);
        series1.add(new Day(10, 11, 2003).getMiddleMillisecond(), 27414);
        series1.add(new Day(28, 07, 2004).getMiddleMillisecond(), 1264);
        series1.add(new Day(27, 05, 2004).getMiddleMillisecond(), 3700);
        series1.add(new Day(30, 07, 2004).getMiddleMillisecond(), 23915);
        series1.add(new Day(21, 05, 2004).getMiddleMillisecond(), 3140);
        series1.add(new Day(13, 8, 2004).getMiddleMillisecond(), 9925);
        series1.add(new Day(24, 05, 2004).getMiddleMillisecond(), 45225);
        series1.add(new Day(03, 02, 2004).getMiddleMillisecond(), 2816);
        series1.add(new Day(11, 02, 2004).getMiddleMillisecond(), 84916);
        series1.add(new Day(19, 04, 2004).getMiddleMillisecond(), 10025);
        series1.add(new Day(27, 11, 2003).getMiddleMillisecond(), 25607);
        series1.add(new Day(21, 06, 2004).getMiddleMillisecond(), 6961);
        series1.add(new Day(13, 8, 2004).getMiddleMillisecond(), 758);
        series1.add(new Day(11, 02, 2004).getMiddleMillisecond(), 53802);
        series1.add(new Day(19, 12, 2003).getMiddleMillisecond(), 125030);
        series1.add(new Day(04, 10, 2004).getMiddleMillisecond(), 3056);
        series1.add(new Day(13, 8, 2004).getMiddleMillisecond(), 1700);
        series1.add(new Day(11, 05, 2004).getMiddleMillisecond(), 12967);
        series1.add(new Day(15, 12, 2003).getMiddleMillisecond(), 4549);
        series1.add(new Day(17, 11, 2003).getMiddleMillisecond(), 152336);
        series1.add(new Day(15, 12, 2003).getMiddleMillisecond(), 110483);
        series1.add(new Day(22, 06, 2004).getMiddleMillisecond(), 27274);
        series1.add(new Day(28, 9, 2004).getMiddleMillisecond(), 8161);
        series1.add(new Day(27, 11, 2003).getMiddleMillisecond(), 103805);
        series1.add(new Day(11, 10, 2004).getMiddleMillisecond(), 32);
        series1.add(new Day(19, 01, 2004).getMiddleMillisecond(), 1847);
        series1.add(new Day(11, 06, 2004).getMiddleMillisecond(), 40917);
        series1.add(new Day(13, 8, 2004).getMiddleMillisecond(), 6125);
        series1.add(new Day(8, 10, 2004).getMiddleMillisecond(), 7822);
        series1.add(new Day(10, 9, 2004).getMiddleMillisecond(), 28213);
        series1.add(new Day(16, 9, 2004).getMiddleMillisecond(), 15285);
        series1.add(new Day(18, 10, 2004).getMiddleMillisecond(), 4015);
        series1.add(new Day(25, 8, 2004).getMiddleMillisecond(), 13929);
        series1.add(new Day(20, 03, 2004).getMiddleMillisecond(), 55132);
        series1.add(new Day(26, 10, 2004).getMiddleMillisecond(), 3473);
        series1.add(new Day(27, 10, 2004).getMiddleMillisecond(), 2);
        series1.add(new Day(11, 06, 2004).getMiddleMillisecond(), 34557);
        series1.add(new Day(04, 05, 2004).getMiddleMillisecond(), 55015);
        series1.add(new Day(07, 06, 2004).getMiddleMillisecond(), 1750);
        series1.add(new Day(23, 02, 2004).getMiddleMillisecond(), 109978);
        series1.add(new Day(13, 8, 2004).getMiddleMillisecond(), 43759);
        series1.add(new Day(12, 8, 2004).getMiddleMillisecond(), 42897);
        series1.add(new Day(10, 03, 2004).getMiddleMillisecond(), 91480);
        series1.add(new Day(19, 02, 2004).getMiddleMillisecond(), 141200);
        series1.add(new Day(28, 05, 2004).getMiddleMillisecond(), 5437);
        series1.add(new Day(9, 8, 2004).getMiddleMillisecond(), 2750);
        series1.add(new Day(13, 04, 2004).getMiddleMillisecond(), 25170);
        series1.add(new Day(12, 05, 2004).getMiddleMillisecond(), 45996);
        series1.add(new Day(17, 12, 2003).getMiddleMillisecond(), 61903);
        series1.add(new Day(15, 04, 2004).getMiddleMillisecond(), 75129);
        series1.add(new Day(26, 10, 2004).getMiddleMillisecond(), 15217);
        series1.add(new Day(27, 07, 2004).getMiddleMillisecond(), 15352);
        series1.add(new Day(05, 05, 2004).getMiddleMillisecond(), 74223);
        series1.add(new Day(27, 02, 2004).getMiddleMillisecond(), 97847);
        series1.add(new Day(24, 11, 2003).getMiddleMillisecond(), 13082);
        series1.add(new Day(17, 11, 2004).getMiddleMillisecond(), 4019);
        series1.add(new Day(25, 10, 2004).getMiddleMillisecond(), 186);
        series1.add(new Day(07, 01, 2004).getMiddleMillisecond(), 10595);
        series1.add(new Day(04, 11, 2004).getMiddleMillisecond(), 240);
        series1.add(new Day(9, 8, 2004).getMiddleMillisecond(), 35085);
        series1.add(new Day(17, 07, 2004).getMiddleMillisecond(), 59925);
        series1.add(new Day(17, 8, 2004).getMiddleMillisecond(), 43200);
        series1.add(new Day(03, 11, 2004).getMiddleMillisecond(), 217);
        series1.add(new Day(30, 10, 2004).getMiddleMillisecond(), 637);
        series1.add(new Day(20, 9, 2004).getMiddleMillisecond(), 12296);
        series1.add(new Day(17, 11, 2004).getMiddleMillisecond(), 4299);
        series1.add(new Day(29, 11, 2004).getMiddleMillisecond(), 3137);
        series1.add(new Day(06, 05, 2004).getMiddleMillisecond(), 62702);
        series1.add(new Day(24, 06, 2004).getMiddleMillisecond(), 23243);
        series1.add(new Day(9, 9, 2004).getMiddleMillisecond(), 873);
        series1.add(new Day(8, 9, 2004).getMiddleMillisecond(), 23100);
        series1.add(new Day(20, 8, 2004).getMiddleMillisecond(), 207);
        series1.add(new Day(01, 04, 2004).getMiddleMillisecond(), 105779);
        series1.add(new Day(30, 03, 2004).getMiddleMillisecond(), 69874);
        series1.add(new Day(8, 9, 2004).getMiddleMillisecond(), 19287);
        series1.add(new Day(26, 07, 2004).getMiddleMillisecond(), 59130);
        series1.add(new Day(14, 9, 2004).getMiddleMillisecond(), 13343);
        series1.add(new Day(03, 03, 2004).getMiddleMillisecond(), 89040);
        series1.add(new Day(13, 12, 2004).getMiddleMillisecond(), 4752);
        series1.add(new Day(16, 01, 2004).getMiddleMillisecond(), 2321);
        series1.add(new Day(26, 04, 2004).getMiddleMillisecond(), 24831);
        series1.add(new Day(15, 01, 2004).getMiddleMillisecond(), 3390);
        series1.add(new Day(8, 11, 2004).getMiddleMillisecond(), 2340);
        series1.add(new Day(15, 12, 2004).getMiddleMillisecond(), 1553);
        series1.add(new Day(21, 9, 2004).getMiddleMillisecond(), 7186);
        series1.add(new Day(30, 10, 2004).getMiddleMillisecond(), 16444);
        series1.add(new Day(26, 05, 2004).getMiddleMillisecond(), 54688);
        series1.add(new Day(13, 10, 2004).getMiddleMillisecond(), 12562);
        series1.add(new Day(07, 10, 2004).getMiddleMillisecond(), 4023);
        series1.add(new Day(11, 11, 2004).getMiddleMillisecond(), 12999);
        series1.add(new Day(25, 10, 2004).getMiddleMillisecond(), 5378);
        series1.add(new Day(29, 06, 2004).getMiddleMillisecond(), 49340);
        series1.add(new Day(14, 01, 2004).getMiddleMillisecond(), 22112);
        series1.add(new Day(9, 02, 2004).getMiddleMillisecond(), 79733);
        series1.add(new Day(23, 9, 2004).getMiddleMillisecond(), 490);
        series1.add(new Day(13, 11, 2004).getMiddleMillisecond(), 59);
        series1.add(new Day(13, 10, 2004).getMiddleMillisecond(), 31160);
        series1.add(new Day(18, 02, 2004).getMiddleMillisecond(), 104655);
        series1.add(new Day(26, 07, 2004).getMiddleMillisecond(), 2956);
        series1.add(new Day(10, 12, 2004).getMiddleMillisecond(), 80);
        series1.add(new Day(13, 03, 2004).getMiddleMillisecond(), 192969);
        series1.add(new Day(9, 8, 2004).getMiddleMillisecond(), 45940);
        series1.add(new Day(8, 04, 2004).getMiddleMillisecond(), 109268);
        series1.add(new Day(04, 10, 2004).getMiddleMillisecond(), 12716);
        series1.add(new Day(9, 12, 2004).getMiddleMillisecond(), 8430);
        series1.add(new Day(18, 11, 2004).getMiddleMillisecond(), 24994);
        series1.add(new Day(17, 03, 2004).getMiddleMillisecond(), 96378);
        series1.add(new Day(20, 12, 2004).getMiddleMillisecond(), 11023);
        series1.add(new Day(20, 12, 2004).getMiddleMillisecond(), 11023);
        series1.add(new Day(14, 06, 2004).getMiddleMillisecond(), 49043);
        series1.add(new Day(06, 11, 2004).getMiddleMillisecond(), 21168);
        series1.add(new Day(04, 9, 2004).getMiddleMillisecond(), 64336);
        series1.add(new Day(12, 01, 2005).getMiddleMillisecond(), 522);
        series1.add(new Day(17, 06, 2004).getMiddleMillisecond(), 84255);
        series1.add(new Day(11, 11, 2004).getMiddleMillisecond(), 4106);
        series1.add(new Day(13, 9, 2004).getMiddleMillisecond(), 69040);
        series1.add(new Day(9, 06, 2004).getMiddleMillisecond(), 44534);
        series1.add(new Day(29, 10, 2004).getMiddleMillisecond(), 1556);
        series1.add(new Day(21, 9, 2004).getMiddleMillisecond(), 28048);
        series1.add(new Day(14, 01, 2005).getMiddleMillisecond(), 482);
        series1.add(new Day(12, 02, 2004).getMiddleMillisecond(), 137490);
        series1.add(new Day(9, 10, 2004).getMiddleMillisecond(), 19965);
        series1.add(new Day(05, 01, 2005).getMiddleMillisecond(), 1625);
        series1.add(new Day(12, 11, 2004).getMiddleMillisecond(), 17972);
        series1.add(new Day(23, 9, 2004).getMiddleMillisecond(), 23591);
        series1.add(new Day(8, 04, 2004).getMiddleMillisecond(), 44562);
        series1.add(new Day(04, 10, 2004).getMiddleMillisecond(), 40126);
        series1.add(new Day(12, 8, 2004).getMiddleMillisecond(), 84683);
        series1.add(new Day(9, 11, 2004).getMiddleMillisecond(), 11943);
        series1.add(new Day(27, 07, 2004).getMiddleMillisecond(), 114763);
        series1.add(new Day(9, 8, 2004).getMiddleMillisecond(), 61353);
        series1.add(new Day(04, 01, 2005).getMiddleMillisecond(), 8746);
        series1.add(new Day(13, 10, 2004).getMiddleMillisecond(), 43273);
        series1.add(new Day(17, 9, 2004).getMiddleMillisecond(), 36157);
        series1.add(new Day(8, 12, 2004).getMiddleMillisecond(), 19701);
        series1.add(new Day(02, 06, 2004).getMiddleMillisecond(), 58987);
        series1.add(new Day(06, 07, 2004).getMiddleMillisecond(), 17320);
        series1.add(new Day(18, 12, 2004).getMiddleMillisecond(), 6923);
        series1.add(new Day(13, 03, 2004).getMiddleMillisecond(), 49538);
        series1.add(new Day(20, 01, 2005).getMiddleMillisecond(), 350);
        series1.add(new Day(19, 03, 2004).getMiddleMillisecond(), 45218);
        series1.add(new Day(16, 12, 2004).getMiddleMillisecond(), 9122);
        series1.add(new Day(12, 01, 2004).getMiddleMillisecond(), 150321);
        series1.add(new Day(17, 11, 2004).getMiddleMillisecond(), 44811);
        series1.add(new Day(24, 11, 2004).getMiddleMillisecond(), 5229);
        series1.add(new Day(19, 05, 2004).getMiddleMillisecond(), 103090);
        series1.add(new Day(9, 06, 2004).getMiddleMillisecond(), 57797);
        series1.add(new Day(29, 06, 2004).getMiddleMillisecond(), 20401);
        series1.add(new Day(15, 10, 2004).getMiddleMillisecond(), 22720);
        series1.add(new Day(03, 02, 2005).getMiddleMillisecond(), 7430);
        series1.add(new Day(16, 8, 2004).getMiddleMillisecond(), 1450);
        series1.add(new Day(02, 11, 2004).getMiddleMillisecond(), 44595);
        series1.add(new Day(11, 02, 2004).getMiddleMillisecond(), 165301);
        series1.add(new Day(28, 06, 2004).getMiddleMillisecond(), 70065);
        series1.add(new Day(23, 06, 2004).getMiddleMillisecond(), 18394);
        series1.add(new Day(26, 07, 2004).getMiddleMillisecond(), 45087);
        series1.add(new Day(20, 10, 2004).getMiddleMillisecond(), 6971);
        series1.add(new Day(29, 11, 2004).getMiddleMillisecond(), 971);
        series1.add(new Day(02, 03, 2005).getMiddleMillisecond(), 4241);
        series1.add(new Day(07, 10, 2004).getMiddleMillisecond(), 2671);
        series1.add(new Day(14, 04, 2004).getMiddleMillisecond(), 78014);
        series1.add(new Day(13, 8, 2004).getMiddleMillisecond(), 81107);
        series1.add(new Day(15, 11, 2004).getMiddleMillisecond(), 37558);
        series1.add(new Day(02, 03, 2005).getMiddleMillisecond(), 6128);
        series1.add(new Day(17, 12, 2004).getMiddleMillisecond(), 6478);
        series1.add(new Day(27, 11, 2003).getMiddleMillisecond(), 39804);
        series1.add(new Day(13, 04, 2004).getMiddleMillisecond(), 25591);
        series1.add(new Day(18, 05, 2004).getMiddleMillisecond(), 2297);
        series1.add(new Day(02, 02, 2005).getMiddleMillisecond(), 3785);
        series1.add(new Day(18, 8, 2004).getMiddleMillisecond(), 5256);
        series1.add(new Day(24, 02, 2005).getMiddleMillisecond(), 6100);
        series1.add(new Day(13, 05, 2004).getMiddleMillisecond(), 86738);
        series1.add(new Day(31, 03, 2005).getMiddleMillisecond(), 278);
        series1.add(new Day(06, 8, 2004).getMiddleMillisecond(), 110455);
        series1.add(new Day(30, 9, 2004).getMiddleMillisecond(), 99828);
        series1.add(new Day(22, 10, 2004).getMiddleMillisecond(), 64827);
        series1.add(new Day(13, 10, 2004).getMiddleMillisecond(), 149269);
        series1.add(new Day(16, 8, 2004).getMiddleMillisecond(), 5948);
        series1.add(new Day(13, 04, 2005).getMiddleMillisecond(), 6025);
        series1.add(new Day(28, 02, 2005).getMiddleMillisecond(), 7755);
        series1.add(new Day(8, 12, 2004).getMiddleMillisecond(), 44226);
        series1.add(new Day(8, 07, 2004).getMiddleMillisecond(), 89481);
        series1.add(new Day(19, 05, 2004).getMiddleMillisecond(), 85650);
        series1.add(new Day(28, 9, 2004).getMiddleMillisecond(), 50713);
        series1.add(new Day(13, 8, 2004).getMiddleMillisecond(), 94151);
        series1.add(new Day(20, 01, 2005).getMiddleMillisecond(), 38905);
        series1.add(new Day(07, 04, 2005).getMiddleMillisecond(), 437);
        series1.add(new Day(31, 01, 2004).getMiddleMillisecond(), 155322);
        series1.add(new Day(26, 10, 2004).getMiddleMillisecond(), 80867);
        series1.add(new Day(10, 01, 2005).getMiddleMillisecond(), 22530);
        series1.add(new Day(18, 03, 2005).getMiddleMillisecond(), 7280);
        series1.add(new Day(03, 03, 2005).getMiddleMillisecond(), 199);
        series1.add(new Day(13, 9, 2004).getMiddleMillisecond(), 93475);
        series1.add(new Day(11, 8, 2004).getMiddleMillisecond(), 74984);
        series1.add(new Day(21, 12, 2004).getMiddleMillisecond(), 28153);
        series1.add(new Day(18, 03, 2005).getMiddleMillisecond(), 6725);
        series1.add(new Day(05, 04, 2005).getMiddleMillisecond(), 2095);
        series1.add(new Day(15, 07, 2004).getMiddleMillisecond(), 140022);
        series1.add(new Day(12, 11, 2004).getMiddleMillisecond(), 63582);
        series1.add(new Day(02, 04, 2004).getMiddleMillisecond(), 5117);
        series1.add(new Day(17, 05, 2004).getMiddleMillisecond(), 93320);
        series1.add(new Day(15, 12, 2004).getMiddleMillisecond(), 20393);
        series1.add(new Day(01, 03, 2004).getMiddleMillisecond(), 29900);
        series1.add(new Day(27, 04, 2005).getMiddleMillisecond(), 832);
        series1.add(new Day(15, 06, 2004).getMiddleMillisecond(), 69073);
        series1.add(new Day(20, 05, 2005).getMiddleMillisecond(), 2625);
        series1.add(new Day(21, 04, 2005).getMiddleMillisecond(), 2956);
        series1.add(new Day(12, 02, 2005).getMiddleMillisecond(), 881);
        series1.add(new Day(8, 10, 2004).getMiddleMillisecond(), 95544);
        series1.add(new Day(9, 8, 2004).getMiddleMillisecond(), 103419);
        series1.add(new Day(23, 06, 2004).getMiddleMillisecond(), 99280);
        series1.add(new Day(27, 8, 2004).getMiddleMillisecond(), 47822);
        series1.add(new Day(9, 03, 2005).getMiddleMillisecond(), 1336);
        series1.add(new Day(23, 07, 2004).getMiddleMillisecond(), 78313);
        series1.add(new Day(26, 10, 2004).getMiddleMillisecond(), 111320);
        series1.add(new Day(03, 11, 2004).getMiddleMillisecond(), 6187);
        series1.add(new Day(18, 8, 2004).getMiddleMillisecond(), 88757);
        series1.add(new Day(8, 11, 2004).getMiddleMillisecond(), 64621);
        series1.add(new Day(13, 04, 2005).getMiddleMillisecond(), 5005);
        series1.add(new Day(14, 01, 2004).getMiddleMillisecond(), 18246);
        series1.add(new Day(15, 10, 2004).getMiddleMillisecond(), 50016);
        series1.add(new Day(24, 03, 2005).getMiddleMillisecond(), 24179);
        series1.add(new Day(04, 03, 2005).getMiddleMillisecond(), 24203);
        series1.add(new Day(01, 03, 2005).getMiddleMillisecond(), 6079);
        series1.add(new Day(19, 11, 2004).getMiddleMillisecond(), 77738);
        series1.add(new Day(28, 9, 2004).getMiddleMillisecond(), 49283);
        series1.add(new Day(24, 11, 2004).getMiddleMillisecond(), 4880);
        series1.add(new Day(25, 02, 2005).getMiddleMillisecond(), 30705);
        series1.add(new Day(22, 10, 2004).getMiddleMillisecond(), 89693);
        series1.add(new Day(01, 04, 2005).getMiddleMillisecond(), 2865);
        series1.add(new Day(20, 01, 2005).getMiddleMillisecond(), 46560);
        series1.add(new Day(04, 01, 2005).getMiddleMillisecond(), 16570);
        series1.add(new Day(13, 04, 2005).getMiddleMillisecond(), 2602);
        series1.add(new Day(17, 06, 2005).getMiddleMillisecond(), 551);
        series1.add(new Day(22, 10, 2004).getMiddleMillisecond(), 103984);
        series1.add(new Day(04, 01, 2005).getMiddleMillisecond(), 18078);
        series1.add(new Day(07, 04, 2005).getMiddleMillisecond(), 11146);
        series1.add(new Day(22, 04, 2005).getMiddleMillisecond(), 2266);
        series1.add(new Day(18, 05, 2004).getMiddleMillisecond(), 78472);
        series1.add(new Day(22, 10, 2004).getMiddleMillisecond(), 83375);
        series1.add(new Day(25, 8, 2004).getMiddleMillisecond(), 1265);
        series1.add(new Day(07, 04, 2005).getMiddleMillisecond(), 380);
        series1.add(new Day(20, 04, 2005).getMiddleMillisecond(), 30071);
        series1.add(new Day(02, 02, 2005).getMiddleMillisecond(), 2020);
        series1.add(new Day(24, 03, 2005).getMiddleMillisecond(), 30547);
        series2.add(new Day(22, 02, 2005).getMiddleMillisecond(), 57743);
        series2.add(new Day(05, 11, 2003).getMiddleMillisecond(), 21135);
        series2.add(new Day(28, 05, 2005).getMiddleMillisecond(), 4988);
        series2.add(new Day(05, 04, 2004).getMiddleMillisecond(), 51760);
        series2.add(new Day(16, 03, 2005).getMiddleMillisecond(), 2283);
        series2.add(new Day(16, 03, 2005).getMiddleMillisecond(), 12927);
        series2.add(new Day(26, 04, 2005).getMiddleMillisecond(), 9223);
        series2.add(new Day(15, 12, 2004).getMiddleMillisecond(), 88625);
        series2.add(new Day(19, 10, 2004).getMiddleMillisecond(), 106741);
        series2.add(new Day(11, 07, 2005).getMiddleMillisecond(), 1802);
        series2.add(new Day(20, 01, 2005).getMiddleMillisecond(), 43372);
        series2.add(new Day(30, 03, 2005).getMiddleMillisecond(), 67224);
        series2.add(new Day(06, 06, 2005).getMiddleMillisecond(), 8204);
        series2.add(new Day(02, 03, 2005).getMiddleMillisecond(), 38504);
        series2.add(new Day(01, 01, 2005).getMiddleMillisecond(), 47599);
        series2.add(new Day(30, 9, 2004).getMiddleMillisecond(), 64959);
        series2.add(new Day(07, 05, 2005).getMiddleMillisecond(), 30701);
        series2.add(new Day(03, 05, 2004).getMiddleMillisecond(), 150586);
        series2.add(new Day(05, 8, 2005).getMiddleMillisecond(), 713);
        series2.add(new Day(21, 06, 2005).getMiddleMillisecond(), 8091);
        series2.add(new Day(21, 06, 2005).getMiddleMillisecond(), 891);
        series2.add(new Day(8, 07, 2005).getMiddleMillisecond(), 5940);
        series2.add(new Day(18, 06, 2005).getMiddleMillisecond(), 453);
        series2.add(new Day(11, 8, 2005).getMiddleMillisecond(), 715);
        series2.add(new Day(23, 07, 2004).getMiddleMillisecond(), 45792);
        series2.add(new Day(11, 11, 2004).getMiddleMillisecond(), 83422);
        series2.add(new Day(03, 05, 2004).getMiddleMillisecond(), 216825);
        series2.add(new Day(26, 07, 2004).getMiddleMillisecond(), 3443);
        series2.add(new Day(25, 06, 2005).getMiddleMillisecond(), 21288);
        series2.add(new Day(28, 06, 2004).getMiddleMillisecond(), 64987);
        series2.add(new Day(15, 07, 2005).getMiddleMillisecond(), 113);
        series2.add(new Day(01, 12, 2004).getMiddleMillisecond(), 18000);
        series2.add(new Day(22, 06, 2005).getMiddleMillisecond(), 23581);
        series2.add(new Day(13, 01, 2005).getMiddleMillisecond(), 90667);
        series2.add(new Day(24, 06, 2004).getMiddleMillisecond(), 11599);
        series2.add(new Day(8, 12, 2004).getMiddleMillisecond(), 47985);
        series2.add(new Day(27, 06, 2005).getMiddleMillisecond(), 6545);
        series2.add(new Day(10, 06, 2005).getMiddleMillisecond(), 29100);
        series2.add(new Day(31, 8, 2004).getMiddleMillisecond(), 388);
        series2.add(new Day(10, 12, 2004).getMiddleMillisecond(), 73404);
        series2.add(new Day(16, 8, 2005).getMiddleMillisecond(), 516);
        series2.add(new Day(17, 12, 2004).getMiddleMillisecond(), 34289);
        series2.add(new Day(19, 10, 2004).getMiddleMillisecond(), 53409);
        series2.add(new Day(06, 01, 2005).getMiddleMillisecond(), 102640);
        series2.add(new Day(22, 04, 2005).getMiddleMillisecond(), 1741);
        series2.add(new Day(01, 10, 2004).getMiddleMillisecond(), 104899);
        series2.add(new Day(29, 07, 2005).getMiddleMillisecond(), 785);
        series2.add(new Day(20, 06, 2005).getMiddleMillisecond(), 24834);
        series2.add(new Day(10, 06, 2005).getMiddleMillisecond(), 34197);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Breakdowns",
                "Production Date", "Hours of Operation", dataset, true, true,
                false);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        XYLineAndShapeRenderer renderer
                = (XYLineAndShapeRenderer) plot.getRenderer();

        //Configure the presentation of the items.
        renderer.setSeriesShape(0, new Rectangle(2, 2));
        renderer.setSeriesShape(1, new Rectangle(2, 2));
        renderer.setBaseLinesVisible(false);
        renderer.setBaseShapesFilled(true);
        renderer.setBaseShapesVisible(true);

        // don't generate tooltips for data items
        renderer.setBaseToolTipGenerator(null);

        //Set the colors for the two different series of shapes
        renderer.setSeriesPaint(0, Color.blue);
        renderer.setSeriesPaint(1, Color.red);

        //Configure the grid lines
        plot.setDomainGridlineStroke(new BasicStroke(1.0f));
        plot.setRangeGridlineStroke(new BasicStroke(1.0f));

        //Configure the Y axis
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickUnit(new NumberTickUnit(50000));

        //Configure the X axis

        DateAxis domainAxis = (DateAxis) plot.getDomainAxis();
        domainAxis.setTickUnit(new DateTickUnit(DateTickUnitType.MONTH, 1));
        domainAxis.setDateFormatOverride(new SimpleDateFormat("MMM.yy"));
        domainAxis.setVerticalTickLabels(true);

        Paint transpRed = new Color(255, 0, 0, 60);
        Paint transpGreen = new Color(0, 255, 0, 60);

        XYBoxAnnotation annotation = new XYBoxAnnotation(
                new Day(01, 1, 2004).getMiddleMillisecond(), 0,
                new Day(31, 1, 2004).getMiddleMillisecond(), 50000,
                new BasicStroke(0.0f), transpRed, transpRed);
        annotation.setToolTipText("Value: 2.9");
        renderer.addAnnotation(annotation);

        annotation = new XYBoxAnnotation(
                new Day(01, 2, 2004).getMiddleMillisecond(), 0,
                new Day(29, 2, 2004).getMiddleMillisecond(), 50000,
                new BasicStroke(0.0f), transpRed, transpRed);
        annotation.setToolTipText("Value: 2.5");
        renderer.addAnnotation(annotation);

        annotation = new XYBoxAnnotation(
                new Day(01, 5, 2004).getMiddleMillisecond(), 50000,
                new Day(31, 5, 2004).getMiddleMillisecond(),
                100000, new BasicStroke(0.0f), transpRed, transpRed);
        annotation.setToolTipText("Value: 1.8");
        renderer.addAnnotation(annotation);

        annotation = new XYBoxAnnotation(
                new Day(01, 6, 2005).getMiddleMillisecond(), 0,
                new Day(30, 6, 2005).getMiddleMillisecond(), 50000,
                new BasicStroke(0.0f), transpGreen, transpGreen);
        annotation.setToolTipText("Value: 3.7");
        renderer.addAnnotation(annotation);

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
        XYBoxAnnotationDemo1 demo = new XYBoxAnnotationDemo1(
                "XYBoxAnnotationDemo1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
