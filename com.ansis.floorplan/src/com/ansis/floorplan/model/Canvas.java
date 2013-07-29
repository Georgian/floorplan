package com.ansis.floorplan.model;


public class Canvas extends ModelTest {

	// ====================== 2. Instance Fields =============================

	private String address;

	private int capital;

	private String name;


	// ==================== 6. Action Methods ====================

	public void setAddress(final String address) {
		this.address = address;
	}

	public void setCapital(final int capital) {
		this.capital = capital;
	}

	public String getAddress() {
		return this.address;
	}

	public int getCapital() {
		return this.capital;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getName()	{
		return this.name;
	}

}