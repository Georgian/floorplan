package com.ansis.floorplan.action;

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
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.ansis.floorplan.model.Polly;
import com.ansis.floorplan.wizard.OpacityWizard;


public class OpacityAction extends SelectionAction{

	// ==================== 1. Static Fields ========================

	public static final String opacityProperty = "opacityProperty"; //$NON-NLS-1$


	// ==================== 4. Constructors ====================

	public OpacityAction(final IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createTestCommand(final String opacity) {
		final Request opacityReq = new Request("opacity"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newOpacity", opacity); //$NON-NLS-1$
		opacityReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(opacityReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createTestCommand(""); //$NON-NLS-1$
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(opacityProperty);
		setText("Opacity..."); //$NON-NLS-1$
		setToolTipText("Opacity"); //$NON-NLS-1$
		final ImageDescriptor icon = AbstractUIPlugin.imageDescriptorFromPlugin("FloorPlan", "icons/rename-icon.png"); //$NON-NLS-1$ //$NON-NLS-2$
		if (icon != null)
			setImageDescriptor(icon);
		setEnabled(false);
	}

	@Override
	public void run() {
		final Polly polly = getSelectedNode();
		final OpacityWizard wizard = new OpacityWizard(Integer.toString(polly.getOpacity()));
		final WizardDialog dialog = new WizardDialog(getWorkbenchPart().getSite().getShell(), wizard);
		dialog.create(); 
		dialog.getShell().setSize(640, 480);
		dialog.setTitle("Opacity wizard"); //$NON-NLS-1$
		dialog.setMessage(""); //$NON-NLS-1$
		if (dialog.open() == Window.OK) {
			final String opacity = wizard.getOpacityValue(); 
			execute(createTestCommand(opacity)); 
		}
	}


	// ==================== 7. Getters & Setters ====================

	// Helper
	private Polly getSelectedNode() {
		final List<?> objects = getSelectedObjects();
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;
		final EditPart part = (EditPart)objects.get(0);
		return (Polly)part.getModel();
	}

}