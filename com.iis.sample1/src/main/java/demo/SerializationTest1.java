/* -----------------------
 * SerializationTest1.java
 * -----------------------
 * (C) Copyright 2004, 2009, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * Based on the DynamicDataDemo class, this demo serializes and deserializes
 * the chart before displaying it - the idea is that this confirms that the
 * serialization process returns a working chart.
 */
public class SerializationTest1 extends ApplicationFrame
        implements ActionListener {

    /** The time series data. */
    private TimeSeries series;

    /** The most recent value added. */
    private double lastValue = 100.0;

    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public SerializationTest1(String title) {

        super(title);
        this.series = new TimeSeries("Random Data");
        TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        JFreeChart chart = createChart(dataset);

        // SERIALIZE - DESERIALIZE for testing purposes
        JFreeChart deserializedChart = null;

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(chart);
            out.close();
            chart = null;
            dataset = null;
            this.series = null;
            System.gc();

            ObjectInput in = new ObjectInputStream(
                    new ByteArrayInputStream(buffer.toByteArray()));
            deserializedChart = (JFreeChart) in.readObject();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        XYPlot p = (XYPlot) deserializedChart.getPlot();
        TimeSeriesCollection c = (TimeSeriesCollection) p.getDataset();
        this.series = c.getSeries(0);
        // FINISHED TEST

        ChartPanel chartPanel = new ChartPanel(deserializedChart);
        JButton button = new JButton("Add New Data Item");
        button.setActionCommand("ADD_DATA");
        button.addActionListener(this);

        JPanel content = new JPanel(new BorderLayout());
        content.add(chartPanel);
        content.add(button, BorderLayout.SOUTH);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(content);

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Serialization Test 1",
            "Time",
            "Value",
            dataset,
            true,
            true,
            false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(60000.0);  // 60 seconds
        return chart;
    }

    /**
     * Handles a click on the button by adding new (random) data.
     *
     * @param e  the action event.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADD_DATA")) {
            double factor = 0.90 + 0.2 * Math.random();
            this.lastValue = this.lastValue * factor;
            Millisecond now = new Millisecond();
            System.out.println("Now = " + now.toString());
            this.series.add(new Millisecond(), this.lastValue);
        }
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        SerializationTest1 demo = new SerializationTest1("Serialization Test 1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}







