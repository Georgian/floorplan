package com.ansis.floorplan.listener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.KeyEvent;
import org.eclipse.draw2d.KeyListener;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

import com.ansis.floorplan.model.Canvas;
import com.ansis.floorplan.model.ModelTest;
import com.ansis.floorplan.model.Polly;


public class MyListener {

	// ====================== 2. Instance Fields =============================

	private boolean isShiftPressed = false;

	private int i = 0;

	private PointList pointList = new PointList();

	private Rectangle rect = new Rectangle();

	private Rectangle label = new Rectangle();

	private Point lastPoint;


	// ==================== 4. Constructors ====================

	public MyListener(final Canvas model, final IFigure figure) {
		figure.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(final KeyEvent ke) {
				System.out.println("key pressed "+(++i));
				if (ke.keycode == SWT.SHIFT) {
					isShiftPressed = true;
					System.out.println("SHIFT is pressed: "+isShiftPressed);
				}
			}

			@Override
			public void keyReleased(final KeyEvent ke) {
				if (ke.keycode == SWT.SHIFT) {
					isShiftPressed = false;
					System.out.println("SHIFT is released: "+isShiftPressed);
				}
			}

		});

		figure.addMouseListener(new MouseListener() {
			@Override
			public void mouseDoubleClicked(final MouseEvent me) {

			}

			@Override
			public void mousePressed(final MouseEvent me) {

				if (me.button == 2) {
					//// ==================== left click add point ====================

					//Remember click in point list
					pointList.addPoint(new Point(me.x, me.y));
					lastPoint = new Point(me.x, me.y);

					System.out.println(pointList.size());
					System.out.println(me.x);
					System.out.println(me.y);
				}

				if (me.button == 3 && pointList.size() > 2) {

					Point pointmin = null, pointmax = null, point = null;
					final Point pointg = new Point (0,0), pointk = new Point(0,0);

					/////////////////
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

					////////////////

					final Polly polly3 = new Polly();
					polly3.setList(pointList);

					polly3.setName("polly test"); //$NON-NLS-1$
					polly3.setEtage(3);
					polly3.setBounds(rect);
					polly3.setR(rect);
					polly3.setG(label);

					model.addChild(polly3);

					pointList = new PointList();
					System.out.println("Number of children");
					System.out.println(ModelTest.getChildren().size());
				}

				if(me.button == 1 && isShiftPressed == true) {
					if (pointList.size() > 0) {	
						if(Math.abs(Math.abs(lastPoint.x)-Math.abs(me.x)) > Math.abs(Math.abs(lastPoint.y)-Math.abs(me.y)))	{
							pointList.addPoint(new Point(me.x, lastPoint.y));
							lastPoint = new Point(me.x, lastPoint.y);
							//							final Rectangle r = new Rectangle(lastPoint, new Point(me.x,lastPoint.y));
							//							add(new PolyFigure(pointList, r));
						} else {
							pointList.addPoint(new Point(lastPoint.x,me.y));
							lastPoint = new Point(lastPoint.x, me.y);
							//							final Rectangle r = new Rectangle(lastPoint, new Point(lastPoint.x,me.y));
							//							add(new PolyFigure(pointList, r));
						}
					}
				}
			}

			@Override
			public void mouseReleased(final MouseEvent me) {

			}
		});
	}

}