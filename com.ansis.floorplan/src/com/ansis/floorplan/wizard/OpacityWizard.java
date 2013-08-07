package com.ansis.floorplan.wizard;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


public class OpacityWizard extends Wizard {

	// ====================== 2. Instance Fields =============================

	private String oldOpacity;

	private String newOpacity;


	// ==================== 4. Constructors ====================

	public OpacityWizard(final String oldOpacity) {
		this.oldOpacity = oldOpacity;
		this.newOpacity = null;
		addPage(new OpacityPage("MyOpacityPage")); //$NON-NLS-1$
	}


	// ==================== 6. Action Methods ====================

	@Override
	public boolean performFinish() {
		final OpacityPage page = (OpacityPage)getPage("MyOpacityPage"); //$NON-NLS-1$
		if (page.opacityText.getText().isEmpty()) {
			page.setErrorMessage("You left the field empty!");  //$NON-NLS-1$
			return false;
		}
		newOpacity = page.opacityText.getText();
		return true;
	}


	// ==================== 7. Getters & Setters ====================

	public String getOpacityValue() { 
		return newOpacity;
	}


	// =======================================================
	// 			 19. Inline Classes 
	// =======================================================

	private class OpacityPage extends WizardPage {

		// ====================== 2. Instance Fields =============================

		public Text opacityText;


		// ==================== 4. Constructors ====================

		public OpacityPage(final String pageName) {
			super(pageName);
			setTitle("Opacity"); //$NON-NLS-1$
			setDescription("Opacity"); //$NON-NLS-1$
		}


		// ==================== 5. Creators ====================

		@Override
		public void createControl(final Composite parent) { 
			final Composite composite = new Composite(parent, SWT.NONE);

			final Label opacityLabel = new Label(composite, SWT.NONE);
			opacityLabel.setText("Opacity: "); //$NON-NLS-1$

			opacityText = new Text(composite, SWT.NONE);
			opacityText.setText(oldOpacity);

			final RowLayout layout = new RowLayout();
			composite.setLayout(layout);

			setControl(composite);
		}

	}

}