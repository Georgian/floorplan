package com.ansis.floorplan.editpolicy;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.swt.graphics.RGB;

import com.ansis.floorplan.command.ChangeLabelColorCommand;


public class AppChangeLabelColorPolicy extends AbstractEditPolicy {

	// ==================== 5. Creators ====================

	protected Command createChangeLabelColorCommand(final Request changeLabelColorRequest) {
		final ChangeLabelColorCommand command = new ChangeLabelColorCommand();

		command.setModel(getHost().getModel()); 
		command.setNewChangeLabelColor((RGB)changeLabelColorRequest.getExtendedData().get("newLabelColor")); //$NON-NLS-1$

		return command;
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public Command getCommand(final Request request) {
		if (request.getType().equals("changeLabelColor"))  //$NON-NLS-1$
			return createChangeLabelColorCommand(request);
		return null;
	}
}