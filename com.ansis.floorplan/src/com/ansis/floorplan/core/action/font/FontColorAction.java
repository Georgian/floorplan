package com.ansis.floorplan.core.action.font;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.FPConstPresentation;


public class FontColorAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String fontColorProperty = "fontColorProperty"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private RGB fontColor;


	// ==================== 4. Constructors ====================

	public FontColorAction(final IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createFontColorCommand(final RGB fontColor) {
		final Request fontColorReq = new Request("fontColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newFontColor", fontColor); //$NON-NLS-1$
		fontColorReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(fontColorReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createFontColorCommand(null); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(fontColorProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_FONT_COLOR)); 
		setText("Font color"+FPConstPresentation.ELIPSES);
		setEnabled(false);
	}

	@Override
	public void run() {

		final Request fontColorReq = new Request("fontColor"); 
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newFontColor", getFontColor()); //$NON-NLS-1$
		fontColorReq.setExtendedData(reqData);	

		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(fontColorReq);

		execute(cmd);

		final ChildModel firstChild  = (ChildModel) object.getModel();

		for (final Object ob : getSelectedObjects()) {

			final EditPart objects = (EditPart)ob;

			final ChildModel children = (ChildModel) objects.getModel();
			children.setFontColor(firstChild.getFontColor());
			// Still WIP. - sbrosteanu
			// Should work now. - nmona
		}
	}


	// ==================== 7. Getters & Setters ====================

	public RGB getFontColor() {
		return fontColor;
	}

}