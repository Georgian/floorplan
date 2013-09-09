package com.ansis.floorplan.core.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;

public class CanvasModel 
{

	// ==================== 1. Static Fields ========================

	public static final String PROPERTY_LAYOUT = "CanvasModel"; //$NON-NLS-1$
	public static final String PROPERTY_ADD = "CanvasModelAddChild"; //$NON-NLS-1$
	public static final String PROPERTY_REMOVE = "CanvasModelRemoveChild"; //$NON-NLS-1$
	public static final String PROPERTY_BACKGROUND = "ModelBkgImage"; //$NON-NLS-1$

	private List<ChildModel> children = new ArrayList<>();


	// ====================== 2. Instance Fields =============================

	private InputStream image;

	private Rectangle layout;

	private PropertyChangeSupport listeners;


	// ==================== 4. Constructors ====================

	public CanvasModel() 
	{
		this.listeners = new PropertyChangeSupport(this);
	}


	// ==================== 7. Getters & Setters ====================

	public void setLayout(final Rectangle layout) 
	{ 
		final Rectangle oldLayout = this.layout;
		this.layout = layout;
		getListeners().firePropertyChange(PROPERTY_LAYOUT, oldLayout, layout);
	}

	public Rectangle getLayout() 
	{
		return layout;
	}


	// ==================== 6. Action Methods ====================

	public void addPropertyChangeListener(final PropertyChangeListener listener) 
	{
		listeners.addPropertyChangeListener(listener);
	}

	public PropertyChangeSupport getListeners() 
	{ 
		return listeners; 
	}

	public void removePropertyChangeListener(final PropertyChangeListener listener) 
	{
		listeners.removePropertyChangeListener(listener);
	}

	public void addChild(final ChildModel child) 
	{
		children.add(child);
		child.setParent(this);
		getListeners().firePropertyChange(PROPERTY_ADD, null, child);
	}

	public void removeChild(final ChildModel child) 
	{
		children.remove(child);
		child.setParent(null);
		getListeners().firePropertyChange(PROPERTY_REMOVE, child, null);
	}


	// ==================== 7. Getters & Setters ====================

	public List<ChildModel> getChildren()
	{
		return children;
	}

	public InputStream getImage() 
	{
		return image;
	}

	public void setImage(final InputStream image) 
	{
		this.image = image;
		getListeners().firePropertyChange(PROPERTY_BACKGROUND, null, image);
	}

}