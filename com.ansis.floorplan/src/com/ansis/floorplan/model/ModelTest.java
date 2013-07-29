package com.ansis.floorplan.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;


public class ModelTest {

	// ==================== 1. Static Fields ========================

	public static final String PROPERTY_LAYOUT = "ModelTest"; //$NON-NLS-1$

	public static final String PROPERTY_MODEL_CHANGED = "ModelTest"; //$NON-NLS-1$

	private static List<Polly> children = new ArrayList<>();

	public static final String PROPERTY_ADD = "ModelTestAddChild";

	public static final String PROPERTY_REMOVE = "ModelTestRemoveChild";


	// ====================== 2. Instance Fields =============================

	private Rectangle layout;

	private PropertyChangeSupport listeners;


	// ==================== 4. Constructors ====================

	public ModelTest() {
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


	public boolean removeChild(final ModelTest child) {
		final boolean b = this.children.remove(child);
		if (b)
			getListeners().firePropertyChange(PROPERTY_REMOVE, child, null);
		return b;
	}



	public boolean addChild(final Polly figure) {
		final boolean b = this.children.add(figure);
		if (b) {
			figure.setParent(this);
			getListeners().firePropertyChange(PROPERTY_ADD, null, figure);
		}
		else{

			figure.setParent(this);
			children.add(figure);
			getListeners().firePropertyChange(new PropertyChangeEvent(this, "", null, null));
		}
		return b;

	}

	public static List<Polly> getChildren() {
		return children;
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

}