package com.ansis.floorplan.listener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

import com.ansis.floorplan.model.Canvas;
import com.ansis.floorplan.model.ModelTest;
import com.ansis.floorplan.model.Polly;
import com.ansis.floorplan.model.PollyLine;


public class MyListener {

	// ====================== 2. Instance Fields =============================

	public static boolean isShiftPressed = false;

	public static boolean isControlPressed = false;

	private int ok = 0;

	private PointList tempPointList = new PointList();

	private PointList mainPointList = new PointList();

	private Rectangle rect = new Rectangle();

	private Rectangle label = new Rectangle();

	private Point lastPoint, firstPoint = null;


	// ==================== 4. Constructors ====================

	public MyListener(final Canvas model, final IFigure figure) {

		figure.addMouseListener(new MouseListener() {

			@Override
			public void mouseDoubleClicked(final MouseEvent me) {

			}

			@Override
			public void mousePressed(final MouseEvent me) {

				if(me.button == 1 && isShiftPressed == true) {
					if (mainPointList.size() > 0) {	
						if(Math.abs(Math.abs(lastPoint.x)-Math.abs(me.x)) > Math.abs(Math.abs(lastPoint.y)-Math.abs(me.y)))	{

							mainPointList.addPoint(new Point(me.x, lastPoint.y));

							tempPointList = new PointList();
							tempPointList.addAll(mainPointList);

							lastPoint = new Point(me.x, lastPoint.y);

							Point minPoint = null, maxPoint = null, point = null;
							final Point minLabelPoint = new Point (0,0), maxLabelPoint = new Point(0,0);

							///////////////////////////////////////////////////
							// Find min/max points. Compute bounds for our new figure

							for (int i = 0; i < tempPointList.size(); i++) {
								if (minPoint == null)
									minPoint = tempPointList.getPoint(i);

								if (maxPoint == null)
									maxPoint = tempPointList.getPoint(i);

								point = tempPointList.getPoint(i);

								if(point.x < minPoint.x)
									minPoint.x = point.x;
								if(point.x > maxPoint.x)
									maxPoint.x = point.x;
								if(point.y < minPoint.y)
									minPoint.y = point.y;
								if(point.y > maxPoint.y)
									maxPoint.y = point.y;
							}

							if(minPoint.x != 0)
								for (int i = 0; i < tempPointList.size(); i++) {
									point = tempPointList.getPoint(i);
									point.x = point.x - minPoint.x;
									tempPointList.setPoint(point, i);
									minLabelPoint.x = minLabelPoint.x + point.x;
								}

							if(minPoint.y != 0)
								for (int i = 0; i < tempPointList.size(); i++) {
									point = tempPointList.getPoint(i);
									point.y = point.y - minPoint.y;
									tempPointList.setPoint(point, i);
									minLabelPoint.y = minLabelPoint.y + point.y;
								}

							minLabelPoint.x = minLabelPoint.x/tempPointList.size()-35;
							minLabelPoint.y = minLabelPoint.y/tempPointList.size();

							maxLabelPoint.x = minLabelPoint.x + 70;
							maxLabelPoint.y = minLabelPoint.y + 15;

							rect = new Rectangle(minPoint,maxPoint);

							label = new Rectangle(minLabelPoint, maxLabelPoint);

							///////////////////////////////////////////////////

							final PollyLine newPolly = new PollyLine();
							newPolly.setList(tempPointList);

							newPolly.setBounds(rect);

							model.addChild(newPolly);

							ok = 1;

							final ModelTest model2 = new ModelTest();

							if ( ModelTest.getChildren().size() >= 1 && ok == 1)
								model2.removeChild(ModelTest.getChildren().get(ModelTest.getChildren().size() - 1));

							isShiftPressed = false;
							tempPointList = new PointList();
						} 
						else {

							mainPointList.addPoint(new Point(lastPoint.x, me.y));

							tempPointList = new PointList();
							tempPointList.addAll(mainPointList);

							lastPoint = new Point(lastPoint.x, me.y);

							Point minPoint = null, maxPoint = null, point = null;
							final Point minLabelPoint = new Point (0,0), maxLabelPoint = new Point(0,0);

							///////////////////////////////////////////////////
							// Find min/max points. Compute bounds for our new figure

							for (int i = 0; i < tempPointList.size(); i++) {
								if (minPoint == null)
									minPoint = tempPointList.getPoint(i);

								if (maxPoint == null)
									maxPoint = tempPointList.getPoint(i);

								point = tempPointList.getPoint(i);

								if(point.x < minPoint.x)
									minPoint.x = point.x;
								if(point.x > maxPoint.x)
									maxPoint.x = point.x;
								if(point.y < minPoint.y)
									minPoint.y = point.y;
								if(point.y > maxPoint.y)
									maxPoint.y = point.y;
							}

							if(minPoint.x != 0)
								for (int i = 0; i < tempPointList.size(); i++) {
									point = tempPointList.getPoint(i);
									point.x = point.x - minPoint.x;
									tempPointList.setPoint(point, i);
									minLabelPoint.x = minLabelPoint.x + point.x;
								}

							if(minPoint.y != 0)
								for (int i = 0; i < tempPointList.size(); i++) {
									point = tempPointList.getPoint(i);
									point.y = point.y - minPoint.y;
									tempPointList.setPoint(point, i);
									minLabelPoint.y = minLabelPoint.y + point.y;
								}

							minLabelPoint.x = minLabelPoint.x/tempPointList.size()-35;
							minLabelPoint.y = minLabelPoint.y/tempPointList.size();

							maxLabelPoint.x = minLabelPoint.x + 70;
							maxLabelPoint.y = minLabelPoint.y + 15;

							rect = new Rectangle(minPoint,maxPoint);

							label = new Rectangle(minLabelPoint, maxLabelPoint);

							///////////////////////////////////////////////////

							final PollyLine newPolly = new PollyLine();
							newPolly.setList(tempPointList);

							newPolly.setBounds(rect);

							model.addChild(newPolly);

							ok = 1;

							final ModelTest model2 = new ModelTest();

							if ( ModelTest.getChildren().size() >= 1 && ok == 1)
								model2.removeChild(ModelTest.getChildren().get(ModelTest.getChildren().size() - 1));

							isShiftPressed = false;
							tempPointList = new PointList();

						}
					}
				}

				if (me.button == 1 && isControlPressed == true) {

					//Remember click in point list
					if ( firstPoint == null)
						firstPoint = new Point(me.x, me.y);

					mainPointList.addPoint(new Point(me.x, me.y));

					tempPointList = new PointList();
					tempPointList.addAll(mainPointList);

					lastPoint = new Point(me.x, me.y);

					if (tempPointList.size() > 1)
					{
						Point minPoint = null, maxPoint = null, point = null;
						final Point minLabelPoint = new Point (0,0), maxLabelPoint = new Point(0,0);

						///////////////////////////////////////////////////
						// Find min/max points. Compute bounds for our new figure

						for (int i = 0; i < tempPointList.size(); i++) {
							if (minPoint == null)
								minPoint = tempPointList.getPoint(i);

							if (maxPoint == null)
								maxPoint = tempPointList.getPoint(i);

							point = tempPointList.getPoint(i);

							if(point.x < minPoint.x)
								minPoint.x = point.x;
							if(point.x > maxPoint.x)
								maxPoint.x = point.x;
							if(point.y < minPoint.y)
								minPoint.y = point.y;
							if(point.y > maxPoint.y)
								maxPoint.y = point.y;
						}

						if(minPoint.x != 0)
							for (int i = 0; i < tempPointList.size(); i++) {
								point = tempPointList.getPoint(i);
								point.x = point.x - minPoint.x;
								tempPointList.setPoint(point, i);
								minLabelPoint.x = minLabelPoint.x + point.x;
							}

						if(minPoint.y != 0)
							for (int i = 0; i < tempPointList.size(); i++) {
								point = tempPointList.getPoint(i);
								point.y = point.y - minPoint.y;
								tempPointList.setPoint(point, i);
								minLabelPoint.y = minLabelPoint.y + point.y;
							}

						minLabelPoint.x = minLabelPoint.x/tempPointList.size()-35;
						minLabelPoint.y = minLabelPoint.y/tempPointList.size();

						maxLabelPoint.x = minLabelPoint.x + 70;
						maxLabelPoint.y = minLabelPoint.y + 15;

						rect = new Rectangle(minPoint,maxPoint);

						label = new Rectangle(minLabelPoint, maxLabelPoint);

						///////////////////////////////////////////////////

						final PollyLine newPolly = new PollyLine();
						newPolly.setList(tempPointList);

						newPolly.setBounds(rect);

						model.addChild(newPolly);

						ok = 1;

						final ModelTest model2 = new ModelTest();

						if ( ModelTest.getChildren().size() >= 1 && ok == 1)
							model2.removeChild(ModelTest.getChildren().get(ModelTest.getChildren().size() - 1));

						tempPointList = new PointList();

					}					
				}

				if (me.button == 3 && mainPointList.size() > 2 && isControlPressed == true) {

					Point minPoint = null, maxPoint = null, point = null;
					final Point minLabelPoint = new Point (0,0), maxLabelPoint = new Point(0,0);

					mainPointList.addPoint(firstPoint);

					///////////////////////////////////////////////////
					// Find min/max points. Compute bounds for our new figure

					for (int i = 0; i < mainPointList.size(); i++) {
						if (minPoint == null)
							minPoint = mainPointList.getPoint(i);

						if (maxPoint == null)
							maxPoint = mainPointList.getPoint(i);

						point = mainPointList.getPoint(i);

						if(point.x < minPoint.x)
							minPoint.x = point.x;
						if(point.x > maxPoint.x)
							maxPoint.x = point.x;
						if(point.y < minPoint.y)
							minPoint.y = point.y;
						if(point.y > maxPoint.y)
							maxPoint.y = point.y;
					}

					if(minPoint.x != 0)
						for (int i = 0; i < mainPointList.size(); i++) {
							point = mainPointList.getPoint(i);
							point.x = point.x - minPoint.x;
							mainPointList.setPoint(point, i);
							minLabelPoint.x = minLabelPoint.x + point.x;
						}

					if(minPoint.y != 0)
						for (int i = 0; i < mainPointList.size(); i++) {
							point = mainPointList.getPoint(i);
							point.y = point.y - minPoint.y;
							mainPointList.setPoint(point, i);
							minLabelPoint.y = minLabelPoint.y + point.y;
						}

					minLabelPoint.x = minLabelPoint.x/mainPointList.size()-35;
					minLabelPoint.y = minLabelPoint.y/mainPointList.size();

					maxLabelPoint.x = minLabelPoint.x + 70;
					maxLabelPoint.y = minLabelPoint.y + 15;

					rect = new Rectangle(minPoint, maxPoint);

					label = new Rectangle(minLabelPoint, maxLabelPoint);

					///////////////////////////////////////////////////

					final Polly finalPolly = new Polly();
					finalPolly.setList(mainPointList);

					finalPolly.setBounds(rect);
					finalPolly.setLabelPosition(label);

					model.addChild(finalPolly);

					mainPointList = new PointList();
					firstPoint = null;

				}
			}

			@Override
			public void mouseReleased(final MouseEvent me) {

			}
		});
	}

}