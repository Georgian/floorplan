package com.ansis.floorplan.core.editpolicy;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.swt.graphics.RGB;

import com.ansis.floorplan.core.command.FigureColorCommand;


public class AppFigureColorPolicy extends AbstractEditPolicy {

	// ==================== 5. Creators ====================

	protected Command createChangeColorCommand(final Request figureColorRequest) {
		final FigureColorCommand command = new FigureColorCommand();

		command.setModel(getHost().getModel()); 
		command.setNewFigureColor((RGB)figureColorRequest.getExtendedData().get("newFigureColor")); //$NON-NLS-1$

		return command;
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public Command getCommand(final Request request) {
		if (request.getType().equals("figureColor"))  //$NON-NLS-1$
			return createChangeColorCommand(request);
		return null;
	}

}