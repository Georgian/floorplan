package com.ansis.floorplan.util.color;


/**
 * Any color definition must implement this interface. Typically it will be AEFStandardColor
 * but sometimes we need a custom color as well.
 * 
 * @author akozma
 *
 */
public interface FPColor {

	// ==================== 7. Getters & Setters ====================

	public int red();

	public int green();

	public int blue();


	// ==================== 13. Utility Methods ====================

	@Override
	public boolean equals(Object obj);

	@Override
	public int hashCode();

}