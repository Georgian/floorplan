package com.ansis.floorplan.core.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.tools.DragEditPartsTracker;

import com.ansis.floorplan.core.command.PollyLineCreateCommand;
import com.ansis.floorplan.core.editpolicy.EditLayoutPolicy;
import com.ansis.floorplan.core.figure.CanvasFigure;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;


public class CanvasEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

	protected static final String DEFAULT_FIGURE_COLOR_EDIT_POLICY = "defaultFigureColor"; //$NON-NLS-1$

	
	// ==================== 5. Creators ====================

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new EditLayoutPolicy());

	}

	@Override
	protected IFigure createFigure() {
		final IFigure figure = new CanvasFigure();
		return figure;
	}

	// This is an experimental way of checking for selection
	@Override
	public DragTracker getDragTracker(final Request request) {
		return new DragEditPartsTracker(this) {
			@Override
			protected void performConditionalSelection() {
				super.performConditionalSelection();
				if (getCurrentInput().isShiftKeyDown())	
					PollyLineCreateCommand.isShiftPressed = true;
				else 
					PollyLineCreateCommand.isShiftPressed = false;

//				if (getCurrentInput().isControlKeyDown())	
//					MyListener.isControlPressed = true;
//				else
//					MyListener.isControlPressed = false;
			}
		};
	}

	

	// ==================== 6. Action Methods ====================

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {

		if (evt.getPropertyName().equals(CanvasModel.PROPERTY_BACKGROUND))
			changeBackground();
		else
			refreshChildren();
	}

	private void changeBackground() {
		final CanvasFigure figure = (CanvasFigure) getFigure();
		final CanvasModel model = (CanvasModel) getModel();

		figure.setImage(model.getImage());
		figure.repaint();
	}

	@Override
	protected void refreshVisuals() {

	}
	
	@Override
	public void activate() {
		super.activate();
		((CanvasModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate() {
		super.deactivate();
		((CanvasModel) getModel()).removePropertyChangeListener(this);
	}
	

	// ==================== 7. Getters & Setters ====================

	@Override
	protected List<ChildModel> getModelChildren() {
		return ((CanvasModel) getModel()).getChildren();
	}

}