package com.ansis.floorplan.core.provider;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.actions.ActionFactory;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.core.action.ChangeColorAction;
import com.ansis.floorplan.core.action.ChangeLabelColorAction;
import com.ansis.floorplan.core.action.defaults.FontSizeDefaultAction;
import com.ansis.floorplan.core.action.defaults.OpacityDefaultAction;
import com.ansis.floorplan.core.action.font.FontColorAction;
import com.ansis.floorplan.core.action.font.FontSizeNineAction;
import com.ansis.floorplan.core.action.font.FontSizeSevenAction;
import com.ansis.floorplan.core.action.font.FontSizeSixteenAction;
import com.ansis.floorplan.core.action.font.FontSizeTenAction;
import com.ansis.floorplan.core.action.font.FontSizeThirteenAction;
import com.ansis.floorplan.core.action.font.FontSizeTwentyAction;
import com.ansis.floorplan.core.action.font.FontSizeTwentyfiveAction;
import com.ansis.floorplan.core.action.font.FontStyleBoldAction;
import com.ansis.floorplan.core.action.font.FontStyleBoldItalicAction;
import com.ansis.floorplan.core.action.font.FontStyleItalicAction;
import com.ansis.floorplan.core.action.font.FontStyleNormalAction;
import com.ansis.floorplan.core.action.opacity.OpacityEightyAction;
import com.ansis.floorplan.core.action.opacity.OpacityFourtyAction;
import com.ansis.floorplan.core.action.opacity.OpacityHundredAction;
import com.ansis.floorplan.core.action.opacity.OpacitySixtyAction;
import com.ansis.floorplan.core.action.opacity.OpacityTenAction;
import com.ansis.floorplan.core.action.opacity.OpacityTwentyAction;


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
		menu.appendToGroup(GEFActionConstants.GROUP_COPY, action);

		// Color
		action = getActionRegistry().getAction(ChangeColorAction.changeColorProperty);
		menu.appendToGroup(GEFActionConstants.GROUP_VIEW, action);

		// Opacity
		final MenuManager opacitySubmenu = new MenuManager("Figure opacity", FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_OPACITY), "opacitySubmenuID"); //$NON-NLS-1$ //$NON-NLS-2$ 

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
		final MenuManager fontStyleSubmenu = new MenuManager("Font style", FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_FONT_NORMAL), "fontStyleSubmenuID"); //$NON-NLS-1$ //$NON-NLS-2$ 

		action = getActionRegistry().getAction(FontStyleNormalAction.fontStylePropertyNormal);
		fontStyleSubmenu.add(action);

		action = getActionRegistry().getAction(FontStyleBoldAction.fontStylePropertyBold);
		fontStyleSubmenu.add(action);

		action = getActionRegistry().getAction(FontStyleItalicAction.fontStylePropertyItalic);
		fontStyleSubmenu.add(action);
		
		action = getActionRegistry().getAction(FontStyleBoldItalicAction.fontStylePropertyBoldItalic);
		fontStyleSubmenu.add(action);

		menu.appendToGroup(GEFActionConstants.GROUP_REST, fontStyleSubmenu);

		// Font Size
		final MenuManager fontSizeSubmenu = new MenuManager("Font size", FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_FONT_SIZE), "fontSizeSubmenuID"); //$NON-NLS-1$ //$NON-NLS-2$ 

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
		
		// Defaults Sub menu-- opacity, font size, font color, font background color, line color, figure color. 
		final MenuManager defaultsSubmenu = new MenuManager("Default", "defaultsSubmenuID");  //$NON-NLS-1$//$NON-NLS-2$
		
		action = getActionRegistry().getAction(OpacityDefaultAction.opacityPropertyDefault);
		defaultsSubmenu.add(action);
		
		action = getActionRegistry().getAction(FontSizeDefaultAction.fontSizePropertyDefault);
		defaultsSubmenu.add(action);
		
		menu.appendToGroup(GEFActionConstants.GROUP_SAVE, defaultsSubmenu );
	}


	// ==================== 7. Getters & Setters ====================

	private ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	private void setActionRegistry(final ActionRegistry registry) {
		actionRegistry = registry;
	}

}