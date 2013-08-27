package com.ansis.floorplan.core.action;

import java.util.HashMap;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.core.wizard.RenameWizard;
import com.ansis.floorplan.util.FPConstPresentation;


public class RenameAction extends SelectionAction {

	// ==================== 4. Constructors ====================

	public RenameAction(final IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}


	// ==================== 5. Creators ====================

	public Command createRenameCommand(final String name) {
		final Request renameReq = new Request("rename"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newName", name); //$NON-NLS-1$
		renameReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(renameReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected void init() {
		setText("Rename"+FPConstPresentation.ELIPSES); //$NON-NLS-1$
		setId(ActionFactory.RENAME.getId());
		final ImageDescriptor icon = AbstractUIPlugin.imageDescriptorFromPlugin("FloorPlan", "icons/rename-icon.png"); //$NON-NLS-1$ //$NON-NLS-2$
		if (icon != null)
			setImageDescriptor(icon);
		setEnabled(false);
	}

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createRenameCommand(""); //$NON-NLS-1$
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	public void run() {
		final ChildModel polly = getSelectedNode();
		final RenameWizard wizard = new RenameWizard(polly.getName());
		final WizardDialog dialog = new WizardDialog(getWorkbenchPart().getSite().getShell(), wizard);
		dialog.create(); 
		dialog.getShell().setSize(640, 480);
		dialog.setTitle("Rename wizard"); //$NON-NLS-1$
		dialog.setMessage(""); //$NON-NLS-1$
		if (dialog.open() == Window.OK) {
			final String name = wizard.getRenameValue(); 
			execute(createRenameCommand(name)); 
		}
	}

	// ==================== 7. Getters & Setters ====================

	// Helper
	private ChildModel getSelectedNode() {
		final List<?> objects = getSelectedObjects();
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;
		final EditPart part = (EditPart)objects.get(0);
		return (ChildModel)part.getModel();
	}
}
