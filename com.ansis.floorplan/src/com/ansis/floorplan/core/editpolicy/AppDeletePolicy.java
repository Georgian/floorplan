package com.ansis.floorplan.core.editpolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.ansis.floorplan.core.command.DeleteCommand;


public class AppDeletePolicy extends ComponentEditPolicy {

	// ==================== 5. Creators ====================

	@Override
	protected Command createDeleteCommand(final GroupRequest deleteRequest) {
		final DeleteCommand command = new DeleteCommand();

		command.setModel(getHost().getModel());
		command.setParentModel(getHost().getParent().getModel());

		return command;
	}

}