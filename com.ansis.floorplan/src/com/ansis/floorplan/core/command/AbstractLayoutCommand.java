package com.ansis.floorplan.core.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;


public abstract class AbstractLayoutCommand extends Command {

	// ==================== 7. Getters & Setters ====================

	public abstract void setConstraint(Rectangle rect);
	public abstract void setModel(Object model);

}