package com.ansis.floorplan.request;

import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;

import com.ansis.floorplan.factory.RectangleCreationFactory;
import com.ansis.floorplan.model.ChildModel;

public class RectangleCreateRequest extends CreateRequest {
	
	// ====================== 2. Instance Fields =============================

	
	
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
