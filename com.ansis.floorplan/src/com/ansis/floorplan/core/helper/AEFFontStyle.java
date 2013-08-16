package com.ansis.floorplan.core.helper;

/**
 * 
 * @author ggrec
 *
 */
public enum AEFFontStyle
{
	NORMAL      ("Normal",      AEFConstPresentation.SWT_NORMAL),
	BOLD        ("Bold",        AEFConstPresentation.SWT_BOLD),
	ITALIC      ("Italic",      AEFConstPresentation.SWT_ITALIC),
	BOLD_ITALIC ("Bold Italic", AEFConstPresentation.SWT_BOLD_ITALIC);

	private final String name;
	private final int style;

	private AEFFontStyle(final String name, final int style) 
	{
		this.name = name;
		this.style = style;
	}

	public static AEFFontStyle getFontForStyle(final int style)
	{
		switch (style) 
		{
		case AEFConstPresentation.SWT_NORMAL:
			return NORMAL;

		case AEFConstPresentation.SWT_BOLD:
			return BOLD;

		case AEFConstPresentation.SWT_ITALIC:
			return ITALIC;

		case AEFConstPresentation.SWT_BOLD_ITALIC:
			return BOLD_ITALIC;

		default:
			return null;
		}
	}

	public String getName() 
	{
		return name;
	}

	public int getStyle()
	{
		return style;
	}
}
