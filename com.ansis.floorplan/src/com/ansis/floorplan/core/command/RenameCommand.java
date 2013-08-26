package com.ansis.floorplan.core.command;

import org.eclipse.gef.commands.Command;

import com.ansis.floorplan.core.model.ChildModel;


public class RenameCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private ChildModel model;

	private String oldName;

	private String newName;


	// ==================== 6. Action Methods ====================

	@Override
	public void execute() {
		this.oldName = model.getName(); 
		this.model.setName(newName);
	}

	@Override
	public void undo() {
		this.model.setName(oldName);
	}


	// ==================== 7. Getters & Setters ====================

	public void setModel(final Object model) {
		this.model = (ChildModel)model;
	}

	public void setNewName(final String newName) {
		this.newName = newName;
	}

}