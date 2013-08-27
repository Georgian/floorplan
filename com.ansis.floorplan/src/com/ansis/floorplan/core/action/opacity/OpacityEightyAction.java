package com.ansis.floorplan.core.action.opacity;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.util.FPConstPresentation;


public class OpacityEightyAction extends SelectionAction{

	// ==================== 1. Static Fields ========================

	public static final String opacityPropertyEighty = "opacityPropertyEighty"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private final String eighty = "80"; //$NON-NLS-1$


	// ==================== 4. Constructors ====================

	public OpacityEightyAction(final IWorkbenchPart part) {
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
		setId(opacityPropertyEighty);
		setText(getEighty() + FPConstPresentation.PERCENT);  
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createOpacityCommand(getEighty()));
	}


	// ==================== 7. Getters & Setters ====================

	public String getEighty() {
		return eighty;
	}

}