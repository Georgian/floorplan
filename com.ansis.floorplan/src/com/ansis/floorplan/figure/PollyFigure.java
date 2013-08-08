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

		// Creating a new font
		// final Font classFont = new Font(null, "Arial", 12, SWT.BOLD);

		labelName.setForegroundColor(ColorConstants.black);
		labelName.setBackgroundColor(ColorConstants.lightBlue);
		labelName.setFont(new Font(null, "Lucida Handwriting", getFontSize(), getFontStyle())); //$NON-NLS-1$
		labelName.setOpaque(true);
		add(labelName, OrderedLayout.ALIGN_CENTER);
		setConstraint(labelName, g);

		labelEtage.setForegroundColor(ColorConstants.black);
		labelEtage.setFont(new Font(null, "Lucida Handwriting", getFontSize(), getFontStyle())); //$NON-NLS-1$
		labelEtage.setBackgroundColor(ColorConstants.lightBlue);
		labelEtage.setOpaque(true);
		add(labelEtage, OrderedLayout.ALIGN_CENTER);
		setConstraint(labelEtage, g2);
		
//		setForegroundColor(ColorConstants.red);
		setForegroundColor(new Color(null, 0, 0, 255));
		setLineStyle(1);
		setLineWidth(1);

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

	public int getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(final int fontStyle) {
		this.fontStyle = fontStyle;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(final int fontSize) {
		this.fontSize = fontSize;
	}

}