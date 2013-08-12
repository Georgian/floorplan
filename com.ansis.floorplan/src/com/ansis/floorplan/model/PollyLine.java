package com.ansis.floorplan.model;

import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;


public class PollyLine extends ChildModel {

	// ==================== 1. Static Fields ========================

	public static final String PROPERTY_RENAME = "PollyRename"; //$NON-NLS-1$

	public static final String PROPERTY_COLOR = "PollyColor"; //$NON-NLS-1$
	
	public static final String PROPERTY_OPACITY = "PollyOpacity"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private PointList list;

	private Rectangle r;

	private Rectangle g;

	private Color color;
	
	private int opacity;


	// ==================== 3. Static Methods ====================

	private static Color defaultColor() {
		return new Color(null, 0, 0, 192);
	}
	private static int defaultOpacity() {
		return 127;
	}


	// ==================== 4. Constructors ====================

	public PollyLine() {
		this.setColor(defaultColor());
		this.setOpacity(defaultOpacity());
	}


	// ==================== 7. Getters & Setters ====================

	public PointList getList() {
		return list;
	}

	public void setList(final PointList list) {
		this.list = list;
	}

	public Rectangle getR() {
		return r;
	}

	public void setR(final Rectangle r) {
		this.r = r;
	}

	public Rectangle getG() {
		return g;
	}

	public void setG(final Rectangle g) {
		this.g = g;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(final Color color) {
		final Color oldColor = this.color;
		this.color = color;
		getListeners().firePropertyChange(PROPERTY_COLOR, oldColor, this.color);
	}

	public int getOpacity() {
		return opacity;
	}

	public void setOpacity(final int opacity) {
		final int oldOpacity = this.opacity;
		this.opacity = opacity;
		getListeners().firePropertyChange(PROPERTY_OPACITY, oldOpacity, this.opacity);
	}

}