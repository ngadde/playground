/* --------------
 * SuperDemo.java
 * --------------
 * (C) Copyright 2004-2009, by Object Refinery Limited.
 *
 * 1.0.x
 */

package demo;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.FontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartTransferable;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * A collection of demos for JFreeChart.  This application is an "umbrella"
 * for the individual JFreeChart demos - you can run this application to look
 * at all the chart demos, or run each demo on its own.
 */
public class SuperDemo extends ApplicationFrame
                       implements ActionListener, TreeSelectionListener {

    /** Exit action command. */
    public static final String EXIT_COMMAND = "EXIT";

    private JPanel displayPanel;

    private JPanel chartContainer;

    private JPanel descriptionContainer;

    private JTextPane descriptionPane;

    private JEditorPane editorPane;

    private TreePath defaultChartPath;

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public SuperDemo(String title) {
        super(title);
        setContentPane(createContent());
        setJMenuBar(createMenuBar());
    }

    /**
     * Creates a panel for the main window.
     *
     * @return A panel.
     */
    private JComponent createContent() {
        JPanel content = new JPanel(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();
        JPanel content1 = new JPanel(new BorderLayout());
        content1.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JTree tree = new JTree(createTreeModel());
        tree.addTreeSelectionListener(this);
        JScrollPane treePane = new JScrollPane(tree);
        treePane.setPreferredSize(new Dimension(300, 100));
        splitter.setLeftComponent(treePane);
        splitter.setRightComponent(createChartDisplayPanel());
        content1.add(splitter);
        tabs.add("Demos", content1);
        MemoryUsageDemo memUse = new MemoryUsageDemo(300000);
        memUse.new DataGenerator(1000).start();
        tabs.add("Memory Usage", memUse);
        tabs.add("Source Code", createSourceCodePanel());
        tabs.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        content.add(tabs);
        tree.setSelectionPath(this.defaultChartPath);
        return content;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // first the file menu
        JMenu fileMenu = new JMenu("File", true);
        fileMenu.setMnemonic('F');

        JMenuItem exportToPDF = new JMenuItem("Export to PDF...", 'p');
        exportToPDF.setActionCommand("EXPORT_TO_PDF");
        exportToPDF.addActionListener(this);
        fileMenu.add(exportToPDF);

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit", 'x');
        exitItem.setActionCommand(EXIT_COMMAND);
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);

        // finally, glue together the menu and return it
        menuBar.add(fileMenu);

        JMenu editMenu = new JMenu("Edit", false);
        menuBar.add(editMenu);
        JMenuItem copyItem = new JMenuItem("Copy", 'C');
        copyItem.setActionCommand("COPY");
        copyItem.addActionListener(this);
        editMenu.add(copyItem);

        JMenu themeMenu = new JMenu("Theme", true);
        themeMenu.setMnemonic('T');

        JCheckBoxMenuItem jfree = new JCheckBoxMenuItem("JFree", true);
        jfree.setActionCommand("JFREE_THEME");
        jfree.addActionListener(this);
        themeMenu.add(jfree);

        JCheckBoxMenuItem darkness = new JCheckBoxMenuItem("Darkness", false);
        darkness.setActionCommand("DARKNESS_THEME");
        darkness.addActionListener(this);
        themeMenu.add(darkness);

        JCheckBoxMenuItem legacy = new JCheckBoxMenuItem("Legacy", false);
        legacy.setActionCommand("LEGACY_THEME");
        legacy.addActionListener(this);
        themeMenu.add(legacy);

        ButtonGroup g = new ButtonGroup();
        g.add(jfree);
        g.add(darkness);
        g.add(legacy);
        menuBar.add(themeMenu);

        return menuBar;
    }

    private JPanel createSourceCodePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        this.editorPane = new JEditorPane();
        this.editorPane.setEditable(false);
        this.editorPane.setFont(new Font("Monospaced", Font.PLAIN, 12));
        updateSourceCodePanel("source.html");

        JScrollPane editorScrollPane = new JScrollPane(this.editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        editorScrollPane.setPreferredSize(new Dimension(250, 145));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));

        panel.add(editorScrollPane);
        return panel;
    }

    /**
     * Display source code in the Source Code tab.  If the file cannot be
     * located then default to source.html
     *
     * @param sourceFilename  file to display in the source code panel
     */
    private void updateSourceCodePanel(String sourceFilename) {
        java.net.URL sourceURL = null;
        if (sourceFilename != null) {
            sourceURL = SuperDemo.class.getResource(sourceFilename);
        }
        if (sourceURL == null) {
            // default if java source files are not available
            sourceURL = SuperDemo.class.getResource("source.html");
        }

        if (sourceURL != null) {
            try {
                this.editorPane.setPage(sourceURL);
            }
            catch (IOException e) {
                System.err.println("Attempted to read a bad URL: " + sourceURL);
            }
        }
        else {
            System.err.println("Couldn't find file: source.html");
        }
    }

    private void copyToClipboard() {
        JFreeChart chart = null;
        int w = 0;
        int h = 0;

        Component c = this.chartContainer.getComponent(0);
        if (c instanceof ChartPanel) {
            ChartPanel cp = (ChartPanel) c;
            chart = cp.getChart();
            w = cp.getWidth();
            h = cp.getHeight();
        }
        else if (c instanceof DemoPanel) {
            DemoPanel dp = (DemoPanel) c;
            chart = (JFreeChart) dp.charts.get(0);
            w = dp.getWidth();
            h = dp.getHeight();
        }

        if (chart != null) {
            Clipboard systemClipboard
                    = Toolkit.getDefaultToolkit().getSystemClipboard();
            ChartTransferable selection = new ChartTransferable(chart, w, h);
            systemClipboard.setContents(selection, null);
        }
    }

    /**
     * Handles menu selections by passing control to an appropriate method.
     *
     * @param event  the event.
     */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("EXPORT_TO_PDF")) {
            exportToPDF();
        }
        else if (command.equals("COPY")) {
            copyToClipboard();
        }
        else if (command.equals("LEGACY_THEME")) {
            ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
            applyThemeToChart();
        }
        else if (command.equals("JFREE_THEME")) {
            ChartFactory.setChartTheme(StandardChartTheme.createJFreeTheme());
            applyThemeToChart();
        }
        else if (command.equals("DARKNESS_THEME")) {
            ChartFactory.setChartTheme(StandardChartTheme.createDarknessTheme());
            applyThemeToChart();
        }
        else if (command.equals(EXIT_COMMAND)) {
            attemptExit();
        }
    }

    private void applyThemeToChart() {
        Component c = this.chartContainer.getComponent(0);
        if (c instanceof ChartPanel) {
            ChartPanel cp = (ChartPanel) c;
            ChartUtilities.applyCurrentTheme(cp.getChart());
        }
        else if (c instanceof DemoPanel) {
            DemoPanel dp = (DemoPanel) c;
            JFreeChart[] charts = dp.getCharts();
            for (int i = 0; i < charts.length; i++) {
                ChartUtilities.applyCurrentTheme(charts[i]);
            }
        }
    }

    /**
     * Opens a "Save As..." dialog, inviting the user to save the selected
     * chart to a file in PDF format.
     */
    private void exportToPDF() {
        JFreeChart chart = null;
        int w = 0;
        int h = 0;

        Component c = this.chartContainer.getComponent(0);
        if (c instanceof ChartPanel) {
            ChartPanel cp = (ChartPanel) c;
            chart = cp.getChart();
            w = cp.getWidth();
            h = cp.getHeight();
        }
        else if (c instanceof DemoPanel) {
            DemoPanel dp = (DemoPanel) c;
            chart = (JFreeChart) dp.charts.get(0);
            w = dp.getWidth();
            h = dp.getHeight();
        }

        if (chart != null) {
            JFileChooser fc = new JFileChooser();
            fc.setName("untitled.pdf");
            fc.setFileFilter(new FileFilter() {

                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().endsWith(".pdf");
                }

                public String getDescription() {
                    return "Portable Document Format (PDF)";
                }});
            int result = fc.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    JFreeChart chartClone = (JFreeChart) chart.clone();
                    PDFExportTask t = new PDFExportTask(chartClone, w, h,
                            fc.getSelectedFile());
                    Thread thread = new Thread(t);
                    thread.start();
                }
                catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            String message = "Unable to export the selected item.  There is ";
            message += "either no chart selected,\nor else the chart is not ";
            message += "at the expected location in the component hierarchy\n";
            message += "(future versions of the demo may include code to ";
            message += "handle these special cases).";
            JOptionPane.showMessageDialog(this, message, "PDF Export",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    static class PDFExportTask implements Runnable {

        JFreeChart chart;

        int width;

        int height;

        File file;

        /**
         * A task that exports a chart to a file in PDF format using iText.
         *
         * @param chart  the chart.
         * @param width  the width.
         * @param height  the height.
         * @param file  the file.
         */
        public PDFExportTask(JFreeChart chart, int width, int height,
                File file) {
            this.chart = chart;
            this.file = file;
            this.width = width;
            this.height = height;
            chart.setBorderVisible(true);
            chart.setPadding(new RectangleInsets(2, 2, 2, 2));
        }

        /**
         * Performs the task.
         */
        public void run() {
            try {
                SuperDemo.saveChartAsPDF(this.file, this.chart, this.width,
                        this.height, new DefaultFontMapper());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Writes a chart to an output stream in PDF format using iText.
     *
     * @param out  the output stream.
     * @param chart  the chart.
     * @param width  the chart width.
     * @param height  the chart height.
     * @param mapper  the font mapper.
     *
     * @throws IOException if there is an I/O problem.
     */
    public static void writeChartAsPDF(OutputStream out,
            JFreeChart chart,
            int width,
            int height,
            FontMapper mapper) throws IOException {
        Rectangle pagesize = new Rectangle(width, height);
        Document document = new Document(pagesize, 50, 50, 50, 50);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.addAuthor("JFreeChart");
            document.addSubject("Demonstration");
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            PdfTemplate tp = cb.createTemplate(width, height);
            Graphics2D g2 = tp.createGraphics(width, height, mapper);
            Rectangle2D r2D = new Rectangle2D.Double(0, 0, width, height);
            chart.draw(g2, r2D);
            g2.dispose();
            cb.addTemplate(tp, 0, 0);
        }
        catch (DocumentException de) {
            System.err.println(de.getMessage());
        }
        document.close();
    }

    /**
     * Saves a chart to a file in PDF format using iText.
     *
     * @param file  the file.
     * @param chart  the chart.
     * @param width  the chart width.
     * @param height  the chart height.
     * @param mapper  the font mapper.
     *
     * @throws IOException if there is an I/O problem.
     */
    public static void saveChartAsPDF(File file,
            JFreeChart chart,
            int width,
            int height,
            FontMapper mapper) throws IOException {
        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        writeChartAsPDF(out, chart, width, height, mapper);
        out.close();
    }

    /**
     * Exits the application, but only if the user agrees.
     */
    private void attemptExit() {

        String title = "Confirm";
        String message = "Are you sure you want to exit the demo?";
        int result = JOptionPane.showConfirmDialog(
            this, message, title, JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        if (result == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }


    private JPanel createChartDisplayPanel() {

        this.displayPanel = new JPanel(new BorderLayout());
        this.chartContainer = new JPanel(new BorderLayout());
        this.chartContainer.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(4, 4, 4, 4),
                BorderFactory.createLineBorder(Color.black)
            )
        );
        this.chartContainer.add(createNoDemoSelectedPanel());
        this.descriptionContainer = new JPanel(new BorderLayout());
        this.descriptionContainer.setBorder(
            BorderFactory.createEmptyBorder(4, 4, 4, 4)
        );
        this.descriptionContainer.setPreferredSize(new Dimension(600, 140));
        this.descriptionPane = new JTextPane();
        this.descriptionPane.setEditable(false);
        JScrollPane scroller = new JScrollPane(
                this.descriptionPane,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.descriptionContainer.add(scroller);
        displayDescription("select.html");
        JSplitPane splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitter.setTopComponent(this.chartContainer);
        splitter.setBottomComponent(this.descriptionContainer);
        this.displayPanel.add(splitter);
        splitter.setDividerLocation(0.75);
        return this.displayPanel;
    }

    /**
     * Creates a <code>TreeModel</code> with references to all the individual
     * demo applications.  This is an ugly piece of hard-coding but, hey, it's
     * just a demo!
     *
     * @return A TreeModel.
     */
    private TreeModel createTreeModel() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("JFreeChart");
        MutableTreeNode showcase = createShowcaseNode(root);
        root.add(showcase);
        root.add(createAreaChartsNode());
        root.add(createBarChartsNode());
        root.add(createStackedBarChartsNode());
        root.add(createCombinedAxisChartsNode());
        root.add(createFinancialChartsNode());
        root.add(createGanttChartsNode());
        root.add(createLineChartsNode());
        root.add(createMeterChartsNode());
        root.add(createMultipleAxisChartsNode());
        root.add(createOverlaidChartsNode());
        root.add(createPieChartsNode());
        root.add(createStatisticalChartsNode());
        root.add(createTimeSeriesChartsNode());
        root.add(createXYChartsNode());
        root.add(createMiscellaneousChartsNode());
//        root.add(createExperimentalNode());
        return new DefaultTreeModel(root);
    }

    /**
     * Creates the tree node for the pie chart demos.
     *
     * @return A populated tree node.
     */
    private MutableTreeNode createPieChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Pie Charts");
        root.add(createNode("demo.PieChartDemo1", "PieChartDemo1.java"));
        root.add(createNode("demo.PieChartDemo2", "PieChartDemo2.java"));
        root.add(createNode("demo.PieChartDemo3", "PieChartDemo3.java"));
        root.add(createNode("demo.PieChartDemo4", "PieChartDemo4.java"));
        root.add(createNode("demo.PieChartDemo5", "PieChartDemo5.java"));
        root.add(createNode("demo.PieChartDemo6", "PieChartDemo6.java"));
        root.add(createNode("demo.PieChartDemo7", "PieChartDemo7.java"));
        root.add(createNode("demo.PieChartDemo8", "PieChartDemo8.java"));
        root.add(createNode("demo.PieChart3DDemo1", "PieChart3DDemo1.java"));
        root.add(createNode("demo.PieChart3DDemo2", "PieChart3DDemo2.java"));
        root.add(createNode("demo.PieChart3DDemo3", "PieChart3DDemo3.java"));
        root.add(createNode("demo.MultiplePieChartDemo1", "MultiplePieChartDemo1.java"));
        root.add(createNode("demo.MultiplePieChartDemo2", "MultiplePieChartDemo2.java"));
        root.add(createNode("demo.MultiplePieChartDemo3", "MultiplePieChartDemo3.java"));
        root.add(createNode("demo.MultiplePieChartDemo4", "MultiplePieChartDemo4.java"));
        root.add(createNode("demo.RingChartDemo1", "RingChartDemo1.java"));
        return root;
    }

    /**
     * Creates the tree node for the overlaid chart demos.
     *
     * @return A populated tree node.
     */
    private MutableTreeNode createOverlaidChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Overlaid Charts");
        root.add(createNode("demo.OverlaidBarChartDemo1", "OverlaidBarChartDemo1.java"));
        root.add(createNode("demo.OverlaidBarChartDemo2", "OverlaidBarChartDemo2.java"));
        root.add(createNode("demo.OverlaidXYPlotDemo1", "OverlaidXYPlotDemo1.java"));
        root.add(createNode("demo.OverlaidXYPlotDemo2", "OverlaidXYPlotDemo2.java"));
        return root;
    }

    /**
     * Creates a tree node containing sample bar charts.
     *
     * @return The tree node.
     */
    private MutableTreeNode createBarChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Bar Charts");
        root.add(createCategoryBarChartsNode());
        root.add(createXYBarChartsNode());
        return root;
    }

    /**
     * A node for various stacked bar charts.
     *
     * @return The node.
     */
    private MutableTreeNode createStackedBarChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Bar Charts - Stacked");
        root.add(createNode("demo.PopulationChartDemo1", "PopulationChartDemo1.java"));
        root.add(createNode("demo.StackedBarChartDemo1", "StackedBarChartDemo1.java"));
        root.add(createNode("demo.StackedBarChartDemo2", "StackedBarChartDemo2.java"));
        root.add(createNode("demo.StackedBarChartDemo3", "StackedBarChartDemo3.java"));
        root.add(createNode("demo.StackedBarChartDemo4", "StackedBarChartDemo4.java"));
        root.add(createNode("demo.StackedBarChartDemo5", "StackedBarChartDemo5.java"));
        root.add(createNode("demo.StackedBarChartDemo6", "StackedBarChartDemo6.java"));
        root.add(createNode("demo.StackedBarChartDemo7", "StackedBarChartDemo7.java"));
        root.add(createNode("demo.StackedBarChart3DDemo1", "StackedBarChart3DDemo1.java"));
        root.add(createNode("demo.StackedBarChart3DDemo2", "StackedBarChart3DDemo2.java"));
        root.add(createNode("demo.StackedBarChart3DDemo3", "StackedBarChart3DDemo3.java"));
        root.add(createNode("demo.StackedBarChart3DDemo4", "StackedBarChart3DDemo4.java"));
        root.add(createNode("demo.StackedBarChart3DDemo5", "StackedBarChart3DDemo5.java"));
        return root;
    }

    /**
     * Creates a tree node containing bar charts based on the
     * {@link CategoryPlot} class.
     *
     * @return The tree node.
     */
    private MutableTreeNode createCategoryBarChartsNode() {
        DefaultMutableTreeNode root
            = new DefaultMutableTreeNode("CategoryPlot");

        root.add(createNode("demo.BarChartDemo1", "BarChartDemo1.java"));
        root.add(createNode("demo.BarChartDemo2", "BarChartDemo2.java"));
        root.add(createNode("demo.BarChartDemo3", "BarChartDemo3.java"));
        root.add(createNode("demo.BarChartDemo4", "BarChartDemo4.java"));
        root.add(createNode("demo.BarChartDemo5", "BarChartDemo5.java"));
        root.add(createNode("demo.BarChartDemo6", "BarChartDemo6.java"));
        root.add(createNode("demo.BarChartDemo7", "BarChartDemo7.java"));
        root.add(createNode("demo.BarChartDemo8", "BarChartDemo8.java"));
        root.add(createNode("demo.BarChartDemo9", "BarChartDemo9.java"));
        root.add(createNode("demo.BarChartDemo10", "BarChartDemo10.java"));
        root.add(createNode("demo.BarChartDemo11", "BarChartDemo11.java"));
        root.add(createNode("demo.BarChart3DDemo1", "BarChart3DDemo1.java"));
        root.add(createNode("demo.BarChart3DDemo2", "BarChart3DDemo2.java"));
        root.add(createNode("demo.BarChart3DDemo3", "BarChart3DDemo3.java"));
        root.add(createNode("demo.BarChart3DDemo4", "BarChart3DDemo4.java"));
        root.add(createNode("demo.CylinderChartDemo1", "CylinderChartDemo1.java"));
        root.add(createNode("demo.CylinderChartDemo2", "CylinderChartDemo2.java"));
        root.add(createNode("demo.IntervalBarChartDemo1", "IntervalBarChartDemo1.java"));
        root.add(createNode("demo.LayeredBarChartDemo1", "LayeredBarChartDemo1.java"));
        root.add(createNode("demo.LayeredBarChartDemo2", "LayeredBarChartDemo2.java"));
        root.add(createNode("demo.SlidingCategoryDatasetDemo1", "SlidingCategoryDatasetDemo1.java"));
        root.add(createNode("demo.SlidingCategoryDatasetDemo2", "SlidingCategoryDatasetDemo2.java"));
        root.add(createNode("demo.StatisticalBarChartDemo1",
                "StatisticalBarChartDemo1.java"));
        root.add(createNode("demo.SurveyResultsDemo1", "SurveyResultsDemo1.java"));
        root.add(createNode("demo.SurveyResultsDemo2", "SurveyResultsDemo2.java"));
        root.add(createNode("demo.SurveyResultsDemo3", "SurveyResultsDemo3.java"));
        root.add(createNode("demo.WaterfallChartDemo1", "WaterfallChartDemo1.java"));
        return root;
    }

    private MutableTreeNode createXYBarChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("XYPlot");
        root.add(createNode("demo.XYBarChartDemo1", "XYBarChartDemo1.java"));
        root.add(createNode("demo.XYBarChartDemo2", "XYBarChartDemo2.java"));
        root.add(createNode("demo.XYBarChartDemo3", "XYBarChartDemo3.java"));
        root.add(createNode("demo.XYBarChartDemo4", "XYBarChartDemo4.java"));
        root.add(createNode("demo.XYBarChartDemo5", "XYBarChartDemo5.java"));
        root.add(createNode("demo.XYBarChartDemo6", "XYBarChartDemo6.java"));
        root.add(createNode("demo.XYBarChartDemo7", "XYBarChartDemo7.java"));
        root.add(createNode("demo.ClusteredXYBarRendererDemo1", "ClusteredXYBarRendererDemo1.java"));
        root.add(createNode("demo.StackedXYBarChartDemo1", "StackedXYBarChartDemo1.java"));
        root.add(createNode("demo.StackedXYBarChartDemo2", "StackedXYBarChartDemo2.java"));
        root.add(createNode("demo.StackedXYBarChartDemo3", "StackedXYBarChartDemo3.java"));
        root.add(createNode("demo.RelativeDateFormatDemo1", "RelativeDateFormatDemo1.java"));
        root.add(createNode("demo.RelativeDateFormatDemo2", "RelativeDateFormatDemo2.java"));
        return root;
    }

    /**
     * Creates a tree node containing line chart items.
     *
     * @return A tree node.
     */
    private MutableTreeNode createLineChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Line Charts");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.AnnotationDemo1",
                "AnnotationDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo1",
                "LineChartDemo1.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo2",
                "LineChartDemo2.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo3",
                "LineChartDemo3.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo4",
                "LineChartDemo4.java"));
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo5",
                "LineChartDemo5.java"));
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo6",
                "LineChartDemo6.java"));
        DefaultMutableTreeNode n8 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo7",
                "LineChartDemo7.java"));
        DefaultMutableTreeNode n9 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo8",
                "LineChartDemo8.java"));
        DefaultMutableTreeNode n10 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChart3DDemo1",
                "LineChart3DDemo1.java"));
        DefaultMutableTreeNode n11 = new DefaultMutableTreeNode(
                new DemoDescription("demo.StatisticalLineChartDemo1",
                "StatisticalLineChartDemo1.java"));
        DefaultMutableTreeNode n12 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYSplineRendererDemo1",
                "XYSplineRendererDemo1.java"));
        DefaultMutableTreeNode n13 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYStepRendererDemo1",
                "XYStepRendererDemo1.java"));
        DefaultMutableTreeNode n14 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYStepRendererDemo2",
                "XYStepRendererDemo2.java"));

        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);
        root.add(n9);
        root.add(n10);
        root.add(n11);
        root.add(n12);
        root.add(n13);
        root.add(n14);

        return root;
    }

    private MutableTreeNode createNode(String name, String file) {
        return new DefaultMutableTreeNode(new DemoDescription(name, file));
    }

    /**
     * A node for the 'cool' charts.
     *
     * @param r  the root node.
     *
     * @return The node.
     */
    private MutableTreeNode createShowcaseNode(DefaultMutableTreeNode r) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "*** Showcase Charts ***");
        MutableTreeNode n = createNode("demo.PieChartDemo4", "PieChartDemo4.java");
        root.add(n);
        root.add(createNode("demo.MultiplePieChartDemo1", "MultiplePieChartDemo1.java"));
        MutableTreeNode n2 = createNode("demo.BarChart3DDemo1", "BarChart3DDemo1.java");
        this.defaultChartPath = new TreePath(new Object[] {r, root, n2});
        root.add(n2);
        root.add(createNode("demo.StatisticalBarChartDemo1", "StatisticalBarChartDemo1.java"));
        root.add(createNode("demo.HistogramDemo1", "HistogramDemo1.java"));
        root.add(createNode("demo.StackedBarChartDemo2", "StackedBarChartDemo2.java"));
        root.add(createNode("demo.StackedXYBarChartDemo2", "StackedXYBarChartDemo2.java"));
        root.add(createNode("demo.NormalDistributionDemo2", "NormalDistributionDemo2.java"));
        root.add(createNode("demo.ParetoChartDemo1", "ParetoChartDemo1.java"));
        root.add(createNode("demo.WaterfallChartDemo1", "WaterfallChartDemo1.java"));
        root.add(createNode("demo.LineChartDemo1", "LineChartDemo1.java"));
        root.add(createNode("demo.AnnotationDemo1", "AnnotationDemo1.java"));
        root.add(createNode("demo.XYSplineRendererDemo1", "XYSplineRendererDemo1.java"));
        root.add(createNode("demo.DualAxisDemo1", "DualAxisDemo1.java"));
        root.add(createNode("demo.PriceVolumeDemo1", "PriceVolumeDemo1.java"));
        root.add(createNode("demo.YieldCurveDemo1", "YieldCurveDemo1.java"));
        root.add(createNode("demo.MultipleAxisDemo1", "MultipleAxisDemo1.java"));
        root.add(createNode("demo.DifferenceChartDemo1", "DifferenceChartDemo1.java"));
        root.add(createNode("demo.DifferenceChartDemo2", "DifferenceChartDemo2.java"));
        root.add(createNode("demo.DeviationRendererDemo2", "DeviationRendererDemo2.java"));
        root.add(createNode("demo.DialDemo2a", "DialDemo2a.java"));
        root.add(createNode("demo.VectorPlotDemo1", "VectorPlotDemo1.java"));
        root.add(createNode("demo.CrosshairDemo2", "CrosshairDemo2.java"));
        root.add(createNode("demo.XYDrawableAnnotationDemo1", "XYDrawableAnnotationDemo1.java"));
        root.add(createNode("demo.XYTaskDatasetDemo2", "XYTaskDatasetDemo2.java"));
        root.add(createNode("demo.SlidingCategoryDatasetDemo2", "SlidingCategoryDatasetDemo2.java"));
        root.add(createNode("demo.CrossSectionDemo1", "CrossSectionDemo1.java"));
        return root;
    }

    /**
     * A node for various area charts.
     *
     * @return The node.
     */
    private MutableTreeNode createAreaChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Area Charts");
        root.add(createNode("demo.AreaChartDemo1", "AreaChartDemo1.java"));
        root.add(createNode("demo.StackedAreaChartDemo1", "StackedAreaChartDemo1.java"));
        root.add(createNode("demo.StackedXYAreaChartDemo1", "StackedXYAreaChartDemo1.java"));
        root.add(createNode("demo.StackedXYAreaChartDemo2", "StackedXYAreaChartDemo2.java"));
        root.add(createNode("demo.StackedXYAreaRendererDemo1", "StackedXYAreaRendererDemo1.java"));
        root.add(createNode("demo.XYAreaChartDemo1", "XYAreaChartDemo1.java"));
        root.add(createNode("demo.XYAreaChartDemo2", "XYAreaChartDemo2.java"));
        root.add(createNode("demo.XYAreaRenderer2Demo1", "XYAreaRenderer2Demo1.java"));
        root.add(createNode("demo.XYStepAreaRendererDemo1", "XYStepAreaRendererDemo1.java"));
        return root;
    }

    private MutableTreeNode createStatisticalChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Statistical Charts");
        root.add(createNode("demo.BoxAndWhiskerChartDemo1", "BoxAndWhiskerChartDemo1.java"));
        root.add(createNode("demo.BoxAndWhiskerChartDemo2", "BoxAndWhiskerChartDemo2.java"));
        root.add(createNode("demo.HistogramDemo1", "HistogramDemo1.java"));
        root.add(createNode("demo.HistogramDemo2", "HistogramDemo2.java"));
        root.add(createNode("demo.MinMaxCategoryPlotDemo1", "MinMaxCategoryPlotDemo1.java"));
        root.add(createNode("demo.NormalDistributionDemo1", "NormalDistributionDemo1.java"));
        root.add(createNode("demo.NormalDistributionDemo2", "NormalDistributionDemo2.java"));
        root.add(createNode("demo.RegressionDemo1", "RegressionDemo1.java"));
        root.add(createNode("demo.ScatterPlotDemo1", "ScatterPlotDemo1.java"));
        root.add(createNode("demo.ScatterPlotDemo2", "ScatterPlotDemo2.java"));
        root.add(createNode("demo.ScatterPlotDemo3", "ScatterPlotDemo3.java"));
        root.add(createNode("demo.ScatterPlotDemo4", "ScatterPlotDemo4.java"));
        root.add(createNode("demo.XYErrorRendererDemo1", "XYErrorRendererDemo1.java"));
        root.add(createNode("demo.XYErrorRendererDemo2", "XYErrorRendererDemo2.java"));
        return root;
    }

    /**
     * Creates a sub-tree for the time series charts.
     *
     * @return The root node for the subtree.
     */
    private MutableTreeNode createTimeSeriesChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "Time Series Charts");

        root.add(createNode("demo.TimeSeriesDemo1", "TimeSeriesDemo1.java"));
        root.add(createNode("demo.TimeSeriesDemo2", "TimeSeriesDemo2.java"));
        root.add(createNode("demo.TimeSeriesDemo3", "TimeSeriesDemo3.java"));
        root.add(createNode("demo.TimeSeriesDemo4", "TimeSeriesDemo4.java"));
        root.add(createNode("demo.TimeSeriesDemo5", "TimeSeriesDemo5.java"));
        root.add(createNode("demo.TimeSeriesDemo6", "TimeSeriesDemo6.java"));
        root.add(createNode("demo.TimeSeriesDemo7", "TimeSeriesDemo7.java"));
        root.add(createNode("demo.TimeSeriesDemo8", "TimeSeriesDemo8.java"));
        root.add(createNode("demo.TimeSeriesDemo9", "TimeSeriesDemo9.java"));
        root.add(createNode("demo.TimeSeriesDemo10", "TimeSeriesDemo10.java"));
        root.add(createNode("demo.TimeSeriesDemo11", "TimeSeriesDemo11.java"));
        root.add(createNode("demo.TimeSeriesDemo12", "TimeSeriesDemo12.java"));
        root.add(createNode("demo.TimeSeriesDemo13", "TimeSeriesDemo13.java"));

        root.add(createNode("demo.PeriodAxisDemo1", "PeriodAxisDemo1.java"));
        root.add(createNode("demo.PeriodAxisDemo2", "PeriodAxisDemo2.java"));
        root.add(createNode("demo.PeriodAxisDemo3", "PeriodAxisDemo3.java"));
        root.add(createNode("demo.RelativeDateFormatDemo1", "RelativeDateFormatDemo1.java"));
        root.add(createNode("demo.DeviationRendererDemo1", "DeviationRendererDemo1.java"));
        root.add(createNode("demo.DeviationRendererDemo2", "DeviationRendererDemo2.java"));
        root.add(createNode("demo.DifferenceChartDemo1", "DifferenceChartDemo1.java"));
        root.add(createNode("demo.DifferenceChartDemo2", "DifferenceChartDemo2.java"));
        root.add(createNode("demo.CompareToPreviousYearDemo", "CompareToPreviousYearDemo.java"));

        return root;
    }

    /**
     * Creates a node for the tree model that contains financial charts.
     *
     * @return The tree node.
     */
    private MutableTreeNode createFinancialChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Financial Charts");
        root.add(createNode("demo.CandlestickChartDemo1", "CandlestickChartDemo1.java"));
        root.add(createNode("demo.HighLowChartDemo1", "HighLowChartDemo1.java"));
        root.add(createNode("demo.HighLowChartDemo2", "HighLowChartDemo2.java"));
        root.add(createNode("demo.HighLowChartDemo3", "HighLowChartDemo3.java"));
        root.add(createNode("demo.MovingAverageDemo1", "MovingAverageDemo1.java"));
        root.add(createNode("demo.PriceVolumeDemo1", "PriceVolumeDemo1.java"));
        root.add(createNode("demo.PriceVolumeDemo2", "PriceVolumeDemo2.java"));
        root.add(createNode("demo.YieldCurveDemo1", "YieldCurveDemo1.java"));
        return root;
    }

    private MutableTreeNode createXYChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("XY Charts");
        root.add(createNode("demo.ScatterPlotDemo1", "ScatterPlotDemo1.java"));
        root.add(createNode("demo.ScatterPlotDemo2", "ScatterPlotDemo2.java"));
        root.add(createNode("demo.ScatterPlotDemo3", "ScatterPlotDemo3.java"));
        root.add(createNode("demo.LogAxisDemo1", "LogAxisDemo1.java"));
        root.add(createNode("demo.Function2DDemo1", "Function2DDemo1.java"));
        root.add(createNode("demo.XYBlockChartDemo1", "XYBlockChartDemo1.java"));
        root.add(createNode("demo.XYBlockChartDemo2", "XYBlockChartDemo2.java"));
        root.add(createNode("demo.XYBlockChartDemo3", "XYBlockChartDemo3.java"));
        root.add(createNode("demo.XYLineAndShapeRendererDemo1", "XYLineAndShapeRendererDemo1.java"));
        root.add(createNode("demo.XYLineAndShapeRendererDemo2", "XYLineAndShapeRendererDemo2.java"));
        root.add(createNode("demo.XYSeriesDemo1", "XYSeriesDemo1.java"));
        root.add(createNode("demo.XYSeriesDemo2", "XYSeriesDemo2.java"));
        root.add(createNode("demo.XYSeriesDemo3", "XYSeriesDemo3.java"));
        root.add(createNode("demo.XYShapeRendererDemo1", "XYShapeRendererDemo1.java"));
        root.add(createNode("demo.VectorPlotDemo1", "VectorPlotDemo1.java"));
        return root;
    }

    /**
     * Creates a node for the tree model that contains dial and meter charts.
     *
     * @return The tree node.
     */
    private MutableTreeNode createMeterChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "Dial / Meter Charts");
        root.add(createNode("demo.DialDemo1", "DialDemo1.java"));
        root.add(createNode("demo.DialDemo2", "DialDemo2.java"));
        root.add(createNode("demo.DialDemo2a", "DialDemo2a.java"));
        root.add(createNode("demo.DialDemo3", "DialDemo3.java"));
        root.add(createNode("demo.DialDemo4", "DialDemo4.java"));
        root.add(createNode("demo.DialDemo5", "DialDemo5.java"));
        root.add(createNode("demo.MeterChartDemo1", "MeterChartDemo1.java"));
        root.add(createNode("demo.MeterChartDemo2", "MeterChartDemo2.java"));
        root.add(createNode("demo.MeterChartDemo3", "MeterChartDemo3.java"));
        root.add(createNode("demo.ThermometerDemo1", "ThermometerDemo1.java"));
        return root;
    }

    private MutableTreeNode createMultipleAxisChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "Multiple Axis Charts");
        root.add(createNode("demo.DualAxisDemo1", "DualAxisDemo1.java"));
        root.add(createNode("demo.DualAxisDemo2", "DualAxisDemo2.java"));
        root.add(createNode("demo.DualAxisDemo3", "DualAxisDemo3.java"));
        root.add(createNode("demo.DualAxisDemo4", "DualAxisDemo4.java"));
        root.add(createNode("demo.DualAxisDemo5", "DualAxisDemo5.java"));
        root.add(createNode("demo.MultipleAxisDemo1", "MultipleAxisDemo1.java"));
        root.add(createNode("demo.MultipleAxisDemo2", "MultipleAxisDemo2.java"));
        root.add(createNode("demo.MultipleAxisDemo3", "MultipleAxisDemo3.java"));
        root.add(createNode("demo.ParetoChartDemo1", "ParetoChartDemo1.java"));
        return root;
    }

    private MutableTreeNode createCombinedAxisChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Combined Axis Charts");
        root.add(createNode("demo.CombinedCategoryPlotDemo1", "CombinedCategoryPlotDemo1.java"));
        root.add(createNode("demo.CombinedCategoryPlotDemo2", "CombinedCategoryPlotDemo2.java"));
        root.add(createNode("demo.CombinedTimeSeriesDemo1", "CombinedTimeSeriesDemo1.java"));
        root.add(createNode("demo.CombinedXYPlotDemo1", "CombinedXYPlotDemo1.java"));
        root.add(createNode("demo.CombinedXYPlotDemo2", "CombinedXYPlotDemo2.java"));
        root.add(createNode("demo.CombinedXYPlotDemo3", "CombinedXYPlotDemo3.java"));
        root.add(createNode("demo.CombinedXYPlotDemo4", "CombinedXYPlotDemo4.java"));
        return root;
    }

    private MutableTreeNode createGanttChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Gantt Charts");
        root.add(createNode("demo.GanttDemo1", "GanttDemo1.java"));
        root.add(createNode("demo.GanttDemo2", "GanttDemo2.java"));
        root.add(createNode("demo.SlidingGanttDatasetDemo1", "SlidingGanttDatasetDemo1.java"));
        root.add(createNode("demo.XYTaskDatasetDemo1", "XYTaskDatasetDemo1"));
        root.add(createNode("demo.XYTaskDatasetDemo2", "XYTaskDatasetDemo2"));
        return root;
    }

    /**
     * Creates the subtree containing the miscellaneous chart types.
     *
     * @return A subtree.
     */
    private MutableTreeNode createMiscellaneousChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Miscellaneous");
        root.add(createAnnotationsNode());
        root.add(createCrosshairChartsNode());
        root.add(createDynamicChartsNode());
        root.add(createItemLabelsNode());
        root.add(createLegendNode());
        root.add(createMarkersNode());
        root.add(createOrientationNode());

        root.add(createNode("demo.AxisOffsetsDemo1", "AxisOffsetsDemo1.java"));
        root.add(createNode("demo.BubbleChartDemo1", "BubbleChartDemo1.java"));
        root.add(createNode("demo.BubbleChartDemo2", "BubbleChartDemo2.java"));
        root.add(createNode("demo.CategoryLabelPositionsDemo1", "CategoryLabelPositionsDemo1.java"));
        root.add(createNode("demo.CategoryStepChartDemo1", "CategoryStepChartDemo1.java"));
        root.add(createNode("demo.CompassDemo1", "CompassDemo1.java"));
        root.add(createNode("demo.CompassFormatDemo1", "CompassFormatDemo1.java"));
        root.add(createNode("demo.CompassFormatDemo2", "CompassFormatDemo2.java"));
        root.add(createNode("demo.EventFrequencyDemo1", "EventFrequencyDemo1.java"));
        root.add(createNode("demo.GradientPaintTransformerDemo1", "GradientPaintTransformerDemo1.java"));
        root.add(createNode("demo.GridBandDemo1", "GridBandDemo1.java"));
        root.add(createNode("demo.HideSeriesDemo1", "HideSeriesDemo1.java"));
        root.add(createNode("demo.HideSeriesDemo2", "HideSeriesDemo2.java"));
        root.add(createNode("demo.HideSeriesDemo3", "HideSeriesDemo3.java"));
        root.add(createNode("demo.MultipleDatasetDemo1", "MultipleDatasetDemo1.java"));
        root.add(createNode("demo.PolarChartDemo1", "PolarChartDemo1.java"));
        root.add(createNode("demo.ScatterRendererDemo1", "ScatterRendererDemo1.java"));
        root.add(createNode("demo.SpiderWebChartDemo1", "SpiderWebChartDemo1.java"));
        root.add(createNode("demo.SymbolAxisDemo1", "SymbolAxisDemo1.java"));
        root.add(createNode("demo.ThermometerDemo1", "ThermometerDemo1.java"));
        root.add(createNode("demo.ThermometerDemo2", "ThermometerDemo2.java"));
        root.add(createNode("demo.ThumbnailDemo1", "ThumbnailDemo1.java"));
        root.add(createNode("demo.TranslateDemo1", "TranslateDemo1.java"));
        root.add(createNode("demo.WindChartDemo1", "WindChartDemo1.java"));
        root.add(createNode("demo.YIntervalChartDemo1", "YIntervalChartDemo1.java"));
        root.add(createNode("demo.YIntervalChartDemo2", "YIntervalChartDemo2.java"));
        return root;
    }

    private MutableTreeNode createAnnotationsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Annotations");
        root.add(createNode("demo.AnnotationDemo1", "AnnotationDemo1.java"));
        root.add(createNode("demo.AnnotationDemo2", "AnnotationDemo2.java"));
        root.add(createNode("demo.CategoryPointerAnnotationDemo1", "CategoryPointerAnnotationDemo1.java"));
        root.add(createNode("demo.XYBoxAnnotationDemo1", "XYBoxAnnotationDemo1.java"));
        root.add(createNode("demo.XYPolygonAnnotationDemo1", "XYPolygonAnnotationDemo1.java"));
        root.add(createNode("demo.XYTitleAnnotationDemo1", "XYTitleAnnotationDemo1.java"));
        return root;
    }

    private MutableTreeNode createCrosshairChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Crosshairs");
        root.add(createNode("demo.CrosshairDemo1", "CrosshairDemo1.java"));
        root.add(createNode("demo.CrosshairDemo2", "CrosshairDemo2.java"));
        root.add(createNode("demo.CrosshairDemo3", "CrosshairDemo3.java"));
        root.add(createNode("demo.CrosshairDemo4", "CrosshairDemo4.java"));
        return root;
    }

    private MutableTreeNode createDynamicChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Dynamic Charts");
        root.add(createNode("demo.DynamicDataDemo1", "DynamicDataDemo1.java"));
        root.add(createNode("demo.DynamicDataDemo2", "DynamicDataDemo2.java"));
        root.add(createNode("demo.DynamicDataDemo3", "DynamicDataDemo3.java"));
        root.add(createNode("demo.MouseOverDemo1", "MouseOverDemo1.java"));
        return root;
    }

    private MutableTreeNode createItemLabelsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Item Labels");
        root.add(createNode("demo.ItemLabelDemo1", "ItemLabelDemo1.java"));
        root.add(createNode("demo.ItemLabelDemo2", "ItemLabelDemo2.java"));
        root.add(createNode("demo.ItemLabelDemo3", "ItemLabelDemo3.java"));
        root.add(createNode("demo.ItemLabelDemo4", "ItemLabelDemo4.java"));
        root.add(createNode("demo.ItemLabelDemo5", "ItemLabelDemo5.java"));
        return root;
    }

    private MutableTreeNode createLegendNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Legends");
        root.add(createNode("demo.LegendWrapperDemo1", "LegendWrapperDemo1.java"));
        return root;
    }

    private MutableTreeNode createMarkersNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Markers");
        root.add(createNode("demo.CategoryMarkerDemo1", "CategoryMarkerDemo1.java"));
        root.add(createNode("demo.CategoryMarkerDemo2", "CategoryMarkerDemo2.java"));
        root.add(createNode("demo.MarkerDemo1", "MarkerDemo1.java"));
        root.add(createNode("demo.MarkerDemo2", "MarkerDemo2.java"));
        return root;
    }

    private MutableTreeNode createOrientationNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Plot Orientation");
        root.add(createNode("demo.PlotOrientationDemo1", "PlotOrientationDemo1.java"));
        root.add(createNode("demo.PlotOrientationDemo2", "PlotOrientationDemo2.java"));
        return root;
    }

