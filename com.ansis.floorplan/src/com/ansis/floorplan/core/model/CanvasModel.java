package com.ansis.floorplan.core.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;


public class CanvasModel {

	// ==================== 1. Static Fields ========================

	public static final String PROPERTY_LAYOUT = "CanvasModel"; //$NON-NLS-1$

	public static final String PROPERTY_ADD = "CanvasModelAddChild"; //$NON-NLS-1$

	public static final String PROPERTY_REMOVE = "CanvasModelRemoveChild"; //$NON-NLS-1$

	public static final String PROPERTY_BACKGROUND = "ModelBkgImage"; //$NON-NLS-1$

	private static List<ChildModel> children = new ArrayList<>();


	// ====================== 2. Instance Fields =============================

	private InputStream image;

	private Rectangle layout;

	private PropertyChangeSupport listeners;


	// ==================== 3. Static Methods ====================

	public static List<ChildModel> getChildren() {
		return children;
	}

	// ==================== 4. Constructors ====================

	public CanvasModel() {
		this.listeners = new PropertyChangeSupport(this);
	}


	// ==================== 7. Getters & Setters ====================

	public void setLayout(final Rectangle newLayout) { 
		final Rectangle oldLayout = this.layout;

		this.layout = newLayout;

		getListeners().firePropertyChange(PROPERTY_LAYOUT, oldLayout, newLayout);
	}

	public Rectangle getLayout() {
		return this.layout;
	}


	// ==================== 6. Action Methods ====================

	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}

	public PropertyChangeSupport getListeners() { 
		return listeners; 
	}

	public void removePropertyChangeListener(final PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

	public boolean addChild(final ChildModel figure) {
		final boolean b = CanvasModel.getChildren().add(figure);

		if (b) {
			figure.setParent(this);
			getListeners().firePropertyChange(PROPERTY_ADD, null, figure);
		} else {
			figure.setParent(this);
			children.add(figure);
			getListeners().firePropertyChange(new PropertyChangeEvent(this, "", null, null)); //$NON-NLS-1$
		}

		return b;
	}

	public boolean removeChild(final CanvasModel child) {
		final boolean b = CanvasModel.getChildren().remove(child);

		if (b)
			getListeners().firePropertyChange(PROPERTY_REMOVE, child, null);
		return b;
	}
	
	// ==================== 7. Getters & Setters ====================

	public InputStream getImage() {
		return image;
	}

	public void setImage(final InputStream image) {
		this.image = image;
		getListeners().firePropertyChange(PROPERTY_BACKGROUND, null, image);
	}

}