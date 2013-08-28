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

	private Point location, helper = null, lastPoint = null;

	private Dimension size;

	private Rectangle bounds, tempBounds;

	private Point minPoint = null, maxPoint = null, point;

	// ==================== 6. Action Methods ====================

	@Override
	public boolean canExecute() {
		return true;
	}

	@SuppressWarnings("static-access")
	@Override
	public void execute() {
		if (canvas.getChildren().size() > 0)
		{	
			final PollyLine newPolly = (PollyLine) canvas.getChildren().get(canvas.getChildren().size()-1);

			if (size == null && newPolly.isDrawingDenied() == true)
			{ 	
				if (canvas.getChildren().size() > 0)
				{

					PointList builderList = newPolly.getList();

					builderList.addPoint(location.x, location.y);
					
					tempBounds = newPolly.getBounds();
					helper = new Point();
					helper.x = tempBounds.x;
					helper.y = tempBounds.y;

					lastPoint = new Point();
					lastPoint = builderList.getPoint(builderList.size()-1);
					lastPoint.x -= helper.x;
					lastPoint.y -= helper.y;
					
					builderList.removePoint(builderList.size()-1);
					builderList.addPoint(lastPoint);
					
					newPolly.setList(builderList);
					
					for (int i = 0; i < builderList.size(); i++) {
						if (minPoint == null)
							minPoint = builderList.getPoint(i);

						if (maxPoint == null)
							maxPoint = builderList.getPoint(i);

						point = builderList.getPoint(i);

						if(point.x < minPoint.x)
							minPoint.x = point.x;
						if(point.x > maxPoint.x)
							maxPoint.x = point.x;
						if(point.y < minPoint.y)
							minPoint.y = point.y;
						if(point.y > maxPoint.y)
							maxPoint.y = point.y;
					}

					//					if(minPoint.x != 0)
					//						for (int i = 0; i < builderList.size(); i++) {
					//							point = builderList.getPoint(i);
					//							point.x = point.x - minPoint.x;
					//							builderList.setPoint(point, i);
					//						}

					//					if(minPoint.y != 0)
					//						for (int i = 0; i < builderList.size(); i++) {
					//							point = builderList.getPoint(i);
					//							point.y = point.y - minPoint.y;
					//							builderList.setPoint(point, i);
					//						}

				
					minPoint.x += helper.x;
					minPoint.y += helper.y;
					maxPoint.x += helper.x;
					maxPoint.y += helper.y;

//					if(minPoint.x > lastPoint.x)
//						for (int i = 0; i < builderList.size(); i++) {
//							minPoint.x = lastPoint.x;
//						}
//
//					if(minPoint.y > lastPoint.y)
//						for (int i = 0; i < builderList.size(); i++) {
//							minPoint.y = lastPoint.y;
//						}

					
					
					
					bounds = new Rectangle(minPoint,maxPoint);

					newPolly.setBounds(bounds);
					newPolly.setLayout(bounds);
					
					if ( canvas.getChildren().size() >= 0)
						canvas.removeChild(canvas.getChildren().get(canvas.getChildren().size() - 1));
					canvas.addChild(newPolly);

					builderList = new PointList();
				}
			}
			else if (newPolly.isDrawingDenied() == false) {

				pollyLine.setBounds(new Rectangle(location, size));
				pollyLine.setLayout(new Rectangle(location, size));

				final PointList pointList = new PointList(new int[] {0, 0, size.width, size.height});

				pollyLine.setList(pointList);
				pollyLine.setDrawingDenied(true);

				canvas.addChild(pollyLine);
			}
		} 		
		else if (size != null) {

			pollyLine.setBounds(new Rectangle(location, size));
			pollyLine.setLayout(new Rectangle(location, size));

			final PointList pointList = new PointList(new int[] {0, 0, size.width, size.height});

			pollyLine.setList(pointList);
			pollyLine.setDrawingDenied(true);

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