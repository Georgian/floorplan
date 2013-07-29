package com.ansis.floorplan.command;

import org.eclipse.gef.commands.Command;

import com.ansis.floorplan.model.ModelTest;
import com.ansis.floorplan.model.Polly;

public class DeleteCommand extends Command{
	private ModelTest model;
	private ModelTest parentModel;
	@Override
	public void execute() {
		this.parentModel.removeChild(model);
	}
	public void setModel(final Object model) {
		this.model = (ModelTest)model;
	}
	public void setParentModel(final Object model) {
		parentModel = (ModelTest)model;
	}
	@Override
	public void undo() {
		this.parentModel.addChild((Polly) model);
	}
}