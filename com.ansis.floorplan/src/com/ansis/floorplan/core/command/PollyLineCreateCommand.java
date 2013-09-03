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
		for(i = 0; i < canvas.getChildren().size(); i++)
		{
			if ( canvas.getChildren().get(i) instanceof PollyLine ) 
			{	
				hasLine = 1;
				lineLocation = i;
				break;
			}

		}
		if ( (canvas.getChildren().size() == 0 && size == null) || (hasLine == 0 && size == null) )
		{	
			final PollyLine newPolly = new PollyLine();
			final PointList builderList = new PointList();

			builderList.addPoint(location.x, location.y);
			point = new Point();
			point.x = location.x +3;
			point.y = location.y +3;
			bounds = new Rectangle(location, point);

			newPolly.setBounds(bounds);
			newPolly.setLayout(bounds);
			newPolly.setDrawingDenied(false);
			newPolly.setList(builderList);

			canvas.addChild(newPolly);

		}
		else if (size == null && canvas.getChildren().size() > 0 && hasLine == 1)
		{ 	
			final PollyLine newPolly = (PollyLine) canvas.getChildren().get(lineLocation);


			PointList builderList = newPolly.getList();

			builderList.addPoint(location.x, location.y);


			tempBounds = newPolly.getBounds();
			System.out.println(newPolly.getBounds());
			System.out.println("//////////");
			helper = new Point();

			helper.x = 0;
			helper.y = 0;

			if(newPolly.isDrawingDenied() == true)
			{
				helper.x = tempBounds.x;
				helper.y = tempBounds.y;
				System.out.println(helper.x);
				System.out.println(helper.y);


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

				minPoint.x = helper.x;
				minPoint.y = helper.y;
			}

			newPolly.setDrawingDenied(true);


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

			System.out.println(minPoint);
			System.out.println(maxPoint);

			if(minPoint.x != 0)
				for (int i = 0; i < builderList.size(); i++) {
					point = builderList.getPoint(i);
					point.x = point.x - minPoint.x;
					builderList.setPoint(point, i);
				}
			System.out.println("execute once");
			if(minPoint.y != 0)
				for (int i = 0; i < builderList.size(); i++) {
					point = builderList.getPoint(i);
					point.y = point.y - minPoint.y;
					builderList.setPoint(point, i);
				}

			//
			//
			//					minPoint.x += helper.x;
			//					minPoint.y += helper.y;
			//					maxPoint.x += helper.x;
			//					maxPoint.y += helper.y;
			//
			//					if(minPoint.x > lastPoint.x)
			//						for (int i = 0; i < builderList.size(); i++) {
			//							minPoint.x = lastPoint.x;
			//						}
			//
			//					if(minPoint.y > lastPoint.y)
			//						for (int i = 0; i < builderList.size(); i++) {
			//							minPoint.y = lastPoint.y;
			//						}


			lastPoint = new Point();
			lastPoint = builderList.getPoint(builderList.size()-1);
			final Point firstPoint = builderList.getPoint(0);
			//					lastPoint.x -= helper.x;
			//					lastPoint.y -= helper.y;
			//
			//					builderList.removePoint(builderList.size()-1);
			//					builderList.addPoint(lastPoint);

			if ( (lastPoint.x + 10 >= firstPoint.x && lastPoint.x - 10 <= firstPoint.x) && (lastPoint.y + 10 >= firstPoint.y && lastPoint.y - 10 <= firstPoint.y)) 
			{

				final Polly polly = new Polly();

				builderList.removePoint(builderList.size()-1);
				builderList.addPoint(firstPoint);

				polly.setList(builderList);

				//				maxPoint.x += helper.x;
				//				maxPoint.y += helper.y;


				bounds = new Rectangle(minPoint,maxPoint);
				System.out.println(bounds);

				polly.setBounds(bounds);
				polly.setLayout(bounds);
				polly.setLabelPosition(new Rectangle(0,0,10,10));
				//				if ( canvas.getChildren().size() >= 0)
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
			else {
				newPolly.setList(builderList);


				bounds = new Rectangle(minPoint,maxPoint);
				System.out.println(bounds);

				newPolly.setBounds(bounds);
				newPolly.setLayout(bounds);
				newPolly.setLabelPosition(new Rectangle(0,0,10,10));

				//				if ( canvas.getChildren().size() >= 0)
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


			builderList = new PointList();
		}
	}
	//			else if (newPolly.isDrawingDenied() == false) {
	//
	//				pollyLine.setBounds(new Rectangle(location, size));
	//				pollyLine.setLayout(new Rectangle(location, size));
	//
	//				final PointList pointList = new PointList(new int[] {0, 0, size.width, size.height});
	//
	//				pollyLine.setList(pointList);
	//				pollyLine.setDrawingDenied(true);
	//
	//				canvas.addChild(pollyLine);
	//			}
	//		} 		
	//		else if (size != null) {
	//
	//			pollyLine.setBounds(new Rectangle(location, size));
	//			pollyLine.setLayout(new Rectangle(location, size));
	//
	//			final PointList pointList = new PointList(new int[] {0, 0, size.width, size.height});
	//
	//			pollyLine.setList(pointList);
	//			pollyLine.setDrawingDenied(true);
	//
	//			canvas.addChild(pollyLine);
	//		}




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