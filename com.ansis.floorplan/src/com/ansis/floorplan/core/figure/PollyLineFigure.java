package com.ansis.floorplan.core.figure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineShape;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.RGB;


public class PollyLineFigure extends PolylineShape implements IFigure {

	// ====================== 2. Instance Fields =============================

	private PointList list = new PointList();

	private RGB lineColor;


	// ==================== 4. Constructors ====================

	public PollyLineFigure() {

		final XYLayout layout = new XYLayout();
		setLayoutManager(layout);

		setLineStyle(1);
		setLineWidth(3);

		setPoints(list);
	}


	// ==================== 7. Getters & Setters ====================

	public RGB getLineColor() {
		return lineColor;
	}

	public void setLineColor(final RGB lineColor) {
		this.lineColor = lineColor;
	}

	public void setLayout(final Rectangle rect) {
		getParent().setConstraint(this, rect);
	}

	public void setList(final PointList list) {
		setPoints(list);
		this.list = list;
	}

}