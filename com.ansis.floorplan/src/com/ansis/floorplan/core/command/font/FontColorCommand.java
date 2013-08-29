package com.ansis.floorplan.core.command.font;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;

import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.FPConstPresentation;


public class FontColorCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private ChildModel model;

	private RGB oldFontColor;

	@SuppressWarnings("unused")
	private RGB newFontColor;


	// ==================== 6. Action Methods ====================

	@Override
	public void execute() {
		this.oldFontColor = model.getFontColor();

		// Create the color-change dialog
		final ColorDialog dlg = new ColorDialog(Display.getCurrent().getActiveShell());

		// Change the title bar text
		dlg.setText("Choose a font color" + FPConstPresentation.ELIPSES); //$NON-NLS-1$

		// Open the dialog and retrieve the selected color
		final RGB rgb = dlg.open();

		if (rgb != null) 
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

	public void setNewFontColor(final RGB newFontColor) {
		this.newFontColor = newFontColor;
	}

}