package com.ansis.floorplan.util.font;

import com.ansis.floorplan.util.FPConstPresentation;


/**
 * 
 * @author ggrec
 *
 */
public enum FPFontStyle {

	NORMAL      ("Normal",      FPConstPresentation.SWT_NORMAL), //$NON-NLS-1$
	BOLD        ("Bold",        FPConstPresentation.SWT_BOLD), //$NON-NLS-1$
	ITALIC      ("Italic",      FPConstPresentation.SWT_ITALIC), //$NON-NLS-1$
	BOLD_ITALIC ("Bold Italic", FPConstPresentation.SWT_BOLD_ITALIC); //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private final String name;

	private final int style;


	// ==================== 4. Constructors ====================

	private FPFontStyle(final String name, final int style) {
		this.name = name;
		this.style = style;
	}


	// ==================== 7. Getters & Setters ====================

	public static FPFontStyle getFontForStyle(final int style) {
		switch (style) {
		case FPConstPresentation.SWT_NORMAL:
			return NORMAL;

		case FPConstPresentation.SWT_BOLD:
			return BOLD;

		case FPConstPresentation.SWT_ITALIC:
			return ITALIC;

		case FPConstPresentation.SWT_BOLD_ITALIC:
			return BOLD_ITALIC;

		default:
			return null;
		}
	}

	public String getName() {
		return name;
	}

	public int getStyle() {
		return style;
	}

}