package com.ansis.floorplan.core.request;

import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;

import com.ansis.floorplan.core.factory.RectangleCreationFactory;
import com.ansis.floorplan.core.model.ChildModel;


public class RectangleCreateRequest extends CreateRequest {

	// ==================== 4. Constructors ====================

	public RectangleCreateRequest() {
		super(ChildModel.class);
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected CreationFactory getFactory() {
		return new RectangleCreationFactory();
	}

}