package com.ansis.floorplan.core.editpart;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.tools.DragEditPartsTracker;

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

	private Rectangle etageLabelPosition;

	private int oldFontSize;

	private int height = 20;


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
		figure.setConstraint(figure.getLabelEtage(), etageLabelPosition);
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


	// ==================== 6. Action Methods ====================

	@Override
	public void refreshVisuals() {
		final RectangleFigure figure = (RectangleFigure)getFigure();
		final ChildModel model = (ChildModel)getModel();

		dynamicLabelPositionAlgorythm(model);

		// Bounds
		figure.setBounds(model.getBounds());
		// TODO Dynamic label positions
		// Name Label Position
		figure.setConstraint(figure.getLabelName(), nameLabelPosition);
		// Etage Label Position
		figure.setConstraint(figure.getLabelEtage(), etageLabelPosition);
		// Name
		figure.setName(model.getName());
		// Etage
		figure.setEtage(model.getEtage());
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
		figure.getLabelEtage().setForegroundColor( FloorplanActivator.getDefault().getColor(model.getFontColor()) );
		// Label Color
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

	private Rectangle initialLabelPositionAlgorythm(final ChildModel model) {
		nameLabelPosition = model.getLabelPosition();

		nameLabelPosition.y = nameLabelPosition.height/2 - height;
		nameLabelPosition.x = nameLabelPosition.width/2 - (model.getName().length() * model.getFontSize())/2;

		nameLabelPosition.height = height;
		nameLabelPosition.width = model.getName().length() * model.getFontSize();

		etageLabelPosition = new Rectangle(nameLabelPosition);

		etageLabelPosition.y = etageLabelPosition.y + 20;

		return etageLabelPosition;
	}

	private Rectangle dynamicLabelPositionAlgorythm(final ChildModel model) {
		height = 10;

		height = height + model.getFontSize();

		System.out.println("===========================================================================================");
		System.out.println("nameLabelPosition.x before: " + nameLabelPosition.x);
		System.out.println("nameLabelPosition.width before: " + nameLabelPosition.width);
		System.out.println("model.getFontSize(): " + model.getFontSize());
		System.out.println("oldFontSize: " + oldFontSize);
		System.out.println("model.getName().length(): " + model.getName().length());
		
		if (oldFontSize < model.getFontSize()) {
			nameLabelPosition.y = nameLabelPosition.y - (model.getFontSize() - oldFontSize);
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println( nameLabelPosition.x + " - ( ( " + model.getFontSize() + " - " + oldFontSize + " ) * " + model.getName().length() + " )/2 = " );
			System.out.println( nameLabelPosition.x + " - ( " + (model.getFontSize() - oldFontSize) + " * " + model.getName().length() + " )/2 = " );
			System.out.println( nameLabelPosition.x + " - " + ((model.getFontSize() - oldFontSize) * model.getName().length()) + "/2 = " );
			System.out.println( nameLabelPosition.x + " - " + (((model.getFontSize() - oldFontSize) * model.getName().length())/2) + " = " );
			
			nameLabelPosition.x = nameLabelPosition.x - ( (model.getFontSize() - oldFontSize) * model.getName().length() )/2;
		} else if (oldFontSize > model.getFontSize()) {
			nameLabelPosition.y = nameLabelPosition.y + (oldFontSize - model.getFontSize());
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println( nameLabelPosition.x + " + ( ( " +  oldFontSize + " - " + model.getFontSize() + " ) * " + model.getName().length() + " )/2 = " );
			System.out.println( nameLabelPosition.x + " + ( " +  (oldFontSize - model.getFontSize()) + " * " + model.getName().length() + " )/2 = " );
			System.out.println( nameLabelPosition.x + " + " +  ((oldFontSize - model.getFontSize()) * model.getName().length()) + "/2 = " );
			System.out.println( nameLabelPosition.x + " + " +  (((oldFontSize - model.getFontSize()) * model.getName().length())/2) + " = " );
			
			nameLabelPosition.x = nameLabelPosition.x + ( (oldFontSize - model.getFontSize()) * model.getName().length() )/2;
		} else {
			return null;
		}
		System.out.println( "nameLabelPosition.x after: " + nameLabelPosition.x );

		nameLabelPosition.height = height;
		nameLabelPosition.width = model.getName().length() * (model.getFontSize() - 1);
		
		System.out.println("nameLabelPosition.width after: " + nameLabelPosition.width);

		etageLabelPosition = new Rectangle(nameLabelPosition);

		etageLabelPosition.y = etageLabelPosition.y + height;

		oldFontSize = model.getFontSize();

		return etageLabelPosition;
	}


	// ==================== 7. Getters & Setters ====================

	// This is an experimental way of checking for selection
	@Override
	public DragTracker getDragTracker(final Request request) {
		return new DragEditPartsTracker(this) {

			@Override
			protected void performConditionalSelection() {
				super.performConditionalSelection();
				// This condition is not needed since the figure is always active after a selection
				if (isActive()) {
					final RectangleFigure figure = (RectangleFigure)getFigure();
					figure.setLineStyle(2);
					figure.setLineWidth(3);
				}
			}
		};
	}

}