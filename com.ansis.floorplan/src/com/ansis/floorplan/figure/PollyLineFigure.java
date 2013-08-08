package com.ansis.floorplan.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineShape;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;


public class PollyLineFigure extends PolylineShape implements IFigure {

	// ==================== 1. Static Fields ========================

	public static final int POLLYLINE_FIGURE_DEFWIDTH = 250;

	public static final int POLLYLINE_FIGURE_DEFHEIGHT = 150;


	// ====================== 2. Instance Fields =============================

	private PointList list = new PointList();

	private Rectangle r;

	
	// ==================== 4. Constructors ====================

	public PollyLineFigure(final Rectangle g) {

		final XYLayout layout = new XYLayout();
		setLayoutManager(layout);

		setForegroundColor(ColorConstants.green);
		setLineStyle(1);
		setLineWidth(1);

		setPoints(list);
	}


	// ==================== 7. Getters & Setters ====================

	public Rectangle getR() {
		return r;
	}

	public void setR(final Rectangle r) {
		this.r = r;
	}

	public void setLayout(final Rectangle rect) {
		getParent().setConstraint(this, rect);
	}

	public void setList(final PointList list) {
		setPoints(list);
		this.list = list;
	}

}