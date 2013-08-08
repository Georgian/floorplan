package com.ansis.floorplan.command;

import org.eclipse.gef.commands.Command;

import com.ansis.floorplan.model.Polly;


public class FontSizeCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private Polly model;

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
		this.model = (Polly)model;
	}

	public void setNewFontSize(final String newFontSize) {
		this.newFontSize = newFontSize;
	}

}