package com.ansis.floorplan.core.action.defaults;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.color.FPStandardColor;


public class DefaultFigureColorAction extends BaseDefaultAction {

	// ==================== 1. Static Fields ========================

	public static final String defaultFigureColorProperty = "defaultFigureColorProperty"; //$NON-NLS-1$


	// ==================== 4. Constructors ====================

	public DefaultFigureColorAction(final IWorkbenchPart part, final CanvasModel model) {
		super(part, model);
		setLazyEnablementCalculation(true);
	}

	@Override
	protected void init() {
		setId(defaultFigureColorProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_COLOR)); 
		setText("Default figure color (Navy Blue)");
		setEnabled(false);
	}

	
	// ==================== 6. Action Methods ====================

	@Override
	protected void changeProperty(final ChildModel children) 
	{
		children.setColor(getDefaultFigureColor());
		
		if ( children.getFontColorChanged() == false )
			children.setFontColor(FloorplanActivator.getDefault().getColor(FPStandardColor.BLACK).getRGB());
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public RGB getDefaultFigureColor() {
		return FloorplanActivator.getDefault().getColor(FPStandardColor.BLUE_NAVY).getRGB();
	}
}