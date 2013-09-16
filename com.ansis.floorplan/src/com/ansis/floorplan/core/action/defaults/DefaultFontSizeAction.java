package com.ansis.floorplan.core.action.defaults;

import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.font.FPFontSize;


public class DefaultFontSizeAction extends BaseDefaultAction {

	// ==================== 1. Static Fields ========================

	public static final String defaultFontSizeProperty = "defaultFontSizeProperty"; //$NON-NLS-1$


	// ==================== 4. Constructors ====================

	public DefaultFontSizeAction(final IWorkbenchPart part, final CanvasModel model) {
		super(part, model);
		setLazyEnablementCalculation(true);
	}

	@Override
	protected void init() {
		setId(defaultFontSizeProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_FONT_SIZE)); 
		setText("Default font size (" + FPFontSize.NORMAL.getPercent()/10 + ")");
		setEnabled(false);
	}


	// ==================== 6. Action Methods ====================


	@Override
	protected void changeProperty(final ChildModel children) {
		children.setFontSize(FPFontSize.NORMAL.getPercent()/10);

	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public String getDefaultFontSize() {
		return String.valueOf( FPFontSize.NORMAL.getPercent()/10 );
	}
}