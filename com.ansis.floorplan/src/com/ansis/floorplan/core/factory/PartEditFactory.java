package com.ansis.floorplan.factory;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.ansis.floorplan.editpart.CanvasEditPart;
import com.ansis.floorplan.editpart.PollyEditPart;
import com.ansis.floorplan.editpart.PollyLineEditPart;
import com.ansis.floorplan.editpart.RectangleEditPart;
import com.ansis.floorplan.model.Canvas;
import com.ansis.floorplan.model.Polly;
import com.ansis.floorplan.model.PollyLine;
import com.ansis.floorplan.model.RectangleModel;


public class PartEditFactory implements EditPartFactory {

	// ==================== 5. Creators ====================

	@Override
	public EditPart createEditPart(final EditPart context, final Object model) {

		AbstractGraphicalEditPart part = null;

		if (model instanceof Canvas)
			part = new CanvasEditPart();
		else if (model instanceof Polly)
			part = new PollyEditPart();
		else if (model instanceof PollyLine)
			part = new PollyLineEditPart();
		else if (model instanceof RectangleModel)
			part = new RectangleEditPart();

		part.setModel(model);
		return part;
	}

}