package com.ansis.floorplan.core.editpart;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Color;

import com.ansis.floorplan.core.editpolicy.AppDeletePolicy;
import com.ansis.floorplan.core.figure.PollyLineFigure;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.PollyLine;


public class PollyLineEditPart extends AppAbstractEditPart {

	// ==================== 5. Creators ====================

	@Override
	protected IFigure createFigure() {
		final PollyLineFigure figure = new PollyLineFigure();

		figure.setBounds( ((PollyLine)getModel()).getBounds());
		figure.setList( ((PollyLine)getModel()).getList() );
		figure.setForegroundColor( new Color(null, ((PollyLine)getModel()).getLineColor()) );
		figure.setAlpha( ((PollyLine)getModel()).getOpacity() );

		return figure;
	}

	@Override
	protected void createEditPolicies() {
		// Create
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());

	}


	// ==================== 6. Action Methods ====================

	@Override
	public void refreshVisuals() {
		final PollyLineFigure figure = (PollyLineFigure)getFigure();
		final PollyLine model = (PollyLine)getModel();

		figure.setBounds(model.getBounds());
		figure.setLayout(model.getLayout());
		figure.setForegroundColor(new Color(null, model.getLineColor()));
		figure.setAlpha(model.getOpacity());
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {

		// Create
		if (evt.getPropertyName().equals(CanvasModel.PROPERTY_ADD))
			refreshChildren();

		// Delete
		if (evt.getPropertyName().equals(CanvasModel.PROPERTY_REMOVE))
			refreshChildren();
	}

}