package com.ansis.floorplan.core.action.defaults;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.util.color.FPStandardColor;


public class DefaultFigureColorAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String defaultFigureColorProperty = "defaultFigureColorProperty"; //$NON-NLS-1$

	// ==================== 4. Constructors ====================

	public DefaultFigureColorAction(final IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createDefaultFigureColorCommand(final RGB defaultFigureColor) {
		final Request defaultFigureColorReq = new Request("defaultFigureColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newDefaultFigureColor", defaultFigureColor); //$NON-NLS-1$
		defaultFigureColorReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(defaultFigureColorReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createDefaultFigureColorCommand(null); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(defaultFigureColorProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_COLOR)); 
		setText("Default figure color");  //$NON-NLS-1$
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createDefaultFigureColorCommand(getDefaultFigureColor()));
	}


	// ==================== 7. Getters & Setters ====================

	public RGB getDefaultFigureColor() {
		return FloorplanActivator.getDefault().getColor(FPStandardColor.BLUE_NAVY).getRGB();
	}

}