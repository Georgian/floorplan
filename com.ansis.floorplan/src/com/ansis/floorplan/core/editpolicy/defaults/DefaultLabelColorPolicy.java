package com.ansis.floorplan.core.editpolicy.defaults;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.swt.graphics.RGB;

import com.ansis.floorplan.core.command.defaults.DefaultLabelColorCommand;


public class DefaultLabelColorPolicy extends AbstractEditPolicy {

	// ==================== 5. Creators ====================

	protected Command createDefaultLabelColorCommand(final Request defaultLabelColorRequest) {
		final DefaultLabelColorCommand command = new DefaultLabelColorCommand();

		command.setModel(getHost().getModel()); 
		command.setNewDefaultLabelColor((RGB)defaultLabelColorRequest.getExtendedData().get("newDefaultLabelColor")); //$NON-NLS-1$

		return command;
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public Command getCommand(final Request request) {
		if (request.getType().equals("defaultLabelColor"))  //$NON-NLS-1$
			return createDefaultLabelColorCommand(request);
		return null;
	}

}