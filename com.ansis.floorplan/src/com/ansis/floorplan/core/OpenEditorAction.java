package com.ansis.floorplan.core;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;


public class OpenEditorAction extends Action {

	@Override
	public void run() {

		try {
			final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			page.openEditor(new MyEditorInput("TutoGEF"), MyGraphicalEditor.ID, false); //$NON-NLS-1$
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getText() {
		return "Open Editor"; //$NON-NLS-1$
	}

}
