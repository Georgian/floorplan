package com.ansis.floorplan.figure;

import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;


public class RectangleFigure extends PolygonShape {

	// ====================== 2. Instance Fields =============================

	private PointList list = new PointList();


	// ==================== 4. Constructors ====================

	public RectangleFigure() {
		setLayoutManager(new XYLayout());
		setLineWidth(1);
		setPoints(list);
		setBounds(new Rectangle(0, 0, 100, 100));
	}
	
}
