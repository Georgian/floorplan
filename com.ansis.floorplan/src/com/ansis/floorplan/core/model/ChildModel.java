package com.ansis.floorplan.core.model;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.RGB;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.util.color.FPStandardColor;


public class ChildModel extends CanvasModel {

	// ==================== 1. Static Fields ========================

	public static final String PROPERTY_RENAME = "Rename"; //$NON-NLS-1$

	public static final String PROPERTY_COLOR = "Color"; //$NON-NLS-1$

	public static final String PROPERTY_OPACITY = "Opacity"; //$NON-NLS-1$

	public static final String PROPERTY_FONT_STYLE = "FontStyle"; //$NON-NLS-1$

	public static final String PROPERTY_FONT_SIZE = "FontSize"; //$NON-NLS-1$

	public static final String PROPERTY_FONT_COLOR = "FontColor"; //$NON-NLS-1$

	public static final String PROPERTY_LABEL_COLOR = "LabelColor"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private CanvasModel parent;

	private Rectangle bounds;

	private int integer;

	private String name;

	private RGB color;

	private RGB lineColor;

	private int opacity;

	private int fontStyle;

	private int fontSize;

	private RGB fontColor;

	private RGB labelColor;

	private Rectangle labelPosition;


	// ==================== 7. Getters & Setters ====================

	public void setParent(final CanvasModel parent) {
		this.parent = parent;
	}

	public CanvasModel getParent() {
		return parent;
	}

	public void setBounds(final Rectangle bounds) {
		this.bounds = bounds;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setName(final String name) {
		final String oldName = this.name;
		this.name = name;
		getListeners().firePropertyChange(PROPERTY_RENAME, oldName, this.name);
	}

	public String getName() {
		if (name == null)
			name = "New Figure\nInteger: " + getInteger(); //$NON-NLS-1$
		return name;
	}

	public void setInteger(final int integer) {
		this.integer = integer;
	}

	public int getInteger() {
		return integer;
	}

	public RGB getColor() {
		if (color == null)
			color = FloorplanActivator.getDefault().getColor(FPStandardColor.BLUE_NAVY).getRGB();
		return color;
	}

	public void setColor(final RGB color) {
		final RGB oldColor = this.color;
		this.color = color;
		getListeners().firePropertyChange(PROPERTY_COLOR, oldColor, this.color);
	}

	public RGB getLineColor() {
		if (lineColor == null)
			lineColor = FloorplanActivator.getDefault().getColor(FPStandardColor.BLUE).getRGB();
		return lineColor;
	}

	public void setLineColor(final RGB lineColor) {
		this.lineColor = lineColor;
	}

	public int getOpacity() {
		if (opacity == 0)
			opacity = 127;
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
		if (fontSize == 0)
			fontSize = 10;
		return fontSize;
	}

	public void setFontSize(final int fontSize) {
		final int oldFontSize = this.fontSize;
		this.fontSize = fontSize;
		getListeners().firePropertyChange(PROPERTY_FONT_SIZE, oldFontSize, this.fontSize);
	}

	public RGB getFontColor() {
		if (fontColor == null)
			fontColor = FloorplanActivator.getDefault().getColor(FPStandardColor.BLACK).getRGB();
		return fontColor;
	}

	public void setFontColor(final RGB fontColor) {
		final RGB oldFontColor = this.fontColor;
		this.fontColor = fontColor;
		getListeners().firePropertyChange(PROPERTY_FONT_COLOR, oldFontColor, this.fontColor);
	}

	public RGB getLabelColor() {
		if (labelColor == null)
			labelColor = FloorplanActivator.getDefault().getColor(FPStandardColor.WHITE).getRGB();
		return labelColor;
	}

	public void setLabelColor(final RGB labelColor) {
		final RGB oldLabelColor = this.labelColor;
		this.labelColor = labelColor;
		getListeners().firePropertyChange(PROPERTY_LABEL_COLOR, oldLabelColor, this.labelColor);
	}

	public Rectangle getLabelPosition() {
		return labelPosition;
	}

	public void setLabelPosition(final Rectangle labelPosition) {
		this.labelPosition = labelPosition;
	}

}