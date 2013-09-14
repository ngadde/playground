package demo;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import org.jfree.chart.axis.AxisState;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.ui.RectangleEdge;

public class CustomLogAxis extends LogarithmicAxis {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2276383270804243809L;
	public CustomLogAxis(String label) {
		super(label);
		
	}
	public List refreshTicks(Graphics2D g2, AxisState state, 
            Rectangle2D dataArea, RectangleEdge edge) {
		List result = new java.util.ArrayList();
		return result;
	}
}
