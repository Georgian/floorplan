package com.ansis.floorplan.core.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.core.figure.PollyFigure;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.core.model.Polly;


public class PollyEditPart extends ChildEditPart {

	// ====================== 2. Instance Fields =============================

	private Rectangle labelSize;


	// ==================== 5. Creators ====================

	@Override
	protected IFigure createFigure() {
		final PollyFigure figure = new PollyFigure( ((ChildModel)getModel()).getLabelPosition() );
		final ChildModel model = (ChildModel)getModel();

		initialSizeAlgorythm(model);

		// Name
		figure.setName( model.getName() );
		// Bounds
		figure.setBounds( model.getBounds() );
		// PointList
		figure.setList( ((Polly)getModel()).getList() );
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
		final PollyFigure figure = (PollyFigure)getFigure();
		final ChildModel model = (ChildModel)getModel();

		dynamicLabelPositionAlgorythm(model);

		// Bounds
		figure.setBounds(model.getBounds());
		// Name
		figure.setName(model.getName());
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
		// LabelColor
		figure.getLabelName().setBackgroundColor( FloorplanActivator.getDefault().getColor(model.getLabelColor()) );
	}

	private Rectangle initialSizeAlgorythm(final ChildModel model) {
		labelSize = model.getLabelPosition();

		lines = model.getName().split("\r\n|\r|\n"); //$NON-NLS-1$

		labelSize.height = height*lines.length;
		labelSize.width = model.getName().length() * model.getFontSize();

		return labelSize;
	}

	private Rectangle dynamicLabelPositionAlgorythm(final ChildModel model) {
		height = 30;

		// TODO -bulanmaster- label height polly
		lines = model.getName().split("\r\n|\r|\n"); //$NON-NLS-1$

		// TODO -bulanmaster- label width polly
		height = height + model.getFontSize()*lines.length;

		labelSize.height = height;
		labelSize.width = model.getName().length() * (model.getFontSize() - 1);

		return labelSize;
	}

	@Override
	public boolean hasFocus() {
		final PollyFigure figure = (PollyFigure)getFigure();

		figure.setLineStyle(2);

		if (getSelected() == SELECTED_NONE) {
			figure.setLineStyle(1);
		}

		return super.hasFocus();
	}

}