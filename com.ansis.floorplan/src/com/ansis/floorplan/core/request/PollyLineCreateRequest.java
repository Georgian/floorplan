package com.ansis.floorplan.core.request;

import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;

import com.ansis.floorplan.core.factory.PollyLineCreationFactory;
import com.ansis.floorplan.core.model.PollyLine;


public class PollyLineCreateRequest extends CreateRequest {

	// ==================== 4. Constructors ====================

	public PollyLineCreateRequest() {
		super(PollyLine.class);
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected CreationFactory getFactory() {
		return new PollyLineCreationFactory();
	}

}