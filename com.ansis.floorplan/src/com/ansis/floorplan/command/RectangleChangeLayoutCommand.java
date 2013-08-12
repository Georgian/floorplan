package com.ansis.floorplan.command;


import org.eclipse.draw2d.geometry.Rectangle;

import com.ansis.floorplan.model.RectangleModel;


public class RectangleChangeLayoutCommand extends AbstractLayoutCommand {
	private RectangleModel model;
	private Rectangle layout;
	private Rectangle oldLayout;

	@Override
	public void execute() {
		model.setLayout(layout);
	}

	@Override
	public void setConstraint(final Rectangle rect) {
		this.layout = rect;
	}

	@Override
	public void setModel(final Object model) {
		this.model = (RectangleModel)model;
		this.oldLayout = ((RectangleModel)model).getLayout();
	}

	@Override
	public void undo() {
		this.model.setLayout(this.oldLayout);
		
	}
}