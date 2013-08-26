package com.ansis.floorplan.core.command;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.ansis.floorplan.core.model.Canvas;
import com.ansis.floorplan.core.model.RectangleModel;


public class RectangleCreateCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private Canvas canvas;

	private RectangleModel rectModel;

	private Point location;

	private Dimension size;


	// ==================== 6. Action Methods ====================

	@Override
	public boolean canExecute() {
		return true;
	}

	@Override
	public void execute() {

		if (size == null)
			return;

		rectModel.setBounds(new Rectangle(location, size));
		rectModel.setLayout(new Rectangle(location, size));
		
		rectModel.setLabelPosition(new Rectangle(location, size));

		canvas.addChild(rectModel);
	}


	// ==================== 7. Getters & Setters ====================

	public void setRectangle(final RectangleModel rectModel) {
		this.rectModel = rectModel;
	}

	public void setCanvas(final Canvas canvas) {
		this.canvas = canvas;
	}

	public void setLocation(final Point location) {
		this.location = location;
	}

	public void setSize(final Dimension size) {
		this.size = size;
	}

}