package com.ansis.floorplan.figure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;


public class RectangleFigure extends PolygonShape implements IFigure{

	// ====================== 2. Instance Fields =============================

	private Label labelName = new Label();

	private Label labelEtage = new Label();

	private RGB lineColor;

	private int fontStyle;

	private int fontSize;

	private RGB fontColor;

	private RGB labelColor;

	private Rectangle r;


	// ==================== 4. Constructors ====================

	public RectangleFigure() {

		final XYLayout layout = new XYLayout();
		setLayoutManager(layout);

		labelName.setOpaque(true);
		add(labelName, OrderedLayout.ALIGN_CENTER);
		setConstraint(labelName, new Rectangle(0, 0, 100, 20));

		labelEtage.setOpaque(true);
		add(labelEtage, OrderedLayout.ALIGN_CENTER);
		setConstraint(labelEtage, new Rectangle(0, 20, 100, 40));

		setLineStyle(1);
		setLineWidth(5);
	}


	// ==================== 7. Getters & Setters ====================

	public Rectangle getR() {
		return r;
	}

	public void setR(final Rectangle r) {
		this.r = r;
	}

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