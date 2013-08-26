package com.ansis.floorplan.core.tool;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.tools.CreationTool;


public class RectangleCreationTool extends CreationTool {

	// ==================== 7. Getters & Setters ====================

	@Override
	protected Dimension getMaximumSizeFor(final CreateRequest request) {
		return new Dimension(100, 100);
	}

	@Override
	protected Dimension getMinimumSizeFor(final CreateRequest request) {
		return new Dimension(10, 10);
	}

}