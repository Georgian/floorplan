package com.ansis.floorplan.command;

import org.eclipse.gef.commands.Command;

import com.ansis.floorplan.model.ChildModel;


public class FontSizeCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private ChildModel model;

	private String oldFontSize;

	private String newFontSize;


	// ==================== 6. Action Methods ====================

	@Override
	public void execute() {
		this.oldFontSize = Integer.toString(model.getFontSize());
		this.model.setFontSize(Integer.parseInt(newFontSize));
	}

	@Override
	public void undo() {
		this.model.setFontSize(Integer.parseInt(oldFontSize));
	}


	// ==================== 7. Getters & Setters ====================

	public void setModel(final Object model) {
		this.model = (ChildModel)model;
	}

	public void setNewFontSize(final String newFontSize) {
		this.newFontSize = newFontSize;
	}

}