package com.ansis.floorplan.core.figure;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;

import com.ansis.floorplan.util.FPConstPresentation;


public class RectangleFigure extends org.eclipse.draw2d.RectangleFigure {

	// ====================== 2. Instance   =============================

	private Label labelName = new Label();

	private RGB lineColor;

	private int fontStyle;

	private int fontSize;

	private RGB fontColor;


	// ==================== 4. Constructors ====================

	public RectangleFigure() {

		final XYLayout layout = new XYLayout();
		setLayoutManager(layout);

		labelName.setOpaque(true);
		add(labelName, OrderedLayout.ALIGN_CENTER);

		// TODO -bulanmaster- Tooltip
//		setToolTip(getLabelName());
	}


	// ==================== 7. Getters & Setters ====================

	public Label getLabelName() {
		return labelName;
	}

	public void setName(final String name) {
		labelName.setText(name);
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
		this.labelName.setFont(new Font(null, FPConstPresentation.EMPTY_STRING, getFontSize(), fontStyle)); 
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(final int fontSize) {
		this.fontSize = fontSize;
		this.labelName.setFont(new Font(null, FPConstPresentation.EMPTY_STRING, fontSize, getFontStyle())); 
	}

	public RGB getFontColor() {
		return fontColor;
	}

	public void setFontColor(final RGB fontColor) {
		this.fontColor = fontColor;
	}

}