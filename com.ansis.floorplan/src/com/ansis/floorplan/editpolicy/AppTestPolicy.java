package com.ansis.floorplan.editpolicy;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

import com.ansis.floorplan.command.TestCommand;


public class AppTestPolicy extends AbstractEditPolicy {

	// ==================== 5. Creators ====================

	protected Command createTestCommand(final Request testRequest) {
		final TestCommand command = new TestCommand();

		command.setModel(getHost().getModel()); 
		command.setNewOpacity((String)testRequest.getExtendedData().get("newOpacity")); //$NON-NLS-1$

		return command;
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public Command getCommand(final Request request) {
		if (request.getType().equals("test"))  //$NON-NLS-1$
			return createTestCommand(request);

		return null;
	}
}