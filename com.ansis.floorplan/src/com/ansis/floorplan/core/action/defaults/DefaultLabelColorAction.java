package com.ansis.floorplan.core.action.defaults;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.color.FPStandardColor;


public class DefaultLabelColorAction extends BaseDefaultAction {

	// ==================== 1. Static Fields ========================

	public static final String defaultLabelColorProperty = "defaultLabelColorProperty"; //$NON-NLS-1$


	// ==================== 4. Constructors ====================

	public DefaultLabelColorAction(final IWorkbenchPart part, final CanvasModel model) {
		super(part, model);
		setLazyEnablementCalculation(true);
	}

	@Override
	protected void init() {
		setId(defaultLabelColorProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_FONT_BACKGROUND)); 
		setText("Default label color (White)");
		setEnabled(false);
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected void changeProperty(final ChildModel children) {
		children.setLabelColor(getDefaultLabelColor());
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public RGB getDefaultLabelColor() {
		return FloorplanActivator.getDefault().getColor(FPStandardColor.WHITE).getRGB();
	}
}