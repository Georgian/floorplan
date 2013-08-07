package com.ansis.floorplan.editpart;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.tools.DragEditPartsTracker;

import com.ansis.floorplan.editpolicy.AppDeletePolicy;
import com.ansis.floorplan.editpolicy.AppEditLayoutPolicy;
import com.ansis.floorplan.editpolicy.AppOpacityPolicy;
import com.ansis.floorplan.editpolicy.AppRenamePolicy;
import com.ansis.floorplan.figure.PollyFigure;
import com.ansis.floorplan.model.ModelTest;
import com.ansis.floorplan.model.Polly;


public class PollyEditPart extends AppAbstractEditPart {

	private static final String OPACITY_EDIT_POLICY = "opacity";

	// ==================== 5. Creators ====================

	@Override
	protected IFigure createFigure() {
		final PollyFigure figure = new PollyFigure( ((Polly)getModel()).getG() );

		figure.setBounds( ((Polly)getModel()).getR() );
		figure.setList( ((Polly)getModel()).getList() );
		figure.setName( ((Polly)getModel()).getName() );
		figure.setEtage( ((Polly)getModel()).getEtage() );
		figure.setBackgroundColor( ((Polly)getModel()).getColor() );
		figure.setAlpha( ((Polly)getModel()).getOpacity() );

		//		System.out.println(((Polly)getModel()).getList().size()+"\ncreateFigure method called");

		return figure;
	}

	@Override
	protected void createEditPolicies() {

		// Move
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());

		// Create
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());

		// Rename
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());

		// TODO Color

		// Opacity
		installEditPolicy(OPACITY_EDIT_POLICY, new AppOpacityPolicy());
	}

	// This is an experimental way of checking for selection
	@Override
	public DragTracker getDragTracker(final Request request) {
		return new DragEditPartsTracker(this) {

			@Override
			protected void performConditionalSelection() {
				super.performConditionalSelection();
				// This condition is not needed since the figure is always active after a selection
				if (isActive()) {
//					System.out.println("the part was selected and is now active");

					final PollyFigure figure = (PollyFigure)getFigure();
					figure.setLineStyle(2);
					figure.setLineWidth(3);

					System.out.println(getSelected());
				}
			}
		};
	}

	// ==================== 6. Action Methods ====================

	@Override
	public void refreshVisuals() {
		final PollyFigure figure = (PollyFigure)getFigure();
		final Polly model = (Polly)getModel();

		//		G should be used when the figure is refreshed
		//		figure.setG(model.getG());
		figure.setBounds(model.getR());
		figure.setName(model.getName());
		figure.setEtage(model.getEtage());
		figure.setLayout(model.getLayout());
		figure.setBackgroundColor(model.getColor());
		figure.setAlpha(model.getOpacity());
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {

		// Move
		if (evt.getPropertyName().equals(ModelTest.PROPERTY_LAYOUT))
			refreshVisuals();

		// Create
		if (evt.getPropertyName().equals(ModelTest.PROPERTY_ADD))
			refreshChildren();

		// Delete
		if (evt.getPropertyName().equals(ModelTest.PROPERTY_REMOVE))
			refreshChildren();

		// Rename
		if (evt.getPropertyName().equals(Polly.PROPERTY_RENAME))
			refreshVisuals();

		// Color
		if (evt.getPropertyName().equals(Polly.PROPERTY_COLOR))
			refreshVisuals();

		// Opacity
		if (evt.getPropertyName().equals(Polly.PROPERTY_OPACITY))
			refreshVisuals();
	}

}