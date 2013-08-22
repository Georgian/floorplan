package com.ansis.floorplan.editpart;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.ansis.floorplan.model.CanvasModel;


public abstract class AppAbstractEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

	// ==================== 6. Action Methods ====================

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

}