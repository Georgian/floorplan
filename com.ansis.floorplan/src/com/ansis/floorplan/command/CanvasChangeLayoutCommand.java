package com.ansis.floorplan.command;

import org.eclipse.draw2d.geometry.Rectangle;

import com.ansis.floorplan.model.Canvas;


public class CanvasChangeLayoutCommand  extends AbstractLayoutCommand {

	// ====================== 2. Instance Fields =============================

	private Canvas model;

	private Rectangle oldLayout;

	private Rectangle layout;


	// ==================== 6. Action Methods ====================

	@Override
	public void execute() {
		model.setLayout(layout);
	}

	@Override
	public void undo() {
		this.model.setLayout(this.oldLayout);
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public void setConstraint(final Rectangle rect) {
		this.layout = rect;
	}

	@Override
	public void setModel(final Object model) {
		this.model = (Canvas)model;
		this.oldLayout = ((Canvas)model).getLayout();
	}
	
}