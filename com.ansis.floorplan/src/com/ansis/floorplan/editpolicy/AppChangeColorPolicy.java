package com.ansis.floorplan.editpolicy;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.swt.graphics.RGB;

import com.ansis.floorplan.command.ChangeColorCommand;


public class AppChangeColorPolicy extends AbstractEditPolicy {

	// ==================== 5. Creators ====================

	protected Command createChangeColorCommand(final Request changeColorRequest) {
		final ChangeColorCommand command = new ChangeColorCommand();

		command.setModel(getHost().getModel()); 
		command.setNewChangeColor((RGB)changeColorRequest.getExtendedData().get("newColor")); //$NON-NLS-1$

		return command;
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public Command getCommand(final Request request) {
		if (request.getType().equals("changeColor"))  //$NON-NLS-1$
			return createChangeColorCommand(request);
		return null;
	}

}