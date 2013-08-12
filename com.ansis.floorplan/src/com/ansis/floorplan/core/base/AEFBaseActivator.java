package com.ansis.floorplan.core.base;

import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.ansis.floorplan.core.session.ImageCache;


public abstract class AEFBaseActivator extends AbstractUIPlugin {

//	private static final AEFLog LOG = AEFLogger.getLogger(AEFBaseActivator.class);
	
	// The shared instance
	//	private static AEFBaseActivator plugin;

	private ImageCache imageCache;


	// ==================== 3. Static Methods ====================

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	//	public static AEFBaseActivator getDefault() {
	//		return plugin;
	//	}

	// ==================== 5. Creators ====================

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		init();
	}

	int counter = 0;
	
	@Override
	public void stop(final BundleContext context) throws Exception {
		if (counter++ == 0) {
			joinJobs();
		}

		dispose();

		if (imageCache != null)
			imageCache.dispose();

		super.stop(context);
	}

	public void joinJobs() {
		Job[] saveJobs = Job.getJobManager().find(SaveJob.SAVE_JOB_FAMILY);

		System.out.println("Found " + saveJobs.length + " jobs which were joined.");
		
		for(final Job job : saveJobs) {
			job.addJobChangeListener(new IJobChangeListener() {
				
				@Override
				public void sleeping(final IJobChangeEvent event) {
					System.out.println("Job sleeping.");
				}
				
				@Override
				public void scheduled(final IJobChangeEvent event) {
					System.out.println("Job scheduled.");
				}
				
				@Override
				public void running(final IJobChangeEvent event) {
					System.out.println("Job running.");
				}
				
				@Override
				public void done(final IJobChangeEvent event) {
					System.out.println("Job done.");
				}
				
				@Override
				public void awake(final IJobChangeEvent event) {
					System.out.println("Job awake.");
				}
				
				@Override
				public void aboutToRun(final IJobChangeEvent event) {
					System.out.println("Job about to run.");
				}
			});
		}
		
////		Log.info("Found " + saveJobs.length + " jobs which were joined.");
//
//		if (saveJobs.length > 0) {
//
//			final Benchmarking benchmarking = Benchmarking.startNewTimer();
//			
//			try {
//				System.out.println("Ready to JOIN.");
//				Job.getJobManager().join(SaveJob.SAVE_JOB_FAMILY, null);
//				
//				saveJobs = Job.getJobManager().find(SaveJob.SAVE_JOB_FAMILY);
//				System.out.println("Called after JOIN: Found " + saveJobs.length + " jobs which were joined.");
//				
//			} catch (final InterruptedException e) {
//				System.out.println("======================= InterruptedException ================================");
//			} catch (final OperationCanceledException e) {
//				System.out.println("======================= OperationCanceledException ================================");
//			} catch (final Exception e) {
//				System.out.println("======================= Exception ================================");
//			}
//		
//			System.out.println("Time needed for saving job to terminate:" + benchmarking.getElapsedTimeAndStop());
////			Log.info("Time needed for saving job to terminate:" + benchmarking.getElapsedTimeAndStop());
//		}
//		
//		System.out.println("Found " + saveJobs.length + " jobs which were joined.");
////		Log.info("Found " + saveJobs.length + " jobs which were joined.");
	}

	// --------------------- <Callback Hooks> -----------------------

	/**
	 * Subclasses can override to perform any init activities.
	 */
	protected void init() {
	}

	/**
	 * Subclasses can override to perform any init activities.
	 */
	protected void dispose() {
	}

	// ==================== 7. Getters & Setters ====================

	public ImageCache getImageCache() {
		if (imageCache == null)
			imageCache = new ImageCache(getPluginID());
		return imageCache;
	}

	// ==================== 13. Utility Methods ====================

	/**
	 * Returns the plugin id of the main plugin of this application.
	 */
	public static String getProductPlugin() {
		return Platform.getProduct().getDefiningBundle().getSymbolicName();
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public ImageDescriptor getImageDescriptor(final String path) {

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
	public Image getImage(final String path) {
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
	public Image getImage(final ImageData imageData) {
		return getImage(new ImageDescriptor() {
			@Override
			public ImageData getImageData() {
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
	public Image getImage(final ImageDescriptor imageDescriptor) {
		// Wrapper method for hiding delegate ImageCache

		return getImageCache().getImage(imageDescriptor);
	}
	

	public ResourceBundle getResourceBundle(final Locale locale){
		return ResourceBundle.getBundle("/resources/Translation", locale, this.getClass().getClassLoader()); //$NON-NLS-1$

	}
	
	// ==================== 13. io ====================

	/**
	 * Reads the contents of the file located within the plugin that is also hosting 
	 * object. Plugin can be a JAR file or a directory.
	 * 
	 * @param fileNameWithinPlugin IMPORTANT: needs to start with a /
	 * @return
	 */
	public InputStream getInputStreamForFile(final String fileName) {
		// This method can also read from a JAR, not only from file system:
		return this.getClass().getResourceAsStream(fileName);
	}

	public abstract String getPluginID ();



}
