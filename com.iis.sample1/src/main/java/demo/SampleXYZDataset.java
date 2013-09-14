/* ---------------------
 * SampleXYZDataset.java
 * ---------------------
 * (C) Copyright 2003-2005, by Object Refinery Limited.
 *
 */

package demo;

import org.jfree.data.xy.AbstractXYZDataset;
import org.jfree.data.xy.XYZDataset;

/**
 * A quick-and-dirty implementation of the {@link XYZDataset interface}.  
 * Hard-coded and not useful beyond the demo.
 */
public class SampleXYZDataset extends AbstractXYZDataset implements XYZDataset {

    /** The x values. */
    private double[] xVal = {2.1, 2.375625, 2.375625, 2.232928726, 2.232928726, 
        1.860415253, 1.840842668, 1.905415253, 2.336029412, 3.8};

    /** The y values. */
    private double[] yVal = {14.168, 11.156, 10.089, 8.884, 8.719, 8.466, 5.489,
                             4.107, 4.101, 25};

    /** The z values. */
    private double[] zVal = {2.45, 2.791285714, 2.791285714, 2.2125, 2.2125, 
                             2.22, 2.1, 2.22, 1.64875, 4};

    /**
     * Returns the number of series in the dataset.
     *
     * @return The series count.
     */
    public int getSeriesCount() {
        return 1;
    }

    /**
     * Returns the key for a series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The key for the series.
     */
    public Comparable getSeriesKey(int series) {
        return "Series 1";
    }

    /**
     * Returns the number of items in a series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The number of items within the series.
     */
    public int getItemCount(int series) {
        return this.xVal.length;
    }

    /**
     * Returns the x-value for an item within a series.
     * <P>
     * The implementation is responsible for ensuring that the x-values are
     * presented in ascending order.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The x-value.
     */
    public Number getX(int series, int item) {
        return new Double(this.xVal[item]);
    }

    /**
     * Returns the y-value for an item within a series.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The y-value.
     */
    public Number getY(int series, int item) {
        return new Double(this.yVal[item]);
    }

    /**
     * Returns the z-value for the specified series and item.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public Number getZ(int series, int item) {
        return new Double(this.zVal[item]);
    }
    
}
