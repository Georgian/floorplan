package com.ansis.floorplan.core.editpolicy.font;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.swt.graphics.RGB;

import com.ansis.floorplan.core.command.font.FontColorCommand;


public class FontColorPolicy extends AbstractEditPolicy {

	// ==================== 5. Creators ====================

	protected Command createFontColorCommand(final Request fontColorRequest) {
		final FontColorCommand command = new FontColorCommand();

		command.setModel(getHost().getModel()); 
		command.setNewFontColor((RGB)fontColorRequest.getExtendedData().get("newFontColor")); //$NON-NLS-1$

		return command;
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public Command getCommand(final Request request) {
		if (request.getType().equals("fontColor"))  //$NON-NLS-1$
			return createFontColorCommand(request);
		return null;
	}

}