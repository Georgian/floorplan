package com.ansis.floorplan.core.base;

import org.eclipse.core.runtime.jobs.Job;

/**
 * 
 * @author dhosu
 *
 */
public abstract class SaveJob extends Job {

	// ==================== 1. Static Fields ========================
	
	public static final String SAVE_JOB_FAMILY = "SaveJobFamily";

	
	// ====================== 2. Instance Fields =============================
	
	/**
	 * used in save and reload method to reload or not after saving is done
	 * e.g.: if the save is called when workbench is closed we can set this to false to avoid reloading from server after save is done
	 */
	private boolean reloadAfterSave = true;
	
	// ==================== 4. Constructors ====================

	
	public SaveJob(String jobName) {
		super(jobName);
	}

	// ==================== 7. Getters & Setters ====================

	public boolean isReloadAfterSave() {
		return reloadAfterSave;
	}
	
	public void setReloadAfterSave(boolean reloadAfterSave) {
		this.reloadAfterSave = reloadAfterSave;
	}

	// ==================== 13. Utility Methods ====================

	@Override
	public boolean belongsTo(Object family) {
		return SAVE_JOB_FAMILY.equals(family);
	}
	
	@Override
	protected void canceling() {
		System.out.println("Job is cancelling.");
		super.canceling();
	}
	
	
}
