package com.ansis.floorplan.model;

import java.io.InputStream;


public class Canvas extends ModelTest {

	// ====================== 2. Instance Fields =============================

	private InputStream image;


	// ==================== 7. Getters & Setters ====================

	public InputStream getImage() {
		return image;
	}

	public void setImage(final InputStream image) {
		this.image = image;
		getListeners().firePropertyChange(PROPERTY_BACKGROUND, null, image);
	}

}