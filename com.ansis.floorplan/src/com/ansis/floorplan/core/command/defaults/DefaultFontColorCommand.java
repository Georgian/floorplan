package com.ansis.floorplan.core.command.defaults;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.RGB;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.color.FPStandardColor;


public class DefaultFontColorCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private ChildModel model;

	private RGB oldFontColor;

	@SuppressWarnings("unused")
	private RGB newDefaultFontColor;


	// ==================== 6. Action Methods ====================

	@Override
	public void execute() {
		this.oldFontColor = model.getFontColor();

		final RGB rgb = FloorplanActivator.getDefault().getColor(FPStandardColor.BLACK).getRGB();

		model.setFontColor(rgb);
	}

	@Override
	public void undo() {
		this.model.setFontColor(oldFontColor);
	}


	// ==================== 7. Getters & Setters ====================

	public void setModel(final Object model) {
		this.model = (ChildModel)model;
	}

	public void setNewDefaultFontColor(final RGB newDefaultFontColor) {
		this.newDefaultFontColor = newDefaultFontColor;
	}

}