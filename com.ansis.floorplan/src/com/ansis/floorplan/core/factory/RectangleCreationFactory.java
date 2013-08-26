package com.ansis.floorplan.core.factory;

import org.eclipse.gef.requests.CreationFactory;

import com.ansis.floorplan.core.model.RectangleModel;


public class RectangleCreationFactory implements CreationFactory {

	// ==================== 7. Getters & Setters ====================

	@Override
	public Object getNewObject() {
		final RectangleModel newObject = new RectangleModel();
		return newObject;
	}

	@Override
	public Object getObjectType() {
		return RectangleModel.class;
	}

}