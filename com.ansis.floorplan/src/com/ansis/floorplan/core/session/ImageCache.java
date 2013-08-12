package com.ansis.floorplan.core.session;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.ansis.floorplan.app.FloorPlanActivator;

/**
 * Caches all images when they are loaded for the first time. This class is called 
 * from Application.java at startup and is disposed at shutdown.
 * 
 * @author akozma
 *
 */
public class ImageCache {
	
	// ==================== 1. Static Fields ========================

//	private static final AEFLog LOG = AEFLogger.getLogger(ImageCache.class);

	// ====================== 2. Instance Fields =============================

	/**
	 *  We use a String as key, because ImageDescriptor checks availability on path
	 *  each time it is constructed (slow).
	 */
	private final Map<String, Image> imageMap = new HashMap<String, Image>();
	
	// the id for the plugin
	private String plugin_id;

	
	// ==================== 4. Constructors ====================

	/**
	 * plugin specific image cache
	 */
	public ImageCache(final String plugin_id) {
		this.plugin_id = plugin_id;
	}

	
	// ==================== 5. Creators ====================

	/**
	 * Overridden to dispose all images
	 *
	 */
	public void dispose() {
		final Iterator<Image> iter = imageMap.values().iterator();
		while (iter.hasNext())
			((Image) iter.next()).dispose();
		imageMap.clear();
	}
	
	// ==================== 8. Business Methods ====================

	/**
	 * Get or create an image from a class. Null-safe: returns a substitute imgDescr, if the image is not found.
	 * 
	 * @param plugin_id The id of the plugin to get the image from
	 * @param path - the path to the existing image
	 * @return The image
	 */
	public Image getImage(final String path) {
		
		Image image = imageMap.get(path);
		if (image == null) {
			
			image = getImageDescriptor(path).createImage();
			imageMap.put(path, image);
		}
		
		return image;
	}

	/**
	 * Returns the image for the corresponding key or null if there's no image for this key
	 * This is used when you need to find out if there's an image for this key. 
	 * getImage() is not very useful for this.
	 * 
	 * @param key
	 * @return
	 */
	public Image getCachedImage(String key) {
		return imageMap.get(key);
	}
	
	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public ImageDescriptor getImageDescriptor(final String path) {
		ImageDescriptor imgD = AbstractUIPlugin.imageDescriptorFromPlugin(plugin_id, path);
		if (imgD == null) {
			// Get a substitue image:
			// imgD = Activator.showMissingIcon(plugin_id, path);
		}
		
		return imgD;
	}

	/**
	 * Get an image by its descriptor. Caches the image. 
	 * @param imageDescriptor
	 * @return the image
	 */
	public Image getImage(final ImageDescriptor imageDescriptor) {
		if (imageDescriptor == null)
			return null;
		
		final String key = "IMG_DESC-" + imageDescriptor.hashCode();
		Image image = imageMap.get(key);
		if (image == null) {
			image = imageDescriptor.createImage();
			cacheImage(key, image);
		}
		
		return image;
	}
	
	/**
	 * 
	 * 
	 * @param key
	 * @param image
	 */
	public void cacheImage(final String key, final Image image) {
		imageMap.put(key, image);
	}

}
