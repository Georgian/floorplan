package com.ansis.floorplan.core.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;

public abstract class BaseAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	
	// ====================== 2. Instance Fields =============================

	private CanvasModel model;

	// ==================== 4. Constructors ====================

	public BaseAction(final IWorkbenchPart part, final CanvasModel model) {
		super(part);
		setLazyEnablementCalculation(true);
		this.model = model;
	}

	// ==================== 6. Action Methods ====================

	@Override
	public void run() 
	{
		for (final ChildModel children : getSelected()) 
		{
			changeProperty(children);
			break;
		}
		
		final ChildModel firstChild = getSelected().get(0);
		for (final ChildModel children : getSelected())
		{
			children.setColor(firstChild.getColor());
			if ( children.getFontColorChanged() == false )
				children.setFontColor(children.getColor());
		}
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

}