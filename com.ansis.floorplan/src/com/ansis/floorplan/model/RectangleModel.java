package com.ansis.floorplan.model;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;


/**
 *
 * @author ggrec
 *
 */
public class RectangleModel extends ChildModel {

	// ==================== 1. Static Fields ========================

	public static final String PROPERTY_RENAME = "RectangleRename"; //$NON-NLS-1$

	public static final String PROPERTY_COLOR = "RectangleColor"; //$NON-NLS-1$

	public static final String PROPERTY_OPACITY = "RectangleOpacity"; //$NON-NLS-1$

	public static final String PROPERTY_FONT_STYLE = "RectangleFontStyle"; //$NON-NLS-1$

	public static final String PROPERTY_FONT_SIZE = "RectangleFontSize"; //$NON-NLS-1$

	public static final String PROPERTY_FONT_COLOR = "RectangleFontColor"; //$NON-NLS-1$

	public static final String PROPERTY_LABEL_COLOR = "RectangleLabelColor"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private Rectangle r;
	
	private int etage;

	private String name;

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

	public RectangleModel() {
		this.setColor(defaultColor());
		this.setLineColor(defaultLineColor());
		this.setOpacity(defaultOpacity());
		this.setFontStyle(defaultFontStyle());
		this.setFontSize(defaultFontSize());
		this.setFontColor(defaultFontColor());
		this.setLabelColor(defaultLabelColor());
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public void setName(final String name) {
		final String oldName = this.name;
		this.name = name;
		getListeners().firePropertyChange(PROPERTY_RENAME, oldName, this.name);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setEtage(final int etage) {
		this.etage = etage;
	}

	@Override
	public int getEtage() {
		return this.etage;
	}

	@Override
	public Rectangle getR() {
		return r;
	}

	@Override
	public void setR(final Rectangle r) {
		this.r = r;
	}

	@Override
	public RGB getColor() {
		return color;
	}

	@Override
	public void setColor(final RGB color) {
		final RGB oldColor = this.color;
		this.color = color;
		getListeners().firePropertyChange(PROPERTY_COLOR, oldColor, this.color);
	}

	@Override
	public RGB getLineColor() {
		return lineColor;
	}

	@Override
	public void setLineColor(final RGB lineColor) {
		this.lineColor = lineColor;
	}

	@Override
	public int getOpacity() {
		return opacity;
	}

	@Override
	public void setOpacity(final int opacity) {
		final int oldOpacity = this.opacity;
		this.opacity = opacity;
		getListeners().firePropertyChange(PROPERTY_OPACITY, oldOpacity, this.opacity);
	}

	@Override
	public int getFontStyle() {
		return fontStyle;
	}

	@Override
	public void setFontStyle(final int fontStyle) {
		final int oldFontStyle = this.fontStyle;
		this.fontStyle = fontStyle;
		getListeners().firePropertyChange(PROPERTY_FONT_STYLE, oldFontStyle, this.fontStyle);
	}

	@Override
	public int getFontSize() {
		return fontSize;
	}

	@Override
	public void setFontSize(final int fontSize) {
		final int oldFontSize = this.fontSize;
		this.fontSize = fontSize;
		getListeners().firePropertyChange(PROPERTY_FONT_SIZE, oldFontSize, this.fontSize);
	}

	@Override
	public RGB getFontColor() {
		return fontColor;
	}

	@Override
	public void setFontColor(final RGB fontColor) {
		final RGB oldFontColor = this.fontColor;
		this.fontColor = fontColor;
		getListeners().firePropertyChange(PROPERTY_FONT_COLOR, oldFontColor, this.fontColor);
	}

	@Override
	public RGB getLabelColor() {
		return labelColor;
	}

	@Override
	public void setLabelColor(final RGB labelColor) {
		final RGB oldLabelColor = this.labelColor;
		this.labelColor = labelColor;
		getListeners().firePropertyChange(PROPERTY_LABEL_COLOR, oldLabelColor, this.labelColor);
	}

}

