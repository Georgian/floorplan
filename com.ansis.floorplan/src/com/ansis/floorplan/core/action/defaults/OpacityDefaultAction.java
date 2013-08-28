package com.ansis.floorplan.core.action.defaults;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.util.FPConstPresentation;

/**
 * 
 * @author PsYCh
 *
 */


public class OpacityDefaultAction extends SelectionAction{

	// ==================== 1. Static Fields ========================

	public static final String opacityPropertyDefault = "opacityPropertyDefault"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private final String defaultOpacity = "50"; //$NON-NLS-1$


	// ==================== 4. Constructors ====================

	public OpacityDefaultAction(final IWorkbenchPart part) {
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
		setId(opacityPropertyDefault);
		setText("Default Opacity");  
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createOpacityCommand(getDefault()));
	}


	// ==================== 7. Getters & Setters ====================

	public String getDefault() {
		return defaultOpacity;
	}

}