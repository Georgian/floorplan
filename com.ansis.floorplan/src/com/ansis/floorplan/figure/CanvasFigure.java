package com.ansis.floorplan.figure;

import java.io.InputStream;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.ansis.floorplan.app.FloorPlanActivator;


public class CanvasFigure extends Figure {

	// ==================== 1. Static Fields ========================

	public static final String CACHED_IMG_ID = "com.ansis.floorplan.canvasBkgImage";


	// ==================== 4. Constructors ====================

	public CanvasFigure() {
		final XYLayout layout = new XYLayout();

		setLayoutManager(layout);
	}


	// ==================== 7. Getters & Setters ====================

	public void setLayout(final Rectangle rect) {
		setBounds(rect);
	}

	public void setImage(final InputStream imageStream) {
		FloorPlanActivator.getDefault().getImageCache().cacheImage(CACHED_IMG_ID, new Image(Display.getCurrent(), imageStream));
	}

	@Override
	protected void paintFigure(final Graphics graphics) {

		Image image = FloorPlanActivator.getDefault().getImageCache().getCachedImage(CACHED_IMG_ID);

		if (image == null) {
			super.paintFigure(graphics);
			return;
		}

		final Rectangle rect = getBounds().getCopy();
		final org.eclipse.swt.graphics.Rectangle imgBox = image.getBounds();
		graphics.drawImage(image, 0, 0, imgBox.width, imgBox.height, rect.x, rect.y, imgBox.width, imgBox.height);

		super.paintFigure(graphics);
	}



}