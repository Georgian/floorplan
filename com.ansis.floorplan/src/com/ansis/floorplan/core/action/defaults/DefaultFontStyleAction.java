package com.ansis.floorplan.core.action.defaults;

import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.font.FPFontStyle;


public class DefaultFontStyleAction extends BaseDefaultAction {

	// ==================== 1. Static Fields ========================

	public static final String defaultFontStyleProperty = "defaultFontStyleProperty"; //$NON-NLS-1$


	// ==================== 4. Constructors ====================

	public DefaultFontStyleAction(final IWorkbenchPart part, final CanvasModel model) {
		super(part, model);
		setLazyEnablementCalculation(true);
	}

	@Override
	protected void init() {
		setId(defaultFontStyleProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_FONT_NORMAL)); 
		setText("Default font style (Normal)");
		setEnabled(false);
	}


	// ==================== 6. Action Methods ====================


	@Override
	protected void changeProperty(final ChildModel children) {
		children.setFontStyle( FPFontStyle.NORMAL.getStyle() );

	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public String getDefaultNormal() {
		return String.valueOf( FPFontStyle.NORMAL.getStyle() );
	}
}