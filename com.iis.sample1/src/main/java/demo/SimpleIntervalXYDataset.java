/* ----------------------------
 * SimpleIntervalXYDataset.java
 * ----------------------------
 * (C) Copyright 2002-2005, by Object Refinery Limited.
 *
 */

package demo;

import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.xy.AbstractIntervalXYDataset;
import org.jfree.data.xy.IntervalXYDataset;

/**
 * A quick and dirty sample dataset.
 */
public class SimpleIntervalXYDataset extends AbstractIntervalXYDataset 
                                     implements IntervalXYDataset {

    /** The start values. */
    private Double[] xStart = new Double[3];
    
    /** The end values. */
    private Double[] xEnd = new Double[3];

    /** The y values. */
    private Double[] yValues = new Double[3];

    /**
     * Creates a new dataset.
     */
    public SimpleIntervalXYDataset() {

        this.xStart[0] = new Double(0.0);
        this.xStart[1] = new Double(2.0);
        this.xStart[2] = new Double(3.5);

        this.xEnd[0] = new Double(2.0);
        this.xEnd[1] = new Double(3.5);
        this.xEnd[2] = new Double(4.0);

        this.yValues[0] = new Double(3.0);
        this.yValues[1] = new Double(4.5);
        this.yValues[2] = new Double(2.5);
    }

    /**
     * Returns the number of series in the dataset.
     *
     * @return the number of series in the dataset.
     */
    public int getSeriesCount() {
        return 1;
    }

    /**
     * Returns the key for a series.
     *
     * @param series the series (zero-based index).
     *
     * @return The series key.
     */
    public Comparable getSeriesKey(int series) {
        return "Series 1";
    }

    /**
     * Returns the number of items in a series.
     *
     * @param series the series (zero-based index).
     *
     * @return the number of items within a series.
     */
    public int getItemCount(int series) {
        return 3;
    }

    /**
     * Returns the x-value for an item within a series.
     * <P>
     * The implementation is responsible for ensuring that the x-values are presented in ascending
     * order.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return  the x-value for an item within a series.
     */
    public Number getX(int series, int item) {
        return this.xStart[item];
    }

    /**
     * Returns the y-value for an item within a series.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return the y-value for an item within a series.
     */
    public Number getY(int series, int item) {
        return this.yValues[item];
    }

    /**
     * Returns the starting X value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item within a series (zero-based index).
     *
     * @return The value.
     */
    public Number getStartX(int series, int item) {
        return this.xStart[item];
    }

    /**
     * Returns the ending X value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item within a series (zero-based index).
     *
     * @return the end x value.
     */
    public Number getEndX(int series, int item) {
        return this.xEnd[item];
    }

    /**
     * Returns the starting Y value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item within a series (zero-based index).
     *
     * @return The value.
     */
    public Number getStartY(int series, int item) {
        return this.yValues[item];
    }

    /**
     * Returns the ending Y value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item within a series (zero-based index).
     *
     * @return The value.
     */
    public Number getEndY(int series, int item) {
        return this.yValues[item];
    }

    /**
     * Registers an object for notification of changes to the dataset.
     *
     * @param listener  the object to register.
     */
    public void addChangeListener(DatasetChangeListener listener) {
        // ignored
    }

    /**
     * Deregisters an object for notification of changes to the dataset.
     *
     * @param listener  the object to deregister.
     */
    public void removeChangeListener(DatasetChangeListener listener) {
        // ignored
    }

}
