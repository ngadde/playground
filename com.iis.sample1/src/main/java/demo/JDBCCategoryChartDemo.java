/* --------------------------
 * JDBCCategoryChartDemo.java
 * --------------------------
 * (C) Copyright 2002-2004, by Object Refinery Limited..
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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A category chart that obtains data from a database via JDBC.
 * <P>
 * To run this demo, you need to have a database that you can access via JDBC, and you need to
 * create a table called 'CategoryData1' and populate it with sample data.  The table I have used
 * looks like this:
 *
 * CATEGORY | SERIES1 | SERIES2 | SERIES3
 * ---------+---------+---------+--------
 * London   |    54.3 |    32.1 |    53.4
 * New York |    43.4 |    54.3 |    75.2
 * Paris    |    17.9 |    34.8 |    37.1
 *
 * ...but you can use any data you like, as long as the SQL query you use returns at least
 * two columns, the first containing VARCHAR data representing categories, and the remaining
 * columns containing numerical data.
 */
public class JDBCCategoryChartDemo extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public JDBCCategoryChartDemo(String title) {

        super(title);

        // read the data from the database...
        CategoryDataset data = readData();

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart3D(
            "JDBC Category Chart Demo", // chart title
            "Category",
            "Value",
            data,                       // data
            PlotOrientation.VERTICAL,
            true,                       // include legend
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
     * Reads the data from a table called 'CategoryData1' in the 'JFREECHARTDB' database.
     * <P>
     * You need to create this database and table before running the demo.  In the example
     * I have used the username 'jfreechart' and the password 'password' to access the
     * database.  Change these values to match your configuration.
     *
     * @return a dataset.
     */
    private CategoryDataset readData() {

        JDBCCategoryDataset data = null;

        String url = "jdbc:postgresql://localhost/jfreechartdb";
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

            data = new JDBCCategoryDataset(con);
            String sql = "SELECT * FROM CATEGORYDATA1;";
            System.out.println("Once...");
            data.executeQuery(sql);
            System.out.println("Again...");
            data.executeQuery(sql);
            System.out.println("Done.");
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

        JDBCCategoryChartDemo demo = new JDBCCategoryChartDemo("JDBC Category Chart Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}

