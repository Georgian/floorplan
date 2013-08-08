package com.ansis.floorplan.listener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.KeyEvent;
import org.eclipse.draw2d.KeyListener;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

import com.ansis.floorplan.model.Canvas;
import com.ansis.floorplan.model.ModelTest;
import com.ansis.floorplan.model.Polly;


public class MyListener {

	// ====================== 2. Instance Fields =============================

	public static boolean isShiftPressed = false;

	public static boolean isControlPressed = false;

	private int ok = 0;

	private PointList pointList = new PointList();

	private PointList pointList2 = new PointList();

	private Rectangle rect = new Rectangle();

	private Rectangle label = new Rectangle();

	private Point lastPoint;


	// ==================== 4. Constructors ====================

	public MyListener(final Canvas model, final IFigure figure) {
		figure.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(final KeyEvent ke) {
				//				System.out.println("key pressed "+(++i));
				//				if (ke.keycode == SWT.SHIFT) {
				//					isShiftPressed = true;
				/*System.out.println("SHIFT is pressed: "+isShiftPressed);
				} else if (ke.keycode == SWT.CONTROL) {
					isControlPressed = true;
					System.out.println("CONTROL is pressed: "+isControlPressed);*/
				//				}
			}

			@Override
			public void keyReleased(final KeyEvent ke) {
				//				if (ke.keycode == SWT.SHIFT) {
				//					isShiftPressed = false;
				/*System.out.println("SHIFT is released: "+isShiftPressed);
				} else if (ke.keycode == SWT.CONTROL) {
					isControlPressed = false;
					System.out.println("CONTROL is released: "+isControlPressed);*/
				//				}
			}

		});

		figure.addMouseListener(new MouseListener() {

			@Override
			public void mouseDoubleClicked(final MouseEvent me) {

			}

			@Override
			public void mousePressed(final MouseEvent me) {

				if(me.button == 1 && isShiftPressed == true) {
					//					System.out.println("SHIFT + Mouse 1");
					if (pointList2.size() > 0) {	
						if(Math.abs(Math.abs(lastPoint.x)-Math.abs(me.x)) > Math.abs(Math.abs(lastPoint.y)-Math.abs(me.y)))	{
							pointList2.addPoint(new Point(me.x, lastPoint.y));

							pointList = new PointList();
							pointList.addAll(pointList2);

							lastPoint = new Point(me.x, me.y);


							Point pointmin = null, pointmax = null, point = null;
							final Point pointg = new Point (0,0), pointk = new Point(0,0);

							///////////////////////////////////////////////////
							// Find min/max points. Compute bounds for our new figure

							for (int i = 0; i < pointList.size(); i++) {
								if (pointmin == null)
									pointmin = pointList.getPoint(i);

								if (pointmax == null)
									pointmax = pointList.getPoint(i);

								point = pointList.getPoint(i);

								if(point.x < pointmin.x)
									pointmin.x = point.x;
								if(point.x > pointmax.x)
									pointmax.x = point.x;
								if(point.y < pointmin.y)
									pointmin.y = point.y;
								if(point.y > pointmax.y)
									pointmax.y = point.y;
							}

							if(pointmin.x != 0)
								for (int i = 0; i < pointList.size(); i++) {
									point = pointList.getPoint(i);
									point.x = point.x - pointmin.x;
									pointList.setPoint(point, i);
									pointg.x = pointg.x + point.x;
								}

							if(pointmin.y != 0)
								for (int i = 0; i < pointList.size(); i++) {
									point = pointList.getPoint(i);
									point.y = point.y - pointmin.y;
									pointList.setPoint(point, i);
									pointg.y = pointg.y + point.y;
								}

							pointg.x = pointg.x/pointList.size()-35;
							pointg.y = pointg.y/pointList.size();

							pointk.x = pointg.x + 70;
							pointk.y = pointg.y + 15;



							rect = new Rectangle(pointmin,pointmax);

							label = new Rectangle(pointg, pointk);

							///////////////////////////////////////////////////


							final Polly polly3 = new Polly();
							polly3.setList(pointList);

							polly3.setName("Not Done!"); //$NON-NLS-1$
							polly3.setEtage(3);
							polly3.setBounds(rect);
							polly3.setR(rect);
							polly3.setG(label);

							model.addChild(polly3);


							ok = 1;

							final ModelTest model2 = new ModelTest();

							if ( model2.getChildren().size() >= 1 && ok == 1)
								model2.removeChild(model2.getChildren().get(model2.getChildren().size() - 1));
							System.out.println("Created a straight line");

							isShiftPressed = false;
							pointList = new PointList();
							//							final Rectangle r = new Rectangle(lastPoint, new Point(me.x,lastPoint.y));
							//							add(new PolyFigure(pointList, r));
						} 
						else {
							pointList2.addPoint(new Point(lastPoint.x, me.y));
							
							pointList = new PointList();
							pointList.addAll(pointList2);

							lastPoint = new Point(me.x, me.y);


							Point pointmin = null, pointmax = null, point = null;
							final Point pointg = new Point (0,0), pointk = new Point(0,0);

							///////////////////////////////////////////////////
							// Find min/max points. Compute bounds for our new figure

							for (int i = 0; i < pointList.size(); i++) {
								if (pointmin == null)
									pointmin = pointList.getPoint(i);

								if (pointmax == null)
									pointmax = pointList.getPoint(i);

								point = pointList.getPoint(i);

								if(point.x < pointmin.x)
									pointmin.x = point.x;
								if(point.x > pointmax.x)
									pointmax.x = point.x;
								if(point.y < pointmin.y)
									pointmin.y = point.y;
								if(point.y > pointmax.y)
									pointmax.y = point.y;
							}

							if(pointmin.x != 0)
								for (int i = 0; i < pointList.size(); i++) {
									point = pointList.getPoint(i);
									point.x = point.x - pointmin.x;
									pointList.setPoint(point, i);
									pointg.x = pointg.x + point.x;
								}

							if(pointmin.y != 0)
								for (int i = 0; i < pointList.size(); i++) {
									point = pointList.getPoint(i);
									point.y = point.y - pointmin.y;
									pointList.setPoint(point, i);
									pointg.y = pointg.y + point.y;
								}

							pointg.x = pointg.x/pointList.size()-35;
							pointg.y = pointg.y/pointList.size();

							pointk.x = pointg.x + 70;
							pointk.y = pointg.y + 15;



							rect = new Rectangle(pointmin,pointmax);

							label = new Rectangle(pointg, pointk);

							///////////////////////////////////////////////////


							final Polly polly3 = new Polly();
							polly3.setList(pointList);

							polly3.setName("Not Done!"); //$NON-NLS-1$
							polly3.setEtage(3);
							polly3.setBounds(rect);
							polly3.setR(rect);
							polly3.setG(label);

							model.addChild(polly3);


							ok = 1;

							final ModelTest model2 = new ModelTest();

							if ( model2.getChildren().size() >= 1 && ok == 1)
								model2.removeChild(model2.getChildren().get(model2.getChildren().size() - 1));
							System.out.println("Created a straight line");

							isShiftPressed = false;
							pointList = new PointList();
							//							final Rectangle r = new Rectangle(lastPoint, new Point(lastPoint.x,me.y));
							//							add(new PolyFigure(pointList, r));

						}
					}
				}
				
				if (me.button == 1 && isControlPressed == true) {
					//// ==================== left click add point ====================

					//Remember click in point list
					pointList2.addPoint(new Point(me.x, me.y));

					pointList = new PointList();
					pointList.addAll(pointList2);

					lastPoint = new Point(me.x, me.y);


					if (pointList.size() > 1)
					{
						Point pointmin = null, pointmax = null, point = null;
						final Point pointg = new Point (0,0), pointk = new Point(0,0);

						///////////////////////////////////////////////////
						// Find min/max points. Compute bounds for our new figure

						for (int i = 0; i < pointList.size(); i++) {
							if (pointmin == null)
								pointmin = pointList.getPoint(i);

							if (pointmax == null)
								pointmax = pointList.getPoint(i);

							point = pointList.getPoint(i);

							if(point.x < pointmin.x)
								pointmin.x = point.x;
							if(point.x > pointmax.x)
								pointmax.x = point.x;
							if(point.y < pointmin.y)
								pointmin.y = point.y;
							if(point.y > pointmax.y)
								pointmax.y = point.y;
						}

						if(pointmin.x != 0)
							for (int i = 0; i < pointList.size(); i++) {
								point = pointList.getPoint(i);
								point.x = point.x - pointmin.x;
								pointList.setPoint(point, i);
								pointg.x = pointg.x + point.x;
							}

						if(pointmin.y != 0)
							for (int i = 0; i < pointList.size(); i++) {
								point = pointList.getPoint(i);
								point.y = point.y - pointmin.y;
								pointList.setPoint(point, i);
								pointg.y = pointg.y + point.y;
							}

						pointg.x = pointg.x/pointList.size()-35;
						pointg.y = pointg.y/pointList.size();

						pointk.x = pointg.x + 70;
						pointk.y = pointg.y + 15;



						rect = new Rectangle(pointmin,pointmax);

						label = new Rectangle(pointg, pointk);

						///////////////////////////////////////////////////


						final Polly polly3 = new Polly();
						polly3.setList(pointList);

						polly3.setName("Not Done!"); //$NON-NLS-1$
						polly3.setEtage(3);
						polly3.setBounds(rect);
						polly3.setR(rect);
						polly3.setG(label);

						model.addChild(polly3);


						ok = 1;

						final ModelTest model2 = new ModelTest();

						if ( model2.getChildren().size() >= 1 && ok == 1)
							model2.removeChild(model2.getChildren().get(model2.getChildren().size() - 1));
						System.out.println("Created a Node");

						isControlPressed = false;
						pointList = new PointList();

					}					
					//					System.out.println(pointList.size() + me.x + me.y);
				}

				if (me.button == 3 && pointList2.size() > 2 && isControlPressed == true) {

					Point pointmin = null, pointmax = null, point = null;
					final Point pointg = new Point (0,0), pointk = new Point(0,0);

					///////////////////////////////////////////////////
					// Find min/max points. Compute bounds for our new figure

					for (int i = 0; i < pointList2.size(); i++) {
						if (pointmin == null)
							pointmin = pointList2.getPoint(i);

						if (pointmax == null)
							pointmax = pointList2.getPoint(i);

						point = pointList2.getPoint(i);

						if(point.x < pointmin.x)
							pointmin.x = point.x;
						if(point.x > pointmax.x)
							pointmax.x = point.x;
						if(point.y < pointmin.y)
							pointmin.y = point.y;
						if(point.y > pointmax.y)
							pointmax.y = point.y;
					}

					if(pointmin.x != 0)
						for (int i = 0; i < pointList2.size(); i++) {
							point = pointList2.getPoint(i);
							point.x = point.x - pointmin.x;
							pointList2.setPoint(point, i);
							pointg.x = pointg.x + point.x;
						}

					if(pointmin.y != 0)
						for (int i = 0; i < pointList2.size(); i++) {
							point = pointList2.getPoint(i);
							point.y = point.y - pointmin.y;
							pointList2.setPoint(point, i);
							pointg.y = pointg.y + point.y;
						}

					pointg.x = pointg.x/pointList2.size()-35;
					pointg.y = pointg.y/pointList2.size();

					pointk.x = pointg.x + 70;
					pointk.y = pointg.y + 15;

					rect = new Rectangle(pointmin,pointmax);

					label = new Rectangle(pointg, pointk);

					///////////////////////////////////////////////////

					final Polly polly4 = new Polly();
					polly4.setList(pointList2);

					polly4.setName("final polly"); //$NON-NLS-1$
					polly4.setEtage(4);
					polly4.setBounds(rect);
					polly4.setR(rect);
					polly4.setG(label);

					model.addChild(polly4);

					System.out.println("Finished Drawing");
					pointList2 = new PointList();

					//					System.out.println("Number of children: " + ModelTest.getChildren().size());
				}
			}

			@Override
			public void mouseReleased(final MouseEvent me) {

			}
		});
	}

}