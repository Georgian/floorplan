package com.ansis.floorplan.app;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import com.ansis.floorplan.core.action.OpenEditorAction;


public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	// ==================== 4. Constructors ====================

	public ApplicationActionBarAdvisor(final IActionBarConfigurer configurer) {
		super(configurer);
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected void makeActions(final IWorkbenchWindow window) {

	}

	@Override
	protected void fillMenuBar(final IMenuManager menuBar) {
		final MenuManager fileMenu = new MenuManager("&File", "file");  //$NON-NLS-1$ //$NON-NLS-2$
		fileMenu.add(new OpenEditorAction());
		menuBar.add(fileMenu);
	}

}