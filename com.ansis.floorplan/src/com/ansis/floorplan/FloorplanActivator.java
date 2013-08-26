package com.ansis.floorplan;

import java.io.InputStream;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.ansis.floorplan.session.ColorCache;
import com.ansis.floorplan.session.FontCache;
import com.ansis.floorplan.session.ImageCache;
import com.ansis.floorplan.util.color.FPColor;
import com.ansis.floorplan.util.font.FPFont;


/**
 * The activator class controls the plug-in life cycle
 */
public class FloorplanActivator extends AbstractUIPlugin 
{

	// ==================== 1. Static Fields ========================

	// The plug-in ID
	public static final String PLUGIN_ID = "com.ansis.floorplan"; //$NON-NLS-1$

	// The shared instance
	private static FloorplanActivator plugin;


	// ====================== 2. Instance Fields =============================

	private ColorCache colorCache;

	private ImageCache imageCache;

	private FontCache fontCache;


	// ==================== 3. Static Methods ====================

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static FloorplanActivator getDefault()
	{
		return plugin;
	}


	// ==================== 4. Constructors ====================

	/**
	 * The constructor
	 */
	public FloorplanActivator() {

	}


	// ==================== 6. Action Methods ====================

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(final BundleContext context) throws Exception 
	{
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(final BundleContext context) throws Exception 
	{
		plugin = null;

		if (colorCache != null)
			colorCache.dispose();

		if (imageCache != null)
			imageCache.dispose();

		if (fontCache != null)
			fontCache.dispose();

		super.stop(context);
	}

	public String getPluginID() 
	{
		return PLUGIN_ID;
	}


	// ==================== 7. Getters & Setters ====================

	private ColorCache getColorCache() 
	{
		if (colorCache == null)
			colorCache = new ColorCache();
		return colorCache;
	}

	public ImageCache getImageCache() 
	{
		if (imageCache == null)
			imageCache = new ImageCache(getPluginID());
		return imageCache;
	}

	private FontCache getFontCache() 
	{
		if (fontCache == null)
			fontCache = new FontCache();
		return fontCache;
	}


	// ==================== 13. Utility Methods ====================

	// ==================== Image Cache ========================

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public ImageDescriptor getImageDescriptor(final String path) 
	{
		return getImageCache().getImageDescriptor(path);
	}

	/**
	 * Returns an image from a path, which should be stored in a 
	 * constant in <class>IImageKeys</class>, so that path changes
	 * can be implemented centrally without breaking any code.
	 * <b>Important:</b> The image does NOT need to be disposed in 
	 * your local code. Since the image is cached centrally, it will be 
	 * disposed at shutdown. 
	 * 
	 * @param path
	 * @return Image
	 */
	public Image getImage(final String path) 
	{
		// Wrapper method for hiding delegate ImageCache
		return getImageCache().getImage(path);
	}

	/**
	 * Return an image from an image data which should be stored in a
	 * constant in <class>IImageKeys</class> so that the path changes
	 * can be implemented centrally without breaking any code.
	 * <b>Important:</b> The image does NOT need to be disposed in 
	 * your local code. Since the image is cached centrally, it will be 
	 * disposed at shutdown. 
	 * 
	 * @param imageData
	 * @return Image
	 */
	public Image getImage(final ImageData imageData) 
	{
		return getImage(new ImageDescriptor() 
		{
			@Override public ImageData getImageData() 
			{
				return imageData;
			}
		});
	}

	/**
	 * Returns an image from an ImageDescriptor.
	 * <b>Important:</b> The image does NOT need to be disposed in 
	 * your local code. Since the image is cached centrally, it will be 
	 * disposed at shutdown. 
	 * 
	 * @param path
	 * @return Image
	 */
	public Image getImage(final ImageDescriptor imageDescriptor)
	{
		// Wrapper method for hiding delegate ImageCache
		return getImageCache().getImage(imageDescriptor);
	}


	// ==================== Color Cache ========================

	public Color getColor(final FPColor color) 
	{
		if (color == null)
			return null;
		return getColorCache().getColor(color);
	}

	public Color getColor(final RGB rgb) 
	{
		return getColorCache().getColor(rgb);
	}


	// ==================== Font Cache ============================

	public Font getFont(final FPFont style) 
	{
		return getFontCache().getFont(style);
	}

	public Font getCustomizedFont(final Font referenceFont, final int sizePercent, final int style) 
	{
		return getFontCache().getCustomisedFont(referenceFont, sizePercent, style);
	}

	public Font getMonospaceFont() 
	{
		return getFontCache().getMonospaceFont();
	}


	// ==================== IO ====================

	/**
	 * Reads the contents of the file located within the plugin that is also hosting 
	 * object. Plugin can be a JAR file or a directory.
	 * 
	 * @param fileNameWithinPlugin IMPORTANT: needs to start with a /
	 * @return
	 */
	public InputStream getInputStreamForFile(final String fileName) 
	{
		// This method can also read from a JAR, not only from file system:
		return this.getClass().getResourceAsStream(fileName);
	}
}