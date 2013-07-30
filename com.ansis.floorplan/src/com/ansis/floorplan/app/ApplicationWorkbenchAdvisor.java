package com.ansis.floorplan.app;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;


public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	// ==================== 1. Static Fields ========================

	private static final String PERSPECTIVE_ID = "com.ansis.floorplan.perspective"; //$NON-NLS-1$


	// ==================== 5. Creators ====================

	@Override
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(final IWorkbenchWindowConfigurer configurer) {
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}


	// ==================== 6. Action Methods ====================

	/*@Override
	public void postStartup() {
		try {
			final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			page.openEditor(new MyEditorInput("TutoGEF"), MyGraphicalEditor.ID, false); //$NON-NLS-1$
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}*/


	// ==================== 7. Getters & Setters ====================

	@Override
	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

}