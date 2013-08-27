package com.ansis.floorplan.core.action.font;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.util.FPConstPresentation;


public class FontSizeThirteenAction extends SelectionAction{

	// ==================== 1. Static Fields ========================

	public static final String fontSizePropertyThirteen = "fontSizePropertyThirteen"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private final String thirteen = "13"; //$NON-NLS-1$


	// ==================== 4. Constructors ====================

	public FontSizeThirteenAction(final IWorkbenchPart part) {
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
		final Command cmd = createFontSizeCommand(FPConstPresentation.EMPTY_STRING); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(fontSizePropertyThirteen);
		setText(getThirteen());
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createFontSizeCommand(getThirteen()));
	}


	// ==================== 7. Getters & Setters ====================

	public String getThirteen() {
		return thirteen;
	}

}