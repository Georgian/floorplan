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
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.FPConstPresentation;


public class FigureColorAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String figureColorProperty = "figureColorProperty"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private RGB figureColor;

	private final CanvasModel model;
	
	private int selection = 0;


	// ==================== 4. Constructors ====================

	public FigureColorAction(final IWorkbenchPart part, final CanvasModel model) {
		super(part);
		this.model = model;
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
		setText("Figure color" + FPConstPresentation.ELIPSES);
		setEnabled(false);
	}

	@Override
	public void run() {
		final Request figureColorReq = new Request("figureColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newFigureColor", getFigureColor()); //$NON-NLS-1$
		figureColorReq.setExtendedData(reqData);

		if (getSelectedObjects().get(0) != null) {
			final EditPart object = (EditPart)getSelectedObjects().get(0);
			final Command cmd = object.getCommand(figureColorReq);

			execute(cmd);

			final ChildModel firstChild  = (ChildModel) object.getModel();

			for (final Object ob : getSelectedObjects()) {

				final EditPart objects = (EditPart)ob;

				final ChildModel children = (ChildModel) objects.getModel();
				children.setColor(firstChild.getColor());
			}
		}
		else {
			// This is not yet working
//			final EditPart object = (EditPart)getSelectedObjects().get(0);
//			final Command cmd = object.getCommand(figureColorReq);
//
//			execute(cmd);
//
//			final ChildModel firstChild  = (ChildModel) object.getModel();
//
//			for (final ChildModel childModel : model.getChildren())
//				childModel.setColor(firstChild.getColor());
		}
	}


	// ==================== 7. Getters & Setters ====================

	public RGB getFigureColor() {
		return figureColor;
	}

}