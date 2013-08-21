package com.ansis.floorplan.model;

import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;


public class Polly extends ChildModel {

	// ====================== 2. Instance Fields =============================

	private PointList list;

	private Rectangle labelPosition;


	// ==================== 7. Getters & Setters ====================

	public PointList getList() {
		return list;
	}

	public void setList(final PointList list) {
		this.list = list;
	}

	public Rectangle getLabelPosition() {
		return labelPosition;
	}

	public void setLabelPosition(final Rectangle labelPosition) {
		this.labelPosition = labelPosition;
	}

}