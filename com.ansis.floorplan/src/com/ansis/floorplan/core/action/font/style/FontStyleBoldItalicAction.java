package com.ansis.floorplan.core.action.font.style;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.util.FPConstPresentation;
import com.ansis.floorplan.util.font.FPFontStyle;


public class FontStyleBoldItalicAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String fontStylePropertyBoldItalic = "fontStylePropertyBoldItalic"; //$NON-NLS-1$


	// ==================== 4. Constructors ====================

	public FontStyleBoldItalicAction(final IWorkbenchPart part) {
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
		final Command cmd = createFontStyleCommand(FPConstPresentation.EMPTY_STRING); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(fontStylePropertyBoldItalic);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_FONT_BOLDITALIC)); 
		setText(FPFontStyle.BOLD_ITALIC.getName()); 
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createFontStyleCommand(getBoldItalic()));
	}


	// ==================== 7. Getters & Setters ====================

	public String getBoldItalic() {
		return String.valueOf( FPFontStyle.BOLD_ITALIC.getStyle() );
	}

}