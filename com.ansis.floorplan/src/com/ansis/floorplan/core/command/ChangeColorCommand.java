package com.ansis.floorplan.core.command;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;

import com.ansis.floorplan.core.model.ChildModel;


public class ChangeColorCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private ChildModel model;

	private RGB oldColor;

	@SuppressWarnings("unused")
	private RGB newColor;


	// ==================== 6. Action Methods ====================

	@Override
	public void execute() {
		this.oldColor = model.getColor();
		//		this.model.setColor(newColor);

		// Create the color-change dialog
		final ColorDialog dlg = new ColorDialog(Display.getCurrent().getActiveShell());

		// Change the title bar text
		dlg.setText("Choose a color..."); //$NON-NLS-1$

		// Open the dialog and retrieve the selected color
		final RGB rgb = dlg.open();

		if (rgb != null) 
			model.setColor(rgb);

	}

	@Override
	public void undo() {
		this.model.setColor(oldColor);
	}


	// ==================== 7. Getters & Setters ====================

	public void setModel(final Object model) {
		this.model = (ChildModel)model;
	}

	public void setNewChangeColor(final RGB newColor) {
		this.newColor = newColor;
	}

}