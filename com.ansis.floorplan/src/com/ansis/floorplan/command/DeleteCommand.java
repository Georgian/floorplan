package com.ansis.floorplan.command;

import org.eclipse.gef.commands.Command;

import com.ansis.floorplan.model.ModelTest;
import com.ansis.floorplan.model.Polly;


public class DeleteCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private ModelTest model;

	private ModelTest parentModel;


	// ==================== 6. Action Methods ====================

	@Override
	public void execute() {
		this.parentModel.removeChild(model);
	}

	@Override
	public void undo() {
		this.parentModel.addChild((Polly) model);
	}


	// ==================== 7. Getters & Setters ====================

	public void setModel(final Object model) {
		this.model = (ModelTest)model;
	}

	public void setParentModel(final Object model) {
		parentModel = (ModelTest)model;
	}

}