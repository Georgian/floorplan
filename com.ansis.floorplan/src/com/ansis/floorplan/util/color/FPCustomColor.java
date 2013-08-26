package com.ansis.floorplan.util.color;

import static com.ansis.floorplan.util.FPConstPresentation.SPACE;

import java.io.Serializable;

/**
 * Stores a custom color.
 * 
 * @author ggrec
 *
 */
public class FPCustomColor implements FPColor, Serializable 
{

	// ==================== 1. Static Fields ========================

	private static final long serialVersionUID = 1L;


	// ====================== 2. Instance Fields =============================

	private int red;
	private int green;
	private int blue;


	// ==================== 4. Constructors ====================

	public FPCustomColor(final int[] rgb) 
	{
		super();
		this.red = rgb[0];
		this.green = rgb[1];
		this.blue = rgb[2];
	}

	public FPCustomColor(final int red, final int green, final int blue) 
	{
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public FPCustomColor(final FPColor original) 
	{
		super();

		this.red = original.red();
		this.green = original.green();
		this.blue = original.blue();
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public int red() 
	{
		return red;
	}

	@Override
	public int green() 
	{
		return green;
	}

	@Override
	public int blue() 
	{
		return blue;
	}

	public void setRed(final int red) 
	{
		this.red = truncateTo256(red);
	}

	public void setGreen(final int green)
	{
		this.green = truncateTo256(green);
	}

	public void setBlue(final int blue)
	{
		this.blue = truncateTo256(blue);
	}


	// ==================== 8. Business Methods ====================

	private static int truncateTo256(final int colorPart) 
	{
		return colorPart % 256;
	}


	// ==================== 12. Presentation ====================

	@Override
	public String toString() 
	{
		return red + SPACE + green + SPACE + blue;
	}


	// ==================== 13. Utility Methods ====================

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + blue;
		result = prime * result + green;
		result = prime * result + red;
		return result;
	}

	@Override
	public boolean equals(final Object obj) 
	{
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		final FPCustomColor other = (FPCustomColor) obj;

		if (blue != other.blue)
			return false;

		if (green != other.green)
			return false;

		if (red != other.red)
			return false;

		return true;
	}
}
