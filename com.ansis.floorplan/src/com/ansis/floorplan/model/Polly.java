package com.ansis.floorplan.model;

import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;


public class Polly extends ModelTest {

	// ==================== 1. Static Fields ========================

	public static final String PROPERTY_RENAME = "PollyRename"; //$NON-NLS-1$

	public static final String PROPERTY_COLOR = "PollyColor"; //$NON-NLS-1$

	public static final String PROPERTY_OPACITY = "PollyOpacity"; //$NON-NLS-1$

	public static final String PROPERTY_FONT_STYLE = "PollyFontStyle"; //$NON-NLS-1$

	public static final String PROPERTY_FONT_SIZE = "PollyFontSize"; //$NON-NLS-1$

	public static final String PROPERTY_FONT_COLOR = "PollyFontColor"; //$NON-NLS-1$

	public static final String PROPERTY_LABEL_COLOR = "PollyLabelColor"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private PointList list;

	private ModelTest parent;

	private Rectangle bounds;

	private int etage;

	private String name;

	private Rectangle r;

	private Rectangle g;

	private RGB color;

	private RGB lineColor;

	private int opacity;

	private int fontStyle;

	private int fontSize;

	private RGB fontColor;

	private RGB labelColor;


	// ==================== 3. Static Methods ====================

	private static RGB defaultColor() {
		return new RGB(0, 0, 192);
	}

	private static RGB defaultLineColor() {
		return new RGB(0, 0, 255);
	}

	private static int defaultOpacity() {
		return 127;
	}

	private static int defaultFontStyle() {
		return SWT.NORMAL;
	}

	private static int defaultFontSize() {
		return 10;
	}

	private static RGB defaultFontColor() {
		return new RGB(0, 0, 0);
	}

	private static RGB defaultLabelColor() {
		return new RGB(255, 255, 255);
	}


	// ==================== 4. Constructors ====================

	public Polly() {
		this.setColor(defaultColor());
		this.setLineColor(defaultLineColor());
		this.setOpacity(defaultOpacity());
		this.setFontStyle(defaultFontStyle());
		this.setFontSize(defaultFontSize());
		this.setFontColor(defaultFontColor());
		this.setLabelColor(defaultLabelColor());
	}


	// ==================== 7. Getters & Setters ====================

	public void setName(final String name) {
		final String oldName = this.name;
		this.name = name;
		getListeners().firePropertyChange(PROPERTY_RENAME, oldName, this.name);
	}

	public String getName() {
		return this.name;
	}

	public void setEtage(final int etage) {
		this.etage = etage;
	}

	public int getEtage() {
		return this.etage;
	}

	public PointList getList() {
		return list;
	}

	public void setList(final PointList list) {
		this.list = list;
	}

	public void setParent(final ModelTest parent) {
		this.parent = parent;
	}

	public ModelTest getParent() {
		return parent;
	}

	public void setBounds(final Rectangle bounds) {
		this.bounds = bounds;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public Rectangle getR() {
		return r;
	}

	public void setR(final Rectangle r) {
		this.r = r;
	}

	public Rectangle getG() {
		return g;
	}

	public void setG(final Rectangle g) {
		this.g = g;
	}

	public RGB getColor() {
		return color;
	}

	public void setColor(final RGB color) {
		final RGB oldColor = this.color;
		this.color = color;
		getListeners().firePropertyChange(PROPERTY_COLOR, oldColor, this.color);
	}

	public RGB getLineColor() {
		return lineColor;
	}

	public void setLineColor(final RGB lineColor) {
		this.lineColor = lineColor;
	}

	public int getOpacity() {
		return opacity;
	}

	public void setOpacity(final int opacity) {
		final int oldOpacity = this.opacity;
		this.opacity = opacity;
		getListeners().firePropertyChange(PROPERTY_OPACITY, oldOpacity, this.opacity);
	}

	public int getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(final int fontStyle) {
		final int oldFontStyle = this.fontStyle;
		this.fontStyle = fontStyle;
		getListeners().firePropertyChange(PROPERTY_FONT_STYLE, oldFontStyle, this.fontStyle);
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(final int fontSize) {
		final int oldFontSize = this.fontSize;
		this.fontSize = fontSize;
		getListeners().firePropertyChange(PROPERTY_FONT_SIZE, oldFontSize, this.fontSize);
	}

	public RGB getFontColor() {
		return fontColor;
	}

	public void setFontColor(final RGB fontColor) {
		final RGB oldFontColor = this.fontColor;
		this.fontColor = fontColor;
		getListeners().firePropertyChange(PROPERTY_FONT_COLOR, oldFontColor, this.fontColor);
	}

	public RGB getLabelColor() {
		return labelColor;
	}

	public void setLabelColor(final RGB labelColor) {
		final RGB oldLabelColor = this.labelColor;
		this.labelColor = labelColor;
		getListeners().firePropertyChange(PROPERTY_LABEL_COLOR, oldLabelColor, this.labelColor);
	}

}