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


public class DefaultFontColorAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String defaultFontColorProperty = "defaultFontColorProperty"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private RGB defaultFontColor;


	// ==================== 4. Constructors ====================

	public DefaultFontColorAction(final IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createDefaultFontColorCommand(final RGB defaultFontColor) {
		final Request defaultFontColorReq = new Request("defaultFontColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newDefaultFontColor", defaultFontColor); //$NON-NLS-1$
		defaultFontColorReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(defaultFontColorReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createDefaultFontColorCommand(null); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(defaultFontColorProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_FONT_COLOR)); 
		setText("Default font color");  //$NON-NLS-1$
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createDefaultFontColorCommand(getDefaultFontColor()));
	}


	// ==================== 7. Getters & Setters ====================

	public RGB getDefaultFontColor() {
		return defaultFontColor;
	}

}