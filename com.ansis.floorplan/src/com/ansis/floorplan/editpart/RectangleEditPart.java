package com.ansis.floorplan.editpart;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;

import com.ansis.floorplan.model.RectangleModel;

/**
 *
 * @author ggrec
 *
 */
public class RectangleEditPart extends AppAbstractEditPart {

	@Override
	protected IFigure createFigure() {
		final RectangleFigure figure = new RectangleFigure();
//		figure.setLayoutManager(new XYLayout());
		return figure;
	}

	@Override
	public void refreshVisuals() {
		final RectangleFigure figure = (RectangleFigure)getFigure();
		final RectangleModel model = (RectangleModel)getModel();

		figure.setBounds(model.getBounds());
	}

	@Override
	protected void createEditPolicies() {

	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
//		if (evt.getPropertyName().equals(RootModel.PROPERTY_LAYOUT)) 
//			refreshVisuals();
	}

}
