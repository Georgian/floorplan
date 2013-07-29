package com.ansis.floorplan.app;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;


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
	}

}