package com.ansis.floorplan.core.action.defaults;

import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.FPConstPresentation;


public class DefaultOpacityAction extends BaseDefaultAction {

	// ==================== 1. Static Fields ========================

	public static final String defaultOpacityProperty = "defaultOpacityProperty"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private final String defaultOpacity = "127"; //$NON-NLS-1$


	// ==================== 4. Constructors ====================

	public DefaultOpacityAction(final IWorkbenchPart part, final CanvasModel model) {
		super(part, model);
		setLazyEnablementCalculation(true);
	}

	@Override
	protected void init() {
		setId(defaultOpacityProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_OPACITY)); 
		setText("Default opacity (50" + FPConstPresentation.PERCENT + ")");
		setEnabled(false);
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected void changeProperty(final ChildModel children) {
		children.setOpacity(Integer.parseInt(getDefaultOpacity()));

	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public String getDefaultOpacity() {
		return defaultOpacity;
	}
}