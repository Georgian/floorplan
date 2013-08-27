package com.ansis.floorplan.core.action.font;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.util.font.FPFontStyle;


public class FontStyleNormalAction extends SelectionAction{

	// ==================== 1. Static Fields ========================

	public static final String fontStylePropertyNormal = "fontStylePropertyNormal"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private final String normal = "0"; //$NON-NLS-1$


	// ==================== 4. Constructors ====================

	public FontStyleNormalAction(final IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createFontStyleCommand(final String fontStyle) {
		final Request fontStyleReq = new Request("fontStyle"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newFontStyle", fontStyle); //$NON-NLS-1$
		fontStyleReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(fontStyleReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createFontStyleCommand(""); //$NON-NLS-1$
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(fontStylePropertyNormal);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor("icons/font/fontNormal.png")); //$NON-NLS-1$
		setText(FPFontStyle.NORMAL.getName()); 
		final ImageDescriptor icon = AbstractUIPlugin.imageDescriptorFromPlugin("FloorPlan", "icons/rename-icon.png"); //$NON-NLS-1$ //$NON-NLS-2$
		if (icon != null)
			setImageDescriptor(icon);
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createFontStyleCommand(getNormal()));
	}


	// ==================== 7. Getters & Setters ====================

	public String getNormal() {
		return normal;
	}
	
}