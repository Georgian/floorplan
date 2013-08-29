package com.ansis.floorplan.core.editpolicy.font;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

import com.ansis.floorplan.core.command.font.FontSizeCommand;


public class FontSizePolicy extends AbstractEditPolicy {

	// ==================== 5. Creators ====================

	protected Command createFontSizeCommand(final Request fontSizeRequest) {
		final FontSizeCommand command = new FontSizeCommand();

		command.setModel(getHost().getModel()); 
		command.setNewFontSize((String)fontSizeRequest.getExtendedData().get("newFontSize")); //$NON-NLS-1$

		return command;
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public Command getCommand(final Request request) {
		if (request.getType().equals("fontSize"))  //$NON-NLS-1$
			return createFontSizeCommand(request);
		return null;
	}

}