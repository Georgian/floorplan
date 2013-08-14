package com.ansis.floorplan.command;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.ansis.floorplan.model.ChildModel;
import com.ansis.floorplan.model.ModelTest;

public class RectangleCreateCommand extends Command {


	// ====================== 2. Instance Fields =============================

	private ModelTest canvas;

	private ChildModel rFigure;

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
			
		rFigure.setBounds(new Rectangle(location, size));
		rFigure.setR(new Rectangle(location, size));
		
		rFigure.setName("final");
		rFigure.setEtage(4);
		
		canvas.addChild(rFigure);
	}


	// ==================== 7. Getters & Setters ====================

	public void setRectangle(final ChildModel rFigure) {
		this.rFigure = rFigure;
	}

	public void setCanvas(final ModelTest canvas) {
		this.canvas = canvas;
	}

	public void setLocation(final Point location) {
		this.location = location;
	}
	
	public void setSize(final Dimension size) {
		this.size = size;
	}

}