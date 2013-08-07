package com.ansis.floorplan.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.tools.DragEditPartsTracker;

import com.ansis.floorplan.editpolicy.AppEditLayoutPolicy;
import com.ansis.floorplan.figure.CanvasFigure;
import com.ansis.floorplan.listener.MyListener;
import com.ansis.floorplan.model.Canvas;
import com.ansis.floorplan.model.ModelTest;
import com.ansis.floorplan.model.Polly;


public class CanvasEditPart extends AppAbstractEditPart {

	// ==================== 5. Creators ====================

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
	}

	@Override
	protected IFigure createFigure() {
		final IFigure figure = new CanvasFigure();

		new MyListener( ((Canvas)getModel()), figure );
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
		if (evt.getPropertyName().equals(ModelTest.PROPERTY_ADD))
			refreshChildren();
		if (evt.getPropertyName().equals(ModelTest.PROPERTY_REMOVE))
			refreshChildren();

		//		System.out.println("CanvasEditPart: " + evt.toString());

		refreshChildren();
	}

	@Override
	protected void refreshVisuals() {
		final CanvasFigure figure = (CanvasFigure)getFigure();
		final Canvas model = (Canvas)getModel();

		figure.setName(model.getName());
		figure.setAddress(model.getAddress());
		figure.setCapital(model.getCapital());
	}

	@Override
	protected void refreshChildren() {
		super.refreshChildren();
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	protected List<Polly> getModelChildren() {
		return ModelTest.getChildren();
	}

}