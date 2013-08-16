package com.ansis.floorplan.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.ansis.floorplan.model.Canvas;
import com.ansis.floorplan.model.PollyLine;


public class PollyLineCreateCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private Canvas en;

	private PollyLine srv;


	// ==================== 4. Constructors ====================

	public PollyLineCreateCommand() {
		super();
		en = null;
		srv = null;
	}


	// ==================== 6. Action Methods ====================

	@Override
	public boolean canExecute() {
		if (srv == null || en == null)
			return false;
		return true;
	}

	@Override
	public void execute() {
		en.addChild(srv);
		srv.setParent(en);
	}


	// ==================== 7. Getters & Setters ====================

	public void setPollyLine(final Object s) {
		if (s instanceof PollyLine)
			this.srv = (PollyLine)s;
	}

	public void setCanvas(final Object e) {
		if (e instanceof Canvas)
			this.en = (Canvas)e;
	}

	public void setLayout(final Rectangle r) {
		if (srv == null)
			return;
		srv.setLayout(r);
	}

}