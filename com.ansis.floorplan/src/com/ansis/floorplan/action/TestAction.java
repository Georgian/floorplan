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
import com.ansis.floorplan.wizard.TestWizard;


public class TestAction extends SelectionAction{

	public static final String testProperty = "testProperty";

	public TestAction(final IWorkbenchPart part) {
		super(part);
		setId(testProperty);
		setLazyEnablementCalculation(true);
	}

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createTestCommand(""); //$NON-NLS-1$
		if (cmd == null)
			return false;
		return true;
	}

	public Command createTestCommand(final String opacity) {
		final Request testReq = new Request("rename"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newOpacity", opacity); //$NON-NLS-1$
		testReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(testReq);
		return cmd; 
	}

	@Override
	protected void init() {
		setText("Test..."); //$NON-NLS-1$
		setToolTipText("Test"); //$NON-NLS-1$
		final ImageDescriptor icon = AbstractUIPlugin.imageDescriptorFromPlugin("FloorPlan", "icons/rename-icon.png"); //$NON-NLS-1$ //$NON-NLS-2$
		if (icon != null)
			setImageDescriptor(icon);
		setEnabled(false);
	}

	@Override
	public void run() {
		final Polly polly = getSelectedNode();
		final TestWizard wizard = new TestWizard(Float.toString(polly.getOpacity()));
		final WizardDialog dialog = new WizardDialog(getWorkbenchPart().getSite().getShell(), wizard);
		dialog.create(); 
		dialog.getShell().setSize(640, 480);
		dialog.setTitle("Test wizard"); //$NON-NLS-1$
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