package com.ansis.floorplan.editpart;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.tools.DragEditPartsTracker;

import com.ansis.floorplan.editpolicy.AppDeletePolicy;
import com.ansis.floorplan.editpolicy.AppEditLayoutPolicy;
import com.ansis.floorplan.editpolicy.AppFontSizePolicy;
import com.ansis.floorplan.editpolicy.AppFontStylePolicy;
import com.ansis.floorplan.editpolicy.AppOpacityPolicy;
import com.ansis.floorplan.editpolicy.AppRenamePolicy;
import com.ansis.floorplan.figure.PollyFigure;
import com.ansis.floorplan.model.ModelTest;
import com.ansis.floorplan.model.Polly;


public class PollyEditPart extends AppAbstractEditPart {

	private static final String OPACITY_EDIT_POLICY = "opacity"; //$NON-NLS-1$

	private static final String FONT_STYLE_EDIT_POLICY = "fontStyle"; //$NON-NLS-1$

	private static final String FONT_SIZE_EDIT_POLICY = "fontSize"; //$NON-NLS-1$


	// ==================== 5. Creators ====================

	@Override
	protected IFigure createFigure() {
		final PollyFigure figure = new PollyFigure( ((Polly)getModel()).getG() );

		// Bounds
		figure.setBounds( ((Polly)getModel()).getR() );
		// PointList
		figure.setList( ((Polly)getModel()).getList() );
		// Name
		figure.setName( ((Polly)getModel()).getName() );
		// Etage
		figure.setEtage( ((Polly)getModel()).getEtage() );
		// TODO Color
		figure.setBackgroundColor( ((Polly)getModel()).getColor() );
		// Line Color
		figure.setForegroundColor( ((Polly)getModel()).getLineColor() );
		// Opacity
		figure.setAlpha( ((Polly)getModel()).getOpacity() );
		// FontStyle
		figure.setFontStyle( ((Polly)getModel()).getFontStyle() );
		// FontSize
		figure.setFontSize( ((Polly)getModel()).getFontSize() );

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

		// Font Style
		installEditPolicy(FONT_STYLE_EDIT_POLICY, new AppFontStylePolicy());

		// Font Size
		installEditPolicy(FONT_SIZE_EDIT_POLICY, new AppFontSizePolicy());
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
		// Bounds
		figure.setBounds(model.getR());
		// Name
		figure.setName(model.getName());
		// Etage
		figure.setEtage(model.getEtage());
		// Layout
		figure.setLayout(model.getLayout());
		// TODO Color
		figure.setBackgroundColor(model.getColor());
		// Opacity
		figure.setAlpha(model.getOpacity());
		// FontStyle
		figure.setFontStyle(model.getFontStyle());
		// FontSize
		figure.setFontSize(model.getFontSize());
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

		// Font Style
		if (evt.getPropertyName().equals(Polly.PROPERTY_FONT_STYLE))
			refreshVisuals();

		// Font Size
		if (evt.getPropertyName().equals(Polly.PROPERTY_FONT_SIZE))
			refreshVisuals();
	}

}