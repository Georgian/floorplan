package com.ansis.floorplan.editpolicy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.ansis.floorplan.command.AbstractLayoutCommand;
import com.ansis.floorplan.command.CanvasChangeLayoutCommand;
import com.ansis.floorplan.command.PollyChangeLayoutCommand;
import com.ansis.floorplan.command.PollyLineChangeLayoutCommand;
import com.ansis.floorplan.command.RectangleChangeLayoutCommand;
import com.ansis.floorplan.command.RectangleCreateCommand;
import com.ansis.floorplan.editpart.CanvasEditPart;
import com.ansis.floorplan.editpart.PollyEditPart;
import com.ansis.floorplan.editpart.PollyLineEditPart;
import com.ansis.floorplan.editpart.RectangleEditPart;
import com.ansis.floorplan.model.Canvas;
import com.ansis.floorplan.model.RectangleModel;


public class AppEditLayoutPolicy extends XYLayoutEditPolicy {

	// ==================== 5. Creators ====================

	@Override
	protected Command createChangeConstraintCommand(final EditPart child, final Object constraint) {
		AbstractLayoutCommand command = null;

		if (child instanceof CanvasEditPart) {
			command = new CanvasChangeLayoutCommand();
		}
		else if (child instanceof PollyEditPart) {
			command = new PollyChangeLayoutCommand();
		}
		else if (child instanceof PollyLineEditPart) {
			command = new PollyLineChangeLayoutCommand();
		}
		else if (child instanceof RectangleEditPart) {
			command = new RectangleChangeLayoutCommand();
		}

		command.setModel(child.getModel());
		command.setConstraint((Rectangle)constraint);

		return command;
	}


	// ==================== 7. Getters & Setters ====================

//	protected Command getCreateCommand(final CreateRequest request) {
//		if (request.getType() == REQ_CREATE && getHost() instanceof CanvasEditPart) {
//			final PollyCreateCommand cmd = new PollyCreateCommand();
//
//			cmd.setCanvas(getHost().getModel());
//			cmd.setPolly(request.getNewObject());
//
//			final Rectangle constraint = (Rectangle)getConstraintFor(request);
//
//			constraint.x = (constraint.x < 0) ? 0 : constraint.x;
//			constraint.y = (constraint.y < 0) ? 0 : constraint.y;
//			constraint.width = (constraint.width <= 0) ? PollyFigure.POLLY_FIGURE_DEFWIDTH : constraint.width;
//			constraint.height = (constraint.height <= 0) ? PollyFigure.POLLY_FIGURE_DEFHEIGHT : constraint.height;
//
//			cmd.setLayout(constraint);
//
//			return cmd;
//		}
//
//		return null;
//	}
	
	@Override
	protected Command getCreateCommand(final CreateRequest request) {
		if (request.getType() == REQ_CREATE && getHost() instanceof CanvasEditPart) {
			final RectangleCreateCommand command = new RectangleCreateCommand();

			//		System.out.println(request.getSize());
			//		System.out.println(request.getLocation());

			command.setLocation(request.getLocation());
			command.setSize(request.getSize());
			command.setCanvas((Canvas) getHost().getModel());
			command.setRectangle((RectangleModel) request.getNewObject());

			return command;
		}
		return null;
	}
		// ==================== 9. Convenience Methods ====================

		@Override 
		protected EditPolicy createChildEditPolicy(final EditPart child) { 
			return new NonResizableEditPolicy() {
				@SuppressWarnings("rawtypes")
				@Override
				protected List createSelectionHandles() {
					return new ArrayList<>();
				}
				@Override
				protected IFigure createDragSourceFeedbackFigure() {
					// Use a ghost rectangle for feedback
					final RectangleFigure r = new RectangleFigure();
					FigureUtilities.makeGhostShape(r);
					r.setLineStyle(Graphics.LINE_DOT);
					r.setForegroundColor(ColorConstants.white);
					r.setBounds(getInitialFeedbackBounds());
					r.validate();
					addFeedback(r);
					return r;
				}
			}; 
		} 

	}