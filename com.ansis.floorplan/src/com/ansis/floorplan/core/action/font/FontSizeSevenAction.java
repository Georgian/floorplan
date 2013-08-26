package com.ansis.floorplan.core.action.font;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;


public class FontSizeSevenAction extends SelectionAction{

	// ==================== 1. Static Fields ========================

	public static final String fontSizePropertySeven = "fontSizePropertySeven"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private final String seven = "7"; //$NON-NLS-1$


	// ==================== 4. Constructors ====================

	public FontSizeSevenAction(final IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createFontSizeCommand(final String fontSize) {
		final Request fontSizeReq = new Request("fontSize"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newFontSize", fontSize); //$NON-NLS-1$
		fontSizeReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(fontSizeReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createFontSizeCommand(""); //$NON-NLS-1$
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(fontSizePropertySeven);
		setText(getSeven());
		setToolTipText("Font Size 7"); //$NON-NLS-1$
		final ImageDescriptor icon = AbstractUIPlugin.imageDescriptorFromPlugin("FloorPlan", "icons/rename-icon.png"); //$NON-NLS-1$ //$NON-NLS-2$
		if (icon != null)
			setImageDescriptor(icon);
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createFontSizeCommand(getSeven()));
	}


	// ==================== 7. Getters & Setters ====================

	public String getSeven() {
		return seven;
	}

}