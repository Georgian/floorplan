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


/**
 * 
 * @author sbrosteanu
 *
 */
public class DefaultOpacityAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String defaultOpacityProperty = "defaultOpacityProperty"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private final String defaultOpacity = "50"; //$NON-NLS-1$

	private CanvasModel model;

	private int selection = 0;


	// ==================== 4. Constructors ====================

	public DefaultOpacityAction(final IWorkbenchPart part, final CanvasModel model) {
		super(part);
		this.model = model;
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createDefaultOpacityCommand(final String defaultOpacity) {
		final Request defaultOpacityReq = new Request("opacity"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newOpacity", defaultOpacity); //$NON-NLS-1$
		defaultOpacityReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(defaultOpacityReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createDefaultOpacityCommand(FPConstPresentation.EMPTY_STRING); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(defaultOpacityProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_OPACITY)); 
		setText("Default opacity (" + getDefaultOpacity() + FPConstPresentation.PERCENT + ")");
		setEnabled(false);
	}

	@Override
	public void run() {

		final Request defaultOpacityReq = new Request("opacity"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newOpacity", defaultOpacity); //$NON-NLS-1$
		defaultOpacityReq.setExtendedData(reqData);

		for (final Object ob : getSelectedObjects()) {

			final EditPart object = (EditPart)ob;
			final Command cmd = object.getCommand(defaultOpacityReq);
			selection = 1;
			execute(cmd);

		}

		if (selection == 0) {
			for (final ChildModel childModel : model.getChildren())
				childModel.setOpacity(Integer.parseInt(getDefaultOpacity()));
		}
	}


	// ==================== 7. Getters & Setters ====================

	public String getDefaultOpacity() {
		return defaultOpacity;
	}

}