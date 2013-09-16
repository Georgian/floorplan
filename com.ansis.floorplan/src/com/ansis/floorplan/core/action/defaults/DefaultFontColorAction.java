package com.ansis.floorplan.core.action.defaults;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.color.FPStandardColor;


public class DefaultFontColorAction extends BaseDefaultAction {

	// ==================== 1. Static Fields ========================

	public static final String defaultFontColorProperty = "defaultFontColorProperty"; //$NON-NLS-1$

	
	// ==================== 4. Constructors ====================

	public DefaultFontColorAction(final IWorkbenchPart part, final CanvasModel model) {
		super(part, model);
		setLazyEnablementCalculation(true);
	}

	@Override
	protected void init() {
		setId(defaultFontColorProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_FONT_COLOR)); 
		setText("Default font color (Black)");
		setEnabled(false);
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected void changeProperty(final ChildModel children) {
		
		children.setFontColor(getDefaultFontColor());
		children.setFontColorChanged(false);
		
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public RGB getDefaultFontColor() {
		return FloorplanActivator.getDefault().getColor(FPStandardColor.BLACK).getRGB();
	}
}