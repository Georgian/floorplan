package com.ansis.floorplan.core.model;

import org.eclipse.draw2d.geometry.PointList;


public class PollyLine extends ChildModel {

	// ====================== 2. Instance Fields =============================

	private PointList list;

	private boolean b;


	// ==================== 7. Getters & Setters ====================

	public PointList getList() {
		return list;
	}

	public void setList(final PointList list) {
		this.list = list;
	}

	public void setDrawingDenied(final boolean b) {
		this.b = b;
	}

	public boolean isDrawingDenied() {
		return b;
	}

}