package com.ansis.floorplan.model;

import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;


public class Polly extends ModelTest{

	// ====================== 2. Instance Fields =============================

	private PointList list;

	private ModelTest parent;

	private Rectangle bounds;

	private int etage;

	private String name;

	private Rectangle r;

	private Rectangle g;


	// ==================== 7. Getters & Setters ====================

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setEtage(final int etage) {
		this.etage = etage;
	}

	public int getEtage() {
		return this.etage;
	}

	public PointList getList() {
		return list;
	}

	public void setList(final PointList list) {
		this.list = list;
	}

	public void setParent(final ModelTest parent) {
		this.parent = parent;
	}

	public ModelTest getParent() {
		return parent;
	}

	public void setBounds(final Rectangle bounds) {
		this.bounds = bounds;
	}

	public Rectangle getBounds(){
		return bounds;

	}

	public Rectangle getR() {
		return r;
	}

	public void setR(final Rectangle r){
		this.r = r;
	}

	public Rectangle getG() {
		return g;
	}

	public void setG(final Rectangle g) {
		this.g = g;
	}

}