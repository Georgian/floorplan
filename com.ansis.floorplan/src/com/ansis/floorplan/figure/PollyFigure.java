package com.ansis.floorplan.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;


public class PollyFigure extends PolygonShape implements IFigure {

	// ==================== 1. Static Fields ========================

	public static final int POLLY_FIGURE_DEFWIDTH = 250;

	public static final int POLLY_FIGURE_DEFHEIGHT = 150;


	// ====================== 2. Instance Fields =============================

	private PointList list = new PointList();

	private Label labelName = new Label();

	private Label labelEtage = new Label();

	private Color lineColor;

	private int fontStyle;

	private int fontSize;

	private Rectangle r;

	private Rectangle g2;


	// ==================== 4. Constructors ====================

	public PollyFigure(final Rectangle g) {

		final XYLayout layout = new XYLayout();
		setLayoutManager(layout);

		g2 = new Rectangle(g);

		// Label positions/sizes
		g2.y = g2.y + 15;
		g2.height = g2.height +20;

		labelName.setForegroundColor(ColorConstants.black);
		labelName.setBackgroundColor(ColorConstants.lightBlue);
		labelName.setOpaque(true);
		add(labelName, OrderedLayout.ALIGN_CENTER);
		setConstraint(labelName, g);

		labelEtage.setForegroundColor(ColorConstants.black);
		labelEtage.setBackgroundColor(ColorConstants.lightBlue);
		labelEtage.setOpaque(true);
		add(labelEtage, OrderedLayout.ALIGN_CENTER);
		setConstraint(labelEtage, g2);

		setLineStyle(1);
		setLineWidth(5);

		setPoints(list);
	}


	// ==================== 7. Getters & Setters ====================

	public Rectangle getR() {
		return r;
	}

	public void setR(final Rectangle r) {
		this.r = r;
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

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(final Color lineColor) {
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

}