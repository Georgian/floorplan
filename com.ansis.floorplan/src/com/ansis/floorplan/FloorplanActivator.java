package com.ansis.floorplan.app;

import org.osgi.framework.BundleContext;

import com.ansis.floorplan.core.base.AEFBaseActivator;


/**
 * The activator class controls the plug-in life cycle
 */
public class FloorPlanActivator extends AEFBaseActivator {

	// ==================== 1. Static Fields ========================

	// The plug-in ID
	public static final String PLUGIN_ID = "com.ansis.floorplan"; //$NON-NLS-1$

	// The shared instance
	private static FloorPlanActivator plugin;


	// ==================== 3. Static Methods ====================

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static FloorPlanActivator getDefault() {
		return plugin;
	}


	// ==================== 4. Constructors ====================

	/**
	 * The constructor
	 */
	public FloorPlanActivator() {

	}


	// ==================== 6. Action Methods ====================

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	@Override
	public String getPluginID() {
		return PLUGIN_ID;
	}

}