package com.ansis.floorplan.core.command;

import org.eclipse.gef.commands.Command;

import com.ansis.floorplan.core.model.ChildModel;


public class OpacityCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private ChildModel model;

	private String oldOpacity;

	private String newOpacity;


	// ==================== 6. Action Methods ====================

	@Override
	public void execute() {
		this.oldOpacity = Integer.toString(model.getOpacity());
		final int realOpacity = (255 * Integer.parseInt(newOpacity))/100;
		this.model.setOpacity(realOpacity);
	}

	@Override
	public void undo() {
		this.model.setOpacity(Integer.parseInt(oldOpacity));
	}


	// ==================== 7. Getters & Setters ====================

	public void setModel(final Object model) {
		this.model = (ChildModel)model;
	}

	public void setNewOpacity(final String newOpacity) {
		this.newOpacity = newOpacity;
	}

}