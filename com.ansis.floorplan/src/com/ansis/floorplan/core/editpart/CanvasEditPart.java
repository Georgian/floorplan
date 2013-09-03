package com.ansis.floorplan.core.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.tools.DragEditPartsTracker;

import com.ansis.floorplan.core.editpolicy.EditLayoutPolicy;
import com.ansis.floorplan.core.figure.CanvasFigure;
import com.ansis.floorplan.core.listener.MyListener;
import com.ansis.floorplan.core.model.Canvas;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;


public class CanvasEditPart extends AppAbstractEditPart {

	// ==================== 5. Creators ====================

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new EditLayoutPolicy());
	}

	@Override
	protected IFigure createFigure() {
		final IFigure figure = new CanvasFigure();

//		new MyListener( ((Canvas)getModel()), figure );
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
					MyListener.isShiftPressed = true;
				else 
					MyListener.isShiftPressed = false;

				if (getCurrentInput().isControlKeyDown())	
					MyListener.isControlPressed = true;
				else
					MyListener.isControlPressed = false;
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
		final Canvas model = (Canvas) getModel();

		figure.setImage(model.getImage());
		figure.repaint();
	}

	@Override
	protected void refreshVisuals() {

	}

	// ==================== 7. Getters & Setters ====================

	@Override
	protected List<ChildModel> getModelChildren() {
		return CanvasModel.getChildren();
	}

}