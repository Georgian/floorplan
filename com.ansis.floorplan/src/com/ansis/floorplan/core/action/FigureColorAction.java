package com.ansis.floorplan.core.action;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.FPConstPresentation;


public class FigureColorAction extends BaseAction {

	// ==================== 1. Static Fields ========================

	public static final String figureColorProperty = "figureColorProperty"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private RGB figureColor;


	// ==================== 4. Constructors ====================

	public FigureColorAction(final IWorkbenchPart part, final CanvasModel model) {
		super(part, model);
		setLazyEnablementCalculation(true);
	}

	@Override
	protected void init() {
		setId(figureColorProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_COLOR)); 
		setText("Figure color" + FPConstPresentation.ELIPSES);
		setEnabled(false);
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected void changeProperty(final ChildModel children) {
		final Request figureColorReq = new Request("figureColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newFigureColor", getFigureColor()); //$NON-NLS-1$
		figureColorReq.setExtendedData(reqData);

		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(figureColorReq);

		execute(cmd);		

		final ChildModel firstChild  = (ChildModel) object.getModel();

		for (final Object ob : getSelectedObjects()) {

			final EditPart objects = (EditPart)ob;

			final ChildModel test = (ChildModel) objects.getModel();
			test.setColor(firstChild.getColor());

			if ( test.getFontColorChanged() == false )
				test.setFontColor(firstChild.getColor());
		}
	}
	
	
	// ==================== 7. Getters & Setters ====================

	public RGB getFigureColor() {
		return figureColor;
	}
}