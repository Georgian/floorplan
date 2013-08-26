package com.ansis.floorplan.app;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;

import com.ansis.floorplan.core.factory.ModelTestCreationFactory;


public class MyTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener {

	// ==================== 4. Constructors ====================

	public MyTemplateTransferDropTargetListener(final EditPartViewer viewer) {
		super(viewer);
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	protected CreationFactory getFactory(final Object template) {
		return new ModelTestCreationFactory((Class<?>)template);
	}

}