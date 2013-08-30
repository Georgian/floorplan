package com.ansis.floorplan.core.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.core.figure.RectangleFigure;
import com.ansis.floorplan.core.model.ChildModel;


public class RectangleEditPart extends ChildEditPart {

	// ====================== 2. Instance Fields =============================

	private Rectangle nameLabelPosition;

	private int oldFontSize;


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
		// Name
		figure.setName( model.getName() );
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
		// Name
		figure.setName(model.getName());
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