package com.ansis.floorplan.factory;

import org.eclipse.gef.requests.CreationFactory;

import com.ansis.floorplan.model.RectangleModel;


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