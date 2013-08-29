package com.ansis.floorplan.core.action;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.util.FPConstPresentation;


public class FigureColorAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String figureColorProperty = "figureColorProperty"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private RGB figureColor;


	// ==================== 4. Constructors ====================

	public FigureColorAction(final IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createFigureColorCommand(final RGB figureColor) {
		final Request figureColorReq = new Request("figureColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newFigureColor", figureColor); //$NON-NLS-1$
		figureColorReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(figureColorReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createFigureColorCommand(null); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(figureColorProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_COLOR)); 
		setText("Figure color" + FPConstPresentation.ELIPSES);  //$NON-NLS-1$
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createFigureColorCommand(getFigureColor()));
	}


	// ==================== 7. Getters & Setters ====================

	public RGB getFigureColor() {
		return figureColor;
	}

}