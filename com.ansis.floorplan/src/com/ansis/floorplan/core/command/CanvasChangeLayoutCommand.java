package com.ansis.floorplan.core.command;

import org.eclipse.draw2d.geometry.Rectangle;

import com.ansis.floorplan.core.model.CanvasModel;


public class CanvasChangeLayoutCommand  extends AbstractLayoutCommand {

	// ====================== 2. Instance Fields =============================

	private CanvasModel model;

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
		this.model = (CanvasModel)model;
		this.oldLayout = ((CanvasModel)model).getLayout();
	}

}