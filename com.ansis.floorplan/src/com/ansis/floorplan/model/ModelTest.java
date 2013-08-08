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

	public static final String PROPERTY_ADD = "ModelTestAddChild"; //$NON-NLS-1$

	public static final String PROPERTY_REMOVE = "ModelTestRemoveChild"; //$NON-NLS-1$

	private static List<Polly> children = new ArrayList<>();
	
	private static List<PollyLine> pChildren = new ArrayList<>();
	


	// ====================== 2. Instance Fields =============================

	private Rectangle layout;

	private PropertyChangeSupport listeners;


	// ==================== 3. Static Methods ====================

	public static List<Polly> getChildren() {
		return children;
	}
	
	public static List<PollyLine> getPChildren() {
		return pChildren;
	}


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

	public boolean addChild(final Polly figure) {
		final boolean b = ModelTest.getChildren().add(figure);
		
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

	public boolean removeChild(final ModelTest child) {
		final boolean b = ModelTest.getChildren().remove(child);
		
		if (b)
			getListeners().firePropertyChange(PROPERTY_REMOVE, child, null);
		return b;
	}
	
	public boolean addPChild(final PollyLine figure) {
		final boolean b = ModelTest.getPChildren().add(figure);
		
		if (b) {
			figure.setParent(this);
			getListeners().firePropertyChange(PROPERTY_ADD, null, figure);
		} else {
			figure.setParent(this);
			pChildren.add(figure);
			getListeners().firePropertyChange(new PropertyChangeEvent(this, "", null, null)); //$NON-NLS-1$
		}
		
		return b;
	}

	public boolean removePChild(final ModelTest child) {
		final boolean b = ModelTest.getPChildren().remove(child);
		
		if (b)
			getListeners().firePropertyChange(PROPERTY_REMOVE, child, null);
		return b;
	}

}