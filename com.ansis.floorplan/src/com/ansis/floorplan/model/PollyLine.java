package com.ansis.floorplan.model;

import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.RGB;


public class PollyLine extends ChildModel {

	// ==================== 1. Static Fields ========================

	public static final String PROPERTY_RENAME = "PollyLineRename"; //$NON-NLS-1$

	public static final String PROPERTY_COLOR = "PollyLineColor"; //$NON-NLS-1$

	public static final String PROPERTY_OPACITY = "PollyLineOpacity"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private PointList list;

	private Rectangle r;

	private Rectangle g;

	private RGB lineColor;

	private int opacity;


	// ==================== 3. Static Methods ====================

	private static RGB defaultLineColor() {
		return new RGB(0, 0, 255);
	}

	private static int defaultOpacity() {
		return 127;
	}


	// ==================== 4. Constructors ====================

	public PollyLine() {
		this.setLineColor(defaultLineColor());
		this.setOpacity(defaultOpacity());
	}


	// ==================== 7. Getters & Setters ====================

	public PointList getList() {
		return list;
	}

	public void setList(final PointList list) {
		this.list = list;
	}

	@Override
	public Rectangle getR() {
		return r;
	}

	@Override
	public void setR(final Rectangle r) {
		this.r = r;
	}

	public Rectangle getG() {
		return g;
	}

	public void setG(final Rectangle g) {
		this.g = g;
	}

	@Override
	public RGB getLineColor() {
		return lineColor;
	}

	@Override
	public void setLineColor(final RGB lineColor) {
		this.lineColor = lineColor;
	}

	@Override
	public int getOpacity() {
		return opacity;
	}

	@Override
	public void setOpacity(final int opacity) {
		final int oldOpacity = this.opacity;
		this.opacity = opacity;
		getListeners().firePropertyChange(PROPERTY_OPACITY, oldOpacity, this.opacity);
	}

}