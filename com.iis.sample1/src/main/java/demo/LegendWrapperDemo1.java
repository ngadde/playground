/* -----------------------
 * LegendWrapperDemo1.java
 * -----------------------
 * (C) Copyright 2005, 2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.LabelBlock;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to use a wrapper with
 * a {@link LegendTitle}.
 */
public class LegendWrapperDemo1 extends ApplicationFrame {

    /**
     * Default constructor.
     *
     * @param title  the frame title.
     */
    public LegendWrapperDemo1(String title) {
        super(title);
        setContentPane(createDemoPanel());
    }

    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("One", new Double(43.2));
        dataset.setValue("Two", new Double(10.0));
        dataset.setValue("Three", new Double(27.5));
        dataset.setValue("Four", new Double(17.5));
        dataset.setValue("Five", new Double(11.0));
        dataset.setValue("Six", new Double(19.4));
        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return a chart.
     */
    private static JFreeChart createChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart(
            "Legend Wrapper Demo 1",  // chart title
            dataset,             // data
            false,               // add legend later
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(true);
        plot.setLabelGap(0.02);

        LegendTitle legend = new LegendTitle(chart.getPlot());

        BlockContainer wrapper = new BlockContainer(new BorderArrangement());
        wrapper.setFrame(new BlockBorder(1.0, 1.0, 1.0, 1.0));

        LabelBlock title = new LabelBlock("Legend Items:",
                new Font("SansSerif", Font.BOLD, 12));
        title.setPadding(5, 5, 5, 5);
        wrapper.add(title, RectangleEdge.TOP);

        LabelBlock subtitle = new LabelBlock("Source: http://www.jfree.org");

        subtitle.setPadding(8, 20, 2, 5);
        wrapper.add(subtitle, RectangleEdge.BOTTOM);

        // *** this is important - you need to add the item container to
        // the wrapper, otherwise the legend items won't be displayed when
        // the wrapper is drawn... ***
        BlockContainer items = legend.getItemContainer();
        items.setPadding(2, 10, 5, 2);
        wrapper.add(items);
        legend.setWrapper(wrapper);

        legend.setPosition(RectangleEdge.RIGHT);
        legend.setHorizontalAlignment(HorizontalAlignment.LEFT);
        chart.addSubtitle(legend);

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
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        LegendWrapperDemo1 demo = new LegendWrapperDemo1(
                "JFreeChart: LegendWrapperDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
