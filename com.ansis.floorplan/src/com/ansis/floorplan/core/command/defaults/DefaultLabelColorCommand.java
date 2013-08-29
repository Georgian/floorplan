package com.ansis.floorplan.core.command.defaults;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.RGB;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.color.FPStandardColor;


public class DefaultLabelColorCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private ChildModel model;

	private RGB oldLabelColor;

	@SuppressWarnings("unused")
	private RGB newDefaultLabelColor;


	// ==================== 6. Action Methods ====================

	@Override
	public void execute() {
		this.oldLabelColor = model.getLabelColor();

		final RGB rgb = FloorplanActivator.getDefault().getColor(FPStandardColor.WHITE).getRGB();

		model.setLabelColor(rgb);
	}

	@Override
	public void undo() {
		this.model.setLabelColor(oldLabelColor);
	}


	// ==================== 7. Getters & Setters ====================

	public void setModel(final Object model) {
		this.model = (ChildModel)model;
	}

	public void setNewDefaultLabelColor(final RGB newDefaultLabelColor) {
		this.newDefaultLabelColor = newDefaultLabelColor;
	}

}