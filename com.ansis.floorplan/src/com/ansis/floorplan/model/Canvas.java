package com.ansis.floorplan.model;

import java.io.InputStream;

/**
 * 
 * @author ggrec
 *
 */
public class Canvas extends ModelTest {

	// ====================== 2. Instance Fields =============================

	private InputStream image;


	// ==================== 7. Getters & Setters ====================

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
		getListeners().firePropertyChange(PROPERTY_BACKGROUND, null, image);
	}

}