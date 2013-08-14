package com.ansis.floorplan.model;

import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;


public class Polly extends ChildModel {


	// ====================== 2. Instance Fields =============================

	private PointList list;

	private Rectangle g;


	// ==================== 7. Getters & Setters ====================

	public PointList getList() {
		return list;
	}

	public void setList(final PointList list) {
		this.list = list;
	}

	public Rectangle getG() {
		return g;
	}

	public void setG(final Rectangle g) {
		this.g = g;
	}

}