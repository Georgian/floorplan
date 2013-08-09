package com.ansis.floorplan.action;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;


public class ChangeLabelColorAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String changeLabelColorProperty = "changeLabelColorProperty"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private RGB labelColor;


	// ==================== 4. Constructors ====================

	public ChangeLabelColorAction(final IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createChangeLabelColorCommand(final RGB labelColor) {
		final Request changeLabelColorReq = new Request("changeLabelColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newLabelColor", labelColor); //$NON-NLS-1$
		changeLabelColorReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(changeLabelColorReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createChangeLabelColorCommand(null); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(changeLabelColorProperty);
		setText("Font background color...");  //$NON-NLS-1$
		setToolTipText("Font background color"); //$NON-NLS-1$
		final ImageDescriptor icon = AbstractUIPlugin.imageDescriptorFromPlugin("FloorPlan", "icons/rename-icon.png"); //$NON-NLS-1$ //$NON-NLS-2$
		if (icon != null)
			setImageDescriptor(icon);
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createChangeLabelColorCommand(getLabelColor()));
	}


	// ==================== 7. Getters & Setters ====================

	public RGB getLabelColor() {
		return labelColor;
	}

}