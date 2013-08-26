package com.ansis.floorplan.util.font;

/**
 * 
 * @author ggrec
 *
 */
public enum FPFontSize 
{
	XS     (70, 7),
	S      (90, 7),
	M      (100, 8),
	NORMAL (100, 8),
	L      (130, 10),
	XL     (165, 14),
	XXL    (200, 16),
	XXXL   (250, 18);

	final public int percent;
	final public int points;

	private FPFontSize(final int sizePercent, final int sizePoints)
	{
		percent = sizePercent;
		points = sizePoints;
	}

	public String getName() 
	{
		return toString();
	}
}
