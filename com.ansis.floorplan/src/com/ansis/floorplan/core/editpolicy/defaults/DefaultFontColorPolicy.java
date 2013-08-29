package com.ansis.floorplan.core.editpolicy.defaults;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.swt.graphics.RGB;

import com.ansis.floorplan.core.command.defaults.DefaultFontColorCommand;


public class DefaultFontColorPolicy extends AbstractEditPolicy {

	// ==================== 5. Creators ====================

	protected Command createDefaultFontColorCommand(final Request defaultFontColorRequest) {
		final DefaultFontColorCommand command = new DefaultFontColorCommand();

		command.setModel(getHost().getModel()); 
		command.setNewDefaultFontColor((RGB)defaultFontColorRequest.getExtendedData().get("newDefaultFontColor")); //$NON-NLS-1$

		return command;
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public Command getCommand(final Request request) {
		if (request.getType().equals("defaultFontColor"))  //$NON-NLS-1$
			return createDefaultFontColorCommand(request);
		return null;
	}

}