package com.ansis.floorplan.core.model;

import org.eclipse.draw2d.geometry.PointList;


public class Polly extends ChildModel {

	// ====================== 2. Instance Fields =============================

	private PointList list;


	// ==================== 7. Getters & Setters ====================

	public PointList getList() {
		return list;
	}

	public void setList(final PointList list) {
		this.list = list;
	}

}