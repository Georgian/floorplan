package com.ansis.floorplan.editpolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.ansis.floorplan.command.RectangleCreateCommand;
import com.ansis.floorplan.model.Canvas;
import com.ansis.floorplan.model.RectangleModel;

/**
 *
 * @author ggrec
 *
 */
public class RectangleCreateEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command getCreateCommand(final CreateRequest request) {

		final RectangleCreateCommand command = new RectangleCreateCommand();

		//		System.out.println(request.getSize());
		//		System.out.println(request.getLocation());

		command.setLocation(request.getLocation());
		command.setSize(request.getSize());
		command.setCanvas((Canvas) getHost().getModel());
		command.setPolly((RectangleModel) request.getNewObject());

		return command;
	}

}
