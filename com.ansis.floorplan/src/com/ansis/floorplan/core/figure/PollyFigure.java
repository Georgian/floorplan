package com.ansis.floorplan.core.figure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;


public class PollyFigure extends PolygonShape implements IFigure {

	// ====================== 2. Instance Fields =============================

	private PointList list = new PointList();

	private Label labelName = new Label();

	private Label labelEtage = new Label();

	private RGB lineColor;

	private int fontStyle;

	private int fontSize;

	private RGB fontColor;

	private RGB labelColor;

	private Rectangle etageLabelPosition;


	// ==================== 4. Constructors ====================

	public PollyFigure(final Rectangle labelPosition) {

		final XYLayout layout = new XYLayout();
		setLayoutManager(layout);

		etageLabelPosition = new Rectangle(labelPosition);

		// Label positions/sizes
		etageLabelPosition.y = etageLabelPosition.y + 15;
		etageLabelPosition.height = etageLabelPosition.height +20;

		labelName.setOpaque(true);
		add(labelName, OrderedLayout.ALIGN_CENTER);
		setConstraint(labelName, labelPosition);

		labelEtage.setOpaque(true);
		add(labelEtage, OrderedLayout.ALIGN_CENTER);
		setConstraint(labelEtage, etageLabelPosition);

		setLineStyle(1);
		setLineWidth(5);

		setPoints(list);
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

	public void setList(final PointList list) {
		setPoints(list);
		this.list = list;
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
		this.labelName.setFont(new Font(null, "", getFontSize(), fontStyle)); //$NON-NLS-1$
		this.labelEtage.setFont(new Font(null, "", getFontSize(), fontStyle)); //$NON-NLS-1$
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(final int fontSize) {
		this.fontSize = fontSize;
		this.labelName.setFont(new Font(null, "", fontSize, getFontStyle())); //$NON-NLS-1$
		this.labelEtage.setFont(new Font(null, "", fontSize, getFontStyle())); //$NON-NLS-1$
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