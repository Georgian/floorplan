package com.ansis.floorplan.core.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.ansis.floorplan.core.editpolicy.DeletePolicy;
import com.ansis.floorplan.core.editpolicy.EditLayoutPolicy;
import com.ansis.floorplan.core.editpolicy.FigureColorPolicy;
import com.ansis.floorplan.core.editpolicy.OpacityPolicy;
import com.ansis.floorplan.core.editpolicy.RenamePolicy;
import com.ansis.floorplan.core.editpolicy.defaults.DefaultFigureColorPolicy;
import com.ansis.floorplan.core.editpolicy.defaults.DefaultFontColorPolicy;
import com.ansis.floorplan.core.editpolicy.defaults.DefaultLabelColorPolicy;
import com.ansis.floorplan.core.editpolicy.font.FontColorPolicy;
import com.ansis.floorplan.core.editpolicy.font.FontSizePolicy;
import com.ansis.floorplan.core.editpolicy.font.FontStylePolicy;
import com.ansis.floorplan.core.editpolicy.font.LabelColorPolicy;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;


public class ChildEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

	// ==================== 1. Static Fields ========================

	protected static final String OPACITY_EDIT_POLICY = "opacity"; //$NON-NLS-1$

	protected static final String FIGURE_COLOR_EDIT_POLICY = "figureColor"; //$NON-NLS-1$

	protected static final String FONT_STYLE_EDIT_POLICY = "fontStyle"; //$NON-NLS-1$

	protected static final String FONT_SIZE_EDIT_POLICY = "fontSize"; //$NON-NLS-1$

	protected static final String FONT_COLOR_EDIT_PART = "fontColor"; //$NON-NLS-1$

	protected static final String LABEL_COLOR_EDIT_PART = "labelColor"; //$NON-NLS-1$

	protected static final String DEFAULT_FONT_COLOR_EDIT_PART = "defaultFontColor"; //$NON-NLS-1$

	protected static final String DEFAULT_LABEL_COLOR_EDIT_PART = "defaultLabelColor"; //$NON-NLS-1$

	protected static final String DEFAULT_FIGURE_COLOR_EDIT_POLICY = "defaultFigureColor"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	protected int height = 20;

	protected String[] lines;


	// ==================== 5. Creators ====================

	@Override
	protected IFigure createFigure() {
		return null;
	}

	@Override
	protected void createEditPolicies() {

		// Move
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new EditLayoutPolicy());

		// Create
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new DeletePolicy());

		// Rename
		installEditPolicy(EditPolicy.NODE_ROLE, new RenamePolicy());

		// Figure Color
		installEditPolicy(FIGURE_COLOR_EDIT_POLICY, new FigureColorPolicy());

		// Opacity
		installEditPolicy(OPACITY_EDIT_POLICY, new OpacityPolicy());

		// Font Style
		installEditPolicy(FONT_STYLE_EDIT_POLICY, new FontStylePolicy());

		// Font Size
		installEditPolicy(FONT_SIZE_EDIT_POLICY, new FontSizePolicy());

		// Font Color
		installEditPolicy(FONT_COLOR_EDIT_PART, new FontColorPolicy());

		// Label Color
		installEditPolicy(LABEL_COLOR_EDIT_PART, new LabelColorPolicy());

		// Default Font Color
		installEditPolicy(DEFAULT_FONT_COLOR_EDIT_PART, new DefaultFontColorPolicy());

		// Default Label Color
		installEditPolicy(DEFAULT_LABEL_COLOR_EDIT_PART, new DefaultLabelColorPolicy());

		// Default Figure Color
		installEditPolicy(DEFAULT_FIGURE_COLOR_EDIT_POLICY, new DefaultFigureColorPolicy());
	}


	// ==================== 6. Action Methods ====================

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {

		final String propName = evt.getPropertyName();

		if (CanvasModel.PROPERTY_ADD.equalsIgnoreCase(propName) || CanvasModel.PROPERTY_REMOVE.equalsIgnoreCase(propName))
			refreshChildren();
		else
			refreshVisuals();
	}
	
	@Override
	public void activate() {
		super.activate();
		((ChildModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate() {
		super.deactivate();
		((ChildModel) getModel()).removePropertyChangeListener(this);
	}

}