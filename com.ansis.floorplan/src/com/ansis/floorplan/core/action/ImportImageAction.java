package com.ansis.floorplan.core.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import com.ansis.floorplan.core.editor.MyGraphicalEditor;


public class ImportImageAction implements IEditorActionDelegate {

	// ==================== 1. Static Fields ========================

	public static final String ID = "com.ansis.floorplan.importImage.action"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private String image;

	private MyGraphicalEditor editor;


	// ==================== 5. Creators ====================

	@Override
	public void setActiveEditor(final IAction action, final IEditorPart editor) {
		this.editor = (MyGraphicalEditor) editor;
	}


	// ==================== 6. Action Methods ====================

	@Override
	public void run (final IAction action) {

		final FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(), SWT.OPEN);

		fd.setFilterPath("C:/"); //$NON-NLS-1$
		final String[] filterExt = { "*.*", "*.bmp", "*.jpeg", "*.jpg", "*.png", "*.gif" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

		fd.setFilterExtensions(filterExt);
		final String selected = fd.open();

		// If file wasn't selected, return.
		if (selected == null)
			return;

		try {

			InputStream stream = new FileInputStream(selected);
			editor.getModel().setImage(stream);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void selectionChanged(final IAction action, final ISelection selection) {

	}


	// ==================== 7. Getters & Setters ====================

	public String getImage() {
		return image;
	}

	public void setImage(final String image) {
		this.image = image;
	}

}