package com.ansis.floorplan.core.command;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.ansis.floorplan.core.model.Canvas;
import com.ansis.floorplan.core.model.PollyLine;


public class PollyLineCreateCommand extends Command {


	// ====================== 2. Instance Fields =============================

	private Canvas canvas;

	private PollyLine pollyLine;

	private Point location;

	private Dimension size;


	// ==================== 6. Action Methods ====================


	@Override
	public boolean canExecute() {
		return true;
	}

	@Override
	public void execute() {

		if (size == null)
		{
			if (canvas.getChildren().size() > 0)
			{
				final PollyLine newPolly = (PollyLine) canvas.getChildren().get(canvas.getChildren().size()-1);

				PointList builderList = newPolly.getList();
				builderList.addPoint(location.x, location.y);
				newPolly.setList(builderList);

//				final Rectangle boundsHelper = new Rectangle();
//
//				boundsHelper.x = 
//				boundsHelper.y = 
//				boundsHelper.height =
//				boundsHelper.width = 
				newPolly.setBounds(new Rectangle(0,0, 1000,1000));
				newPolly.setLayout(new Rectangle(0,0, 1000,1000));


				if ( canvas.getChildren().size() >= 0)
					canvas.removeChild(canvas.getChildren().get(canvas.getChildren().size() - 1));
				canvas.addChild(newPolly);



				builderList = new PointList();


			}
		}
		else {
			System.out.println(location);
			System.out.println(size);
			pollyLine.setBounds(new Rectangle(location, size));
			pollyLine.setLayout(new Rectangle(location, size));


			final PointList pointList = new PointList(new int[] {0, 0, size.width, size.height});

			pollyLine.setList(pointList);

			canvas.addChild(pollyLine);
		}
	}



	// ==================== 7. Getters & Setters ====================

	public void setPollyLine(final PollyLine pollyLine) {
		this.pollyLine = pollyLine;
	}

	public void setCanvas(final Canvas canvas) {
		this.canvas = canvas;
	}

	public void setLocation(final Point location) {
		this.location = location;
	}

	public void setSize(final Dimension size) {
		this.size = size;
	}

}