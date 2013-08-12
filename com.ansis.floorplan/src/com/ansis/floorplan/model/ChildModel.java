package com.ansis.floorplan.model;

import org.eclipse.draw2d.geometry.Rectangle;

public class ChildModel extends ModelTest {

	// ====================== 2. Instance Fields =============================

	private ModelTest parent;

	private Rectangle bounds;


	// ==================== 7. Getters & Setters ====================

	public void setParent(final ModelTest parent) {
		this.parent = parent;
	}

	public ModelTest getParent() {
		return parent;
	}

	public void setBounds(final Rectangle bounds){
		this.bounds = bounds;
	}

	public Rectangle getBounds(){
		return bounds;
	}

}
