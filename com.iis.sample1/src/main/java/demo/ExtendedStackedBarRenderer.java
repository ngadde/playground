/* -------------------------------
 * ExtendedStackedBarRenderer.java
 * -------------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRendererState;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

/**
 * An extension of the {@link StackedBarRenderer} that can draw positive and
 * negative totals at the top and bottom of the stacked bars.
 */
public class ExtendedStackedBarRenderer extends StackedBarRenderer {

    /** Show positive label? */
    private boolean showPositiveTotal = true;

    /** Show negative label? */
    private boolean showNegativeTotal = true;

    /** Font for labels. */
    private Font totalLabelFont = new Font("SansSerif", Font.BOLD, 12);

    /** Formatter for total. */
    private NumberFormat totalFormatter;

    /**
     * Creates a new renderer.
     */
    public ExtendedStackedBarRenderer() {
        super();
        this.totalFormatter = NumberFormat.getInstance();
    }

    /**
     * Returns the total formatter.
     *
     * @return the total formatter (never <code>null</code>).
     */
    public NumberFormat getTotalFormatter() {
        return this.totalFormatter;
    }

    /**
     * Sets the total formatter.
     *
     * @param format  the formatter (<code>null</code> not permitted).
     */
    public void setTotalFormatter(NumberFormat format) {
        if (format == null) {
            throw new IllegalArgumentException("Null format not permitted.");
        }
        this.totalFormatter = format;
    }

    /**
     * Draws a stacked bar for a specific item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the plot area.
     * @param plot  the plot.
     * @param domainAxis  the domain (category) axis.
     * @param rangeAxis  the range (value) axis.
     * @param dataset  the data.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2,
                         CategoryItemRendererState state,
                         Rectangle2D dataArea,
                         CategoryPlot plot,
                         CategoryAxis domainAxis,
                         ValueAxis rangeAxis,
                         CategoryDataset dataset,
                         int row,
                         int column,
                         int pass) {

        // nothing is drawn for null values...
        Number dataValue = dataset.getValue(row, column);
        if (dataValue == null) {
            return;
        }

        double value = dataValue.doubleValue();

        PlotOrientation orientation = plot.getOrientation();
        double barW0 = domainAxis.getCategoryMiddle(column, getColumnCount(),
                dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0;

        double positiveBase = 0.0;
        double negativeBase = 0.0;

        for (int i = 0; i < row; i++) {
            Number v = dataset.getValue(i, column);
            if (v != null) {
                double d = v.doubleValue();
                if (d > 0) {
                    positiveBase = positiveBase + d;
                }
                else {
                    negativeBase = negativeBase + d;
                }
            }
        }

        double translatedBase;
        double translatedValue;
        RectangleEdge location = plot.getRangeAxisEdge();
        if (value > 0.0) {
            translatedBase = rangeAxis.valueToJava2D(positiveBase, dataArea,
                    location);
            translatedValue = rangeAxis.valueToJava2D(positiveBase + value,
                    dataArea, location);
        }
        else {
            translatedBase = rangeAxis.valueToJava2D(negativeBase, dataArea,
                    location);
            translatedValue = rangeAxis.valueToJava2D(negativeBase + value,
                    dataArea, location);
        }
        double barL0 = Math.min(translatedBase, translatedValue);
        double barLength = Math.max(Math.abs(translatedValue - translatedBase),
                getMinimumBarLength());

        Rectangle2D bar = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            bar = new Rectangle2D.Double(barL0, barW0, barLength,
                    state.getBarWidth());
        }
        else {
            bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(),
                    barLength);
        }
        Paint seriesPaint = getItemPaint(row, column);
        g2.setPaint(seriesPaint);
        g2.fill(bar);
        if (isDrawBarOutline() && state.getBarWidth()
                > BAR_OUTLINE_WIDTH_THRESHOLD) {
            g2.setStroke(getItemStroke(row, column));
            g2.setPaint(getItemOutlinePaint(row, column));
            g2.draw(bar);
        }

        CategoryItemLabelGenerator generator
            = getItemLabelGenerator(row, column);
        if (generator != null && isItemLabelVisible(row, column)) {
            drawItemLabel(g2, dataset, row, column, plot, generator, bar,
                    (value < 0.0));
        }

        if (value > 0.0) {
            if (this.showPositiveTotal) {
                if (isLastPositiveItem(dataset, row, column)) {
                    g2.setPaint(Color.black);
                    g2.setFont(this.totalLabelFont);
                    double total = calculateSumOfPositiveValuesForCategory(
                            dataset, column);
                    float labelX = (float) bar.getCenterX();
                    float labelY = (float) bar.getMinY() - 4.0f;
                    TextAnchor labelAnchor = TextAnchor.BOTTOM_CENTER;
                    if (orientation == PlotOrientation.HORIZONTAL) {
                        labelX = (float) bar.getMaxX() + 4.0f;
                        labelY = (float) bar.getCenterY();
                        labelAnchor = TextAnchor.CENTER_LEFT;
                    }
                    TextUtilities.drawRotatedString(
                            this.totalFormatter.format(total), g2, labelX,
                            labelY, labelAnchor, 0.0, TextAnchor.CENTER);
                }
            }
        }
        else {
            if (this.showNegativeTotal) {
                if (isLastNegativeItem(dataset, row, column)) {
                    g2.setPaint(Color.black);
                    g2.setFont(this.totalLabelFont);
                    double total = calculateSumOfNegativeValuesForCategory(
                            dataset, column);
                    float labelX = (float) bar.getCenterX();
                    float labelY = (float) bar.getMaxY() + 4.0f;
                    TextAnchor labelAnchor = TextAnchor.TOP_CENTER;
                    if (orientation == PlotOrientation.HORIZONTAL) {
                        labelX = (float) bar.getMinX() - 4.0f;
                        labelY = (float) bar.getCenterY();
                        labelAnchor = TextAnchor.CENTER_RIGHT;
                    }
                    TextUtilities.drawRotatedString(
                            this.totalFormatter.format(total), g2, labelX,
                            labelY, labelAnchor, 0.0, TextAnchor.CENTER);
                }
            }
        }

        // collect entity and tool tip information...
        if (state.getInfo() != null) {
            EntityCollection entities = state.getEntityCollection();
            if (entities != null) {
                String tip = null;
                CategoryToolTipGenerator tipster = getToolTipGenerator(
                        row, column);
                if (tipster != null) {
                    tip = tipster.generateToolTip(dataset, row, column);
                }
                String url = null;
                if (getItemURLGenerator(row, column) != null) {
                    url = getItemURLGenerator(row, column).generateURL(
                            dataset, row, column);
                }
                CategoryItemEntity entity = new CategoryItemEntity(
                        bar, tip, url, dataset, dataset.getRowKey(row),
                        dataset.getColumnKey(column));
                entities.add(entity);
            }
        }

    }

    /**
     * Returns true if the specified item is the last positive value for that
     * category.
     *
     * @param dataset  the dataset.
     * @param row  the row (series).
     * @param column  the column (category).
     *
     * @return a boolean.
     */
    private boolean isLastPositiveItem(CategoryDataset dataset,
                                       int row,
                                       int column) {
        boolean result = true;
        Number dataValue = dataset.getValue(row, column);
        if (dataValue == null) {
            return false;  // value is null
        }
        for (int r = row + 1; r < dataset.getRowCount(); r++) {
            dataValue = dataset.getValue(r, column);
            if (dataValue != null) {
                result = result && (dataValue.doubleValue() <= 0.0);
            }
        }
        return result;
    }

