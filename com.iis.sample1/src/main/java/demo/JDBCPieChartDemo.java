/* ---------------------
 * JDBCPieChartDemo.java
 * ---------------------
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
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;
import org.jfree.data.jdbc.JDBCPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A pie chart that obtains data from a database via JDBC.
 * <P>
 * To run this demo, you need to have a database that you can access via JDBC, and you need to
 * create a table called 'PieData1' and populate it with sample data.  The table I have used
 * looks like this:
 *
 * CATEGORY | VALUE
 * ---------+------
 * London   |  54.3
 * New York |  43.4
 * Paris    |  17.9
 *
 * ...but you can use any data you like, as long as the SQL query you use returns two columns,
 * the first containing VARCHAR data and the second containing numerical data.
 */
public class JDBCPieChartDemo extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public JDBCPieChartDemo(String title) {

        super(title);

        // read the data from the database...
        PieDataset data = readData();

        // create the chart...
        JFreeChart chart = ChartFactory.createPieChart(
            "JDBC Pie Chart Demo", // chart title
            data,                  // data
            true,                  // include legend
            true,
            false
        );

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.yellow);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setNoDataMessage("No data available");

        // add the chart to a panel...
        ChartPanel chartPanel = new ChartPanel(chart);
        setContentPane(chartPanel);

    }

    /**
     * Reads the data from a table called 'PieData1' in the 'JFREECHARTDB' database.
     * <P>
     * You need to create this database and table before running the demo.  In the example
     * I have used the username 'jfreechart' and the password 'password' to access the
     * database.  Change these values to match your configuration.
     *
     * @return a dataset.
     */
    private PieDataset readData() {

        JDBCPieDataset data = null;

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

            data = new JDBCPieDataset(con);
            String sql = "SELECT * FROM PIEDATA1;";
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

        JDBCPieChartDemo demo = new JDBCPieChartDemo("JDBC Pie Chart Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