//    private MutableTreeNode createExperimentalNode() {
//        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Experimental");
//        return root;
//    }


    private void displayDescription(String fileName) {
        java.net.URL descriptionURL = SuperDemo.class.getResource(fileName);
        if (descriptionURL != null) {
            try {
                this.descriptionPane.setPage(descriptionURL);
            }
            catch (IOException e) {
                System.err.println("Attempted to read a bad URL: "
                        + descriptionURL);
            }
        }
        else {
            System.err.println("Couldn't find file: " + fileName);
        }

    }

    /**
     * Receives notification of tree selection events and updates the demo
     * display accordingly.
     *
     * @param event  the event.
     */
    public void valueChanged(TreeSelectionEvent event) {
        String sourceFilename = null;
        TreePath path = event.getPath();
        Object obj = path.getLastPathComponent();
        if (obj != null) {
            DefaultMutableTreeNode n = (DefaultMutableTreeNode) obj;
            Object userObj = n.getUserObject();
            if (userObj instanceof DemoDescription) {
                DemoDescription dd = (DemoDescription) userObj;
                sourceFilename = dd.getDescription();
                updateSourceCodePanel(sourceFilename);
                SwingUtilities.invokeLater(new DisplayDemo(this, dd));
            }
            else {
                this.chartContainer.removeAll();
                this.chartContainer.add(createNoDemoSelectedPanel());
                this.displayPanel.validate();
                displayDescription("select.html");
                updateSourceCodePanel(null);
            }
        }
        System.out.println(obj);
    }

    private JPanel createNoDemoSelectedPanel() {
        JPanel panel = new JPanel(new FlowLayout()) {

            /* (non-Javadoc)
             * @see javax.swing.JComponent#getToolTipText()
             */
            public String getToolTipText() {
                // TODO Auto-generated method stub
                return "(" + getWidth() + ", " + getHeight() + ")";
            }

        };
        ToolTipManager.sharedInstance().registerComponent(panel);
        panel.add(new JLabel("No demo selected"));
        panel.setPreferredSize(new Dimension(600, 400));
        return panel;
    }

    /**
     * Starting point for the JFreeChart Demo Collection.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        // let's see if Nimbus is available...
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            // ... otherwise just use the system look and feel
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        SuperDemo demo = new SuperDemo("JFreeChart 1.0.13 Demo Collection");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class DisplayDemo implements Runnable {

        private SuperDemo app;

        private DemoDescription demoDescription;

        /**
         * Creates a new runnable.
         *
         * @param app  the application.
         * @param d  the demo description.
         */
        public DisplayDemo(SuperDemo app, DemoDescription d) {
            this.app = app;
            this.demoDescription = d;
        }

        /**
         * Runs the task.
         */
        public void run() {
            try {
                Class c = Class.forName(this.demoDescription.getClassName());
                Method m = c.getDeclaredMethod("createDemoPanel",
                        (Class[]) null);
                JPanel panel = (JPanel) m.invoke(null, (Object[]) null);
                this.app.chartContainer.removeAll();
                this.app.chartContainer.add(panel);
                this.app.displayPanel.validate();
                String className = c.getName();
                String fileName = className;
                int i = className.lastIndexOf('.');
                if (i > 0) {
                    fileName = className.substring(i + 1);
                }
                fileName = fileName + ".html";
                this.app.displayDescription(fileName);

            }
            catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            }
            catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
            catch (IllegalAccessException e4) {
                e4.printStackTrace();
            }

        }

    }
}
