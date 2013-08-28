package com.ansis.floorplan.core.editpart;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
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
import com.ansis.floorplan.core.figure.RectangleFigure;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;


public class RectangleEditPart extends AppAbstractEditPart {

	// ==================== 1. Static Fields ========================

	private static final String OPACITY_EDIT_POLICY = "Opacity"; //$NON-NLS-1$

	private static final String COLOR_EDIT_POLICY = "Color"; //$NON-NLS-1$

	private static final String FONT_STYLE_EDIT_POLICY = "FontStyle"; //$NON-NLS-1$

	private static final String FONT_SIZE_EDIT_POLICY = "FontSize"; //$NON-NLS-1$

	private static final String FONT_COLOR_EDIT_PART = "FontColor"; //$NON-NLS-1$

	private static final String LABEL_COLOR_EDIT_PART = "LabelColor"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private Rectangle nameLabelPosition;

	private int oldFontSize;

	private int height = 20;

	private String[] lines;


	// ==================== 5. Creators ====================

	@Override
	protected IFigure createFigure() {
		final RectangleFigure figure = new RectangleFigure();
		final ChildModel model = (ChildModel)getModel();

		nameLabelPosition = model.getLabelPosition();

		oldFontSize = model.getFontSize();

		initialLabelPositionAlgorythm(model);

		// Bounds
		figure.setBounds( model.getBounds() );
		// Name Label Position
		figure.setConstraint(figure.getLabelName(), nameLabelPosition);
		// Etage Label Position
//		figure.setConstraint(figure.getLabelEtage(), etageLabelPosition);
		// Name
		figure.setName( model.getName() );
		// Etage
//		figure.setEtage( model.getEtage() );
		// Color
		figure.setBackgroundColor( FloorplanActivator.getDefault().getColor(model.getColor()) );
		// Line Color
		figure.setForegroundColor( FloorplanActivator.getDefault().getColor(model.getLineColor()) );
		// Opacity
		figure.setAlpha( model.getOpacity() );
		// Font Style
		figure.setFontStyle( model.getFontStyle() );
		// Font Size
		figure.setFontSize( model.getFontSize() );
		// Font Color
		figure.getLabelName().setForegroundColor( FloorplanActivator.getDefault().getColor(model.getFontColor()) );
		// Label Color
		figure.getLabelName().setBackgroundColor( FloorplanActivator.getDefault().getColor(model.getLabelColor()) );
		// Line Width
		figure.setLineWidth(3);

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


	// ==================== 6. Action Methods ====================

	@Override
	public void refreshVisuals() {
		final RectangleFigure figure = (RectangleFigure)getFigure();
		final ChildModel model = (ChildModel)getModel();

		dynamicLabelPositionAlgorythm(model);

		// Bounds
		figure.setBounds(model.getBounds());
		// TODO -bulanmaster- Dynamic label positions rectangle
		// Name Label Position
		figure.setConstraint(figure.getLabelName(), nameLabelPosition);
		// Etage Label Position
//		figure.setConstraint(figure.getLabelEtage(), etageLabelPosition);
		// Name
		figure.setName(model.getName());
		// Etage
//		figure.setEtage(model.getEtage());
		// Layout
		figure.setLayout(model.getLayout());
		// Color
		figure.setBackgroundColor( FloorplanActivator.getDefault().getColor(model.getColor()) );
		// Line Color
		figure.setForegroundColor( FloorplanActivator.getDefault().getColor(model.getColor()) );
		// Opacity
		figure.setAlpha(model.getOpacity());
		// Font Style
		figure.setFontStyle(model.getFontStyle());
		// Font Size
		figure.setFontSize(model.getFontSize());
		// Font Color
		figure.getLabelName().setForegroundColor( FloorplanActivator.getDefault().getColor(model.getFontColor()) );
		// Label Color
		figure.getLabelName().setBackgroundColor( FloorplanActivator.getDefault().getColor(model.getLabelColor()) );
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

	private Rectangle initialLabelPositionAlgorythm(final ChildModel model) {
		nameLabelPosition = model.getLabelPosition();

		lines = model.getName().split("\r\n|\r|\n"); //$NON-NLS-1$

		nameLabelPosition.y = nameLabelPosition.height/2 - (height*lines.length)/2;
		nameLabelPosition.x = nameLabelPosition.width/2 - (model.getName().length() * model.getFontSize())/2;

		nameLabelPosition.height = height*lines.length;
		nameLabelPosition.width = model.getName().length() * model.getFontSize();

		return nameLabelPosition;
	}

	private Rectangle dynamicLabelPositionAlgorythm(final ChildModel model) {
//		System.out.println("===========================================================================================");
		height = 30;
		// TODO -bulanmaster- label height rectangle
//		if (oldFontSize > model.getFontSize()) {
//			height = height - (oldFontSize - model.getFontSize());
//		} else if (oldFontSize < model.getFontSize()) {
//			height = height + (model.getFontSize() - oldFontSize);
//		}

		lines = model.getName().split("\r\n|\r|\n"); //$NON-NLS-1$

		height = height + model.getFontSize()*lines.length;

		// useful information
//		System.out.println("nameLabelPosition.x before: " + nameLabelPosition.x);
//		System.out.println("nameLabelPosition.width before: " + nameLabelPosition.width);
//		System.out.println("height: " + height);
//		System.out.println("model.getFontSize(): " + model.getFontSize());
//		System.out.println("oldFontSize: " + oldFontSize);
//		System.out.println("model.getName().length(): " + model.getName().length());

		if (oldFontSize < model.getFontSize()) {
			nameLabelPosition.y = nameLabelPosition.y - (model.getFontSize() - oldFontSize)/2;

			// TODO -bulanmaster- label width rectangle
//			System.out.println("----------------------------------------------------------------------------------------");
//			System.out.println( nameLabelPosition.x + " - ( ( " + model.getFontSize() + " - " + oldFontSize + " ) * " + model.getName().length() + " )/2 = " );
//			System.out.println( nameLabelPosition.x + " - ( " + (model.getFontSize() - oldFontSize) + " * " + model.getName().length() + " )/2 = " );
//			System.out.println( nameLabelPosition.x + " - " + ((model.getFontSize() - oldFontSize) * model.getName().length()) + "/2 = " );
//			System.out.println( nameLabelPosition.x + " - " + (((model.getFontSize() - oldFontSize) * model.getName().length())/2) + " = " );

			nameLabelPosition.x = nameLabelPosition.x - ( (model.getFontSize() - oldFontSize) * model.getName().length() )/2;
		} else if (oldFontSize > model.getFontSize()) {
			nameLabelPosition.y = nameLabelPosition.y + (oldFontSize - model.getFontSize())/2;

			// TODO -bulanmaster- label width rectangle
//			System.out.println("----------------------------------------------------------------------------------------");
//			System.out.println( nameLabelPosition.x + " + ( ( " +  oldFontSize + " - " + model.getFontSize() + " ) * " + model.getName().length() + " )/2 = " );
//			System.out.println( nameLabelPosition.x + " + ( " +  (oldFontSize - model.getFontSize()) + " * " + model.getName().length() + " )/2 = " );
//			System.out.println( nameLabelPosition.x + " + " +  ((oldFontSize - model.getFontSize()) * model.getName().length()) + "/2 = " );
//			System.out.println( nameLabelPosition.x + " + " +  (((oldFontSize - model.getFontSize()) * model.getName().length())/2) + " = " );

			nameLabelPosition.x = nameLabelPosition.x + ( (oldFontSize - model.getFontSize()) * model.getName().length() )/2;
		} else {
			return null;
		}

		nameLabelPosition.height = height;
		nameLabelPosition.width = model.getName().length() * (model.getFontSize() - 1);

//		System.out.println( "nameLabelPosition.x after: " + nameLabelPosition.x );
//		System.out.println("nameLabelPosition.width after: " + nameLabelPosition.width);

		oldFontSize = model.getFontSize();

		return nameLabelPosition;
	}

	// For selection/deselection
	@Override
	public boolean hasFocus() {
		final RectangleFigure figure = (RectangleFigure)getFigure();

		figure.setLineStyle(2);
		

		if (getSelected() == SELECTED_NONE) {
			figure.setLineStyle(1);
		}

		return super.hasFocus();
	}

}