package com.ansis.floorplan.core.action;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.util.FPConstPresentation;


public class ChangeColorAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String changeColorProperty = "changeColorProperty"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private RGB color;


	// ==================== 4. Constructors ====================

	public ChangeColorAction(final IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createChangeColorCommand(final RGB color) {
		final Request changeColorReq = new Request("changeColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newColor", color); //$NON-NLS-1$
		changeColorReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(changeColorReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createChangeColorCommand(null); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(changeColorProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor("icons/figureColor.png")); //$NON-NLS-1$
		setText("Figure color"+FPConstPresentation.ELIPSES);  //$NON-NLS-1$
		final ImageDescriptor icon = AbstractUIPlugin.imageDescriptorFromPlugin("FloorPlan", "icons/rename-icon.png"); //$NON-NLS-1$ //$NON-NLS-2$
		if (icon != null)
			setImageDescriptor(icon);
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createChangeColorCommand(getColor()));
	}


	// ==================== 7. Getters & Setters ====================

	public RGB getColor() {
		return color;
	}

}