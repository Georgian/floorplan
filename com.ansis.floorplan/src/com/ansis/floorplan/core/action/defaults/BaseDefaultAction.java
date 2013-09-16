package com.ansis.floorplan.core.action.defaults;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.color.FPStandardColor;
import com.ansis.floorplan.util.font.FPFontSize;
import com.ansis.floorplan.util.font.FPFontStyle;


public abstract class BaseDefaultAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	private final String defaultOpacity = "50"; //$NON-NLS-1$
	
	// ====================== 2. Instance Fields =============================

	private CanvasModel model;

	// ==================== 4. Constructors ====================

	public BaseDefaultAction(final IWorkbenchPart part, final CanvasModel model) {
		super(part);
		setLazyEnablementCalculation(true);
		this.model = model;
	}


	// ==================== 6. Action Methods ====================

	@Override
	public void run() 
	{
		for (final ChildModel children : getSelected()) 
			changeProperty(children);
	}

	abstract protected void changeProperty(final ChildModel children);

	@Override
	protected boolean calculateEnabled() {
//		System.out.println("!getSelected().isEmpty() : " + !getSelected().isEmpty());
		return /*!getSelected().isEmpty()*/ true;
	}


	// ==================== 13. Utility Methods ====================

	public List<ChildModel> getSelected()
	{
		final List<ChildModel> selectedList = new ArrayList<>();

		for (final Object object : getSelectedObjects())
			if (object instanceof EditPart)
				if (((EditPart) object).getModel() instanceof ChildModel)
					selectedList.add((ChildModel) ((EditPart) object).getModel());

		if (selectedList.isEmpty())
		{
			for ( final ChildModel childModel : model.getChildren())
				selectedList.add(childModel);
			System.out.println(model.getChildren().size());
		}
		
		return selectedList;
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
