package com.ansis.floorplan.core.action.defaults;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.FPConstPresentation;
import com.ansis.floorplan.util.font.FPFontStyle;


public class DefaultFontStyleAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String defaultFontStyleProperty = "defaultFontStyleProperty"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private CanvasModel model;

	private int selection = 0;

	
	// ==================== 4. Constructors ====================

	public DefaultFontStyleAction(final IWorkbenchPart part, final CanvasModel model) {
		super(part);
		this.model = model;
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createDefaultFontStyleCommand(final String defaultFontStyle) {
		final Request defaultFontStyleReq = new Request("fontStyle"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newFontStyle", getDefaultNormal()); //$NON-NLS-1$
		defaultFontStyleReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(defaultFontStyleReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createDefaultFontStyleCommand(FPConstPresentation.EMPTY_STRING); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(defaultFontStyleProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_FONT_NORMAL)); 
		setText("Default font style (Normal)");
		setEnabled(false);
	}

	@Override
	public void run() {
		
		final Request defaultFontStyleReq = new Request("fontStyle"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newFontStyle", getDefaultNormal()); //$NON-NLS-1$
		defaultFontStyleReq.setExtendedData(reqData);
		for (final Object ob : getSelectedObjects()) {

			final EditPart object = (EditPart)ob;
			final Command cmd = object.getCommand(defaultFontStyleReq);
			selection = 1;
			execute(cmd);

		}
		
		if (selection == 0) {
			for (final ChildModel childModel : model.getChildren())
				childModel.setFontStyle( FPFontStyle.NORMAL.getStyle() );
		}
	}


	// ==================== 7. Getters & Setters ====================

	public String getDefaultNormal() {
		return String.valueOf( FPFontStyle.NORMAL.getStyle() );
	}

}