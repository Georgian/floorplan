package com.ansis.floorplan.core.action.opacity;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.util.FPConstPresentation;


public class OpacitySixtyAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String opacityPropertySixty = "opacityPropertySixty"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private final String sixty = "60"; //$NON-NLS-1$


	// ==================== 4. Constructors ====================

	public OpacitySixtyAction(final IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createOpacityCommand(final String opacity) {
		final Request opacityReq = new Request("opacity"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newOpacity", opacity); //$NON-NLS-1$
		opacityReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(opacityReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createOpacityCommand(FPConstPresentation.EMPTY_STRING); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(opacityPropertySixty);
		setText(getSixty() + FPConstPresentation.PERCENT);  
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createOpacityCommand(getSixty()));
	}


	// ==================== 7. Getters & Setters ====================

	public String getSixty() {
		return sixty;
	}

}