package com.ansis.floorplan.editpart;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.ansis.floorplan.model.ModelTest;


public abstract class AppAbstractEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

	// ==================== 6. Action Methods ====================

	@Override
	public void activate() {
		super.activate();
		((ModelTest) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate() {
		super.deactivate();
		((ModelTest) getModel()).removePropertyChangeListener(this);
	}

}