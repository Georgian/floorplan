package com.ansis.floorplan.factory;

import org.eclipse.gef.requests.CreationFactory;

import com.ansis.floorplan.model.RectangleModel;

/**
 *
 * @author ggrec
 *
 */
public class RectangleCreationFactory implements CreationFactory {

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
