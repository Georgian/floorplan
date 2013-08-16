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
import com.ansis.floorplan.action.ChangeLabelColorAction;
import com.ansis.floorplan.action.font.FontColorAction;
import com.ansis.floorplan.action.font.FontSizeNineAction;
import com.ansis.floorplan.action.font.FontSizeSevenAction;
import com.ansis.floorplan.action.font.FontSizeSixteenAction;
import com.ansis.floorplan.action.font.FontSizeTenAction;
import com.ansis.floorplan.action.font.FontSizeThirteenAction;
import com.ansis.floorplan.action.font.FontSizeTwentyAction;
import com.ansis.floorplan.action.font.FontSizeTwentyfiveAction;
import com.ansis.floorplan.action.font.FontStyleBoldAction;
import com.ansis.floorplan.action.font.FontStyleItalicAction;
import com.ansis.floorplan.action.font.FontStyleNormalAction;
import com.ansis.floorplan.action.opacity.OpacityEightyAction;
import com.ansis.floorplan.action.opacity.OpacityFourtyAction;
import com.ansis.floorplan.action.opacity.OpacityHundredAction;
import com.ansis.floorplan.action.opacity.OpacitySixtyAction;
import com.ansis.floorplan.action.opacity.OpacityTenAction;
import com.ansis.floorplan.action.opacity.OpacityTwentyAction;
import com.ansis.floorplan.core.helper.AEFConstPresentation;


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

		// Color
		action = getActionRegistry().getAction(ChangeColorAction.changeColorProperty);
		menu.appendToGroup(GEFActionConstants.GROUP_VIEW, action);

		// Opacity
		final MenuManager opacitySubmenu = new MenuManager("Figure opacity"); //$NON-NLS-1$

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

		menu.appendToGroup(GEFActionConstants.GROUP_VIEW, opacitySubmenu);

		// Font Style
		final MenuManager fontStyleSubmenu = new MenuManager("Font style"); //$NON-NLS-1$

		action = getActionRegistry().getAction(FontStyleNormalAction.fontStylePropertyNormal);
		fontStyleSubmenu.add(action);

		action = getActionRegistry().getAction(FontStyleBoldAction.fontStylePropertyBold);
		fontStyleSubmenu.add(action);

		action = getActionRegistry().getAction(FontStyleItalicAction.fontStylePropertyItalic);
		fontStyleSubmenu.add(action);

		menu.appendToGroup(GEFActionConstants.GROUP_REST, fontStyleSubmenu);

		// Font Size
		final MenuManager fontSizeSubmenu = new MenuManager("Font size"); //$NON-NLS-1$

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

		menu.appendToGroup(GEFActionConstants.GROUP_REST, fontSizeSubmenu);

		// Font Color
		action = getActionRegistry().getAction(FontColorAction.fontColorProperty);
		menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

		// Label Color
		action = getActionRegistry().getAction(ChangeLabelColorAction.changeLabelColorProperty);
		menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
	}


	// ==================== 7. Getters & Setters ====================

	private ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	private void setActionRegistry(final ActionRegistry registry) {
		actionRegistry = registry;
	}

}