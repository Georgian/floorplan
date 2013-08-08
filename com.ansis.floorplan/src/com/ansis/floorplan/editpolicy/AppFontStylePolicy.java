package com.ansis.floorplan.editpolicy;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

import com.ansis.floorplan.command.FontStyleCommand;


public class AppFontStylePolicy extends AbstractEditPolicy {

	// ==================== 5. Creators ====================

	protected Command createFontStyleCommand(final Request fontStyleRequest) {
		final FontStyleCommand command = new FontStyleCommand();

		command.setModel(getHost().getModel()); 
		command.setNewFontStyle((String)fontStyleRequest.getExtendedData().get("newFontStyle")); //$NON-NLS-1$

		return command;
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public Command getCommand(final Request request) {
		if (request.getType().equals("fontStyle"))  //$NON-NLS-1$
			return createFontStyleCommand(request);
		return null;
	}
}