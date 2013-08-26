package com.ansis.floorplan.editpolicy;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

import com.ansis.floorplan.command.OpacityCommand;


public class AppOpacityPolicy extends AbstractEditPolicy {

	// ==================== 5. Creators ====================

	protected Command createOpacityCommand(final Request opacityRequest) {
		final OpacityCommand command = new OpacityCommand();

		command.setModel(getHost().getModel()); 
		command.setNewOpacity((String)opacityRequest.getExtendedData().get("newOpacity")); //$NON-NLS-1$

		return command;
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public Command getCommand(final Request request) {
		if (request.getType().equals("opacity"))  //$NON-NLS-1$
			return createOpacityCommand(request);
		return null;
	}

}