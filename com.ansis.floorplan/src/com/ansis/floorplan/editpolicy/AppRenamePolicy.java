package com.ansis.floorplan.editpolicy;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

import com.ansis.floorplan.command.RenameCommand;


public class AppRenamePolicy extends AbstractEditPolicy {

	// ==================== 5. Creators ====================

	protected Command createRenameCommand(final Request renameRequest) {
		final RenameCommand command = new RenameCommand();
		command.setModel(getHost().getModel()); 
		command.setNewName((String)renameRequest.getExtendedData().get("newName")); //$NON-NLS-1$
		return command;
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public Command getCommand(final Request request) {
		if (request.getType().equals("rename"))  //$NON-NLS-1$
			return createRenameCommand(request);
		return null;
	}

}