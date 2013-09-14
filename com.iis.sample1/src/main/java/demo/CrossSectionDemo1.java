/* ----------------------
 * CrossSectionDemo1.java
 * ----------------------
 * (C) Copyright 2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYDataImageAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.panel.CrosshairOverlay;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.chart.renderer.PaintScale;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultHeatMapDataset;
import org.jfree.data.general.HeatMapDataset;
import org.jfree.data.general.HeatMapUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;

/**
 * A test for the heat map annotations.
 */
public class CrossSectionDemo1 extends ApplicationFrame {

    static class MyDemoPanel extends DemoPanel implements ChangeListener,
            ChartChangeListener {
        
        private HeatMapDataset dataset;

        private JFreeChart mainChart;

        private JFreeChart subchart1;

        private JFreeChart subchart2;

        private JSlider slider1;

        private JSlider slider2;

        private Crosshair crosshair1;

        private Crosshair crosshair2;

        private Range lastXRange;

        private Range lastYRange;

        public MyDemoPanel() {
            super(new BorderLayout());
            ChartPanel chartPanel = (ChartPanel) createMainPanel();
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
            CrosshairOverlay overlay = new CrosshairOverlay();
            this.crosshair1 = new Crosshair(0.0);
            this.crosshair1.setPaint(Color.red);
            this.crosshair2 = new Crosshair(0.0);
            this.crosshair2.setPaint(Color.blue);
            overlay.addDomainCrosshair(this.crosshair1);
            overlay.addRangeCrosshair(this.crosshair2);
            chartPanel.addOverlay(overlay);
            this.crosshair1.setLabelVisible(true);
            this.crosshair1.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
            this.crosshair1.setLabelBackgroundPaint(new Color(255, 255, 0, 100));
            this.crosshair2.setLabelVisible(true);
            this.crosshair2.setLabelBackgroundPaint(new Color(255, 255, 0, 100));

            add(chartPanel);

            JPanel slider1Panel = new JPanel(new BorderLayout());

            XYSeriesCollection dataset1 = new XYSeriesCollection();
            this.subchart1 = ChartFactory.createXYLineChart("Cross-section A", "Y",
                    "Z", dataset1, PlotOrientation.HORIZONTAL, false, false, false);
            XYPlot plot1 = (XYPlot) subchart1.getPlot();
            plot1.getDomainAxis().setLowerMargin(0.0);
            plot1.getDomainAxis().setUpperMargin(0.0);
            plot1.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

            ChartPanel subchart1Panel = new ChartPanel(subchart1);
            subchart1Panel.setMinimumDrawWidth(0);
            subchart1Panel.setMinimumDrawHeight(0);

            subchart1Panel.setPreferredSize(new Dimension(200, 150));
            this.slider1 = new JSlider(-250, 250, 0);
            this.slider1.addChangeListener(this);
            this.slider1.setOrientation(JSlider.VERTICAL);

            slider1Panel.add(subchart1Panel);
            slider1Panel.add(slider1, BorderLayout.WEST);

            JPanel slider2Panel = new JPanel(new BorderLayout());
            XYSeriesCollection dataset2 = new XYSeriesCollection();
            this.subchart2 = ChartFactory.createXYLineChart("Cross-section B", "X",
                    "Z", dataset2, PlotOrientation.VERTICAL, false, false, false);
            XYPlot plot2 = (XYPlot) subchart2.getPlot();
            plot2.getDomainAxis().setLowerMargin(0.0);
            plot2.getDomainAxis().setUpperMargin(0.0);
            plot2.getRenderer().setSeriesPaint(0, Color.blue);

            ChartPanel subchart2Panel = new ChartPanel(subchart2);
            subchart2Panel.setMinimumDrawWidth(0);
            subchart2Panel.setMinimumDrawHeight(0);
            subchart2Panel.setPreferredSize(new Dimension(200, 150));

            JPanel blank = new JPanel();
            blank.setPreferredSize(new Dimension(200, 10));
            slider2Panel.add(blank, BorderLayout.EAST);

            this.slider2 = new JSlider(-250, 250, 0);
            this.slider2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 200));
            this.slider2.addChangeListener(this);
            slider2Panel.add(subchart2Panel);
            slider2Panel.add(slider2, BorderLayout.NORTH);
            add(slider1Panel, BorderLayout.EAST);
            add(slider2Panel, BorderLayout.SOUTH);
            this.mainChart.setNotify(true);

        }

        /**
         * Creates a panel for the demo (used by SuperDemo.java).
         *
         * @return A panel.
         */
        public JPanel createMainPanel() {
            this.mainChart = createChart(new XYSeriesCollection());
            this.mainChart.addChangeListener(this);
            ChartPanel panel = new ChartPanel(this.mainChart);
            panel.setFillZoomRectangle(true);
            panel.setMouseWheelEnabled(true);
            return panel;
        }

        public void stateChanged(ChangeEvent e) {
            if (e.getSource() == this.slider1) {
                this.crosshair2.setValue(this.slider1.getValue());
                int xIndex = this.slider1.getValue() - this.slider1.getMinimum();
                XYDataset d = HeatMapUtilities.extractColumnFromHeatMapDataset(
                        this.dataset, xIndex, "Y1");
                this.subchart2.getXYPlot().setDataset(d);

            }
            else if (e.getSource() == this.slider2) {
                this.crosshair1.setValue(this.slider2.getValue());
                int xIndex = this.slider2.getValue() - this.slider2.getMinimum();
                XYDataset d = HeatMapUtilities.extractRowFromHeatMapDataset(
                        this.dataset, xIndex, "Y2");
                this.subchart1.getXYPlot().setDataset(d);
            }
        }

        /**
         * See if the axis ranges have changed in the main chart and, if so,
         * update the subchards.
         *
         * @param event
         */
        public void chartChanged(ChartChangeEvent event) {
            XYPlot plot = (XYPlot) this.mainChart.getPlot();
            if (!plot.getDomainAxis().getRange().equals(this.lastXRange)) {
                this.lastXRange = plot.getDomainAxis().getRange();
                XYPlot plot2 = (XYPlot) this.subchart2.getPlot();
                plot2.getDomainAxis().setRange(this.lastXRange);
            }
            if (!plot.getRangeAxis().getRange().equals(this.lastYRange)) {
                this.lastYRange = plot.getRangeAxis().getRange();
                XYPlot plot1 = (XYPlot) this.subchart1.getPlot();
                plot1.getDomainAxis().setRange(this.lastYRange);
            }
        }

        /**
         * Creates a chart.
         *
         * @param dataset  a dataset.
         *
         * @return A chart.
         */
        private JFreeChart createChart(XYDataset dataset) {

            JFreeChart chart = ChartFactory.createScatterPlot("CrossSectionDemo1",
                    "X", "Y", dataset, PlotOrientation.VERTICAL, true, false,
                    false);

            this.dataset = createMapDataset();
            PaintScale ps = new GrayPaintScale(-1.0, 1.0, 128);
            BufferedImage image = HeatMapUtilities.createHeatMapImage(this.dataset,
                    ps);
            XYDataImageAnnotation ann = new XYDataImageAnnotation(image, -250.5,
                    -250.5, 501.0, 501.0, true);
            XYPlot plot = (XYPlot) chart.getPlot();
            plot.setDomainPannable(true);
            plot.setRangePannable(true);
            plot.getRenderer().addAnnotation(ann, Layer.BACKGROUND);
            NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
            xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            xAxis.setLowerMargin(0.0);
            xAxis.setUpperMargin(0.0);
            NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
            yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            yAxis.setLowerMargin(0.0);
            yAxis.setUpperMargin(0.0);
            return chart;
        }
    }

    /**
     * A demonstration application showing how to create a simple time series
     * chart.  This example uses monthly data.
     *
     * @param title  the frame title.
     */
    public CrossSectionDemo1(String title) {
        super(title);
        JPanel content = createDemoPanel();
        setContentPane(content);
    }

    private static HeatMapDataset createMapDataset() {
        DefaultHeatMapDataset d = new DefaultHeatMapDataset(501, 501, -250,
                250, -250, 250);
        for (int i = 0; i < 501; i++) {
            for (int j = 0; j < 501; j++) {
                d.setZValue(i, j, Math.sin(Math.sqrt(i * j) / 10));
            }
        }
        return d;
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        CrossSectionDemo1 demo = new CrossSectionDemo1("JFreeChart: CrossSectionDemo1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }


}
