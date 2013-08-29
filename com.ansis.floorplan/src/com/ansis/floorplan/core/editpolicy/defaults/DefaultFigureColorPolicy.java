package com.ansis.floorplan.core.editpolicy.defaults;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.swt.graphics.RGB;

import com.ansis.floorplan.core.command.defaults.DefaultFigureColorCommand;


public class DefaultFigureColorPolicy extends AbstractEditPolicy {

	// ==================== 5. Creators ====================

	protected Command createDefaultFigureColorCommand(final Request defaultFigureColorRequest) {
		final DefaultFigureColorCommand command = new DefaultFigureColorCommand();

		command.setModel(getHost().getModel()); 
		command.setNewDefaultFigureColor((RGB)defaultFigureColorRequest.getExtendedData().get("newDefaultFigureColor")); //$NON-NLS-1$

		return command;
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public Command getCommand(final Request request) {
		if (request.getType().equals("defaultFigureColor"))  //$NON-NLS-1$
			return createDefaultFigureColorCommand(request);
		return null;
	}

}