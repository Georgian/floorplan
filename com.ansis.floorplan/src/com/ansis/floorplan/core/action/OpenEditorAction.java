package com.ansis.floorplan.core.action;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import com.ansis.floorplan.core.editor.MyEditorInput;
import com.ansis.floorplan.core.editor.MyGraphicalEditor;


public class OpenEditorAction extends Action {

	@Override
	public void run() {

		try {
			final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			page.openEditor(new MyEditorInput("FloorPlan"), MyGraphicalEditor.ID, false); //$NON-NLS-1$
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getText() {
		return "Open Editor"; //$NON-NLS-1$
	}

}
