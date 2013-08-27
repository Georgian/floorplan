package com.ansis.floorplan.core.command;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.ansis.floorplan.core.model.Canvas;
import com.ansis.floorplan.core.model.PollyLine;


public class PollyLineCreateCommand extends Command {


	// ====================== 2. Instance Fields =============================

	private Canvas canvas;

	private PollyLine pollyLine;

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

		pollyLine.setBounds(new Rectangle(location, size));
		pollyLine.setLayout(new Rectangle(location, size));
		final Point p = new Point();
		p.x = size.width;
		p.y = size.height;
		
		final PointList pointList = new PointList(new int[] {location.x, location.y, p.x, p.y});
		pollyLine.setList(pointList);

		canvas.addChild(pollyLine);
	}



	// ==================== 7. Getters & Setters ====================

	public void setPollyLine(final PollyLine pollyLine) {
		this.pollyLine = pollyLine;
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