    /**
     * Returns true if the specified item is the last negative value for that
     * category.
     *
     * @param dataset  the dataset.
     * @param row  the row (series).
     * @param column  the column (category).
     *
     * @return a boolean.
     */
    private boolean isLastNegativeItem(CategoryDataset dataset,
                                       int row,
                                       int column) {
        boolean result = true;
        Number dataValue = dataset.getValue(row, column);
        if (dataValue == null) {
            return false;  // value is null
        }
        for (int r = row + 1; r < dataset.getRowCount(); r++) {
            dataValue = dataset.getValue(r, column);
            if (dataValue != null) {
                result = result && (dataValue.doubleValue() >= 0.0);
            }
        }
        return result;
    }

    /**
     * Calculates the sum of the positive values within a category.
     *
     * @param dataset  the dataset.
     * @param column  the column (category).
     *
     * @return the sum of the positive values.
     */
    private double calculateSumOfPositiveValuesForCategory(
            CategoryDataset dataset, int column) {
        double result = 0.0;
        for (int r = 0; r < dataset.getRowCount(); r++) {
            Number dataValue = dataset.getValue(r, column);
            if (dataValue != null) {
                double v = dataValue.doubleValue();
                if (v > 0.0) {
                    result = result + v;
                }
            }
        }
        return result;
    }

    /**
     * Calculates the sum of the negative values within a category.
     *
     * @param dataset  the dataset.
     * @param column  the column (category).
     *
     * @return the sum of the negative values.
     */
    private double calculateSumOfNegativeValuesForCategory(
            CategoryDataset dataset, int column) {
        double result = 0.0;
        for (int r = 0; r < dataset.getRowCount(); r++) {
            Number dataValue = dataset.getValue(r, column);
            if (dataValue != null) {
                double v = dataValue.doubleValue();
                if (v < 0.0) {
                    result = result + v;
                }
            }
        }
        return result;
    }

}
