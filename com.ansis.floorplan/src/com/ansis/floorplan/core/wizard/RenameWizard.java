package com.ansis.floorplan.core.wizard;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


public class RenameWizard extends Wizard {

	// ====================== 2. Instance Fields =============================

	private String oldName;

	private String newName;


	// ==================== 4. Constructors ====================

	public RenameWizard(final String oldName) {
		this.oldName = oldName;
		this.newName = null;
		addPage(new RenamePage("MyRenamePage")); //$NON-NLS-1$
	}


	// ==================== 6. Action Methods ====================

	@Override
	public boolean performFinish() {
		final RenamePage page = (RenamePage)getPage("MyRenamePage"); //$NON-NLS-1$
		if (page.nameText.getText().isEmpty()) {
			page.setErrorMessage("You left the field empty!");  //$NON-NLS-1$
			return false;
		}
		newName = page.nameText.getText();
		return true;
	}


	// ==================== 7. Getters & Setters ====================

	public String getRenameValue() { 
		return newName;
	}


	// =======================================================
	// 			 19. Inline Classes 
	// =======================================================

	private class RenamePage extends WizardPage {

		// ====================== 2. Instance Fields =============================

		public Text nameText;


		// ==================== 4. Constructors ====================

		public RenamePage(final String pageName) {
			super(pageName);
			setTitle("Rename"); //$NON-NLS-1$
			setDescription("Rename a component"); //$NON-NLS-1$
		}


		// ==================== 5. Creators ====================

		@Override
		public void createControl(final Composite parent) { 
			final Composite composite = new Composite(parent, SWT.NONE);

			final Label nameLabel = new Label(composite, SWT.NONE);
			nameLabel.setText("Rename to: "); //$NON-NLS-1$

			nameText = new Text(composite, SWT.NONE);
			nameText.setText(oldName);

			final RowLayout layout = new RowLayout();
			composite.setLayout(layout);

			setControl(composite);
		}

	}

}