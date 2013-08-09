package com.ansis.floorplan.provider;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.actions.ActionFactory;

import com.ansis.floorplan.action.ChangeColorAction;
import com.ansis.floorplan.action.FontSizeNineAction;
import com.ansis.floorplan.action.FontSizeSevenAction;
import com.ansis.floorplan.action.FontSizeSixteenAction;
import com.ansis.floorplan.action.FontSizeTenAction;
import com.ansis.floorplan.action.FontSizeThirteenAction;
import com.ansis.floorplan.action.FontSizeTwentyAction;
import com.ansis.floorplan.action.FontSizeTwentyfiveAction;
import com.ansis.floorplan.action.FontStyleBoldAction;
import com.ansis.floorplan.action.FontStyleItalicAction;
import com.ansis.floorplan.action.FontStyleNormalAction;
import com.ansis.floorplan.action.OpacityEightyAction;
import com.ansis.floorplan.action.OpacityFourtyAction;
import com.ansis.floorplan.action.OpacityHundredAction;
import com.ansis.floorplan.action.OpacitySixtyAction;
import com.ansis.floorplan.action.OpacityTenAction;
import com.ansis.floorplan.action.OpacityTwentyAction;


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

		// TODO Color
		action = getActionRegistry().getAction(ChangeColorAction.changeColorProperty);
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		// Opacity
		final MenuManager opacitySubmenu = new MenuManager("Opacity"); //$NON-NLS-1$

		action = getActionRegistry().getAction(OpacityTenAction.opacityPropertyTen);
		opacitySubmenu.add(action);

		action = getActionRegistry().getAction(OpacityTwentyAction.opacityPropertyTwenty);
		opacitySubmenu.add(action);

		action = getActionRegistry().getAction(OpacityFourtyAction.opacityPropertyFourty);
		opacitySubmenu.add(action);

		action = getActionRegistry().getAction(OpacitySixtyAction.opacityPropertySixty);
		opacitySubmenu.add(action);

		action = getActionRegistry().getAction(OpacityEightyAction.opacityPropertyEighty);
		opacitySubmenu.add(action);

		action = getActionRegistry().getAction(OpacityHundredAction.opacityPropertyHundred);
		opacitySubmenu.add(action);

		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, opacitySubmenu);

		// Font Style
		final MenuManager fontStyleSubmenu = new MenuManager("Font Style"); //$NON-NLS-1$

		action = getActionRegistry().getAction(FontStyleNormalAction.fontStylePropertyNormal);
		fontStyleSubmenu.add(action);

		action = getActionRegistry().getAction(FontStyleBoldAction.fontStylePropertyBold);
		fontStyleSubmenu.add(action);

		action = getActionRegistry().getAction(FontStyleItalicAction.fontStylePropertyItalic);
		fontStyleSubmenu.add(action);

		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, fontStyleSubmenu);

		// Font Size
		final MenuManager fontSizeSubmenu = new MenuManager("Font Size"); //$NON-NLS-1$

		action = getActionRegistry().getAction(FontSizeSevenAction.fontSizePropertySeven);
		fontSizeSubmenu.add(action);

		action = getActionRegistry().getAction(FontSizeNineAction.fontSizePropertyNine);
		fontSizeSubmenu.add(action);

		action = getActionRegistry().getAction(FontSizeTenAction.fontSizePropertyTen);
		fontSizeSubmenu.add(action);

		action = getActionRegistry().getAction(FontSizeThirteenAction.fontSizePropertyThirteen);
		fontSizeSubmenu.add(action);

		action = getActionRegistry().getAction(FontSizeSixteenAction.fontSizePropertySixteen);
		fontSizeSubmenu.add(action);

		action = getActionRegistry().getAction(FontSizeTwentyAction.fontSizePropertyTwenty);
		fontSizeSubmenu.add(action);

		action = getActionRegistry().getAction(FontSizeTwentyfiveAction.fontSizePropertyTwentyfive);
		fontSizeSubmenu.add(action);

		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, fontSizeSubmenu);
	}


	// ==================== 7. Getters & Setters ====================

	private ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	private void setActionRegistry(final ActionRegistry registry) {
		actionRegistry = registry;
	}

}