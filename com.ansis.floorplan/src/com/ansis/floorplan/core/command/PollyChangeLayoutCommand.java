package com.ansis.floorplan.core.command;

import org.eclipse.draw2d.geometry.Rectangle;

import com.ansis.floorplan.core.model.ChildModel;


public class PollyChangeLayoutCommand extends AbstractLayoutCommand {

	// ====================== 2. Instance Fields =============================

	private ChildModel model;

	private Rectangle layout;

	private Rectangle oldLayout;


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
		this.model = (ChildModel)model;
		this.oldLayout = ((ChildModel)model).getLayout();
	}

}