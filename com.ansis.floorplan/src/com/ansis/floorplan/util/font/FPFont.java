package com.ansis.floorplan.util.font;

import com.ansis.floorplan.util.FPConstPresentation;


/**
 * 
 * @author ggrec
 *
 */
public enum FPFont {

	SMALL_FILTER (FPFontSize.S,      FPFontStyle.NORMAL),
	SMALL_BOLD   (FPFontSize.S,      FPFontStyle.BOLD),
	SMALL_ITALIC (FPFontSize.XS,     FPFontStyle.ITALIC),
	NORMAL       (FPFontSize.NORMAL, FPFontStyle.NORMAL),
	BOLD         (FPFontSize.NORMAL, FPFontStyle.BOLD),
	BOLD_ITALIC  (FPFontSize.NORMAL, FPFontStyle.BOLD_ITALIC),
	ITALIC       (FPFontSize.NORMAL, FPFontStyle.ITALIC),
	LARGE_BOLD   (FPFontSize.XXL,    FPFontStyle.BOLD);


	// ====================== 2. Instance Fields =============================

	private final FPFontSize size;

	private final FPFontStyle style;


	// ==================== 4. Constructors ====================

	private FPFont(final FPFontSize size, final FPFontStyle style) {
		this.size = size;
		this.style = style;
	}


	// ==================== 7. Getters & Setters ====================

	public int getSizePercent() {
		return size.percent;
	}

	public int getSizePoints() {	
		return size.points;
	}

	public FPFontSize getSize() {
		return size;
	}

	public FPFontStyle getStyle() {
		return style;
	}

	public int getSwtStyle() {
		return style.getStyle();
	}

	public static FPFont fontForStyle(final int style) {
		switch (style) {
		case FPConstPresentation.SWT_BOLD:
			return BOLD;

		case FPConstPresentation.SWT_BOLD_ITALIC:
			return BOLD_ITALIC;

		case FPConstPresentation.SWT_ITALIC:
			return ITALIC;

		case FPConstPresentation.SWT_NORMAL:
			return NORMAL;

		default:
			return null;
		}
	}

}