package com.ansis.floorplan.core.action.font.style;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.core.model.Canvas;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.FPConstPresentation;
import com.ansis.floorplan.util.font.FPFontStyle;


public class FontStyleNormalAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String fontStylePropertyNormal = "fontStylePropertyNormal"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private Canvas model;
	
	private int selection = 0;
	
	
	// ==================== 4. Constructors ====================

	public FontStyleNormalAction(final IWorkbenchPart part, final Canvas model) {
		super(part);
		this.model = model;
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
		final Command cmd = createFontStyleCommand(FPConstPresentation.EMPTY_STRING); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(fontStylePropertyNormal);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_FONT_NORMAL)); 
		setText(FPFontStyle.NORMAL.getName()); 
		setEnabled(false);
	}

	@Override
	public void run() {

		final Request fontStyleReq = new Request("fontStyle"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newFontStyle", getNormal()); //$NON-NLS-1$
		fontStyleReq.setExtendedData(reqData);
		for (final Object ob : getSelectedObjects()) {

			final EditPart object = (EditPart)ob;
			final Command cmd = object.getCommand(fontStyleReq);
			selection = 1;
			execute(cmd);

		}
		if (selection == 0) {
			for (final ChildModel childModel : model.getChildren())
				childModel.setFontStyle( FPFontStyle.NORMAL.getStyle() );
		}
	}


	// ==================== 7. Getters & Setters ====================

	public String getNormal() {
		return String.valueOf( FPFontStyle.NORMAL.getStyle() );
	}

}