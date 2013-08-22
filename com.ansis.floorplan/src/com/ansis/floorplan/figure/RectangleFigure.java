package com.ansis.floorplan.figure;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;


public class RectangleFigure extends org.eclipse.draw2d.RectangleFigure {

	// ====================== 2. Instance   =============================

	private Label labelName = new Label();

	private Label labelEtage = new Label();

	private Rectangle etageLabelPosition;

	private RGB lineColor;

	private int fontStyle;

	private int fontSize;

	private RGB fontColor;

	private RGB labelColor;


	// ==================== 4. Constructors ====================

	public RectangleFigure(final Rectangle labelPosition) {

		final XYLayout layout = new XYLayout();
		setLayoutManager(layout);

		labelPosition.x = labelPosition.width/2 - 50;
		labelPosition.y = labelPosition.height/2 - 20;

		labelPosition.height = 20;
		labelPosition.width = 100;

		etageLabelPosition = new Rectangle(labelPosition);

		etageLabelPosition.y = etageLabelPosition.y + 15;

		labelName.setOpaque(true);
		add(labelName, OrderedLayout.ALIGN_CENTER);
		setConstraint(labelName, labelPosition);

		labelEtage.setOpaque(true);
		add(labelEtage, OrderedLayout.ALIGN_CENTER);
		setConstraint(labelEtage, etageLabelPosition);

		setLineStyle(1);
		setLineWidth(5);
	}


	// ==================== 7. Getters & Setters ====================

	public Label getLabelName() {
		return labelName;
	}

	public Label getLabelEtage() {
		return labelEtage;
	}

	public void setName(final String name) {
		labelName.setText(name);
	}

	public void setEtage(final int etage) {
		labelEtage.setText("Etage:"+etage); //$NON-NLS-1$
	}

	public void setLayout(final Rectangle rect) {
		getParent().setConstraint(this, rect);
	}

	public RGB getLineColor() {
		return lineColor;
	}

	public void setLineColor(final RGB lineColor) {
		this.lineColor = lineColor;
	}

	public int getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(final int fontStyle) {
		this.fontStyle = fontStyle;
		this.labelName.setFont(new Font(null, "Lucida Handwriting", getFontSize(), fontStyle)); //$NON-NLS-1$
		this.labelEtage.setFont(new Font(null, "Lucida Handwriting", getFontSize(), fontStyle)); //$NON-NLS-1$
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(final int fontSize) {
		this.fontSize = fontSize;
		this.labelName.setFont(new Font(null, "Lucida Handwriting", fontSize, getFontStyle())); //$NON-NLS-1$
		this.labelEtage.setFont(new Font(null, "Lucida Handwriting", fontSize, getFontStyle())); //$NON-NLS-1$
	}

	public RGB getFontColor() {
		return fontColor;
	}

	public void setFontColor(final RGB fontColor) {
		this.fontColor = fontColor;
	}

	public RGB getLabelColor() {
		return labelColor;
	}

	public void setLabelColor(final RGB labelColor) {
		this.labelColor = labelColor;
	}

}