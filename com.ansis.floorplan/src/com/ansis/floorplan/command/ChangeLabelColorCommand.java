package com.ansis.floorplan.command;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;

import com.ansis.floorplan.model.ChildModel;


public class ChangeLabelColorCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private ChildModel model;

	private RGB oldLabelColor;

	@SuppressWarnings("unused")
	private RGB newLabelColor;


	// ==================== 6. Action Methods ====================

	@Override
	public void execute() {
		this.oldLabelColor = model.getLabelColor();
		//		this.model.setColor(newColor);

		// Create the color-change dialog
		final ColorDialog dlg = new ColorDialog(Display.getCurrent().getActiveShell());

		// Change the title bar text
		dlg.setText("Choose a font background color..."); //$NON-NLS-1$

		// Open the dialog and retrieve the selected color
		final RGB rgb = dlg.open();

		if (rgb != null) 
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

	public void setNewChangeLabelColor(final RGB newLabelColor) {
		this.newLabelColor = newLabelColor;
	}

}