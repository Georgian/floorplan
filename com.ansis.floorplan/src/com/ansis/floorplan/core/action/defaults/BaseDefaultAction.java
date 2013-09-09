package com.ansis.floorplan.core.action.defaults;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.core.model.Canvas;
import com.ansis.floorplan.util.color.FPStandardColor;
import com.ansis.floorplan.util.font.FPFontSize;
import com.ansis.floorplan.util.font.FPFontStyle;

public abstract class BaseDefaultAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	private final String defaultOpacity = "50"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private Canvas model;

	private int selection = 0;


	// ==================== 4. Constructors ====================

	public BaseDefaultAction(final IWorkbenchPart part, final Canvas model) {
		super(part);
		this.model = model;
		setLazyEnablementCalculation(true);

	}

	public void prepareSetDefaultFigureColor() 
	{
		final Request defaultFigureColorReq = new Request("defaultFigureColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newDefaultFigureColor", getDefaultFigureColor()); //$NON-NLS-1$
		defaultFigureColorReq.setExtendedData(reqData);
	}

	public void prepareSetDefaultOpacity() {
		final Request defaultOpacityReq = new Request("opacity"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newOpacity", getDefaultOpacity()); //$NON-NLS-1$
		defaultOpacityReq.setExtendedData(reqData);
	}

	public void prepareSetDefaulFontColor() {
		final Request defaultFontColorReq = new Request("defaultFontColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newDefaultFontColor", getDefaultFontColor()); //$NON-NLS-1$
		defaultFontColorReq.setExtendedData(reqData);
	}

	public void prepareSetDefaultFontSize() {
		final Request defaultFontSizeReq = new Request("fontSize"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newFontSize", getDefaultFontSize()); //$NON-NLS-1$
		defaultFontSizeReq.setExtendedData(reqData);
	}

	public void prepareSetDefaultFontStyle() {
		final Request defaultFontStyleReq = new Request("fontStyle"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newFontStyle", getDefaultNormal()); //$NON-NLS-1$
		defaultFontStyleReq.setExtendedData(reqData);
	}

	public void prepareSetDefaultLabelColor() {
		final Request defaultLabelColorReq = new Request("defaultLabelColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newDefaultLabelColor", getDefaultLabelColor()); //$NON-NLS-1$
		defaultLabelColorReq.setExtendedData(reqData);
	}

	protected void executeAction(final Request request) {
		for (final Object ob : getSelectedObjects()) {

			final EditPart object = (EditPart)ob;
			final Command cmd = object.getCommand(request);

			execute(cmd);

		}
	}


	// ==================== 7. Getters & Setters ====================

	public RGB getDefaultFigureColor() {
		return FloorplanActivator.getDefault().getColor(FPStandardColor.BLUE_NAVY).getRGB();
	}

	public String getDefaultOpacity() {
		return defaultOpacity;
	}

	public RGB getDefaultFontColor() {
		return FloorplanActivator.getDefault().getColor(FPStandardColor.BLACK).getRGB();
	}

	public String getDefaultFontSize() {
		return String.valueOf( FPFontSize.NORMAL.getPercent()/10 );
	}

	public String getDefaultNormal() {
		return String.valueOf( FPFontStyle.NORMAL.getStyle() );
	}

	public RGB getDefaultLabelColor() {
		return FloorplanActivator.getDefault().getColor(FPStandardColor.WHITE).getRGB();
	}
}
