/* --------------------
 * JDBCXYChartDemo.java
 * --------------------
 * (C) Copyright 2002-2004, by Object Refinery Limited.
 *
 */

package demo;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.jdbc.JDBCXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * An XY chart that obtains data from a database via JDBC.
 * <P>
 * To run this demo, you need to have a database that you can access via JDBC, and you need to
 * create a table called 'XYData1' and populate it with sample data.  The table I have used
 * looks like this:
 *
 * X          | SERIES1 | SERIES2 | SERIES3
 * -----------+---------+---------+--------
 * 1-Aug-2002 |    54.3 |    32.1 |    53.4
 * 2-Aug-2002 |    43.4 |    54.3 |    75.2
 * 3-Aug-2002 |    39.6 |    55.9 |    37.1
 * 4-Aug-2002 |    35.4 |    55.2 |    27.5
 * 5-Aug-2002 |    33.9 |    49.8 |    22.3
 * 6-Aug-2002 |    35.2 |    48.4 |    17.7
 * 7-Aug-2002 |    38.9 |    49.7 |    15.3
 * 8-Aug-2002 |    36.3 |    44.4 |    12.1
 * 9-Aug-2002 |    31.0 |    46.3 |    11.0
 *
 * ...but you can use any data you like, as long as the SQL query you use returns at least
 * two columns, the first containing x-values, and the remaining columns containing y-values
 * for each series.
 */
public class JDBCXYChartDemo extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public JDBCXYChartDemo(String title) {

        super(title);

        // read the data from the database...
        XYDataset data = readData();

        // create the chart...
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "JDBC XY Chart Demo", // chart title
            "Date",
            "Value",
            data,                 // data
            true,                 // include legend
            true,
            false
        );

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.yellow);

        // add the chart to a panel...
        ChartPanel chartPanel = new ChartPanel(chart);
        setContentPane(chartPanel);

    }

    /**
     * Reads the data from a table called 'XYData1' in the 'JFREECHARTDB' database.
     * <P>
     * You need to create this database and table before running the demo.  In the example
     * I have used the username 'jfreechart' and the password 'password' to access the
     * database.  Change these values to match your configuration.
     *
     * @return a dataset.
     */
    private XYDataset readData() {

        JDBCXYDataset data = null;

        String url = "jdbc:postgresql://nomad/jfreechartdb";
        Connection con;

        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }

        try {
            con = DriverManager.getConnection(url, "jfreechart", "password");

            data = new JDBCXYDataset(con);
            String sql = "SELECT * FROM XYDATA1;";
            data.executeQuery(sql);
            con.close();
        }

        catch (SQLException e) {
            System.err.print("SQLException: ");
            System.err.println(e.getMessage());
        }

        catch (Exception e) {
            System.err.print("Exception: ");
            System.err.println(e.getMessage());
        }

        return data;

    }

    /**
     * Starting point for the demo...
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        JDBCXYChartDemo demo = new JDBCXYChartDemo("JDBC XY Chart Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
