package com.ansis.floorplan.factory;

import org.eclipse.draw2d.geometry.Rectangle;
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

		newObject.setBounds(new Rectangle(0, 0, 100, 100));
		newObject.setLayout(new Rectangle(0, 0, 100, 100));

		return newObject;
	}

	@Override
	public Object getObjectType() {
		return RectangleModel.class;
	}

}
