package com.ansis.floorplan.provider;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

import com.ansis.floorplan.action.TestAction;


public class AppContextMenuProvider extends ContextMenuProvider{

	// ====================== 2. Instance Fields =============================

	private ActionRegistry actionRegistry;


	// ==================== 4. Constructors ====================

	public AppContextMenuProvider(final EditPartViewer viewer, final ActionRegistry registry) {
		super(viewer);
		setActionRegistry(registry);
	}


	// ==================== 6. Action Methods ====================

	@Override
	public void buildContextMenu(final IMenuManager menu) {
		IAction action;
		GEFActionConstants.addStandardActionGroups(menu);

		// Undo
		action = getActionRegistry().getAction(ActionFactory.UNDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		// Redo
		action = getActionRegistry().getAction(ActionFactory.REDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		// Delete
		action = getActionRegistry().getAction(ActionFactory.DELETE.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		// Rename
		action = getActionRegistry().getAction(ActionFactory.RENAME.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = getActionRegistry().getAction(TestAction.testProperty);
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
	}


	// ==================== 7. Getters & Setters ====================

	private ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	private void setActionRegistry(final ActionRegistry registry) {
		actionRegistry = registry;
	}

}