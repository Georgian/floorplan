package com.ansis.floorplan.core.editpolicy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.PolylineShape;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.core.command.AbstractLayoutCommand;
import com.ansis.floorplan.core.command.CanvasChangeLayoutCommand;
import com.ansis.floorplan.core.command.PollyChangeLayoutCommand;
import com.ansis.floorplan.core.command.PollyLineChangeLayoutCommand;
import com.ansis.floorplan.core.command.PollyLineCreateCommand;
import com.ansis.floorplan.core.command.RectangleChangeLayoutCommand;
import com.ansis.floorplan.core.command.RectangleCreateCommand;
import com.ansis.floorplan.core.editpart.CanvasEditPart;
import com.ansis.floorplan.core.editpart.PollyEditPart;
import com.ansis.floorplan.core.editpart.PollyLineEditPart;
import com.ansis.floorplan.core.editpart.RectangleEditPart;
import com.ansis.floorplan.core.model.Canvas;
import com.ansis.floorplan.core.model.Polly;
import com.ansis.floorplan.core.model.PollyLine;
import com.ansis.floorplan.core.model.RectangleModel;
import com.ansis.floorplan.util.color.FPStandardColor;


public class EditLayoutPolicy extends XYLayoutEditPolicy {

	// ==================== 5. Creators ====================

	@Override
	protected Command createChangeConstraintCommand(final EditPart child, final Object constraint) {
		AbstractLayoutCommand command = null;

		if (child instanceof CanvasEditPart) {
			command = new CanvasChangeLayoutCommand();
		} else if (child instanceof PollyEditPart) {
			command = new PollyChangeLayoutCommand();
		} else if (child instanceof PollyLineEditPart) {
			command = new PollyLineChangeLayoutCommand();
		} else if (child instanceof RectangleEditPart) {
			command = new RectangleChangeLayoutCommand();
		}

		command.setModel(child.getModel());
		command.setConstraint((Rectangle)constraint);

		return command;
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	protected Command getCreateCommand(final CreateRequest request) {
		if (request.getType() == REQ_CREATE && getHost() instanceof CanvasEditPart) {

			if ( request.getNewObject() instanceof RectangleModel) {
				final RectangleCreateCommand command = new RectangleCreateCommand();

				command.setLocation(request.getLocation());
				command.setSize(request.getSize());
				command.setCanvas((Canvas) getHost().getModel());
				command.setRectangle((RectangleModel) request.getNewObject());

				return command;
			} else if ( request.getNewObject() instanceof PollyLine) {
				final PollyLineCreateCommand command = new PollyLineCreateCommand();

				command.setLocation(request.getLocation());
				command.setSize(request.getSize());
				command.setCanvas((Canvas) getHost().getModel());
				command.setPollyLine((PollyLine) request.getNewObject());

				return command;
			}
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

				if (child.getModel() instanceof Polly) {
					final PolygonShape p = new PolygonShape();
					final Polly polly = (Polly) child.getModel();
					p.setPoints(polly.getList());
					FigureUtilities.makeGhostShape(p);
					p.setForegroundColor( FloorplanActivator.getDefault().getColor(FPStandardColor.WHITE) );
					p.validate();
					addFeedback(p);
					return p;

				} else if (child.getModel() instanceof PollyLine) {
					final PolylineShape p = new PolylineShape();
					final PollyLine pollyLine = (PollyLine) child.getModel();
					p.setPoints(pollyLine.getList());
					FigureUtilities.makeGhostShape(p);
					p.setForegroundColor( FloorplanActivator.getDefault().getColor(FPStandardColor.WHITE) );
					p.validate();
					addFeedback(p);
					return p;
				} else if (child.getModel() instanceof RectangleModel) {
					final RectangleFigure r = new RectangleFigure();
					FigureUtilities.makeGhostShape(r);
					r.setForegroundColor( FloorplanActivator.getDefault().getColor(FPStandardColor.WHITE) );
					r.setBounds(getInitialFeedbackBounds());
					r.validate();
					addFeedback(r);
					return r;

				}
				return null;
			}
		}; 
	} 

}