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


public class LabelColorAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String labelColorProperty = "labelColorProperty"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private RGB labelColor;

	private CanvasModel model;
	
	private int selection = 0;

	// ==================== 4. Constructors ====================

	public LabelColorAction(final IWorkbenchPart part, final CanvasModel model) {
		super(part);
		this.model = model;
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createLabelColorCommand(final RGB labelColor) {
		final Request labelColorReq = new Request("labelColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newLabelColor", labelColor); //$NON-NLS-1$
		labelColorReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(labelColorReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createLabelColorCommand(null); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(labelColorProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_FONT_BACKGROUND)); 
		setText("Label color" + FPConstPresentation.ELIPSES);
		setEnabled(false);
	}

	@Override
	public void run() {
		final Request labelColorReq = new Request("labelColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newLabelColor", getLabelColor()); //$NON-NLS-1$
		labelColorReq.setExtendedData(reqData);
		if (getSelectedObjects().get(0) != null) {
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(labelColorReq);
		
		execute(cmd);
		
		final ChildModel firstChild  = (ChildModel) object.getModel();

		for (final Object ob : getSelectedObjects()) {

			final EditPart objects = (EditPart)ob;

			final ChildModel children = (ChildModel) objects.getModel();
			children.setLabelColor(firstChild.getLabelColor());
		}
		}
		else{
			// This is not yet working
//			final EditPart object = (EditPart)getSelectedObjects().get(0);
//			final Command cmd = object.getCommand(labelColorReq);
//
//			execute(cmd);
//
//			final ChildModel firstChild  = (ChildModel) object.getModel();
//
//			for (final ChildModel childModel : model.getChildren())
//				childModel.setLabelColor(firstChild.getLabelColor());
		}
	}


	// ==================== 7. Getters & Setters ====================

	public RGB getLabelColor() {
		return labelColor;
	}

}