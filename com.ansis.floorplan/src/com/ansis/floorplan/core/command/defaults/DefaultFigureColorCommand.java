package com.ansis.floorplan.core.command.defaults;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.RGB;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.color.FPStandardColor;


public class DefaultFigureColorCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private ChildModel model;

	private RGB oldFigureColor;

	@SuppressWarnings("unused")
	private RGB newDefaultFigureColor;


	// ==================== 6. Action Methods ====================

	@Override
	public void execute() {
		this.oldFigureColor = model.getColor();

		final RGB rgb = FloorplanActivator.getDefault().getColor(FPStandardColor.BLUE_NAVY).getRGB();

		model.setColor(rgb);
	}

	@Override
	public void undo() {
		this.model.setColor(oldFigureColor);
	}


	// ==================== 7. Getters & Setters ====================

	public void setModel(final Object model) {
		this.model = (ChildModel)model;
	}

	public void setNewDefaultFigureColor(final RGB newDefaultFigureColor) {
		this.newDefaultFigureColor = newDefaultFigureColor;
	}

}