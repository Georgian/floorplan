package com.ansis.floorplan.core.command;

import org.eclipse.gef.commands.Command;

import com.ansis.floorplan.core.model.ChildModel;


public class FontStyleCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private ChildModel model;

	private String oldFontStyle;

	private String newFontStyle;


	// ==================== 6. Action Methods ====================

	@Override
	public void execute() {
		this.oldFontStyle = Integer.toString(model.getFontStyle());
		this.model.setFontStyle(Integer.parseInt(newFontStyle));
	}

	@Override
	public void undo() {
		this.model.setFontStyle(Integer.parseInt(oldFontStyle));
	}


	// ==================== 7. Getters & Setters ====================

	public void setModel(final Object model) {
		this.model = (ChildModel)model;
	}

	public void setNewFontStyle(final String newFontStyle) {
		this.newFontStyle = newFontStyle;
	}

}