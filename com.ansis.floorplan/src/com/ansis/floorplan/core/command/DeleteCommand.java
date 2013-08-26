package com.ansis.floorplan.core.command;

import org.eclipse.gef.commands.Command;

import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;


public class DeleteCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private ChildModel model;

	private CanvasModel parentModel;


	// ==================== 6. Action Methods ====================

	@Override
	public void execute() {
		this.parentModel.removeChild(model);
	}

	@Override
	public void undo() {
		this.parentModel.addChild(model);
	}


	// ==================== 7. Getters & Setters ====================

	public void setModel(final Object model) {
		this.model = (ChildModel)model;
	}

	public void setParentModel(final Object model) {
		parentModel = (CanvasModel)model;
	}

}