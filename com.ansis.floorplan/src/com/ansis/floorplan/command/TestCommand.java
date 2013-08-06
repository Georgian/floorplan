package com.ansis.floorplan.command;

import org.eclipse.gef.commands.Command;

import com.ansis.floorplan.model.Polly;


public class TestCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private Polly model;

	private String oldOpacity;

	private String newOpacity;


	// ==================== 6. Action Methods ====================

	@Override
	public void execute() {
		this.oldOpacity = model.getName(); 
		this.model.setOpacity(Float.parseFloat(newOpacity));
	}

	@Override
	public void undo() {
		this.model.setName(oldOpacity);
	}


	// ==================== 7. Getters & Setters ====================

	public void setModel(final Object model) {
		this.model = (Polly)model;
	}

	public void setNewOpacity(final String newOpacity) {
		this.newOpacity = newOpacity;
	}

}