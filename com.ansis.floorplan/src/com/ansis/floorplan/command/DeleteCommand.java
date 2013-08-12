package com.ansis.floorplan.command;

import org.eclipse.gef.commands.Command;

import com.ansis.floorplan.model.ChildModel;
import com.ansis.floorplan.model.ModelTest;
import com.ansis.floorplan.model.Polly;


public class DeleteCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private ChildModel model;

	private ModelTest parentModel;


	// ==================== 6. Action Methods ====================

	@Override
	public void execute() {
		this.parentModel.removeChild(model);
	}

	@Override
	public void undo() {
		this.parentModel.addChild((ChildModel) model);
	}


	// ==================== 7. Getters & Setters ====================

	public void setModel(final Object model) {
		this.model = (ChildModel)model;
	}

	public void setParentModel(final Object model) {
		parentModel = (ModelTest)model;
	}

}