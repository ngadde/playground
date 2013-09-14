/* ------------------------
 * CombinedXYPlotDemo1.java
 * ------------------------
 * (C) Copyright 2007-2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

/**
 * A demonstration application showing a {@link CombinedDomainXYPlot} with
 * two subplots.
 */
public class CombinedXYPlotDemo1 extends ApplicationFrame {

    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public CombinedXYPlotDemo1(String title) {
        super(title);
        JPanel panel = createDemoPanel();
        panel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(panel);
    }

    /**
     * Creates an overlaid chart.
     *
     * @return The chart.
     */
    private static JFreeChart createCombinedChart() {

        // create plot ...
        IntervalXYDataset data1 = createDataset1();
        XYItemRenderer renderer1 = new XYLineAndShapeRenderer(true, false);
        renderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));
        renderer1.setSeriesStroke(0, new BasicStroke(4.0f,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        renderer1.setSeriesPaint(0, Color.blue);

        DateAxis domainAxis = new DateAxis("Year");
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.02);
        ValueAxis rangeAxis = new NumberAxis("$billion");
        XYPlot plot1 = new XYPlot(data1, null, rangeAxis, renderer1);
        plot1.setBackgroundPaint(Color.lightGray);
        plot1.setDomainGridlinePaint(Color.white);
        plot1.setRangeGridlinePaint(Color.white);

        // add a second dataset and renderer...
        IntervalXYDataset data2 = createDataset2();
        XYBarRenderer renderer2 = new XYBarRenderer() {
            public Paint getItemPaint(int series, int item) {
                XYDataset dataset = getPlot().getDataset();
                if (dataset.getYValue(series, item) >= 0.0) {
                    return Color.red;
                }
                else {
                    return Color.green;
                }
            }
        };
        renderer2.setSeriesPaint(0, Color.red);
        renderer2.setDrawBarOutline(false);
        renderer2.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));

        XYPlot plot2 = new XYPlot(data2, null, new NumberAxis("$billion"),
                renderer2);
        plot2.setBackgroundPaint(Color.lightGray);
        plot2.setDomainGridlinePaint(Color.white);
        plot2.setRangeGridlinePaint(Color.white);

        CombinedDomainXYPlot cplot = new CombinedDomainXYPlot(domainAxis);
        cplot.add(plot1, 3);
        cplot.add(plot2, 2);
        cplot.setGap(8.0);
        cplot.setDomainGridlinePaint(Color.white);
        cplot.setDomainGridlinesVisible(true);
        cplot.setDomainPannable(true);

        // return a new chart containing the overlaid plot...
        JFreeChart chart = new JFreeChart("United States Public Debt",
                JFreeChart.DEFAULT_TITLE_FONT, cplot, false);
        TextTitle source = new TextTitle(
                "Source: http://www.publicdebt.treas.gov/opd/opdhisms.htm",
                new Font("Dialog", Font.PLAIN, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);
        LegendTitle legend = new LegendTitle(cplot);
        chart.addSubtitle(legend);
        ChartUtilities.applyCurrentTheme(chart);
        renderer2.setBarPainter(new StandardXYBarPainter());
        renderer2.setShadowVisible(false);
        return chart;
    }

    /**
     * Creates a sample dataset.  You wouldn't normally hard-code the
     * population of a dataset in this way (it would be better to read the
     * values from a file or a database query), but for a self-contained demo
     * this is the least complicated solution.
     *
     * @return The dataset.
     */
    private static IntervalXYDataset createDataset1() {

        // create dataset 1...
        TimeSeries series1 = new TimeSeries("Public Debt Outstanding");
        series1.add(new Month(1, 1990), 2974.584);
        series1.add(new Month(2, 1990), 2994.354);
        series1.add(new Month(3, 1990), 3051.956);
        series1.add(new Month(4, 1990), 3061.013);
        series1.add(new Month(5, 1990), 3095.172);
        series1.add(new Month(6, 1990), 3143.754);
        series1.add(new Month(7, 1990), 3168.772);
        series1.add(new Month(8, 1990), 3211.691);
        series1.add(new Month(9, 1990), 3233.313);
        series1.add(new Month(10, 1990), 3274.950);
        series1.add(new Month(11, 1990), 3330.685);
        series1.add(new Month(12, 1990), 3364.820);
        series1.add(new Month(1, 1991), 3411.409);
        series1.add(new Month(2, 1991), 3458.637);
        series1.add(new Month(3, 1991), 3441.367);
        series1.add(new Month(4, 1991), 3445.059);
        series1.add(new Month(5, 1991), 3497.232);
        series1.add(new Month(6, 1991), 3537.988);
        series1.add(new Month(7, 1991), 3576.827);
        series1.add(new Month(8, 1991), 3614.399);
        series1.add(new Month(9, 1991), 3665.303);
        series1.add(new Month(10, 1991), 3717.106);
        series1.add(new Month(11, 1991), 3747.363);
        series1.add(new Month(12, 1991), 3801.698);
        series1.add(new Month(1, 1992), 3809.334);
        series1.add(new Month(2, 1992), 3829.059);
        series1.add(new Month(3, 1992), 3881.288);
        series1.add(new Month(4, 1992), 3891.974);
        series1.add(new Month(5, 1992), 3934.435);
        series1.add(new Month(6, 1992), 3984.656);
        series1.add(new Month(7, 1992), 4010.612);
        series1.add(new Month(8, 1992), 4048.938);
        series1.add(new Month(9, 1992), 4064.621);
        series1.add(new Month(10, 1992), 4067.329);
        series1.add(new Month(11, 1992), 4132.525);
        series1.add(new Month(12, 1992), 4177.009);
        series1.add(new Month(1, 1993), 4167.200);
        series1.add(new Month(2, 1993), 4197.004);
        series1.add(new Month(3, 1993), 4230.580);
        series1.add(new Month(4, 1993), 4254.084);
        series1.add(new Month(5, 1993), 4296.278);
        series1.add(new Month(6, 1993), 4351.950);
        series1.add(new Month(7, 1993), 4350.261);
        series1.add(new Month(8, 1993), 4403.313);
        series1.add(new Month(9, 1993), 4411.489);
        series1.add(new Month(10, 1993), 4422.511);
        series1.add(new Month(11, 1993), 4493.535);
        series1.add(new Month(12, 1993), 4535.687);
        series1.add(new Month(1, 1994), 4526.308);
        series1.add(new Month(2, 1994), 4559.541);
        series1.add(new Month(3, 1994), 4575.869);
        series1.add(new Month(4, 1994), 4568.704);
        series1.add(new Month(5, 1994), 4609.296);
        series1.add(new Month(6, 1994), 4645.802);
        series1.add(new Month(7, 1994), 4636.362);
        series1.add(new Month(8, 1994), 4691.991);
        series1.add(new Month(9, 1994), 4692.750);
        series1.add(new Month(10, 1994), 4734.167);
        series1.add(new Month(11, 1994), 4778.520);
        series1.add(new Month(12, 1994), 4800.150);
        series1.add(new Month(1, 1995), 4815.827);
        series1.add(new Month(2, 1995), 4854.298);
        series1.add(new Month(3, 1995), 4864.116);
        series1.add(new Month(4, 1995), 4852.327);
        series1.add(new Month(5, 1995), 4903.926);
        series1.add(new Month(6, 1995), 4951.372);
        series1.add(new Month(7, 1995), 4960.152);
        series1.add(new Month(8, 1995), 4970.756);
        series1.add(new Month(9, 1995), 4973.983);
        series1.add(new Month(10, 1995), 4985.262);
        series1.add(new Month(11, 1995), 4989.330);
        series1.add(new Month(12, 1995), 4988.665);
        series1.add(new Month(1, 1996), 4987.436);
        series1.add(new Month(2, 1996), 5017.041);
        series1.add(new Month(3, 1996), 5117.786);
        series1.add(new Month(4, 1996), 5102.049);
        series1.add(new Month(5, 1996), 5128.509);
        series1.add(new Month(6, 1996), 5161.076);
        series1.add(new Month(7, 1996), 5188.889);
        series1.add(new Month(8, 1996), 5208.303);
        series1.add(new Month(9, 1996), 5224.811);
        series1.add(new Month(10, 1996), 5247.320);
        series1.add(new Month(11, 1996), 5296.549);
        series1.add(new Month(12, 1996), 5323.172);
        series1.add(new Month(1, 1997), 5313.997);
        series1.add(new Month(2, 1997), 5349.937);
        series1.add(new Month(3, 1997), 5380.890);
        series1.add(new Month(4, 1997), 5353.971);
        series1.add(new Month(5, 1997), 5344.961);
        series1.add(new Month(6, 1997), 5376.151);
        series1.add(new Month(7, 1997), 5373.231);
        series1.add(new Month(8, 1997), 5404.420);
        series1.add(new Month(9, 1997), 5413.146);
        series1.add(new Month(10, 1997), 5427.225);
        series1.add(new Month(11, 1997), 5462.622);
        series1.add(new Month(12, 1997), 5502.388);
        series1.add(new Month(1, 1998), 5490.064);
        series1.add(new Month(2, 1998), 5520.668);
        series1.add(new Month(3, 1998), 5542.426);
        series1.add(new Month(4, 1998), 5499.895);
        series1.add(new Month(5, 1998), 5506.356);
        series1.add(new Month(6, 1998), 5547.935);
        series1.add(new Month(7, 1998), 5527.738);
        series1.add(new Month(8, 1998), 5564.553);
        series1.add(new Month(9, 1998), 5526.193);
        series1.add(new Month(10, 1998), 5559.255);
        series1.add(new Month(11, 1998), 5591.979);
        series1.add(new Month(12, 1998), 5614.217);
        series1.add(new Month(1, 1999), 5610.117);
        series1.add(new Month(2, 1999), 5621.946);
        series1.add(new Month(3, 1999), 5651.615);
        series1.add(new Month(4, 1999), 5585.840);
        series1.add(new Month(5, 1999), 5604.198);
        series1.add(new Month(6, 1999), 5638.780);
        series1.add(new Month(7, 1999), 5638.656);
        series1.add(new Month(8, 1999), 5672.386);
        series1.add(new Month(9, 1999), 5656.271);
        series1.add(new Month(10, 1999), 5679.727);
        series1.add(new Month(11, 1999), 5693.600);
        series1.add(new Month(12, 1999), 5776.091);
        series1.add(new Month(1, 2000), 5711.285);
        series1.add(new Month(2, 2000), 5735.333);
        series1.add(new Month(3, 2000), 5773.392);
        series1.add(new Month(4, 2000), 5685.108);
        series1.add(new Month(5, 2000), 5647.170);
        series1.add(new Month(6, 2000), 5685.938);
        series1.add(new Month(7, 2000), 5658.807);
        series1.add(new Month(8, 2000), 5677.822);
        series1.add(new Month(9, 2000), 5674.178);
        series1.add(new Month(10, 2000), 5657.328);
        series1.add(new Month(11, 2000), 5709.699);
        series1.add(new Month(12, 2000), 5662.216);
        series1.add(new Month(1, 2001), 5716.071);
        series1.add(new Month(2, 2001), 5735.859);
        series1.add(new Month(3, 2001), 5773.740);
        series1.add(new Month(4, 2001), 5661.348);
        series1.add(new Month(5, 2001), 5656.182);
        series1.add(new Month(6, 2001), 5726.815);
        series1.add(new Month(7, 2001), 5718.303);
        series1.add(new Month(8, 2001), 5769.876);
        series1.add(new Month(9, 2001), 5807.463);
        series1.add(new Month(10, 2001), 5815.983);
        series1.add(new Month(11, 2001), 5888.897);
        series1.add(new Month(12, 2001), 5943.439);
        series1.add(new Month(1, 2002), 5937.229);
        series1.add(new Month(2, 2002), 6003.453);
        series1.add(new Month(3, 2002), 6006.032);
        series1.add(new Month(4, 2002), 5984.677);
        series1.add(new Month(5, 2002), 6019.332);
        series1.add(new Month(6, 2002), 6126.469);
        series1.add(new Month(7, 2002), 6159.741);
        series1.add(new Month(8, 2002), 6210.482);
        series1.add(new Month(9, 2002), 6228.236);
        series1.add(new Month(10, 2002), 6282.528);
        series1.add(new Month(11, 2002), 6343.460);
        series1.add(new Month(12, 2002), 6405.707);
        series1.add(new Month(1, 2003), 6401.377);
        series1.add(new Month(2, 2003), 6445.790);
        series1.add(new Month(3, 2003), 6460.776);
        series1.add(new Month(4, 2003), 6460.381);
        series1.add(new Month(5, 2003), 6558.147);
        series1.add(new Month(6, 2003), 6670.121);
        series1.add(new Month(7, 2003), 6751.195);
        series1.add(new Month(8, 2003), 6790.041);
        series1.add(new Month(9, 2003), 6783.231);
        series1.add(new Month(10, 2003), 6872.676);
        series1.add(new Month(11, 2003), 6925.065);
        series1.add(new Month(12, 2003), 6997.964);
        series1.add(new Month(1, 2004), 7009.235);
        series1.add(new Month(2, 2004), 7091.943);
        series1.add(new Month(3, 2004), 7131.068);
        series1.add(new Month(4, 2004), 7133.789);
        series1.add(new Month(5, 2004), 7196.383);
        series1.add(new Month(6, 2004), 7274.335);
        series1.add(new Month(7, 2004), 7316.568);
        series1.add(new Month(8, 2004), 7350.950);
        series1.add(new Month(9, 2004), 7379.053);
        series1.add(new Month(10, 2004), 7429.677);
        series1.add(new Month(11, 2004), 7525.210);
        series1.add(new Month(12, 2004), 7596.144);
        series1.add(new Month(1, 2005), 7627.743);
        series1.add(new Month(2, 2005), 7713.138);
        series1.add(new Month(3, 2005), 7776.939);
        series1.add(new Month(4, 2005), 7764.537);
        series1.add(new Month(5, 2005), 7777.880);
        series1.add(new Month(6, 2005), 7836.496);
        series1.add(new Month(7, 2005), 7887.618);
        series1.add(new Month(8, 2005), 7926.933);
        series1.add(new Month(9, 2005), 7932.710);
        series1.add(new Month(10, 2005), 8027.123);
        series1.add(new Month(11, 2005), 8092.322);
        series1.add(new Month(12, 2005), 8170.414);
        series1.add(new Month(1, 2006), 8196.070);
        series1.add(new Month(2, 2006), 8269.886);
        series1.add(new Month(3, 2006), 8371.156);
        series1.add(new Month(4, 2006), 8355.718);
        series1.add(new Month(5, 2006), 8356.777);
        series1.add(new Month(6, 2006), 8420.042);
        series1.add(new Month(7, 2006), 8444.347);
        series1.add(new Month(8, 2006), 8515.034);
        series1.add(new Month(9, 2006), 8506.974);
        series1.add(new Month(10, 2006), 8584.329);
        series1.add(new Month(11, 2006), 8633.246);
        series1.add(new Month(12, 2006), 8680.224);
        series1.add(new Month(1, 2007), 8707.561);
        return new TimeSeriesCollection(series1);

    }

    /**
     * Creates a sample dataset.  You wouldn't normally hard-code the
     * population of a dataset in this way (it would be better to read the
     * values from a file or a database query), but for a self-contained demo
     * this is the least complicated solution.
     *
     * @return A sample dataset.
     */
    private static IntervalXYDataset createDataset2() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();

        TimeSeries series1 = new TimeSeries("Change from previous year");
        series1.add(new Month(1, 1990), 276.627);
        series1.add(new Month(2, 1990), 271.509);
        series1.add(new Month(3, 1990), 311.058);
        series1.add(new Month(4, 1990), 304.345);
        series1.add(new Month(5, 1990), 317.632);
        series1.add(new Month(6, 1990), 343.831);
        series1.add(new Month(7, 1990), 368.317);
        series1.add(new Month(8, 1990), 375.266);
        series1.add(new Month(9, 1990), 375.882);
        series1.add(new Month(10, 1990), 373.731);
        series1.add(new Month(11, 1990), 407.096);
        series1.add(new Month(12, 1990), 411.826);
        series1.add(new Month(1, 1991), 436.825);
        series1.add(new Month(2, 1991), 464.283);
        series1.add(new Month(3, 1991), 389.411);
        series1.add(new Month(4, 1991), 384.046);
        series1.add(new Month(5, 1991), 402.060);
        series1.add(new Month(6, 1991), 394.234);
        series1.add(new Month(7, 1991), 408.055);
        series1.add(new Month(8, 1991), 402.708);
        series1.add(new Month(9, 1991), 431.990);
        series1.add(new Month(10, 1991), 442.156);
        series1.add(new Month(11, 1991), 416.678);
        series1.add(new Month(12, 1991), 436.878);
        series1.add(new Month(1, 1992), 397.925);
        series1.add(new Month(2, 1992), 370.422);
        series1.add(new Month(3, 1992), 439.921);
        series1.add(new Month(4, 1992), 446.915);
        series1.add(new Month(5, 1992), 437.203);
        series1.add(new Month(6, 1992), 446.668);
        series1.add(new Month(7, 1992), 433.785);
        series1.add(new Month(8, 1992), 434.539);
        series1.add(new Month(9, 1992), 399.318);
        series1.add(new Month(10, 1992), 350.223);
        series1.add(new Month(11, 1992), 385.162);
        series1.add(new Month(12, 1992), 375.311);
        series1.add(new Month(1, 1993), 357.866);
        series1.add(new Month(2, 1993), 367.945);
        series1.add(new Month(3, 1993), 349.292);
        series1.add(new Month(4, 1993), 362.110);
        series1.add(new Month(5, 1993), 361.843);
        series1.add(new Month(6, 1993), 367.294);
        series1.add(new Month(7, 1993), 339.649);
        series1.add(new Month(8, 1993), 354.375);
        series1.add(new Month(9, 1993), 346.868);
        series1.add(new Month(10, 1993), 355.182);
        series1.add(new Month(11, 1993), 361.010);
        series1.add(new Month(12, 1993), 358.678);
        series1.add(new Month(1, 1994), 359.108);
        series1.add(new Month(2, 1994), 362.537);
        series1.add(new Month(3, 1994), 345.289);
        series1.add(new Month(4, 1994), 314.620);
        series1.add(new Month(5, 1994), 313.018);
        series1.add(new Month(6, 1994), 293.852);
        series1.add(new Month(7, 1994), 286.101);
        series1.add(new Month(8, 1994), 288.678);
        series1.add(new Month(9, 1994), 281.261);
        series1.add(new Month(10, 1994), 311.656);
        series1.add(new Month(11, 1994), 284.985);
        series1.add(new Month(12, 1994), 264.463);
        series1.add(new Month(1, 1995), 289.519);
        series1.add(new Month(2, 1995), 294.757);
        series1.add(new Month(3, 1995), 288.247);
        series1.add(new Month(4, 1995), 283.623);
        series1.add(new Month(5, 1995), 294.630);
        series1.add(new Month(6, 1995), 305.570);
        series1.add(new Month(7, 1995), 323.790);
        series1.add(new Month(8, 1995), 278.765);
        series1.add(new Month(9, 1995), 281.233);
        series1.add(new Month(10, 1995), 251.095);
        series1.add(new Month(11, 1995), 210.810);
        series1.add(new Month(12, 1995), 188.515);
        series1.add(new Month(1, 1996), 171.609);
        series1.add(new Month(2, 1996), 162.743);
        series1.add(new Month(3, 1996), 253.670);
        series1.add(new Month(4, 1996), 249.722);
        series1.add(new Month(5, 1996), 224.583);
        series1.add(new Month(6, 1996), 209.704);
        series1.add(new Month(7, 1996), 228.737);
        series1.add(new Month(8, 1996), 237.547);
        series1.add(new Month(9, 1996), 250.828);
        series1.add(new Month(10, 1996), 262.058);
        series1.add(new Month(11, 1996), 307.219);
        series1.add(new Month(12, 1996), 334.507);
        series1.add(new Month(1, 1997), 326.561);
        series1.add(new Month(2, 1997), 332.896);
        series1.add(new Month(3, 1997), 263.104);
        series1.add(new Month(4, 1997), 251.922);
        series1.add(new Month(5, 1997), 216.452);
        series1.add(new Month(6, 1997), 215.075);
        series1.add(new Month(7, 1997), 184.342);
        series1.add(new Month(8, 1997), 196.117);
        series1.add(new Month(9, 1997), 188.335);
        series1.add(new Month(10, 1997), 179.905);
        series1.add(new Month(11, 1997), 166.073);
        series1.add(new Month(12, 1997), 179.216);
        series1.add(new Month(1, 1998), 176.067);
        series1.add(new Month(2, 1998), 170.731);
        series1.add(new Month(3, 1998), 161.536);
        series1.add(new Month(4, 1998), 145.924);
        series1.add(new Month(5, 1998), 161.395);
        series1.add(new Month(6, 1998), 171.784);
        series1.add(new Month(7, 1998), 154.507);
        series1.add(new Month(8, 1998), 160.133);
        series1.add(new Month(9, 1998), 113.047);
        series1.add(new Month(10, 1998), 132.030);
        series1.add(new Month(11, 1998), 129.357);
        series1.add(new Month(12, 1998), 111.829);
        series1.add(new Month(1, 1999), 120.053);
        series1.add(new Month(2, 1999), 101.278);
        series1.add(new Month(3, 1999), 109.189);
        series1.add(new Month(4, 1999), 85.945);
        series1.add(new Month(5, 1999), 97.842);
        series1.add(new Month(6, 1999), 90.845);
        series1.add(new Month(7, 1999), 110.918);
        series1.add(new Month(8, 1999), 107.833);
        series1.add(new Month(9, 1999), 130.078);
        series1.add(new Month(10, 1999), 120.472);
        series1.add(new Month(11, 1999), 101.621);
        series1.add(new Month(12, 1999), 161.874);
        series1.add(new Month(1, 2000), 101.168);
        series1.add(new Month(2, 2000), 113.387);
        series1.add(new Month(3, 2000), 121.777);
        series1.add(new Month(4, 2000), 99.268);
        series1.add(new Month(5, 2000), 42.972);
        series1.add(new Month(6, 2000), 47.158);
        series1.add(new Month(7, 2000), 20.151);
        series1.add(new Month(8, 2000), 5.436);
        series1.add(new Month(9, 2000), 17.907);
        series1.add(new Month(10, 2000), -22.399);
        series1.add(new Month(11, 2000), 16.099);
        series1.add(new Month(12, 2000), -113.875);
        series1.add(new Month(1, 2001), 4.786);
        series1.add(new Month(2, 2001), .526);
        series1.add(new Month(3, 2001), .348);
        series1.add(new Month(4, 2001), -23.760);
        series1.add(new Month(5, 2001), 9.012);
        series1.add(new Month(6, 2001), 40.877);
        series1.add(new Month(7, 2001), 59.496);
        series1.add(new Month(8, 2001), 92.054);
        series1.add(new Month(9, 2001), 133.285);
        series1.add(new Month(10, 2001), 158.655);
        series1.add(new Month(11, 2001), 179.198);
        series1.add(new Month(12, 2001), 281.223);
        series1.add(new Month(1, 2002), 221.158);
        series1.add(new Month(2, 2002), 267.594);
        series1.add(new Month(3, 2002), 232.292);
        series1.add(new Month(4, 2002), 323.329);
        series1.add(new Month(5, 2002), 363.150);
        series1.add(new Month(6, 2002), 399.654);
        series1.add(new Month(7, 2002), 441.438);
        series1.add(new Month(8, 2002), 440.606);
        series1.add(new Month(9, 2002), 420.773);
        series1.add(new Month(10, 2002), 466.545);
        series1.add(new Month(11, 2002), 454.563);
        series1.add(new Month(12, 2002), 462.268);
        series1.add(new Month(1, 2003), 464.148);
        series1.add(new Month(2, 2003), 442.337);
        series1.add(new Month(3, 2003), 454.744);
        series1.add(new Month(4, 2003), 475.704);
        series1.add(new Month(5, 2003), 538.815);
        series1.add(new Month(6, 2003), 543.652);
        series1.add(new Month(7, 2003), 591.454);
        series1.add(new Month(8, 2003), 579.559);
        series1.add(new Month(9, 2003), 554.995);
        series1.add(new Month(10, 2003), 590.148);
        series1.add(new Month(11, 2003), 581.605);
        series1.add(new Month(12, 2003), 592.257);
        series1.add(new Month(1, 2004), 607.858);
        series1.add(new Month(2, 2004), 646.153);
        series1.add(new Month(3, 2004), 670.292);
        series1.add(new Month(4, 2004), 673.408);
        series1.add(new Month(5, 2004), 638.236);
        series1.add(new Month(6, 2004), 604.214);
        series1.add(new Month(7, 2004), 565.373);
        series1.add(new Month(8, 2004), 560.909);
        series1.add(new Month(9, 2004), 595.822);
        series1.add(new Month(10, 2004), 557.001);
        series1.add(new Month(11, 2004), 600.145);
        series1.add(new Month(12, 2004), 598.180);
        series1.add(new Month(1, 2005), 618.508);
        series1.add(new Month(2, 2005), 621.195);
        series1.add(new Month(3, 2005), 645.871);
        series1.add(new Month(4, 2005), 630.748);
        series1.add(new Month(5, 2005), 581.497);
        series1.add(new Month(6, 2005), 562.161);
        series1.add(new Month(7, 2005), 571.050);
        series1.add(new Month(8, 2005), 575.983);
        series1.add(new Month(9, 2005), 553.657);
        series1.add(new Month(10, 2005), 597.446);
        series1.add(new Month(11, 2005), 567.112);
        series1.add(new Month(12, 2005), 574.270);
        series1.add(new Month(1, 2006), 568.327);
        series1.add(new Month(2, 2006), 556.748);
        series1.add(new Month(3, 2006), 594.217);
        series1.add(new Month(4, 2006), 591.181);
        series1.add(new Month(5, 2006), 578.897);
        series1.add(new Month(6, 2006), 583.546);
        series1.add(new Month(7, 2006), 556.729);
        series1.add(new Month(8, 2006), 588.101);
        series1.add(new Month(9, 2006), 574.264);
        series1.add(new Month(10, 2006), 557.206);
        series1.add(new Month(11, 2006), 540.924);
        series1.add(new Month(12, 2006), 509.810);
        series1.add(new Month(1, 2007), 511.491);
        dataset.addSeries(series1);
        return dataset;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createCombinedChart();
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        CombinedXYPlotDemo1 demo = new CombinedXYPlotDemo1(
                "JFreeChart : CombinedXYPlotDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
