package com.ansis.floorplan.core.factory;

import org.eclipse.gef.requests.CreationFactory;

import com.ansis.floorplan.core.model.PollyLine;


public class PollyLineCreationFactory implements CreationFactory {

	// ==================== 7. Getters & Setters ====================

	@Override
	public Object getNewObject() {
		final PollyLine newObject = new PollyLine();
		return newObject;
	}

	@Override
	public Object getObjectType() {
		return PollyLine.class;
	}

}