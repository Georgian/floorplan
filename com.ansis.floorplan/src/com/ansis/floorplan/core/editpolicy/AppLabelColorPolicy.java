package com.ansis.floorplan.core.editpolicy;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.swt.graphics.RGB;

import com.ansis.floorplan.core.command.LabelColorCommand;


public class AppLabelColorPolicy extends AbstractEditPolicy {

	// ==================== 5. Creators ====================

	protected Command createChangeLabelColorCommand(final Request labelColorRequest) {
		final LabelColorCommand command = new LabelColorCommand();

		command.setModel(getHost().getModel()); 
		command.setNewLabelColor((RGB)labelColorRequest.getExtendedData().get("newLabelColor")); //$NON-NLS-1$

		return command;
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public Command getCommand(final Request request) {
		if (request.getType().equals("labelColor"))  //$NON-NLS-1$
			return createChangeLabelColorCommand(request);
		return null;
	}

}