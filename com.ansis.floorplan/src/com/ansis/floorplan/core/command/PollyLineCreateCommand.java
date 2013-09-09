package com.ansis.floorplan.core.command;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.ansis.floorplan.core.model.Canvas;
import com.ansis.floorplan.core.model.Polly;
import com.ansis.floorplan.core.model.PollyLine;


public class PollyLineCreateCommand extends Command {

	// ====================== 2. Instance Fields =============================

	public static boolean isShiftPressed = false;

	private Canvas canvas;

	private PollyLine pollyLine;

	private Point location, helper = null, lastPoint = null;

	private Dimension size;

	private Rectangle bounds, tempBounds;

	private Point minPoint = null, maxPoint = null, point;

	private int i, hasLine = 0, lineLocation = 0;


	// ==================== 6. Action Methods ====================

	@Override
	public boolean canExecute() {
		return true;
	}

	@SuppressWarnings("static-access")
	@Override
	public void execute() {

		// Check for open Polygons
		for(i = 0; i < canvas.getChildren().size(); i++)
		{
			if ( canvas.getChildren().get(i) instanceof PollyLine ) 
			{	
				hasLine = 1;
				// Set list location of the open Polygon
				lineLocation = i;
				break;
			}
		}

		// Check if Drawing was started or not
		if ( (canvas.getChildren().size() == 0 && size == null) || (hasLine == 0 && size == null) )
		{	
			// If not, start a new Polyline
			final PollyLine newPolly = new PollyLine();
			final PointList builderList = new PointList();

			// First point must be in the origin of the bounds
			builderList.addPoint(0, 0);
			// Add an aditional point for visual fixes
			builderList.addPoint(3, 3);

			// Create temporary bounds for the single point in the list
			bounds = new Rectangle(location.x-1,location.y-1,3,3);

			// Create the point on the canvas
			newPolly.setBounds(bounds);
			newPolly.setLayout(bounds);
			newPolly.setDrawingDenied(false);
			newPolly.setList(builderList);

			canvas.addChild(newPolly);
		}
		// If yes, get the unfinished Polyline and continue building it (also force recheck for open Polygons)
		else if (size == null && canvas.getChildren().size() > 0 && hasLine == 1)
		{ 	
			// Test shift presses. WIP
			System.out.println(isShiftPressed);

			// Get the unfinished Polyline
			final PollyLine newPolly = (PollyLine) canvas.getChildren().get(lineLocation);

			// Get the old pointlist
			PointList builderList = newPolly.getList();

			// Create temporary bounds to update new bounds
			tempBounds = newPolly.getBounds();

			// Remove the first two points which were used for visual representation and replace them with the original mouse location
			if( builderList.getPoint(1).x == 3 && builderList.getPoint(1).y == 3 )
			{
				builderList.removeAllPoints();
				builderList.addPoint(new Point(tempBounds.x, tempBounds.y));
			}

			// Create a helper point
			helper = new Point();
			helper.x = 0;
			helper.y = 0;

			// Add current mouse location to the pointlist
			builderList.addPoint(location.x, location.y);


			// Check if points in the pointlist had been fixed or not
			if(newPolly.isDrawingDenied() /*rename this*/ == true)
			{
				// Helper becomes the (x,y) of the old bounds (aka old minPoint)
				helper.x = tempBounds.x;
				helper.y = tempBounds.y;

				// Parse list and fix point locations
				for (int i = 0; i < builderList.size()-1; i++) {
					point = builderList.getPoint(i);
					point.x = point.x + helper.x;
					builderList.setPoint(point, i);
				}
				for (int i = 0; i < builderList.size()-1; i++) {
					point = builderList.getPoint(i);
					point.y = point.y + helper.y;
					builderList.setPoint(point, i);
				}

				minPoint = new Point();

				// This is needed. NPE if it is removed
				minPoint.x = helper.x;
				minPoint.y = helper.y;
			}

			// Schedule pointlist fix on next addpoint
			newPolly.setDrawingDenied(true);


			// Compute new minPoint and maxPoint
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

			// If minPoint differs from the origins of the canvas, fix points in the pointlist
			// This is needed so points won't float inside the bounds rectangle
			if(minPoint.x != 0)
				for (int i = 0; i < builderList.size(); i++) {
					point = builderList.getPoint(i);
					point.x = point.x - minPoint.x;
					builderList.setPoint(point, i);
				}
			if(minPoint.y != 0)
				for (int i = 0; i < builderList.size(); i++) {
					point = builderList.getPoint(i);
					point.y = point.y - minPoint.y;
					builderList.setPoint(point, i);
				}

			// Create bounds after the points were computed
			bounds = new Rectangle(minPoint,maxPoint);

			// Get lastpoint and firstpoint of the list
			lastPoint = new Point();
			lastPoint = builderList.getPoint(builderList.size()-1);
			final Point firstPoint = builderList.getPoint(0);

			// Check if user wants to close the Polygon
			if ( (lastPoint.x + 10 >= firstPoint.x && lastPoint.x - 10 <= firstPoint.x) && (lastPoint.y + 10 >= firstPoint.y && lastPoint.y - 10 <= firstPoint.y) && builderList.size() > 3) 
			{
				// If yes, create a new Polygon
				final Polly polly = new Polly();

				// Make the lastpoint become the firstpoint in order to close the Polygon
				builderList.removePoint(builderList.size()-1);
				builderList.addPoint(firstPoint);

				// Set the properties of the Polygon
				polly.setList(builderList);
				polly.setBounds(bounds);
				polly.setLayout(bounds);
				polly.setLabelPosition(new Rectangle(0,0,10,10));

				// Find the location of the Polyline (in the childlist), delete it and add the newly finished Polygon
				for(i = 0; i < canvas.getChildren().size(); i++)
				{
					if ( canvas.getChildren().get(i) instanceof PollyLine ) 
					{
						canvas.removeChild(canvas.getChildren().get(i));
						canvas.addChild(polly);
						break;
					}
				}
			}
			// If not, create a new Polyline
			else {
				// Set the properties of the Polyline
				newPolly.setList(builderList);
				newPolly.setBounds(bounds);
				newPolly.setLayout(bounds);
				newPolly.setLabelPosition(new Rectangle(0,0,10,10));

				// Find the location of the Polyline (in the childlist), delete it and add the updated Polyline
				for(i = 0; i < canvas.getChildren().size(); i++)
				{
					if ( canvas.getChildren().get(i) instanceof PollyLine ) 
					{
						canvas.removeChild(canvas.getChildren().get(i));
						canvas.addChild(newPolly);
						break;
					}
				}
			}

			// Reset the pointlist
			builderList = new PointList();
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