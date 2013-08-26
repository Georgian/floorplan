package com.ansis.floorplan.util.color;

/**
 * Any color definition must implement this interface. Typically it will be AEFStandardColor
 * but sometimes we need a custom color as well.
 * 
 * @author akozma
 *
 */
public interface FPColor {

	public int red();

	public int green();

	public int blue();

	@Override
	public boolean equals(Object obj);

	@Override
	public int hashCode();
}