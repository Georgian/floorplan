package com.ansis.floorplan.session;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * 
 * @author ggrec
 *
 */
public class ImageCache 
{

	// ====================== 2. Instance Fields =============================

	/**
	 * We use a String as key, because ImageDescriptor checks availability on path
	 * each time it is constructed (slow).
	 */
	private final Map<String, Image> imageMap = new HashMap<String, Image>();

	private String pluginID;


	// ==================== 4. Constructors ====================

	/**
	 * Plugin specific image cache
	 */
	public ImageCache(final String pluginID) 
	{
		this.pluginID = pluginID;
	}


	// ==================== 5. Creators ====================

	/**
	 * Overridden to dispose all images
	 */
	public void dispose() 
	{
		final Iterator<Image> iter = imageMap.values().iterator();
		while (iter.hasNext())
			iter.next().dispose();
		imageMap.clear();
	}


	// ==================== 8. Business Methods ====================

	/**
	 * Get or create an image from a class. Null-safe: returns a substitute imgDescr, if the image is not found.
	 * 
	 * @param pluginID The id of the plugin to get the image from
	 * @param path - the path to the existing image
	 * @return The image
	 */
	public Image getImage(final String path)
	{
		Image image = imageMap.get(path);
		if (image == null) 
		{
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
	public Image getCachedImage(final String key) 
	{
		return imageMap.get(key);
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public ImageDescriptor getImageDescriptor(final String path) 
	{
		return AbstractUIPlugin.imageDescriptorFromPlugin(pluginID, path);
	}

	/**
	 * Get an image by its descriptor. Caches the image. 
	 * @param imageDescriptor
	 * @return the image
	 */
	public Image getImage(final ImageDescriptor imageDescriptor) 
	{
		if (imageDescriptor == null)
			return null;

		final String key = "IMG_DESC-" + imageDescriptor.hashCode(); //$NON-NLS-1$
		Image image = imageMap.get(key);
		if (image == null) 
		{
			image = imageDescriptor.createImage();
			cacheImage(key, image);
		}

		return image;
	}

	/**
	 * 
	 * @param key
	 * @param image
	 */
	public void cacheImage(final String key, final Image image) 
	{
		imageMap.put(key, image);
	}
}