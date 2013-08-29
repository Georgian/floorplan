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


public class DefaultLabelColorAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String defaultLabelColorProperty = "defaultLabelColorProperty"; //$NON-NLS-1$


	// ==================== 4. Constructors ====================

	public DefaultLabelColorAction(final IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createDefaultLabelColorCommand(final RGB defaultLabelColor) {
		final Request defaultLabelColorReq = new Request("defaultLabelColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newDefaultLabelColor", defaultLabelColor); //$NON-NLS-1$
		defaultLabelColorReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(defaultLabelColorReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createDefaultLabelColorCommand(null); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(defaultLabelColorProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_FONT_BACKGROUND)); 
		setText("Default label color");  //$NON-NLS-1$
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createDefaultLabelColorCommand(getDefaultLabelColor()));
	}


	// ==================== 7. Getters & Setters ====================

	public RGB getDefaultLabelColor() {
		return FloorplanActivator.getDefault().getColor(FPStandardColor.WHITE).getRGB();
	}

}