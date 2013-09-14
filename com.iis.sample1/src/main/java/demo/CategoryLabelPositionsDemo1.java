/* --------------------------------
 * CategoryLabelPositionsDemo1.java
 * --------------------------------
 * (C) Copyright 2006-2008, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A chart with controls to modify the category label rotation.  This
 * demo was used to track down a bug.
 */
public class CategoryLabelPositionsDemo1 extends ApplicationFrame {

    static JFreeChart chart;

    static JCheckBox invertCheckBox;

    static JRadioButton horizontalRadioButton;

    static JRadioButton verticalRadioButton;

    static JSlider slider;

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public CategoryLabelPositionsDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 350));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  a dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "CategoryLabelPositionsDemo1",
                "Category", "Value", dataset, PlotOrientation.VERTICAL, false,
                false, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis axis = plot.getDomainAxis();
        axis.setMaximumCategoryLabelLines(Integer.MAX_VALUE);
        axis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(
                        Math.PI / 4.0));
        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    public static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.0, "R1", "Category 1 (Long)");
        dataset.addValue(5.0, "R1", "Category 2 (Long)");
        dataset.addValue(3.0, "R1", "Category 3 (Long)");
        dataset.addValue(2.0, "R1", "Category 4 (Long)");
        dataset.addValue(9.0, "R1", "Category 5 (Long)");
        dataset.addValue(7.0, "R1", "Category 6 (Long)");
        dataset.addValue(6.0, "R1", "Category 7 (Long)");
        dataset.addValue(8.0, "R1", "Category 8 (Long)");
        return dataset;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        CategoryDataset dataset = createDataset();
        chart = createChart(dataset);
        DemoPanel panel = new DemoPanel(new BorderLayout());


        JPanel controlPanel = new JPanel(new BorderLayout());

        JPanel orientPanel = new JPanel();
        invertCheckBox = new JCheckBox("Invert Range Axis?");
        invertCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CategoryPlot plot = (CategoryPlot) chart.getPlot();
                plot.getRangeAxis().setInverted(invertCheckBox.isSelected());
            }
        });
        orientPanel.add(invertCheckBox);

        ButtonGroup group = new ButtonGroup();
        horizontalRadioButton = new JRadioButton("Horizontal", false);
        horizontalRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (horizontalRadioButton.isSelected()) {
                    CategoryPlot plot = (CategoryPlot) chart.getPlot();
                    plot.setOrientation(PlotOrientation.HORIZONTAL);
                }
            }
        });
        group.add(horizontalRadioButton);
        verticalRadioButton = new JRadioButton("Vertical", true);
        verticalRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (verticalRadioButton.isSelected()) {
                    CategoryPlot plot = (CategoryPlot) chart.getPlot();
                    plot.setOrientation(PlotOrientation.VERTICAL);
                }
            }
        });
        group.add(verticalRadioButton);
        orientPanel.add(horizontalRadioButton);
        orientPanel.add(verticalRadioButton);
        orientPanel.setBorder(new TitledBorder("Plot Settings: "));

        JPanel axisPanel = new JPanel(new BorderLayout());
        slider = new JSlider(0, 90, 45);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                CategoryPlot plot = (CategoryPlot) chart.getPlot();
                CategoryAxis axis = plot.getDomainAxis();
                axis.setCategoryLabelPositions(
                        CategoryLabelPositions.createUpRotationLabelPositions(
                                slider.getValue() * Math.PI / 180.0));
            }
        });
        axisPanel.add(slider);
        axisPanel.setBorder(new TitledBorder("Axis Label Rotation Angle:"));

        controlPanel.add(BorderLayout.NORTH, orientPanel);
        controlPanel.add(axisPanel);

        panel.add(new ChartPanel(chart));
        panel.addChart(chart);
        panel.add(BorderLayout.SOUTH, controlPanel);
        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        CategoryLabelPositionsDemo1 demo = new CategoryLabelPositionsDemo1(
                "JFreeChart: CategoryLabelPositionsDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
