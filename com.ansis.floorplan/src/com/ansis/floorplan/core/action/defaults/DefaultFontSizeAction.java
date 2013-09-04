package com.ansis.floorplan.core.action.defaults;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.util.FPConstPresentation;
import com.ansis.floorplan.util.font.FPFontSize;


/**
 * 
 * @author sbrosteanu
 *
 */
public class DefaultFontSizeAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String defaultFontSizeProperty = "defaultFontSizeProperty"; //$NON-NLS-1$


	// ==================== 4. Constructors ====================

	public DefaultFontSizeAction(final IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createFontSizeCommand(final String defaultFontSize) {
		final Request defaultFontSizeReq = new Request("fontSize"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newFontSize", defaultFontSize); //$NON-NLS-1$
		defaultFontSizeReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(defaultFontSizeReq);
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
		setId(defaultFontSizeProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_FONT_SIZE)); 
		setText("Default font size (" + FPFontSize.NORMAL.getPercent()/10 + ")");
		setEnabled(false);
	}

	@Override
	public void run() {
		
		final Request defaultFontSizeReq = new Request("fontSize"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newFontSize", getDefaultFontSize()); //$NON-NLS-1$
		defaultFontSizeReq.setExtendedData(reqData);
		
		for (Object ob : getSelectedObjects()) {

			final EditPart object = (EditPart)ob;
			final Command cmd = object.getCommand(defaultFontSizeReq);

			execute(cmd);

		}
		
		
		
		//execute(createFontSizeCommand(getDefaultFontSize()));
	}


	// ==================== 7. Getters & Setters ====================

	public String getDefaultFontSize() {
		return String.valueOf( FPFontSize.NORMAL.getPercent()/10 );
	}

}