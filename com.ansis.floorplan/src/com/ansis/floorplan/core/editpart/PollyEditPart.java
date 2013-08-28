package com.ansis.floorplan.core.editpart;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.core.editpolicy.AppChangeColorPolicy;
import com.ansis.floorplan.core.editpolicy.AppChangeLabelColorPolicy;
import com.ansis.floorplan.core.editpolicy.AppDeletePolicy;
import com.ansis.floorplan.core.editpolicy.AppEditLayoutPolicy;
import com.ansis.floorplan.core.editpolicy.AppFontColorPolicy;
import com.ansis.floorplan.core.editpolicy.AppFontSizePolicy;
import com.ansis.floorplan.core.editpolicy.AppFontStylePolicy;
import com.ansis.floorplan.core.editpolicy.AppOpacityPolicy;
import com.ansis.floorplan.core.editpolicy.AppRenamePolicy;
import com.ansis.floorplan.core.figure.PollyFigure;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.core.model.Polly;


public class PollyEditPart extends AppAbstractEditPart {

	// ==================== 1. Static Fields ========================

	private static final String OPACITY_EDIT_POLICY = "opacity"; //$NON-NLS-1$

	private static final String COLOR_EDIT_POLICY = "color"; //$NON-NLS-1$

	private static final String FONT_STYLE_EDIT_POLICY = "fontStyle"; //$NON-NLS-1$

	private static final String FONT_SIZE_EDIT_POLICY = "fontSize"; //$NON-NLS-1$

	private static final String FONT_COLOR_EDIT_PART = "fontColor"; //$NON-NLS-1$

	private static final String LABEL_COLOR_EDIT_PART = "labelColor"; //$NON-NLS-1$


	// ==================== 5. Creators ====================

	@Override
	protected IFigure createFigure() {
		final PollyFigure figure = new PollyFigure( ((ChildModel)getModel()).getLabelPosition() );
		final ChildModel model = (ChildModel)getModel();

		// Name
		figure.setName( model.getName() );
		// Etage
		figure.setEtage( model.getEtage() );
		// Bounds
		figure.setBounds( model.getBounds() );
		// PointList
		figure.setList( ((Polly)getModel()).getList() );
		// Name
		figure.setName( model.getName() );
		// Etage
		figure.setEtage( model.getEtage() );
		// Color
		figure.setBackgroundColor( FloorplanActivator.getDefault().getColor(model.getColor()) );
		// Line Color
		figure.setForegroundColor( FloorplanActivator.getDefault().getColor(model.getLineColor()) );
		// Opacity
		figure.setAlpha( model.getOpacity() );
		// FontStyle
		figure.setFontStyle( model.getFontStyle() );
		// FontSize
		figure.setFontSize( model.getFontSize() );
		// FontColor
		figure.getLabelName().setForegroundColor( FloorplanActivator.getDefault().getColor(model.getFontColor()) );
		figure.getLabelEtage().setForegroundColor( FloorplanActivator.getDefault().getColor(model.getFontColor()) );
		// LabelColor
		figure.getLabelName().setBackgroundColor( FloorplanActivator.getDefault().getColor(model.getLabelColor()) );
		figure.getLabelEtage().setBackgroundColor( FloorplanActivator.getDefault().getColor(model.getLabelColor()) );

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

		// Color
		installEditPolicy(COLOR_EDIT_POLICY, new AppChangeColorPolicy());

		// Opacity
		installEditPolicy(OPACITY_EDIT_POLICY, new AppOpacityPolicy());

		// Font Style
		installEditPolicy(FONT_STYLE_EDIT_POLICY, new AppFontStylePolicy());

		// Font Size
		installEditPolicy(FONT_SIZE_EDIT_POLICY, new AppFontSizePolicy());

		// Font Color
		installEditPolicy(FONT_COLOR_EDIT_PART, new AppFontColorPolicy());

		// Label Color
		installEditPolicy(LABEL_COLOR_EDIT_PART, new AppChangeLabelColorPolicy());
	}

	@Override
	public boolean hasFocus() {
		final PollyFigure figure = (PollyFigure)getFigure();

		figure.setLineStyle(2);
		figure.setLineWidth(3);

		if (getSelected() == SELECTED_NONE) {
			figure.setLineStyle(1);
			figure.setLineWidth(5);
		}

		return super.hasFocus();
	}
	// ==================== 6. Action Methods ====================

	@Override
	public void refreshVisuals() {
		final PollyFigure figure = (PollyFigure)getFigure();
		final ChildModel model = (ChildModel)getModel();

		// Bounds
		figure.setBounds(model.getBounds());
		// Name
		figure.setName(model.getName());
		// Etage
		figure.setEtage(model.getEtage());
		// Layout
		figure.setLayout(model.getLayout());
		// Color
		figure.setBackgroundColor( FloorplanActivator.getDefault().getColor(model.getColor()) );
		// LineColor
		figure.setForegroundColor( FloorplanActivator.getDefault().getColor(model.getColor()) );
		// Opacity
		figure.setAlpha(model.getOpacity());
		// FontStyle
		figure.setFontStyle(model.getFontStyle());
		// FontSize
		figure.setFontSize(model.getFontSize());
		// FontColor
		figure.getLabelName().setForegroundColor( FloorplanActivator.getDefault().getColor(model.getFontColor()) );
		figure.getLabelEtage().setForegroundColor( FloorplanActivator.getDefault().getColor(model.getFontColor()) );
		// LabelColor
		figure.getLabelName().setBackgroundColor( FloorplanActivator.getDefault().getColor(model.getLabelColor()) );
		figure.getLabelEtage().setBackgroundColor( FloorplanActivator.getDefault().getColor(model.getLabelColor()) );
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {

		// Move
		if (evt.getPropertyName().equals(CanvasModel.PROPERTY_LAYOUT))
			refreshVisuals();

		// Create
		if (evt.getPropertyName().equals(CanvasModel.PROPERTY_ADD))
			refreshChildren();

		// Delete
		if (evt.getPropertyName().equals(CanvasModel.PROPERTY_REMOVE))
			refreshChildren();

		// Rename
		if (evt.getPropertyName().equals(ChildModel.PROPERTY_RENAME))
			refreshVisuals();

		// Color
		if (evt.getPropertyName().equals(ChildModel.PROPERTY_COLOR))
			refreshVisuals();

		// Opacity
		if (evt.getPropertyName().equals(ChildModel.PROPERTY_OPACITY))
			refreshVisuals();

		// Font Style
		if (evt.getPropertyName().equals(ChildModel.PROPERTY_FONT_STYLE))
			refreshVisuals();

		// Font Size
		if (evt.getPropertyName().equals(ChildModel.PROPERTY_FONT_SIZE))
			refreshVisuals();

		// Font Color
		if (evt.getPropertyName().equals(ChildModel.PROPERTY_FONT_COLOR))
			refreshVisuals();

		// Label Color
		if (evt.getPropertyName().equals(ChildModel.PROPERTY_LABEL_COLOR))
			refreshVisuals();
	}

}